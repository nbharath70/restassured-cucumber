Feature:Retrieve and validate the find all manufacture contract details by given manufacture name

  @Smoke
  Scenario: Validate find all manufacture contract details by given manufacture name and verify correct status code with response body is returned
    Then User executes the query "deleteContractHeaderByContractName" and "deleteContractDetailByAmendmentName" for contract & Amendment name "QAAutomation%" to delete the record from the database
    Given User create the Initiate New Manufacture Contract date
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName    | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_11 | 2021-05-01 | 2021-07-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 01 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | CPOS           | usStates1 | MON          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_007 | 1             |
    Then User hits the "postInitiateNewManufacturerContract" with post request
    Then User verify InitiateNewManufacturerContract status code "200" for the response
    Then User verify the valid Response body key "recordInserted" and expected value "true"
    Given User create the Initiate New Manufacture Contract date
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness  | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName    | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_12 | 2021-08-01 | 2021-09-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 01 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | HiEx,Commercial | usStates1 | MON          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_008 | 1             |
    Then User hits the "postInitiateNewManufacturerContract" with post request
    Then User verify InitiateNewManufacturerContract status code "200" for the response
    Then User verify the valid Response body key "recordInserted" and expected value "true"
    Given User find all manufacture contract details for given manufacture name "Astra Zeneca"
    Then User hits the FindAllManufactureDetails "findAllManufactureContractDetails" with post request
    Then User verify FindAllManufactureDetails status code "200" for the response

  @Regression @Functional
  Scenario: Validate find all manufacture contract details by given manufacture name response and verify it from DB
    Then User executes the query "deleteContractHeaderByContractName" and "deleteContractDetailByAmendmentName" for contract & Amendment name "QAAutomation%" to delete the record from the database
    Given User create the Initiate New Manufacture Contract date
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName    | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_11 | 2021-05-01 | 2021-07-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 01 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | CPOS           | usStates1 | MON          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_007 | 1             |
    Then User hits the "postInitiateNewManufacturerContract" with post request
    Then User verify InitiateNewManufacturerContract status code "200" for the response
    Then User verify the valid Response body key "recordInserted" and expected value "true"
    Given User create the Initiate New Manufacture Contract date
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness  | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName    | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_12 | 2021-08-01 | 2021-09-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 01 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | HiEx,Commercial | usStates1 | MON          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_008 | 1             |
    Then User hits the "postInitiateNewManufacturerContract" with post request
    Then User verify InitiateNewManufacturerContract status code "200" for the response
    Then User verify the valid Response body key "recordInserted" and expected value "true"
    Given User find all manufacture contract details for given manufacture name "Astra Zeneca"
    Then User hits the FindAllManufactureDetails "findAllManufactureContractDetails" with post request
    Then User verify FindAllManufactureDetails status code "200" for the response
    When User executes FindAllManufacture query "getCountOfManufactureID" And matches the count of MFR returned by API and DB

  @Regression @Functional
  Scenario: Validate error code for invalid manufacturer name
    Given User find all manufacture contract details for given manufacture name "Test"
    Then User hits the FindAllManufactureDetails "findAllManufactureContractDetails" with post request
    Then User verify FindAllManufactureDetails status code "200" for the response


