#In This Feature file All the Scenarios for APItoSearchDGDetailsByDLDrugListIdandNDC exists
Feature: APItoSearchDGDetailsByDrugListIdandNDC.feature - Search and Fetch drug group details by drug list Drug List Id and NDC

#  @Positive @smoke
#  Scenario: validate the Status for valid DG and valid NDC
#    Given User executes the "sqlToFetchValidDRugListRowKey" and captures DL "rowkey"
#    And User executes the "sqlToFetchValidNDC" and captures the NDC from "columnNDC"
#    And User Hits resource "SearchDGDetailsByDrugListIdandNDC" with Get API request
#    And User verifies the correct status code "200" from API response

  @Positive @smoke
  Scenario: validate the API response for valid DG and valid NDC
    Given User executes the "sqlToFetchValidDRugListRowKey" and captures DL "rowkey"
    And User executes the "sqlToFetchValidNDC" and captures the NDC from "columnNDC"
    And User Hits resource "SearchDGDetailsByDrugListRowKeyandNDC" with Get API request
    Then User executes the query "sqlToFetchDrugDetailsWithDrugListRowKeyAndNDC" and gets Drug details from column "result"
    And User Verifies the DBJson with API response Json
    |DrugrowKey|lifecycleStatus|startDate|endDate|recCreatedDate|recCreatedBy|recUpdatedDate|recUpdatedBy|exists|mfrName|brandName|ndc|modifiedDrug|modifiedDrugFlag|generic|strength|dosage|route|brandOrGenericIndicator|ssbOrMSB|therapeuticClassGPI|repackaged|institutional|obsoleteDate|medispanStartDate|medispanEndDate|endDateFlag|
    |DrugrowKey|lifecycleStatus|startDate|endDate|recCreatedDate|recCreatedBy|recUpdatedDate|recUpdatedBy|exists|mfrName|brandName|ndc|modifiedDrug|modifiedDrugFlag|generic|strength|dosage|route|brandOrGenericIndicator|ssbOrMSB|therapeuticClassGPI|repackaged|institutional|obsoleteDate|medispanStartDate|medispanEndDate|endDateFlag|
#
#  @Negative
#  Scenario: validate the API response for Blank DG and valid NDC
#    Given User sets null in DL rowkey
#    Given User executes the "sqlToFetchValidNDC" and captures the NDC from "columnNDC"
#    And User Hits resource "SearchDGDetailsByDrugListRowKeyandNDC" with Get API request
#    Then User validates the correct "errorMsgforSearchDGDByRowkeyandNDC" and validates "errorMessageJsonPath"
#
#  @Negative
#  Scenario: validate the API response for valid DG and blank NDC
#    Given User executes the "sqlToFetchValidDRugListRowKey" and captures DL "rowkey"
#    Given User sets null in NDC
#    And User Hits resource "SearchDGDetailsByDrugListRowKeyandNDC" with Get API request
#    Then User validates the correct "errorMsgforSearchDGDByRowkeyandNDC" and validates "errorMessageJsonPath"

#  @Negative
#  Scenario: validate the API response for Blank DG and blank NDC
#    Given User sets null in DL rowkey
#    Given User sets null in NDC
#    And User Hits resource "SearchDGDetailsByDrugListRowKeyandNDC" with Get API request
#    Then User validates the correct "errorMsgforSearchDGDByRowkeyandNDC" and validates "errorMessageJsonPath"
#
#  @Negative
#  Scenario: validate the API response for valid DG and NDC with 5 chars
#    Given User executes the "sqlToFetchValidDRugListRowKey" and captures DL "rowkey"
#    And User executes the "sqlToFetchValidNDC" and captures the NDC from "columnNDC"
#    And User Hits resource "SearchDGDetailsByDrugListRowKeyandNDC" with Get API request with 5 NDC chars
#    Then User validates the correct "errorMsgforSearchDGDByRowkeyandNDC" and validates "errorMessageJsonPath"