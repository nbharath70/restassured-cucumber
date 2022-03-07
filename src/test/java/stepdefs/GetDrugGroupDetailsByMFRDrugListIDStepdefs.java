package stepdefs;

import TestBase.TestBase;
import baseSteps.GetDrugGroupDetailsByMFRDrugListID;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class GetDrugGroupDetailsByMFRDrugListIDStepdefs extends TestBase {
    GetDrugGroupDetailsByMFRDrugListID getDrugGroupDetailsByMFRDrugListID=new GetDrugGroupDetailsByMFRDrugListID();
    @Given("^User executes query \"([^\"]*)\" and gets MFRDrugListRowKey from column \"([^\"]*)\"$")
    public void userExecutesQueryAndGetsMFRDrugListRowKeyFromColumn(String query, String columnName) throws Throwable {
        getDrugGroupDetailsByMFRDrugListID.userExecutesQueryAndGetsMFRDrugListRowKey(query,columnName);
    }

    @And("^User Hits API \"([^\"]*)\" with Get request for getDrugGroupdetails$")
    public void userHitsAPIWithGetRequestForGetDrugGroupdetails(String endPoint) throws Throwable {
        getDrugGroupDetailsByMFRDrugListID.userHitsAPI(endPoint);
    }

    @Then("^User verifies the valid status code \"([^\"]*)\" in getDrugGroupdetails API response$")
    public void userVerifiesTheValidStatusCodeInGetDrugGroupdetailsAPIResponse(int statusCode) throws Throwable {
        getDrugGroupDetailsByMFRDrugListID.verifyStatusCodeReturnedForDrugGroupDetailsAPI(statusCode);
    }

//    @Then("^User executes the query \"([^\"]*)\" & columnName \"([^\"]*)\" And matches the DrugGroupDetails returned by API and DB$")
//    public void userExecutesTheQueryColumnNameAndMatchesTheDrugGroupDetailsReturnedByAPIAndDB( String jsonPath,String columnName) throws Throwable {
//        getDrugGroupDetailsByMFRDrugListID.validationOfDrugGroupDetails(columnName,jsonPath);
//    }

    @Then("^User executes query \"([^\"]*)\" and gets drug details$")
    public void userExecutesQueryToGetsDrugDetails(String queryKey) {
        getDrugGroupDetailsByMFRDrugListID.executeQueryToGetDrugDetails(queryKey);
    }

    @Then("^User verifies drug details by drug group row key API response with DB response$")
    public void userVerifiesDrugDetailsByDrugGroupRowKeyAPIResponseWithDBResponse() {
        getDrugGroupDetailsByMFRDrugListID.verifyDrugDetailsByDrugGroupRowKeyAPIResponseWithDBResponse();
    }

    @And("^User Hits API \"([^\"]*)\" with invalid drug list row key with Get request$")
    public void userHitsAPIWithInvalidDrugListRowKeyWithGetRequest(String endpoint)  {
        getDrugGroupDetailsByMFRDrugListID.userHitsAPIWithInvalidDrugListRowKey(endpoint);
    }

    @Then("^User executes query \"([^\"]*)\" with invalid drug list row key and gets drug details$")
    public void userExecutesQueryWithInvalidDrugListRowKeyAndGetsDrugDetails(String queryKey)  {
        getDrugGroupDetailsByMFRDrugListID.executeQueryWithInvalidDrugListRowKeyAndGetsDrugDetails(queryKey);
    }
}
