Feature:Retrieve and validate the create new rebate program

  @Smoke
  Scenario: Validate the create new rebate program creation for manufacturer contract and verify correct status code with response body is returned
    Then User executes the query "deleteContractHeaderByContractName" and "deleteContractDetailByAmendmentName" for contract & Amendment name "QAAutomation%" to delete the record from the database
    Then User executes "deleteRebateProgram" query to delete existing rebate program by Contract_ID "ASTRA%" from the database
    Given User create the Initiate New Manufacture Contract date
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-05-01 | 2021-07-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 01 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | CPOS           | All       | MON          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
    Then User hits the "postInitiateNewManufacturerContract" with post request
    Then User verify InitiateNewManufacturerContract status code "200" for the response
    Then User verify the valid Response body key "recordInserted" and expected value "true"
    Then User update manufacture contract data
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-08-01 | 2021-09-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 02 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | HiEx           | All       | YRL          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
    Then User hits the "updateManufacturerContract" update manufacture contract post request
    Then User verify UpdateManufacturerContract status code "200" for the response
    Then User verify the updateManufacturerContract valid Response body key "recordUpdated" and expected value "true"
    Then User executes the query "updateContractHeaderStatusFromNewToInProgress" and "updateContractDetailsStatusFromNewToInProgress" for contract & Amendment name "QAAutomation_10" to update the contract record status from New to InProgress from the database
    Given User create & sava new rebate program
      | contractName    | startDate  | endDate    | programName       | legacyContractID       | pricingBasis | pricingRefDate | psfApply | programLevelPsf | condition |
      | QAAutomation_10 | 2021-08-02 | 2021-08-12 | QATestProgramName | QATestlegacyContractID | WAC          | 1ST_OF_QTR     | true     | 10.2            | All       |
    Then User hits the "createNewRebateProgram" with post request of CreateNewRebateProgram
    Then User verify createNewRebateProgram status code "200" for the response
    Then User verify the valid createNewRebateProgram Response body key "isRecordSaved" and expected value "true"

  @Functional
  Scenario: Validate the create new rebate program creation for manufacturer contract with exiting rebate program name and different with start & end date and verify correct status code with response body is returned
    Then User executes the query "deleteContractHeaderByContractName" and "deleteContractDetailByAmendmentName" for contract & Amendment name "QAAutomation%" to delete the record from the database
    Then User executes "deleteRebateProgram" query to delete existing rebate program by Contract_ID "ASTRA%" from the database
    Given User create the Initiate New Manufacture Contract date
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-05-01 | 2021-07-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 01 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | CPOS           | All       | MON          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
    Then User hits the "postInitiateNewManufacturerContract" with post request
    Then User verify InitiateNewManufacturerContract status code "200" for the response
    Then User verify the valid Response body key "recordInserted" and expected value "true"
    Then User update manufacture contract data
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-08-01 | 2021-09-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 02 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | HiEx           | All       | YRL          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
    Then User hits the "updateManufacturerContract" update manufacture contract post request
    Then User verify UpdateManufacturerContract status code "200" for the response
    Then User verify the updateManufacturerContract valid Response body key "recordUpdated" and expected value "true"
    Then User executes the query "updateContractHeaderStatusFromNewToInProgress" and "updateContractDetailsStatusFromNewToInProgress" for contract & Amendment name "QAAutomation_10" to update the contract record status from New to InProgress from the database
    Given User create & sava new rebate program
      | contractName    | startDate  | endDate    | programName       | legacyContractID       | pricingBasis | pricingRefDate | psfApply | programLevelPsf | condition |
      | QAAutomation_10 | 2021-08-02 | 2021-08-12 | QATestProgramName | QATestlegacyContractID | WAC          | 1ST_OF_QTR     | true     | 10.2            | All       |
    Then User hits the "createNewRebateProgram" with post request of CreateNewRebateProgram
    Then User verify createNewRebateProgram status code "200" for the response
    Then User verify the valid createNewRebateProgram Response body key "isRecordSaved" and expected value "true"
    Given User create & sava new rebate program
      | contractName    | startDate  | endDate    | programName       | legacyContractID       | pricingBasis | pricingRefDate | psfApply | programLevelPsf | condition |
      | QAAutomation_10 | 2021-08-14 | 2021-08-28 | QATestProgramName | QATestlegacyContractID | WAC          | 1ST_OF_QTR     | true     | 10.2            | All       |
    Then User hits the "createNewRebateProgram" with post request of CreateNewRebateProgram
    Then User verify createNewRebateProgram status code "200" for the response
    Then User verify the valid createNewRebateProgram Response body key "isRecordSaved" and expected value "true"

  @Functional
  Scenario: Validate the create new rebate program creation for manufacturer contract with different rebate program name and existing same start & end date and verify correct status code with response body is returned
    Then User executes the query "deleteContractHeaderByContractName" and "deleteContractDetailByAmendmentName" for contract & Amendment name "QAAutomation%" to delete the record from the database
    Then User executes "deleteRebateProgram" query to delete existing rebate program by Contract_ID "ASTRA%" from the database
    Given User create the Initiate New Manufacture Contract date
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-05-01 | 2021-07-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 01 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | CPOS           | All       | MON          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
    Then User hits the "postInitiateNewManufacturerContract" with post request
    Then User verify InitiateNewManufacturerContract status code "200" for the response
    Then User verify the valid Response body key "recordInserted" and expected value "true"
    Then User update manufacture contract data
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-08-01 | 2021-09-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 02 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | HiEx           | All       | YRL          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
    Then User hits the "updateManufacturerContract" update manufacture contract post request
    Then User verify UpdateManufacturerContract status code "200" for the response
    Then User verify the updateManufacturerContract valid Response body key "recordUpdated" and expected value "true"
    Then User executes the query "updateContractHeaderStatusFromNewToInProgress" and "updateContractDetailsStatusFromNewToInProgress" for contract & Amendment name "QAAutomation_10" to update the contract record status from New to InProgress from the database
    Given User create & sava new rebate program
      | contractName    | startDate  | endDate    | programName       | legacyContractID       | pricingBasis | pricingRefDate | psfApply | programLevelPsf | condition |
      | QAAutomation_10 | 2021-08-02 | 2021-08-12 | QATestProgramName | QATestlegacyContractID | WAC          | 1ST_OF_QTR     | true     | 10.2            | All       |
    Then User hits the "createNewRebateProgram" with post request of CreateNewRebateProgram
    Then User verify createNewRebateProgram status code "200" for the response
    Then User verify the valid createNewRebateProgram Response body key "isRecordSaved" and expected value "true"
    Given User create & sava new rebate program
      | contractName    | startDate  | endDate    | programName          | legacyContractID       | pricingBasis | pricingRefDate | psfApply | programLevelPsf | condition |
      | QAAutomation_10 | 2021-08-02 | 2021-08-12 | QATestProgramNameTwo | QATestlegacyContractID | WAC          | 1ST_OF_QTR     | true     | 10.2            | All       |
    Then User hits the "createNewRebateProgram" with post request of CreateNewRebateProgram
    Then User verify createNewRebateProgram status code "200" for the response
    Then User verify the valid createNewRebateProgram Response body key "isRecordSaved" and expected value "true"

  @Functional
  Scenario: Validate the overlapping create new rebate program creation for manufacturer contract with existing rebate program name and start & end date and verify correct status code with response body is returned
    Then User executes the query "deleteContractHeaderByContractName" and "deleteContractDetailByAmendmentName" for contract & Amendment name "QAAutomation%" to delete the record from the database
    Then User executes "deleteRebateProgram" query to delete existing rebate program by Contract_ID "ASTRA%" from the database
    Given User create the Initiate New Manufacture Contract date
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-05-01 | 2021-07-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 01 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | CPOS           | All       | MON          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
    Then User hits the "postInitiateNewManufacturerContract" with post request
    Then User verify InitiateNewManufacturerContract status code "200" for the response
    Then User verify the valid Response body key "recordInserted" and expected value "true"
    Then User update manufacture contract data
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-08-01 | 2021-09-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 02 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | HiEx           | All       | YRL          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
    Then User hits the "updateManufacturerContract" update manufacture contract post request
    Then User verify UpdateManufacturerContract status code "200" for the response
    Then User verify the updateManufacturerContract valid Response body key "recordUpdated" and expected value "true"
    Then User executes the query "updateContractHeaderStatusFromNewToInProgress" and "updateContractDetailsStatusFromNewToInProgress" for contract & Amendment name "QAAutomation_10" to update the contract record status from New to InProgress from the database
    Given User create & sava new rebate program
      | contractName    | startDate  | endDate    | programName       | legacyContractID       | pricingBasis | pricingRefDate | psfApply | programLevelPsf | condition |
      | QAAutomation_10 | 2021-08-02 | 2021-08-12 | QATestProgramName | QATestlegacyContractID | WAC          | 1ST_OF_QTR     | true     | 10.2            | ALL       |
    Then User hits the "createNewRebateProgram" with post request of CreateNewRebateProgram
    Then User verify createNewRebateProgram status code "200" for the response
    Then User verify the valid createNewRebateProgram Response body key "isRecordSaved" and expected value "true"
    Given User create & sava new rebate program
      | contractName    | startDate  | endDate    | programName       | legacyContractID       | pricingBasis | pricingRefDate | psfApply | programLevelPsf | condition |
      | QAAutomation_10 | 2021-08-02 | 2021-08-12 | QATestProgramName | QATestlegacyContractID | WAC          | 1ST_OF_QTR     | true     | 10.2            | All       |
    Then User hits the "createNewRebateProgram" with post request of CreateNewRebateProgram
    Then User verify createNewRebateProgram status code "200" for the response
    Then User verify the valid createNewRebateProgram Response body key "isRecordSaved" and expected value "false"

  @Functional
  Scenario: Validate Program can be added to only contracts which are in InProgress status and verify correct status code with response body is returned
    Then User executes the query "deleteContractHeaderByContractName" and "deleteContractDetailByAmendmentName" for contract & Amendment name "QAAutomation%" to delete the record from the database
    Then User executes "deleteRebateProgram" query to delete existing rebate program by Contract_ID "ASTRA%" from the database
    Given User create the Initiate New Manufacture Contract date
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-05-01 | 2021-07-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 01 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | CPOS           | All       | MON          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
    Then User hits the "postInitiateNewManufacturerContract" with post request
    Then User verify InitiateNewManufacturerContract status code "200" for the response
    Then User verify the valid Response body key "recordInserted" and expected value "true"
    Then User update manufacture contract data
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-08-01 | 2021-09-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 02 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | HiEx           | All       | YRL          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
    Then User hits the "updateManufacturerContract" update manufacture contract post request
    Then User verify UpdateManufacturerContract status code "200" for the response
    Given User create & sava new rebate program
      | contractName    | startDate  | endDate    | programName       | legacyContractID       | pricingBasis | pricingRefDate | psfApply | programLevelPsf | condition |
      | QAAutomation_10 | 2021-08-02 | 2021-08-12 | QATestProgramName | QATestlegacyContractID | WAC          | 1ST_OF_QTR     | true     | 10.2            | All       |
    Then User hits the "createNewRebateProgram" with post request of CreateNewRebateProgram
    Then User verify createNewRebateProgram status code "200" for the response
#    Then User verify the createNewRebateProgram response header Error Code value "-1"
    Then User verify the createNewRebateProgram valid Response body key "message" and expected value "Program can be added to only contracts which are in InProgress status" of string

  @Functional
  Scenario: Validate reabate program end date is greater than the contract end date and verify correct status code with response body is returned
    Then User executes the query "deleteContractHeaderByContractName" and "deleteContractDetailByAmendmentName" for contract & Amendment name "QAAutomation%" to delete the record from the database
    Then User executes "deleteRebateProgram" query to delete existing rebate program by Contract_ID "ASTRA%" from the database
    Given User create the Initiate New Manufacture Contract date
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-05-01 | 2021-07-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 01 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | CPOS           | All       | MON          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
    Then User hits the "postInitiateNewManufacturerContract" with post request
    Then User verify InitiateNewManufacturerContract status code "200" for the response
    Then User verify the valid Response body key "recordInserted" and expected value "true"
    Then User update manufacture contract data
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-08-01 | 2021-09-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 02 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | HiEx           | All       | YRL          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
    Then User hits the "updateManufacturerContract" update manufacture contract post request
    Then User verify UpdateManufacturerContract status code "200" for the response
    Then User verify the updateManufacturerContract valid Response body key "recordUpdated" and expected value "true"
    Then User executes the query "updateContractHeaderStatusFromNewToInProgress" and "updateContractDetailsStatusFromNewToInProgress" for contract & Amendment name "QAAutomation_10" to update the contract record status from New to InProgress from the database
    Given User create & sava new rebate program
      | contractName    | startDate  | programName       | pricingBasis | pricingRefDate | psfApply | programLevelPsf | condition |
      | QAAutomation_10 | 2021-08-02 | QATestProgramName | WAC          | 1ST_OF_QTR     | true     | 10.2            | mandatory |
    Then User hits the "createNewRebateProgram" with post request of CreateNewRebateProgram
    Then User verify createNewRebateProgram status code "200" for the response
#    Then User verify the createNewRebateProgram response header Error Code value "502"
    Then User verify the valid createNewRebateProgram Response body key "isRecordSaved" and expected value "false"

  @Functional
  Scenario: Validate Program level psf should be blank when psf apply is false and verify correct status code with response body is returned
    Then User executes the query "deleteContractHeaderByContractName" and "deleteContractDetailByAmendmentName" for contract & Amendment name "QAAutomation%" to delete the record from the database
    Then User executes "deleteRebateProgram" query to delete existing rebate program by Contract_ID "ASTRA%" from the database
    Given User create the Initiate New Manufacture Contract date
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-05-01 | 2021-07-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 01 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | CPOS           | All       | MON          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
    Then User hits the "postInitiateNewManufacturerContract" with post request
    Then User verify InitiateNewManufacturerContract status code "200" for the response
    Then User verify the valid Response body key "recordInserted" and expected value "true"
    Then User update manufacture contract data
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-08-01 | 2021-09-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 02 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | HiEx           | All       | YRL          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
    Then User hits the "updateManufacturerContract" update manufacture contract post request
    Then User verify UpdateManufacturerContract status code "200" for the response
    Then User verify the updateManufacturerContract valid Response body key "recordUpdated" and expected value "true"
    Then User executes the query "updateContractHeaderStatusFromNewToInProgress" and "updateContractDetailsStatusFromNewToInProgress" for contract & Amendment name "QAAutomation_10" to update the contract record status from New to InProgress from the database
    Given User create & sava new rebate program
      | contractName    | startDate  | endDate    | programName       | legacyContractID       | pricingBasis | pricingRefDate | psfApply | programLevelPsf | condition |
      | QAAutomation_10 | 2021-08-02 | 2021-08-12 | QATestProgramName | QATestlegacyContractID | WAC          | 1ST_OF_QTR     | false    | 10.2            | All       |
    Then User hits the "createNewRebateProgram" with post request of CreateNewRebateProgram
    Then User verify createNewRebateProgram status code "200" for the response
    Then User verify the createNewRebateProgram valid Response body key "message" and expected value "Program level psf should be blank when psf apply is false" of string

  @Functional
  Scenario: Validate end date of program is less the start date
    Then User executes the query "deleteContractHeaderByContractName" and "deleteContractDetailByAmendmentName" for contract & Amendment name "QAAutomation%" to delete the record from the database
    Then User executes "deleteRebateProgram" query to delete existing rebate program by Contract_ID "ASTRA%" from the database
    Given User create the Initiate New Manufacture Contract date
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-05-01 | 2021-07-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 01 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | CPOS           | All       | MON          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
    Then User hits the "postInitiateNewManufacturerContract" with post request
    Then User verify InitiateNewManufacturerContract status code "200" for the response
    Then User verify the valid Response body key "recordInserted" and expected value "true"
    Then User update manufacture contract data
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-08-01 | 2021-09-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 02 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | HiEx           | All       | YRL          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
    Then User hits the "updateManufacturerContract" update manufacture contract post request
    Then User verify UpdateManufacturerContract status code "200" for the response
    Then User verify the updateManufacturerContract valid Response body key "recordUpdated" and expected value "true"
    Then User executes the query "updateContractHeaderStatusFromNewToInProgress" and "updateContractDetailsStatusFromNewToInProgress" for contract & Amendment name "QAAutomation_10" to update the contract record status from New to InProgress from the database
    Given User create & sava new rebate program
      | contractName    | startDate  | endDate    | programName       | legacyContractID       | pricingBasis | pricingRefDate | psfApply | programLevelPsf | condition |
      | QAAutomation_10 | 2021-08-24 | 2021-08-02 | QATestProgramName | QATestlegacyContractID | WAC          | 1ST_OF_QTR     | true     | 10.2            | All       |
    Then User hits the "createNewRebateProgram" with post request of CreateNewRebateProgram
    Then User verify createNewRebateProgram status code "200" for the response
    Then User verify the createNewRebateProgram valid Response body key "message" and expected value "Program start date should not be after end date" of string

  @Functional
  Scenario: Validate Invalid rebateableDrugGroupId  and verify correct status code with response body is returned
    Then User executes the query "deleteContractHeaderByContractName" and "deleteContractDetailByAmendmentName" for contract & Amendment name "QAAutomation%" to delete the record from the database
    Then User executes "deleteRebateProgram" query to delete existing rebate program by Contract_ID "ASTRA%" from the database
    Given User create the Initiate New Manufacture Contract date
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-05-01 | 2021-07-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 01 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | CPOS           | All       | MON          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
    Then User hits the "postInitiateNewManufacturerContract" with post request
    Then User verify InitiateNewManufacturerContract status code "200" for the response
    Then User verify the valid Response body key "recordInserted" and expected value "true"
    Then User update manufacture contract data
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-08-01 | 2021-09-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 02 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | HiEx           | All       | YRL          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
    Then User hits the "updateManufacturerContract" update manufacture contract post request
    Then User verify UpdateManufacturerContract status code "200" for the response
    Then User verify the updateManufacturerContract valid Response body key "recordUpdated" and expected value "true"
    Then User executes the query "updateContractHeaderStatusFromNewToInProgress" and "updateContractDetailsStatusFromNewToInProgress" for contract & Amendment name "QAAutomation_10" to update the contract record status from New to InProgress from the database
    Given User create & sava new rebate program
      | contractName    | startDate  | endDate    | programName       | legacyContractID       | rebateableDrugGroupId | pricingBasis | pricingRefDate | psfApply | programLevelPsf | condition                    |
      | QAAutomation_10 | 2021-08-02 | 2021-08-12 | QATestProgramName | QATestlegacyContractID | 9001                  | WAC          | 1ST_OF_QTR     | true     | 10.2            | invalidRebateableDrugGroupId |
    Then User hits the "createNewRebateProgram" with post request of CreateNewRebateProgram
    Then User verify createNewRebateProgram status code "200" for the response
    Then User verify the createNewRebateProgram valid Response body key "message" and expected value "Invalid Rebateable Drug Group Id : 9001" of string

  @Functional
  Scenario: Validate Invalid qualCompDrugGroupId  and verify correct status code with response body is returned
    Then User executes the query "deleteContractHeaderByContractName" and "deleteContractDetailByAmendmentName" for contract & Amendment name "QAAutomation%" to delete the record from the database
    Then User executes "deleteRebateProgram" query to delete existing rebate program by Contract_ID "ASTRA%" from the database
    Given User create the Initiate New Manufacture Contract date
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-05-01 | 2021-07-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 01 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | CPOS           | All       | MON          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
    Then User hits the "postInitiateNewManufacturerContract" with post request
    Then User verify InitiateNewManufacturerContract status code "200" for the response
    Then User verify the valid Response body key "recordInserted" and expected value "true"
    Then User update manufacture contract data
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-08-01 | 2021-09-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 02 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | HiEx           | All       | YRL          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
    Then User hits the "updateManufacturerContract" update manufacture contract post request
    Then User verify UpdateManufacturerContract status code "200" for the response
    Then User verify the updateManufacturerContract valid Response body key "recordUpdated" and expected value "true"
    Then User executes the query "updateContractHeaderStatusFromNewToInProgress" and "updateContractDetailsStatusFromNewToInProgress" for contract & Amendment name "QAAutomation_10" to update the contract record status from New to InProgress from the database
    Given User create & sava new rebate program
      | contractName    | startDate  | endDate    | programName       | legacyContractID       | qualCompDrugGroupId | pricingBasis | pricingRefDate | psfApply | programLevelPsf | condition                  |
      | QAAutomation_10 | 2021-08-02 | 2021-08-12 | QATestProgramName | QATestlegacyContractID | 9001                | WAC          | 1ST_OF_QTR     | true     | 10.2            | invalidQualCompDrugGroupId |
    Then User hits the "createNewRebateProgram" with post request of CreateNewRebateProgram
    Then User verify createNewRebateProgram status code "200" for the response
    Then User verify the createNewRebateProgram valid Response body key "message" and expected value "Invalid Qualification Competitors Id : 9001" of string

  @Functional
  Scenario: Validate Invalid mktBasketDrugGroupId  and verify correct status code with response body is returned
    Then User executes the query "deleteContractHeaderByContractName" and "deleteContractDetailByAmendmentName" for contract & Amendment name "QAAutomation%" to delete the record from the database
    Then User executes "deleteRebateProgram" query to delete existing rebate program by Contract_ID "ASTRA%" from the database
    Given User create the Initiate New Manufacture Contract date
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-05-01 | 2021-07-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 01 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | CPOS           | All       | MON          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
    Then User hits the "postInitiateNewManufacturerContract" with post request
    Then User verify InitiateNewManufacturerContract status code "200" for the response
    Then User verify the valid Response body key "recordInserted" and expected value "true"
    Then User update manufacture contract data
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-08-01 | 2021-09-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 02 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | HiEx           | All       | YRL          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
    Then User hits the "updateManufacturerContract" update manufacture contract post request
    Then User verify UpdateManufacturerContract status code "200" for the response
    Then User verify the updateManufacturerContract valid Response body key "recordUpdated" and expected value "true"
    Then User executes the query "updateContractHeaderStatusFromNewToInProgress" and "updateContractDetailsStatusFromNewToInProgress" for contract & Amendment name "QAAutomation_10" to update the contract record status from New to InProgress from the database
    Given User create & sava new rebate program
      | contractName    | startDate  | endDate    | programName       | legacyContractID       | mktBasketDrugGroupId | pricingBasis | pricingRefDate | psfApply | programLevelPsf | condition                   |
      | QAAutomation_10 | 2021-08-02 | 2021-08-12 | QATestProgramName | QATestlegacyContractID | 9001                 | WAC          | 1ST_OF_QTR     | true     | 10.2            | invalidMktBasketDrugGroupId |
    Then User hits the "createNewRebateProgram" with post request of CreateNewRebateProgram
    Then User verify createNewRebateProgram status code "200" for the response
    Then User verify the createNewRebateProgram valid Response body key "message" and expected value "Invalid Market Basket Id : 9001" of string

  @Regression @Functional
  Scenario: Validate Invalid dropdown option for PricingBasis value  and verify correct status code with response body is returned
    Then User executes the query "deleteContractHeaderByContractName" and "deleteContractDetailByAmendmentName" for contract & Amendment name "QAAutomation%" to delete the record from the database
    Then User executes "deleteRebateProgram" query to delete existing rebate program by Contract_ID "ASTRA%" from the database
    Given User create the Initiate New Manufacture Contract date
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-05-01 | 2021-07-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 01 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | CPOS           | All       | MON          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
    Then User hits the "postInitiateNewManufacturerContract" with post request
    Then User verify InitiateNewManufacturerContract status code "200" for the response
    Then User verify the valid Response body key "recordInserted" and expected value "true"
    Then User update manufacture contract data
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-08-01 | 2021-09-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 02 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | HiEx           | All       | YRL          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
    Then User hits the "updateManufacturerContract" update manufacture contract post request
    Then User verify UpdateManufacturerContract status code "200" for the response
    Then User verify the updateManufacturerContract valid Response body key "recordUpdated" and expected value "true"
    Then User executes the query "updateContractHeaderStatusFromNewToInProgress" and "updateContractDetailsStatusFromNewToInProgress" for contract & Amendment name "QAAutomation_10" to update the contract record status from New to InProgress from the database
    Given User create & sava new rebate program
      | contractName    | startDate  | endDate    | programName       | legacyContractID       | pricingBasis | pricingRefDate | psfApply | programLevelPsf | condition |
      | QAAutomation_10 | 2021-08-02 | 2021-08-12 | QATestProgramName | QATestlegacyContractID | Test         | 1ST_OF_QTR     | true     | 10.2            | All       |
    Then User hits the "createNewRebateProgram" with post request of CreateNewRebateProgram
    Then User verify createNewRebateProgram status code "200" for the response
    Then User verify the createNewRebateProgram valid Response body key "message" and expected value "Invalid dropdown option for PricingBasis value : Test" of string

  @Regression @Functional
  Scenario: Validate Invalid dropdown option for PricingRefDate value  and verify correct status code with response body is returned
    Then User executes the query "deleteContractHeaderByContractName" and "deleteContractDetailByAmendmentName" for contract & Amendment name "QAAutomation%" to delete the record from the database
    Then User executes "deleteRebateProgram" query to delete existing rebate program by Contract_ID "ASTRA%" from the database
    Given User create the Initiate New Manufacture Contract date
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-05-01 | 2021-07-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 01 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | CPOS           | All       | MON          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
    Then User hits the "postInitiateNewManufacturerContract" with post request
    Then User verify InitiateNewManufacturerContract status code "200" for the response
    Then User verify the valid Response body key "recordInserted" and expected value "true"
    Then User update manufacture contract data
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-08-01 | 2021-09-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 02 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | HiEx           | All       | YRL          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
    Then User hits the "updateManufacturerContract" update manufacture contract post request
    Then User verify UpdateManufacturerContract status code "200" for the response
    Then User verify the updateManufacturerContract valid Response body key "recordUpdated" and expected value "true"
    Then User executes the query "updateContractHeaderStatusFromNewToInProgress" and "updateContractDetailsStatusFromNewToInProgress" for contract & Amendment name "QAAutomation_10" to update the contract record status from New to InProgress from the database
    Given User create & sava new rebate program
      | contractName    | startDate  | endDate    | programName       | legacyContractID       | pricingBasis | pricingRefDate | psfApply | programLevelPsf | condition |
      | QAAutomation_10 | 2021-08-02 | 2021-08-12 | QATestProgramName | QATestlegacyContractID | WAC          | Test           | true     | 10.2            | All       |
    Then User hits the "createNewRebateProgram" with post request of CreateNewRebateProgram
    Then User verify createNewRebateProgram status code "200" for the response
    Then User verify the createNewRebateProgram valid Response body key "message" and expected value "Invalid dropdown option for PricingRefDate value : Test" of string

  @Regression @Functional
  Scenario: Validate Invalid value for Program Level PSF  and verify correct status code with response body is returned
    Then User executes the query "deleteContractHeaderByContractName" and "deleteContractDetailByAmendmentName" for contract & Amendment name "QAAutomation%" to delete the record from the database
    Then User executes "deleteRebateProgram" query to delete existing rebate program by Contract_ID "ASTRA%" from the database
    Given User create the Initiate New Manufacture Contract date
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-05-01 | 2021-07-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 01 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | CPOS           | All       | MON          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
    Then User hits the "postInitiateNewManufacturerContract" with post request
    Then User verify InitiateNewManufacturerContract status code "200" for the response
    Then User verify the valid Response body key "recordInserted" and expected value "true"
    Then User update manufacture contract data
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-08-01 | 2021-09-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 02 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | HiEx           | All       | YRL          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
    Then User hits the "updateManufacturerContract" update manufacture contract post request
    Then User verify UpdateManufacturerContract status code "200" for the response
    Then User verify the updateManufacturerContract valid Response body key "recordUpdated" and expected value "true"
    Then User executes the query "updateContractHeaderStatusFromNewToInProgress" and "updateContractDetailsStatusFromNewToInProgress" for contract & Amendment name "QAAutomation_10" to update the contract record status from New to InProgress from the database
    Given User create & sava new rebate program
      | contractName    | startDate  | endDate    | programName       | legacyContractID       | pricingBasis | pricingRefDate | psfApply | programLevelPsf | condition |
      | QAAutomation_10 | 2021-08-02 | 2021-08-12 | QATestProgramName | QATestlegacyContractID | WAC          | 1ST_OF_QTR     | true     | 10.223          | All       |
    Then User hits the "createNewRebateProgram" with post request of CreateNewRebateProgram
    Then User verify createNewRebateProgram status code "200" for the response
    Then User verify the createNewRebateProgram valid Response body key "message" and expected value "Invalid value for Program Level PSF" of string

  @NonAutomated
  Scenario:This is non automated scenario
    Then User executes the query "deleteContractHeaderByContractName" and "deleteContractDetailByAmendmentName" for contract & Amendment name "QAAutomation%" to delete the record from the database
