#### This Feature file Cover Scenarios of Terming the Drugs from Drug group
Feature: Terming the Drugs From the Drug Group API

  @Smoke
  Scenario: User Terms the Drugs from Drug group and Check the Status code of the Drug group
    Given User Runs the Query "fetchDrugListDetail" and get valid Drug List Detail with columName "resultColumnName"
    And User fetchs all the Details from the Result and createRequestBody
    When User Hits the API endpoint "termDrugsFromDrugGroupAPI" with Post Request along with RequestBody
    Then User Checks the Status Code of the Response is "200"
    Then User Runs the Query "updatingEndDateQuery" to Update the EndDate and "updateLifeCycleStatus" Life Cycle Status of DrugList Detail

  @Smoke
  Scenario: User Terms the Drugs from Drug group and Check the Status respons is in Proper JSON format
    Given User Runs the Query "fetchDrugListDetail" and get valid Drug List Detail with columName "resultColumnName"
    And User fetchs all the Details from the Result and createRequestBody
    When User Hits the API endpoint "termDrugsFromDrugGroupAPI" with Post Request along with RequestBody
    Then User Checks the Status Code of the Response is "200"
    Then User verifies the Response in JSON format
    Then User Runs the Query "updatingEndDateQuery" to Update the EndDate and "updateLifeCycleStatus" Life Cycle Status of DrugList Detail

  @Regression @Functional
  Scenario: User terms only one Drug from the Drug group
    Given User Runs the Query "fetchDrugListDetail" and get valid Drug List Detail with columName "resultColumnName"
    And User fetchs all the Details from the Result and createRequestBody
    When User Hits the API endpoint "termDrugsFromDrugGroupAPI" with Post Request along with RequestBody
    Then User Runs the Query"lifeCycleStatusCheckQuery" and Check the LifeCycle Status in Dbcolumn "expectedlifeCycleStatus" as "Termed"
    Then User Checks the Status Code of the Response is "200"
    Then User Runs the Query "updatingEndDateQuery" to Update the EndDate and "updateLifeCycleStatus" Life Cycle Status of DrugList Detail

  @Regression @Functional
  Scenario: User terms only two Drug from the Drug group
    Given User Runs the Query "fetchTwoNDCFromDrugList" and get valid Drug List Detail with columName "resultColumnName"
    And User fetchs all the Details from the Result and createRequestBody
    When User Hits the API endpoint "termDrugsFromDrugGroupAPI" with Post Request along with RequestBody
    Then User Runs the Query"lifeCycleStatusCheckQuery" and Check the LifeCycle Status in Dbcolumn "expectedlifeCycleStatus" as "Termed"
    Then User Checks the Status Code of the Response is "200"
    Then User Runs the Query "updatingEndDateQuery" to Update the EndDate and "updateLifeCycleStatus" Life Cycle Status of DrugList Detail


