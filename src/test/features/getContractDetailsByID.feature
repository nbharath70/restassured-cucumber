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

   Scenario Outline: get All valid MFR details by ID and verify correct status code is returned
    Given User connects to DB
    And DB details are obtained and printed
    And User executes the  '<query>' to get ContractDetails Row_KEY
    And User hits the ContractDetailsByIDEndpoint with get request
    When API  processes the get request for manufaturer contract details
    Then User gets the correct statusCode from API ContractDetails by ID
     Then Then User verifies the response is in JSON format
     Examples:
      |query|
      |getMFRRowKey|

  Scenario Outline: get Manufacturername and Manufacturer ID from response and Validate with DB
    Given User connects to DB
    And DB details are obtained and printed
    And User executes the  '<query>' to get ContractDetails Row_KEY
    And User executes the  '<query2>' to get ContractID from DB
    And User executes the  '<query3>' to get ManufacuterID from contractHeader table
    And User executes the  '<query4>' to get ManufacturerName and ManufacturerID from DB
    And User hits the ContractDetailsByIDEndpoint with get request
    When API  processes the get request for manufaturer contract details
    Then User gets the correct statusCode from API ContractDetails by ID
    Then User verifies the response details with DB details
    Examples:
      |query|query2|query3|query4|
      |getMFRRowKey|getContractID|getManufacturerID|getManufacturerName|

  Scenario Outline: get ContractHeaderDetails from response and Validate with DB
    Given User connects to DB
    And DB details are obtained and printed
    And User executes the  '<query>' to get ContractDetails Row_KEY
    And User executes the  '<query2>' to get ContractID from DB
    And User executes the  '<query3>' to get ContractHeaderDetails from contractDetails table
    And User hits the ContractDetailsByIDEndpoint with get request
    When API  processes the get request for manufaturer contract details
    Then User gets the correct statusCode from API ContractDetails by ID
    Then User verifies the ContractHeaderDetails details with DB details
    Examples:
      |query|query2|query3|
      |getMFRRowKey|getContractID|getContractDetails|

  Scenario Outline: get ContractHeaderDetails from response and Validate with DB
    Given User connects to DB
    And DB details are obtained and printed
    And User executes the  '<query>' to get ContractDetails Row_KEY
    And User executes the  '<query2>' to get ContractID from DB
    And User executes the  '<query3>' to get ContractHeaderDetailJSON from contractDetails table
    And User hits the ContractDetailsByIDEndpoint with get request
    When API  processes the get request for manufaturer contract details
    Then User gets the correct statusCode from API ContractDetails by ID
    Then User verifies the ContractHeaderDetailsJSON details with DB details
    Examples:
      |query|query2|query3|
      |getMFRRowKey|getContractID|getContractDetailsJSON|
