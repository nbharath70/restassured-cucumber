Feature:Retrieve and validate the cancel job

  @Smoke
  Scenario: Validate the cancel job for scheduled(0) status and verify correct status code with response body is returned
    Given User executes the query "getPROCESSING_JOB_ID" to get the processing job id for processing job status "0"
    When User hits the "cancelJob" with post request for cancel job
    Then User verifies the response is in JSON format for cancel job API
    Then User verify cancelJob status code "200" for the response
    Then User verify API response isCanceled is true
    Then User execute the query to update existing processing job status to the database

  @Smoke
  Scenario: Validate the cancel job for Running(1) status and verify correct status code with response body
    Given User executes the query "getPROCESSING_JOB_ID" to get the processing job id for processing job status "1"
    When User hits the "cancelJob" with post request for cancel job
    Then User verifies the response is in JSON format for cancel job API
    Then User verifies cancelJob status code "200" for the response
    Then User verifies API response isCanceled is true
    Then User execute the query to update existing processing job status to the database

  @Functional @Regression
  Scenario: Verify other than scheduled and running jobs are allowed for cancellation and verify correct status code with response body
    Given User executes the query "getPROCESSING_JOB_ID" to get the processing job id for processing job status "5"
    When User hits the "cancelJob" with post request for cancel job
    Then User verifies the response is in JSON format for cancel job API
    Then User verifies cancelJob status code "200" for the response
    Then User verifies API response isCanceled is false
    Then User verifies API response error message
    Then User execute the query to update existing processing job status to the database

  @Functional @Regression
  Scenario: Invalid processing job ID and verify correct status code with response body
    Given User enter the invalid processing job ID
    When User hits the "cancelJob" with post request for cancel job
    Then User verifies the response is in JSON format for cancel job API
    Then User verifies cancelJob status code "200" for the response
    Then User verifies API response isCanceled is false
    Then User verify API response error message no job exists