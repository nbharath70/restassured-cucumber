package baseSteps;

import HelperClass.DataBaseHelper;
import HelperClass.ResourcePath;
import HelperClass.VerificationHelperClass;
import TestBase.TestBase;
import io.restassured.response.Response;
import org.apache.log4j.Logger;

import java.sql.ResultSet;

public class GetSelectOpt extends TestBase {
    public static Logger log = getMyLogger(GetSelectOpt.class);
    public static Response getSelectOptionsResponse;
    public String jsonPath;
    public String columnName;
    public VerificationHelperClass verificationHelperClass = new VerificationHelperClass();
    DataBaseHelper dataBaseHelper=new DataBaseHelper();
    ResultSet resultSet;
    /**
     * @param endpoint
     * @uthour Arun Kumar
     * This method hits getSelectOptionsResource the end point and logs the response and also verify json formate type
     */
    public void getSelectOptionsResource(String endpoint) {
        getSelectOptionsResponse = getCall(endpoint);
    }

    /**
     * getSelectOptionsResponseStatusCode method used to verify the status code
     *
     * @param statusCode
     */
    public void getSelectOptionsResponseStatusCode(int statusCode) {
        verificationHelperClass.verifyStatusCode(getSelectOptionsResponse, statusCode);
        log.info("getSelectOptionsResponse StatusCode is " + statusCode + " and its Pass");
    }

    /**
     * @param jsonPath
     * @param query
     * @param columnName
     * @uthour Arun Kumar
     * validatingGetSelectOptionsResponseBody method is used to validate the response body output with database value
     */
    public void validatingGetSelectOptionsResponseBody(String jsonPath, String query, String columnName) {
        log.info("Validating GetSelectOptions Response Body JsonPath=" + jsonPath + " and query=" + query);
        verificationHelperClass.verifyResponseJsonAndDbArrayByColumnName(getSelectOptionsResponse, jsonPath, query, columnName);
    }


    /**
     * Verify getSelectOptionsResponseBody
     * @uthor Arun Kumar
     */
    public void getSelectOptionsResponseBody() {
        verifyResponseFormatIsJSON();
    }

    /**
     *
     * @param query-Query that fetches the Columns of DB in the form of
     * @param columnName-
     */
    public void validateJSONResponse(String query,String columnName){
        try{ String actualquery=getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES,query);
            String actualColumnName=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,columnName);
            String apiJsonPath=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"jsonPathForResponsevalidation");
            String dBJsonPath=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"jsonPathForResponsevalidation");
            resultSet=dataBaseHelper.getDataWithoutPropertiesKey(actualquery);
            resultSet.next();
            String dbJson=resultSet.getString(actualColumnName);
            verificationHelperClass.verifyAPIResponseJsonWithDBJsonAsWholeJson(getSelectOptionsResponse,dbJson,apiJsonPath,dBJsonPath);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * validatingCodeValueAndCodeDescription Method is used to verify CodeValue And CodeDescription for given CodeType is returned from API and DB
     * @param query
     * @uthor Arun Kumar
     */
    public void validatingCodeValueAndCodeDescription(String query) {
        if (query.equalsIgnoreCase("getCodeTypeOfDisputeDays")) {
            jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "DisputeDaysCodeValue");
            columnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "codeValue");

        } else if (query.equalsIgnoreCase("getCodeDictionaryOfDisputeDays")) {
            jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "DisputeDaysCodeDescription");
            columnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "codeDescription");

        } else if (query.equalsIgnoreCase("getCodeTypeOfBillingCycle")) {
            jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "BillingCycleCodeValue");
            columnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "codeValue");

        } else if (query.equalsIgnoreCase("getCodeDictionaryOfBillingCycle")) {
            jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "BillingCycleCodeDescription");
            columnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "codeDescription");

        } else if (query.equalsIgnoreCase("getCodeTypeOfReSubmissionWindow")) {
            jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "ReSubmissionWindowCodeValue");
            columnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "codeValue");

        } else if (query.equalsIgnoreCase("getCodeDictionaryOfReSubmissionWindow")) {
            jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "ReSubmissionWindowCodeDescription");
            columnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "codeDescription");

        } else if (query.equalsIgnoreCase("getCodeTypeOfUmRule")) {
            jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "UmRuleCodeValue");
            columnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "codeValue");

        } else if (query.equalsIgnoreCase("getCodeDictionaryOfUmRule")) {
            jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "UmRuleCodeDescription");
            columnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "codeDescription");

        } else if (query.equalsIgnoreCase("getCodeTypeOfPricingMethod")) {
            jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "PricingMethodCodeValue");
            columnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "codeValue");

        } else if (query.equalsIgnoreCase("getCodeDictionaryOfPricingMethod")) {
            jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "PricingMethodCodeDescription");
            columnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "codeDescription");

        } else if (query.equalsIgnoreCase("getCodeTypeOfPaymentTerms")) {
            jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "PaymentTermsCodeValue");
            columnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "codeValue");

        } else if (query.equalsIgnoreCase("getCodeDictionaryOfPaymentTerms")) {
            jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "PaymentTermsCodeDescription");
            columnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "codeDescription");

        } else if (query.equalsIgnoreCase("getCodeTypeOfRebateableDrugTier")) {
            jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "RebateableDrugTierCodeValue");
            columnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "codeValue");

        } else if (query.equalsIgnoreCase("getCodeDictionaryOfRebateableDrugTier")) {
            jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "RebateableDrugTierCodeDescription");
            columnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "codeDescription");

        } else if (query.equalsIgnoreCase("getCodeTypeOfCompFrmlPosition")) {
            jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "CompFrmlPositionCodeValue");
            columnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "codeValue");

        } else if (query.equalsIgnoreCase("getCodeDictionaryOfCompFrmlPosition")) {
            jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "CompFrmlPositionCodeDescription");
            columnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "codeDescription");

        } else if (query.equalsIgnoreCase("getCodeTypeOfFrmlPosition")) {
            jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "FrmlPositionCodeValue");
            columnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "codeValue");

        } else if (query.equalsIgnoreCase("getCodeDictionaryOfFrmlPosition")) {
            jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "FrmlPositionCodeDescription");
            columnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "codeDescription");

        } else if (query.equalsIgnoreCase("getCodeTypeOfSubmissionWindow")) {
            jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "SubmissionWindowCodeValue");
            columnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "codeValue");

        } else if (query.equalsIgnoreCase("getCodeDictionaryOfSubmissionWindow")) {
            jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "SubmissionWindowCodeDescription");
            columnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "codeDescription");

        } else if (query.equalsIgnoreCase("getCodeTypeOfProdOrMfr")) {
            jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "ProdOrMfrCodeValue");
            columnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "codeValue");

        } else if (query.equalsIgnoreCase("getCodeDictionaryOfProdOrMfr")) {
            jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "ProdOrMfrCodeDescription");
            columnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "codeDescription");

        } else if (query.equalsIgnoreCase("getCodeTypeOfDrugGroupType")) {
            jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "DrugGroupTypeCodeValue");
            columnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "codeValue");

        } else if (query.equalsIgnoreCase("getCodeDictionaryOfDrugGroupType")) {
            jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "DrugGroupTypeCodeDescription");
            columnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "codeDescription");

        } else if (query.equalsIgnoreCase("getCodeTypeOfRebateOptionBillingCycle")) {
            jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "RebateOptionBillingCycleCodeValue");
            columnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "codeValue");

        } else if (query.equalsIgnoreCase("getCodeDictionaryOfRebateOptionBillingCycle")) {
            jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "RebateOptionBillingCycleCodeDescription");
            columnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "codeDescription");

        } else if (query.equalsIgnoreCase("getCodeTypeOfLateFee")) {
            jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "LateFeeCodeValue");
            columnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "codeValue");

        } else if (query.equalsIgnoreCase("getCodeDictionaryOfLateFee")) {
            jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "LateFeeCodeDescription");
            columnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "codeDescription");

        } else if (query.equalsIgnoreCase("getCodeTypeOfAuditFrequency")) {
            jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "AuditFrequencyCodeValue");
            columnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "codeValue");

        } else if (query.equalsIgnoreCase("getCodeDictionaryOfAuditFrequency")) {
            jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "AuditFrequencyCodeDescription");
            columnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "codeDescription");

        } else if (query.equalsIgnoreCase("getCodeTypeOfAuditLookback")) {
            jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "AuditLookbackCodeValue");
            columnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "codeValue");

        } else if (query.equalsIgnoreCase("getCodeDictionaryOfAuditLookback")) {
            jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "AuditLookbackCodeDescription");
            columnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "codeDescription");

        } else if (query.equalsIgnoreCase("getCodeTypeOfPricingBasis")) {
            jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "PricingBasisCodeValue");
            columnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "codeValue");

        } else if (query.equalsIgnoreCase("getCodeDictionaryOfPricingBasis")) {
            jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "PricingBasisCodeDescription");
            columnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "codeDescription");

        } else if (query.equalsIgnoreCase("getCodeTypeOfPricingRefDate")) {
            jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "PricingRefDateCodeValue");
            columnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "codeValue");

        } else if (query.equalsIgnoreCase("getCodeDictionaryOfPricingRefDate")) {
            jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "PricingRefDateCodeDescription");
            columnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "codeDescription");

        } else if (query.equalsIgnoreCase("getCodeTypeOfCompRestrictions")) {
            jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "CompRestrictionsCodeValue");
            columnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "codeValue");

        } else if (query.equalsIgnoreCase("getCodeDictionaryOfCompRestrictions")) {
            jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "CompRestrictionsCodeDescription");
            columnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "codeDescription");

        } else if (query.equalsIgnoreCase("getCodeTypeOfCompDrugTier")) {
            jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "CompDrugTierCodeValue");
            columnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "codeValue");

        } else if (query.equalsIgnoreCase("getCodeDictionaryOfCompDrugTier")) {
            jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "CompDrugTierCodeDescription");
            columnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "codeDescription");
        }
        else if (query.equalsIgnoreCase("getCodeTypeOfDrugSource")) {
            jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "DrugSourceCodeValue");
            columnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "codeValue");

        } else if (query.equalsIgnoreCase("getCodeDictionaryOfDrugSource")) {
            jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "DrugSourceCodeDescription");
            columnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "codeDescription");
        }
        else if (query.equalsIgnoreCase("getCodeTypeOfDrugSearchCriteria")) {
            jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "DrugSearchCriteriaCodeValue");
            columnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "codeValue");

        } else if (query.equalsIgnoreCase("getCodeDictionaryOfDrugSearchCriteria")) {
            jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "DrugSearchCriteriaCodeDescription");
            columnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "codeDescription");
        } else if (query.equalsIgnoreCase("getCodeTypeOfLobNonPartD")) {
            jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "LobNonPartDCodeValue");
            columnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "codeValue");

        } else if (query.equalsIgnoreCase("getCodeDictionaryOfLobNonPartD")) {
            jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "LobNonPartDCodeDescription");
            columnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "codeDescription");
        } else if (query.equalsIgnoreCase("getCodeTypeOfLocations")) {
            jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "LocationsCodeValue");
            columnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "codeValue");

        } else if (query.equalsIgnoreCase("getCodeDictionaryOfLocations")) {
            jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "LocationsCodeDescription");
            columnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "codeDescription");
        } else if (query.equalsIgnoreCase("getCodeTypeOfLobPartD")) {
            jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "LobPartDCodeValue");
            columnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "codeValue");

        } else if (query.equalsIgnoreCase("getCodeDictionaryOfLobPartD")) {
            jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "LobPartDCodeDescription");
            columnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "codeDescription");
        } else if (query.equalsIgnoreCase("getCodeTypeOfFormularyType")) {
            jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "FormularyTypeCodeValue");
            columnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "codeValue");

        } else if (query.equalsIgnoreCase("getCodeDictionaryOfFormularyType")) {
            jsonPath = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "FormularyTypeCodeDescription");
            columnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "codeDescription");
        }
        log.info("Validating GetSelectOptions Response Body JsonPath=" + jsonPath + " and query=" + query);
        verificationHelperClass.verifyResponseJsonAndDbArrayByColumnName(getSelectOptionsResponse, jsonPath, query, columnName);
    }
}
