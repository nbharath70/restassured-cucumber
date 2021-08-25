package stepdefs;

import TestBase.TestBase;
import baseSteps.GetDrugGroupDetailsByMFRDrugListID;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class GetDrugGroupDetailsByMFRDrugListIDStepdefs extends TestBase {
    GetDrugGroupDetailsByMFRDrugListID getDrugGroupDetailsByMFRDrugListID=new GetDrugGroupDetailsByMFRDrugListID();
    @Given("^User executes query \"([^\"]*)\" and gets MFRDrugListID from column \"([^\"]*)\"$")
    public void userExecutesQueryAndGetsMFRDrugListIDFromColumn(String query, String columnName) throws Throwable {
        getDrugGroupDetailsByMFRDrugListID.userExecutesQueryAndGetsMFRDrugListID(query,columnName);
    }

    @And("^User Hits API \"([^\"]*)\" with Get request for getDrugGroupdetails$")
    public void userHitsAPIWithGetRequestForGetDrugGroupdetails(String endPoint) throws Throwable {
        getDrugGroupDetailsByMFRDrugListID.userHitsAPI(endPoint);
    }

    @Then("^User verifies the valid status code \"([^\"]*)\" in getDrugGroupdetails API response$")
    public void userVerifiesTheValidStatusCodeInGetDrugGroupdetailsAPIResponse(int statusCode) throws Throwable {
        getDrugGroupDetailsByMFRDrugListID.verifyStatusCodeReturnedForDrugGroupDetailsAPI(statusCode);
    }


    @Then("^User executes the query \"([^\"]*)\" & columnName \"([^\"]*)\" And matches the DrugGroupDetails returned by API and DB$")
    public void userExecutesTheQueryColumnNameAndMatchesTheDrugGroupDetailsReturnedByAPIAndDB( String jsonPath,String columnName) throws Throwable {
        getDrugGroupDetailsByMFRDrugListID.validationOfDrugGroupDetails(columnName,jsonPath);
    }
}
