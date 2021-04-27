/**
 * GetAllMFR class contains the methods to hit the getALLMFR end point and retrieve the details from
 * JSON response and compare the details with expected result retrieved from database
 *
 * @author  QATest
 * @version 1.0
 * @since   03/01/2021
 */
package baseSteps;
import HelperClass.DataBaseHelper;
import HelperClass.ResourcePath;
import HelperClass.VerificationHelperClass;
import TestBase.TestBase;
import io.restassured.response.Response;
import org.apache.log4j.Logger;

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
            String Rk=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "Rowkey");
            rowKeyVal = result.getInt(Rk);
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
            String CID=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "ContractID");
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
        discardContractResponse=deleteOperation(endpoint,rowKeyVal,contractID);
        log.info("Response is "+discardContractResponse.asString());
    }

    public void verifyStatusCodeofDiscardContractAPI(int StatusCode){
        verificationHelperClass.verifyStatusCode(discardContractResponse,StatusCode);
    }
    public void reactivateContracts(String contractHeader, String contractDetails){
        dbHepler.executeUpdatePreparedQuery(contractHeader,rowKeyVal);
        dbHepler.executeUpdatePreparedQuery(contractDetails,rowKeyVal);
    }


}