Feature:Retrieve and validate the Manufacturer Details by ID
  Scenario Outline: get All valid MFR details by ID and verify correct status code is returned
    Given User connects to DB
    And DB details are obtained and printed
    And User executes the  '<query>' to get ContractDetails Row_KEY
    And User hits the ContractDetailsByIDEndpoint with get request
    When API  processes the get request for manufaturer contract details
    Then User gets the correct statusCode from API ContractDetails by ID
    Examples:
      |query|
      |getMFRRowKey|