## In This Feature file All the Scenarios for fetchDrugGroupSummay
Feature: Fetch the Drug group summary and validate the response

  @Smoke
  Scenario: Hit the Fetch Drug group summary API and Check the API response Status Code is 200
    Given User runs the Query and get the "drugGroupSummaryRowkey" and get the Rowkey of the DrugGroup
    When User hits the API with Endpoint "fetchDrugListSummary"
    Then User check the Status Code is "200"

  @Smoke
  Scenario: Hit the Fetch Drug group summary API and Check the API response Status Code is in Json Format
    Given User runs the Query and get the "drugGroupSummaryRowkey" and get the Rowkey of the DrugGroup
    When User hits the API with Endpoint "fetchDrugListSummary"
    Then User check Response is in JSON format

  @Functional
  Scenario: Hit the Fetch Drug group summary API and Validate the API response with DB
    Given User runs the Query and get the "drugGroupSummaryRowkey" and get the Rowkey of the DrugGroup
    When User hits the API with Endpoint "fetchDrugListSummary"
    Then User hits the Query "drugGroupSummaryDetails" and evaluates the Response with DB "resultColumnName"

  @Functional
  Scenario: Verify blank drugGroup RowKey
    When User hits the API with Endpoint "fetchDrugListSummary"
    Then User check the Status Code is "200"
    Then User verify the valid Response fetchDrugGroupSummary body key "messageFindDrugGroupJson" and expected value "invalidMessage"

  @Functional
  Scenario: Hit the Fetch Drug group summary API with invalid dataType of Drug List ID
    Given User Hits the API endpoint "fetchDrugListSummary" with Typemissmatch datatype Druglist ID "inValidDrugListID"
    Then User Verifies and Validate the Response of "ivalidDataResponseMessage" and expected value "invalidMessageForFetchDrugGroupSummary"

