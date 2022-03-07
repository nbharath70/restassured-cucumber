package HelperClass;
import TestBase.TestBase;
import com.google.gson.GsonBuilder;
import com.jayway.jsonpath.JsonPath;
import cucumber.api.java.gl.E;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.*;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;

public class UtilitiesClass extends TestBase {
    int createContractAttempts=0;
    DataBaseHelper dataBaseHelper=new DataBaseHelper();
    /**
     * getStartDate method is used to get the current date by formate yyyy-MM-dd
     * @Author Arun Kumar
     * @return String current date
     */
    public String getStartDate() {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }

    /**
     * getEndDate method is used to get the end date where current date month+3
     * @Arun Kumar
     * @return String
     */
    public String getEndDate() {
        String format = "yyyy-MM-dd" ;
        SimpleDateFormat sdf = new SimpleDateFormat(format) ;
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 3);
        Date dateAsObjAfterAMonth = cal.getTime() ;
        return sdf.format(dateAsObjAfterAMonth);
    }
    /**
     * initiateMFRContract method is used to
     * @Rabbani
     * @return list - list of Contract Id Created and row key created
     */
    public List<String> initiateMFRContract() {
        List<String> list=new ArrayList<String>();
        try {
            JSONParser jsonParser = new JSONParser();
            Reader reader = new FileReader(System.getProperty("user.dir")+"\\src\\test\\testdata\\initiateMFRContract1.json");
            Object obj=jsonParser.parse(reader);
            JSONObject jsonObject=(JSONObject) obj;
            //log.info(new GsonBuilder().setPrettyPrinting().create().toJson(jsonObject));
            Response response=postOperation("postInitiateNewManufacturerContract",jsonObject);

            if(JsonPath.read(response.asString(), "$.recordInserted")){
                log.info("Contract is successfully created");
                String contractIdCreated=dataBaseHelper.getSingleCellValueAsStringFromDB("sqlTogetContractIdAndRowkey","contract_id");
                String contractRowKeyCreated=dataBaseHelper.getSingleCellValueAsStringFromDB("sqlTogetContractIdAndRowkey","row_key");
                log.info("Contract Id created : "+contractIdCreated);
                log.info("Contract Rowkey created : "+contractRowKeyCreated);
                list.add(contractIdCreated);
                list.add(contractRowKeyCreated);

            }
            else {
                log.info("Contract is not created check the response body");
                log.info("Deleting QAAutomation contract for Teva and trying to recreate");
                //do delete operation
                String deleteQAAutomationContract=getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES, "sqlToDeleteQAAutomationContract");
                dataBaseHelper.executeDeleteQueryWithoutreadingFromPropFile(deleteQAAutomationContract);
                //delete sucessful and trying to create contract
                log.info("Existing Contract Deleted successfully and trying to recreate QAAutomation contract");
                if(createContractAttempts==3){
                    log.info("3 times create QA Automation contract attempt failed ");
                }

                else {
                    createContractAttempts++;
                    List<String> list1= initiateMFRContract();
                return list1;
                }

            }

        }catch (Exception e){e.printStackTrace();}
        return list;
    }
    /**
     * updateContractAndSendToRebateOps method is used to
     * @Rabbani
     * @param contractId   -
     * @param contractRowKey -
     * @return processInstanceId as String
     */
    public String updateContractAndSendToRebateOps(String contractId, int contractRowKey){
        try {
            JSONParser jsonParser = new JSONParser();
            Reader reader = new FileReader(System.getProperty("user.dir")+"\\src\\test\\testdata\\updateContractAndSendToRebateOps.json");
            Object obj=jsonParser.parse(reader);
            JSONObject jsonObject=(JSONObject) obj;
            Map<String, Object> map=new HashMap<>();
            map.put("$.contractDetail.contractId", contractId);
            map.put("$.contractHeader.contractId", contractId);
            map.put("$.contractDetail.rowKey", contractRowKey);
            map.put("$.contractHeader.rowKey", contractRowKey);
            JSONObject modifiedJsonObject=jsonValueReplacer(jsonObject,map);
            //log.info(new GsonBuilder().setPrettyPrinting().create().toJson(modifiedJsonObject));

            Response response=postOperation("updateMfrContractSendToRebateOps",modifiedJsonObject);
            if(JsonPath.read(response.asString(), "$.recordUpdated")){
                log.info("Contract is successfully Updated and sent to Rebate Ops");
                String processInstanceId=JsonPath.read(response.asString(), "$.processInstanceId");
                log.info("processInstanceId created: "+processInstanceId);

                return processInstanceId;
            }
            else{
                log.info("ProcessInstanceId is not created please check the response body above");

            }

        }catch (Exception e){e.printStackTrace();}
        return null;
    }
    /**
     * jsonValueReplacer method is used to
     * @Rabbani
     * @return list - list of Contract Id Created and row key created
     */
    public JSONObject jsonValueReplacer(JSONObject jsonObject, Map<String,Object> map){
        try{
            Configuration configuration = Configuration
                    .builder()
                    .options(Option.SUPPRESS_EXCEPTIONS)
                    .build();
            DocumentContext parsed = JsonPath.using(configuration).parse(jsonObject);

            for(Map.Entry<String,Object> me:map.entrySet()){
                parsed.set(me.getKey(),me.getValue());
            }
            JSONObject modifiedJsonObject=new JSONObject(parsed.json());
            return modifiedJsonObject;
        }catch (Exception e){e.printStackTrace();}
        return null;
    }

    /**
     * jsonValueRemover method is used to remove key and values from a nested Json object
     * @Rabbani
     * @param jsonObject -
     * @param listOfJsonPaths -
     * @return JsonObject -
     */
    public JSONObject jsonValueRemover(JSONObject jsonObject, List<String> listOfJsonPaths){
        try{
            Configuration configuration = Configuration
                    .builder()
                    .options(Option.SUPPRESS_EXCEPTIONS)
                    .build();
            DocumentContext parsed = JsonPath.using(configuration).parse(jsonObject);

            for(String jsonPath:listOfJsonPaths){
                parsed.delete(jsonPath);
            }
            JSONObject modifiedJsonObject=new JSONObject(parsed.json());
            return modifiedJsonObject;
        }catch (Exception e){e.printStackTrace();}
        return null;
    }

    /**
     * deleteFlowableWithProcInstId method is used to delete all the entries related to flowable with Process instance Id
     * @Rabbani
     * @return list - list of Contract Id Created and row key created
     */
    public void deleteFlowableWithProcInstId(String procInstId){
//        String deleteFlowableQuery=getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES,"sqlToDeleteFlowableWithProcInstId");
        String finalDeleteQuery=dataBaseHelper.replaceMultipleQueryParamWithOneString("sqlToDeleteFlowableWithProcInstId",procInstId);
        dataBaseHelper.executeDeleteQueryWithoutreadingFromPropFile(finalDeleteQuery);
        log.info("Flowable records related to the ProcessInstance Id: "+procInstId + " are deleted successfully");

    }


}
