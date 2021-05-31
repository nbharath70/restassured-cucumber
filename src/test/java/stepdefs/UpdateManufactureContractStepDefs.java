package stepdefs;

import TestBase.TestBase;
import baseSteps.UpdateManufactureContract;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;

public class UpdateManufactureContractStepDefs extends TestBase {
    UpdateManufactureContract putUpdateManufactureContract=new UpdateManufactureContract();

    @Then("^User update manufacture contract data$")
    public void userUpdateManufactureContractData(DataTable dataTable) {
        putUpdateManufactureContract.updateExistingManufactureContractDetails(dataTable);
    }

    @Then("^User hits the \"([^\"]*)\" update manufacture contract post request$")
    public void userHitsTheUpdateManufactureContractPostRequest(String endPoint) throws Throwable {
        putUpdateManufactureContract.updateManufactureContractPutCall(endPoint);
    }

    @Then("^User verify the updateManufacturerContract valid Response body key \"([^\"]*)\" and expected value \"([^\"]*)\"$")
    public void userVerifyTheUpdateManufacturerContractValidResponseBodyKeyAndExpectedValue(String actualValue,String expectedValue) throws Throwable {
        putUpdateManufactureContract.validationResultsBooleanforUpdateManfCont(actualValue,expectedValue);
    }

    @Then("^User verify the updateManufacturerContract valid Response body key \"([^\"]*)\" and expected value \"([^\"]*)\" of string$")
    public void userVerifyTheUpdateManufacturerContractValidResponseBodyKeyAndExpectedValueOfString(String actualValue,String expectedValue) throws Throwable {
        putUpdateManufactureContract.validationResultsStringforUpdateManfCont(actualValue,expectedValue);
    }

    @Then("^User verify UpdateManufacturerContract status code \"([^\"]*)\" for the response$")
    public void userVerifyUpdateManufacturerContractStatusCodeForTheResponse(int statusCode) throws Throwable {
        putUpdateManufactureContract.postUpdateManufactureContractStatusCode(statusCode);
    }

    @Then("^User verify the updateManufacturerContract response header Error Code value \"([^\"]*)\"$")
    public void userVerifyTheUpdateManufacturerContractResponseHeaderErrorCodeValue(String expectedErrorCode) throws Throwable {
        putUpdateManufactureContract.verifyUpdateManufactureContractResponseHeaderErrorCode(expectedErrorCode);
    }
}
