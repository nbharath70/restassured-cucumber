##This feature file defines the automation scenarios to be developed for getContractByID end point
#Feature:Retrieve and validate the Contract by ID
#  Scenario: get All valid ContractDetailsbyID and verify correct status code is returned
#    Given User Runs the Query "getMFRRowKey" and Get the Rowkey from DB
#    And User hits the "getContractDetailsByID" Endpoint with get API request
#    Then User Verifies the API response Status code is "200"
#
#   Scenario: verifies the response is in JSON format
#     Given User Runs the Query "getMFRRowKey" and Get the Rowkey from DB
#    And User hits the "getContractDetailsByID" Endpoint with get API request
#    Then verify the Response in JSON format
#
#  Scenario: get Manufacturername and Manufacturer ID from response and Validate with DB
#    Given User Runs the Query "getMFRRowKey" and Get the Rowkey from DB
#    And User Runs the Fetching ContractDetailsID Query "getContractID" and Get the ContractDetailsId from DB
#    And User Runs the Fetching ManufactuereID Query "getManufacturerID" and Get the ManufactuereID from DB
#    And user Runs the Fetching ManufacturerDetails Query "getManufacturerDetails" and get All Manufacturer details from DB
#    And User hits the "getContractDetailsByID" Endpoint with get API request
#    Then Verify both the ManufacturerDetails response from DB and API
#
#   Scenario: get ContractHeaderDetails from response and Validate with DB
#    Given User Runs the Query "getMFRRowKey" and Get the Rowkey from DB
#    And User Runs the Fetching ContractDetailsID Query "getContractID" and Get the ContractDetailsId from DB
#    And User Runs the Fetching ContractHeaderDetail Query "getContractHeaderDetails" and Get the ContractHeaderDetails from DB
#    And User hits the "getContractDetailsByID" Endpoint with get API request
#    Then verify both the ContractHeaderDetails response from DB and API
#
#  Scenario Outline: get ContractDetailsJSON from response and Validate with DB
#   Given User Runs the Query "getMFRRowKey" and Get the Rowkey from DB
#   And User Runs the Fetching ContractDetailsID Query "getContractID" and Get the ContractDetailsId from DB
#   And User Runs the Fetching ContractDetailsJSON Query "getContractDetailsJSON" and Get the ContractDetailsJSON from DB
#   And User hits the "getContractDetailsByID" Endpoint with get API request
#   Then verify Both API ContractDetailJSON with JSONpath "<apiJsonPath>" with DB JSON with DB JSON "<dbJsonPath>"
#   Examples:
#    |apiJsonPath|dbJsonPath|
#    |APIcontractDetailsJSONSchemaversion|DBcontractDetailsJSONSchemaversion|
#    |APIcontractDetailsJSONLineOfBusiness|DBcontractDetailsJSONLineOfBusiness|
#    |APIcontractDetailsJSONLocations     |DBcontractDetailsJSONLocations     |
#    |APIcontractDetailsJSONBillingCycle  |DBcontractDetailsJSONBillingCycle  |
#    |APIcontractDetailsJSONSubmissionWindow|DBcontractDetailsJSONSubmissionWindow|
#    |APIcontractDetailsJSONResubmissionWindow|DBcontractDetailsJSONResubmissionWindow|
#    |APIcontractDetailsJSONPaymentTerms      |DBcontractDetailsJSONPaymentTerms      |
#    |APIcontractDetailsJSONThirdPartyAuth    |DBcontractDetailsJSONThirdPartyAuth    |
#    |APIcontractDetailsJSONOpsAssignee       |DBcontractDetailsJSONOpsAssignee       |
#    |APIcontractDetailsJSONOpsQCer           |DBcontractDetailsJSONOpsQCer           |
#    |APIcontractDetailsJS0NPayment             |DBcontractDetailsJSONPayment         |
#    |APIcontractDetailsJSONAudit               |DBcontractDetailsJSONAudit           |
#
