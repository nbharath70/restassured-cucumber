# This feature file defines the automation scenarios to be developed for get All valid MFR end point
Feature:Retrieve and validate the list of valid Manufacturer Details
  Scenario: get All valid MFR details and verify correct status code is returned
    Given User hits the "getAllMFRResource" with get API request
    Then User verifies the valid status code "200" in the response

  Scenario: get All valid MFR details and verify correct status code is returned
    Given User hits the "getAllMFRResource" with get API request
    Then response is in JSON format

  Scenario: Match the count of  All valid MFR details
    Given User hits the "getAllMFRResource" with get API request
    When User executes the query "getCountOfMFRName" And matches the count of MFR returned by API and DB

#  Scenario: Match the MFR Ids from Db and API response
#    Given User hits the "getAllMFRResource" with get API request
#    When User executes the query "getAllActiveMFRID" And matches the MFRIDs returned by API and DB
#  Scenario Outline: Match the MFR Name of  All valid active MFRs
#    Given User hits the MFRContractEndpoint with get request
#    When  API  processes the request
#    Then User connects to DB
#    And DB details are obtained and printed
#    And User executes the  '<query>' to get allActiveMFR name
#    And matches the MFR Name returned by API and  DB
#    Examples:
#      |query|
#      |getAllActiveMFRName|