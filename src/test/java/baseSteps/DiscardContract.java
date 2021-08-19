package baseSteps;
import HelperClass.DataBaseHelper;
import HelperClass.ResourcePath;
import HelperClass.VerificationHelperClass;
import RequestPojo.DisContractPojo.DiscardContractPojo;
import TestBase.TestBase;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.junit.Assert;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DiscardContract extends TestBase {
    public static Logger log=getMyLogger(DiscardContract.class);
    public static Response discardContractResponse;
    public VerificationHelperClass verificationHelperClass= new VerificationHelperClass();
    public DataBaseHelper dbHepler = new DataBaseHelper();
    public ResultSet result;
    private int rowKeyVal;
    private String contractID;
    DiscardContractPojo discardContractPojo;


    /**
     * This method retrieves the Rowkey Details from  DB
     * @author Smruti
     * @param query
     * @exception SQLException
     */
    public  void getRowKey(String query) {
        try {
            log.info("query is "+query);
            result = dbHepler.getData(query);
            result.next();
            String rowKey=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "rowKey");
            rowKeyVal = result.getInt(rowKey);
            log.info("RowKey of ActiveContract is  " + rowKeyVal + " From DB");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method retrieves the ContractID Details from  DB
     * @author Smruti
     * @exception SQLException
     * @param query
     */

    public  void getContractID(String query) {
        try {
            log.info("query is "+query);
            result = dbHepler.getData(query);
            result.next();
            String CID=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "contractId");
            contractID = result.getString(CID);
            log.info("RowKey of ActiveContract is  " + rowKeyVal + " From DB");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * @uthour Smruti
     * This method hits deleteOperation  end point and logs the response
     * @param endpoint,rowKeyVal,contractID
     */
    public void discardContract(String endpoint)
    {
       discardContractPojo=new DiscardContractPojo("undefined",contractID,rowKeyVal);
       discardContractResponse=deleteOperation(endpoint,discardContractPojo);
       log.info("Response is "+discardContractResponse.asString());
    }

    public void verifyStatusCodeofDiscardContractAPI(int StatusCode){
        verificationHelperClass.verifyStatusCode(discardContractResponse,StatusCode);
    }
    public void reactivateContracts(String contractHeader, String contractDetails){
        dbHepler.executeUpdatePreparedQuery(contractHeader,rowKeyVal);
        dbHepler.executeUpdatePreparedQuery(contractDetails,rowKeyVal);
    }

    /**
     * @uthour Smruti
     * This method hits discard Mfr contract  end point and logs the response
     * Verifies the response format is in JSON
     */
    public void verifyResponseFormatJSON()
    {
        verifyResponseFormatIsJSON();
    }
    /**
     * @uthour Smruti
     * This method Verifies the API response value for discarded contract
     */
    public void verifyIfIsManufacturerContractDiscarded()
    {
        String isContractDiscarded=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "jasonPathForIsDiscardContract");
        verificationHelperClass.verifyAPIResponseBooleanValueTrue(discardContractResponse,isContractDiscarded);

    }
    /**
     * @uthour Smruti
     * This method Verifies the API response value for discarded contract
     */
    public void verifyis_current_flagInContractHeaderAndDetailsTable(String verifyContractHeaderQuery, String verifyContractDetailsQuery) throws SQLException {
        String count=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "contractQueryCount");
        int contractHDRCount= dbHepler.executePreparedQuery(verifyContractHeaderQuery,contractID,count);
        int contractDetailsCount=dbHepler.executePreparedQuery(verifyContractDetailsQuery,contractID,count);
        if(contractHDRCount==1 && contractDetailsCount==1){
            log.info("The is_current_flagInContractHeaderAndDetailsTable is updated to 0");
        }


    }

    public void verifyDiscardContractFromDB(String query)
    {
        try {
            ResultSet isCurrentFlag = dbHepler.executePreparedQuery(query, contractID);
            isCurrentFlag.next();
            int currentFlagValue = isCurrentFlag.getInt("Is_Current_Flag");
            int expectedValue=0;
            Assert.assertEquals("Verify discard contract Is_Current_Flag is zero",expectedValue,currentFlagValue);
            log.info("Verify discard contract Is_Current_Flag is zero PASS where expectedValue="+expectedValue+ " and actualValue="+currentFlagValue);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * This validateCreateDrugGroupResponseByString used to validate the response body value as String
     * @uthor Arun Kumar
     * @param actualValue
     */
    public void validateCreateDrugGroupResponseByString(String actualValue)
    {
        String expectedResponse = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "discardErrorResponse");
        verificationHelperClass.verifyResponseJsonString(discardContractResponse,actualValue,expectedResponse);
    }

    /**
     * This method is used validate verifyDiscardContractResponseHeaderErrorCode
     * @uthor ArunKumar
     * @param expectedHeaderValue
     */
    public void verifyDiscardContractResponseHeaderErrorCode(String expectedHeaderValue)
    {
        verificationHelperClass.verifyResponseHeaderApiReturnCodesValue(discardContractResponse,expectedHeaderValue);
    }


}



