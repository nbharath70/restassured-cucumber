#In This Feature file All the Scenarios for APItoChangeProgramStatusFromInProgressToCompleted exists
Feature: CompleteProgramAPI.feature - change the Program status from InProgress to completed

    @Negative
  Scenario: validate the error msg when a Program which doesn't contain a Rebate Option is tried to change to completed status
    Given User runs the Query "sqlToPickaProgramWhichdoesnotHaveaRO" and picks Program "rowKey"
    And User Hits "changeProgramFromInProgresstoCompletedResource" with Post API request and try to change the Program status
    And User verifies the valid error msg "errorMsgforProgramWhichDoesNotHaveaRO" from response

    @Smoke @Positive
  Scenario: validate the success status when a Valid Program is tried to change to completed status
    Given User runs the Query "sqlToPickaValidProgramHavingAtleastOneCompletedRO" and picks Program "rowKey"
    And User Hits "changeProgramFromInProgresstoCompletedResource" with Post API request and try to change the Program status
    And User verifies the valid status code "200" in API response
    And User redoes the changes made by executing "sqlToUpdateProgramStatusFromCompletedToInProgress"

    @Smoke @Positive
  Scenario: validate the response when a Valid Program is changed to completed status
    Given User runs the Query "sqlToPickaValidProgramHavingAtleastOneCompletedRO" and picks Program "rowKey"
    And User Hits "changeProgramFromInProgresstoCompletedResource" with Post API request and try to change the Program status
    And User verifies the success msg "completeValidProgramJsonPath" from API response for recordupdated "true"
    And User redoes the changes made by executing "sqlToUpdateProgramStatusFromCompletedToInProgress"

    @Negative
  Scenario: validate the error msg when a Program having an InProgress Rebate Option is tried to change to completed status
    Given User runs the Query "sqlToPickaProgramHavinganInProgressRO" and picks Program "rowKey"
    And User Hits "changeProgramFromInProgresstoCompletedResource" with Post API request and try to change the Program status
    And User verifies the valid error msg "errorMsgforProgramHavingAnInProgressRO" from response

    @Negative
  Scenario: validate the error msg when a Program having both InProgress & completed Rebate Option is tried to change to completed status
    Given User runs the Query "sqlToPickaProgramHavingBothInProgressandCompletedRO" and picks Program "rowKey"
    And User Hits "changeProgramFromInProgresstoCompletedResource" with Post API request and try to change the Program status
    And User verifies the valid error msg "errorMsgforProgramHavingAnInProgressRO" from response