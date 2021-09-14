####This feature file consists of all the scenarios of fetchFQDetailsByFQHIdAndProgramRowKey API
  Feature: Fetch Formulary Qualification Details and Validate with DB

    @Smoke
    Scenario: Hit the fetchFQDetailsByFQHIdAndProgramRowKey API and check the Status code
      Given User runs the query "getJobTransactionIDAndProgramRowKey" and fetches valid jobID and ProgramRowKey
      And User hits the endpoint "fetchFQDetailsByFQHIdAndProgramRowKey" jobID and programRowkey
      Then User verifies the fetchFQDetailsByFQHIdAndProgramRowKeyAPI statusCode is "200"

    @Smoke
    Scenario: Hit the fetchFQDetailsByFQHIdAndProgramRowKey API and Verify the Response is JSON Format
      Given User runs the query "getJobTransactionIDAndProgramRowKey" and fetches valid jobID and ProgramRowKey
      And User hits the endpoint "fetchFQDetailsByFQHIdAndProgramRowKey" jobID and programRowkey
      And User verifies the Response in JSON Format of API

    @Functional
    Scenario: Verify with Valid processTrasactionJOBID and inValid Programm Row Key
      Given User runs the query "getJobTransactionIDAndProgramRowKey" and fetches valid jobID and ProgramRowKey
      And User hits the endpoint "fetchFQDetailsByFQHIdAndProgramRowKey" jobID with invalid programRowkey
      Then User verifies the fetchFQDetailsByFQHIdAndProgramRowKeyAPI statusCode is "200"
      Then User verifies the invalid message "invalidProgramRowKeyMesssage" using jsonPath "invalidMessageJsonPath"

    @Functional
    Scenario: Verify with inValid processTrasactionJOBID  and Valid Programm Row Key
      Given User runs the query "getJobTransactionIDAndProgramRowKey" and fetches valid jobID and ProgramRowKey
      And User hits the endpoint "fetchFQDetailsByFQHIdAndProgramRowKey" jobID with invalid programRowkey
      Then User verifies the fetchFQDetailsByFQHIdAndProgramRowKeyAPI statusCode is "200"
      Then User verifies the invalid message "invalidtransactionJobMesssage" using jsonPath "invalidMessageJsonPath"

    @Functional
    Scenario: Verify with both processTrasactionJOBID and Programm Row Key as Balnk values
      Given User hits the Endpoint "fetchFQDetailsByFQHIdAndProgramRowKey" jobID with both blankValues
      Then User verifies the fetchFQDetailsByFQHIdAndProgramRowKeyAPI statusCode is "200"
      Then User verifies the invalid message "blankValuesInvalidMessage" using jsonPath "invalidMessageJsonPath"

    @Functional
    Scenario: Hit the API and Valid the Response with DB
      Given User runs the query "getJobTransactionIDAndProgramRowKey" and fetches valid jobID and ProgramRowKey
      And User hits the endpoint "fetchFQDetailsByFQHIdAndProgramRowKey" jobID and programRowkey
      Then User verifies the fetchFQDetailsByFQHIdAndProgramRowKeyAPI statusCode is "200"
      And User Runs the query "getRebateoptionRowkeyRowkeys" and fetch the rebateOptionsRowkey from DB using columnName"rebateOptionRowKey"
      And User Runs the query "rebateOptionsForFetchFormularyAPI" and validate the rebate options response with DB columnname "resultColumnName"
