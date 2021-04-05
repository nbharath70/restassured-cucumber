# This feature file defines the automation scenarios to be developed for get All valid MFR end point
#Feature:Retrieve and validate the list of valid Manufacturer Details
#  Scenario: get All valid MFR details and verify correct status code is returned
#    Given User hits the MFRContractEndpoint with get request
#    When  API  processes the request
#    Then User gets the correct statusCode
#
#  Scenario: get All valid MFR details and verify correct status code is returned
#    Given User hits the MFRContractEndpoint with get request
#    When  API  processes the request
#    Then User gets the response  in JSON format
#
#  Scenario Outline: Match the count of  All valid MFR details
#    Given User hits the MFRContractEndpoint with get request
#    When  API  processes the request
#    Then User connects to DB
#    And DB details are obtained and printed
#    And User executes the  '<query>'
#    And matches the count of MFR returned by API and  DB
#    Examples:
#   |query|
#   |getCountOfMFRName|
#  Scenario Outline: Match the MFR ID of  All valid active MFRs
#    Given User hits the MFRContractEndpoint with get request
#    When  API  processes the request
#    Then User connects to DB
#    And DB details are obtained and printed
#    And User executes the  '<query>' to get allActiveMFRID
#    And matches the MFRID returned by API and  DB
#    Examples:
#      |query|
#      |getAllActiveMFRID|
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