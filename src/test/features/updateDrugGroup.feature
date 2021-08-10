Feature:Retrieve and validate the update drug group details
  @Smoke
  Scenario: Validate update drug group details and verify correct status code with response body is returned
    Then User executes the delete existing DrugGroup query "deleteDrugGroupByName" and List name "QAAutomation%" to delete the record from the database
    Given User create the Create new group details data
      | mfrId    | drugGroupName      | drugGroupType |
      | ASTRA001 | QAAutomation_Drug1 | Rebateable    |
    Then User hits the "createNewDrugGroupDetails" with post request of CreateNewDrugGroup
    Then User verify create new drug group status code "200" for the response
    Then User verify the valid CreateNewDrugGroup Response body key "recordSaved" and expected value "true"
    Then User verify the valid CreateNewDrugGroup Response body key "recordUpdated" and expected value "false"
    Then User verify the valid Response body key "mfrDrugListId" and execute the query "QAAutomation_Drug1" matches the Drug groupID return from API and DB
    Given User update group details data
      | mfrId    | drugGroupName        | drugGroupType | oldDrugGroupName   |
      | ASTRA001 | QAAutomation_DrugNew | Rebateable    | QAAutomation_Drug1 |
    Then User hits the "updateDrugGroup" with put request of UpdateNewDrugGroup
    Then User verify update drug group status code "200" for the response
    Then User verify the valid UpdateNewDrugGroup Response body key "recordSaved" and expected value "false"
    Then User verify the valid UpdateNewDrugGroup Response body key "recordUpdated" and expected value "true"

  @Regression
  Scenario: validation results existing  drug group exists by update the drug group data
    Then User executes the delete existing DrugGroup query "deleteDrugGroupByName" and List name "QAAutomation%" to delete the record from the database
    Given User create the Create new group details data
      | mfrId    | drugGroupName      | drugGroupType |
      | ASTRA001 | QAAutomation_Drug1 | Rebateable    |
    Then User hits the "createNewDrugGroupDetails" with post request of CreateNewDrugGroup
    Then User verify create new drug group status code "200" for the response
    Then User verify the valid CreateNewDrugGroup Response body key "recordSaved" and expected value "true"
    Then User verify the valid CreateNewDrugGroup Response body key "recordUpdated" and expected value "false"
    Then User verify the valid Response body key "mfrDrugListId" and execute the query "QAAutomation_Drug1" matches the Drug groupID return from API and DB
    Given User update group details data
      | mfrId    | drugGroupName      | drugGroupType | oldDrugGroupName   |
      | ASTRA001 | QAAutomation_Drug1 | Rebateable    | QAAutomation_Drug1 |
    Then User hits the "updateDrugGroup" with put request of UpdateNewDrugGroup
    Then User verify the UpdateDrugGroup response header Error Code value "501"
    Then User verify update drug group status code "200" for the response
    Then User verify the valid UpdateNewDrugGroup Response body key "recordSaved" and expected value "false"
    Then User verify the valid UpdateNewDrugGroup Response body key "recordUpdated" and expected value "false"
    Then User verify the valid UpdateNewDrugGroup Response body key "drugGroupExists" and expected value "true"

  @Regression @Functional
  Scenario: validation Blank input response of mfrId,drugGroupName and drugGroupType
    Then User executes the delete existing DrugGroup query "deleteDrugGroupByName" and List name "QAAutomation%" to delete the record from the database
    Given User create the Create new group details data
      | mfrId    | drugGroupName      | drugGroupType |
      | ASTRA001 | QAAutomation_Drug1 | Rebateable    |
    Then User hits the "createNewDrugGroupDetails" with post request of CreateNewDrugGroup
    Then User verify create new drug group status code "200" for the response
    Then User verify the valid CreateNewDrugGroup Response body key "recordSaved" and expected value "true"
    Then User verify the valid CreateNewDrugGroup Response body key "recordUpdated" and expected value "false"
    Then User verify the valid Response body key "mfrDrugListId" and execute the query "QAAutomation_Drug1" matches the Drug groupID return from API and DB
    Given User update group details data
      | mfrId | drugGroupName | drugGroupType | oldDrugGroupName   |
      |       |               |               | QAAutomation_Drug1 |
    Then User hits the "updateDrugGroup" with put request of UpdateNewDrugGroup
    Then User verify the UpdateDrugGroup response header Error Code value "-1"
    Then User verify update drug group status code "200" for the response
    Then User verify the valid Response UpdateDrugGroup body key "messageDrugGroup" and expected value "Invalid input: mfrId,drugGroupName,drugGroupType" of string


  @Regression @Functional
  Scenario: validation Invalid input mfrId by update the drug group details
    Then User executes the delete existing DrugGroup query "deleteDrugGroupByName" and List name "QAAutomation%" to delete the record from the database
    Given User create the Create new group details data
      | mfrId    | drugGroupName      | drugGroupType |
      | ASTRA001 | QAAutomation_Drug1 | Rebateable    |
    Then User hits the "createNewDrugGroupDetails" with post request of CreateNewDrugGroup
    Then User verify create new drug group status code "200" for the response
    Then User verify the valid CreateNewDrugGroup Response body key "recordSaved" and expected value "true"
    Then User verify the valid CreateNewDrugGroup Response body key "recordUpdated" and expected value "false"
    Then User verify the valid Response body key "mfrDrugListId" and execute the query "QAAutomation_Drug1" matches the Drug groupID return from API and DB
    Given User update group details data
      | mfrId | drugGroupName        | drugGroupType | oldDrugGroupName   |
      | Test  | QAAutomation_DrugNew | Rebateable    | QAAutomation_Drug1 |
    Then User hits the "updateDrugGroup" with put request of UpdateNewDrugGroup
    Then User verify the UpdateDrugGroup response header Error Code value "-1"
    Then User verify update drug group status code "200" for the response
    Then User verify the valid Response UpdateDrugGroup body key "messageDrugGroup" and expected value "Invalid Data: DrugGroupType/ ManufacturerId" of string


  Scenario: validation Invalid input drugGroupName by update drug group(BUG)
    Then User executes the delete existing DrugGroup query "deleteDrugGroupByName" and List name "QAAutomation%" to delete the record from the database
    Given User create the Create new group details data
      | mfrId    | drugGroupName      | drugGroupType |
      | ASTRA001 | QAAutomation_Drug1 | Rebateable    |
    Then User hits the "createNewDrugGroupDetails" with post request of CreateNewDrugGroup
    Then User verify create new drug group status code "200" for the response
    Then User verify the valid CreateNewDrugGroup Response body key "recordSaved" and expected value "true"
    Then User verify the valid CreateNewDrugGroup Response body key "recordUpdated" and expected value "false"
    Then User verify the valid Response body key "mfrDrugListId" and execute the query "QAAutomation_Drug1" matches the Drug groupID return from API and DB
    Given User update group details data
      | mfrId    | drugGroupName         | drugGroupType | oldDrugGroupName   |
      | ASTRA001 | #QAAutomation_DrugNew | Rebateable    | QAAutomation_Drug1 |
    Then User hits the "updateDrugGroup" with put request of UpdateNewDrugGroup
    Then User verify update drug group status code "200" for the response

  @Regression @Functional
  Scenario: validation Invalid input drugGroupType by update the drug group details
    Then User executes the delete existing DrugGroup query "deleteDrugGroupByName" and List name "QAAutomation%" to delete the record from the database
    Given User create the Create new group details data
      | mfrId    | drugGroupName      | drugGroupType |
      | ASTRA001 | QAAutomation_Drug1 | Rebateable    |
    Then User hits the "createNewDrugGroupDetails" with post request of CreateNewDrugGroup
    Then User verify create new drug group status code "200" for the response
    Then User verify the valid CreateNewDrugGroup Response body key "recordSaved" and expected value "true"
    Then User verify the valid CreateNewDrugGroup Response body key "recordUpdated" and expected value "false"
    Then User verify the valid Response body key "mfrDrugListId" and execute the query "QAAutomation_Drug1" matches the Drug groupID return from API and DB
    Given User update group details data
      | mfrId | drugGroupName        | drugGroupType | oldDrugGroupName   |
      | Test  | QAAutomation_DrugNew | Test          | QAAutomation_Drug1 |
    Then User hits the "updateDrugGroup" with put request of UpdateNewDrugGroup
    Then User verify the UpdateDrugGroup response header Error Code value "-1"
    Then User verify update drug group status code "200" for the response
    Then User verify the valid Response UpdateDrugGroup body key "messageDrugGroup" and expected value "Invalid Data: DrugGroupType/ ManufacturerId" of string