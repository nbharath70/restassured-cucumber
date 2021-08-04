# This feature file defines the automation scenarios to be developed for API to fetch LOBs for a given Contract details rowkey
Feature:Retrieve and validate the LOBs for a  Contract
  @Smoke
  Scenario: get LOBS for a valid contract and verify the correct status code is returned
    Given User executes query "SQLtoPickaValidContract" and gets value for column "columnNameToGetContractRowkey"
    And User Hits "getLOBofaContractRowkeyResource" with Get API request
    Then User verifies the valid status code "200" in get LOBs API response

  @Regression @Functional
  Scenario: get LOBS for a valid contract and verify it from DB
    Given User executes query "SQLtoPickaValidContract" and gets value for column "columnNameToGetContractRowkey"
    And User executes query "SQLtoPickaValidContract" and gets Contract "colNametoGetContractDetailJson" from DB
    And User Hits "getLOBofaContractRowkeyResource" with Get API request
    Then User verifies API response with DB response

  @Regression @Functional
  Scenario: get LOBS for an Invalid contract and verify the error msg
    Given User executes "SQLtoPickAnInvalidContract" and gets value for column "columnNameToGetContractRowkey"
    And User Hits "getLOBofaContractRowkeyResource" with Get API request
    Then User verifies API response with error msg "errorMsgJsonforLOBofInvalidContractRowKey"
    Then User verifies the LOBS for a contract response header Error Code value "-1"

  @Regression @Functional
  Scenario: get LOBS for an Blank contract rowKey and verify the error msg
    Given User Hits "getLOBofaContractRowkeyResource" with blank rowKey and Get API request
    Then User verifies API response with Blank Contract RowKey error msg "errorMsgJsonforLOBofBlankContractRowKey"
    Then User verifies the LOBS for a contract response header Error Code value "-1"

  @Regression @Functional
  Scenario: get LOBS for a contract with type mismatch and verify the error msg
    Given User Hits "getLOBofaContractRowkeyResource" with rowKey "ABCD" and Get API request
    Then User verifies API response with type mismatch error msg "errorMsgJsonforLOBofTypeMismatchedContractRowkey"
    Then User verifies the LOBS for a contract response header Error Code value "-1"

  @Regression @Functional
  Scenario: get LOBS for a contract with Is_current_Flag 0 and verify the error msg
    Given User executes "SQLtoPickAContractWithIsCurrentFlag0" and gets value for column "columnNameToGetContractRowkey"
    And User Hits "getLOBofaContractRowkeyResource" with Get API request
    Then User verifies API response with error msg "errorMsgJsonforLOBofIsCurrentFlag0ContractRowKey"
    Then User verifies the LOBS for a contract response header Error Code value "-1"

