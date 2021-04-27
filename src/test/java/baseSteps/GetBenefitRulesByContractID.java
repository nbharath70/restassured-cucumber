package baseSteps;

import HelperClass.DataBaseHelper;
import HelperClass.ResourcePath;
import HelperClass.VerificationHelperClass;
import TestBase.TestBase;
import io.restassured.response.Response;
import org.apache.log4j.Logger;

import java.sql.ResultSet;

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
}
