Feature:Retrieve and validate the Update Manufacture Contract
  Scenario: Validate Update Manufacture Contract record Inserted is true and verify correct status code with response body is returned
    Then User executes the query "deleteContractHeaderByContractName" and "deleteContractDetailByAmendmentName" for contract & Amendment name "QAAutomation_10" to delete the record from the database
    Given User create the Initiate New Manufacture Contract date
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-05-01 | 2021-07-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 01 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | CPOS           | usStates1 | MON          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
    Then User hits the "postInitiateNewManufacturerContract" with post request
    Then User verify InitiateNewManufacturerContract status code "200" for the response
    Then User verify the valid Response body key "recordInserted" and expected value "true"
    Then User update manufacture contract data
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-08-01 | 2021-09-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 02 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | healthExchange | usStates1 | MON          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
    Then User hits the "updateManufacturerContract" update manufacture contract post request
    Then User verify UpdateManufacturerContract status code "200" for the response
    Then User verify the updateManufacturerContract valid Response body key "recordInserted" and expected value "true"

  Scenario: Validate the Overlapping date with Update Manufacture Contract with different Line of Business value
    Then User executes the query "deleteContractHeaderByContractName" and "deleteContractDetailByAmendmentName" for contract & Amendment name "QAAutomation_008" to delete the record from the database
    Given User create the Initiate New Manufacture Contract date
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName     | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName    | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_008 | 2021-01-01 | 2021-04-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 01 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | healthExchange | usStates1 | MON          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_008 | 1             |
    Then User hits the "postInitiateNewManufacturerContract" with post request
    Then User verify InitiateNewManufacturerContract status code "200" for the response
    Then User verify the valid Response body key "recordInserted" and expected value "true"
    Then User executes the query "deleteContractHeaderByContractName" and "deleteContractDetailByAmendmentName" for contract & Amendment name "QAAutomation_10" to delete the record from the database
    Given User create the Initiate New Manufacture Contract date
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-05-01 | 2021-07-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 01 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | CPOS           | usStates1 | MON          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
    Then User hits the "postInitiateNewManufacturerContract" with post request
    Then User verify InitiateNewManufacturerContract status code "200" for the response
    Then User verify the valid Response body key "recordInserted" and expected value "true"
    Then User update manufacture contract data
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-01-01 | 2021-04-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 02 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | commercial     | usStates1 | MON          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
    Then User hits the "updateManufacturerContract" update manufacture contract post request
    Then User verify UpdateManufacturerContract status code "200" for the response
    Then User verify the updateManufacturerContract valid Response body key "recordInserted" and expected value "true"

  Scenario: Validate the contractDetailDatesOverlappingWithOtherContractDetailDates in update manufacturer contract
    Then User executes the query "deleteContractHeaderByContractName" and "deleteContractDetailByAmendmentName" for contract & Amendment name "QAAutomation_008" to delete the record from the database
    Given User create the Initiate New Manufacture Contract date
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName     | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName    | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_008 | 2021-01-01 | 2021-04-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 01 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | healthExchange | usStates1 | MON          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_008 | 1             |
    Then User hits the "postInitiateNewManufacturerContract" with post request
    Then User verify InitiateNewManufacturerContract status code "200" for the response
    Then User verify the valid Response body key "recordInserted" and expected value "true"
    Then User executes the query "deleteContractHeaderByContractName" and "deleteContractDetailByAmendmentName" for contract & Amendment name "QAAutomation_10" to delete the record from the database
    Given User create the Initiate New Manufacture Contract date
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-05-01 | 2021-07-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 01 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | CPOS           | usStates1 | MON          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
    Then User hits the "postInitiateNewManufacturerContract" with post request
    Then User verify InitiateNewManufacturerContract status code "200" for the response
    Then User verify the valid Response body key "recordInserted" and expected value "true"
    Then User update manufacture contract data
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-01-01 | 2021-04-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 02 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | healthExchange | usStates1 | MON          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
    Then User hits the "updateManufacturerContract" update manufacture contract post request
    Then User verify UpdateManufacturerContract status code "200" for the response
    Then User verify the updateManufacturerContract valid Response body key "recordInserted" and expected value "false"
    Then User verify the updateManufacturerContract valid Response body key "contractDetailDatesOverlappingWithOtherContractDetailDates" and expected value "true"


#  Scenario: Clarification required validate the Overlapping date with Update Manufacture Contract existing start & end date and different LOB
#    Then User executes the query "deleteContractHeaderByContractName" and "deleteContractDetailByAmendmentName" for contract & Amendment name "QAAutomation_008" to delete the record from the database
#    Given User create the Initiate New Manufacture Contract date
#      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName     | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness            | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName    | versionNumber |
#      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_008 | 2021-01-01 | 2021-04-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 01 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | healthExchange,commercial | usStates1 | MON          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_008 | 1             |
#    Then User hits the "postInitiateNewManufacturerContract" with post request
#    Then User verify InitiateNewManufacturerContract status code "200" for the response
#    Then User verify the valid Response body key "recordInserted" and expected value "true"
#    Then User executes the query "deleteContractHeaderByContractName" and "deleteContractDetailByAmendmentName" for contract & Amendment name "QAAutomation_10" to delete the record from the database
#    Given User create the Initiate New Manufacture Contract date
#      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
#      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-05-01 | 2021-07-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 01 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | CPOS           | usStates1 | MON          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
#    Then User hits the "postInitiateNewManufacturerContract" with post request
#    Then User verify InitiateNewManufacturerContract status code "200" for the response
#    Then User verify the valid Response body key "recordInserted" and expected value "true"
#    Then User update manufacture contract data
#      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
#      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-01-01 | 2021-04-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 02 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | CPOS           | usStates1 | MON          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
#    Then User hits the "updateManufacturerContract" update manufacture contract post request
#    Then User verify UpdateManufacturerContract status code "200" for the response
#    Then User verify the updateManufacturerContract valid Response body key "recordInserted" and expected value "true"


  Scenario: Validate Update Manufacture Contract for contractExists
    Then User executes the query "deleteContractHeaderByContractName" and "deleteContractDetailByAmendmentName" for contract & Amendment name "QAAutomation_007" to delete the record from the database
    Given User create the Initiate New Manufacture Contract date
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName     | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName    | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_007 | 2021-01-01 | 2021-04-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 01 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | CPOS           | usStates1 | MON          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_007 | 1             |
    Then User hits the "postInitiateNewManufacturerContract" with post request
    Then User verify InitiateNewManufacturerContract status code "200" for the response
    Then User verify the valid Response body key "recordInserted" and expected value "true"
    Then User update manufacture contract data
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName     | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_007 | 2021-08-01 | 2021-09-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 02 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | healthExchange | usStates1 | MON          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
    Then User hits the "updateManufacturerContract" update manufacture contract post request
    Then User verify UpdateManufacturerContract status code "200" for the response
    Then User verify the updateManufacturerContract valid Response body key "recordInserted" and expected value "false"
    Then User verify the updateManufacturerContract valid Response body key "contractExists" and expected value "true"

  Scenario: Validate Update Manufacturer Contract start date should not be after the end date
    Then User update manufacture contract data
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-08-01 | 2020-09-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 02 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | healthExchange | usStates1 | MON          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
    Then User hits the "updateManufacturerContract" update manufacture contract post request
    Then User verify UpdateManufacturerContract status code "200" for the response
    Then User verify the updateManufacturerContract valid Response body key "message" and expected value "Invalid input" of string

  Scenario: Validate Update Manufacturer Contract invalid LOB
    Then User update manufacture contract data
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2020-08-01 | 2020-09-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 02 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | Test           | usStates1 | MON          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
    Then User hits the "updateManufacturerContract" update manufacture contract post request
    Then User verify UpdateManufacturerContract status code "200" for the response
    Then User verify the updateManufacturerContract valid Response body key "recordInserted" and expected value "false"
    Then User verify the updateManufacturerContract valid Response body key "invalidLOB" and expected value "true"

  Scenario: Validate Update Manufacturer Contract invalid Notes(Clarification two char notes is accepting,frequency,lookback & locations)
    Then User update manufacture contract data
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-08-01 | 2021-09-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | #a    | 30          | AMT     | null         | null       | false         | null           | Test      | 13       | null           | true                   | false            | 1             | healthExchange | Test      | MON          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
    Then User hits the "updateManufacturerContract" update manufacture contract post request
    Then User verify UpdateManufacturerContract status code "200" for the response
    Then User verify the updateManufacturerContract valid Response body key "recordInserted" and expected value "true"

  Scenario: Validate Update Manufacture Contract of invalidSelectedOptions & validContractHeader
    Then User update manufacture contract data
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-08-01 | 2021-09-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 02 | 31          | Test    | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | healthExchange | usStates1 | Test         | 91               | 61                 | 31           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
    Then User hits the "updateManufacturerContract" update manufacture contract post request
    Then User verify UpdateManufacturerContract status code "200" for the response
    Then User verify the updateManufacturerContract valid Response body key "recordInserted" and expected value "false"
    Then User verify the updateManufacturerContract valid Response body key "invalidSelectedOptions" and expected value "true"
    Then User verify the updateManufacturerContract valid Response body key "invalidSelectedOptions" and expected value "true"






