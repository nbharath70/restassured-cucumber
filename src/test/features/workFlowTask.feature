# This feature file defines the automation scenarios to be developed for API to Update a Mfr Contract and Send to Rebate Ops
Feature:Retrieve and validate the work flow task API
  @Smoke
  Scenario: Validate work flow task response in JSON format and verify correct status code with response body is returned
    And User fetchs all the Details from the Result and create request payload for workflow task
    Then User hits the "workFlowTasks" work task API post request
    Then User verifies work flow task the Status Code of the Response is "200"
    Then User verifies the work flow task response in JSON format

  Scenario: Validate work flow response and verify correct status code with response body is returned
    And User fetchs all the Details from the Result and create request payload for workflow task
    Then User hits the "workFlowTasks" work task API post request
    Then User verifies work flow task the Status Code of the Response is "200"
    Then User verifies the work flow task response in JSON format
    Then User verifies the API response validation

