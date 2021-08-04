# This feature file defines the automation scenarios to be developed for get discard contract end point
Feature: Discard a contract
  @Smoke
  Scenario: Discard the contract and verify correct status code is returned and correct message in response
    Given User executes the "getContractIDAndRowKey" query and retrieves rowKey and ContractID
    When User hits the "discardContractResource" Endpoint with delete API request
    Then User verifies the valid status code "200" in the Discard contract response
    And user runs "updateContractHeader" query and "updateContractDetail" query to activate the contract

  @Regression @Functional
  Scenario: Discard the contract and verify  response is in JSON
    Given User executes the "getContractIDAndRowKey" query and retrieves rowKey and ContractID
    When User hits the "discardContractResource" Endpoint with delete API request
    Then User verifies the response is in JSON format
    And user runs "updateContractHeader" query and "updateContractDetail" query to activate the contract

  @Regression @Functional
  Scenario: Discard the contract and Verify the boolean value  true for isManufacturerContractDiscarded in api response for a successful discard
    Given User executes the "getContractIDAndRowKey" query and retrieves rowKey and ContractID
    When User hits the "discardContractResource" Endpoint with delete API request
    Then User verifies the isManufacturerContractDiscarded as true value in response
    And user runs "updateContractHeader" query and "updateContractDetail" query to activate the contract

  @Regression @Functional
  Scenario: Validate discard contract is_Current_Flag value as zero in database
    Given User executes the "getContractIDAndRowKey" query and retrieves rowKey and ContractID
    When User hits the "discardContractResource" Endpoint with delete API request
    Then User verifies the isManufacturerContractDiscarded as true value in response
    Then User runs "verifyDiscardContractHeader" query and verify is_Current_Flag value as zero in database
    Then User runs "verifyDiscardContractDetails" query and verify is_Current_Flag value as zero in database
    And user runs "updateContractHeader" query and "updateContractDetail" query to activate the contract

  @Regression @Functional
  Scenario: Verify the Error message when discarding an already discarded contract
    Given User executes the "getContractIDAndRowKey" query and retrieves rowKey and ContractID
    When User hits the "discardContractResource" Endpoint with delete API request
    Then User verifies the isManufacturerContractDiscarded as true value in response
    Then User runs "verifyDiscardContractHeader" query and verify is_Current_Flag value as zero in database
    Then User runs "verifyDiscardContractDetails" query and verify is_Current_Flag value as zero in database
    When User hits the "discardContractResource" Endpoint with delete API request
    Then User verify the discard contract response header Error Code value "-1"
    Then User valid Response discarded contract body key "discardErrorMessage" of string
    And user runs "updateContractHeader" query and "updateContractDetail" query to activate the contract

