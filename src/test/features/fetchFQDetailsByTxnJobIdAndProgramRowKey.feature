####This feature file consists of all the scenarios of fetchFQDetailsByFQHIdAndProgramRowKey API
  Feature: Fetch Formulary Qualification Details and Validate with DB

    Scenario: Hit the fetchFQDetailsByFQHIdAndProgramRowKey API and check the Status code
      Given User runs the query "getJobTransactionIDAndProgramRowKey" and fetches valid jobID and ProgramRowKey
      And User hits the Endpoint "fetchFQDetailsByFQHIdAndProgramRowKey" JobID and ProgramRowkey
      Then User verifies the fetchFQDetailsByFQHIdAndProgramRowKeyAPI Status Code is "200"

    Scenario: Hit the fetchFQDetailsByFQHIdAndProgramRowKey API and check the Status code
      Given User runs the query "getJobTransactionIDAndProgramRowKey" and fetches valid jobID and ProgramRowKey
      And User hits the Endpoint "fetchFQDetailsByFQHIdAndProgramRowKey" JobID and ProgramRowkey
      And User verifies the Response in JSON Format of API

    Scenario: Verify with Valid processTrasactionJOBID and inValid Programm Row Key
      Given User runs the query "getJobTransactionIDAndProgramRowKey" and fetches valid jobID and ProgramRowKey
      And User hits the Endpoint "fetchFQDetailsByFQHIdAndProgramRowKey" JobID with inValid ProgramRowkey
      Then User verifies the fetchFQDetailsByFQHIdAndProgramRowKeyAPI Status Code is "200"
      Then User verifies the invalid Message "invalidProgramRowKeyMesssage" using jsonPath "invalidMessageJsonPath"

    Scenario: Verify with inValid processTrasactionJOBID  and Valid Programm Row Key
      Given User runs the query "getJobTransactionIDAndProgramRowKey" and fetches valid jobID and ProgramRowKey
      And User hits the Endpoint "fetchFQDetailsByFQHIdAndProgramRowKey" JobID with inValid processTrasactionJOBID
      Then User verifies the fetchFQDetailsByFQHIdAndProgramRowKeyAPI Status Code is "200"
      Then User verifies the invalid Message "invalidtransactionJobMesssage" using jsonPath "invalidMessageJsonPath"

    Scenario: Verify with both processTrasactionJOBID and Programm Row Key as Balnk values
      Given User hits the Endpoint "fetchFQDetailsByFQHIdAndProgramRowKey" JobID with both BlankValues
      Then User verifies the fetchFQDetailsByFQHIdAndProgramRowKeyAPI Status Code is "200"
      Then User verifies the invalid Message "blankValuesInvalidMessage" using jsonPath "invalidMessageJsonPath"

    Scenario: Hit the API and Valid the Response with DB
      Given User runs the query "getJobTransactionIDAndProgramRowKey" and fetches valid jobID and ProgramRowKey
      And User hits the Endpoint "fetchFQDetailsByFQHIdAndProgramRowKey" JobID and ProgramRowkey
      Then User verifies the fetchFQDetailsByFQHIdAndProgramRowKeyAPI Status Code is "200"
      And User Runs the Query "getRebateoptionRowkeyRowkeys" and fetch the RebateoptionsRowkey from DB using ColumnName"rebateOptionRowKey"
      And User Runs the Query "rebateOptionsForFetchFormularyAPI" and Validate the Rebate option response with DB Columnname "resultColumnName"
