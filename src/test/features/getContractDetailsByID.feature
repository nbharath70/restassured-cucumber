Feature:Retrieve and validate the Manufacturer Details by ID
#  Scenario Outline: get All valid ContractDetailsbyID and verify correct status code is returned
#    Given User connects to DB
#    And DB details are obtained and printed
#    And User executes the  '<query>' to get ContractDetails Row_KEY
#    And User hits the ContractDetailsByIDEndpoint with get request
#    When API  processes the get request for manufaturer contract details
#    Then User gets the correct statusCode from API ContractDetails by ID
#    Examples:
#      |query|
#      |getMFRRowKey|
#
#   Scenario Outline: get All valid ContractDetailsbyID and verify correct status code is returned
#    Given User connects to DB
#    And DB details are obtained and printed
#    And User executes the  '<query>' to get ContractDetails Row_KEY
#    And User hits the ContractDetailsByIDEndpoint with get request
#    When API  processes the get request for manufaturer contract details
#    Then User gets the correct statusCode from API ContractDetails by ID
#     Then Then User verifies the response is in JSON format
#     Examples:
#      |query|
#      |getMFRRowKey|
#
#  Scenario Outline: get Manufacturername and Manufacturer ID from response and Validate with DB
#    Given User connects to DB
#    And DB details are obtained and printed
#    And User executes the  '<query>' to get ContractDetails Row_KEY
#    And User executes the  '<query2>' to get ContractID from DB
#    And User executes the  '<query3>' to get ManufacuterID from contractHeader table
#    And User executes the  '<query4>' to get ManufacturerName and ManufacturerID from DB
#    And User hits the ContractDetailsByIDEndpoint with get request
#    When API  processes the get request for manufaturer contract details
#    Then User gets the correct statusCode from API ContractDetails by ID
#    Then User verifies the response details with DB details
#    Examples:
#      |query|query2|query3|query4|
#      |getMFRRowKey|getContractID|getManufacturerID|getManufacturerName|
#
#  Scenario Outline: get ContractHeaderDetails from response and Validate with DB
#    Given User connects to DB
#    And DB details are obtained and printed
#    And User executes the  '<query>' to get ContractDetails Row_KEY
#    And User executes the  '<query2>' to get ContractID from DB
#    And User executes the  '<query3>' to get ContractHeaderDetails from contractDetails table
#    And User hits the ContractDetailsByIDEndpoint with get request
#    When API  processes the get request for manufaturer contract details
#    Then User gets the correct statusCode from API ContractDetails by ID
#    Then User verifies the ContractHeaderDetails details with DB details
#    Examples:
#      |query|query2|query3|
#      |getMFRRowKey|getContractID|getContractDetails|

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

#  Scenario Outline: Hit the API with Invalid Rowkey and validate Response
#    Given User connects to DB
#    And DB details are obtained and printed
#    And User executes the  '<query>' to get Count of ContractDetails
#    And User hits the ContractDetailsByIDEndpoint with wrong Rowkey with get request
#    When API  processes the get request for manufaturer contract details
#    Then User gets the correct statusCode from API ContractDetails by ID
#    Then User verifies Error message from API
#    Examples:
#      |query|
#      |getContractDetailsCount|

#  Scenario: Hit the API With blank Rowkey and validate Response
#    Given User connects to DB
#    And DB details are obtained and printed
#    And User hits the BlankContractDetailsByIDEndpoint with  get request
#    When API  processes the get request for manufaturer contract details
#    Then User gets the correct statusCode from API ContractDetails by ID
#    Then User verifies BlankID Error message from API
#
#
#  Scenario Outline: Hit the API With TypeMissMatch RowKey and validate Response
#    Given User connects to DB
#    And DB details are obtained and printed
#    And User executes the  '<query>' to get ContractDetails Row_KEY
#    And User executes the  '<query2>' to get ContractID from DB
#    And User hits the ContractDetailsByIDEndpoint with TypeMissMatch RowKey
#    When API  processes the get request for manufaturer contract details
#    Then User gets the correct statusCode from API ContractDetails by ID
#    Then User verifies the Response of API for TypeMissMatch request
#    Examples:
#      |query|query2|
#      |getMFRRowKey|getContractID|
