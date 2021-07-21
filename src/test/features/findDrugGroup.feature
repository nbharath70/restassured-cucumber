#This feature file defines the automation scenarios to be developed for Finding Drug of the given Manufacturer
Feature:Retrieve and validate FindDrugGroup API

  Scenario: Hit findDrugGroup Api and validate Status Code
    Given User Runs the Query "getManufactuereName" and Fetch the Manufactuere Name from DB
    When User hits the FindDrugGroup "findDrugGroup" Endpoint with get request
    Then User verifies the API response Status code is "200" for FindDrugGroup API


  Scenario: Hit findDrugGroup Api and Format of response
    Given User Runs the Query "getManufactuereName" and Fetch the Manufactuere Name from DB
    When User hits the FindDrugGroup "findDrugGroup" Endpoint with get request
    Then User verifies the FindDrugGroup API Response is in JSON Format

  Scenario Outline: Hit the findDrugGroup Api and validate ResponseDetailsWith DB
    Given User Runs the Query "getManufactuereName" and Fetch the Manufactuere Name from DB
    When User hits the FindDrugGroup "findDrugGroup" Endpoint with get request
    Then User Runs the Query "getDrugGroupSummaryFromDB" and matches the DrugGroupSummary Column "<columnName>" and JSON from response
    Examples:
      | columnName                  |
      | lifeCycleStatus             |
      | manufacturerNameofDrugGroup |
      | drugGroupName               |
      | drugGroupType               |
      | mode                        |

  Scenario Outline: Hit the findDrugGroup Api and validate drugGroupTypeDesc and drugListRuleId with DB
    Given User Runs the Query "getManufactuereName" and Fetch the Manufactuere Name from DB
    When User hits the FindDrugGroup "findDrugGroup" Endpoint with get request
    Then User Runs the Query "getDrugGroupSummaryFromDB" and matches the DrugListRuleId and DruggroupDescription "<columnName>" and JSON from response
    Examples:
      | columnName     |
      | drugListRuleId |

  Scenario: Hit the findDrugGroup Api and validate ManufacturerDrugListID with  DBDetailJSon
    Given User Runs the Query "getManufactuereName" and Fetch the Manufactuere Name from DB
    And User execute the getManufacturerDruglistNAme Query "getManufacturerDrugList" and get the DetialJSOn from DB
    When User hits the FindDrugGroup "findDrugGroup" Endpoint with get request
    Then Verifies the JSON response with DB JSOn by manufacturerDrugListIDJsonPath "manufacturerDrugListIDFinddrugGroupApi"


  Scenario: Hit the findDrugGroup Api and validate Number of Approved NDC with  DBDetailJSon
    Given User Runs the Query "getManufactuereName" and Fetch the Manufactuere Name from DB
    And User execute the getNumberofApprovedNDC Query "getNumberofApprovedNDC" and get the DetialJSOn from DB
    When User hits the FindDrugGroup "findDrugGroup" Endpoint with get request
    Then Verifies the JSON response with DB JSOn by numberOfApprovedNDCJsonPath "numberOfApprovedNDCFinddrugGroupApi"

  Scenario: Hit the findDrugGroup Api and validate Number of Pending NDC with  DBDetailJSon
    Given User Runs the Query "getManufactuereName" and Fetch the Manufactuere Name from DB
    And User execute the getNumberofPendingNDC Query "getNumberofPendingNDC" and get the DetialJSOn from DB
    When User hits the FindDrugGroup "findDrugGroup" Endpoint with get request
    Then Verifies the JSON response with DB JSOn by numberOfPendingNDCJsonPath "numberOfPendingNDCFinddrugGroupApi"

  Scenario: Validate error code for invalid manufactureName
    When User hits the FindDrugGroup "findDrugGroup" Endpoint with get request for given ManufactuereName "Test"
    Then User verifies the API response Status code is "200" for FindDrugGroup API
    Then User verify the FindDrugGroup response header Error Code value "-1"
    Then User verify the valid Response FindDrugGroup body key "messageFindDrugGroup" and expected value "404 NOT_FOUND" of string






