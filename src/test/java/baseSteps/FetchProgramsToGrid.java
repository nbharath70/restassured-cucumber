package baseSteps;

import TestBase.TestBase;
import HelperClass.ResourcePath;
import HelperClass.DataBaseHelper;
import HelperClass.VerificationHelperClass;
import TestBase.TestBase;
import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;
import org.apache.log4j.Logger;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FetchProgramsToGrid extends TestBase {
    public ResultSet result;
    private String programContractID;
    private String jsonPath;
    private String aPIjsonPathForProgram;
    private String dBjsonPathForProgram;
    private String programDetailJSON;
    private String drugDetailJSON;
    private String drugGroupJSON;
    private ArrayList drugGroupIDList;
    private String columnNameofProgramtoProgramGrid;
    private Array array;
    public static Logger log = getMyLogger(FetchProgramsToGrid.class);
    public static Response fetchProgramsToGridResponse;
    public VerificationHelperClass verificationHelperClass = new VerificationHelperClass();
    public DataBaseHelper dbHepler = new DataBaseHelper();
    /**
     * This method used to fetch ProgramContractID from DataBase
     * @author Bharath
     * @param query
     */
    public  void programContractID(String query) {
        try {
            log.info("query is "+query);
            result = dbHepler.getData(query);
            result.next();
            String programCID=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "contractId");
            programContractID = result.getString(programCID);
            log.info("Program ContractID is  " + programContractID + " From DB");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method used to hit the FetchProgramsToGridAPI with Endpoint
     * @author Bharath
     * @param endpoint
     */
    public void hitFetchProgramsToGridEndpoint(String endpoint) {
        String endpointCompletetion=programContractID+"/programs";
        log.info("FetchProgramToGrid API Appending EndPoint " + endpointCompletetion);
        fetchProgramsToGridResponse = getCall(endpoint,endpointCompletetion);
    }
    /**
     * This method used to  FetchProgramsToGridAPI response
     * @author Bharath
     * @param statusCode
     */
    public void verifyFetchProgramsToGridAPIResponse(int statusCode) {
        verificationHelperClass.verifyStatusCode(fetchProgramsToGridResponse, statusCode);
        log.info("FetchProgramToGrid API StatusCode is " + statusCode + " and its Pass");
    }

    /**
     * This method used to  Verify the Status Code of the API response
     * @author Bharath
     */
    public void verifyFetchProgramToGridResponseJSONFormat(){
        verifyResponseFormatIsJSON();
    }

    /**
     * This method used to  Verify the ProgramDetails which are present in Column
     * @author Bharath
     * @param query
     * @param columnName
     */
  public void verifyFetchProgramResponseDetailsWithDbTable(String query,String columnName){
    if(columnName.equalsIgnoreCase("manufactuererProgramID")){
            jsonPath=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"manufacturerProgramIdJSONPAth");
         columnNameofProgramtoProgramGrid=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,columnName);

          } else if(columnName.equalsIgnoreCase("startDate")){
            jsonPath=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"startDateOfProgramJSONPath");
         columnNameofProgramtoProgramGrid=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,columnName);

        } else if(columnName.equalsIgnoreCase("endDate")){
            jsonPath=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"endDateOfProgramJSONPath");
         columnNameofProgramtoProgramGrid=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,columnName);

        } else if(columnName.equalsIgnoreCase("contractID")){
            jsonPath=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,"contractIDOfProgramJSONPath");
         columnNameofProgramtoProgramGrid=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,columnName);
        }

      verificationHelperClass.verifyResponseJsonAndDbArrayByColumnNameWithPreparedQuery(fetchProgramsToGridResponse,jsonPath,query,columnNameofProgramtoProgramGrid,programContractID);
  }
    /**
     * This method used to  get the  ProgramDetails which are present in DetailJSOnColumn
     * @author Bharath
     * @param query
     */
  public void getProgramDetailJSON(String query){
      try {
          result=dbHepler.executePreparedQuery(query,programContractID);
          result.next();
          String programJSON=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "ProgramDetailJSON");
          programDetailJSON = result.getString(programJSON);
          log.info("Program ContractID is  " + programContractID + " From DB");
      } catch (SQLException e) {
          e.printStackTrace();
      }

  }
    /**
     * This method used to  Verify the ProgramDetails which are present in DetailJSOnColumn
     * @author Bharath
     * @param progarmDetailjsonPath
     */
  public void verifyProgarmDetailJSONWithDB(String progarmDetailjsonPath){
        jsonPath=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,progarmDetailjsonPath);
        verificationHelperClass.verifyAPIResponseJsonWithDBJson(fetchProgramsToGridResponse,programDetailJSON,jsonPath);
  }
    /**
     * This method used to  Verify the DrugGroupId with DB
     * @author Bharath
     * @param dbJSONPath
     * @param aPIJSONPath
     */
  public void verifyDrugGroupIDWithDB(String dbJSONPath,String aPIJSONPath){
      aPIjsonPathForProgram=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,aPIJSONPath);
      dBjsonPathForProgram=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,dbJSONPath);
      verificationHelperClass.verifyAPIResponseJsonWithDBJsonWithDifferentDataTypeValues(fetchProgramsToGridResponse,programDetailJSON,aPIjsonPathForProgram,dBjsonPathForProgram);
  }
    /**
     * This method used to  get the RebatableDrugGroupID from DB
     * @author Bharath
     * @param jsonPath
     */
  public void getRebatableDrugGroupIDfromJSON(String jsonPath) {
        String jSONPath=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,jsonPath);
      ArrayList drugGroupID=JsonPath.read(programDetailJSON,jSONPath) ;
      drugGroupIDList =removeDuplicates(drugGroupID);

  }
    /**
     * This method used to  get the DruggroupDetails from DB
     * @author Bharath
     * @param initialSplitquery
     * @param finalSplitQuery
     */
  public void getDruggroupDetails(String initialSplitquery,String finalSplitQuery){
      try {result=dbHepler.executePreparedQueryForArrayAsParameter(initialSplitquery,drugGroupIDList,finalSplitQuery);
          result.next();
          drugGroupJSON=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "drugGroupJSON");
          drugDetailJSON = result.getString(drugGroupJSON);
          log.info("the DB ProgramDetailJSON is "+drugDetailJSON);
      } catch (SQLException e) {
          e.printStackTrace();
      }
  }
    /**
     * This method used to  get the rugListNameAndListType from DB
     * @author Bharath
     * @param dbJsonpath
     * @param apiJsonpath
     */
  public void verifyDrugListNameAndListType(String dbJsonpath,String apiJsonpath){
      String aPIjsonPathForListNameAndTypeOfDrogGroup=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,apiJsonpath);
      String dBjsonPathForListNameAndTypeOfDrogGroup=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,dbJsonpath);
        verificationHelperClass.verifyAPIResponseJsonWithDBJsonWithDifferentDataTypeValues(fetchProgramsToGridResponse,drugDetailJSON,aPIjsonPathForListNameAndTypeOfDrogGroup,dBjsonPathForListNameAndTypeOfDrogGroup);
  }

}
