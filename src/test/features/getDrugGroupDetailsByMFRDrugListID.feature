Feature:Retrieve and validate the Drug group details by a given MFRDrugListID

#  @Smoke
#  Scenario: Get drug group details for a valid MFR_DrugList_ID and verify the correct status code is returned
#    Given User executes query "getMFRDrugListRowKey" and gets MFRDrugListRowKey from column "columnMFRDrugListRowKey"
#    And User Hits API "getDrugGroupDetailsByMFRDrugListRowKey" with Get request for getDrugGroupdetails
#    Then User verifies the valid status code "200" in getDrugGroupdetails API response

  @Regression @Functional
  Scenario: Validate drug group details response and verify it from DB
    Given User executes query "getMFRDrugListRowKey" and gets MFRDrugListRowKey from column "columnMFRDrugListRowKey"
    And User Hits API "getDrugGroupDetailsByMFRDrugListRowKey" with Get request for getDrugGroupdetails
    Then User executes query "sqlToFetchDrugDetailsOfDruglist" and gets drug details
    Then User verifies drug details by drug group row key API response with DB response
