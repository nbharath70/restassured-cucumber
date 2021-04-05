/**
 * GetContractDetailsByIDDefinition class contains the glue code for getContractDetailsByID feature file
 * @author  Bharath.N
 * @version 1.0
 * @since   30/03/2021
 */
package stepdefs;
import baseSteps.GetContractDetailsByID;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;


public class GetContractDetailsByIDDefinition {

    GetContractDetailsByID contractDetails=new GetContractDetailsByID();
    /**
     * This method defines the glue code for @given User hit the endpoint with get request
     * step in the getAllMFRContract.feature
     * @author Bharath
     */
    @Given("^User hits the ContractDetailsByIDEndpoint with get request$")
    public void UserHitscontractDetailsByID()  {
        contractDetails.hitGetContractDetailsByIDEndpoint();
    }

    /**
     * This method defines the glue code for @Given User hit the endpoint with WrongRowKey
     * step in the getAllMFRContract.feature
     * @author Bharath
     */
    @Given("^User hits the ContractDetailsByIDEndpoint with wrong Rowkey with get request$")
    public void userHitsTheContractdetailsbyidendpointWithWrongRowkeyWithGetRequest() {
        contractDetails.hitGetContractDetailsByIDEndpointWithInvalidRowKey();
    }

    /**
     * This method defines the glue code for @Given User hit the endpoint with Blank RowKey
     * step in the getAllMFRContract.feature
     * @author Bharath
     */
    @Given("^User hits the BlankContractDetailsByIDEndpoint with  get request$")
    public void user_hits_the_blankcontractdetailsbyidendpoint_with_get_request(){
        contractDetails.hitBlankContractDetailsByIDEndpoint();
    }

    /**
     * This method defines the glue code for @When which proccesses the request
     * step in the getAllMFRContract.feature
     * @author Bharath
     */
    @When("^API  processes the get request for manufaturer contract details$")
    public void apiProcessesTheRequest()  {
        contractDetails.processGetRequest();
    }

    /**
     * This method defines the glue code for @Then which verifies the status code
     * step in the getAllMFRContract.feature
     * @author Bharath
     */
    @Then("^User gets the correct statusCode from API ContractDetails by ID$")
    public void userGetsTheCorrectStatuscode()  {
        contractDetails.verifyGetRequestStatusCode200();
    }

    /**
     * This method defines the glue code for @Then which verifies the response in JSON
     * step in the getAllMFRContract.feature
     * @author Bharath
     */
    @Then("^Then User verifies the response is in JSON format$")
    public void userVerifiesTheResponseIsInJsonFormat() {contractDetails.verifyResponseIsInJSONformat();
    }

    /**
     * This method defines the glue code for @Then which compare the manufacturerID and Name in response with DB
     * step in the getAllMFRContract.feature
     * @author Bharath
     */
    @Then("^User verifies the response details with DB details$")
    public void userVerifiesTheResponseDetailsWithDbDetails() {
        contractDetails.matchmanufacturerDetails();
    }

    /**
     * This method defines the glue code for @Then which compare the ContractHeaderDetails in response with DB
     * step in the getAllMFRContract.feature
     * @author Bharath
     */
    @Then("^User verifies the ContractHeaderDetails details with DB details$")
    public void userVerifiesTheContractHeaderdetailsDetailsWithDbDetails()  {
       contractDetails.matchContracHeadertDetails();
    }

    /**
     * This method defines the glue code for @Then which compare the ContractHeaderDetailsJSON in response with DB
     * step in the getAllMFRContract.feature
     * @author Bharath
     */
    @Then("^User verifies the ContractHeaderDetailsJSON details with DB details$")
    public void UserVerifiesTheContractHeaderdetailsjsonDetailsWithDbDetails(){
        contractDetails.matchContractDetailJSON();
    }

    /**
     * This method defines the glue code for @Then User Verifies the Error Message from API
     * step in the getAllMFRContract.feature
     * @author Bharath
     */
    @Then("^User verifies Error message from API$")
    public void userVerifiesErrorMessageFromApi(){
        contractDetails.matchInvalidRowKeyResponse();
    }

    /**
     * This method defines the glue code for @Then User Verifies the Error Message from API
     * step in the getAllMFRContract.feature
     * @author Bharath
     */
    @Then("^User verifies BlankID Error message from API$")
    public void user_verifiesBlankidErrorMessageFromApi(){
        contractDetails.matchBlankRowKeyResponse();
    }
    @Given("^User hits the ContractDetailsByIDEndpoint with TypeMissMatch RowKey$")
    public void userHitsTheContractdetailsbyidendpointWithTypemissmatchRowkey(){
        contractDetails.hitTypeMissMAtchContractDetailsByIDEndpoint();
    }
    @Then("^User verifies the Response of API for TypeMissMatch request$")
    public void userVerifiesTheResponseOfApiForTypemissmatchRequest() {
        contractDetails.matchTypeMissmatchRowKeyResponse();
    }





}