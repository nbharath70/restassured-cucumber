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

    /**
     * @uthor: Rabbani
     *This Method is Used to get the ContractID
     * @param queryKey-
     */
    public void userExecutesQueryAndGetsContractID(String queryKey, String columnKey)
    {
        String columnName=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, columnKey);
        contractID=dbHelper.getSingleCellValueAsStringFromDB(queryKey, columnName);
        log.info("Contract ID picked from DB: " + contractID);
    }

    /**
     * @uthor: Rabbani
     *This Method is Used to hit the API
     * @param endPointKey-
     */
    public void userHitsAPI(String endPointKey) {

        jsonResponse=getCall(endPointKey,contractID);
    }

    /**
     * @uthor: Rabbani
     *This Method is Used to verify the Status code
     * @param expectedStatusCode-
     */
    public void verifyStatusCodeReturnedForBenefitRulesAPI(int expectedStatusCode) {
        verificationHelper.verifyStatusCode(jsonResponse,expectedStatusCode);
    }

    /**
     * @uthor: Rabbani
     *This Method is Used to get Benefit Rule Id and Benefit rule name
     * @param queryKey-
     */
    public void executeQueryAndGetBenefitRuleIdAndBenefitRuleNameAsJson(String queryKey) {
        try{
        resultSet=dbHelper.executePreparedQuery(queryKey,contractID);
        resultSet.next();
        benefitRulesAsJsonfromDB=resultSet.getString("benefit_rule_Json");
        }catch (Exception e){ e.printStackTrace(); }

    }

    /**
     * @uthor: Rabbani
     *This Method is Used to verify the Response
     */
    public void verifyAPIresponseJsonwithDBresponseAsJson() {
        String jsonPathforBenefitRuleId=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "jsonPathforBenefitRuleId");
        verificationHelper.verifyAPIResponseJsonWithDBJson(jsonResponse,benefitRulesAsJsonfromDB,jsonPathforBenefitRuleId);
        String jsonPathforBenefitRuleName=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "jsonPathforBenefitRuleName");
        verificationHelper.verifyAPIResponseJsonWithDBJson(jsonResponse,benefitRulesAsJsonfromDB,jsonPathforBenefitRuleName);

    }

    /**
     * @uthor: Rabbani
     *This Method is Used to hit the API with Invalid Contract ID
     * @param endPointKey,invalidContractId-
     */
    public void userHitsAPIWithInvalidContractId(String endPointKey, String invalidContractId) {
        String actualInvalidContractId=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,invalidContractId);
        jsonResponse=getCall(endPointKey,actualInvalidContractId);

    }

    /**
     * @uthor: Rabbani
     *This Method is Used to verify Error messages with
     * @param errorMsgJsonKey-
     */
    public void verifyAPIresponseErrorMsgWithExpectedErrorMsg(String errorMsgJsonKey) {
        String jsonPathforErrorMsg=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "jsonPathforErrorMsgOfBenefitRulesforBlankContractId");
        String errorMsgJsonAsString=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, errorMsgJsonKey);
        verificationHelper.verifyAPIResponseJsonWithDBJson(jsonResponse,errorMsgJsonAsString,jsonPathforErrorMsg);

    }

    /**
     * @uthor: Rabbani
     *This Method is Used to verify the Header codes of the API
     * @param expectedHeaderErrorCode-
     */
    public void verifyTheBenefitRulesForAContractResponseHeaderErrorCodeValue(String expectedHeaderErrorCode) {
        verificationHelper.verifyResponseHeaderApiReturnCodesValue(jsonResponse,expectedHeaderErrorCode);
    }
}
