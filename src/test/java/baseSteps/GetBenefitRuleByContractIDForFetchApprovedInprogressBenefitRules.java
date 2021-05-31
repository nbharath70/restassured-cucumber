package baseSteps;

import HelperClass.DataBaseHelper;
import HelperClass.ResourcePath;
import HelperClass.VerificationHelperClass;
import TestBase.TestBase;
import com.jayway.jsonpath.JsonPath;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.junit.Assert;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import static io.restassured.RestAssured.given;

public class GetBenefitRuleByContractIDForFetchApprovedInprogressBenefitRules extends TestBase {
    DataBaseHelper dataBaseHelper=new DataBaseHelper();
    public VerificationHelperClass verificationHelperClass = new VerificationHelperClass();
    public static Logger log = getMyLogger(GetBenefitRuleByContractIDForFetchApprovedInprogressBenefitRules.class);
    Response response;
    public String jsonPath;
    public String columnName;
    public ResultSet result;
    private String contractID;

    /**
     * userExecutesQueryAndGetsContractID method is used to get contractID from MFR_Contract_Benefit_Rule table
     * @uthor ArunKumar
     * @param queryKey
     * @param columnKey
     */
    public void userExecutesQueryAndGetsContractID(String queryKey, String columnKey)
    {
        String columnName=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, columnKey);
        contractID=dataBaseHelper.getSingleCellValueAsStringFromDB(queryKey, columnName);
        log.info("Contract ID picked from DB: " + contractID);
    }

    /**
     * getOperationOfBenefitRuleByContractID this method is used to hit the get call for getBenefitRule by contractID of fetchApprovedBenefitRules true or false
     * @param endPoint
     * @param fetchApprovedBenefitRules
     * @return
     */
    public Response getOperationOfBenefitRuleByContractID(String endPoint,Boolean fetchApprovedBenefitRules)
    {
        try {
            response = given().pathParam("contractID", contractID)
                    .log().all().header("Authorization", "Bearer " + getPropertiesFileValue(ResourcePath.Environment_Properties, "bearerToken"))
                    .when().get(getEndPointUrl(endPoint) + "/{contractID}/" +"benefitrules?fetchApprovedBenefitRules="+fetchApprovedBenefitRules);
            log.info("****************** The Response JSON Body**************");
            log.info(response.getBody().jsonPath().prettify());
            response.then().assertThat().contentType(ContentType.JSON);
            log.info("The response is in proper JSON format");
            return response;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * This method is used validate GetBenefitRuleResponseHeader
     * @uthor ArunKumar
     * @param expectedHeaderValue
     */
    public void verifyGetBenefitRuleResponseHeader(String expectedHeaderValue)
    {
        verificationHelperClass.verifyResponseHeaderApiReturnCodesValue(response,expectedHeaderValue);
    }

    /**
     * This method is used to validate the status code of BenefitRuleByContractID
     * @uthor Arun Kumar
     * @param statusCode
     */
    public void verifyBenefitRuleByContractID(int statusCode)
    {
        verificationHelperClass.verifyStatusCode(response, statusCode);
        log.info("GetBenefitRuleByContractID StatusCode is " + statusCode + " and its Pass");
    }

    /**
     * validatingGetBenefitRuleByContractID Method is used to verify returned from API and DB
     * @param query
     * @uthor Arun Kumar
     */
    public void validatingGetBenefitRuleByContractID(String query) {
        if (query.equalsIgnoreCase("getBenefitRuleByContractIDOfBenefit_Rule_ID")) {
            jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "getBenefitRuleOfBenefitRuleIdJsonPath");
            columnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "getBenefitRuleByContractIDColumnBenefit_Rule_ID");
            String query1 = "select Benefit_Rule_ID from [cfg].[MFR_Contract_Benefit_Rule] where Contract_ID='"+contractID+"' and Is_Current_Flag=1";
            log.info("Validating GetBenefitRuleByContractID Response Body JsonPath=" + jsonPath + " and query=" + query);
            verificationHelperClass.verifyResponseJsonAndDbArrayByColumnNameForIntegerWithoutPropertiesKey(response, jsonPath, query1, columnName);
        } else if (query.equalsIgnoreCase("getBenefitRuleByContractIDOfBenefit_Rule_Name")) {
            jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "getBenefitRuleOfBenefitRuleNameJsonPath");
            columnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "getBenefitRuleByContractIDColumnBenefit_Rule_Name");
            String query1 = "select Benefit_Rule_Name  from [cfg].[MFR_Contract_Benefit_Rule] where Contract_ID='"+contractID+"' and Is_Current_Flag=1";
            log.info("Validating GetBenefitRuleByContractID Response Body JsonPath=" + jsonPath + " and query=" + query);
            verificationHelperClass.verifyResponseJsonAndDbArrayByColumnNameWithoutPropertiesKey(response, jsonPath, query1, columnName);
        }else if (query.equalsIgnoreCase("getBenefitRuleByContractIDOfFormulary_Type")) {
            jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "getBenefitRuleOfFormularyTypeJsonPath");
            columnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "getBenefitRuleByContractIDColumnFormulary_Type");
            String query1 = "select Formulary_Type from [cfg].[MFR_Contract_Benefit_Rule] where Contract_ID='"+contractID+"' and Is_Current_Flag=1";
            log.info("Validating GetBenefitRuleByContractID Response Body JsonPath=" + jsonPath + " and query=" + query);
            verificationHelperClass.verifyResponseJsonAndDbArrayByColumnNameWithoutPropertiesKey(response, jsonPath, query1, columnName);
        }else if (query.equalsIgnoreCase("getBenefitRuleByContractIDOfAbove_Value")) {
            jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "getBenefitRuleOfAboveValueJsonPath");
            columnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "getBenefitRuleByContractIDColumnAbove_Value");
            String query1 = "select Above_Value from [cfg].[MFR_Contract_Benefit_Rule] where Contract_ID='"+contractID+"' and Is_Current_Flag=1";
            log.info("Validating GetBenefitRuleByContractID Response Body JsonPath=" + jsonPath + " and query=" + query);
            verifyResponseJsonAndDbArrayByColumnNameForAboveBelowValue(response, jsonPath, query1, columnName);
        }else if (query.equalsIgnoreCase("getBenefitRuleByContractIDOfBelow_Value")) {
            jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "getBenefitRuleOfBelowValueJsonPath");
            columnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "getBenefitRuleByContractIDColumnBelow_Value");
            String query1 = "select Below_Value from [cfg].[MFR_Contract_Benefit_Rule] where Contract_ID='"+contractID+"' and Is_Current_Flag=1";
            log.info("Validating GetBenefitRuleByContractID Response Body JsonPath=" + jsonPath + " and query=" + query);
            verifyResponseJsonAndDbArrayByColumnNameForAboveBelowValue(response, jsonPath, query1, columnName);
        }
    }
    /**
     * verifyResponseJsonAndDbArrayByColumnNameForAboveBelowValue method is used verify the given jsonPath and DB query column value ArrayList value
     * @param response
     * @param actualJsonPath
     * @param Query
     * @param ColumnName
     * @uthor Arun Kumar
     */
    public void verifyResponseJsonAndDbArrayByColumnNameForAboveBelowValue(Response response, String actualJsonPath, String Query, String ColumnName) {
        try {
            ArrayList<String> actualValue = JsonPath.read(response.asString(), actualJsonPath);
            ArrayList<Integer> expectedValue = dataBaseHelper.getDataColumnArrayListValueDBIntergerWithoutPropertiesKey(Query, ColumnName);
            Iterator<String> itr1 = actualValue.iterator();
            Iterator<Integer> itr2 = expectedValue.iterator();
            System.out.println("Json"+actualValue);
            System.out.println("DB"+expectedValue);
            while(itr1.hasNext())
            {
                if(itr1.next()==null)
                {
                    itr1.remove();
                }
            }
            while(itr2.hasNext())
            {
                if(itr2.next()==0)
                {
                    itr2.remove();
                }
            }
            ArrayList<String> arr1 = new ArrayList<String>();
            for(int i=0;i<expectedValue.size();i++){
                String s =String.valueOf("\""+expectedValue.get(i)+".00\"").trim();
                arr1.add(s);
            }
            System.out.println("Json"+actualValue);
            System.out.println("DB"+arr1);
            Collections.sort(actualValue);
            Collections.sort(arr1);
            log.info("expectedValue from DB" + arr1 + " And actualValue from Json response=" + actualValue);
            Assert.assertEquals("The lists do not match!", arr1,actualValue);
            log.info("Verification pass where expectedValue=" + arr1 + " equals to actualValue=" + actualValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
