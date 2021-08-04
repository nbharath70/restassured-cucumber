### In This Feature file All the Scenarios for FtechJobList API
#Feature: Fetch the Drug group summary and validate the response
#
#  Scenario: Hit the Fetch Drug group summary API and Check the API response Status Code is 200
#    Given User runs the Query and get the "drugGroupSummaryRowkey" and get the Rowkey of the DrugGroup
#    When User hits the API with Endpoint "fetchDrugListSummary"
#    Then User check the Status Code is "200"
#
#  Scenario: Hit the Fetch Drug group summary API and Check the API response Status Code is in Json Format
#    Given User runs the Query and get the "drugGroupSummaryRowkey" and get the Rowkey of the DrugGroup
#    When User hits the API with Endpoint "fetchDrugListSummary"
#    Then User check Response is in JSON format
#
#  Scenario: Hit the Fetch Drug group summary API and verify DrugListid from response
#    Given User runs the Query and get the "drugGroupSummaryRowkey" and get the Rowkey of the DrugGroup
#    When User hits the API with Endpoint "fetchDrugListSummary"
#    And User Runs the DrugGroupQuery "drugGroupSummaryDetails" to fetch Coulumn "fetchJobListAPIColumnName" and Verify with Response by JSONpath "drugGroupNameForFetcDrugGroupSummary"
#
#  Scenario: Hit the Fetch Drug group summary API and verify DrugListid from response
#    Given User runs the Query and get the "drugGroupSummaryRowkey" and get the Rowkey of the DrugGroup
#    When User hits the API with Endpoint "fetchDrugListSummary"
#    And User Runs the DrugGroupQuery "drugGroupSummaryDetails" to fetch Coulumn "fetchJobListAPIColumnName" and Verify with Response by JSONpath "mfrNameJsonPathForFetcDrugGroupSummary"
#
#  Scenario: Hit the Fetch Drug group summary API and verify DrugListid from response
#    Given User runs the Query and get the "drugGroupSummaryRowkey" and get the Rowkey of the DrugGroup
#    When User hits the API with Endpoint "fetchDrugListSummary"
#    And User Runs the DrugGroupQuery "drugGroupSummaryDetails" to fetch Coulumn "fetchJobListAPIColumnName" and Verify with Response by JSONpath "drugGroupTypeForFetcDrugGroupSummary"
#
#  Scenario: Hit the Fetch Drug group summary API and verify DrugListid from response
#    Given User runs the Query and get the "drugGroupSummaryRowkey" and get the Rowkey of the DrugGroup
#    When User hits the API with Endpoint "fetchDrugListSummary"
#    And User Runs the DrugGroupQuery "drugGroupSummaryDetails" to fetch Coulumn "fetchJobListAPIColumnName" and Verify with Response by JSONpath "drugGroupTypeDescFetcDrugGroupSummary"
#
#  Scenario: Hit the Fetch Drug group summary API and verify DrugListid from response
#    Given User runs the Query and get the "drugGroupSummaryRowkey" and get the Rowkey of the DrugGroup
#    When User hits the API with Endpoint "fetchDrugListSummary"
#    And User Runs the DrugGroupQuery "drugGroupSummaryDetails" to fetch Coulumn "fetchJobListAPIColumnName" and Verify with Response by JSONpath "lifecycleStatusForFetcDrugGroupSummary"
#
#  Scenario: Hit the Fetch Drug group summary API and verify DrugListid from response
#    Given User runs the Query and get the "drugGroupSummaryRowkey" and get the Rowkey of the DrugGroup
#    When User hits the API with Endpoint "fetchDrugListSummary"
#    And User Runs the DrugGroupQuery "drugGroupSummaryDetails" to fetch Coulumn "fetchJobListAPIColumnName" and Verify with Response by JSONpath "previouslyApprovedForFetcDrugGroupSummary"
