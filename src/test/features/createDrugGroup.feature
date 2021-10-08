Feature: Retrieve and validate the Create new Drug group details

  @Smoke
  Scenario: Validate Create new Drug group details creation and verify correct status code
    Given User creates a new drug group
      | mfrId    | drugGroupName      | drugGroupType | condition |
      | ASTRA001 | QAAutomation_Drug1 | Rebateable    | valid     |
    Then User hits the "createNewDrugGroupDetails" with post request of CreateNewDrugGroup with query "getRowKeyForCreateDrugGroup"
    Then User verify create new drug group status code "200" for the response
    Then User verify the valid CreateNewDrugGroup Response body key "recordSaved" and expected value "true"
    Then User verify the valid CreateNewDrugGroup Response body key "recordUpdated" and expected value "false"
    Then User verify the valid Response body key "mfrDrugListId" and execute the query "getDrugListID" matches the Drug groupID return from API and DB
    Then User discards the drugGroup and deletes the record from DB

  @Functional
  Scenario: validation drug group exists
    Given User creates a new drug group
      | mfrId    | drugGroupName      | drugGroupType |condition|
      | ASTRA001 | QAAutomation_Drug1 | Rebateable    |valid    |
    Then User hits the "createNewDrugGroupDetails" with post request of CreateNewDrugGroup with query "getRowKeyForCreateDrugGroup"
    Then User verify create new drug group status code "200" for the response
    Then User verify the valid CreateNewDrugGroup Response body key "recordSaved" and expected value "true"
    Then User verify the valid CreateNewDrugGroup Response body key "recordUpdated" and expected value "false"
    Then User verify the valid Response body key "mfrDrugListId" and execute the query "QAAutomation_Drug1" matches the Drug groupID return from API and DB
    And User creates a new drug group
      | mfrId    | drugGroupName      | drugGroupType |condition|
      | ASTRA001 | QAAutomation_Drug1 | Rebateable    |invalid  |
    Then User hits the "createNewDrugGroupDetails" with post request of CreateNewDrugGroup with query "getRowKeyForCreateDrugGroup"
    Then User verify the Create new group details response header Error Code value "501"
    Then User verify create new drug group status code "200" for the response
    Then User verify the valid CreateNewDrugGroup Response body key "recordSaved" and expected value "false"
    Then User verify the valid CreateNewDrugGroup Response body key "recordUpdated" and expected value "false"
    Then User verify the valid CreateNewDrugGroup Response body key "drugGroupExists" and expected value "true"
    Then User discards the drugGroup and deletes the record from DB

  @Functional
  Scenario: validation Blank input response of mfrId,drugGroupName and drugGroupType
    Given User creates a new drug group
      | mfrId | drugGroupName | drugGroupType |condition|
      |       |               |               |invalid  |
    Then User hits the "createNewDrugGroupDetails" with post request of CreateNewDrugGroup with query "getRowKeyForCreateDrugGroup"
    Then User verify the Create new group details response header Error Code value "-1"
    Then User verify create new drug group status code "200" for the response
    Then User verify the valid Response CreateNewDrugGroup body key "messageDrugGroup" and expected value "Invalid input: mfrId,drugGroupName,drugGroupType" of string
    Then User discards the drugGroup and deletes the record from DB

  @Regression @Functional
  Scenario: validation Invalid manufactureId
    Given User creates a new drug group
      | mfrId | drugGroupName      | drugGroupType |condition|
      | Test  | QAAutomation_Drug2 | Rebateable    |invalid  |
    Then User hits the "createNewDrugGroupDetails" with post request of CreateNewDrugGroup with query "getRowKeyForCreateDrugGroup"
    Then User verify the Create new group details response header Error Code value "-1"
    Then User verify create new drug group status code "200" for the response
    Then User verify the valid Response CreateNewDrugGroup body key "messageDrugGroup" and expected value "Invalid Data: DrugGroupName/ DrugGroupType/ ManufacturerId" of string
    Then User discards the drugGroup and deletes the record from DB

  @Regression @Functional
  Scenario: validation Invalid drugGroupName
    Given User creates a new drug group
      | mfrId    | drugGroupName       | drugGroupType |condition|
      | ASTRA001 | #QAAutomation_Drug2 | Rebateable    |invalid  |
    Then User hits the "createNewDrugGroupDetails" with post request of CreateNewDrugGroup with query "getRowKeyForCreateDrugGroup"
    Then User verify the Create new group details response header Error Code value "-1"
    Then User verify create new drug group status code "200" for the response
    Then User verify the valid Response CreateNewDrugGroup body key "messageDrugGroup" and expected value "Invalid Data: DrugGroupName/ DrugGroupType/ ManufacturerId" of string
    Then User discards the drugGroup and deletes the record from DB

  @Regression @Functional
  Scenario: validation Invalid drugGroupType
    Given User creates a new drug group
      | mfrId    | drugGroupName      | drugGroupType |condition|
      | ASTRA001 | QAAutomation_Drug2 | Test          |invalid  |
    Then User hits the "createNewDrugGroupDetails" with post request of CreateNewDrugGroup with query "getRowKeyForCreateDrugGroup"
    Then User verify the Create new group details response header Error Code value "-1"
    Then User verify create new drug group status code "200" for the response
    Then User verify the valid Response CreateNewDrugGroup body key "messageDrugGroup" and expected value "Invalid Data: DrugGroupName/ DrugGroupType/ ManufacturerId" of string
    Then User discards the drugGroup and deletes the record from DB


