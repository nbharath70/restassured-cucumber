/**
 * DatabaseStepDefinitions class contains steps def for the database related scenarios in all feature file
 * and it calls the respective method to do actual operation present in DatabaseUtils classes.
 * @author  QATest
 * @version 1.0
 * @since   03/01/2021
 */
package stepdefs;
import baseSteps.DatabaseUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.sql.SQLException;

public class DatabaseStepDefinition {
    DatabaseUtils dbUtil = new DatabaseUtils();

    @Given("^User connects to DB$")
    public void UserConnectsToDB() throws SQLException {
        dbUtil.connectToRebateDB();
    }
    @Then("^DB details are obtained and printed$")
    public void DBDetailsAreObtainedAndPrinted() throws SQLException {
        dbUtil.getDBDetails();
    }
    @Then("^User executes the  '(.*)'$")
    public void UserExecutesTheQuery(String query) throws SQLException {
        dbUtil.executeGetMFRCountQuery(query);
    }
    @Then("^User executes the  '(.*)' to get allActiveMFRID$")
    public void UserExecutesTheQueryToGetAllActiveMFRID(String query) throws SQLException {
        dbUtil.executeAllMFRIDQuery(query);
    }

    @Then("^User executes the  '(.*)' to get allActiveMFR name$")
    public void UserExecutesTheQueryToGetAllActiveMFRName(String query) throws SQLException {
        dbUtil.executeAllMFRNameQuery(query);
    }
    @Given("^User executes the  '(.*)' to get ContractDetails Row_KEY$")
    public void userExecutesTheToGetContractdetailsRowkey(String query) throws SQLException {
        dbUtil.executeGetMFRBYIDQuery(query);
    }
}
