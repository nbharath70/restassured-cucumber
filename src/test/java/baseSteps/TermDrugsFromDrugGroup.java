package baseSteps;

import HelperClass.DataBaseHelper;
import HelperClass.ResourcePath;
import HelperClass.VerificationHelperClass;
import RequestPojo.TermDrugsFromDrugGroup.NdcDetail;
import RequestPojo.TermDrugsFromDrugGroup.TermDrugsFromDrugGroupPojoClass;
import TestBase.TestBase;
import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.lang3.time.DateUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class TermDrugsFromDrugGroup extends TestBase {
    public VerificationHelperClass verificationHelperClass = new VerificationHelperClass();
    public TermDrugsFromDrugGroupPojoClass termDrugsFromDrugGroup=new TermDrugsFromDrugGroupPojoClass();
    private String dbJson;
    DataBaseHelper dbHelper=new DataBaseHelper();
    private List<Integer> listOfRowkeys ;
    private List<String> listOfDLIds;
    private List<String> listOfNDCs ;
    private List<String> listOfStartDate;
    private List<String> listOfEndDate;
    Response response;
    ResultSet resultSet;

    /**
     * This method is FetchDrugList Details From the DB the query I used here i have Wrapped as JSON So the Column Name here is Single
     * @uthor Bharath
     * @param queryKey-This Query fetched the DrugList Details From the Drug group ie Rowkey,MFRDrugListID,NDC,StartDate,EndDate
     * @param columnName-This is the ColumnName Gave to JSON body so that Dynamic Name can be rid off
     */
    public void executeQueryandfetchDrugListDetails(String queryKey,String columnName){
        try {
            String actualColumnName=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, columnName);
            dbJson = dbHelper.getSingleCellValueAsStringFromDB(queryKey, actualColumnName);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * This Method will Fetch all the Details from the Result from the DB and Store that for Future Usage in List
     * This Method also Dynamically Generate the Term date of the Drug by Comparing the Highest Drug Start Date
     * This Methods main Ajenda is Creating the Request body Which is Required to hit the API whic is Final outcome of this method
     * @uthor Bharath
     */
    public void fetchAllTheDrugListDetails(){

            String rowKey = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "rowKeyForTermingDrugs");
            String mfrDrugListID = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "mfrDrugListID");
            String ndc = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "ndcForTermingDrugs");
            String startDate = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "startDateforDrugs");
            String endDate = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, "endDateForDrugs");

            listOfRowkeys = JsonPath.read(dbJson, rowKey);
            listOfDLIds = JsonPath.read(dbJson, mfrDrugListID);
            listOfNDCs = JsonPath.read(dbJson, ndc);
            listOfStartDate = JsonPath.read(dbJson, startDate);
            listOfEndDate = JsonPath.read(dbJson, endDate);
            String maxDate = "1900-1-1";
            Date termDate = null;
            for (int i = 0; i < listOfStartDate.size(); i++) {
                try{String startdate = listOfStartDate.get(i);
                Date maxinDate = new SimpleDateFormat("yyyy-MM-dd").parse(maxDate);
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(startdate);
                if (date.after(maxinDate)) {
                    maxinDate = date;
                    termDate = DateUtils.addDays(maxinDate, 5);}

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String termDateStr = simpleDateFormat.format(termDate);
                    for (int i = 0; i < listOfNDCs.size(); i++) {
                        NdcDetail ndcDetail = new NdcDetail();
                        termDrugsFromDrugGroup.setDrugRowKey(listOfRowkeys.get(i));
                        termDrugsFromDrugGroup.setDrugListId(listOfDLIds.get(i));
                        ndcDetail.setNdc(listOfNDCs.get(i));
                        ndcDetail.setStartDate(listOfStartDate.get(i));
                        termDrugsFromDrugGroup.setNdcDetails(ndcDetail);
                        termDrugsFromDrugGroup.setEndDate(termDateStr);
                    }


                }

    /**
     * This method is used to hit the API with Request body as Post Operation
     * @uthor Bharath
     * @param endpoint-Its a Key Passed from the Fetaure File this Endpoint will pulled from Environment Property file when it is passed to Post Operation method which i called here
     */
    public void hitEndpoint(String endpoint){
        response =postOperation(endpoint,termDrugsFromDrugGroup);

    }

    /**
     * This method is used to Verify the Status code of the response
     * @uthor Bharath
     * @param statusCode-Its a Key value Passed from the Fetaure File
     */
    public void verifyreponsecode (int statusCode)
    {
        verificationHelperClass.verifyStatusCode(response,statusCode);
        log.info("fetchJobList API's StatusCode is: "+statusCode);
    }

    /**
     * This method is used for Updating the EndDate of the DrugList and LifeCycle Status of the DrugGroup whis is changed when we hit the API
     * So that It will not change Any thing DB just like Undoing the Changes
     * @uthor Bharath
     * @param endDateUpdateQuery-Its a Key Passed from the Fetaure File which is used to Update the EndDate But the Query is not Full Since
     *                          SQl Does not have the option handle multiple ? in Query So it is Concatinated with Complete Query with Params Passed in List
     * @param  lifeCycleStatusUpdateQuery- Its a Key Passed from the Fetaure File Sams Applies for this Also as Above
     */
    public void updatingBackDetailsinDB(String endDateUpdateQuery,String lifeCycleStatusUpdateQuery){
        for (int i=0;i<listOfEndDate.size();i++){
            String updateFirstHalf=getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES,endDateUpdateQuery);
            log.info("Update the Previous EndDate is"+ listOfEndDate.get(i));
            String completeSDQuery=updateFirstHalf.concat("'"+listOfEndDate.get(i)+"'"+" where Drug_Product_Code=?"+" and "+"MFR_DrugList_ID="+listOfDLIds.get(i));
            dbHelper.executeUpdatePreparedQueryAsStringwithDirectQuery(completeSDQuery,listOfNDCs.get(i));
            dbHelper.executeUpdatePreparedQueryAsString(lifeCycleStatusUpdateQuery,listOfNDCs.get(i));

        }
    }

    /**
     * This methos is used to verify the JSON Format of the Response
     * @uthor Bharath
     */
    public void verifyTermDrugAPIResponseFormatJSON()
    {
        verifyResponseFormatIsJSON();
    }

    /**
     * This methos is used to verify the DB Details with Expected value
     * @uthor Bharath
     * @param query-this Query fetches the NDC Life Status From DB Which are termed while hitting the API
     * @param columnName-Its a Life Cycle Status Column name
     */
    public void verifyWithDB(String query,String columnName,String message) {
        try {
            for (int i = 0; i < listOfNDCs.size(); i++) {
                String querypart1= getPropertiesFileValue(ResourcePath.DATABASE_PROPERTIES,query);
                String completeQuery=querypart1.concat(" and MFR_DrugList_ID="+"'"+listOfDLIds.get(i)+"'");
                resultSet=dbHelper.executePreparedDirectQuery(completeQuery,listOfNDCs.get(i) );
                resultSet.next();
                String lcs=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES,columnName);
                String lifecycleStatusfromDB = resultSet.getString(lcs);
                verificationHelperClass.compareTwoStrings(message,lifecycleStatusfromDB);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
