Feature:Retrieve and validate the save drug group details

  @Smoke
  Scenario: Validate save drug group details creation and verify correct status code with response body is returned
    Then User executes the delete existing DrugGroup query "deleteDrugGroupByName" and List name "QAAutomation%" to delete the record from the database
    Given User create the Create new group details data
      | mfrId    | drugGroupName      | drugGroupType |
      | ASTRA001 | QAAutomation_Drug1 | Rebateable    |
    Then User hits the "createNewDrugGroupDetails" with post request of CreateNewDrugGroup
    Then User verify create new drug group status code "200" for the response
    Then User verify the valid CreateNewDrugGroup Response body key "recordSaved" and expected value "true"
    Then User verify the valid CreateNewDrugGroup Response body key "recordUpdated" and expected value "false"
    Then User verify the valid Response body key "mfrDrugListId" and execute the query "QAAutomation_Drug1" matches the Drug groupID return from API and DB
    Then User executes the query "getMFR_DrugList_ID" by drugGroup name "QAAutomation_Drug1" to get MFR_DrugList_ID to save drug group details
    Then User executes the query "getDrugGroupRowKey" by drugGroup name "QAAutomation_Drug1" to get row key for drug group to save drug group details
    Given User create the save drug group details data
      | drugListId | ndcs | startDate | endDate | query         | columnName        | condition |
      |            |      |           |         | getListOfNdcs | DRUG_PRODUCT_CODE | valid     |
    Then User hits the "saveDrugGroupDetails" with post request of saveDrugGroupDetails API
    Then User verify save drug drug group status code "200" for the response
    Then User verify the NDC details And matches returned by API and DB by NDCJson path "ndcsSaveDrugGroupDetailsJSON" and columnName "saveDrugGroupDetailsDrugProductCodeColumName"
    Then User verify the NDC details Row Key And matches returned by API and DB by NDCJson path "rowKeySaveDrugGroupDetailsJSON" and columnName "saveDrugGroupDetailsRowKeyColumName"

  @Functional @Regression
  Scenario: Validate DrugGroupDetailName Already Exists with overlapping date for save drug group details creation and verify correct status code with response body is returned
    Then User executes the delete existing DrugGroup query "deleteDrugGroupByName" and List name "QAAutomation%" to delete the record from the database
    Given User create the Create new group details data
      | mfrId    | drugGroupName      | drugGroupType |
      | ASTRA001 | QAAutomation_Drug1 | Rebateable    |
    Then User hits the "createNewDrugGroupDetails" with post request of CreateNewDrugGroup
    Then User verify create new drug group status code "200" for the response
    Then User verify the valid CreateNewDrugGroup Response body key "recordSaved" and expected value "true"
    Then User verify the valid CreateNewDrugGroup Response body key "recordUpdated" and expected value "false"
    Then User verify the valid Response body key "mfrDrugListId" and execute the query "QAAutomation_Drug1" matches the Drug groupID return from API and DB
    Then User executes the query "getMFR_DrugList_ID" by drugGroup name "QAAutomation_Drug1" to get MFR_DrugList_ID to save drug group details
    Then User executes the query "getDrugGroupRowKey" by drugGroup name "QAAutomation_Drug1" to get row key for drug group to save drug group details
    Given User create the save drug group details data
      | drugListId | ndcs | startDate | endDate | query         | columnName        | condition |
      |            |      |           |         | getListOfNdcs | DRUG_PRODUCT_CODE | valid     |
    Then User hits the "saveDrugGroupDetails" with post request of saveDrugGroupDetails API
    Then User verify save drug drug group status code "200" for the response
    Then User verify the NDC details And matches returned by API and DB by NDCJson path "ndcsSaveDrugGroupDetailsJSON" and columnName "saveDrugGroupDetailsDrugProductCodeColumName"
    Then User verify the NDC details Row Key And matches returned by API and DB by NDCJson path "rowKeySaveDrugGroupDetailsJSON" and columnName "saveDrugGroupDetailsRowKeyColumName"
    Then User executes the query "getMFR_DrugList_ID" by drugGroup name "QAAutomation_Drug1" to get MFR_DrugList_ID to save drug group details
    Then User executes the query "getDrugGroupRowKey" by drugGroup name "QAAutomation_Drug1" to get row key for drug group to save drug group details
    Given User create the save drug group details data
      | drugListId | ndcs | startDate | endDate | query                 | columnName        | condition |
      |            |      |           |         | getListOfExistingNdcs | DRUG_PRODUCT_CODE | valid     |
    Then User hits the "saveDrugGroupDetails" with post request of saveDrugGroupDetails API
    Then User verify save drug drug group status code "200" for the response
#    Then User verify the saveDrugGroupDetails valid Response body key "saveDrugGroupDetailsMessage" and expected value "DrugGroupDetailName Already Exists" of string
#    Then User verify the saveDrugGroupDetails valid Response body key "saveDrugGroupDetailsStatusCode" and expected value "500" of Integer

  @Functional @Regression
  Scenario: Validate DrugGroupDetailName Already Exists without overlapping date for save drug group details creation and verify correct status code with response body is returned
    Then User executes the delete existing DrugGroup query "deleteDrugGroupByName" and List name "QAAutomation%" to delete the record from the database
    Given User create the Create new group details data
      | mfrId    | drugGroupName      | drugGroupType |
      | ASTRA001 | QAAutomation_Drug1 | Rebateable    |
    Then User hits the "createNewDrugGroupDetails" with post request of CreateNewDrugGroup
    Then User verify create new drug group status code "200" for the response
    Then User verify the valid CreateNewDrugGroup Response body key "recordSaved" and expected value "true"
    Then User verify the valid CreateNewDrugGroup Response body key "recordUpdated" and expected value "false"
    Then User verify the valid Response body key "mfrDrugListId" and execute the query "QAAutomation_Drug1" matches the Drug groupID return from API and DB
    Then User executes the query "getMFR_DrugList_ID" by drugGroup name "QAAutomation_Drug1" to get MFR_DrugList_ID to save drug group details
    Then User executes the query "getDrugGroupRowKey" by drugGroup name "QAAutomation_Drug1" to get row key for drug group to save drug group details
    Given User create the save drug group details data
      | drugListId | ndcs | startDate | endDate | query         | columnName        | condition |
      |            |      |           |         | getListOfNdcs | DRUG_PRODUCT_CODE | valid     |
    Then User hits the "saveDrugGroupDetails" with post request of saveDrugGroupDetails API
    Then User verify save drug drug group status code "200" for the response
    Then User verify the NDC details And matches returned by API and DB by NDCJson path "ndcsSaveDrugGroupDetailsJSON" and columnName "saveDrugGroupDetailsDrugProductCodeColumName"
    Then User verify the NDC details Row Key And matches returned by API and DB by NDCJson path "rowKeySaveDrugGroupDetailsJSON" and columnName "saveDrugGroupDetailsRowKeyColumName"
    Then User executes the query "getMFR_DrugList_ID" by drugGroup name "QAAutomation_Drug1" to get MFR_DrugList_ID to save drug group details
    Then User executes the query "getDrugGroupRowKey" by drugGroup name "QAAutomation_Drug1" to get row key for drug group to save drug group details
    Given User create the save drug group details data
      | drugListId | drugRowKey | ndcs | startDate  | endDate    | query                 | columnName        | condition          |
      |            |            |      | 2023-01-10 | 2024-01-10 | getListOfExistingNdcs | DRUG_PRODUCT_CODE | nonOverLappingDate |
    Then User hits the "saveDrugGroupDetails" with post request of saveDrugGroupDetails API
    Then User verify save drug drug group status code "200" for the response
#    Then User verify the saveDrugGroupDetails valid Response body key "saveDrugGroupDetailsMessage" and expected value "SUCCESS" of string

  @Functional @Regression
  Scenario: Validate DrugListId doesn't exists in Drug Group for save drug group details creation and verify correct status code with response body is returned
    Then User executes the delete existing DrugGroup query "deleteDrugGroupByName" and List name "QAAutomation%" to delete the record from the database
    Given User create the Create new group details data
      | mfrId    | drugGroupName      | drugGroupType |
      | ASTRA001 | QAAutomation_Drug1 | Rebateable    |
    Then User hits the "createNewDrugGroupDetails" with post request of CreateNewDrugGroup
    Then User verify create new drug group status code "200" for the response
    Then User verify the valid CreateNewDrugGroup Response body key "recordSaved" and expected value "true"
    Then User verify the valid CreateNewDrugGroup Response body key "recordUpdated" and expected value "false"
    Then User verify the valid Response body key "mfrDrugListId" and execute the query "QAAutomation_Drug1" matches the Drug groupID return from API and DB
    Then User executes the query "getMFR_DrugList_ID" by drugGroup name "QAAutomation_Drug1" to get MFR_DrugList_ID to save drug group details
    Then User executes the query "getDrugGroupRowKey" by drugGroup name "QAAutomation_Drug1" to get row key for drug group to save drug group details
    Given User create the save drug group details data
      | drugListId | drugRowKey | ndcs | startDate  | endDate    | query                 | columnName        | condition         |
      | 47233333   |            |      | 2023-01-10 | 2024-01-10 | getListOfExistingNdcs | DRUG_PRODUCT_CODE | invalidDrugListId |
    Then User hits the "saveDrugGroupDetails" with post request of saveDrugGroupDetails API
    Then User verify save drug drug group status code "200" for the response
    Then User verify the saveDrugGroupDetails valid Response body key "message" and expected value "DrugListId '47233333' doesn't exists in Drug Group" of string

  @Functional @Regression
  Scenario: Validate Invalid NDC Not Exists In Medispan for save drug group details creation and verify correct status code with response body is returned
    Then User executes the delete existing DrugGroup query "deleteDrugGroupByName" and List name "QAAutomation%" to delete the record from the database
    Given User create the Create new group details data
      | mfrId    | drugGroupName      | drugGroupType |
      | ASTRA001 | QAAutomation_Drug1 | Rebateable    |
    Then User hits the "createNewDrugGroupDetails" with post request of CreateNewDrugGroup
    Then User verify create new drug group status code "200" for the response
    Then User verify the valid CreateNewDrugGroup Response body key "recordSaved" and expected value "true"
    Then User verify the valid CreateNewDrugGroup Response body key "recordUpdated" and expected value "false"
    Then User verify the valid Response body key "mfrDrugListId" and execute the query "QAAutomation_Drug1" matches the Drug groupID return from API and DB
    Then User executes the query "getMFR_DrugList_ID" by drugGroup name "QAAutomation_Drug1" to get MFR_DrugList_ID to save drug group details
    Then User executes the query "getDrugGroupRowKey" by drugGroup name "QAAutomation_Drug1" to get row key for drug group to save drug group details
    Given User create the save drug group details data
      | drugListId | drugRowKey | ndcs        | startDate  | endDate    | query                 | columnName        | condition   |
      |            |            | 22333344444 | 2023-01-10 | 2024-01-10 | getListOfExistingNdcs | DRUG_PRODUCT_CODE | invalidNDCS |
    Then User hits the "saveDrugGroupDetails" with post request of saveDrugGroupDetails API
    Then User verify save drug drug group status code "200" for the response
    #    Then User verify the saveDrugGroupDetails valid Response body key "saveDrugGroupDetailsMessage" and expected value "NDC Not Exists In Medispan" of string
    #    Then User verify the saveDrugGroupDetails valid Response body key "saveDrugGroupDetailsStatusCode" and expected value "500" of Integer

  @Functional @Regression
  Scenario: Invalid date where start should be less than the end date and verify correct status code with response body is returned
    Then User executes the delete existing DrugGroup query "deleteDrugGroupByName" and List name "QAAutomation%" to delete the record from the database
    Given User create the Create new group details data
      | mfrId    | drugGroupName      | drugGroupType |
      | ASTRA001 | QAAutomation_Drug1 | Rebateable    |
    Then User hits the "createNewDrugGroupDetails" with post request of CreateNewDrugGroup
    Then User verify create new drug group status code "200" for the response
    Then User verify the valid CreateNewDrugGroup Response body key "recordSaved" and expected value "true"
    Then User verify the valid CreateNewDrugGroup Response body key "recordUpdated" and expected value "false"
    Then User verify the valid Response body key "mfrDrugListId" and execute the query "QAAutomation_Drug1" matches the Drug groupID return from API and DB
    Then User executes the query "getMFR_DrugList_ID" by drugGroup name "QAAutomation_Drug1" to get MFR_DrugList_ID to save drug group details
    Then User executes the query "getDrugGroupRowKey" by drugGroup name "QAAutomation_Drug1" to get row key for drug group to save drug group details
    Given User create the save drug group details data
      | drugListId | drugRowKey | ndcs | startDate  | endDate    | query                 | columnName        | condition          |
      |            |            |      | 2024-01-10 | 2023-01-10 | getListOfExistingNdcs | DRUG_PRODUCT_CODE | nonOverLappingDate |
    Then User hits the "saveDrugGroupDetails" with post request of saveDrugGroupDetails API
    Then User verify save drug drug group status code "200" for the response
    Then User verify the saveDrugGroupDetails valid Response body key "message" and expected value "Invalid date where start should be less than the end date" of string

  @Functional @Regression
  Scenario: Invalid input drugRowKey and verify correct status code with response body is returned
    Then User executes the delete existing DrugGroup query "deleteDrugGroupByName" and List name "QAAutomation%" to delete the record from the database
    Given User create the Create new group details data
      | mfrId    | drugGroupName      | drugGroupType |
      | ASTRA001 | QAAutomation_Drug1 | Rebateable    |
    Then User hits the "createNewDrugGroupDetails" with post request of CreateNewDrugGroup
    Then User verify create new drug group status code "200" for the response
    Then User verify the valid CreateNewDrugGroup Response body key "recordSaved" and expected value "true"
    Then User verify the valid CreateNewDrugGroup Response body key "recordUpdated" and expected value "false"
    Then User verify the valid Response body key "mfrDrugListId" and execute the query "QAAutomation_Drug1" matches the Drug groupID return from API and DB
    Then User executes the query "getMFR_DrugList_ID" by drugGroup name "QAAutomation_Drug1" to get MFR_DrugList_ID to save drug group details
    Then User executes the query "getDrugGroupRowKey" by drugGroup name "QAAutomation_Drug1" to get row key for drug group to save drug group details
    Given User create the save drug group details data
      | drugListId | ndcs | startDate  | endDate    | query                 | columnName        | condition             |
      |            |      | 2023-01-10 | 2024-01-10 | getListOfExistingNdcs | DRUG_PRODUCT_CODE | invalidDrugListRowKey |
    Then User hits the "saveDrugGroupDetails" with post request of saveDrugGroupDetails API
    Then User verify save drug drug group status code "200" for the response
#    Then User verify the saveDrugGroupDetails valid Response body key "message" and expected value "Invalid input: drugRowKey" of string
    Then User verify the saveDrugGroupDetails valid Response body key "message" and expected value "System error, contact support team" of string