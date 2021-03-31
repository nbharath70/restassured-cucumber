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
    /**
     * This method defines the glue code for @Given User executes the query to get ContractdetailsRowkey from DB
     * step in the getAllMFRContract.feature
     * @author Bharath
     * @exception SQLException
     */
    @Given("^User executes the  '(.*)' to get ContractDetails Row_KEY$")
    public void userExecutesTheToGetContractdetailsRowkey(String query) throws SQLException {
        dbUtil.executeGetContractBYIDQuery(query);
    }

    /**
     * This method defines the glue code for @Given User executes the query to get ManufacturerName and ManufacturerID from DB
     * step in the getAllMFRContract.feature
     * @author Bharath
     * @exception SQLException
     */
    @Given("^User executes the  '(.*)' to get ManufacturerName and ManufacturerID from DB$")
    public void userExecutesTheToGetManufacturernameAndManufactureridFromDb(String query4) {
    dbUtil.executeManufactuerNameQuery(query4);
    }

    /**
     * This method defines the glue code for @Given User executes the query to get ContractID from DB
     * step in the getAllMFRContract.feature
     * @author Bharath
     * @exception SQLException
     */
    @Given("^User executes the  '(.*)' to get ContractID from DB$")
    public void UserExecutesTheToGetContractidFromDb(String query2){
        dbUtil.executeContractDetailQuery(query2);
    }

    /**
     * This method defines the glue code for @Given User executes the query to get ManufacuterID from DB
     * step in the getAllMFRContract.feature
     * @author Bharath
     * @exception SQLException
     */
    @Given("^User executes the  '(.*)' to get ManufacuterID from contractHeader table$")
    public void userExecutesTheToGetManufacuteridFromContractheaderTable(String query3) {
        dbUtil.executeContractHeaderQuery(query3);
    }


}
