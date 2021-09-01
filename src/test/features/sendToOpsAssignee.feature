#In This Feature file All the Scenarios for APIto send contract to Ops Assignee exists
Feature: sendToOpsAssignee.feature - Delete Drug group with Drug detail RowKeys


  @Smoke @Regression
  Scenario:Send a Valid contract to Ops Assignee
    Given User creates a new contract and updates it and sends to Rebate Ops
    And User gets the task Id with Process Instance Id by executing the "sqlToGetLatestTaskIdWithProcInstId"
    Then User updates "Bharath Narasimha" as Ops assignee and "Rabbani Shaik" as Ops QCer
    And User Hits "sendContractToOpsAssignee" with Post request
    Then User prepares expected result json after sending to OpsAssignee
    And User verifies the actual response with expected response
    And User verifies the lifecycle status of contract changed to "InProgress"
    Then User deletes the flowable records for the Process Instance Id
    And User deletes the QAAutomation Contract by executing "sqlToDeleteQAAutomationContractWithContractId"

  @Smoke @Regression
  Scenario:Send a Valid contract to Ops Assignee and check the status code
    Given User creates a new contract and updates it and sends to Rebate Ops
    And User gets the task Id with Process Instance Id by executing the "sqlToGetLatestTaskIdWithProcInstId"
    Then User updates "Bharath Narasimha" as Ops assignee and "Rabbani Shaik" as Ops QCer
    And User Hits "sendContractToOpsAssignee" with Post request
    Then User checks the status code "200" from the response
    Then User deletes the flowable records for the Process Instance Id
    And User deletes the QAAutomation Contract by executing "sqlToDeleteQAAutomationContractWithContractId"

  Scenario:Send a Valid contract to Ops Assignee with Invalid task Id
    Given User creates a new contract and updates it and sends to Rebate Ops
    And User gets the task Id with Process Instance Id by executing the "sqlToGetLatestTaskIdWithProcInstId"
    Then User updates "Bharath Narasimha" as Ops assignee "Rabbani Shaik" as Ops QCer and Invalid taskId
    And User Hits "sendContractToOpsAssignee" with Post request
    Then User prepares expected result json after sending to OpsAssignee for invalid taskId
    And User verifies the actual response with expected response
    Then User deletes the flowable records for the Process Instance Id
    And User deletes the QAAutomation Contract by executing "sqlToDeleteQAAutomationContractWithContractId"

  Scenario:Send a Valid contract to Ops Assignee with invalid ops assignee name
    Given User creates a new contract and updates it and sends to Rebate Ops
    And User gets the task Id with Process Instance Id by executing the "sqlToGetLatestTaskIdWithProcInstId"
    Then User updates "ABC" as Ops assignee and "Rabbani Shaik" as Ops QCer
    And User Hits "sendContractToOpsAssignee" with Post request
    Then User prepares expected result json after sending to OpsAssignee
    And User verifies the actual response with expected response
    Then User deletes the flowable records for the Process Instance Id
    And User deletes the QAAutomation Contract by executing "sqlToDeleteQAAutomationContractWithContractId"

