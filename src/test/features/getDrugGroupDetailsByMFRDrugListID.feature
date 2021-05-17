Feature:Retrieve and validate the Drug group details by a given MFRDrugListID

  Scenario: Get drug group details for a valid MFR_DrugList_ID and verify the correct status code is returned
    Given User executes query "getMFRDrugListID" and gets MFRDrugListID from column "columnMFRDrugListID"
    And User Hits API "getDrugGroupDetailsByMFRDrugListID" with Get request for getDrugGroupdetails
    Then User verifies the valid status code "200" in getDrugGroupdetails API response

  Scenario Outline: Validate drug group details response and verify it from DB
    Given User executes query "getMFRDrugListID" and gets MFRDrugListID from column "columnMFRDrugListID"
    And User Hits API "getDrugGroupDetailsByMFRDrugListID" with Get request for getDrugGroupdetails
    Then User verifies the valid status code "200" in getDrugGroupdetails API response
    Then User executes the query "<Query>" & columnName "<columnName>" And matches the DrugGroupDetails returned by API and DB

    Examples:
      | Query                      | columnName              |
      | getMfrName                 | mfrName                 |
      | getStartDate               | startDate               |
      | getEndDate                 | endDate                 |
      | getBrandName               | brandName               |
      | getNdc                     | ndc                     |
      | getModifiedDrug            | modifiedDrug            |
      | getGeneric                 | generic                 |
      | getStrength                | strength                |
      | getDosage                  | dosage                  |
      | getBrandOrGenericIndicator | brandOrGenericIndicator |
      | getMedispanStartDate       | medispanStartDate       |



