Feature:Retrieve and validate the list of valid getSelect Options of Code Dictionary Details
  @Smoke
  Scenario: get All valid Code_Dictionary details and verify correct statusCode
    Given User hits the "getSelectOptionsResource" with get request
    Then User verify status code "200" for the response

  @Smoke
  Scenario: get All valid Code_Dictionary details and verify Response JSON body and JSON formate
    Given User hits the "getSelectOptionsResource" with get request
    Then The response is in JSON format

  @Regression @Functional
  Scenario: Validate CodeValue and CodeDescription of given CodeType is returned from API and DB
    Given User hits the "getSelectOptionsResource" with get request
    Then User Verifies with DB using Query "getSelectOptionsQuery" and ColumnName "resultColumnName"





