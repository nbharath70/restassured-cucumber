package baseSteps;
import HelperClass.DataBaseHelper;
import HelperClass.ResourcePath;
import HelperClass.VerificationHelperClass;
import RequestPojo.*;
import TestBase.TestBase;
import com.jayway.jsonpath.JsonPath;
import cucumber.api.DataTable;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.junit.Assert;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
public class CreateBenefitRuleForAContract extends TestBase {
    public static Logger log = getMyLogger(CreateBenefitRuleForAContract.class);
    DataBaseHelper dataBaseHelper =new DataBaseHelper();
    VerificationHelperClass verificationHelperClass= new VerificationHelperClass();
    Response response;
    BenefitRule benefitRuleforAContract;
    ResultSet resultSet;
    private String contractId;
    public void getContractIdToUpdateAndCreateBenefitRule(String query){
        try{resultSet= dataBaseHelper.getData(query);
            resultSet.next();
            String contractIdColumn=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "contractId");
            contractId = resultSet.getString(contractIdColumn);
            log.info("Contract ID for a ActiveContract " + contractId + " From DB");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void updateLifeCycleStatusToInprogressForContract(String contractHeaderQuery,String contractDetailQuery)
    {
        dataBaseHelper.executeUpdatePreparedQueryAsString(contractHeaderQuery,contractId);
        dataBaseHelper.executeUpdatePreparedQueryAsString(contractDetailQuery,contractId);
    }
    public void updateLifeCycleStatusToNewForContract(String contractHeaderQuery,String contractDetailQuery)
    {
        dataBaseHelper.executeUpdatePreparedQueryAsString(contractHeaderQuery,contractId);
        dataBaseHelper.executeUpdatePreparedQueryAsString(contractDetailQuery,contractId);
    }
    public void verifyStatusCodeofCreateBenefitRuleAPI(int StatusCode){
        verificationHelperClass.verifyStatusCode(response,StatusCode);
    }
    public void deleteCreatedBenefitRule(String deleteBenefitRuleQuery){
        dataBaseHelper.executeUpdatePreparedQueryAsString(deleteBenefitRuleQuery,contractId);
    }
    public void createBenefitRuleJSONBody(DataTable dataTable) {
        try {
            List<Map<String, String>> benefitRuleData = dataTable.asMaps(String.class, String.class);
            for (Map<String, String> map : benefitRuleData) {
                if(map.get("aboveValue").isEmpty()&&map.get("belowValue").isEmpty()){
                    ArrayList<BenefitRule.BenefitRules1> benefitRules1 = new ArrayList<BenefitRule.BenefitRules1>();
                    BenefitRule.BenefitRules1 benefitRules2 = new BenefitRule.BenefitRules1();
                    benefitRules2.setBenefitRuleName(map.get("benefitRuleName"));
                    benefitRules2.setFormularyType(map.get("formularyType"));
                    benefitRules2.setAboveValueString(map.get("aboveValue"));
                    benefitRules2.setBelowValueString(map.get("belowValue"));
                    benefitRules1.add(benefitRules2);
                    benefitRuleforAContract=new BenefitRule(contractId,benefitRules1);}
                else if(map.get("aboveValue").isEmpty()){
                    ArrayList<BenefitRule.BenefitRules1> benefitRules1 = new ArrayList<BenefitRule.BenefitRules1>();
                    BenefitRule.BenefitRules1 benefitRules2 = new BenefitRule.BenefitRules1();
                    benefitRules2.setBenefitRuleName(map.get("benefitRuleName"));
                    benefitRules2.setFormularyType(map.get("formularyType"));
                    benefitRules2.setAboveValueString(map.get("aboveValue"));
                    benefitRules2.setBelowValue(Integer.valueOf(map.get("belowValue")));
                    benefitRules1.add(benefitRules2);
                    benefitRuleforAContract=new BenefitRule(contractId,benefitRules1);
                }
                else if(map.get("belowValue").isEmpty()){
                    ArrayList<BenefitRule.BenefitRules1> benefitRules1 = new ArrayList<BenefitRule.BenefitRules1>();
                    BenefitRule.BenefitRules1 benefitRules2 = new BenefitRule.BenefitRules1();
                    benefitRules2.setBenefitRuleName(map.get("benefitRuleName"));
                    benefitRules2.setFormularyType(map.get("formularyType"));
                    benefitRules2.setAboveValue(Integer.valueOf(map.get("aboveValue")));
                    benefitRules2.setBelowValueString(map.get("belowValue"));
                    benefitRules1.add(benefitRules2);
                    benefitRuleforAContract=new BenefitRule(contractId,benefitRules1);
                }
                else {
                    ArrayList<BenefitRule.BenefitRules1> benefitRules1 = new ArrayList<BenefitRule.BenefitRules1>();
                    BenefitRule.BenefitRules1 benefitRules2 = new BenefitRule.BenefitRules1();
                    benefitRules2.setBenefitRuleName(map.get("benefitRuleName"));
                    benefitRules2.setFormularyType(map.get("formularyType"));
                    benefitRules2.setAboveValue(Integer.valueOf(map.get("aboveValue")));
                    benefitRules2.setBelowValue(Integer.valueOf(map.get("belowValue")));
                    benefitRules1.add(benefitRules2);
                    benefitRuleforAContract=new BenefitRule(contractId,benefitRules1);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
//    public void createMultipleBenefitRuleJSONBody(DataTable dataTable) {
//        try {
//            List<Map<String, String>> benefitRuleData = dataTable.asMaps(String.class, String.class);
//            for (Map<String, String> map : benefitRuleData) {
//                if(map.get("aboveValue").isEmpty()&&map.get("belowValue").isEmpty()&&map.get("aboveValue2").isEmpty()&&map.get("belowValue2").isEmpty()){
//                    ArrayList<BenefitRule.BenefitRules1> benefitRules1 = new ArrayList<BenefitRule.BenefitRules1>();
//                    BenefitRule.BenefitRules1 benefitRules2 = new BenefitRule.BenefitRules1();
//                    benefitRules2.setBenefitRuleName(map.get("benefitRuleName"));
//                    benefitRules2.setFormularyType(map.get("formularyType"));
//                    benefitRules2.setAboveValueString(map.get("aboveValue"));
//                    benefitRules2.setBelowValueString(map.get("belowValue"));
//                    benefitRules1.add(benefitRules2);
//                    BenefitRule.BenefitRules1 benefitRules3 = new BenefitRule.BenefitRules1();
//                    benefitRules3.setBenefitRuleName(map.get("benefitRuleName2"));
//                    benefitRules3.setFormularyType(map.get("formularyType2"));
//                    benefitRules3.setAboveValueString(map.get("aboveValue2"));
//                    benefitRules3.setBelowValueString(map.get("belowValue2"));
//                    benefitRules1 .add(benefitRules3);
//                    benefitRuleforAContract=new BenefitRule(contractId,benefitRules1);}
//                else if(map.get("aboveValue").isEmpty()&&map.get("belowValue").isEmpty()&&map.get("aboveValue2").isEmpty()){
//                    ArrayList<BenefitRule.BenefitRules1> benefitRules1 = new ArrayList<BenefitRule.BenefitRules1>();
//                    BenefitRule.BenefitRules1 benefitRules2 = new BenefitRule.BenefitRules1();
//                    benefitRules2.setBenefitRuleName(map.get("benefitRuleName"));
//                    benefitRules2.setFormularyType(map.get("formularyType"));
//                    benefitRules2.setAboveValueString(map.get("aboveValue"));
//                    benefitRules2.setBelowValueString(map.get("belowValue"));
//                    benefitRules1.add(benefitRules2);
//                    BenefitRule.BenefitRules1 benefitRules3 = new BenefitRule.BenefitRules1();
//                    benefitRules3.setBenefitRuleName(map.get("benefitRuleName2"));
//                    benefitRules3.setFormularyType(map.get("formularyType2"));
//                    benefitRules3.setAboveValueString(map.get("aboveValue2"));
//                    benefitRules3.setBelowValueString(map.get("belowValue2"));
//                    benefitRules1 .add(benefitRules3);
//                    benefitRuleforAContract=new BenefitRule(contractId,benefitRules1);}
//                else if(map.get("aboveValue").isEmpty()&&map.get("belowValue").isEmpty()&&map.get("belowValue2").isEmpty()){
//                    ArrayList<BenefitRule.BenefitRules1> benefitRules1 = new ArrayList<BenefitRule.BenefitRules1>();
//                    BenefitRule.BenefitRules1 benefitRules2 = new BenefitRule.BenefitRules1();
//                    benefitRules2.setBenefitRuleName(map.get("benefitRuleName"));
//                    benefitRules2.setFormularyType(map.get("formularyType"));
//                    benefitRules2.setAboveValueString(map.get("aboveValue"));
//                    benefitRules2.setBelowValueString(map.get("belowValue"));
//                    benefitRules1.add(benefitRules2);
//                    BenefitRule.BenefitRules1 benefitRules3 = new BenefitRule.BenefitRules1();
//                    benefitRules3.setBenefitRuleName(map.get("benefitRuleName2"));
//                    benefitRules3.setFormularyType(map.get("formularyType2"));
//                    benefitRules3.setAboveValueString(map.get("aboveValue2"));
//                    benefitRules3.setBelowValueString(map.get("belowValue2"));
//                    benefitRules1 .add(benefitRules3);
//                    benefitRuleforAContract=new BenefitRule(contractId,benefitRules1);}
//                else if(map.get("aboveValue").isEmpty()&&map.get("belowValue").isEmpty()&&map.get("belowValue2").isEmpty()){
//                    ArrayList<BenefitRule.BenefitRules1> benefitRules1 = new ArrayList<BenefitRule.BenefitRules1>();
//                    BenefitRule.BenefitRules1 benefitRules2 = new BenefitRule.BenefitRules1();
//                    benefitRules2.setBenefitRuleName(map.get("benefitRuleName"));
//                    benefitRules2.setFormularyType(map.get("formularyType"));
//                    benefitRules2.setAboveValueString(map.get("aboveValue"));
//                    benefitRules2.setBelowValueString(map.get("belowValue"));
//                    benefitRules1.add(benefitRules2);
//                    BenefitRule.BenefitRules1 benefitRules3 = new BenefitRule.BenefitRules1();
//                    benefitRules3.setBenefitRuleName(map.get("benefitRuleName2"));
//                    benefitRules3.setFormularyType(map.get("formularyType2"));
//                    benefitRules3.setAboveValueString(map.get("aboveValue2"));
//                    benefitRules3.setBelowValueString(map.get("belowValue2"));
//                    benefitRules1 .add(benefitRules3);
//                    benefitRuleforAContract=new BenefitRule(contractId,benefitRules1);}
//                else if(map.get("aboveValue").isEmpty()){
//                    ArrayList<BenefitRule.BenefitRules1> benefitRules1 = new ArrayList<BenefitRule.BenefitRules1>();
//                    BenefitRule.BenefitRules1 benefitRules2 = new BenefitRule.BenefitRules1();
//                    benefitRules2.setBenefitRuleName(map.get("benefitRuleName"));
//                    benefitRules2.setFormularyType(map.get("formularyType"));
//                    benefitRules2.setAboveValueString(map.get("aboveValue"));
//                    benefitRules2.setBelowValue(Integer.valueOf(map.get("belowValue")));
//                    benefitRules1.add(benefitRules2);
//                    BenefitRule.BenefitRules1 benefitRules3 = new BenefitRule.BenefitRules1();
//                    benefitRules2.setBenefitRuleName(map.get("benefitRuleName2"));
//                    benefitRules2.setFormularyType(map.get("formularyType2"));
//                    benefitRules2.setAboveValueString(map.get("aboveValue2"));
//                    benefitRules2.setBelowValueString(map.get("belowValue2"));
//                    benefitRules1.add(benefitRules3);
//                    benefitRuleforAContract=new BenefitRule(contractId,benefitRules1);
//                }
//                else if(map.get("belowValue").isEmpty()){
//                    ArrayList<BenefitRule.BenefitRules1> benefitRules1 = new ArrayList<BenefitRule.BenefitRules1>();
//                    BenefitRule.BenefitRules1 benefitRules2 = new BenefitRule.BenefitRules1();
//                    benefitRules2.setBenefitRuleName(map.get("benefitRuleName"));
//                    benefitRules2.setFormularyType(map.get("formularyType"));
//                    benefitRules2.setAboveValue(Integer.valueOf(map.get("aboveValue")));
//                    benefitRules2.setBelowValueString(map.get("belowValue"));
//                    benefitRules1.add(benefitRules2);
//                    BenefitRule.BenefitRules1 benefitRules3 = new BenefitRule.BenefitRules1();
//                    benefitRules2.setBenefitRuleName(map.get("benefitRuleName2"));
//                    benefitRules2.setFormularyType(map.get("formularyType2"));
//                    benefitRules2.setAboveValueString(map.get("aboveValue2"));
//                    benefitRules2.setBelowValueString(map.get("belowValue2"));
//                    benefitRules1.add(benefitRules3);
//                    benefitRuleforAContract=new BenefitRule(contractId,benefitRules1);
//                }
//                else {
//                    ArrayList<BenefitRule.BenefitRules1> benefitRules1 = new ArrayList<BenefitRule.BenefitRules1>();
//                    BenefitRule.BenefitRules1 benefitRules2 = new BenefitRule.BenefitRules1();
//                    benefitRules2.setBenefitRuleName(map.get("benefitRuleName"));
//                    benefitRules2.setFormularyType(map.get("formularyType"));
//                    benefitRules2.setAboveValue(Integer.valueOf(map.get("aboveValue")));
//                    benefitRules2.setBelowValue(Integer.valueOf(map.get("belowValue")));
//                    benefitRules1.add(benefitRules2);
//                    BenefitRule.BenefitRules1 benefitRules3 = new BenefitRule.BenefitRules1();
//                    benefitRules2.setBenefitRuleName(map.get("benefitRuleName2"));
//                    benefitRules2.setFormularyType(map.get("formularyType2"));
//                    benefitRules2.setAboveValueString(map.get("aboveValue2"));
//                    benefitRules2.setBelowValueString(map.get("belowValue2"));
//                    benefitRules1.add(benefitRules3);
//                    benefitRuleforAContract=new BenefitRule(contractId,benefitRules1);
//                }
//            }
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

    public void hitCreateBenefitRuleAPI(String endpoint){
        response=postOperation(endpoint,benefitRuleforAContract);
        String s=response.getHeader("rb-api-result");
        System.out.println(s);
    }
    public void validationCreateBenefitResultStatus(String jsonPath,String expectedValue)
    {
        String jsonPathForBR=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,jsonPath);
        String expected=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,expectedValue);
        verificationHelperClass.verifyAPIResponseJsonWithDBJson(response,expected,jsonPathForBR);
    }

}