##This feature file defines the automation scenarios to be developed for getContractByID end point
Feature:Retrieve and validate the Contract by ID
  @Smoke
  Scenario: get All valid ContractDetailsbyID and verify correct status code is returned
    Given User Runs the Query "getMFRRowKey" and Get the Rowkey from DB
    And User hits the "getContractDetailsByID" Endpoint with get API request
    Then User Verifies the API response Status code is "200"

  @Smoke
  Scenario: verifies the response is in JSON format
     Given User Runs the Query "getMFRRowKey" and Get the Rowkey from DB
    And User hits the "getContractDetailsByID" Endpoint with get API request
    Then verify the Response in JSON format

  @Regression @Functional
  Scenario: Hit the API and Validate all the Details with DB
    Given User Runs the Query "getMFRRowKey" and Get the Rowkey from DB
    And User Runs the Fetching ContractDetailsID Query "getContractID" and Get the ContractDetailsId from DB
    And User Runs the Fetching ManufactuereID Query "getManufacturerID" and Get the ManufactuereID from DB
    And user Runs the Fetching ManufacturerDetails Query "getManufacturerDetails" and get All Manufacturer details from DB
    And User hits the "getContractDetailsByID" Endpoint with get API request
    Then User runs the Query "getContractDetailJson" and Validate the response with DB column "resultColumnName"
#    Then User Validates the task message in db with Response using the Query ""


