Feature:Retrieve and validate the save drug group details

  Background:
    Given User creates a new drug group to add some drugs to it and test
      | mfrId    | drugGroupName      | drugGroupType |condition|
      | ASTRA001 | QAAutomation_Drug1 | Rebateable    |valid     |
    Then User hits the "createNewDrugGroupDetails" API and fetches its rowKey using query "getRowKeyForCreateDrugGroup"


  @Smoke
  Scenario: Validate save drug group details creation and verify correct status code with response body is returned
     Given User create the save drug group details data
      | drugListId | ndcs | startDate | endDate | query         | columnName        | condition |
      |            |      |           |         | getListOfNdcs | DRUG_PRODUCT_CODE | valid     |
    Then User hits the "saveDrugGroupDetails" Save Drug Group Details API
    Then User verify save drug drug group status code "200" for the response
    Then User verify the NDC details And matches returned by API and DB by NDCJson path "ndcsSaveDrugGroupDetailsJSON" and columnName "saveDrugGroupDetailsDrugProductCodeColumName"
    Then User verify the NDC details Row Key And matches returned by API and DB by NDCJson path "rowKeySaveDrugGroupDetailsJSON" and columnName "saveDrugGroupDetailsRowKeyColumName"
    Then User discards the drugGroup and deletes the created record of drugGroup from DB


  @Functional @Regression
  Scenario: Validate DrugGroupDetailName Already Exists with overlapping date for save drug group details creation and verify correct status code with response body is returned
    Given User create the save drug group details data
      | drugListId | ndcs | startDate | endDate | query         | columnName        | condition |
      |            |      |           |         | getListOfNdcs | DRUG_PRODUCT_CODE | valid     |
    Then User hits the "saveDrugGroupDetails" Save Drug Group Details API
    Then User verify save drug drug group status code "200" for the response
    Then User verify the NDC details And matches returned by API and DB by NDCJson path "ndcsSaveDrugGroupDetailsJSON" and columnName "saveDrugGroupDetailsDrugProductCodeColumName"
    Then User verify the NDC details Row Key And matches returned by API and DB by NDCJson path "rowKeySaveDrugGroupDetailsJSON" and columnName "saveDrugGroupDetailsRowKeyColumName"

    Given User create the save drug group details data
      | drugListId | ndcs | startDate | endDate | query                 | columnName        | condition |
      |            |      |           |         | getListOfExistingNdcs | DRUG_PRODUCT_CODE | valid     |
    Then User hits the "saveDrugGroupDetails" Save Drug Group Details API
    Then User verify save drug drug group status code "200" for the response
    Then User discards the drugGroup and deletes the record from DB
#    Then User verify the saveDrugGroupDetails valid Response body key "saveDrugGroupDetailsMessage" and expected value "DrugGroupDetailName Already Exists" of string
#    Then User verify the saveDrugGroupDetails valid Response body key "saveDrugGroupDetailsStatusCode" and expected value "500" of Integer

  @Functional @Regression
  Scenario: Validate DrugGroupDetailName Already Exists without overlapping date for save drug group details creation and verify correct status code with response body is returned
    Given User create the save drug group details data
      | drugListId | ndcs | startDate | endDate | query         | columnName        | condition |
      |            |      |           |         | getListOfNdcs | DRUG_PRODUCT_CODE | valid     |
    Then User hits the "saveDrugGroupDetails" Save Drug Group Details API
    Then User verify save drug drug group status code "200" for the response
    Then User verify the NDC details And matches returned by API and DB by NDCJson path "ndcsSaveDrugGroupDetailsJSON" and columnName "saveDrugGroupDetailsDrugProductCodeColumName"
    Then User verify the NDC details Row Key And matches returned by API and DB by NDCJson path "rowKeySaveDrugGroupDetailsJSON" and columnName "saveDrugGroupDetailsRowKeyColumName"


    Given User create the save drug group details data
      | drugListId | drugRowKey | ndcs | startDate  | endDate    | query                 | columnName        | condition          |
      |            |            |      | 2023-01-10 | 2024-01-10 | getListOfExistingNdcs | DRUG_PRODUCT_CODE | nonOverLappingDate |
    Then User hits the "saveDrugGroupDetails" Save Drug Group Details API
    Then User verify save drug drug group status code "200" for the response
    Then User discards the drugGroup and deletes the record from DB
    Then User verify the saveDrugGroupDetails valid Response body key "saveDrugGroupDetailsMessage" and expected value "SUCCESS" of string

  @Functional @Regression
  Scenario: Validate DrugListId doesn't exists in Drug Group for save drug group details creation and verify correct status code with response body is returned
    Given User create the save drug group details data
      | drugListId | drugRowKey | ndcs | startDate  | endDate    | query                 | columnName        | condition         |
      | 47233333   |            |      | 2023-01-10 | 2024-01-10 | getListOfExistingNdcs | DRUG_PRODUCT_CODE | invalidDrugListId |
    Then User hits the "saveDrugGroupDetails" Save Drug Group Details API
    Then User verify save drug drug group status code "200" for the response
    Then User discards the drugGroup and deletes the record from DB
    Then User Verifies the response "message" with error message "errorMessageForInvalidDrugListID"

  @Functional @Regression
  Scenario: Validate Invalid NDC Not Exists In Medispan for save drug group details creation and verify correct status code with response body is returned
       Given User create the save drug group details data
      | drugListId | drugRowKey | ndcs        | startDate  | endDate    | query                 | columnName        | condition   |
      |            |            | 22333344444 | 2023-01-10 | 2024-01-10 | getListOfExistingNdcs | DRUG_PRODUCT_CODE | invalidNDCS |
    Then User hits the "saveDrugGroupDetails" Save Drug Group Details API
    Then User verify save drug drug group status code "200" for the response
    Then User discards the drugGroup and deletes the record from DB



  @Functional @Regression
  Scenario: Invalid date where start should be less than the end date and verify correct status code with response body is returned
    Given User create the save drug group details data
      | drugListId | drugRowKey | ndcs | startDate  | endDate    | query                 | columnName        | condition          |
      |            |            |      | 2024-01-10 | 2023-01-10 | getListOfExistingNdcs | DRUG_PRODUCT_CODE | nonOverLappingDate |
    Then User hits the "saveDrugGroupDetails" Save Drug Group Details API
    Then User verify save drug drug group status code "200" for the response
    Then User verify the saveDrugGroupDetails valid Response body key "message" and expected value "errorMessageForInvalidDates" of string
    Then User discards the drugGroup and deletes the record from DB

  @Functional @Regression
  Scenario: Invalid input drugRowKey and verify correct status code with response body is returned
    Given User create the save drug group details data
      | drugListId | ndcs | startDate  | endDate    | query                 | columnName        | condition             |
      |            |      | 2023-01-10 | 2024-01-10 | getListOfExistingNdcs | DRUG_PRODUCT_CODE | invalidDrugListRowKey |
    Then User hits the "saveDrugGroupDetails" Save Drug Group Details API
    Then User verify save drug drug group status code "200" for the response
    Then User verify the saveDrugGroupDetails valid Response body key "message" and expected value "errorMessageForInvalidRowKey" of string
    Then User discards the drugGroup and deletes the record from DB