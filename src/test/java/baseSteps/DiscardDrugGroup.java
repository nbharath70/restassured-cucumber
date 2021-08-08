package baseSteps;
import HelperClass.DataBaseHelper;
import HelperClass.ResourcePath;
import HelperClass.VerificationHelperClass;
import TestBase.TestBase;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.junit.Assert;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DiscardDrugGroup extends TestBase {
    public static Logger log=getMyLogger(DiscardDrugGroup.class);
    public static Response discardDrugGroupResponse;
    public VerificationHelperClass verificationHelperClass= new VerificationHelperClass();
    public DataBaseHelper dbHepler = new DataBaseHelper();
    public ResultSet result;
    private int  drugGroupRowKeyVal;



    /**
     * This method retrieves the rowKey of a drug group as per the supplied query  from  DB
     * @author Smruti
     * @param query
     * @exception SQLException
     */

    public  void getDrugGroupRowKey(String query) {
        try {
            log.info("query is "+query);
            result = dbHepler.getData(query);
            result.next();
            String drugGroupRowKey=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "discardDrugGroupRowKey");
            drugGroupRowKeyVal = result.getInt(drugGroupRowKey);
            log.info("drug group row key is:  " + drugGroupRowKeyVal + " From DB");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * @uthour Smruti
     * This method hits delete API  end point and logs the response
     * @param endpoint
     */
    public void discardDrugGroup(String endpoint)
    {
        discardDrugGroupResponse=deleteOperation(endpoint,drugGroupRowKeyVal);
        log.info("Response is "+discardDrugGroupResponse.asString());
    }
    /**
     * @uthour Smruti
     * This method verifies the discard drug group  API's return response code
     * @param StatusCode
     */
    public void verifyStatusCodeOfDiscardDrugGroupAPI(int StatusCode){
        verificationHelperClass.verifyStatusCode(discardDrugGroupResponse,StatusCode);
    }
    /**
     * @uthour Smruti
     * This method reactivates the discarded drug List
     */
    public void reactivateDrugListToNew(String restoreTheDrugGroupStatusToNew){
        dbHepler.executeUpdatePreparedQuery(restoreTheDrugGroupStatusToNew,drugGroupRowKeyVal);
        log.info("======Reactivated the drug group========");
    }

    /**
     * @uthour Smruti
     * This method hits discard drug group  end point and Verifies the response format is in JSON
     */
    public void verifyTheDiscardDrugGroupResponseFormat()
    {
        verifyResponseFormatIsJSON();
    }
    /**
     * @uthour Smruti
     * This method hits discard drug group  end point and  Verifies the response message containing boolean value true
     */
    public void verifyResponseMessageValue(){
            String isDrugGroupDiscarded=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "JsonPathForIsDrugGroupDiscarded");
            verificationHelperClass.verifyAPIResponseBooleanValueTrue(discardDrugGroupResponse,isDrugGroupDiscarded);
    }
    /**
     * @uthour Smruti
     * This method hits discard drug group  end point and  Verifies the response error message
     *  @param drugGroupResponseErrorMessage
     */
    public void verifyResponseErrorMessageValue(String drugGroupResponseErrorMessage){
        String discardDrugGroupErrorMessageJsonPath=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "JsonPathForErrorMessage");
        String drugGroupErrorMessage=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "ResponseErrorMessage");
        String ExpectedDrugGroupResponseErrorMessage=drugGroupErrorMessage+" "+drugGroupRowKeyVal;
        verificationHelperClass.verifyAPIResponseErrorMessage(discardDrugGroupResponse,ExpectedDrugGroupResponseErrorMessage,discardDrugGroupErrorMessageJsonPath);
    }
    /**
     * @uthour Smruti
     * This method hits discard drug group  end point for an approved drug group  and  Verifies the response error message
     *  @param discardedErrorMessage
     */

    public void verifyTheDiscardedDrugGroupInJsonResponse(String discardedErrorMessage){
        String discardedDrugGroupErrorMessageJsonPath=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "JsonPathForErrorMessage");
        String expectedDiscardedErrorMessage=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "DiscardedErrorMessage");
        verificationHelperClass.verifyAPIResponseErrorMessage(discardDrugGroupResponse,expectedDiscardedErrorMessage,discardedDrugGroupErrorMessageJsonPath);
    }
    /**
     * @uthour Smruti
     * This method hits discard drug group  end point for an InReview drug group  and  Verifies the response error message
     *  @param InReviewDrugGroupErrorMessage
     */

    public void verifyinReviewResponseInJSON(String InReviewDrugGroupErrorMessage){
        String discardInReviewErrorMessageJsonPath=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "JsonPathForErrorMessage");
        String expectedInReviewrrorMessage=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "InReviewDrugGroupErrorMessage");
        verificationHelperClass.verifyAPIResponseErrorMessage(discardDrugGroupResponse,expectedInReviewrrorMessage,discardInReviewErrorMessageJsonPath);
    }
    /**
     * @uthour Smruti
     * This method hits  discard drug group API end point and verifies the is_current_flag value 0
     */
    public void verifyDiscardedDrugGroupIsCurrentFlag(String query){
            try {
                ResultSet rs = dbHepler.executePreparedQuery(query, drugGroupRowKeyVal);
                rs.next();
                int currentFlagValue = rs.getInt("Is_Current_Flag");
                int expectedValue=0;
                Assert.assertEquals("Verify discarded drug group's  Is_Current_Flag is zero",expectedValue,currentFlagValue);
                log.info("Verified discarded drug group's Is_Current_Flag is zero");
                log.info(" expectedValue:"+expectedValue+ " and actualValue:"+currentFlagValue) ;
            }catch (Exception e){
                e.printStackTrace();
            }
        }

}



