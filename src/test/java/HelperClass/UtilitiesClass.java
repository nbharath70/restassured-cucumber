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
        try {
            JSONParser jsonParser = new JSONParser();
            Reader reader = new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\initiateMFRContract.json");
            Object obj=jsonParser.parse(reader);
            JSONObject jsonObject=(JSONObject) obj;
            log.info(new GsonBuilder().setPrettyPrinting().create().toJson(jsonObject));
            Response response=postOperation("postInitiateNewManufacturerContract",jsonObject);

            if(JsonPath.read(response.asString(), "$.recordInserted")){
                log.info("Contract is successfully created");
                String queryTogetContractId="select * from [cfg].[MFR_Contract_header] where Lifecycle_Status='New' and Is_Current_Flag=1 and Manufacturer_ID='TEVA001' and Contract_Name='QAAutomationContract' and Start_Date='1993-01-01'";
                String contractIdCreated=dataBaseHelper.getSingleCellValueAsStringFromDB(queryTogetContractId,"contract_id");
                String contractRowKeyCreated=dataBaseHelper.getSingleCellValueAsStringFromDB(queryTogetContractId,"row_key");
                log.info("Contract Id created : "+contractIdCreated);
                log.info("Contract Rowkey created : "+contractRowKeyCreated);
                List<String> list=new ArrayList<String>();
                list.add(contractRowKeyCreated);
                list.add(contractIdCreated);
                return list;
            }
            else {
                log.info("Contract is not created check the response body");
                log.info("recommend you to delete QAAutomation contract for Teva and try creating");
            }

        }catch (Exception e){e.printStackTrace();}
        return null;
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
//            File jsonFile = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\updateContractAndSendToRebateOps.json");
            Reader reader = new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\updateContractAndSendToRebateOps.json");
            Object obj=jsonParser.parse(reader);
            JSONObject jsonObject=(JSONObject) obj;
            Map<String, Object> map=new HashMap<>();
            map.put("$.contractDetail.contractId", contractId);
            map.put("$.contractHeader.contractId", contractId);
            map.put("$.contractDetail.rowKey", contractRowKey);
            map.put("$.contractHeader.rowKey", contractRowKey);
            JSONObject modifiedJsonObject=jsonValueReplacer(jsonObject,map);
            log.info(new GsonBuilder().setPrettyPrinting().create().toJson(modifiedJsonObject));

            Response response=postOperation("updateMfrContractSendToRebateOps",modifiedJsonObject);
            if(JsonPath.read(response.asString(), "$.recordUpdated")){
                log.info("Contract is successfully Updated and sent to Rebate Ops");
                String processInstanceId=JsonPath.read(response.asString(), "$.processInstanceId");
                log.info("processInstanceId created: "+processInstanceId);

                return processInstanceId;
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


}
