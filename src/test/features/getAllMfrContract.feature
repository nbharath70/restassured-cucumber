#This feature file defines the automation scenarios to be developed for get All valid MFR end point
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

  Scenario: Match the ManufacturerID of  All valid MFR details
    Given User hits the "getAllMFRResource" with get API request
    Then User executes the query "getAllActiveMFRID" And matches the MFR ID returned by API and DB


  Scenario: Match the ManufacturerName of  All valid MFR details
    Given User hits the "getAllMFRResource" with get API request
    Then User executes the query "getAllActiveMFRName" And matches the MFR Name returned by API and DB





