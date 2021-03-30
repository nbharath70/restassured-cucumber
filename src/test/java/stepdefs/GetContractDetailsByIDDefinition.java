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



}