Feature:Retrieve and validate the create new rebate option

  @Smoke
  Scenario: Validate the create new rebate options save progress creation for manufacturer contract and verify correct status code with response body is returned
    Then User executes the query "deleteContractHeaderByContractName" and "deleteContractDetailByAmendmentName" for contract & Amendment name "QAAutomation%" to delete the record from the database
    Then User executes "deleteRebateProgram" query to delete existing rebate program by Contract_ID "ASTRA%" from the database
    Then User executes the query "deleteBenefitRule" by benefitRuleName "QAAutomation%" for delete benefit rule by contractID
    Then User executes the query "deleteRebateOption" by RebateOptionName "QAAutomation%" for deleting existing rebate option record from DB
    Given User create the Initiate New Manufacture Contract date
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-08-01 | 2022-07-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 01 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | CPOS           | All       | MON          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
    Then User hits the "postInitiateNewManufacturerContract" with post request
    Then User verify InitiateNewManufacturerContract status code "200" for the response
    Then User verify the valid Response body key "recordInserted" and expected value "true"
    Then User update manufacture contract data
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-08-01 | 2022-07-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 02 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | HiEx           | All       | YRL          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
    Then User hits the "updateManufacturerContract" update manufacture contract post request
    Then User verify UpdateManufacturerContract status code "200" for the response
    Then User verify the updateManufacturerContract valid Response body key "recordUpdated" and expected value "true"
    Then User executes the query "updateContractHeaderStatusFromNewToInProgress" and "updateContractDetailsStatusFromNewToInProgress" for contract & Amendment name "QAAutomation_10" to update the contract record status from New to InProgress from the database
    Given User create & sava new rebate program
      | contractName    | startDate  | endDate    | programName             | legacyContractID       | pricingBasis | pricingRefDate | psfApply | programLevelPsf | condition |
      | QAAutomation_10 | 2021-08-01 | 2022-07-20 | QAAutomationProgramName | QATestlegacyContractID | WAC          | 1ST_OF_QTR     | true     | 1               | All       |
    Then User hits the "createNewRebateProgram" with post request of CreateNewRebateProgram
    Then User verify createNewRebateProgram status code "200" for the response
    Then User verify the valid createNewRebateProgram Response body key "isRecordSaved" and expected value "true"
    Then User executes the query "getContractIdByContractName" by contract name "QAAutomation_10" to get contractID to create benefit rule
    And User create the Benefitruledata
      | benefitRuleName         | formularyType | aboveValue | belowValue |
      | QAAutomationBenefitRule | Incentive     | 1          | 1          |
    When User hits createBenefitRuleAPI "createBenefitRuleForAContract" with Post Request
    Then User executes the query "updateContractBenefitRuleToApproveStatus" by benefitRuleName "QAAutomationBenefitRule"  for update benefit rule status to approved by contractID
    Then User executes the query "getContractIdByContractName" by contract name "QAAutomation_10" to get contractID to create rebate options
    Then User executes the query "getMFR_Program_IDByContractID" to get programID from contractID to create rebate options
    Then User executes the query "getBenefitRule" by BenefitRulename "QAAutomationBenefitRule" to get benefitRuleID to create rebate options
    Then User executes the query "getMfrDrugListIdByListType" to get rebateableDrugListID for create new rebate options
    Given User create new rebate options details
      | mfrRebateOptionName       | startDate  | endDate    | xvalue | yvalue | prodOrMfr | paAndSt | billingCycle | lineOfBusiness | benefitRuleName         | psfOptionLevel | pricingMethod | price | firstRule      | firstCondition | secondRule     | secondCondition | thirdRule | position    | rebateableDrugfirstTier | rebateableDrugcondition | rebateableDrugsecondTier | compFormularyPosition | compDrugTier | compRestrictionsfirstRule | compRestrictionsfirstCondition | compRestrictionssecondRule | compRestrictionssecondCondition | compRestrictionsthirdRule | compRestrictionsthirdCondition | compRestrictionsfourthRule | compRestrictionsfourthCondition | compRestrictionsfifthRule |
      | QAAutomationRebateOptions | 2021-08-01 | 2022-07-20 | 1      | 1      | Product   | true    | MON          | HiEx           | QAAutomationBenefitRule | 1              | PctOfWAC      | 1     | PA_NOT_ALLOWED | AND            | ST_NOT_ALLOWED |                 |           | OnFormulary |                         |                         |                          |                       |              |                           |                                |                            |                                 |                           |                                |                            |                                 |                           |
    Then User hits the "createNewRebateOptionsSaveProgress" with post request of CreateNewRebateOptions save progress API
    Then User verify createNewRebateOptionsSaveProgress status code "200" for the response
    Then User verify the valid createNewRebateOptionsSaveProgress Response body key "isRecordSaved" and expected value "true"

#    Then validate the rebate option ID by executing query "getRebateOptionIDFromDB" by rebate option name "QAAutomationRebateOptions" and response json "getRebateOptionIdFromResponseJSON"
#    Then User verify the createNewRebateOptionsSaveProgress valid Response body key "message" and expected value "Program level psf should be blank when psf apply is false" of string
#    Then User verify the createNewRebateOptionsSaveProgress response header Error Code value "501"

  @Functional @Regression
  Scenario: Validate the existing create new rebate options record for save progress creation for manufacturer contract and verify correct status code with response body is returned
    Then User executes the query "deleteContractHeaderByContractName" and "deleteContractDetailByAmendmentName" for contract & Amendment name "QAAutomation%" to delete the record from the database
    Then User executes "deleteRebateProgram" query to delete existing rebate program by Contract_ID "ASTRA%" from the database
    Then User executes the query "deleteBenefitRule" by benefitRuleName "QAAutomation%" for delete benefit rule by contractID
    Then User executes the query "deleteRebateOption" by RebateOptionName "QAAutomation%" for deleting existing rebate option record from DB
    Given User create the Initiate New Manufacture Contract date
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-08-01 | 2022-07-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 01 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | CPOS           | All       | MON          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
    Then User hits the "postInitiateNewManufacturerContract" with post request
    Then User verify InitiateNewManufacturerContract status code "200" for the response
    Then User verify the valid Response body key "recordInserted" and expected value "true"
    Then User update manufacture contract data
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-08-01 | 2022-07-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 02 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | HiEx           | All       | YRL          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
    Then User hits the "updateManufacturerContract" update manufacture contract post request
    Then User verify UpdateManufacturerContract status code "200" for the response
    Then User verify the updateManufacturerContract valid Response body key "recordUpdated" and expected value "true"
    Then User executes the query "updateContractHeaderStatusFromNewToInProgress" and "updateContractDetailsStatusFromNewToInProgress" for contract & Amendment name "QAAutomation_10" to update the contract record status from New to InProgress from the database
    Given User create & sava new rebate program
      | contractName    | startDate  | endDate    | programName             | legacyContractID       | pricingBasis | pricingRefDate | psfApply | programLevelPsf | condition |
      | QAAutomation_10 | 2021-08-01 | 2022-07-20 | QAAutomationProgramName | QATestlegacyContractID | WAC          | 1ST_OF_QTR     | true     | 1               | All       |
    Then User hits the "createNewRebateProgram" with post request of CreateNewRebateProgram
    Then User verify createNewRebateProgram status code "200" for the response
    Then User verify the valid createNewRebateProgram Response body key "isRecordSaved" and expected value "true"
    Then User executes the query "getContractIdByContractName" by contract name "QAAutomation_10" to get contractID to create benefit rule
    And User create the Benefitruledata
      | benefitRuleName         | formularyType | aboveValue | belowValue |
      | QAAutomationBenefitRule | Incentive     | 1          | 1          |
    When User hits createBenefitRuleAPI "createBenefitRuleForAContract" with Post Request
    Then User executes the query "updateContractBenefitRuleToApproveStatus" by benefitRuleName "QAAutomationBenefitRule"  for update benefit rule status to approved by contractID
    Then User executes the query "getContractIdByContractName" by contract name "QAAutomation_10" to get contractID to create rebate options
    Then User executes the query "getMFR_Program_IDByContractID" to get programID from contractID to create rebate options
    Then User executes the query "getBenefitRule" by BenefitRulename "QAAutomationBenefitRule" to get benefitRuleID to create rebate options
    Then User executes the query "getMfrDrugListIdByListType" to get rebateableDrugListID for create new rebate options
    Given User create new rebate options details
      | mfrRebateOptionName       | startDate  | endDate    | xvalue | yvalue | prodOrMfr | paAndSt | billingCycle | lineOfBusiness | benefitRuleName         | psfOptionLevel | pricingMethod | price | firstRule      | firstCondition | secondRule     | secondCondition | thirdRule | position    | rebateableDrugfirstTier | rebateableDrugcondition | rebateableDrugsecondTier | compFormularyPosition | compDrugTier | compRestrictionsfirstRule | compRestrictionsfirstCondition | compRestrictionssecondRule | compRestrictionssecondCondition | compRestrictionsthirdRule | compRestrictionsthirdCondition | compRestrictionsfourthRule | compRestrictionsfourthCondition | compRestrictionsfifthRule |
      | QAAutomationRebateOptions | 2021-08-01 | 2022-07-20 | 1      | 1      | Product   | true    | MON          | HiEx           | QAAutomationBenefitRule | 1              | PctOfWAC      | 1     | PA_NOT_ALLOWED | AND            | ST_NOT_ALLOWED |                 |           | OnFormulary |                         |                         |                          |                       |              |                           |                                |                            |                                 |                           |                                |                            |                                 |                           |
    Then User hits the "createNewRebateOptionsSaveProgress" with post request of CreateNewRebateOptions save progress API
    Then User verify createNewRebateOptionsSaveProgress status code "200" for the response
    Then User verify the valid createNewRebateOptionsSaveProgress Response body key "isRecordSaved" and expected value "true"
    Given User create new rebate options details
      | mfrRebateOptionName       | startDate  | endDate    | xvalue | yvalue | prodOrMfr | paAndSt | billingCycle | lineOfBusiness | benefitRuleName         | psfOptionLevel | pricingMethod | price | firstRule      | firstCondition | secondRule     | secondCondition | thirdRule | position    | rebateableDrugfirstTier | rebateableDrugcondition | rebateableDrugsecondTier | compFormularyPosition | compDrugTier | compRestrictionsfirstRule | compRestrictionsfirstCondition | compRestrictionssecondRule | compRestrictionssecondCondition | compRestrictionsthirdRule | compRestrictionsthirdCondition | compRestrictionsfourthRule | compRestrictionsfourthCondition | compRestrictionsfifthRule |
      | QAAutomationRebateOptions | 2021-08-01 | 2022-07-20 | 1      | 1      | Product   | true    | MON          | HiEx           | QAAutomationBenefitRule | 1              | PctOfWAC      | 1     | PA_NOT_ALLOWED | AND            | ST_NOT_ALLOWED |                 |           | OnFormulary |                         |                         |                          |                       |              |                           |                                |                            |                                 |                           |                                |                            |                                 |                           |
    Then User hits the "createNewRebateOptionsSaveProgress" with post request of CreateNewRebateOptions save progress API
    Then User verify createNewRebateOptionsSaveProgress status code "200" for the response
    Then User verify the valid createNewRebateOptionsSaveProgress Response body key "isRecordSaved" and expected value "false"


  @Functional @Regression
  Scenario: Validate the Rebate Option dates are overlapping with parent program dates and verify correct status code with response body is returned
    Then User executes the query "deleteContractHeaderByContractName" and "deleteContractDetailByAmendmentName" for contract & Amendment name "QAAutomation%" to delete the record from the database
    Then User executes "deleteRebateProgram" query to delete existing rebate program by Contract_ID "ASTRA%" from the database
    Then User executes the query "deleteBenefitRule" by benefitRuleName "QAAutomation%" for delete benefit rule by contractID
    Then User executes the query "deleteRebateOption" by RebateOptionName "QAAutomation%" for deleting existing rebate option record from DB
    Given User create the Initiate New Manufacture Contract date
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-08-01 | 2022-07-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 01 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | CPOS           | All       | MON          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
    Then User hits the "postInitiateNewManufacturerContract" with post request
    Then User verify InitiateNewManufacturerContract status code "200" for the response
    Then User verify the valid Response body key "recordInserted" and expected value "true"
    Then User update manufacture contract data
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-08-01 | 2022-07-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 02 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | HiEx           | All       | YRL          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
    Then User hits the "updateManufacturerContract" update manufacture contract post request
    Then User verify UpdateManufacturerContract status code "200" for the response
    Then User verify the updateManufacturerContract valid Response body key "recordUpdated" and expected value "true"
    Then User executes the query "updateContractHeaderStatusFromNewToInProgress" and "updateContractDetailsStatusFromNewToInProgress" for contract & Amendment name "QAAutomation_10" to update the contract record status from New to InProgress from the database
    Given User create & sava new rebate program
      | contractName    | startDate  | endDate    | programName             | legacyContractID       | pricingBasis | pricingRefDate | psfApply | programLevelPsf | condition |
      | QAAutomation_10 | 2021-08-01 | 2022-07-20 | QAAutomationProgramName | QATestlegacyContractID | WAC          | 1ST_OF_QTR     | true     | 1               | All       |
    Then User hits the "createNewRebateProgram" with post request of CreateNewRebateProgram
    Then User verify createNewRebateProgram status code "200" for the response
    Then User verify the valid createNewRebateProgram Response body key "isRecordSaved" and expected value "true"
    Then User executes the query "getContractIdByContractName" by contract name "QAAutomation_10" to get contractID to create benefit rule
    And User create the Benefitruledata
      | benefitRuleName         | formularyType | aboveValue | belowValue |
      | QAAutomationBenefitRule | Incentive     | 1          | 1          |
    When User hits createBenefitRuleAPI "createBenefitRuleForAContract" with Post Request
    Then User executes the query "updateContractBenefitRuleToApproveStatus" by benefitRuleName "QAAutomationBenefitRule"  for update benefit rule status to approved by contractID
    Then User executes the query "getContractIdByContractName" by contract name "QAAutomation_10" to get contractID to create rebate options
    Then User executes the query "getMFR_Program_IDByContractID" to get programID from contractID to create rebate options
    Then User executes the query "getBenefitRule" by BenefitRulename "QAAutomationBenefitRule" to get benefitRuleID to create rebate options
    Then User executes the query "getMfrDrugListIdByListType" to get rebateableDrugListID for create new rebate options
    Given User create new rebate options details
      | mfrRebateOptionName       | startDate  | endDate    | xvalue | yvalue | prodOrMfr | paAndSt | billingCycle | lineOfBusiness | benefitRuleName         | psfOptionLevel | pricingMethod | price | firstRule      | firstCondition | secondRule     | secondCondition | thirdRule | position    | rebateableDrugfirstTier | rebateableDrugcondition | rebateableDrugsecondTier | compFormularyPosition | compDrugTier | compRestrictionsfirstRule | compRestrictionsfirstCondition | compRestrictionssecondRule | compRestrictionssecondCondition | compRestrictionsthirdRule | compRestrictionsthirdCondition | compRestrictionsfourthRule | compRestrictionsfourthCondition | compRestrictionsfifthRule |
      | QAAutomationRebateOptions | 2021-08-01 | 2023-07-20 | 1      | 1      | Product   | true    | MON          | HiEx           | QAAutomationBenefitRule | 1              | PctOfWAC      | 1     | PA_NOT_ALLOWED | AND            | ST_NOT_ALLOWED |                 |           | OnFormulary |                         |                         |                          |                       |              |                           |                                |                            |                                 |                           |                                |                            |                                 |                           |
    Then User hits the "createNewRebateOptionsSaveProgress" with post request of CreateNewRebateOptions save progress API
    Then User verify createNewRebateOptionsSaveProgress status code "200" for the response
    Then User verify the valid createNewRebateOptionsSaveProgress Response body key "isRecordSaved" and expected value "false"


  @Functional @Regression
  Scenario: Validate the Admin fee must be same as programLevelPsf and verify correct status code with response body is returned
    Then User executes the query "deleteContractHeaderByContractName" and "deleteContractDetailByAmendmentName" for contract & Amendment name "QAAutomation%" to delete the record from the database
    Then User executes "deleteRebateProgram" query to delete existing rebate program by Contract_ID "ASTRA%" from the database
    Then User executes the query "deleteBenefitRule" by benefitRuleName "QAAutomation%" for delete benefit rule by contractID
    Then User executes the query "deleteRebateOption" by RebateOptionName "QAAutomation%" for deleting existing rebate option record from DB
    Given User create the Initiate New Manufacture Contract date
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-08-01 | 2022-07-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 01 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | CPOS           | All       | MON          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
    Then User hits the "postInitiateNewManufacturerContract" with post request
    Then User verify InitiateNewManufacturerContract status code "200" for the response
    Then User verify the valid Response body key "recordInserted" and expected value "true"
    Then User update manufacture contract data
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-08-01 | 2022-07-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 02 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | HiEx           | All       | YRL          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
    Then User hits the "updateManufacturerContract" update manufacture contract post request
    Then User verify UpdateManufacturerContract status code "200" for the response
    Then User verify the updateManufacturerContract valid Response body key "recordUpdated" and expected value "true"
    Then User executes the query "updateContractHeaderStatusFromNewToInProgress" and "updateContractDetailsStatusFromNewToInProgress" for contract & Amendment name "QAAutomation_10" to update the contract record status from New to InProgress from the database
    Given User create & sava new rebate program
      | contractName    | startDate  | endDate    | programName             | legacyContractID       | pricingBasis | pricingRefDate | psfApply | programLevelPsf | condition |
      | QAAutomation_10 | 2021-08-01 | 2022-07-20 | QAAutomationProgramName | QATestlegacyContractID | WAC          | 1ST_OF_QTR     | true     | 1               | All       |
    Then User hits the "createNewRebateProgram" with post request of CreateNewRebateProgram
    Then User verify createNewRebateProgram status code "200" for the response
    Then User verify the valid createNewRebateProgram Response body key "isRecordSaved" and expected value "true"
    Then User executes the query "getContractIdByContractName" by contract name "QAAutomation_10" to get contractID to create benefit rule
    And User create the Benefitruledata
      | benefitRuleName         | formularyType | aboveValue | belowValue |
      | QAAutomationBenefitRule | Incentive     | 1          | 1          |
    When User hits createBenefitRuleAPI "createBenefitRuleForAContract" with Post Request
    Then User executes the query "updateContractBenefitRuleToApproveStatus" by benefitRuleName "QAAutomationBenefitRule"  for update benefit rule status to approved by contractID
    Then User executes the query "getContractIdByContractName" by contract name "QAAutomation_10" to get contractID to create rebate options
    Then User executes the query "getMFR_Program_IDByContractID" to get programID from contractID to create rebate options
    Then User executes the query "getBenefitRule" by BenefitRulename "QAAutomationBenefitRule" to get benefitRuleID to create rebate options
    Then User executes the query "getMfrDrugListIdByListType" to get rebateableDrugListID for create new rebate options
    Given User create new rebate options details
      | mfrRebateOptionName       | startDate  | endDate    | xvalue | yvalue | prodOrMfr | paAndSt | billingCycle | lineOfBusiness | benefitRuleName         | psfOptionLevel | pricingMethod | price | firstRule      | firstCondition | secondRule     | secondCondition | thirdRule | position    | rebateableDrugfirstTier | rebateableDrugcondition | rebateableDrugsecondTier | compFormularyPosition | compDrugTier | compRestrictionsfirstRule | compRestrictionsfirstCondition | compRestrictionssecondRule | compRestrictionssecondCondition | compRestrictionsthirdRule | compRestrictionsthirdCondition | compRestrictionsfourthRule | compRestrictionsfourthCondition | compRestrictionsfifthRule |
      | QAAutomationRebateOptions | 2021-08-01 | 2022-07-20 | 1      | 1      | Product   | true    | MON          | HiEx           | QAAutomationBenefitRule | 10              | PctOfWAC      | 1     | PA_NOT_ALLOWED | AND            | ST_NOT_ALLOWED |                 |           | OnFormulary |                         |                         |                          |                       |              |                           |                                |                            |                                 |                           |                                |                            |                                 |                           |
    Then User hits the "createNewRebateOptionsSaveProgress" with post request of CreateNewRebateOptions save progress API
    Then User verify createNewRebateOptionsSaveProgress status code "200" for the response
    Then User verify the createNewRebateOptionsSaveProgress valid Response body key "message" and expected value "Admin fee must be same as programLevelPsf" of string


  @Functional @Regression
  Scenario: Validate the No program exist with program id and verify correct status code with response body is returned
    Then User executes the query "deleteContractHeaderByContractName" and "deleteContractDetailByAmendmentName" for contract & Amendment name "QAAutomation%" to delete the record from the database
    Then User executes "deleteRebateProgram" query to delete existing rebate program by Contract_ID "ASTRA%" from the database
    Then User executes the query "deleteBenefitRule" by benefitRuleName "QAAutomation%" for delete benefit rule by contractID
    Then User executes the query "deleteRebateOption" by RebateOptionName "QAAutomation%" for deleting existing rebate option record from DB
    Given User create the Initiate New Manufacture Contract date
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-08-01 | 2022-07-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 01 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | CPOS           | All       | MON          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
    Then User hits the "postInitiateNewManufacturerContract" with post request
    Then User verify InitiateNewManufacturerContract status code "200" for the response
    Then User verify the valid Response body key "recordInserted" and expected value "true"
    Then User update manufacture contract data
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-08-01 | 2022-07-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 02 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | HiEx           | All       | YRL          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
    Then User hits the "updateManufacturerContract" update manufacture contract post request
    Then User verify UpdateManufacturerContract status code "200" for the response
    Then User verify the updateManufacturerContract valid Response body key "recordUpdated" and expected value "true"
    Then User executes the query "updateContractHeaderStatusFromNewToInProgress" and "updateContractDetailsStatusFromNewToInProgress" for contract & Amendment name "QAAutomation_10" to update the contract record status from New to InProgress from the database
    Given User create & sava new rebate program
      | contractName    | startDate  | endDate    | programName             | legacyContractID       | pricingBasis | pricingRefDate | psfApply | programLevelPsf | condition |
      | QAAutomation_10 | 2021-08-01 | 2022-07-20 | QAAutomationProgramName | QATestlegacyContractID | WAC          | 1ST_OF_QTR     | true     | 1               | All       |
    Then User hits the "createNewRebateProgram" with post request of CreateNewRebateProgram
    Then User verify createNewRebateProgram status code "200" for the response
    Then User verify the valid createNewRebateProgram Response body key "isRecordSaved" and expected value "true"
    Then User executes the query "getContractIdByContractName" by contract name "QAAutomation_10" to get contractID to create benefit rule
    And User create the Benefitruledata
      | benefitRuleName         | formularyType | aboveValue | belowValue |
      | QAAutomationBenefitRule | Incentive     | 1          | 1          |
    When User hits createBenefitRuleAPI "createBenefitRuleForAContract" with Post Request
    Then User executes the query "updateContractBenefitRuleToApproveStatus" by benefitRuleName "QAAutomationBenefitRule"  for update benefit rule status to approved by contractID
    Then User executes the query "getContractIdByContractName" by contract name "QAAutomation_10" to get contractID to create rebate options
    Then User send the invalid Program ID "2222333355" for validation
    Then User executes the query "getBenefitRule" by BenefitRulename "QAAutomationBenefitRule" to get benefitRuleID to create rebate options
    Then User executes the query "getMfrDrugListIdByListType" to get rebateableDrugListID for create new rebate options
    Given User create new rebate options details
      | mfrRebateOptionName       | startDate  | endDate    | xvalue | yvalue | prodOrMfr | paAndSt | billingCycle | lineOfBusiness | benefitRuleName         | psfOptionLevel | pricingMethod | price | firstRule      | firstCondition | secondRule     | secondCondition | thirdRule | position    | rebateableDrugfirstTier | rebateableDrugcondition | rebateableDrugsecondTier | compFormularyPosition | compDrugTier | compRestrictionsfirstRule | compRestrictionsfirstCondition | compRestrictionssecondRule | compRestrictionssecondCondition | compRestrictionsthirdRule | compRestrictionsthirdCondition | compRestrictionsfourthRule | compRestrictionsfourthCondition | compRestrictionsfifthRule |
      | QAAutomationRebateOptions | 2021-08-01 | 2022-07-20 | 1      | 1      | Product   | true    | MON          | HiEx           | QAAutomationBenefitRule | 10              | PctOfWAC      | 1     | PA_NOT_ALLOWED | AND            | ST_NOT_ALLOWED |                 |           | OnFormulary |                         |                         |                          |                       |              |                           |                                |                            |                                 |                           |                                |                            |                                 |                           |
    Then User hits the "createNewRebateOptionsSaveProgress" with post request of CreateNewRebateOptions save progress API
    Then User verify createNewRebateOptionsSaveProgress status code "200" for the response
    Then User verify the createNewRebateOptionsSaveProgress valid Response body key "message" and expected value "No program exist with program id -2072633941" of string


  @Functional @Regression
  Scenario: Validate the create new rebate options End date is less than the start date and verify correct status code with response body is returned
    Then User executes the query "deleteContractHeaderByContractName" and "deleteContractDetailByAmendmentName" for contract & Amendment name "QAAutomation%" to delete the record from the database
    Then User executes "deleteRebateProgram" query to delete existing rebate program by Contract_ID "ASTRA%" from the database
    Then User executes the query "deleteBenefitRule" by benefitRuleName "QAAutomation%" for delete benefit rule by contractID
    Then User executes the query "deleteRebateOption" by RebateOptionName "QAAutomation%" for deleting existing rebate option record from DB
    Given User create the Initiate New Manufacture Contract date
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-08-01 | 2022-07-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 01 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | CPOS           | All       | MON          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
    Then User hits the "postInitiateNewManufacturerContract" with post request
    Then User verify InitiateNewManufacturerContract status code "200" for the response
    Then User verify the valid Response body key "recordInserted" and expected value "true"
    Then User update manufacture contract data
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-08-01 | 2022-07-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 02 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | HiEx           | All       | YRL          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
    Then User hits the "updateManufacturerContract" update manufacture contract post request
    Then User verify UpdateManufacturerContract status code "200" for the response
    Then User verify the updateManufacturerContract valid Response body key "recordUpdated" and expected value "true"
    Then User executes the query "updateContractHeaderStatusFromNewToInProgress" and "updateContractDetailsStatusFromNewToInProgress" for contract & Amendment name "QAAutomation_10" to update the contract record status from New to InProgress from the database
    Given User create & sava new rebate program
      | contractName    | startDate  | endDate    | programName             | legacyContractID       | pricingBasis | pricingRefDate | psfApply | programLevelPsf | condition |
      | QAAutomation_10 | 2021-08-01 | 2022-07-20 | QAAutomationProgramName | QATestlegacyContractID | WAC          | 1ST_OF_QTR     | true     | 1               | All       |
    Then User hits the "createNewRebateProgram" with post request of CreateNewRebateProgram
    Then User verify createNewRebateProgram status code "200" for the response
    Then User verify the valid createNewRebateProgram Response body key "isRecordSaved" and expected value "true"
    Then User executes the query "getContractIdByContractName" by contract name "QAAutomation_10" to get contractID to create benefit rule
    And User create the Benefitruledata
      | benefitRuleName         | formularyType | aboveValue | belowValue |
      | QAAutomationBenefitRule | Incentive     | 1          | 1          |
    When User hits createBenefitRuleAPI "createBenefitRuleForAContract" with Post Request
    Then User executes the query "updateContractBenefitRuleToApproveStatus" by benefitRuleName "QAAutomationBenefitRule"  for update benefit rule status to approved by contractID
    Then User executes the query "getContractIdByContractName" by contract name "QAAutomation_10" to get contractID to create rebate options
    Then User executes the query "getMFR_Program_IDByContractID" to get programID from contractID to create rebate options
    Then User executes the query "getBenefitRule" by BenefitRulename "QAAutomationBenefitRule" to get benefitRuleID to create rebate options
    Then User executes the query "getMfrDrugListIdByListType" to get rebateableDrugListID for create new rebate options
    Given User create new rebate options details
      | mfrRebateOptionName       | startDate  | endDate    | xvalue | yvalue | prodOrMfr | paAndSt | billingCycle | lineOfBusiness | benefitRuleName         | psfOptionLevel | pricingMethod | price | firstRule      | firstCondition | secondRule     | secondCondition | thirdRule | position    | rebateableDrugfirstTier | rebateableDrugcondition | rebateableDrugsecondTier | compFormularyPosition | compDrugTier | compRestrictionsfirstRule | compRestrictionsfirstCondition | compRestrictionssecondRule | compRestrictionssecondCondition | compRestrictionsthirdRule | compRestrictionsthirdCondition | compRestrictionsfourthRule | compRestrictionsfourthCondition | compRestrictionsfifthRule |
      | QAAutomationRebateOptions | 2022-08-01 | 2021-07-20 | 1      | 1      | Product   | true    | MON          | HiEx           | QAAutomationBenefitRule | 1              | PctOfWAC      | 1     | PA_NOT_ALLOWED | AND            | ST_NOT_ALLOWED |                 |           | OnFormulary |                         |                         |                          |                       |              |                           |                                |                            |                                 |                           |                                |                            |                                 |                           |
    Then User hits the "createNewRebateOptionsSaveProgress" with post request of CreateNewRebateOptions save progress API
    Then User verify createNewRebateOptionsSaveProgress status code "200" for the response
    Then User verify the createNewRebateOptionsSaveProgress valid Response body key "message" and expected value "Rebate option start date should not be after end date" of string

  @Functional @Regression
  Scenario: Validate the invalid LOB for creating new rebate option save progress and verify correct status code with response body is returned
    Then User executes the query "deleteContractHeaderByContractName" and "deleteContractDetailByAmendmentName" for contract & Amendment name "QAAutomation%" to delete the record from the database
    Then User executes "deleteRebateProgram" query to delete existing rebate program by Contract_ID "ASTRA%" from the database
    Then User executes the query "deleteBenefitRule" by benefitRuleName "QAAutomation%" for delete benefit rule by contractID
    Then User executes the query "deleteRebateOption" by RebateOptionName "QAAutomation%" for deleting existing rebate option record from DB
    Given User create the Initiate New Manufacture Contract date
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-08-01 | 2022-07-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 01 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | CPOS           | All       | MON          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
    Then User hits the "postInitiateNewManufacturerContract" with post request
    Then User verify InitiateNewManufacturerContract status code "200" for the response
    Then User verify the valid Response body key "recordInserted" and expected value "true"
    Then User update manufacture contract data
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-08-01 | 2022-07-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 02 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | HiEx           | All       | YRL          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
    Then User hits the "updateManufacturerContract" update manufacture contract post request
    Then User verify UpdateManufacturerContract status code "200" for the response
    Then User verify the updateManufacturerContract valid Response body key "recordUpdated" and expected value "true"
    Then User executes the query "updateContractHeaderStatusFromNewToInProgress" and "updateContractDetailsStatusFromNewToInProgress" for contract & Amendment name "QAAutomation_10" to update the contract record status from New to InProgress from the database
    Given User create & sava new rebate program
      | contractName    | startDate  | endDate    | programName             | legacyContractID       | pricingBasis | pricingRefDate | psfApply | programLevelPsf | condition |
      | QAAutomation_10 | 2021-08-01 | 2022-07-20 | QAAutomationProgramName | QATestlegacyContractID | WAC          | 1ST_OF_QTR     | true     | 1               | All       |
    Then User hits the "createNewRebateProgram" with post request of CreateNewRebateProgram
    Then User verify createNewRebateProgram status code "200" for the response
    Then User verify the valid createNewRebateProgram Response body key "isRecordSaved" and expected value "true"
    Then User executes the query "getContractIdByContractName" by contract name "QAAutomation_10" to get contractID to create benefit rule
    And User create the Benefitruledata
      | benefitRuleName         | formularyType | aboveValue | belowValue |
      | QAAutomationBenefitRule | Incentive     | 1          | 1          |
    When User hits createBenefitRuleAPI "createBenefitRuleForAContract" with Post Request
    Then User executes the query "updateContractBenefitRuleToApproveStatus" by benefitRuleName "QAAutomationBenefitRule"  for update benefit rule status to approved by contractID
    Then User executes the query "getContractIdByContractName" by contract name "QAAutomation_10" to get contractID to create rebate options
    Then User executes the query "getMFR_Program_IDByContractID" to get programID from contractID to create rebate options
    Then User executes the query "getBenefitRule" by BenefitRulename "QAAutomationBenefitRule" to get benefitRuleID to create rebate options
    Then User executes the query "getMfrDrugListIdByListType" to get rebateableDrugListID for create new rebate options
    Given User create new rebate options details
      | mfrRebateOptionName       | startDate  | endDate    | xvalue | yvalue | prodOrMfr | paAndSt | billingCycle | lineOfBusiness | benefitRuleName         | psfOptionLevel | pricingMethod | price | firstRule      | firstCondition | secondRule     | secondCondition | thirdRule | position    | rebateableDrugfirstTier | rebateableDrugcondition | rebateableDrugsecondTier | compFormularyPosition | compDrugTier | compRestrictionsfirstRule | compRestrictionsfirstCondition | compRestrictionssecondRule | compRestrictionssecondCondition | compRestrictionsthirdRule | compRestrictionsthirdCondition | compRestrictionsfourthRule | compRestrictionsfourthCondition | compRestrictionsfifthRule |
      | QAAutomationRebateOptions | 2021-08-01 | 2022-07-20 | 1      | 1      | Product   | true    | MON          | MMP            | QAAutomationBenefitRule | 1              | PctOfWAC      | 1     | PA_NOT_ALLOWED | AND            | ST_NOT_ALLOWED |                 |           | OnFormulary |                         |                         |                          |                       |              |                           |                                |                            |                                 |                           |                                |                            |                                 |                           |
    Then User hits the "createNewRebateOptionsSaveProgress" with post request of CreateNewRebateOptions save progress API
    Then User verify createNewRebateOptionsSaveProgress status code "200" for the response
    Then User verify the createNewRebateOptionsSaveProgress valid Response body key "message" and expected value "Invalid Line Of Business : [MMP]" of string


  @Functional @Regression
  Scenario: Verify the Invalid xvalue
    Then User executes the query "deleteContractHeaderByContractName" and "deleteContractDetailByAmendmentName" for contract & Amendment name "QAAutomation%" to delete the record from the database
    Then User executes "deleteRebateProgram" query to delete existing rebate program by Contract_ID "ASTRA%" from the database
    Then User executes the query "deleteBenefitRule" by benefitRuleName "QAAutomation%" for delete benefit rule by contractID
    Then User executes the query "deleteRebateOption" by RebateOptionName "QAAutomation%" for deleting existing rebate option record from DB
    Given User create the Initiate New Manufacture Contract date
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-08-01 | 2022-07-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 01 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | CPOS           | All       | MON          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
    Then User hits the "postInitiateNewManufacturerContract" with post request
    Then User verify InitiateNewManufacturerContract status code "200" for the response
    Then User verify the valid Response body key "recordInserted" and expected value "true"
    Then User update manufacture contract data
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-08-01 | 2022-07-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 02 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | HiEx           | All       | YRL          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
    Then User hits the "updateManufacturerContract" update manufacture contract post request
    Then User verify UpdateManufacturerContract status code "200" for the response
    Then User verify the updateManufacturerContract valid Response body key "recordUpdated" and expected value "true"
    Then User executes the query "updateContractHeaderStatusFromNewToInProgress" and "updateContractDetailsStatusFromNewToInProgress" for contract & Amendment name "QAAutomation_10" to update the contract record status from New to InProgress from the database
    Given User create & sava new rebate program
      | contractName    | startDate  | endDate    | programName             | legacyContractID       | pricingBasis | pricingRefDate | psfApply | programLevelPsf | condition |
      | QAAutomation_10 | 2021-08-01 | 2022-07-20 | QAAutomationProgramName | QATestlegacyContractID | WAC          | 1ST_OF_QTR     | true     | 1               | All       |
    Then User hits the "createNewRebateProgram" with post request of CreateNewRebateProgram
    Then User verify createNewRebateProgram status code "200" for the response
    Then User verify the valid createNewRebateProgram Response body key "isRecordSaved" and expected value "true"
    Then User executes the query "getContractIdByContractName" by contract name "QAAutomation_10" to get contractID to create benefit rule
    And User create the Benefitruledata
      | benefitRuleName         | formularyType | aboveValue | belowValue |
      | QAAutomationBenefitRule | Incentive     | 1          | 1          |
    When User hits createBenefitRuleAPI "createBenefitRuleForAContract" with Post Request
    Then User executes the query "updateContractBenefitRuleToApproveStatus" by benefitRuleName "QAAutomationBenefitRule"  for update benefit rule status to approved by contractID
    Then User executes the query "getContractIdByContractName" by contract name "QAAutomation_10" to get contractID to create rebate options
    Then User executes the query "getMFR_Program_IDByContractID" to get programID from contractID to create rebate options
    Then User executes the query "getBenefitRule" by BenefitRulename "QAAutomationBenefitRule" to get benefitRuleID to create rebate options
    Then User executes the query "getMfrDrugListIdByListType" to get rebateableDrugListID for create new rebate options
    Given User create new rebate options details
      | mfrRebateOptionName       | startDate  | endDate    | xvalue | yvalue | prodOrMfr | paAndSt | billingCycle | lineOfBusiness | benefitRuleName         | psfOptionLevel | pricingMethod | price | firstRule      | firstCondition | secondRule     | secondCondition | thirdRule | position    | rebateableDrugfirstTier | rebateableDrugcondition | rebateableDrugsecondTier | compFormularyPosition | compDrugTier | compRestrictionsfirstRule | compRestrictionsfirstCondition | compRestrictionssecondRule | compRestrictionssecondCondition | compRestrictionsthirdRule | compRestrictionsthirdCondition | compRestrictionsfourthRule | compRestrictionsfourthCondition | compRestrictionsfifthRule |
      | QAAutomationRebateOptions | 2021-08-01 | 2022-07-20 | 103    | 1      | Product   | true    | MON          | HiEx           | QAAutomationBenefitRule | 1              | PctOfWAC      | 1     | PA_NOT_ALLOWED | AND            | ST_NOT_ALLOWED |                 |           | OnFormulary |                         |                         |                          |                       |              |                           |                                |                            |                                 |                           |                                |                            |                                 |                           |
    Then User hits the "createNewRebateOptionsSaveProgress" with post request of CreateNewRebateOptions save progress API
    Then User verify createNewRebateOptionsSaveProgress status code "200" for the response
    Then User verify the createNewRebateOptionsSaveProgress valid Response body key "message" and expected value "Invalid xValue : 103" of string


  @Functional @Regression
  Scenario: Verify the Invalid yvalue
    Then User executes the query "deleteContractHeaderByContractName" and "deleteContractDetailByAmendmentName" for contract & Amendment name "QAAutomation%" to delete the record from the database
    Then User executes "deleteRebateProgram" query to delete existing rebate program by Contract_ID "ASTRA%" from the database
    Then User executes the query "deleteBenefitRule" by benefitRuleName "QAAutomation%" for delete benefit rule by contractID
    Then User executes the query "deleteRebateOption" by RebateOptionName "QAAutomation%" for deleting existing rebate option record from DB
    Given User create the Initiate New Manufacture Contract date
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-08-01 | 2022-07-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 01 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | CPOS           | All       | MON          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
    Then User hits the "postInitiateNewManufacturerContract" with post request
    Then User verify InitiateNewManufacturerContract status code "200" for the response
    Then User verify the valid Response body key "recordInserted" and expected value "true"
    Then User update manufacture contract data
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-08-01 | 2022-07-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 02 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | HiEx           | All       | YRL          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
    Then User hits the "updateManufacturerContract" update manufacture contract post request
    Then User verify UpdateManufacturerContract status code "200" for the response
    Then User verify the updateManufacturerContract valid Response body key "recordUpdated" and expected value "true"
    Then User executes the query "updateContractHeaderStatusFromNewToInProgress" and "updateContractDetailsStatusFromNewToInProgress" for contract & Amendment name "QAAutomation_10" to update the contract record status from New to InProgress from the database
    Given User create & sava new rebate program
      | contractName    | startDate  | endDate    | programName             | legacyContractID       | pricingBasis | pricingRefDate | psfApply | programLevelPsf | condition |
      | QAAutomation_10 | 2021-08-01 | 2022-07-20 | QAAutomationProgramName | QATestlegacyContractID | WAC          | 1ST_OF_QTR     | true     | 1               | All       |
    Then User hits the "createNewRebateProgram" with post request of CreateNewRebateProgram
    Then User verify createNewRebateProgram status code "200" for the response
    Then User verify the valid createNewRebateProgram Response body key "isRecordSaved" and expected value "true"
    Then User executes the query "getContractIdByContractName" by contract name "QAAutomation_10" to get contractID to create benefit rule
    And User create the Benefitruledata
      | benefitRuleName         | formularyType | aboveValue | belowValue |
      | QAAutomationBenefitRule | Incentive     | 1          | 1          |
    When User hits createBenefitRuleAPI "createBenefitRuleForAContract" with Post Request
    Then User executes the query "updateContractBenefitRuleToApproveStatus" by benefitRuleName "QAAutomationBenefitRule"  for update benefit rule status to approved by contractID
    Then User executes the query "getContractIdByContractName" by contract name "QAAutomation_10" to get contractID to create rebate options
    Then User executes the query "getMFR_Program_IDByContractID" to get programID from contractID to create rebate options
    Then User executes the query "getBenefitRule" by BenefitRulename "QAAutomationBenefitRule" to get benefitRuleID to create rebate options
    Then User executes the query "getMfrDrugListIdByListType" to get rebateableDrugListID for create new rebate options
    Given User create new rebate options details
      | mfrRebateOptionName       | startDate  | endDate    | xvalue | yvalue | prodOrMfr | paAndSt | billingCycle | lineOfBusiness | benefitRuleName         | psfOptionLevel | pricingMethod | price | firstRule      | firstCondition | secondRule     | secondCondition | thirdRule | position    | rebateableDrugfirstTier | rebateableDrugcondition | rebateableDrugsecondTier | compFormularyPosition | compDrugTier | compRestrictionsfirstRule | compRestrictionsfirstCondition | compRestrictionssecondRule | compRestrictionssecondCondition | compRestrictionsthirdRule | compRestrictionsthirdCondition | compRestrictionsfourthRule | compRestrictionsfourthCondition | compRestrictionsfifthRule |
      | QAAutomationRebateOptions | 2021-08-01 | 2022-07-20 | 1      | Z      | Product   | true    | MON          | HiEx           | QAAutomationBenefitRule | 1              | PctOfWAC      | 1     | PA_NOT_ALLOWED | AND            | ST_NOT_ALLOWED |                 |           | OnFormulary |                         |                         |                          |                       |              |                           |                                |                            |                                 |                           |                                |                            |                                 |                           |
    Then User hits the "createNewRebateOptionsSaveProgress" with post request of CreateNewRebateOptions save progress API
    Then User verify createNewRebateOptionsSaveProgress status code "200" for the response
    Then User verify the createNewRebateOptionsSaveProgress valid Response body key "message" and expected value "Invalid yValue : Z" of string


  @Functional @Regression
  Scenario: Verify the Invalid prodOrMfr
    Then User executes the query "deleteContractHeaderByContractName" and "deleteContractDetailByAmendmentName" for contract & Amendment name "QAAutomation%" to delete the record from the database
    Then User executes "deleteRebateProgram" query to delete existing rebate program by Contract_ID "ASTRA%" from the database
    Then User executes the query "deleteBenefitRule" by benefitRuleName "QAAutomation%" for delete benefit rule by contractID
    Then User executes the query "deleteRebateOption" by RebateOptionName "QAAutomation%" for deleting existing rebate option record from DB
    Given User create the Initiate New Manufacture Contract date
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-08-01 | 2022-07-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 01 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | CPOS           | All       | MON          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
    Then User hits the "postInitiateNewManufacturerContract" with post request
    Then User verify InitiateNewManufacturerContract status code "200" for the response
    Then User verify the valid Response body key "recordInserted" and expected value "true"
    Then User update manufacture contract data
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-08-01 | 2022-07-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 02 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | HiEx           | All       | YRL          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
    Then User hits the "updateManufacturerContract" update manufacture contract post request
    Then User verify UpdateManufacturerContract status code "200" for the response
    Then User verify the updateManufacturerContract valid Response body key "recordUpdated" and expected value "true"
    Then User executes the query "updateContractHeaderStatusFromNewToInProgress" and "updateContractDetailsStatusFromNewToInProgress" for contract & Amendment name "QAAutomation_10" to update the contract record status from New to InProgress from the database
    Given User create & sava new rebate program
      | contractName    | startDate  | endDate    | programName             | legacyContractID       | pricingBasis | pricingRefDate | psfApply | programLevelPsf | condition |
      | QAAutomation_10 | 2021-08-01 | 2022-07-20 | QAAutomationProgramName | QATestlegacyContractID | WAC          | 1ST_OF_QTR     | true     | 1               | All       |
    Then User hits the "createNewRebateProgram" with post request of CreateNewRebateProgram
    Then User verify createNewRebateProgram status code "200" for the response
    Then User verify the valid createNewRebateProgram Response body key "isRecordSaved" and expected value "true"
    Then User executes the query "getContractIdByContractName" by contract name "QAAutomation_10" to get contractID to create benefit rule
    And User create the Benefitruledata
      | benefitRuleName         | formularyType | aboveValue | belowValue |
      | QAAutomationBenefitRule | Incentive     | 1          | 1          |
    When User hits createBenefitRuleAPI "createBenefitRuleForAContract" with Post Request
    Then User executes the query "updateContractBenefitRuleToApproveStatus" by benefitRuleName "QAAutomationBenefitRule"  for update benefit rule status to approved by contractID
    Then User executes the query "getContractIdByContractName" by contract name "QAAutomation_10" to get contractID to create rebate options
    Then User executes the query "getMFR_Program_IDByContractID" to get programID from contractID to create rebate options
    Then User executes the query "getBenefitRule" by BenefitRulename "QAAutomationBenefitRule" to get benefitRuleID to create rebate options
    Then User executes the query "getMfrDrugListIdByListType" to get rebateableDrugListID for create new rebate options
    Given User create new rebate options details
      | mfrRebateOptionName       | startDate  | endDate    | xvalue | yvalue | prodOrMfr | paAndSt | billingCycle | lineOfBusiness | benefitRuleName         | psfOptionLevel | pricingMethod | price | firstRule      | firstCondition | secondRule     | secondCondition | thirdRule | position    | rebateableDrugfirstTier | rebateableDrugcondition | rebateableDrugsecondTier | compFormularyPosition | compDrugTier | compRestrictionsfirstRule | compRestrictionsfirstCondition | compRestrictionssecondRule | compRestrictionssecondCondition | compRestrictionsthirdRule | compRestrictionsthirdCondition | compRestrictionsfourthRule | compRestrictionsfourthCondition | compRestrictionsfifthRule |
      | QAAutomationRebateOptions | 2021-08-01 | 2022-07-20 | 1      | 1      | Test      | true    | MON          | HiEx           | QAAutomationBenefitRule | 1              | PctOfWAC      | 1     | PA_NOT_ALLOWED | AND            | ST_NOT_ALLOWED |                 |           | OnFormulary |                         |                         |                          |                       |              |                           |                                |                            |                                 |                           |                                |                            |                                 |                           |
    Then User hits the "createNewRebateOptionsSaveProgress" with post request of CreateNewRebateOptions save progress API
    Then User verify createNewRebateOptionsSaveProgress status code "200" for the response
    Then User verify the valid createNewRebateOptionsSaveProgress Response body key "isRecordSaved" and expected value "false"


  @Functional @Regression
  Scenario: verify the Invalid billingCycle
    Then User executes the query "deleteContractHeaderByContractName" and "deleteContractDetailByAmendmentName" for contract & Amendment name "QAAutomation%" to delete the record from the database
    Then User executes "deleteRebateProgram" query to delete existing rebate program by Contract_ID "ASTRA%" from the database
    Then User executes the query "deleteBenefitRule" by benefitRuleName "QAAutomation%" for delete benefit rule by contractID
    Then User executes the query "deleteRebateOption" by RebateOptionName "QAAutomation%" for deleting existing rebate option record from DB
    Given User create the Initiate New Manufacture Contract date
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-08-01 | 2022-07-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 01 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | CPOS           | All       | MON          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
    Then User hits the "postInitiateNewManufacturerContract" with post request
    Then User verify InitiateNewManufacturerContract status code "200" for the response
    Then User verify the valid Response body key "recordInserted" and expected value "true"
    Then User update manufacture contract data
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-08-01 | 2022-07-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 02 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | HiEx           | All       | YRL          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
    Then User hits the "updateManufacturerContract" update manufacture contract post request
    Then User verify UpdateManufacturerContract status code "200" for the response
    Then User verify the updateManufacturerContract valid Response body key "recordUpdated" and expected value "true"
    Then User executes the query "updateContractHeaderStatusFromNewToInProgress" and "updateContractDetailsStatusFromNewToInProgress" for contract & Amendment name "QAAutomation_10" to update the contract record status from New to InProgress from the database
    Given User create & sava new rebate program
      | contractName    | startDate  | endDate    | programName             | legacyContractID       | pricingBasis | pricingRefDate | psfApply | programLevelPsf | condition |
      | QAAutomation_10 | 2021-08-01 | 2022-07-20 | QAAutomationProgramName | QATestlegacyContractID | WAC          | 1ST_OF_QTR     | true     | 1               | All       |
    Then User hits the "createNewRebateProgram" with post request of CreateNewRebateProgram
    Then User verify createNewRebateProgram status code "200" for the response
    Then User verify the valid createNewRebateProgram Response body key "isRecordSaved" and expected value "true"
    Then User executes the query "getContractIdByContractName" by contract name "QAAutomation_10" to get contractID to create benefit rule
    And User create the Benefitruledata
      | benefitRuleName         | formularyType | aboveValue | belowValue |
      | QAAutomationBenefitRule | Incentive     | 1          | 1          |
    When User hits createBenefitRuleAPI "createBenefitRuleForAContract" with Post Request
    Then User executes the query "updateContractBenefitRuleToApproveStatus" by benefitRuleName "QAAutomationBenefitRule"  for update benefit rule status to approved by contractID
    Then User executes the query "getContractIdByContractName" by contract name "QAAutomation_10" to get contractID to create rebate options
    Then User executes the query "getMFR_Program_IDByContractID" to get programID from contractID to create rebate options
    Then User executes the query "getBenefitRule" by BenefitRulename "QAAutomationBenefitRule" to get benefitRuleID to create rebate options
    Then User executes the query "getMfrDrugListIdByListType" to get rebateableDrugListID for create new rebate options
    Given User create new rebate options details
      | mfrRebateOptionName       | startDate  | endDate    | xvalue | yvalue | prodOrMfr | paAndSt | billingCycle | lineOfBusiness | benefitRuleName         | psfOptionLevel | pricingMethod | price | firstRule      | firstCondition | secondRule     | secondCondition | thirdRule | position    | rebateableDrugfirstTier | rebateableDrugcondition | rebateableDrugsecondTier | compFormularyPosition | compDrugTier | compRestrictionsfirstRule | compRestrictionsfirstCondition | compRestrictionssecondRule | compRestrictionssecondCondition | compRestrictionsthirdRule | compRestrictionsthirdCondition | compRestrictionsfourthRule | compRestrictionsfourthCondition | compRestrictionsfifthRule |
      | QAAutomationRebateOptions | 2021-08-01 | 2022-07-20 | 1      | 1      | Product   | true    | Test         | HiEx           | QAAutomationBenefitRule | 1              | PctOfWAC      | 1     | PA_NOT_ALLOWED | AND            | ST_NOT_ALLOWED |                 |           | OnFormulary |                         |                         |                          |                       |              |                           |                                |                            |                                 |                           |                                |                            |                                 |                           |
    Then User hits the "createNewRebateOptionsSaveProgress" with post request of CreateNewRebateOptions save progress API
    Then User verify createNewRebateOptionsSaveProgress status code "200" for the response
    Then User verify the createNewRebateOptionsSaveProgress valid Response body key "message" and expected value "Invalid Billing cycle : Test" of string


  @Functional @Regression
  Scenario: Verify the Invalid pricingMethod
    Then User executes the query "deleteContractHeaderByContractName" and "deleteContractDetailByAmendmentName" for contract & Amendment name "QAAutomation%" to delete the record from the database
    Then User executes "deleteRebateProgram" query to delete existing rebate program by Contract_ID "ASTRA%" from the database
    Then User executes the query "deleteBenefitRule" by benefitRuleName "QAAutomation%" for delete benefit rule by contractID
    Then User executes the query "deleteRebateOption" by RebateOptionName "QAAutomation%" for deleting existing rebate option record from DB
    Given User create the Initiate New Manufacture Contract date
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-08-01 | 2022-07-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 01 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | CPOS           | All       | MON          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
    Then User hits the "postInitiateNewManufacturerContract" with post request
    Then User verify InitiateNewManufacturerContract status code "200" for the response
    Then User verify the valid Response body key "recordInserted" and expected value "true"
    Then User update manufacture contract data
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-08-01 | 2022-07-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 02 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | HiEx           | All       | YRL          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
    Then User hits the "updateManufacturerContract" update manufacture contract post request
    Then User verify UpdateManufacturerContract status code "200" for the response
    Then User verify the updateManufacturerContract valid Response body key "recordUpdated" and expected value "true"
    Then User executes the query "updateContractHeaderStatusFromNewToInProgress" and "updateContractDetailsStatusFromNewToInProgress" for contract & Amendment name "QAAutomation_10" to update the contract record status from New to InProgress from the database
    Given User create & sava new rebate program
      | contractName    | startDate  | endDate    | programName             | legacyContractID       | pricingBasis | pricingRefDate | psfApply | programLevelPsf | condition |
      | QAAutomation_10 | 2021-08-01 | 2022-07-20 | QAAutomationProgramName | QATestlegacyContractID | WAC          | 1ST_OF_QTR     | true     | 1               | All       |
    Then User hits the "createNewRebateProgram" with post request of CreateNewRebateProgram
    Then User verify createNewRebateProgram status code "200" for the response
    Then User verify the valid createNewRebateProgram Response body key "isRecordSaved" and expected value "true"
    Then User executes the query "getContractIdByContractName" by contract name "QAAutomation_10" to get contractID to create benefit rule
    And User create the Benefitruledata
      | benefitRuleName         | formularyType | aboveValue | belowValue |
      | QAAutomationBenefitRule | Incentive     | 1          | 1          |
    When User hits createBenefitRuleAPI "createBenefitRuleForAContract" with Post Request
    Then User executes the query "updateContractBenefitRuleToApproveStatus" by benefitRuleName "QAAutomationBenefitRule"  for update benefit rule status to approved by contractID
    Then User executes the query "getContractIdByContractName" by contract name "QAAutomation_10" to get contractID to create rebate options
    Then User executes the query "getMFR_Program_IDByContractID" to get programID from contractID to create rebate options
    Then User executes the query "getBenefitRule" by BenefitRulename "QAAutomationBenefitRule" to get benefitRuleID to create rebate options
    Then User executes the query "getMfrDrugListIdByListType" to get rebateableDrugListID for create new rebate options
    Given User create new rebate options details
      | mfrRebateOptionName       | startDate  | endDate    | xvalue | yvalue | prodOrMfr | paAndSt | billingCycle | lineOfBusiness | benefitRuleName         | psfOptionLevel | pricingMethod | price | firstRule      | firstCondition | secondRule     | secondCondition | thirdRule | position    | rebateableDrugfirstTier | rebateableDrugcondition | rebateableDrugsecondTier | compFormularyPosition | compDrugTier | compRestrictionsfirstRule | compRestrictionsfirstCondition | compRestrictionssecondRule | compRestrictionssecondCondition | compRestrictionsthirdRule | compRestrictionsthirdCondition | compRestrictionsfourthRule | compRestrictionsfourthCondition | compRestrictionsfifthRule |
      | QAAutomationRebateOptions | 2021-08-01 | 2022-07-20 | 1      | 1      | Product   | true    | MON          | HiEx           | QAAutomationBenefitRule | 1              | Test          | 1     | PA_NOT_ALLOWED | AND            | ST_NOT_ALLOWED |                 |           | OnFormulary |                         |                         |                          |                       |              |                           |                                |                            |                                 |                           |                                |                            |                                 |                           |
    Then User hits the "createNewRebateOptionsSaveProgress" with post request of CreateNewRebateOptions save progress API
    Then User verify createNewRebateOptionsSaveProgress status code "200" for the response
    Then User verify the createNewRebateOptionsSaveProgress valid Response body key "message" and expected value "Invalid dropdown option for PricingMethod value : Test" of string