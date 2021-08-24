#This feature file defines the automation scenarios to be developed for get All valid MFR end point
  Feature:Retrieve and validate the list of valid Manufacturer Details
   @Smoke
  Scenario: get All valid MFR details and verify correct status code is returned
    Given User hits the "getAllMFRResource" with get API request
    Then User verifies the valid status code "200" in the response

  @Smoke
  Scenario: get All valid MFR details and verify correct status code is returned
    Given User hits the "getAllMFRResource" with get API request
    Then response is in JSON format

    @Functional
  Scenario: Match the count of  All valid MFR details
    Given User hits the "getAllMFRResource" with get API request
    When User executes the query "getCountOfMFRName" And matches the count of MFR returned by API and DB

    @Regression @Functional
  Scenario: verify API Response with DB
    Given User hits the "getAllMFRResource" with get API request
    Then User executes the query "getAllManufacturerDetails" And matches the Details returned by API and DB ColumnName "resultColumnName"






