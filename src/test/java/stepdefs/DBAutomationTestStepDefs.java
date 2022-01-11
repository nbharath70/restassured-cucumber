package stepdefs;

import baseSteps.DBAutomationTest;
import baseSteps.DeleteDrugAPI;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class DBAutomationTestStepDefs {
    DBAutomationTest DBAuto=new DBAutomationTest();


    @Given("^DB Automaiton Test$")
    public void dbAutomaitonTestH2()  {
//        deleteDrugAPI.connectToTheFlowableAndExecutesTheQuery(query);
//    deleteDrugAPI.inMemoryDBSQLite();
        DBAuto.inMemoryDBH2();
    }

//    CREATE ALIAS GET_ORDERS AS  $$
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.Collection;
//import java.util.HashSet;
//import java.util.stream.Collectors;
//    @CODE
//    ResultSet getOrdersWithValidItems(final Connection conn, final int valueId) throws SQLException
//    {
//        final Collection<String> recordsWithoutValidity = new HashSet<String>();
//        final Collection<String> validRecords = new HashSet<String>();
//        // get records without validity information
//        StringBuffer sql = new StringBuffer();
//        sql.append("declare @id BIGINT="+valueId+"; INSERT INTO Registration VALUES (@id, 'Test', 'first', 11)");
////        sql.append("WHERE CUSTOMER='" + customer + "' ");
//        PreparedStatement ps = conn.prepareStatement(sql.toString());
//        ps.execute();
//        return
//    }
//    $$;
}
