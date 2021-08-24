## In This Feature file All the Scenarios for fetchDrugGroupSummay
Feature: Fetch the Drug group summary and validate the response

  Scenario: Hit the Fetch Drug group summary API and Check the API response Status Code is 200
    Given User runs the Query and get the "drugGroupSummaryRowkey" and get the Rowkey of the DrugGroup
    When User hits the API with Endpoint "fetchDrugListSummary"
    Then User check the Status Code is "200"

  Scenario: Hit the Fetch Drug group summary API and Check the API response Status Code is in Json Format
    Given User runs the Query and get the "drugGroupSummaryRowkey" and get the Rowkey of the DrugGroup
    When User hits the API with Endpoint "fetchDrugListSummary"
    Then User check Response is in JSON format

  Scenario: Hit the Fetch Drug group summary API and Validate the API response with DB
    Given User runs the Query and get the "drugGroupSummaryRowkey" and get the Rowkey of the DrugGroup
    When User hits the API with Endpoint "fetchDrugListSummary"
    Then User hits the Query "drugGroupSummaryDetails" and evaluates the Response with DB "resultColumnName"

#  Scenario: Hit the Fetch Drug group summary API and verify drugGroupName from response
#    Given User runs the Query and get the "drugGroupSummaryRowkey" and get the Rowkey of the DrugGroup
#    When User hits the API with Endpoint "fetchDrugListSummary"
#    And User Runs the DrugGroupQuery "drugGroupSummaryDetails" to fetch Coulumn "fetchDrugGroupSummaryAPIColumnName" and Verify with Response by JSONpath "drugGroupNameForFetcDrugGroupSummary"
#
#  Scenario: Hit the Fetch Drug group summary API and verify mfrName from response
#    Given User runs the Query and get the "drugGroupSummaryRowkey" and get the Rowkey of the DrugGroup
#    When User hits the API with Endpoint "fetchDrugListSummary"
#    And User Runs the DrugGroupQuery "drugGroupSummaryDetails" to fetch Coulumn "fetchDrugGroupSummaryAPIColumnName" and Verify with Response by JSONpath "mfrNameJsonPathForFetcDrugGroupSummary"
#
#  Scenario: Hit the Fetch Drug group summary API and verify drugGroupType from response
#    Given User runs the Query and get the "drugGroupSummaryRowkey" and get the Rowkey of the DrugGroup
#    When User hits the API with Endpoint "fetchDrugListSummary"
#    And User Runs the DrugGroupQuery "drugGroupSummaryDetails" to fetch Coulumn "fetchDrugGroupSummaryAPIColumnName" and Verify with Response by JSONpath "drugGroupTypeForFetcDrugGroupSummary"
#
#  Scenario: Hit the Fetch Drug group summary API and verify drugGroupTypeDesc from response
#    Given User runs the Query and get the "drugGroupSummaryRowkey" and get the Rowkey of the DrugGroup
#    When User hits the API with Endpoint "fetchDrugListSummary"
#    And User Runs the DrugGroupQuery "drugGroupSummaryDetails" to fetch Coulumn "fetchDrugGroupSummaryAPIColumnName" and Verify with Response by JSONpath "drugGroupTypeDescFetcDrugGroupSummary"
#
#  Scenario: Hit the Fetch Drug group summary API and verify lifecycleStatus from response
#    Given User runs the Query and get the "drugGroupSummaryRowkey" and get the Rowkey of the DrugGroup
#    When User hits the API with Endpoint "fetchDrugListSummary"
#    And User Runs the DrugGroupQuery "drugGroupSummaryDetails" to fetch Coulumn "fetchDrugGroupSummaryAPIColumnName" and Verify with Response by JSONpath "lifecycleStatusForFetcDrugGroupSummary"
#
##  Scenario: Hit the Fetch Drug group summary API and verify previouslyApproved drug from response
##    Given User runs the Query and get the "drugGroupSummaryRowkey" and get the Rowkey of the DrugGroup
##    When User hits the API with Endpoint "fetchDrugListSummary"
##    And User Runs the DrugGroupQuery "drugGroupSummaryDetails" to fetch Coulumn "fetchJobListAPIColumnName" and Verify with Response by JSONpath "previouslyApprovedForFetcDrugGroupSummary"

  Scenario: Verify blank drugGroup RowKey
    When User hits the API with Endpoint "fetchDrugListSummary"
    Then User check the Status Code is "200"
    Then User verify the valid Response fetchDrugGroupSummary body key "messageFindDrugGroupJson" and expected value "invalidMessage" of string

  Scenario: Hit the Fetch Drug group summary API with invalid dataType of Drug List ID
    Given User Hits the API endpoint "fetchDrugListSummary" with Typemissmatch datatype Druglist ID "ZABD"
    Then User Verifies and Validate the Response of "ivalidDataResponseMessage" and expected value "invalidMessageForFetchDrugGroupSummary"

  Scenario: Hit the fetch Drug group summary API with discarded drug list id
    Given User runs the Query and get the "drugGroupSummaryRowkey" and get the Rowkey of the DrugGroup
