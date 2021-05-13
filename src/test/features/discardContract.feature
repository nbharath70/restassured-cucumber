# This feature file defines the automation scenarios to be developed for get discard contract end point
Feature: Discard a contract
  Scenario: Discard the contract and verify correct status code is returned and correct message in response
    Given User executes the "getContractIDAndRowKey" query and retrieves rowKey and ContractID
    When User hits the "discardContractResource" Endpoint with delete API request
    Then User verifies the valid status code "200" in the Discard contract response
    And user runs "updateContractHeader" query and "updateContractDetail" query to activate the contract

  Scenario: Discard the contract and verify  response is in JSON
    Given User executes the "getContractIDAndRowKey" query and retrieves rowKey and ContractID
    When User hits the "discardContractResource" Endpoint with delete API request
    Then User verifies the response is in JSON format
    And user runs "updateContractHeader" query and "updateContractDetail" query to activate the contract
  Scenario: Discard the contract and Verify the boolean value  true for isManufacturerContractDiscarded in api response for a successful discard
    Given User executes the "getContractIDAndRowKey" query and retrieves rowKey and ContractID
    When User hits the "discardContractResource" Endpoint with delete API request
    Then User verifies the isManufacturerContractDiscarded as true value in response
    And user runs "updateContractHeader" query and "updateContractDetail" query to activate the contract
