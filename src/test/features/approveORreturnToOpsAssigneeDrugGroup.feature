Feature:Hit Approvre or Reject Drug group API and verify all the Scenarios

  Background:
    Given User creates the DrugGroup Using this data for Approving or Rejecting DrugGroup
      | mfrId   | drugGroupName                   | drugGroupType |
      | ABOT001 | QaTestAutomationDataForWorkFlow | Rebateable    |
    And User hits the API "createNewDrugGroupDetails" and create the DrugGroup for Approving or Rejecting DrugGroup and get the DrugGroupRowkey "getRowKeyForCreateDrugGroup"
    And User create data to Add Some Drugs to created DruGroup to Approve or Reject that DrugGroup
      | ndcs | startDate | endDate |
      |      |           |         |
    And User hits the API "saveDrugGroupDetails"to add drugs to DrugGroup to Approve or Reject that DrugGroup
    And User creates the Data to send the DrugGroup to QC to Approve or Reject that DrugGroup
      | opsAssignee       | opsQc         |
      | Bharath Narasimha | Rabbani Shaik |
    And User hits the SendToQC for DrugGroup API "sendToQCDrugGroup" to Approve or Reject that DrugGroup
    And User fetches the Task ID of the DruGroup sent to QC

  @Smoke
  Scenario: User hit the Approve drug group API and verify the the Status Code
    Given User creates the data for Approve or Reject API
      | ApproveorrejectFlag |
      | true                |
    When User hits the ApproveOrReject DrugGroup API "approveOrRejectDrugGroup"
    And User verifies that the Status code of approveOrrejectDrugGroupAPi is "200"
    Then User deletes the records created to verify drugGroup API

  @Smoke
  Scenario: User hit the Reject drug group API and verify the the Status Code
    Given User creates the data for Approve or Reject API
      | ApproveorrejectFlag |
      | false               |
    When User hits the ApproveOrReject DrugGroup API "approveOrRejectDrugGroup"
    And User verifies that the Status code of approveOrrejectDrugGroupAPi is "200"
    And User Discards the DrugGroup created
    Then User deletes the records created to verify drugGroup API

  @Functional @Regression
  Scenario: User hit the Approve drug group API and verify the Life cycle Status is Changed to Approved
    Given User creates the data for Approve or Reject API
      | ApproveorrejectFlag |
      | true                |
    When User hits the ApproveOrReject DrugGroup API "approveOrRejectDrugGroup"
    And User verifies the Life Cycle Status of the Drug Group is "Approved"
    Then User deletes the records created to verify drugGroup API

  @Functional @Regression
  Scenario: User hit the Return to opsAssignee drug group API and verify the life Cycyle Status is InProgress
    Given User creates the data for Approve or Reject API
      | ApproveorrejectFlag |
      | false               |
    When User hits the ApproveOrReject DrugGroup API "approveOrRejectDrugGroup"
    And User verifies the Life Cycle Status of the Drug Group is "InProgress"
    And User Discards the DrugGroup created
    Then User deletes the records created to verify drugGroup API
