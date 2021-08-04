Feature:Retrieve and validate the Create new Drug group details

  @Smoke
  Scenario: Validate Create new Drug group details creation and verify correct status code with response body is returned
    Then User executes the delete existing DrugGroup query "deleteDrugGroupByName" and List name "QAAutomation%" to delete the record from the database
    Given User create the Create new group details data
      | mfrId    | drugGroupName      | drugGroupType |
      | ASTRA001 | QAAutomation_Drug1 | Rebateable    |
    Then User hits the "createNewDrugGroupDetails" with post request of CreateNewDrugGroup
    Then User verify create new drug group status code "200" for the response
    Then User verify the valid CreateNewDrugGroup Response body key "recordSaved" and expected value "true"
    Then User verify the valid CreateNewDrugGroup Response body key "recordUpdated" and expected value "false"
    Then User verify the valid Response body key "mfrDrugListId" and execute the query "QAAutomation_Drug1" matches the Drug groupID return from API and DB

  @Functional
  Scenario: validation results existing  drug group exists
    Then User executes the delete existing DrugGroup query "deleteDrugGroupByName" and List name "QAAutomation%" to delete the record from the database
    Given User create the Create new group details data
      | mfrId    | drugGroupName      | drugGroupType |
      | ASTRA001 | QAAutomation_Drug1 | Rebateable    |
    Then User hits the "createNewDrugGroupDetails" with post request of CreateNewDrugGroup
    Then User verify create new drug group status code "200" for the response
    Then User verify the valid CreateNewDrugGroup Response body key "recordSaved" and expected value "true"
    Then User verify the valid CreateNewDrugGroup Response body key "recordUpdated" and expected value "false"
    Then User verify the valid Response body key "mfrDrugListId" and execute the query "QAAutomation_Drug1" matches the Drug groupID return from API and DB
    Given User create the Create new group details data
      | mfrId    | drugGroupName      | drugGroupType |
      | ASTRA001 | QAAutomation_Drug1 | Rebateable    |
    Then User hits the "createNewDrugGroupDetails" with post request of CreateNewDrugGroup
    Then User verify the Create new group details response header Error Code value "501"
    Then User verify create new drug group status code "200" for the response
    Then User verify the valid CreateNewDrugGroup Response body key "recordSaved" and expected value "false"
    Then User verify the valid CreateNewDrugGroup Response body key "recordUpdated" and expected value "false"
    Then User verify the valid CreateNewDrugGroup Response body key "drugGroupExists" and expected value "true"

  @Functional
  Scenario: validation Blank input response of mfrId,drugGroupName and drugGroupType
    Then User executes the delete existing DrugGroup query "deleteDrugGroupByName" and List name "QAAutomation%" to delete the record from the database
    Given User create the Create new group details data
      | mfrId | drugGroupName | drugGroupType |
      |       |               |               |
    Then User hits the "createNewDrugGroupDetails" with post request of CreateNewDrugGroup
    Then User verify the Create new group details response header Error Code value "-1"
    Then User verify create new drug group status code "200" for the response
    Then User verify the valid Response CreateNewDrugGroup body key "messageDrugGroup" and expected value "Invalid input: mfrId,drugGroupName,drugGroupType" of string

  @Regression @Functional
  Scenario: validation Invalid manufactureId
    Then User executes the delete existing DrugGroup query "deleteDrugGroupByName" and List name "QAAutomation%" to delete the record from the database
    Given User create the Create new group details data
      | mfrId | drugGroupName      | drugGroupType |
      | Test  | QAAutomation_Drug2 | Rebateable    |
    Then User hits the "createNewDrugGroupDetails" with post request of CreateNewDrugGroup
    Then User verify the Create new group details response header Error Code value "-1"
    Then User verify create new drug group status code "200" for the response
    Then User verify the valid Response CreateNewDrugGroup body key "messageDrugGroup" and expected value "Invalid Data: DrugGroupName/ DrugGroupType/ ManufacturerId" of string

  @Regression @Functional
  Scenario: validation Invalid drugGroupName
    Then User executes the delete existing DrugGroup query "deleteDrugGroupByName" and List name "QAAutomation%" to delete the record from the database
    Given User create the Create new group details data
      | mfrId    | drugGroupName       | drugGroupType |
      | ASTRA001 | #QAAutomation_Drug2 | Rebateable    |
    Then User hits the "createNewDrugGroupDetails" with post request of CreateNewDrugGroup
    Then User verify the Create new group details response header Error Code value "-1"
    Then User verify create new drug group status code "200" for the response
    Then User verify the valid Response CreateNewDrugGroup body key "messageDrugGroup" and expected value "Invalid Data: DrugGroupName/ DrugGroupType/ ManufacturerId" of string

  @Regression @Functional
  Scenario: validation Invalid drugGroupType
    Then User executes the delete existing DrugGroup query "deleteDrugGroupByName" and List name "QAAutomation%" to delete the record from the database
    Given User create the Create new group details data
      | mfrId    | drugGroupName      | drugGroupType |
      | ASTRA001 | QAAutomation_Drug2 | Test          |
    Then User hits the "createNewDrugGroupDetails" with post request of CreateNewDrugGroup
    Then User verify the Create new group details response header Error Code value "-1"
    Then User verify create new drug group status code "200" for the response
    Then User verify the valid Response CreateNewDrugGroup body key "messageDrugGroup" and expected value "Invalid Data: DrugGroupName/ DrugGroupType/ ManufacturerId" of string
    Then User executes the delete existing DrugGroup query "deleteDrugGroupByName" and List name "QAAutomation%" to delete the record from the database

