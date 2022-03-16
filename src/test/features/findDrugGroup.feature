#This feature file defines the automation scenarios to be developed for Finding Drug of the given Manufacturer
Feature:Validate the Find Drug group API test cases

  @Smoke
  Scenario: Hit findDrugGroup Api and validate Status Code
    Given User runs the query "getManufactuereName" and fetch the manufactuereName from DB
    When User hits the findDrugGroup "findDrugGroup" endpoint
    Then User verifies the API response statusCode is "200" for findDrugGroup API

  @Smoke
  Scenario: Hit findDrugGroup Api and Format of response
    Given User runs the query "getManufactuereName" and fetch the manufactuereName from DB
    When User hits the findDrugGroup "findDrugGroup" endpoint
    Then User verifies the findDrugGroup API response is in JSON format

  @Functional
  Scenario: Hit the findDrugGroup Api and validate ResponseDetailsWith DB
    Given User runs the query "getManufactuereName" and fetch the manufactuereName from DB
    When User hits the findDrugGroup "findDrugGroup" endpoint
    Then User verifies json response with DB using query "DBJsonResponse" and columnName "resultColumnName"

  @Regression @Functional
  Scenario: Validate error code for invalid manufactureName
    When User hits the "findDrugGroup" endpoint with manufactuereName "inValidmanuFacturerName"
    Then User verifies the API response statusCode is "200" for findDrugGroup API
    Then User verifies the findDrugGroup response header errorCode value ""
    Then User verifies the valid response using findDrugGroup bodyKey "messageFindDrugGroupJson" and expected value "invalidDrugMessage"

  @Regression @Functional
  Scenario: Verify the DrugGroup name is in Ascending order
    Given User runs the query "getManufactuereName" and fetch the manufactuereName from DB
    When User hits the findDrugGroup "findDrugGroup" endpoint
    Then User Runs the query "getDrugGroupSummaryFromDB" and matches the DrugGroupSummary Column "drugGroupName" and JSON from response

  @Regression @Functional
  Scenario: Validate for invalid manufactureName
    When User hits the "findDrugGroup" endpoint with manufactuereName "inValidmanuFacturerName"
    Then User verifies the API response statusCode is "200" for findDrugGroup API
    Then User verifies the valid response using findDrugGroup bodyKey "messageFindDrugGroupJson" and expected value "invalidDrugMessage"

  @Regression @Functional
  Scenario: Verify with TypeMismatch of Manufacturer name
    When User hits the "findDrugGroup" endpoint with manufactuereName "typeMissMatchManufacturerName"
    Then User verifies the API response statusCode is "200" for findDrugGroup API
    Then User verifies the valid response using findDrugGroup bodyKey "messageFindDrugGroupJson" and expected value "invalidDrugMessage"

