#In This Feature file All the Scenarios for APItoDeleteDrug exists
Feature: APItoDeleteDrug.feature - Delete Drug group with Drug detail RowKeys

  @Positive @smoke
  Scenario:Delete a Valid drug from Drug group
    Given User executes the query "sqlToGetaValidDrugRowKey"and gets a valid drug detail "rowkey"
    And User prepares request body with the list of drug rowkeys
    Then User hits the "deleteDrugResource" with Delete request
    And User verifies APIresponse with "deleteValidDrugMsg"



