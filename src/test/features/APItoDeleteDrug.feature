#In This Feature file All the Scenarios for APItoDeleteDrug exists
Feature: APItoDeleteDrug.feature - Delete Drug group with Drug detail RowKeys

  @Positive @smoke
  Scenario:Delete a Valid drug from Drug group and check the status code
    Given User executes the query "sqlToGetaValidDrugRowKey"and gets a valid drug detail "rowkey" , "columnMFRDrugListID", "columnNDC", "columnStartDate" and "columnEndDate"
    And User prepares request body with the list of drug rowkeys
    Then User hits the "deleteDrugResource" with Delete request
    And User Verifies the correct status code "200" from API response
    And User deletes the new deleted record from the DB by executing "sqlToDeleteAdeletedDrug"

  @Positive @smoke
  Scenario:Delete a Valid drug from Drug group
    Given User executes the query "sqlToGetaValidDrugRowKey"and gets a valid drug detail "rowkey" , "columnMFRDrugListID", "columnNDC", "columnStartDate" and "columnEndDate"
    And User prepares request body with the list of drug rowkeys
    Then User hits the "deleteDrugResource" with Delete request
    And User verifies APIresponse with "deleteValidDrugMsg"
    Then User executes the "sqlToCheckDeletedDrug" verifies the data base for the deleted record
    And User deletes the new deleted record from the DB by executing "sqlToDeleteAdeletedDrug"

  @Negative
  Scenario:Delete an InValid drug with LCS Added
    Given User executes the query "sqlToGetaAddedDrugRowKey"and gets a drug detail "rowkey"
    And User prepares request body with the list of drug rowkeys
    Then User hits the "deleteDrugResource" with Delete request
    And User verifies APIresponse message with "deleteNonApprovedDrugMsg"

  @Negative
  Scenario:Delete an InValid drug with LCS Termed
    Given User executes the query "sqlToGetaTermedDrugRowKey"and gets a drug detail "rowkey"
    And User prepares request body with the list of drug rowkeys
    Then User hits the "deleteDrugResource" with Delete request
    And User verifies APIresponse message with "deleteNonApprovedDrugMsg"
  @Negative
  Scenario:Delete an InValid drug with LCS Deleted
    Given User executes the query "sqlToGetaDeletedDrugRowKey"and gets a drug detail "rowkey"
    And User prepares request body with the list of drug rowkeys
    Then User hits the "deleteDrugResource" with Delete request
    And User verifies APIresponse message with "deleteNonApprovedDrugMsg"

  @Negative
  Scenario:Delete an InValid drug group detail rowkey
    Given User prepares request body with InValid RowKey "111119999" of drug
    Then User hits the "deleteDrugResource" with Delete request
    And User verifies APIresponse message with "deleteInvalidDrugMsg"

  @Negative
  Scenario:Delete a Valid and an InValid drug group detail rowkey
    Given User executes the query "sqlToGetaValidDrugRowKey"and gets a drug detail "rowkey"
    Given User prepares request body with valid and InValid RowKey "111119999" of drug
    Then User hits the "deleteDrugResource" with Delete request
    And User verifies APIresponse message with "deleteValidAndInvalidDrugMsg"

  @Negative
  Scenario:Delete multiple InValid drug group detail rowkey
    Given User prepares request body with InValid RowKeys "111119999" and "12345678" of drug
    Then User hits the "deleteDrugResource" with Delete request
    And User verifies APIresponse message with "deleteInvalidDrugMsg"

  @Positive @smoke
  Scenario:Delete two Valid drugs from Drug group
    Given User executes the query "sqlToGetMultipleValidDrugRowKey"and get multiple valid drug detail "rowkey" , "columnMFRDrugListID", "columnNDC", "columnStartDate" and "columnEndDate"
    And User prepares request body with the multiple drug rowkeys
    Then User hits the "deleteDrugResource" with Delete request
    And User verifies APIresponse with "deleteValidMultipleDrugMsg" for two drugs
    Then User executes the "sqlToCheckDeletedDrug" verifies the data base for the deleted record for two drugs
    And User deletes the new deleted record from the DB by executing "sqlToDeleteAdeletedDrug" for two drugs