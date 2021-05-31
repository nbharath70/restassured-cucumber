package baseSteps;

import HelperClass.DataBaseHelper;
import HelperClass.ResourcePath;
import HelperClass.VerificationHelperClass;
import TestBase.TestBase;
import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.junit.Assert;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;

public class GetBenefitRulesByContractID extends TestBase {
    DataBaseHelper dbHelper=new DataBaseHelper();
    VerificationHelperClass verificationHelper=new VerificationHelperClass();
    public static Logger log= TestBase.getMyLogger(GetBenefitRulesByContractID.class);
    private String contractID=null;
    private Response jsonResponse=null;
    private String benefitRuleId=null;
    private String benefitRuleName=null;
    private ResultSet resultSet=null;
    private String benefitRulesAsJsonfromDB=null;

    public void userExecutesQueryAndGetsContractID(String queryKey, String columnKey)
    {
        String columnName=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, columnKey);
        contractID=dbHelper.getSingleCellValueAsStringFromDB(queryKey, columnName);
        log.info("Contract ID picked from DB: " + contractID);
    }

    public void userHitsAPI(String endPointKey) {

        jsonResponse=getCall(endPointKey,contractID);
    }

    public void verifyStatusCodeReturnedForBenefitRulesAPI(int expectedStatusCode) {
        verificationHelper.verifyStatusCode(jsonResponse,expectedStatusCode);
    }

    //Next scenario
    public void executeQueryAndGet_benefitRuleId_And_benefitRuleNameAsJson(String queryKey) {
        try{
        resultSet=dbHelper.executePreparedQuery(queryKey,contractID);
        resultSet.next();
        benefitRulesAsJsonfromDB=resultSet.getString("benefit_rule_Json");
        }catch (Exception e){ e.printStackTrace(); }

    }


    public void verifyAPIresponseJsonwithDBresponseAsJson() {
        String jsonPathforBenefitRuleId=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "jsonPathforBenefitRuleId");
        verificationHelper.verifyAPIResponseJsonWithDBJson(jsonResponse,benefitRulesAsJsonfromDB,jsonPathforBenefitRuleId);
        String jsonPathforBenefitRuleName=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "jsonPathforBenefitRuleName");
        verificationHelper.verifyAPIResponseJsonWithDBJson(jsonResponse,benefitRulesAsJsonfromDB,jsonPathforBenefitRuleName);

    }

    public void userHitsAPIWithInvalidContractId(String endPointKey, String invalidContractId) {
        jsonResponse=getCall(endPointKey,invalidContractId);

//        String s=jsonResponse.getHeader("rb-api-result");
//        System.out.println("Hiiiiii"+ s);
//        int actualCode = JsonPath.read(s, "$.apiReturnCodes[0]");
//        System.out.println(actualCode);
//        String expectedErrorCodeString=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "expectedErrorCode");
//        int expectedErrorCode=Integer.valueOf(expectedErrorCodeString);
//        System.out.println(expectedErrorCode);
//        Assert.assertEquals("The lists do not match!", expectedErrorCode,actualCode);
//        log.info("Verification pass where expectedValue=" + expectedErrorCode + " equals to actualValue=" + actualCode);

    }

    public void verifyAPIresponseErrorMsgWithExpectedErrorMsg(String errorMsgJsonKey) {
        String jsonPathforErrorMsg=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "jsonPathforErrorMsgOfBenefitRulesforBlankContractId");
        String errorMsgJsonAsString=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, errorMsgJsonKey);
        verificationHelper.verifyAPIResponseJsonWithDBJson(jsonResponse,errorMsgJsonAsString,jsonPathforErrorMsg);

    }

    public void verifyTheBenefitRulesForAContractResponseHeaderErrorCodeValue(String expectedHeaderErrorCode) {
        verificationHelper.verifyResponseHeaderApiReturnCodesValue(jsonResponse,expectedHeaderErrorCode);
    }
}
