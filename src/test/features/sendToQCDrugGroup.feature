Feature:Retrieve and validate the Initiate New Manufacture Contract

  Scenario: Hit Send to QC API and verify the Status code
    Given User creates the DrugGroupUsing this data
      | mfrId   | drugGroupName                   | drugGroupType |
      | ABOT001 | QaTestAutomationDataForWorkFlow | Rebateable    |
    And User hits the API "createNewDrugGroupDetails" and create the DrugGroup and get the DrugGroupRowkey "getRowKeyForCreateDrugGroup"
    And User create data to Add Some Drugs to created DruGroup
      | ndcs | startDate | endDate |
      |      |           |         |
    And User hits the API "saveDrugGroupDetails"to add drugs to DrugGroup
    And User creates the Data to send the DrugGroup to Ops Assignee
      | opsAssignee       | opsQc         |
      | Bharath Narasimha | Rabbani Shaik |
    When User hits the SendToQC for DrugGroup API "sendToQCDrugGroup"
    And User check the Status code of SendingDrugGroup to ops assignee is "200"
    Then User discards and deletes the DrugGroup and DrugDetails created


  Scenario: User hit the send to QC API and verify that life cycle Status has been Changed to InReview
    Given User creates the DrugGroupUsing this data
      | mfrId   | drugGroupName                   | drugGroupType |
      | ABOT001 | QaTestAutomationDataForWorkFlow | Rebateable    |
    And User hits the API "createNewDrugGroupDetails" and create the DrugGroup and get the DrugGroupRowkey "getRowKeyForCreateDrugGroup"
    And User create data to Add Some Drugs to created DruGroup
      | ndcs | startDate | endDate |
      |      |           |         |
    And User hits the API "saveDrugGroupDetails"to add drugs to DrugGroup
    And User creates the Data to send the DrugGroup to Ops Assignee
      | opsAssignee       | opsQc         |
      | Bharath Narasimha | Rabbani Shaik |
    When User hits the SendToQC for DrugGroup API "sendToQCDrugGroup"
    And User checks the Life cycle Status of the drugGroup is changes to "InReview"
    Then User discards and deletes the DrugGroup and DrugDetails created


  Scenario: User hits the send to QC API for DrugGroup which is in New Status
    Given User creates the DrugGroupUsing this data
      | mfrId   | drugGroupName                   | drugGroupType |
      | ABOT001 | QaTestAutomationDataForWorkFlow | Rebateable    |
    And User hits the API "createNewDrugGroupDetails" and create the DrugGroup and get the DrugGroupRowkey "getRowKeyForCreateDrugGroup"
    And User creates the Data to send the DrugGroup to Ops Assignee
      | opsAssignee | opsQc |
      |             |       |
    When User hits the SendToQC for DrugGroup API "sendToQCDrugGroup"
    And Then user verifies the response "errorMessageforSendingToQCDrugGroupInNewStatus"
    Then User discards and deletes the DrugGroup and DrugDetails created



#  Scenario: User hits the send to QC API for DrugGroup with invalid Ops QC name
#    Given User creates the DrugGroupUsing this data
#      | mfrId   | drugGroupName                   | drugGroupType |
#      | ABOT001 | QaTestAutomationDataForWorkFlow | Rebateable    |
#    And User hits the API "createNewDrugGroupDetails" and create the DrugGroup and get the DrugGroupRowkey "getRowKeyForCreateDrugGroup"
#    And User create data to Add Some Drugs to created DruGroup
#      | ndcs | startDate | endDate |
#      |      |           |         |
#    And User hits the API "saveDrugGroupDetails"to add drugs to DrugGroup
#    Given User creates the Data to send the DrugGroup to Ops Assignee
#      | opsAssignee | opsQc  |
#      |             | Golden |
#    When User hits the SendToQC for DrugGroup API "sendToQCDrugGroup"
#    And Then user verifies the response
#    Then User discards and deletes the DrugGroup and DrugDetails created
#
#
#
#  Scenario: User hits the send to QC API for DrugGroup with invalid Ops assignee name
#    Given User creates the DrugGroupUsing this data
#      | mfrId   | drugGroupName                   | drugGroupType |
#      | ABOT001 | QaTestAutomationDataForWorkFlow | Rebateable    |
#    And User hits the API "createNewDrugGroupDetails" and create the DrugGroup and get the DrugGroupRowkey "getRowKeyForCreateDrugGroup"
#    And User create data to Add Some Drugs to created DruGroup
#      | ndcs | startDate | endDate |
#      |      |           |         |
#    And User hits the API "saveDrugGroupDetails"to add drugs to DrugGroup
#    And User creates the Data to send the DrugGroup to Ops Assignee
#      | opsAssignee | opsQc |
#      | Golden      |       |
#    When User hits the SendToQC for DrugGroup API "sendToQCDrugGroup"
#    And Then user verifies the response
#    Then User discards and deletes the DrugGroup and DrugDetails created
#


  Scenario: User hits the send to QC API multiple times and check the reponse
    Given User creates the DrugGroupUsing this data
      | mfrId   | drugGroupName                   | drugGroupType |
      | ABOT001 | QaTestAutomationDataForWorkFlow | Rebateable    |
    And User hits the API "createNewDrugGroupDetails" and create the DrugGroup and get the DrugGroupRowkey "getRowKeyForCreateDrugGroup"
    And User create data to Add Some Drugs to created DruGroup
      | ndcs | startDate | endDate |
      |      |           |         |
    And User hits the API "saveDrugGroupDetails"to add drugs to DrugGroup
    And User creates the Data to send the DrugGroup to Ops Assignee
      | opsAssignee       | opsQc         |
      | Bharath Narasimha | Rabbani Shaik |
    When User hits the SendToQC for DrugGroup API "sendToQCDrugGroup"
    When User hits the SendToQC for DrugGroup API "sendToQCDrugGroup"
    And Then user verifies the response "errorMessageForSendToQCDruGroup"
    Then User discards and deletes the DrugGroup and DrugDetails created





