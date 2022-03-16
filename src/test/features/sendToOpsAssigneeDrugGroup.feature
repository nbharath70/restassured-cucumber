Feature:Retrieve and validate the Initiate New Manufacture Contract

Scenario: Hit Send to QC API and validate the Response is proper

  Given User creates the DrugGroupUsing this data
    | mfrId    | drugGroupName      | drugGroupType |
    | ABOT001 | QaTestAutomationDataForWorkFlow | Rebateable    |
  And User Add Some Drugs to created DruGroup
    | drugListId | ndcs | startDate | endDate | query         | columnName        |
    |            |      |           |         | getListOfNdcs | DRUG_PRODUCT_CODE |


