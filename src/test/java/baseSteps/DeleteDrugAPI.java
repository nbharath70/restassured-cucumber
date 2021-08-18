package baseSteps;

import HelperClass.DataBaseHelper;
import HelperClass.ResourcePath;
import HelperClass.VerificationHelperClass;
import RequestPojo.DeleteDrugPojo;
import TestBase.TestBase;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;

public class DeleteDrugAPI extends TestBase {
    DeleteDrugPojo deleteDrugPojo=new DeleteDrugPojo();
    private int drugRowkey=0;
    Response response=null;
    DataBaseHelper dataBaseHelper=new DataBaseHelper();
    VerificationHelperClass verificationHelperClass=new VerificationHelperClass();
    public void executeTheQueryAndGetAValidDrugDetail(String queryKey, String columnNameKey) {
        String columnName=getPropertiesFileValue(ResourcePath.VERIFICATION_PROPERTIES, columnNameKey);
        drugRowkey=Integer.parseInt(dataBaseHelper.getSingleCellValueAsStringFromDB(queryKey,columnName));
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
}
