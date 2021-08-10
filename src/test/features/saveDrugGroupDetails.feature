Feature:Retrieve and validate the save drug group details

  @Smoke
  Scenario: Validate save drug group details creation and verify correct status code with response body is returned
    Then User executes the delete existing DrugGroup query "deleteDrugGroupByName" and List name "QAAutomation%" to delete the record from the database
    Given User create the Create new group details data
      | mfrId    | drugGroupName      | drugGroupType |
      | ASTRA001 | QAAutomation_Drug1 | Rebateable    |
    Then User hits the "createNewDrugGroupDetails" with post request of CreateNewDrugGroup
    Then User verify create new drug group status code "200" for the response
    Then User verify the valid CreateNewDrugGroup Response body key "recordSaved" and expected value "true"
    Then User verify the valid CreateNewDrugGroup Response body key "recordUpdated" and expected value "false"
    Then User verify the valid Response body key "mfrDrugListId" and execute the query "QAAutomation_Drug1" matches the Drug groupID return from API and DB
    Then User executes the query "getMFR_DrugList_ID" by drugGroup name "QAAutomation_Drug1" to get MFR_DrugList_ID to save drug group details
    Then User executes the query "getDrugGroupRowKey" by drugGroup name "QAAutomation_Drug1" to get row key for drug group to save drug group details
    Given User create the save drug group details data
      | drugListId | ndcs | startDate | endDate | query         | columnName        |
      |            |      |           |         | getListOfNdcs | DRUG_PRODUCT_CODE |
    Then User hits the "saveDrugGroupDetails" with post request of saveDrugGroupDetails API
    Then User verify save drug drug group status code "200" for the response
