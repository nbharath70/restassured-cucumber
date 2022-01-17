Feature:Retrieve and validate the update drug group details

  Background:
    Given User creates a new drug group to update the record and test
      | mfrId    | drugGroupName      | drugGroupType | condition |
      | ASTRA001 | QAAutomation_Drug1 | Rebateable    | Valid     |
    Then User hits the "createNewDrugGroupDetails" API fetches the RowKey of created drugGroup Using query "getRowKeyForCreateDrugGroup"

  @Smoke
  Scenario: Validate update drug group details and verify correct status code with response body is returned
    And User update group details data
      | mfrId    | drugGroupName        | drugGroupType | oldDrugGroupName   |
      | ASTRA001 | QAAutomation_DrugNew | Rebateable    | QAAutomation_Drug1 |
    Then User hits the "updateDrugGroup" with put request of UpdateNewDrugGroup
    Then User verify update drug group status code "200" for the response
    Then User verify the valid UpdateNewDrugGroup Response body key "recordSaved" and expected value "false"
    Then User verify the valid UpdateNewDrugGroup Response body key "recordUpdated" and expected value "true"
    Then User discards the drugGroup and deletes the created record from DB

  @Regression
  Scenario: validation results existing  drug group exists by update the drug group data
    And User update group details data
      | mfrId    | drugGroupName      | drugGroupType | oldDrugGroupName   |
      | ASTRA001 | QAAutomation_Drug1 | Rebateable    | QAAutomation_Drug1 |
    Then User hits the "updateDrugGroup" with put request of UpdateNewDrugGroup
    Then User verify the UpdateDrugGroup response header Error Code value "501"
    Then User verify update drug group status code "200" for the response
    Then User verify the valid UpdateNewDrugGroup Response body key "recordSaved" and expected value "false"
    Then User verify the valid UpdateNewDrugGroup Response body key "recordUpdated" and expected value "false"
    Then User verify the valid UpdateNewDrugGroup Response body key "drugGroupExists" and expected value "true"
    Then User discards the drugGroup and deletes the created record from DB

  @Regression @Functional
  Scenario: validation Blank input response of mfrId,drugGroupName and drugGroupType
    And User update group details data
      | mfrId | drugGroupName | drugGroupType | oldDrugGroupName   |
      |       |               |               | QAAutomation_Drug1 |
    Then User hits the "updateDrugGroup" with put request of UpdateNewDrugGroup
    Then User verify the UpdateDrugGroup response header Error Code value "-1"
    Then User verify update drug group status code "200" for the response
    Then User verify the valid Response UpdateDrugGroup body key "messageDrugGroup" and expected value "blankInputErrorMessage" of string
    Then User discards the drugGroup and deletes the created record from DB


  @Regression @Functional
  Scenario: validation Invalid input mfrId by update the drug group details
    And User update group details data
      | mfrId | drugGroupName        | drugGroupType | oldDrugGroupName   |
      | Test  | QAAutomation_DrugNew | Rebateable    | QAAutomation_Drug1 |
    Then User hits the "updateDrugGroup" with put request of UpdateNewDrugGroup
    Then User verify the UpdateDrugGroup response header Error Code value "-1"
    Then User verify update drug group status code "200" for the response
    Then User verify the valid Response UpdateDrugGroup body key "messageDrugGroup" and expected value "invalidInputErrorMessage" of string
    Then User discards the drugGroup and deletes the created record from DB


  Scenario: validation Invalid input drugGroupName by update drug group(BUG)
    And User update group details data
      | mfrId    | drugGroupName         | drugGroupType | oldDrugGroupName   |
      | ASTRA001 | #QAAutomation_DrugNew | Rebateable    | QAAutomation_Drug1 |
    Then User hits the "updateDrugGroup" with put request of UpdateNewDrugGroup
    Then User verify update drug group status code "200" for the response
    Then User discards the drugGroup and deletes the created record from DB

  @Regression @Functional
  Scenario: validation Invalid input drugGroupType by update the drug group details
    And User update group details data
      | mfrId | drugGroupName        | drugGroupType | oldDrugGroupName   |
      | Test  | QAAutomation_DrugNew | Test          | QAAutomation_Drug1 |
    Then User hits the "updateDrugGroup" with put request of UpdateNewDrugGroup
    Then User verify the UpdateDrugGroup response header Error Code value "-1"
    Then User verify update drug group status code "200" for the response
    Then User verify the valid Response UpdateDrugGroup body key "messageDrugGroup" and expected value "invalidInputErrorMessage" of string
    Then User discards the drugGroup and deletes the created record from DB