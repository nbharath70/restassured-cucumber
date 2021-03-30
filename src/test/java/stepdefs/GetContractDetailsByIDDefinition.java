package stepdefs;
import baseSteps.GetContractDetailsByID;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;


public class GetContractDetailsByIDDefinition {
    GetContractDetailsByID ContractDetailsByID =new GetContractDetailsByID();
    @Given("^User hits the MFRDetailsByIDEndpoint with get request$")
    public void UserHitsGetMFRdetails()  {
        ContractDetailsByID.hitGetContractDetailsByIDEndpoint();
    }


    @When("^ API  processes the request$")
    public void apiProcessesTheRequest()  {
        ContractDetailsByID.processGetRequest();
    }

    @Then("^User gets the correct statusCode from API ContractDetails by ID$")
    public void userGetsTheCorrectStatuscode()  {
        ContractDetailsByID.verifyGetRequestStatusCode200();
    }



}