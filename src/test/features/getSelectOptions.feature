Feature:Retrieve and validate the list of valid getSelect Options of Code Dictionary Details
  @Smoke
  Scenario: get All valid Code_Dictionary details and verify correct statusCode
    Given User hits the "getSelectOptionsResource" with get request
    Then User verify status code "200" for the response

  @Smoke
  Scenario: get All valid Code_Dictionary details and verify Response JSON body and JSON formate
    Given User hits the "getSelectOptionsResource" with get request
    Then The response is in JSON format

  @Regression @Functional
  Scenario Outline: Validate CodeValue and CodeDescription of given CodeType is returned from API and DB
    Given User hits the "getSelectOptionsResource" with get request
    When User executes the query "<Query>" And matches the CodeValue and CodeDescription returned by API and DB
    Examples:
      | Query                                       |
      | getCodeTypeOfDisputeDays                    |
      | getCodeDictionaryOfDisputeDays              |
      | getCodeTypeOfBillingCycle                   |
      | getCodeDictionaryOfBillingCycle             |
      | getCodeTypeOfReSubmissionWindow             |
      | getCodeDictionaryOfReSubmissionWindow       |
      | getCodeTypeOfUmRule                         |
      | getCodeDictionaryOfUmRule                   |
      | getCodeTypeOfPricingMethod                  |
      | getCodeDictionaryOfPricingMethod            |
      | getCodeTypeOfPaymentTerms                   |
      | getCodeDictionaryOfPaymentTerms             |
      | getCodeTypeOfRebateableDrugTier             |
      | getCodeDictionaryOfRebateableDrugTier       |
      | getCodeTypeOfCompFrmlPosition               |
      | getCodeDictionaryOfCompFrmlPosition         |
      | getCodeTypeOfFrmlPosition                   |
      | getCodeDictionaryOfFrmlPosition             |
      | getCodeTypeOfSubmissionWindow               |
      | getCodeDictionaryOfSubmissionWindow         |
      | getCodeTypeOfProdOrMfr                      |
      | getCodeDictionaryOfProdOrMfr                |
      | getCodeTypeOfDrugGroupType                  |
      | getCodeDictionaryOfDrugGroupType            |
      | getCodeTypeOfRebateOptionBillingCycle       |
      | getCodeDictionaryOfRebateOptionBillingCycle |
      | getCodeTypeOfLateFee                        |
      | getCodeDictionaryOfLateFee                  |
      | getCodeTypeOfLateFee                        |
      | getCodeDictionaryOfLateFee                  |
      | getCodeTypeOfAuditFrequency                 |
      | getCodeDictionaryOfAuditFrequency           |
      | getCodeTypeOfAuditLookback                  |
      | getCodeDictionaryOfAuditLookback            |
      | getCodeTypeOfPricingBasis                   |
      | getCodeDictionaryOfPricingBasis             |
      | getCodeTypeOfPricingRefDate                 |
      | getCodeDictionaryOfPricingRefDate           |
      | getCodeTypeOfCompRestrictions               |
      | getCodeDictionaryOfCompRestrictions         |
      | getCodeTypeOfCompDrugTier                   |
      | getCodeDictionaryOfCompDrugTier             |
      | getCodeTypeOfDrugSource                     |
      | getCodeDictionaryOfDrugSource               |
      | getCodeTypeOfDrugSearchCriteria             |
      | getCodeDictionaryOfDrugSearchCriteria       |
      | getCodeTypeOfLobNonPartD                    |
      | getCodeDictionaryOfLobNonPartD              |
      | getCodeTypeOfLocations                      |
      | getCodeDictionaryOfLocations                |
      | getCodeTypeOfLobPartD                       |
      | getCodeDictionaryOfLobPartD                 |
      | getCodeTypeOfFormularyType                  |
      | getCodeDictionaryOfFormularyType            |





