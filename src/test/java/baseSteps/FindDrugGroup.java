package baseSteps;

import HelperClass.DataBaseHelper;
import HelperClass.ResourcePath;
import HelperClass.VerificationHelperClass;
import TestBase.TestBase;
import io.restassured.response.Response;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FindDrugGroup extends TestBase {
    public ResultSet result;
    private String manufactureName;
    private String jsonPath;
    private String columnNameofDruggroupsummary;
    private String manufactureDruglistID;
    private String numberOfApprovedNDC;
    private String numberOfPendingNDC;
    public static Logger log = getMyLogger(FetchProgramsToGrid.class);
    public static Response findDrugGroupResponse;
    public VerificationHelperClass verificationHelperClass = new VerificationHelperClass();
    public DataBaseHelper dbHepler = new DataBaseHelper();
    ResultSet resultSet;


    public void getManufactuereNameforDrugGroup(String query){
        try {
            log.info("query is "+query);
            result = dbHepler.getData(query);
            result.next();
            String mfrnameColumName=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "manufacturerName");
            manufactureName = result.getString(mfrnameColumName);
            log.info("Program ContractID is  " + manufactureName + " From DB");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void hitFindDruggroupEndpoint(String endpoint){
        findDrugGroupResponse=getCall(endpoint,manufactureName);
        log.info("Response of FindDrugGroup API is "+findDrugGroupResponse);
    }
    public void hitFindDruggroupEndpoint(String endpoint,String manufactureName){
        findDrugGroupResponse=getCall(endpoint,manufactureName);
        log.info("Response of FindDrugGroup API is "+findDrugGroupResponse);
        String s=findDrugGroupResponse.getHeader("rb-api-result");
        log.info("fetchProgramsToGridResponse error code"+s);
    }
    public void validateJSONResponse(String query,String columnName){
        try{
            String actualColumnName=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,columnName);
            String apiJsonPath=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"jsonPathForResponsevalidation");
            String dBJsonPath=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"jsonPathForResponsevalidation");
            resultSet=dbHepler.executePreparedQuery(query,manufactureName);
            resultSet.next();
            String dbJson=resultSet.getString(actualColumnName);
            verificationHelperClass.verifyAPIResponseJsonWithDBJsonAsWholeJson(findDrugGroupResponse,dbJson,apiJsonPath,dBJsonPath);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void verifiyStatusCode(int statusCode){
        verificationHelperClass.verifyStatusCode(findDrugGroupResponse,statusCode);
        log.info(" FindDrugGroup API StatusCode is " + statusCode + " and its Pass");
    }
    public void verifyFormatOfJSONBody(){
        verifyResponseFormatIsJSON();
    }
    public void verifyFindDrugGroupSummaryWithDbTable(String query,String columnName){
        if(columnName.equalsIgnoreCase("lifeCycleStatus")){
            jsonPath=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"lifeCycleStatusFinddrugGroupApi");
            columnNameofDruggroupsummary=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,columnName);

        } else if(columnName.equalsIgnoreCase("manufacturerDrugListID")){
            jsonPath=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"manufacturerDrugListIDFinddrugGroupApi");
            columnNameofDruggroupsummary=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,columnName);

        } else if(columnName.equalsIgnoreCase("manufacturerNameofDrugGroup")){
            jsonPath=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"manufacturerNameofDrugGroupFinddrugGroupApi");
            columnNameofDruggroupsummary=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,columnName);

        } else if(columnName.equalsIgnoreCase("drugGroupName")){
            jsonPath=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"drugGroupNameFinddrugGroupApi");
            columnNameofDruggroupsummary=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,columnName);
        }
        else if(columnName.equalsIgnoreCase("drugGroupType")){
            jsonPath=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"drugGroupTypeFinddrugGroupApi");
            columnNameofDruggroupsummary=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,columnName);
        }
//        else if(columnName.equalsIgnoreCase("drugGroupTypeDesc")){
//            jsonPath=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"drugGroupTypeDescFinddrugGroupApi");
//            columnNameofDruggroupsummary=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,columnName);
//        }
        else if(columnName.equalsIgnoreCase("mode")){
            jsonPath=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"modeFinddrugGroupApi");
            columnNameofDruggroupsummary=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,columnName);
        }
//        else if(columnName.equalsIgnoreCase("drugListRuleId")){
//            jsonPath=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"drugListRuleIdFinddrugGroupApi");
//            columnNameofDruggroupsummary=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,columnName);
//        }
        else if(columnName.equalsIgnoreCase("numberOfApprovedNDC")){
            jsonPath=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"numberOfApprovedNDCFinddrugGroupApi");
            columnNameofDruggroupsummary=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,columnName);
        }else if(columnName.equalsIgnoreCase("numberOfPendingNDC")){
            jsonPath=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"numberOfPendingNDCFinddrugGroupApi");
            columnNameofDruggroupsummary=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,columnName);
        }

        verificationHelperClass.verifyResponseJsonAndDbArrayByColumnNameWithPreparedQuery(findDrugGroupResponse ,jsonPath,query,columnNameofDruggroupsummary,manufactureName);
    }
    public void verifyFindDrugListRuleIDAndDrugListDescription(String query,String columnName){
        if(columnName.equalsIgnoreCase("drugListRuleId")){
            jsonPath=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"drugListRuleIdFinddrugGroupApi");
            columnNameofDruggroupsummary=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,columnName);
        }else if(columnName.equalsIgnoreCase("drugGroupTypeDesc")){
            jsonPath=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"drugGroupTypeDescFinddrugGroupApi");
            columnNameofDruggroupsummary=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,columnName);
        }else if(columnName.equalsIgnoreCase("manufacturerDrugListID")){
            jsonPath=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"manufacturerDrugListIDFinddrugGroupApi");
            columnNameofDruggroupsummary=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,columnName);
        }
        verificationHelperClass.verifyResponseJsonAndDbArrayByColumnNameHavingNullValuesWithPreparedQuery(findDrugGroupResponse,jsonPath,query,columnNameofDruggroupsummary,manufactureName);
    }
    public String getManufactuereNameforDrugList(String query){
        try {
            log.info("query is "+query);
            result = dbHepler.executePreparedQuery(query,manufactureName);
            result.next();
            String mfrnameColumNameForDrugList=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "manufacturerDrugListID");
            manufactureDruglistID = result.getString(mfrnameColumNameForDrugList);
            log.info("Program ContractID is  " + manufactureDruglistID + " From DB");
        } catch (SQLException e) {
            e.printStackTrace();
        } return manufactureDruglistID;
    }
//    public void verifyManufactuereDrugListID(String jsonPathforMfrdlID){
//        jsonPath=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,jsonPathforMfrdlID);
//        verificationHelperClass.verifyAPIResponseJsonWithDBJsonWithDifferentDataTypeValues(findDrugGroupResponse,manufactureDruglistID,jsonPath);
//    }
    public String getNumberOfApprovedNdc(String query){
        try {
            log.info("query is "+query);
            result = dbHepler.executePreparedQuery(query,manufactureName);
            result.next();
            String mfrnameColumNameForDrugList=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "numberOfApprovedNDC");
            numberOfApprovedNDC = result.getString(mfrnameColumNameForDrugList);
            log.info("Program ContractID is  " + numberOfApprovedNDC + " From DB");
        } catch (SQLException e) {
            e.printStackTrace();
        } return numberOfApprovedNDC;
    }
    public void verifyNumberOfApprovedNDC(String jsonPathforNumberOfApprovedNDC){
        jsonPath=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,jsonPathforNumberOfApprovedNDC);
        verificationHelperClass.verifyAPIResponseJsonWithDBJsonWithDifferentDataTypeValues(findDrugGroupResponse,numberOfApprovedNDC,jsonPath);
    }
    public String getNumberOfPendingNdc(String query){
        try {
            log.info("query is "+query);
            result = dbHepler.executePreparedQuery(query,manufactureName);
            result.next();
            String mfrnameColumNameForDrugList=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "numberOfPendingNDC");
            numberOfPendingNDC = result.getString(mfrnameColumNameForDrugList);
            log.info("Program ContractID is  " + numberOfPendingNDC + " From DB");
        } catch (SQLException e) {
            e.printStackTrace();
        } return numberOfPendingNDC;
    }
    public void verfyNumberOfPendingNDC(String jsonPathforNumberOfPendingNDC){
        jsonPath=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,jsonPathforNumberOfPendingNDC);
        verificationHelperClass.verifyAPIResponseJsonWithDBJsonWithDifferentDataTypeValues(findDrugGroupResponse,numberOfPendingNDC,jsonPath);
    }
    public void validateFindDrugGroupResponse(String actualValue,String expectedValue)
    {
        String expectedValueMessage= getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,expectedValue);
        verificationHelperClass.verifyResponseJsonString(findDrugGroupResponse,actualValue,expectedValueMessage);
    }

    public void verifyFindDrugGroupResponseResponseHeaderErrorCode(String expectedHeaderValue)
    {
        verificationHelperClass.verifyResponseHeaderApiReturnCodesValue(findDrugGroupResponse,expectedHeaderValue);
    }


}
