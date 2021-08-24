package baseSteps;

import HelperClass.DataBaseHelper;
import HelperClass.ResourcePath;
import HelperClass.VerificationHelperClass;
import RequestPojo.DeleteDrugPojo;
import TestBase.TestBase;
import io.restassured.response.Response;
import org.junit.Assert;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DeleteDrugAPI extends TestBase {
    DeleteDrugPojo deleteDrugPojo=new DeleteDrugPojo();
    private int drugRowkey=0;
    private String mfrDLId=null;
    private String ndc=null;
    private String startDate=null;
    private String endDate=null;
    private int drugRowkey1=0;
    private String mfrDLId1=null;
    private String ndc1=null;
    private String startDate1=null;
    private String endDate1=null;
    List<String> listOfQueryParams=null;

    Response response=null;
    DataBaseHelper dataBaseHelper=new DataBaseHelper();
    VerificationHelperClass verificationHelperClass=new VerificationHelperClass();
    public void executeTheQueryAndGetAValidDrugDetail(String queryKey, String rowkeyKey, String mfrDLIdKey, String ndcKey,String startDateKey,String endDateKey) {
        try {
            ResultSet rs = dataBaseHelper.getData(queryKey);
            String rowkeyColumnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, rowkeyKey);
            String mfrDLIdColumnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, mfrDLIdKey);
            String ndcColumnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, ndcKey);
            String startDatecolumnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, startDateKey);
            String endDatecolumnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, endDateKey);
            rs.next();
            drugRowkey = Integer.parseInt(rs.getString(rowkeyColumnName));
            mfrDLId=rs.getString(mfrDLIdColumnName);
            ndc = rs.getString(ndcColumnName);
            startDate=rs.getString(startDatecolumnName);
            endDate=rs.getString(endDatecolumnName);
        }catch (Exception e){e.printStackTrace();}

    }

    public void prepareRequestBodyWithTheListOfDrugRowkeys() {
        List<Integer> listOfRowKeys=new ArrayList<Integer>();
        listOfRowKeys.add(drugRowkey);
        System.out.println(listOfRowKeys);
        deleteDrugPojo.setDetailRowKeys(listOfRowKeys);
    }

    public void hitEndPointWithPostRequest(String endPointKey) {
        response=deleteOperation(endPointKey,deleteDrugPojo);

    }

    public void verifyAPIresponseWith(String responseMsgKey) {
        List<String> listOfRowKeys=new ArrayList<String>();
        listOfRowKeys.add(String.valueOf(drugRowkey));
        String expectedResponse=dataBaseHelper.preparedQueryWithListOfStrings(responseMsgKey,listOfRowKeys);
        verificationHelperClass.verifyexpectedAndActualDirectlyAsStrings(response,expectedResponse);
    }

    public void verifyTheDataBaseForTheDeletedDrug(String queryKey) {
        try {
            listOfQueryParams = new ArrayList<String>();
            listOfQueryParams.add(mfrDLId);
            listOfQueryParams.add(ndc);
            listOfQueryParams.add(startDate);
            listOfQueryParams.add(endDate);
            String deletedRecordQuery = dataBaseHelper.preparedQueryWithListOfStrings(queryKey, listOfQueryParams);
            ResultSet rs = dataBaseHelper.getDataWithoutPropertiesKey(deletedRecordQuery);
//            rs.next();
//            String actual_LCS=rs.getString("Life_Cycle_Status");
//            Assert.assertEquals("Deleted record not found in DB","Deleted",actual_LCS);
            Assert.assertTrue("Deleted record not found in DB",rs.next());
            log.info("Deleted record found in DB");

        }catch (Exception e){e.printStackTrace();}

    }

    public void deleteTheNewDeletedRecordFromDB(String queryKey) {
        String SQLToDeleteTheDeletedRecord = dataBaseHelper.preparedQueryWithListOfStrings(queryKey, listOfQueryParams);
        dataBaseHelper.executeDeleteQueryWithoutreadingFromPropFile(SQLToDeleteTheDeletedRecord);
    }

    public void verifyTheCorrectStatusCode(int statusCode) {
        verificationHelperClass.verifyStatusCode(response,statusCode);
    }

    public void verifiesAPIresponseMessageWithDeleteAddedDrugResponse(String responseMsgKey) {
        String expectedResponse=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, responseMsgKey);
        verificationHelperClass.verifyexpectedAndActualDirectlyAsStrings(response,expectedResponse);
    }

    public void prepareRequestBodyWithInValidRowKeyOfDrug(int rowKey) {
        List<Integer> listOfRowKeys=new ArrayList<Integer>();
        listOfRowKeys.add(rowKey);
        deleteDrugPojo.setDetailRowKeys(listOfRowKeys);
    }

    public void executesTheQueryAndGetsADrugDetailRowkey(String queryKey, String rowkeyKey) {
        try {
            ResultSet rs = dataBaseHelper.getData(queryKey);
            String rowkeyColumnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, rowkeyKey);
            rs.next();
            drugRowkey = Integer.parseInt(rs.getString(rowkeyColumnName));
        }catch (Exception e){e.printStackTrace();}
    }

    public void prepareRequestBodyWithValidAndInValidRowKeysOfDrug(int rowKey) {
        List<Integer> listOfRowKeys=new ArrayList<Integer>();
        listOfRowKeys.add(drugRowkey);
        listOfRowKeys.add(rowKey);
        deleteDrugPojo.setDetailRowKeys(listOfRowKeys);
    }

    public void prepareRequestBodyWithInValidRowKeys(int rowKey1, int rowKey2) {
        List<Integer> listOfRowKeys=new ArrayList<Integer>();
        listOfRowKeys.add(rowKey1);
        listOfRowKeys.add(rowKey2);
        deleteDrugPojo.setDetailRowKeys(listOfRowKeys);
    }

    public void executeQueryAndGetMultipleValidDrugDetail(String queryKey, String rowkeyKey, String mfrDLIdKey, String ndcKey, String startDateKey, String endDateKey) {
        try {
            ResultSet rs = dataBaseHelper.getData(queryKey);
            String rowkeyColumnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, rowkeyKey);
            String mfrDLIdColumnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, mfrDLIdKey);
            String ndcColumnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, ndcKey);
            String startDatecolumnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, startDateKey);
            String endDatecolumnName = getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, endDateKey);
            rs.next();
            drugRowkey = Integer.parseInt(rs.getString(rowkeyColumnName));
            mfrDLId=rs.getString(mfrDLIdColumnName);
            ndc = rs.getString(ndcColumnName);
            startDate=rs.getString(startDatecolumnName);
            endDate=rs.getString(endDatecolumnName);
            rs.next();
            drugRowkey1 = Integer.parseInt(rs.getString(rowkeyColumnName));
            mfrDLId1=rs.getString(mfrDLIdColumnName);
            ndc1 = rs.getString(ndcColumnName);
            startDate1=rs.getString(startDatecolumnName);
            endDate1=rs.getString(endDatecolumnName);
        }catch (Exception e){e.printStackTrace();}

    }

    public void prepareRequestBodyWithMultipleDrugRowkeys() {
        List<Integer> listOfRowKeys=new ArrayList<Integer>();
        listOfRowKeys.add(drugRowkey);
        listOfRowKeys.add(drugRowkey1);
        System.out.println(listOfRowKeys);
        deleteDrugPojo.setDetailRowKeys(listOfRowKeys);
    }

    public void verifyAPIresponseWithSuccessMsgforTwoDrugs(String expectedMsgKey) {
        List<String> listOfRowKeys=new ArrayList<String>();
        listOfRowKeys.add(String.valueOf(drugRowkey));
        listOfRowKeys.add(String.valueOf(drugRowkey1));
        String expectedResponse=dataBaseHelper.preparedQueryWithListOfStrings(expectedMsgKey,listOfRowKeys);
        verificationHelperClass.verifyexpectedAndActualDirectlyAsStrings(response,expectedResponse);
    }

    public void executeQueryAndVerifyTheDataBaseForTheDeletedRecordForTwoDrugs(String queryKey) {
        try {
            listOfQueryParams = new ArrayList<String>();
            listOfQueryParams.add(mfrDLId);
            listOfQueryParams.add(ndc);
            listOfQueryParams.add(startDate);
            listOfQueryParams.add(endDate);
            String deletedRecordQuery = dataBaseHelper.preparedQueryWithListOfStrings(queryKey, listOfQueryParams);
            ResultSet rs = dataBaseHelper.getDataWithoutPropertiesKey(deletedRecordQuery);
//            rs.next();
//            String actual_LCS=rs.getString("Life_Cycle_Status");
//            Assert.assertEquals("Deleted record not found in DB","Deleted",actual_LCS);
            Assert.assertTrue("Deleted record not found in DB for first Drug", rs.next());
            log.info("Deleted record found in DB for first Drug With RowKey="+rs.getString("Row_Key"));

            listOfQueryParams = new ArrayList<String>();
            listOfQueryParams.add(mfrDLId1);
            listOfQueryParams.add(ndc1);
            listOfQueryParams.add(startDate1);
            listOfQueryParams.add(endDate1);
            String deletedRecordQuery1 = dataBaseHelper.preparedQueryWithListOfStrings(queryKey, listOfQueryParams);
            ResultSet rs1 = dataBaseHelper.getDataWithoutPropertiesKey(deletedRecordQuery1);
            Assert.assertTrue("Deleted record not found in DB for second Drug", rs.next());
            log.info("Deleted record found in DB for second DrugWith RowKey="+rs.getString("Row_Key"));
        }catch (Exception e){e.printStackTrace();}
    }

    public void deleteTheNewDeletedRecordsFromTheDBByExecutingDeleteQueryForTwoDrugs(String queryKey) {
        listOfQueryParams = new ArrayList<String>();
        listOfQueryParams.add(mfrDLId);
        listOfQueryParams.add(ndc);
        listOfQueryParams.add(startDate);
        listOfQueryParams.add(endDate);
        String SQLToDeleteTheDeletedRecord = dataBaseHelper.preparedQueryWithListOfStrings(queryKey, listOfQueryParams);
        dataBaseHelper.executeDeleteQueryWithoutreadingFromPropFile(SQLToDeleteTheDeletedRecord);

        listOfQueryParams = new ArrayList<String>();
        listOfQueryParams.add(mfrDLId1);
        listOfQueryParams.add(ndc1);
        listOfQueryParams.add(startDate1);
        listOfQueryParams.add(endDate1);
        String SQLToDeleteTheDeletedRecord1 = dataBaseHelper.preparedQueryWithListOfStrings(queryKey, listOfQueryParams);
        dataBaseHelper.executeDeleteQueryWithoutreadingFromPropFile(SQLToDeleteTheDeletedRecord1);

    }
}
