#### This feature consists of ALl the Scenarios of fetch Comments API
  Feature: API to fetch Comments of Task

      @Smoke
      Scenario: hit the Capture Comments API and validate the Status code
      Given User create the Initiate New Manufacture Contract date
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-05-01 | 2021-07-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 01 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | CPOS           | All       | MON          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
      Then User hits the "postInitiateNewManufacturerContract" with post request
      Then User verify InitiateNewManufacturerContract status code "200" for the response
      Then User verify the valid Response body key "recordInserted" and expected value "true"
      Then User creates the manufacture contract data requestBody
      | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
      | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-08-01 | 2021-09-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 02 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | HiEx           | All       | YRL          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
      Then User hits the "updateMfrContractSendToRebateOps" API update manufacture contract post request
      And User creates the request Body for Capture Comments API
      |category|event|comment|
      |Contract|SendToRebateOPs|testComments|
      Then User hits the "captureCommentsAPI" API with request body
      And User verifies the CaptureAPI StatusCode is "200"
      Then User creates the Request Body for fetchComments API
      And User hits the API "fetchComments" API with request body
      Then User verifies the FetchCommentsAPI StatusCode is "200"
      Then User hits the "discardContractResource" Endpoint with delete API request to discard Initiate New Manufacture Contract by "rowKeyContractHeader" and contractName "QAAutomation_10"
      Then User verifies the isManufacturerContractDiscarded as true value in response for new manufacture contract
      Then User executes the query "deleteContractHeaderByContractName" and "deleteContractDetailByAmendmentName" for contract & Amendment name "QAAutomation_10" to delete the record from the database
      Then User executes the query "deleteComments" and Deletes the Comments in comments Tables
      Then User Deletes the task asociated with this task with query "deleteTaskFromFlowable"


      Scenario: hit the Capture Comments API and validate the Status code
          Given User create the Initiate New Manufacture Contract date
              | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
              | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-05-01 | 2021-07-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 01 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | CPOS           | All       | MON          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
          Then User hits the "postInitiateNewManufacturerContract" with post request
          Then User verify InitiateNewManufacturerContract status code "200" for the response
          Then User verify the valid Response body key "recordInserted" and expected value "true"
          Then User creates the manufacture contract data requestBody
              | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
              | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-08-01 | 2021-09-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 02 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | HiEx           | All       | YRL          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
          Then User hits the "updateMfrContractSendToRebateOps" API update manufacture contract post request
          And User creates the request Body for Capture Comments API
              |category|event|comment|
              |Contract|SendToRebateOPs|testComments|
          Then User hits the "captureCommentsAPI" API with request body
          And User verifies the CaptureAPI StatusCode is "200"
          Then User creates the Request Body for fetchComments API
          And User hits the API "fetchComments" API with request body
          Then User verifies fetchComments API the response in JSON Format
          Then User hits the "discardContractResource" Endpoint with delete API request to discard Initiate New Manufacture Contract by "rowKeyContractHeader" and contractName "QAAutomation_10"
          Then User verifies the isManufacturerContractDiscarded as true value in response for new manufacture contract
          Then User executes the query "deleteContractHeaderByContractName" and "deleteContractDetailByAmendmentName" for contract & Amendment name "QAAutomation_10" to delete the record from the database
          Then User executes the query "deleteComments" and Deletes the Comments in comments Tables
          Then User Deletes the task asociated with this task with query "deleteTaskFromFlowable"

      Scenario: hit the Capture Comments API and Validate the Response with DB
          Given User create the Initiate New Manufacture Contract date
              | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
              | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-05-01 | 2021-07-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 01 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | CPOS           | All       | MON          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
          Then User hits the "postInitiateNewManufacturerContract" with post request
          Then User verify InitiateNewManufacturerContract status code "200" for the response
          Then User verify the valid Response body key "recordInserted" and expected value "true"
          Then User creates the manufacture contract data requestBody
              | ManufacturerId | name         | currentFlag | rowKey | contractId | contractType | contractName    | startDate  | endDate    | recCreatedDate          | recCreatedBy | recUpdatedDate          | recUpdatedBy | lifecycleStatus | contractDocReference | notes           | disputeDays | lateFee | lateFeeFixed | lateFeePct | paymentBackup | NCPDPReconFile | frequency | lookback | numScreenshots | allowThirdPartyAuditor | auditScreenshots | schemaVersion | lineOfBusiness | locations | billingCycle | submissionWindow | resubmissionWindow | paymentTerms | thirdPartyAuth | opsAssignee | opsQCer | amendmentNumber | amendmentName   | versionNumber |
              | ASTRA001       | Astra Zeneca | false       | 75     | ABV0011037 | Non-Part D   | QAAutomation_10 | 2021-08-01 | 2021-09-20 | 2021-02-05T11:17:18.337 | SYSTEM       | 2021-02-05T11:17:18.337 | SYSTEM       | NEW             | null                 | This is test 02 | 30          | AMT     | null         | null       | false         | null           | ANNUAL    | 12       | null           | true                   | false            | 1             | HiEx           | All       | YRL          | 90               | 60                 | 30           | true           | null        | null    | 0               | QAAutomation_10 | 1             |
          Then User hits the "updateMfrContractSendToRebateOps" API update manufacture contract post request
          And User creates the request Body for Capture Comments API
              |category|event|comment|
              |Contract|SendToRebateOPs|testComments|
          Then User hits the "captureCommentsAPI" API with request body
          And User verifies the CaptureAPI StatusCode is "200"
          Then User creates the Request Body for fetchComments API
          And User hits the API "fetchComments" API with request body
          Then User verifies the Response With DB using query "fetchCommentsDetails"
          Then User hits the "discardContractResource" Endpoint with delete API request to discard Initiate New Manufacture Contract by "rowKeyContractHeader" and contractName "QAAutomation_10"
          Then User verifies the isManufacturerContractDiscarded as true value in response for new manufacture contract
          Then User executes the query "deleteContractHeaderByContractName" and "deleteContractDetailByAmendmentName" for contract & Amendment name "QAAutomation_10" to delete the record from the database
          Then User executes the query "deleteComments" and Deletes the Comments in comments Tables
          Then User Deletes the task asociated with this task with query "deleteTaskFromFlowable"