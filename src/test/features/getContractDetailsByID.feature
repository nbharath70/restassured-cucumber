Feature:Retrieve and validate the Manufacturer Details by ID
  Scenario Outline: get All valid MFR details by ID and verify correct status code is returned
    Given User hits the MFRDetailsByIDEndpoint with get request
    And User executes the  '<query>' to get ContractDetails Row_KEY
    When  API  processes the request
    Then User gets the correct statusCode from API ContractDetails by ID
    Examples:
      |query|
      |getMFRRowKey|