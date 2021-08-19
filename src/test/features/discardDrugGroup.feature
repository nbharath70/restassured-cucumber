# This feature file defines the automation scenarios to be developed for get discard drug group end point
Feature: Discard a drug group
  @Smoke
   Scenario: Discard a drug group in New status and verify correct status code is returned
    Given User executes the "getNewDrugGroupRowKey" query and retrieves rowkey
    When User hits the "discardDrugGroup" Endpoint with delete request
    Then User verifies the valid status code "200" in the Discard drug group response
    And user runs "restoreTheDrugGroupStatus" query to activate the drug list

  @Smoke
   Scenario: Discard the Drug group  and verify  response is in JSON format
    Given User executes the "getNewDrugGroupRowKey" query and retrieves rowkey
    When User hits the "discardDrugGroup" Endpoint with delete request
    Then User verifies the discard Drug group response is in JSON format
    And user runs "restoreTheDrugGroupStatus" query to activate the drug list

  @Smoke @Functional
   Scenario: Discard the Drug group  and verify  response message value
    Given User executes the "getNewDrugGroupRowKey" query and retrieves rowkey
    When User hits the "discardDrugGroup" Endpoint with delete request
    Then User verifies the discard Drug group response message json value
    And user runs "restoreTheDrugGroupStatus" query to activate the drug list

  @Regression @Functional
   Scenario: Validate discarded drug group's is_Current_Flag value is zero in database
    Given User executes the "getNewDrugGroupRowKey" query and retrieves rowkey
    When User hits the "discardDrugGroup" Endpoint with delete request
    Then User verifies the valid status code "200" in the Discard drug group response
    And User verifies the discard Drug group response is in JSON format
    And User verifies the discard Drug group response message json value
    And user verifies the "DiscardedDrugGroupis_current_flag" as zero in database for discarded drug group
    And user runs "restoreTheDrugGroupStatus" query to activate the drug list

  @Regression @Functional
   Scenario: Discard drug group with Life cycle status  InProgress
    Given User executes the "getInProgressDrugGroupRowKey" query and retrieves rowkey
    When User hits the "discardDrugGroup" Endpoint with delete request
    Then User verifies the valid status code "200" in the Discard drug group response
    Then User verifies the discard Drug group response is in JSON format
    And User verifies the discard Drug group response message json value
    And user verifies the "DiscardedDrugGroupis_current_flag" as zero in database for discarded drug group
    And user runs "restoreTheDrugGroupStatus" query to activate the drug list

  @Regression @Functional
   Scenario: Discard drug group with Life cycle status  Approved and verify the error message
    Given User executes the "getApprovedDrugGroupRowKey" query and retrieves rowkey
    When User hits the "discardDrugGroup" Endpoint with delete request
    Then User verifies the valid status code "200" in the Discard drug group response
    Then User verifies the discard Drug group response is in JSON format
    And User verifies the discarded Drug group response "ResponseErrorMessage" json value

  @Regression @Functional
   Scenario: Verify the Error message when discarding an already discarded drug group
    Given User executes the "getDiscardedDrugGroupRowKey" query and retrieves rowkey
    When User hits the "discardDrugGroup" Endpoint with delete request
    Then User verifies the valid status code "200" in the Discard drug group response
    Then User verifies the discard Drug group response is in JSON format
    And User verifies the discarded Drug group  "DiscardedErrorMessage" in json response

  @Regression @Functional
   Scenario: Discard drug group with Life cycle status  InReview and verify the error message
    Given User executes the "getInReviewDrugGroupRowKey" query and retrieves rowkey
    When User hits the "discardDrugGroup" Endpoint with delete request
    Then User verifies the valid status code "200" in the Discard drug group response
    Then User verifies the discard Drug group response is in JSON format
    And User verifies the discarded Drug group response for "InReviewDrugGroupErrorMessage" json value