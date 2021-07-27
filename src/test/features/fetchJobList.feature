## In This Feature file All the Scenarios for FtechJobList API
Feature: Create and Validate the BenefitRule for a Contract

  @Smoke
  Scenario: Hit the Fetch JobList API and Check the API response Status Code is 200
    Given User hits the fetchJobList API with EndPoint "fetchJobList" with get Request
    Then User verifies the Status Code is "200"

  @Smoke
  Scenario: Verify that JSON Response of the API
    Given User hits the fetchJobList API with EndPoint "fetchJobList" with get Request
    Then User verifies the Status Code is "200"
    And User verifies the Response in JSON Format

  @Regression@Functional
  Scenario: Verify Process Job Run ID from Response
    Given User hits the fetchJobList API with EndPoint "fetchJobList" with get Request
    Then User verifies the Status Code is "200"
    And User Runs the Query "fetchJobListAPI" to fetch Coulumn "jobRunIDColumnNameForFetchJobListAPI" and Verify with Response by JSONpath "jobRunIDJSONForFetchJobListAPI"

  @Regression@Functional
  Scenario: Verify LifeCylce Status Of Fetch JObList API from Response
    Given User hits the fetchJobList API with EndPoint "fetchJobList" with get Request
    Then User verifies the Status Code is "200"
    And User Runs the Query "fetchJobListAPI" to fetch Coulumn "lifeCyceStatusColumnNameForFetchJobListAPI" and Verify with Response by JSONpath "lifeCyceStatusJSONForFetchJobListAPI"

  @Regression@Functional
  Scenario: Verify ProgramName Of Fetch JObList API from Response
    Given User hits the fetchJobList API with EndPoint "fetchJobList" with get Request
    Then User verifies the Status Code is "200"
    And User Runs the Query "fetchJobListAPI" to fetch Coulumn "programNameColumnNameForFetchJobListAPI" and Verify with Response by JSONpath "programmNameJSONForFetchJobListAPI"

  @Regression@Functional
  Scenario: Verify Manufacturer Name Of Fetch JObList API from Response
    Given User hits the fetchJobList API with EndPoint "fetchJobList" with get Request
    Then User verifies the Status Code is "200"
    And User Runs the Query "fetchJobListAPI" to fetch Coulumn "manufacturerNameColumnNameForFetchJobListAPI" and Verify with Response by JSONpath "manufacturerNameJSONForFetchJobListAPI"

  @Regression@Functional
  Scenario: Verify JobScheduled Date Of Fetch JObList API from Response
    Given User hits the fetchJobList API with EndPoint "fetchJobList" with get Request
    Then User verifies the Status Code is "200"
    And User Runs the Query "fetchJobListAPI" to fetch Coulumn "jobScheduledDateColumnNameForFetchJobListAPI" and Verify with Response by JSONpath "jobScheduledDateJSONForFetchJobListAPI"

  @Regression@Functional
  Scenario: Verify JobStatus Date Of Fetch JObList API from Response
    Given User hits the fetchJobList API with EndPoint "fetchJobList" with get Request
    Then User verifies the Status Code is "200"
    And User Runs the Query "fetchJobListAPI" to fetch Coulumn "programRowKeyColumnNameForFetchJobListAPI" and Verify with Response by JSONpath "programRowKeyJSONForFetchJobListAPI"

  @Regression@Functional
  Scenario: Verify JOb Scheduled by for Fetch JobList API from Response
    Given User hits the fetchJobList API with EndPoint "fetchJobList" with get Request
    Then User verifies the Status Code is "200"
    And User Runs the Query "fetchJobListAPI" to fetch Coulumn "jobScheduledByColumnNameForFetchJobListAPI" and Verify with Response by JSONpath "jobScheduledByJSONForFetchJobListAPI"

  @Regression@Functional
  Scenario: Verify JobStatus for Fetch JobList API from Response
    Given User hits the fetchJobList API with EndPoint "fetchJobList" with get Request
    Then User verifies the Status Code is "200"
    And User Runs the Query "fetchJobListAPI" to fetch Coulumn "jobStatusColumnNameForFetchJobListAPI" and Verify with Response by JSONpath "jobStatusJSONForFetchJobListAPI"

    @NonAutomated
  Scenario: Verify LOB for Fetch JobList API from Response
      Given This is Not Automated due to SQL Tool Compatibility