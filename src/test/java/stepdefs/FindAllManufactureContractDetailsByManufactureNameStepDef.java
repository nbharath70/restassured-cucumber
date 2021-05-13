package stepdefs;

import RequestPojo.*;
import TestBase.TestBase;
import baseSteps.FindAllManufactureContractDetailsByManufactureName;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FindAllManufactureContractDetailsByManufactureNameStepDef extends TestBase {
    ManufactureName manufactureName;
    FindAllManufactureContractDetailsByManufactureName findAllManufactureContractDetailsByManufactureName=new FindAllManufactureContractDetailsByManufactureName();
    @Then("^User find all manufacture contract details for given manufacture name \"([^\"]*)\"$")
    public void userFindAllManufactureContractDetailsForGivenManufactureName(String manfName) throws Throwable {
       try {
            manufactureName=new ManufactureName(manfName);
       }catch (Exception e)
       {
           e.printStackTrace();
       }
    }

    @Then("^User hits the FindAllManufactureDetails \"([^\"]*)\" with post request$")
    public void userHitsTheFindAllManufactureDetailsWithPostRequest(String endPoint) throws Throwable {
        findAllManufactureContractDetailsByManufactureName.findAllManufactureContractDetailsByManufactureNamePostCall(endPoint,manufactureName);
    }

    @Then("^User verify FindAllManufactureDetails status code \"([^\"]*)\" for the response$")
    public void userVerifyFindAllManufactureDetailsStatusCodeForTheResponse(int statusCode) throws Throwable {
        findAllManufactureContractDetailsByManufactureName.findAllManufactureContractDetailsByManufactureNameStatusCode(statusCode);
    }

    @When("^User executes FindAllManufacture query \"([^\"]*)\" And matches the count of MFR returned by API and DB$")
    public void userExecutesFindAllManufactureQueryAndMatchesTheCountOfMFRReturnedByAPIAndDB(String query) throws Throwable {
        findAllManufactureContractDetailsByManufactureName.verifyFindAllManufactureCount(query);
    }
}
