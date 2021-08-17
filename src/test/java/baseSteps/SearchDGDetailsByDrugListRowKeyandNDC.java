package baseSteps;

import HelperClass.DataBaseHelper;
import HelperClass.ResourcePath;
import HelperClass.VerificationHelperClass;
import TestBase.TestBase;
import cucumber.api.DataTable;
import io.restassured.response.Response;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SearchDGDetailsByDrugListRowKeyandNDC extends TestBase {
    DataBaseHelper dbHelper=new DataBaseHelper();
    VerificationHelperClass verificationHelper=new VerificationHelperClass();
    private String drugListRowKey=null;
    private String ndc=null;
    private Response response=null;
    private String dbJson=null;
    private List<String> listOfJsonPaths=null;


    public void executeSQLAndCapturesDGRowkey(String queryKey, String columnNameKey) {
            String columnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, columnNameKey);
            drugListRowKey = dbHelper.getSingleCellValueAsStringFromDB(queryKey, columnName);
            log.info("Drug list Id picked from data base: " + drugListRowKey);

    }

    public void executeQueryAndCaptureTheNDC(String queryKey, String columnNameKey) {
        String columnName=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, columnNameKey);
        ndc=dbHelper.getSingleCellValueAsStringFromDB(queryKey,columnName);
        log.info("NDC picked From data base: "+ndc);
    }

    public void hitsResourceWithGetAPIRequest(String endPointKey) {
        List<String> list= new ArrayList<String>();
        list.add(drugListRowKey);
        if(ndc==""){
            list.add(ndc);
        }
        else list.add(ndc.substring(0,9));
        response=getCall(endPointKey,list);
    }

    public void verifyCorrectStatusCodeFromAPIResponse(int statusCode) {
        verificationHelper.verifyStatusCode(response,statusCode);
    }

    public void executesTheQueryAndGetsDrugDetails(String queryKey,String columnName) {
       try {
           List<String> listOfStringsToReplace = new ArrayList<String>();
           listOfStringsToReplace.add(drugListRowKey);
           listOfStringsToReplace.add(ndc.substring(0,9));
           String query = dbHelper.preparedQueryWithListOfStrings(queryKey, listOfStringsToReplace);
           ResultSet rs = dbHelper.getDataWithoutPropertiesKey(query);
           rs.next();
           dbJson=rs.getString(columnName);
       }catch (Exception e) {e.printStackTrace();};
    }

    public void verifyTheDBJsonWithAPIResponseJson(DataTable dataTable) {
        listOfJsonPaths=verificationHelper.getListofJsonpathsFromPropertiesFile(dataTable);
        System.out.println(listOfJsonPaths);
        System.out.println("DBResponse:" +dbJson);
        System.out.println("APIResponse:"+response.getBody().asString());
        verificationHelper.verifyAPIResponseJsonWithDBJsonForListofJsonPaths(response,dbJson,listOfJsonPaths);

    }

    public void setsNullInDLRowkey() {
        drugListRowKey="";
    }

    public void validatesTheCorrectAndValidates(String errorMsgJsonKey, String errorMsgJsonPathKey) {
        String errorMsgJsonPath=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, errorMsgJsonPathKey);
        String errorMsgJson=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, errorMsgJsonKey);
        verificationHelper.verifyAPIResponseJsonWithDBJsonWithonlyStringDataTypeValues(response,errorMsgJson,errorMsgJsonPath,errorMsgJsonPath);
    }

    public void setNullInNDC() {
        ndc="";
    }

    public void hitsResourceWithGetAPIRequestWithNDCChars(String endPointKey) {
        List<String> list= new ArrayList<String>();
        list.add(drugListRowKey);
        if(ndc==""){
            list.add(ndc);
        }
        else list.add(ndc.substring(0,5));
        response=getCall(endPointKey,list);
    }
}
