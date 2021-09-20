Feature: API to capture Comments of Flowable
  @Smoke
  Scenario: hit the Capture Comments API and validate the Status code
    Given User creates the contract and send that contract to rebateOps
    And User creates the request Body of API for Capture Comments API
      |category|event|comment|
      |Contract|SendToRebateOPs|testComments|
    Then User hits the API "captureCommentsAPI" with request body
    And user verifies the API StatusCode is "200"
    Then User hits the "discardContractResource" API and discards the contract
    Then User runs the query "sqlToDeleteQAAutomationContractWithContractId" and delete the contract created
    Then User executes the query "deleteComments" and Deletes the Comments in comments Tables
    Then User deletes the task associated with this task with query "deleteTaskFromFlowable"

  Scenario: hit the API and Validate the Response is in JSON
    Given User creates the contract and send that contract to rebateOps
    And User creates the request Body of API for Capture Comments API
      |category|event|comment|
      |Contract|SendToRebateOPs|testComments|
    Then User hits the API "captureCommentsAPI" with request body
    And User verifies the response is json format
    Then User hits the "discardContractResource" API and discards the contract
    Then User runs the query "sqlToDeleteQAAutomationContractWithContractId" and delete the contract created
    Then User executes the query "deleteComments" and Deletes the Comments in comments Tables
    Then User deletes the task associated with this task with query "deleteTaskFromFlowable"

  Scenario: hit the API and Validate the Response in DB and Check the Check the records Created
    Given User creates the contract and send that contract to rebateOps
    And User creates the request Body of API for Capture Comments API
      |category|event|comment|
      |Contract|SendToRebateOPs|testComments|
    Then User hits the API "captureCommentsAPI" with request body
    Then User verifies the Response with DB using query "commentDetails"
    Then User hits the "discardContractResource" API and discards the contract
    Then User runs the query "sqlToDeleteQAAutomationContractWithContractId" and delete the contract created
    Then User executes the query "deleteComments" and Deletes the Comments in comments Tables
    Then User deletes the task associated with this task with query "deleteTaskFromFlowable"


  Scenario: hit the Capture Comments API with invalid Catagory ID
    Given User creates the contract and send that contract to rebateOps
    And User creates the Invalid request Body of API for Capture Comments API
      |category|catagoryID|event|comment|ProcessInstance|
      |Contract|ASTRA001 |SendToRebateOPs|testComments|ABC |
    Then User hits the API "captureCommentsAPI" with Invalid request body
    And user verifies the API StatusCode is "200"
    Then verify invalid Message from Response
    Then User hits the "discardContractResource" API and discards the contract
    Then User runs the query "sqlToDeleteQAAutomationContractWithContractId" and delete the contract created
    Then User executes the query "deleteComments" and Deletes the Comments in comments Tables
    Then User deletes the task associated with this task with query "deleteTaskFromFlowable"