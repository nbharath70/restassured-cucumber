#### This feature consists of ALl the Scenarios of fetch Comments API
  Feature: API to fetch Comments of Task

      Background:
      Given User creates the Contract and send it to rebate ops
      And User creates the request Body for Capture Comments API
          |category|event|comment|
          |Contract|SendToRebateOPs|testComments|
      Then User hits the "captureCommentsAPI" API with request body
      And User verifies the CaptureAPI StatusCode is "200"
      Then User creates the Request Body for fetchComments API

      @Smoke
      Scenario: hit the fetchComments API and validate the Status code
      And User hits the API "fetchComments" API with request body
      Then User verifies the FetchCommentsAPI StatusCode is "200"
      Then User hits the "discardContractResource" API and deletes the contract created
      Then User deletes the contract using query "sqlToDeleteQAAutomationContractWithContractId"
      Then User executes the query "deleteComments" and deletes the comments
      Then User deletes the task associated with this contract "deleteTaskFromFlowable"

      Scenario: hit the Capture Comments API and validate the Status code
          And User hits the API "fetchComments" API with request body
          Then User verifies fetchComments API the response in JSON Format
          Then User hits the "discardContractResource" API and deletes the contract created
          Then User deletes the contract using query "sqlToDeleteQAAutomationContractWithContractId"
          Then User executes the query "deleteComments" and deletes the comments
          Then User deletes the task associated with this contract "deleteTaskFromFlowable"

      Scenario: hit the Capture Comments API and Validate the Response with DB
          And User hits the API "fetchComments" API with request body
          Then User verifies the Response With DB using query "fetchCommentsDetails"
          Then User hits the "discardContractResource" API and deletes the contract created
          Then User deletes the contract using query "sqlToDeleteQAAutomationContractWithContractId"
          Then User executes the query "deleteComments" and deletes the comments
          Then User deletes the task associated with this contract "deleteTaskFromFlowable"