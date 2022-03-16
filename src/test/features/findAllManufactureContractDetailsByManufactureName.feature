Feature:Retrieve and validate the find all manufacture contract details by given manufacture name

  @Smoke
  Scenario: Validate find all manufacture contract details by given manufacture name and verify correct status code with response body is returned
    Given User creates the New Manufacture Contract data with following details
      |ManufacturerId | ManufacturerName|contractType|startDate|endDate|notes|autoRenewFlag|autoRenewTerm|autoRenewNotifyDate|lineOfBusiness|locations|
      |ASTRA001         |Astra Zeneca           |Non-Part D  |2015-01-01|2016-01-01|Sample Note for the Contract|N        |OneYear      |2015-01-12         |CPOS|US_Only|
    Then User hits the "postInitiateNewManufacturerContract" with post request
    Then User verify InitiateNewManufacturerContract status code "200" for the response
    Then User verify the valid Response body key "recordInserted" and expected value "true"
    Given User creates the New Manufacture Contract data with following details
      |ManufacturerId | ManufacturerName|contractType|startDate|endDate|notes|autoRenewFlag|autoRenewTerm|autoRenewNotifyDate|lineOfBusiness|locations|
      |ASTRA001         |Astra Zeneca           |Non-Part D  |2021-08-01|2021-09-20|Sample Note for the Contract|N        |OneYear      |2015-01-12         |Commercial#HiEx|US_Only|
    Then User hits the "postInitiateNewManufacturerContract" with post request
    Then User verify InitiateNewManufacturerContract status code "200" for the response
    Then User verify the valid Response body key "recordInserted" and expected value "true"
    Given User find all manufacture contract details for given manufacture name "Astra Zeneca"
    Then User hits the FindAllManufactureDetails "findAllManufactureContractDetails" with post request
    Then User verify FindAllManufactureDetails status code "200" for the response


  @Regression @Functional
  Scenario: Validate find all manufacture contract details by given manufacture name response and verify it from DB
    Then User executes the query "deleteContractHeaderByContractName" and "deleteContractDetailByAmendmentName" for contract & Amendment name "QAAutomation%" to delete the record from the database
    Given User creates the New Manufacture Contract data with following details
      |ManufacturerId | ManufacturerName|contractType|startDate|endDate|notes|autoRenewFlag|autoRenewTerm|autoRenewNotifyDate|lineOfBusiness|locations|
      |ASTRA001         |Astra Zeneca           |Non-Part D  |2021-08-01|2021-09-20|Sample Note for the Contract|N        |OneYear      |2015-01-12         |Commercial#HiEx|US_Only|
    Then User hits the "postInitiateNewManufacturerContract" with post request
    Then User verify InitiateNewManufacturerContract status code "200" for the response
    Then User verify the valid Response body key "recordInserted" and expected value "true"
    Given User creates the New Manufacture Contract data with following details
      |ManufacturerId | ManufacturerName|contractType|startDate|endDate|notes|autoRenewFlag|autoRenewTerm|autoRenewNotifyDate|lineOfBusiness|locations|
      |ASTRA001         |Astra Zeneca           |Non-Part D  |2021-08-01|2021-09-20|Sample Note for the Contract|N        |OneYear      |2015-01-12         |HiEx#Commercial|US_Only|
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


