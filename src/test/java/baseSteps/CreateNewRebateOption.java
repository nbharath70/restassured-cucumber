package baseSteps;

import HelperClass.DataBaseHelper;
import HelperClass.ResourcePath;
import HelperClass.VerificationHelperClass;
import RequestPojo.CreateNewRebateOptionPojo.*;
import TestBase.TestBase;
import com.jayway.jsonpath.JsonPath;
import cucumber.api.DataTable;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.testng.Assert;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CreateNewRebateOption extends TestBase {
    Response response;
    DataBaseHelper dataBaseHelper = new DataBaseHelper();
    public VerificationHelperClass verificationHelperClass = new VerificationHelperClass();
    public static Logger log = getMyLogger(CreateNewRebateOption.class);
    CreateNewRebateOptions createNewRebateOptions;
    String contractId;
    String benefitRuleID;
    String programID;
    String rebateableMFR_DrugList_ID;

    /**
     * createNewRebateOptionDetailsData Method is used to create data for create new option
     * @param dataTable
     * @uthor Arun Kumar
     */
    public void createNewRebateOptionDetailsData(DataTable dataTable) {
        try {
            List<Map<String, String>> createNewRebateProgramData = dataTable.asMaps(String.class, String.class);
            for (Map<String, String> map : createNewRebateProgramData) {
                Access access=new Access(map.get("pricingMethod"),Integer.valueOf(map.get("price")));
                RebateTerms rebateTerms1=new RebateTerms();
                rebateTerms1.setLineOfBusiness(map.get("lineOfBusiness"));
                ArrayList lineOfBusiness = rebateTerms1.getLineOfBusiness();
                RebateTerms rebateTerms=new RebateTerms(map.get("billingCycle"),lineOfBusiness,Integer.valueOf(benefitRuleID),map.get("benefitRuleName"),Integer.valueOf(map.get("psfOptionLevel")),Integer.valueOf(rebateableMFR_DrugList_ID),access);
                RebateTermJson rebateTermJson1=new RebateTermJson();
                rebateTermJson1.setRebateTerms(rebateTerms);
                ArrayList rebateTerm2 = rebateTermJson1.getRebateTerms();
                RebateTermJson rebateTermJson=new RebateTermJson(rebateTerm2);
                UmRule umRule=new UmRule(map.get("firstRule"),map.get("firstCondition"),map.get("secondRule"),map.get("secondCondition"),map.get("thirdRule"));
                RebateableDrug rebateableDrug=new RebateableDrug(map.get("rebateableDrugfirstTier"),map.get("rebateableDrugcondition"),map.get("rebateableDrugsecondTier"));
                Formulary formulary=new Formulary(map.get("position"),rebateableDrug);
                CompRestrictions compRestrictions=new CompRestrictions(map.get("compRestrictionsfirstRule"),map.get("compRestrictionsfirstCondition"),map.get("compRestrictionssecondRule"),map.get("compRestrictionssecondCondition"),map.get("compRestrictionsthirdRule"),map.get("compRestrictionsthirdCondition"),map.get("compRestrictionsfourthRule"),map.get("compRestrictionsfourthCondition"),map.get("compRestrictionsfifthRule"));
                FormularyRequirementJson formularyRequirementJson=new FormularyRequirementJson(Integer.valueOf(map.get("xvalue")),map.get("yvalue"),map.get("prodOrMfr"),Boolean.valueOf(map.get("paAndSt")),umRule,formulary,map.get("compFormularyPosition"),map.get("compDrugTier"),compRestrictions);
                createNewRebateOptions=new CreateNewRebateOptions(map.get("mfrRebateOptionName"),Integer.valueOf(programID),map.get("startDate"),map.get("endDate"),formularyRequirementJson,rebateTermJson);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to createNewRebateOptionPostCall
     * @uthor Arun Kumar
     * @param endPoint
     */
    public void createNewRebateOptionPostCall(String endPoint)
    {
        response = postOperation(endPoint,createNewRebateOptions);
//        String s=response.getHeader("rb-api-result");
//        int actualCode = JsonPath.read(s, "$.apiReturnCodes[0].code");
//        System.out.println(actualCode);
    }

    /**
     * This method is used validate verifyUpdateManufactureContractResponseHeaderErrorCode
     * @uthor ArunKumar
     * @param expectedErrorCode
     */
    public void verifyCreateNewRebateOptionSaveProgressResponseHeaderErrorCode(String expectedErrorCode)
    {
        try {
            String s=response.getHeader("rb-api-result");
            int actualCode = JsonPath.read(s, "$.apiReturnCodes[0].code");
            String expectedErrorCodeString=expectedErrorCode;
            int expectedError=Integer.valueOf(expectedErrorCodeString);
            org.junit.Assert.assertEquals("Error code value do not match!", expectedError,actualCode);
            log.info("Verification error code value pass where expectedValue=" + expectedError + " equals to actualValue=" + actualCode);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to send invalid program ID for validation
     * @param invalidprogID
     * @Author Arun kumar
     */
    public void invalidProgramID(int invalidprogID)
    {
        programID=String.valueOf(invalidprogID);
    }

    /**
     * This method is used to validate the status code of createNewRebateOptionPostCallStatusCode
     * @uthor Arun Kumar
     * @param statusCode
     */
    public void createNewRebateOptionPostCallStatusCode(int statusCode)
    {
        verificationHelperClass.verifyStatusCode(response, statusCode);
        log.info("createNewRebateOptionPostCall StatusCode is " + statusCode + " and its Pass");
    }

    /**
     * This validationResults used to validate the respanse body value as boolean
     * @uthor Arun Kumar
     * @param actualValue
     * @param expectedValue
     */
    public void validationResults(String actualValue,String expectedValue)
    {
        verificationHelperClass.verifyResponseJsonBoolean(response,actualValue,expectedValue);
    }

    /**
     * This validationResultsString used to validate the respanse body value as String
     * @uthor Arun Kumar
     * @param actualValue
     * @param expectedValue
     */
    public void validationResultsString(String actualValue,String expectedValue)
    {
        verificationHelperClass.verifyResponseJsonString(response,actualValue,expectedValue);
    }

    /**
     * This method is used to get the contractID from the contract name
     * @param ContractName
     * @Author: Arun Kumar
     */
    public void getContractIDByContractName(String query,String ContractName) {
        try {
            ResultSet getContractId = dataBaseHelper.executePreparedQuery(query, ContractName);
            getContractId.next();
            contractId = getContractId.getString("Contract_ID");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to get the getProgramID from the database
     * @param query
     * @Author: Arun Kumar
     */
    public void getProgramID(String query) {
        try {
            ResultSet getProgramID = dataBaseHelper.executePreparedQuery(query, contractId);
            getProgramID.next();
            programID = getProgramID.getString("MFR_Program_ID");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to get the getBenefitRuleID from the database
     * @param query
     * @param BenefitRuleName
     * @Author: Arun Kumar
     */
    public void getBenefitRuleID(String query,String BenefitRuleName)
    {
        try {
            ResultSet getBenefitRuleID = dataBaseHelper.executePreparedQuery(query, BenefitRuleName);
            getBenefitRuleID.next();
            benefitRuleID = getBenefitRuleID.getString("Benefit_Rule_ID");
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to get the getRebateableDrugListID from the database
     * @param query
     * @Author: Arun Kumar
     */
    public void getRebateableDrugListID(String query)
    {
        try {
            ResultSet getRebateableMFR_DrugList_ID = dataBaseHelper.executePreparedQuery("getMfrDrugListIdByListType", "Rebateable");
            getRebateableMFR_DrugList_ID.next();
            rebateableMFR_DrugList_ID=getRebateableMFR_DrugList_ID.getString("MFR_DrugList_ID");
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to delete Rebate options
     * @param query
     * @param rebateOptionName
     * @Author: Arun Kumar
     */
    public void deleteRebateOption(String query,String rebateOptionName)
    {
        try {
            dataBaseHelper.executeUpdatePreparedQueryAsString(query,rebateOptionName);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to validate the rebate option ID from DB and response JSon
     * @param query
     * @param rebateOptionName
     * @param actualRebateOptionIDJson
     * @Author Arun kumar
     */
    public void verifyRebateOptionID(String query,String rebateOptionName,String actualRebateOptionIDJson)
    {
        try {
            ResultSet getRebateOptionID = dataBaseHelper.executePreparedQuery(query, rebateOptionName);
            getRebateOptionID.next();
            String expectedRebateOptionIDDB=getRebateOptionID.getString("MFR_Rebate_Option_ID");
            Object actualValueRebateOptionID = JsonPath.read(response.asString(), getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, actualRebateOptionIDJson));
            log.info("expectedValue from DB" +  expectedRebateOptionIDDB + " And actualValue from Json response=" + actualValueRebateOptionID);
            Assert.assertEquals(expectedRebateOptionIDDB,actualValueRebateOptionID,"Validating the RebateOption ID");
            log.info("Verification pass where expectedValue=" + expectedRebateOptionIDDB + " equals to actualValue=" + actualValueRebateOptionID);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }





}