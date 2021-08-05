#This feature file defines the automation scenarios to be developed for Finding Drug of the given Manufacturer
Feature:Validate the Find Drug group API test cases
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

  @Regression @Functional
  Scenario Outline: Hit the findDrugGroup Api and validate drugGroupTypeDesc and drugListRuleId with DB
    Given User Runs the Query "getManufactuereName" and Fetch the Manufactuere Name from DB
    When User hits the FindDrugGroup "findDrugGroup" Endpoint with get request
    Then User Runs the Query "getDrugGroupSummaryFromDB" and matches the DrugListRuleId and DruggroupDescription "<columnName>" and JSON from response
    Examples:
      | columnName     |
      | drugListRuleId |
      |manufacturerDrugListIDFinddrugGroupApi|

#  @Regression @Functional
#  Scenario: Hit the findDrugGroup Api and validate ManufacturerDrugListID with  DBDetailJSon
#    Given User Runs the Query "getManufactuereName" and Fetch the Manufactuere Name from DB
#    And User execute the getManufacturerDruglistNAme Query "getManufacturerDrugList" and get the DetialJSOn from DB
#    When User hits the FindDrugGroup "findDrugGroup" Endpoint with get request
#    Then Verifies the JSON response with DB JSOn by manufacturerDrugListIDJsonPath "manufacturerDrugListIDFinddrugGroupApi"

  @Regression @Functional
  Scenario: Hit the findDrugGroup Api and validate Number of Approved NDC with  DBDetailJSon
    Given User Runs the Query "getManufactuereName" and Fetch the Manufactuere Name from DB
    And User execute the getNumberofApprovedNDC Query "getNumberofApprovedNDC" and get the DetialJSOn from DB
    When User hits the FindDrugGroup "findDrugGroup" Endpoint with get request
    Then Verifies the JSON response with DB JSOn by numberOfApprovedNDCJsonPath "numberOfApprovedNDCFinddrugGroupApi"

  @Regression @Functional
  Scenario: Hit the findDrugGroup Api and validate Number of Pending NDC with  DBDetailJSon
    Given User Runs the Query "getManufactuereName" and Fetch the Manufactuere Name from DB
    And User execute the getNumberofPendingNDC Query "getNumberofPendingNDC" and get the DetialJSOn from DB
    When User hits the FindDrugGroup "findDrugGroup" Endpoint with get request
    Then Verifies the JSON response with DB JSOn by numberOfPendingNDCJsonPath "numberOfPendingNDCFinddrugGroupApi"

  @Regression @Functional
  Scenario: Validate error code for invalid manufactureName
    When User hits the FindDrugGroup "findDrugGroup" Endpoint with get request for given ManufactuereName "Test"
    Then User verifies the API response Status code is "200" for FindDrugGroup API
    Then User verify the FindDrugGroup response header Error Code value "-1"
    Then User verify the valid Response FindDrugGroup body key "messageFindDrugGroup" and expected value "No Drug Group Summary found for the Manufacturer Name, contact support team." of string

  @Regression @Functional
  Scenario: Verify the DrugGroup name is in Ascending order
    Given User Runs the Query "getManufactuereName" and Fetch the Manufactuere Name from DB
    When User hits the FindDrugGroup "findDrugGroup" Endpoint with get request
    Then User Runs the Query "getDrugGroupSummaryFromDB" and matches the DrugGroupSummary Column "drugGroupName" and JSON from response

  @Regression @Functional
  Scenario: Validate for invalid manufactureName
    When User hits the FindDrugGroup "findDrugGroup" Endpoint with get request for given ManufactuereName "htarahb"
    Then User verifies the API response Status code is "200" for FindDrugGroup API
    Then User verify the valid Response FindDrugGroup body key "messageFindDrugGroup" and expected value "No Drug Group Summary found for the Manufacturer Name, contact support team." of string

  @Regression @Functional
  Scenario: Verify with TypeMismatch of Manufacturer name
    When User hits the FindDrugGroup "findDrugGroup" Endpoint with get request for given ManufactuereName "$3!23"
    Then User verifies the API response Status code is "200" for FindDrugGroup API
    Then User verify the valid Response FindDrugGroup body key "messageFindDrugGroup" and expected value "Invalid mfr name $3!23 used to search drug groups." of string

#  @Regression @Functional
#  Scenario : Verify that it should fetch DrugList for all the Life_Cycle_Status
#    Given User Runs the Query "getManufactuereName" and Fetch the Manufactuere Name from DB
#    When User hits the FindDrugGroup "findDrugGroup" Endpoint with get request
#    Then User Runs the Query "getDrugGroupSummaryFromDB" and matches the DrugGroupSummary Column "lifeCycleStatus" and JSON from response

