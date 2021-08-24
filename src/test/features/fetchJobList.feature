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

  @Regression @Functional
  Scenario: Verify Process Job Run ID from Response
    Given User hits the fetchJobList API with EndPoint "fetchJobList" with get Request
    Then User verifies the Status Code is "200"
    And Then User hits the Query "fetchJobListAPI" and evaluates the Response with DB "resultColumnName"

   @NonAutomated
  Scenario: Verify LOB for Fetch JobList API from Response
      Given This is Not Automated due to SQL Tool Compatibility