Feature:Retrieve and validate the Drug group details by a given MFRDrugListID

  @Smoke
  Scenario: Get drug group details for a valid MFR_DrugList_ID and verify the correct status code is returned
    Given User executes query "getMFRDrugListID" and gets MFRDrugListID from column "columnMFRDrugListID"
    And User Hits API "getDrugGroupDetailsByMFRDrugListID" with Get request for getDrugGroupdetails
    Then User verifies the valid status code "200" in getDrugGroupdetails API response

  @Regression @Functional
  Scenario Outline: Validate drug group details response and verify it from DB
    Given User executes query "getMFRDrugListID" and gets MFRDrugListID from column "columnMFRDrugListID"
    And User Hits API "getDrugGroupDetailsByMFRDrugListID" with Get request for getDrugGroupdetails
    Then User verifies the valid status code "200" in getDrugGroupdetails API response
    Then User executes the query "<jsonPath>" & columnName "<columnName>" And matches the DrugGroupDetails returned by API and DB

    Examples:
      | columnName                  | jsonPath                            |
      | getDrugGroupRowKey          | getDrugGroupRowKeyJsonPath          |
      | getDrugGroupMFR_DrugList_ID | getDrugGroupMfrDrugListIdJsonPath   |
      | getDrugGroupListType        | getDrugGroupTypeJsonPath            |
      | getDrugGroupListName        | getDrugGroupNameJsonPathJsonPath    |
      | getDrugGroupLifeCycleStatus | getDrugGroupLifecycleStatusJsonPath |
#  getDrugGroupRowKey=Row_Key
#  getDrugGroupMFR_DrugList_ID=MFR_DrugList_ID
#  getDrugGroupListType=List_Type
#  getDrugGroupListName=List_Name
#  getDrugGroupLifeCycleStatus=Life_Cycle_Status
#
#  getDrugGroupRowKeyJsonPath=$..rowKey
#  getDrugGroupMfrDrugListIdJsonPath=$..mfrDrugListId
#  getDrugGroupNameJsonPathJsonPath=$..drugGroupName
#  getDrugGroupTypeJsonPath=$..drugGroupType
#  getDrugGroupTypeDescJsonPath=$..drugGroupTypeDesc
#  getDrugGroupLifecycleStatusJsonPath=$..lifecycleStatus
