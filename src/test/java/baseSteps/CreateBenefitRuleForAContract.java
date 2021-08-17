package baseSteps;
import HelperClass.DataBaseHelper;
import HelperClass.ResourcePath;
import HelperClass.VerificationHelperClass;
import RequestPojo.*;
import TestBase.TestBase;
import cucumber.api.DataTable;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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


    /**
     * This method is used to fetch the contract ID used to create Benefit rule
     * @uthor Bharath
     * @param query-
     */
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

    /**
     * This method is used to update back a Life cycle of Contract header and contract detail status to Inprogress so that we can create the benefit rule
     * @uthor Bharath
     * @param contractHeaderQuery-updating contract header Life Cycle Status to Inprogress
     *@param  contractDetailQuery-updating contract detail life cycle Status to InProgress
     */
    public void updateLifeCycleStatusToInprogressForContract(String contractHeaderQuery,String contractDetailQuery)
    {
        dataBaseHelper.executeUpdatePreparedQueryAsString(contractHeaderQuery,contractId);
        dataBaseHelper.executeUpdatePreparedQueryAsString(contractDetailQuery,contractId);
    }

    /**
     * This method is used to update back a Life cycle of Contract header and contract detail status to New After Testing is done so that Data remains Same
     * @uthor Bharath
     * @param contractHeaderQuery-updating contract header Life Cycle Status to new
     *@param  contractDetailQuery-updating contract detail life cycle Status to new
     */
    public void updateLifeCycleStatusToNewForContract(String contractHeaderQuery,String contractDetailQuery)
    {
        dataBaseHelper.executeUpdatePreparedQueryAsString(contractHeaderQuery,contractId);
        dataBaseHelper.executeUpdatePreparedQueryAsString(contractDetailQuery,contractId);
    }


    /**
     * This method is used to verify the Status code of the response
     * @uthor Bharath
     * @param StatusCode-sent from feture file
     */
    public void verifyStatusCodeofCreateBenefitRuleAPI(int StatusCode){
        verificationHelperClass.verifyStatusCode(response,StatusCode);
    }

    /**
     * This method is used to delete the benefit rule created for testing
     * @uthor Bharath
     * @param deleteBenefitRuleQuery-this Query is used to delete the benefit rule created for testing
     */
    public void deleteCreatedBenefitRule(String deleteBenefitRuleQuery){
        dataBaseHelper.executeUpdatePreparedQueryAsString(deleteBenefitRuleQuery,contractId);
    }

    /**
     * This method is used to create the benefitrule Request body for API
     * @uthor Bharath
     * @param dataTable-the Details are passed from Feature file through data table
     */
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

    /**
     * This method is hit he create benefit Rule API
     * @uthor Bharath
     * @param endpoint-this is the create benefitrule endpoint
     */
    public void hitCreateBenefitRuleAPI(String endpoint){
        response=postOperation(endpoint,benefitRuleforAContract);
//        String s=response.getHeader("rb-api-result");
//        System.out.println(s);
    }

    /**
     * This method is used to check the result Status of in Response
     * @uthor Bharath
     * @param jsonPath-this is jsonpath for result validation
     * @param expectedValue-this is expected value for result validation
     */
    public void validationCreateBenefitResultStatus(String jsonPath,String expectedValue)
    {
        String jsonPathForBR=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,jsonPath);
        String expected=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,expectedValue);
        verificationHelperClass.verifyAPIResponseJsonWithDBJson(response,expected,jsonPathForBR);
    }
    public void validateHeaderCodeOfResponse(String headerCode){
        verificationHelperClass.verifyResponseHeaderApiReturnCodesValue(response,headerCode);
    }

    /**
     * This method is used to get the contractID from the contract name
     * @param ContractName-
     * @Author: Arun Kumar
     */
    public void getContractIDByContractName(String query,String ContractName)
    {
        try {
            ResultSet getContractId = dataBaseHelper.executePreparedQuery(query, ContractName);
            getContractId.next();
            contractId = getContractId.getString("Contract_ID");
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to updateBenefitRule Status from InProgress to Approve
     * @param query-
     * @param benefitRuleName-
     * @Author: Arun Kumar
     */
    public void updateBenefitRuleStatusApprove(String query,String benefitRuleName)
    {
        try {
            dataBaseHelper.executeUpdatePreparedQueryAsString(query,benefitRuleName);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to delete BenefitRule
     * @param query-
     * @param benefitRuleName-
     * @Author: Arun Kumar
     */
    public void deleteBenefitRule(String query,String benefitRuleName)
    {
        try {
            dataBaseHelper.executeUpdatePreparedQueryAsString(query,benefitRuleName);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}