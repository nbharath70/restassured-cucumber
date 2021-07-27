#This feature file defines the automation scenarios to be developed for get job status summary
Feature:Verify the no of Scheduled,running,finished,canceled,aborted job status
  @Smoke
  Scenario: Verify the Job Status Summary API response code is 200
    Given User hits the "getJobStatus" end point with get API request
    Then User verifies the valid status code "200" in the API response
  @Smoke
  Scenario: Verify the Job Status Summary API response is in JSON format
    Given User hits the "getJobStatus" end point with get API request
    Then User verifies the valid status code "200" in the API response
    And  Job status summary API response is in JSON format

  @Regression @Functional
  Scenario: Verify the no of scheduled job status
    Given User hits the "getJobStatus" end point with get API request
    Then User verifies the valid status code "200" in the API response
    And user runs "scheduledJobs" query and asserts the Scheduled job count from API with DB

  @Regression @Functional
  Scenario: Verify the no of running job status
    Given User hits the "getJobStatus" end point with get API request
    Then User verifies the valid status code "200" in the API response
    And user runs "runningJobs" query and asserts the running job count from API with DB

  @Regression @Functional
  Scenario: Verify the no of finished job status
    Given User hits the "getJobStatus" end point with get API request
    Then User verifies the valid status code "200" in the API response
    And user runs "finishedJobs" query and asserts the finished job count from API with DB

  @Regression @Functional
  Scenario: Verify the no of cancelled job status
    Given User hits the "getJobStatus" end point with get API request
    Then User verifies the valid status code "200" in the API response
    And user runs "cancelledJobs" query and asserts the cancelled job count from API with DB

  @Regression @Functional
  Scenario: Verify the no of aborted job status
    Given User hits the "getJobStatus" end point with get API request
    Then User verifies the valid status code "200" in the API response
    And user runs "abortedJobs" query and asserts the aborted job count from API with DB