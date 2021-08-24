package baseSteps;
import HelperClass.DataBaseHelper;
import HelperClass.ResourcePath;
import HelperClass.VerificationHelperClass;
import TestBase.TestBase;
import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FetchFQDetailsByTxnJobIdAndProgramRowKey extends TestBase {
    Response response;
    public VerificationHelperClass verificationHelperClass = new VerificationHelperClass();
    public static Logger log = getMyLogger(FetchFQDetailsByTxnJobIdAndProgramRowKey.class);
    DataBaseHelper dataBaseHelper=new DataBaseHelper();
    ArrayList<String> listOfRowKey=new ArrayList<String>();
    ArrayList<String> joinedList=new ArrayList<>();
    ResultSet resultSet;
    private String txnJobID;
    private String programId;

    public void getJobIDAndProgramRowKey(String query){
        resultSet=dataBaseHelper.getData(query);
        try {
            resultSet.next();
            String jobId=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"transactionJobID");
            String programIdcolumn=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"programID");
            txnJobID =String.valueOf(resultSet.getInt(jobId));
            programId=String.valueOf(resultSet.getInt(programIdcolumn));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void hitEndpoint(String endpoint){
        List<String> list= new ArrayList<String>();
        list.add(txnJobID);
        list.add(programId);
        response=getCall(endpoint,list);
    }
    public void hitEndpointwithInvalidProgramID(String endpoint){
        String ProgramID="0000";
        List<String> list= new ArrayList<String>();
        list.add(txnJobID);
        list.add(ProgramID);
        response=getCall(endpoint,list);
    }
    public void hitEndpointwithInvalidTransactionJobID(String endpoint){
        String txnJbId="0000";
        List<String> list= new ArrayList<String>();
        list.add(txnJbId);
        list.add(programId);
        response=getCall(endpoint,list);
    }
    public void hitEndpointwithBothBlankvalues(String endpoint){
        String txnJbId="";
        String blankProgramID="";
        List<String> list= new ArrayList<String>();
        list.add(txnJbId);
        list.add(blankProgramID);
        response=getCall(endpoint,list);
    }

    public void verifyStatusCode (int statusCode)
    {
        verificationHelperClass.verifyStatusCode(response,statusCode);
        log.info(" FetchFQDetailsByTxnJobIdAndProgramRowKey API's StatusCode is: "+statusCode);
    }
    public void verifyFormatJSON()
    {
        verifyResponseFormatIsJSON();
    }


    public void validateInvalidValues(String invalidMessage,String jsonKey)
    {
        String expectedValueMessage= getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,invalidMessage);
       String jsonPath=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,jsonKey);
       verificationHelperClass.verifyAPIResponseJsonWithDBJsonWithonlyStringDataTypeValues(response,expectedValueMessage,jsonPath,jsonPath);
    }
    public void getRebateOptionRowkey(String query,String columnName){
          ArrayList lORKY=dataBaseHelper.executePreparedQuerytoGetColumnArrayofIntValues(query,programId,columnName);
          for(int i=0;i<lORKY.size();i++){
              String param=lORKY.get(i).toString();
              listOfRowKey.add(param);
          }
          String result=String.join(",",listOfRowKey);
        joinedList.add(result);
    }
    public void validateRebateOptions(String query,String columnName){
        try{String preparedQuery=dataBaseHelper.preparedQueryWithListOfStrings(query,joinedList);
        String actualColumnName=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,columnName);
        String apiJsonPath=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"apiJsonPathRoRowKeyForfetchFormularyQualificationDetails");
        String dBJsonPath=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"DbJsonPathRoRowKeyForfetchFormularyQualificationDetails");
        resultSet=dataBaseHelper.getDataWithoutPropertiesKey(preparedQuery);
        resultSet.next();
        String dbJson=resultSet.getString(actualColumnName);
        verificationHelperClass.verifyAPIResponseJsonWithDBJsonAsWholeJson(response,dbJson,apiJsonPath,dBJsonPath);
        }catch (Exception e){
            e.printStackTrace();
        }

    }


}
