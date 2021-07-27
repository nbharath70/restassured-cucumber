package baseSteps;

import HelperClass.DataBaseHelper;
import HelperClass.ResourcePath;
import HelperClass.VerificationHelperClass;
import TestBase.TestBase;
import io.restassured.response.Response;
import org.apache.log4j.Logger;

import java.sql.Array;
import java.util.ArrayList;


public class FetchJobListBaseStep extends TestBase {
    Response response;
    DataBaseHelper dataBaseHelper=new DataBaseHelper();
    public VerificationHelperClass verificationHelperClass = new VerificationHelperClass();
    public static Logger log = getMyLogger(FetchJobListBaseStep.class);
    public ArrayList<String> scheduledName;


    public void hitFetchJobListAPI(String endpoint)
    {
        response=getCall(endpoint);
    }

    public void verifyFetchJobListAPIresponseCode (int statusCode)
    {
        verificationHelperClass.verifyStatusCode(response,statusCode);
        log.info("getjobStatuscode API's StatusCode is: "+statusCode);
    }

    public void verifyFetchJobListAPIResponseFormatJSON()
    {
        verifyResponseFormatIsJSON();
    }

    public void verifyFetchJobListAPIResponseDetails(String query,String columnName,String jsonPath){
        String actualQuery=getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES,query);
        String actualColumnname=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,columnName);
        String actualJsonPathofAPI=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,jsonPath);
        verificationHelperClass.verifyResponseJsonAndDbArrayByColumnNameWithoutPropertiesKey(response,actualJsonPathofAPI,actualQuery,actualColumnname);

    }

//    public void lobfromResponse(String query,String columnName){
////        String actualQuery=getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES,query);
//
//   String actualColumnname=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,columnName);
//        ArrayList<String> scheduledBy =dataBaseHelper.getDataColumnArrayListValueDB(query,actualColumnname);
//        for(int i=0;i<scheduledBy.size();i++){
//            if(scheduledBy.get(i).contains("@")){
//                int index=scheduledBy.indexOf("@");
//                 String name=scheduledBy.get(i).substring(0,index);
//                scheduledName.add(name);
//            }
//           else {
//               scheduledName.add(scheduledBy.get(i));
//            }
//
//        }
//        System.out.println(scheduledName);
//
//    }
}
