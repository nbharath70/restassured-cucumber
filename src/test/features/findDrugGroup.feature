#This feature file defines the automation scenarios to be developed for Finding Drug of the given Manufacturer
Feature:Validate the Find Drug group API test cases

  @Smoke
  Scenario: Hit findDrugGroup Api and validate Status Code
    Given User Runs the Query "getManufactuereName" and Fetch the Manufactuere Name from DB
    When User hits the FindDrugGroup "findDrugGroup" Endpoint with get request
    Then User verifies the API response Status code is "200" for FindDrugGroup API

  @Smoke
  Scenario: Hit findDrugGroup Api and Format of response
    Given User Runs the Query "getManufactuereName" and Fetch the Manufactuere Name from DB
    When User hits the FindDrugGroup "findDrugGroup" Endpoint with get request
    Then User verifies the FindDrugGroup API Response is in JSON Format

  @Functional
  Scenario: Hit the findDrugGroup Api and validate ResponseDetailsWith DB
    Given User Runs the Query "getManufactuereName" and Fetch the Manufactuere Name from DB
    When User hits the FindDrugGroup "findDrugGroup" Endpoint with get request
    Then User verifies Json Response with DB using Query "DBjsonResponse" and ColumnName "resultColumnName"

  @Regression @Functional
  Scenario: Validate error code for invalid manufactureName
    When User hits the FindDrugGroup "findDrugGroup" Endpoint with get request for given ManufactuereName "Test"
    Then User verifies the API response Status code is "200" for FindDrugGroup API
    Then User verify the FindDrugGroup response header Error Code value "-1"
    Then User verify the valid Response FindDrugGroup body key "messageFindDrugGroupJson" and expected value "invalidDrugMessage" of string

  @Regression @Functional
  Scenario: Verify the DrugGroup name is in Ascending order
    Given User Runs the Query "getManufactuereName" and Fetch the Manufactuere Name from DB
    When User hits the FindDrugGroup "findDrugGroup" Endpoint with get request
    Then User Runs the Query "getDrugGroupSummaryFromDB" and matches the DrugGroupSummary Column "drugGroupName" and JSON from response

  @Regression @Functional
  Scenario: Validate for invalid manufactureName
    When User hits the FindDrugGroup "findDrugGroup" Endpoint with get request for given ManufactuereName "htarahb"
    Then User verifies the API response Status code is "200" for FindDrugGroup API
    Then User verify the valid Response FindDrugGroup body key "messageFindDrugGroupJson" and expected value "invalidDrugMessage" of string

  @Regression @Functional
  Scenario: Verify with TypeMismatch of Manufacturer name
    When User hits the FindDrugGroup "findDrugGroup" Endpoint with get request for given ManufactuereName "$3!23"
    Then User verifies the API response Status code is "200" for FindDrugGroup API
    Then User verify the valid Response FindDrugGroup body key "messageFindDrugGroupJson" and expected value "invalidMessageForTypeMissMatchDrugGroup" of string


