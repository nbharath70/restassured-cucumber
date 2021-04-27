## This feature file defines the automation scenarios to be developed for API to fetch LOBs for a given Contract details rowkey
#Feature:Retrieve and validate the LOBs for a  Contract
#
#  Scenario: get LOBS for a valid contract and verify the correct status code is returned
#    Given User executes query "SQLtoPickaValidContract" and gets value for column "columnNameToGetContractRowkey"
#    And User Hits "getLOBofaContractRowkeyResource" with Get API request
#    Then User verifies the valid status code "200" in get LOBs API response
#
#
#  Scenario: get LOBS for a valid contract and verify it from DB
#    Given User executes query "SQLtoPickaValidContract" and gets value for column "columnNameToGetContractRowkey"
#    And User executes query "SQLtoPickaValidContract" and gets Contract "colNametoGetContractDetailJson" from DB
#    And User Hits "getLOBofaContractRowkeyResource" with Get API request
#    Then User verifies API response with DB response
#
#
