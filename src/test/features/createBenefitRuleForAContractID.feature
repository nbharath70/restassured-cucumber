# In This Feature file All the Scenarios for CreateBenefitRuleForAContract API exists
Feature: Create and Validate the BenefitRule for a Contract
  @Smoke
  Scenario: validate BenefitRule creation for Contract and verify correct status code with response body is returned
    Given User runs the Query "contractIdForCreateBenefitRuleForContract"and Get the Contract ContractId
    And User runs Update Query to Update LifeCycleStatus of Contract ID of ContractHeader "updateContractHeaderLifeCycleStatusToInProgress" and ContractDetails "updateContractDetailLifeCycleStausToInProgress" to InProgress
    And User create the Benefitruledata
      | benefitRuleName | formularyType | aboveValue | belowValue |
      | BenefitRule123  | Closed        |            |            |
    When User hits createBenefitRuleAPI "createBenefitRuleForAContract" with Post Request
    Then User Verifies the Status Code is "200"
    Then user runs Deletes the BenefitRule Created by Executing the Query "deleteBenefitRulesCreated"
    Then User User Redo's Change made to Lifecycle Status for both ContractHeader "updateContractHeaderLifeCycleStatusToNew" and ContractDetail "updateContractDetailLifeCycleStausToNew"

  @Functional
  Scenario: Create BenefitRule for Open formulary Type and check the Response
    Given User runs the Query "contractIdForCreateBenefitRuleForContract"and Get the Contract ContractId
    And User runs Update Query to Update LifeCycleStatus of Contract ID of ContractHeader "updateContractHeaderLifeCycleStatusToInProgress" and ContractDetails "updateContractDetailLifeCycleStausToInProgress" to InProgress
    And User create the Benefitruledata
      | benefitRuleName | formularyType | aboveValue | belowValue |
      | BenefitRule123  | Open          |            |            |
    When User hits createBenefitRuleAPI "createBenefitRuleForAContract" with Post Request
    Then user runs Deletes the BenefitRule Created by Executing the Query "deleteBenefitRulesCreated"
    Then User User Redo's Change made to Lifecycle Status for both ContractHeader "updateContractHeaderLifeCycleStatusToNew" and ContractDetail "updateContractDetailLifeCycleStausToNew"
    Then User verify the valid Response body key of CreateBenefitRuleAPI "recordSavingStatus" and expected value "true"

  @Functional
  Scenario: Create BenefitRule for Closed formulary Type and check the Response
    Given User runs the Query "contractIdForCreateBenefitRuleForContract"and Get the Contract ContractId
    And User runs Update Query to Update LifeCycleStatus of Contract ID of ContractHeader "updateContractHeaderLifeCycleStatusToInProgress" and ContractDetails "updateContractDetailLifeCycleStausToInProgress" to InProgress
    And User create the Benefitruledata
      | benefitRuleName | formularyType | aboveValue | belowValue |
      | BenefitRule123  | Closed        |            |            |
    When User hits createBenefitRuleAPI "createBenefitRuleForAContract" with Post Request
    Then user runs Deletes the BenefitRule Created by Executing the Query "deleteBenefitRulesCreated"
    Then User User Redo's Change made to Lifecycle Status for both ContractHeader "updateContractHeaderLifeCycleStatusToNew" and ContractDetail "updateContractDetailLifeCycleStausToNew"
    Then User verify the valid Response body key of CreateBenefitRuleAPI "recordSavingStatus" and expected value "true"

 @Functional
  Scenario: Create BenefitRule for Open&Closed formulary Type and check the Response
    Given User runs the Query "contractIdForCreateBenefitRuleForContract"and Get the Contract ContractId
    And User runs Update Query to Update LifeCycleStatus of Contract ID of ContractHeader "updateContractHeaderLifeCycleStatusToInProgress" and ContractDetails "updateContractDetailLifeCycleStausToInProgress" to InProgress
    And User create the Benefitruledata
      | benefitRuleName | formularyType | aboveValue | belowValue |
      | BenefitRule123  | OpenClosed    |            |            |
    When User hits createBenefitRuleAPI "createBenefitRuleForAContract" with Post Request
    Then user runs Deletes the BenefitRule Created by Executing the Query "deleteBenefitRulesCreated"
    Then User User Redo's Change made to Lifecycle Status for both ContractHeader "updateContractHeaderLifeCycleStatusToNew" and ContractDetail "updateContractDetailLifeCycleStausToNew"
    Then User verify the valid Response body key of CreateBenefitRuleAPI "recordSavingStatus" and expected value "true"

  @Functional
  Scenario: Create BenefitRule for Incentive formularyType with Above Value and check the Response
    Given User runs the Query "contractIdForCreateBenefitRuleForContract"and Get the Contract ContractId
    And User runs Update Query to Update LifeCycleStatus of Contract ID of ContractHeader "updateContractHeaderLifeCycleStatusToInProgress" and ContractDetails "updateContractDetailLifeCycleStausToInProgress" to InProgress
    And User create the Benefitruledata
      | benefitRuleName | formularyType | aboveValue | belowValue |
      | BenefitRule123  | Incentive     | 22         |            |
    When User hits createBenefitRuleAPI "createBenefitRuleForAContract" with Post Request
    Then user runs Deletes the BenefitRule Created by Executing the Query "deleteBenefitRulesCreated"
    Then User User Redo's Change made to Lifecycle Status for both ContractHeader "updateContractHeaderLifeCycleStatusToNew" and ContractDetail "updateContractDetailLifeCycleStausToNew"
    Then User verify the valid Response body key of CreateBenefitRuleAPI "recordSavingStatus" and expected value "true"

  @Regression @Functional
  Scenario: Create BenefitRule for Incentive formularyType with below Value and check the Response
    Given User runs the Query "contractIdForCreateBenefitRuleForContract"and Get the Contract ContractId
    And User runs Update Query to Update LifeCycleStatus of Contract ID of ContractHeader "updateContractHeaderLifeCycleStatusToInProgress" and ContractDetails "updateContractDetailLifeCycleStausToInProgress" to InProgress
    And User create the Benefitruledata
      | benefitRuleName | formularyType | aboveValue | belowValue |
      | BenefitRule123  | Incentive     |            | 98         |
    When User hits createBenefitRuleAPI "createBenefitRuleForAContract" with Post Request
    Then user runs Deletes the BenefitRule Created by Executing the Query "deleteBenefitRulesCreated"
    Then User User Redo's Change made to Lifecycle Status for both ContractHeader "updateContractHeaderLifeCycleStatusToNew" and ContractDetail "updateContractDetailLifeCycleStausToNew"
    Then User verify the valid Response body key of CreateBenefitRuleAPI "recordSavingStatus" and expected value "true"

  @Regression @Functional
  Scenario: Create BenefitRule for Incentive formularyType withboth above and below Value and check the Response
    Given User runs the Query "contractIdForCreateBenefitRuleForContract"and Get the Contract ContractId
    And User runs Update Query to Update LifeCycleStatus of Contract ID of ContractHeader "updateContractHeaderLifeCycleStatusToInProgress" and ContractDetails "updateContractDetailLifeCycleStausToInProgress" to InProgress
    And User create the Benefitruledata
      | benefitRuleName | formularyType | aboveValue | belowValue |
      | BenefitRule123  | Incentive     | 25         | 98         |
    When User hits createBenefitRuleAPI "createBenefitRuleForAContract" with Post Request
    Then user runs Deletes the BenefitRule Created by Executing the Query "deleteBenefitRulesCreated"
    Then User User Redo's Change made to Lifecycle Status for both ContractHeader "updateContractHeaderLifeCycleStatusToNew" and ContractDetail "updateContractDetailLifeCycleStausToNew"
    Then User verify the valid Response body key of CreateBenefitRuleAPI "recordSavingStatus" and expected value "true"

  @Regression @Functional
  Scenario: Create BenefitRule for IncentiveOpen formularyType with below Value and check the Response
    Given User runs the Query "contractIdForCreateBenefitRuleForContract"and Get the Contract ContractId
    And User runs Update Query to Update LifeCycleStatus of Contract ID of ContractHeader "updateContractHeaderLifeCycleStatusToInProgress" and ContractDetails "updateContractDetailLifeCycleStausToInProgress" to InProgress
    And User create the Benefitruledata
      | benefitRuleName | formularyType | aboveValue | belowValue |
      | BenefitRule123  | IncentiveOpen |            | 98         |
    When User hits createBenefitRuleAPI "createBenefitRuleForAContract" with Post Request
    Then user runs Deletes the BenefitRule Created by Executing the Query "deleteBenefitRulesCreated"
    Then User User Redo's Change made to Lifecycle Status for both ContractHeader "updateContractHeaderLifeCycleStatusToNew" and ContractDetail "updateContractDetailLifeCycleStausToNew"
    Then User verify the valid Response body key of CreateBenefitRuleAPI "recordSavingStatus" and expected value "true"

  @Regression @Functional
  Scenario: Create BenefitRule for IncentiveOpen formularyType with above Value and check the Response
    Given User runs the Query "contractIdForCreateBenefitRuleForContract"and Get the Contract ContractId
    And User runs Update Query to Update LifeCycleStatus of Contract ID of ContractHeader "updateContractHeaderLifeCycleStatusToInProgress" and ContractDetails "updateContractDetailLifeCycleStausToInProgress" to InProgress
    And User create the Benefitruledata
      | benefitRuleName | formularyType | aboveValue | belowValue |
      | BenefitRule123  | IncentiveOpen | 15         |            |
    When User hits createBenefitRuleAPI "createBenefitRuleForAContract" with Post Request
    Then user runs Deletes the BenefitRule Created by Executing the Query "deleteBenefitRulesCreated"
    Then User User Redo's Change made to Lifecycle Status for both ContractHeader "updateContractHeaderLifeCycleStatusToNew" and ContractDetail "updateContractDetailLifeCycleStausToNew"
    Then User verify the valid Response body key of CreateBenefitRuleAPI "recordSavingStatus" and expected value "true"

  @Regression @Functional
  Scenario: Create BenefitRule for IncentiveOpen formularyType with both above and below Value and check the Response
    Given User runs the Query "contractIdForCreateBenefitRuleForContract"and Get the Contract ContractId
    And User runs Update Query to Update LifeCycleStatus of Contract ID of ContractHeader "updateContractHeaderLifeCycleStatusToInProgress" and ContractDetails "updateContractDetailLifeCycleStausToInProgress" to InProgress
    And User create the Benefitruledata
      | benefitRuleName | formularyType | aboveValue | belowValue |
      | BenefitRule123  | IncentiveOpen | 15         | 25         |
    When User hits createBenefitRuleAPI "createBenefitRuleForAContract" with Post Request
    Then user runs Deletes the BenefitRule Created by Executing the Query "deleteBenefitRulesCreated"
    Then User User Redo's Change made to Lifecycle Status for both ContractHeader "updateContractHeaderLifeCycleStatusToNew" and ContractDetail "updateContractDetailLifeCycleStausToNew"
    Then User verify the valid Response body key of CreateBenefitRuleAPI "recordSavingStatus" and expected value "true"

  @Regression @Functional
  Scenario: Create BenefitRule for IncentiveClosed formularyType with above Value and check the Response
    Given User runs the Query "contractIdForCreateBenefitRuleForContract"and Get the Contract ContractId
    And User runs Update Query to Update LifeCycleStatus of Contract ID of ContractHeader "updateContractHeaderLifeCycleStatusToInProgress" and ContractDetails "updateContractDetailLifeCycleStausToInProgress" to InProgress
    And User create the Benefitruledata
      | benefitRuleName | formularyType   | aboveValue | belowValue |
      | BenefitRule123  | IncentiveClosed | 45         |            |
    When User hits createBenefitRuleAPI "createBenefitRuleForAContract" with Post Request
    Then user runs Deletes the BenefitRule Created by Executing the Query "deleteBenefitRulesCreated"
    Then User User Redo's Change made to Lifecycle Status for both ContractHeader "updateContractHeaderLifeCycleStatusToNew" and ContractDetail "updateContractDetailLifeCycleStausToNew"
    Then User verify the valid Response body key of CreateBenefitRuleAPI "recordSavingStatus" and expected value "true"

  Scenario: Create BenefitRule for IncentiveClosed formularyType with below Value and check the Response
    Given User runs the Query "contractIdForCreateBenefitRuleForContract"and Get the Contract ContractId
    And User runs Update Query to Update LifeCycleStatus of Contract ID of ContractHeader "updateContractHeaderLifeCycleStatusToInProgress" and ContractDetails "updateContractDetailLifeCycleStausToInProgress" to InProgress
    And User create the Benefitruledata
      | benefitRuleName | formularyType   | aboveValue | belowValue |
      | BenefitRule123  | IncentiveClosed |            | 98         |
    When User hits createBenefitRuleAPI "createBenefitRuleForAContract" with Post Request
    Then user runs Deletes the BenefitRule Created by Executing the Query "deleteBenefitRulesCreated"
    Then User User Redo's Change made to Lifecycle Status for both ContractHeader "updateContractHeaderLifeCycleStatusToNew" and ContractDetail "updateContractDetailLifeCycleStausToNew"
    Then User verify the valid Response body key of CreateBenefitRuleAPI "recordSavingStatus" and expected value "true"

  Scenario: Create BenefitRule for IncentiveClosed formularyType with both above and below Value and check the Response
    Given User runs the Query "contractIdForCreateBenefitRuleForContract"and Get the Contract ContractId
    And User runs Update Query to Update LifeCycleStatus of Contract ID of ContractHeader "updateContractHeaderLifeCycleStatusToInProgress" and ContractDetails "updateContractDetailLifeCycleStausToInProgress" to InProgress
    And User create the Benefitruledata
      | benefitRuleName | formularyType   | aboveValue | belowValue |
      | BenefitRule123  | IncentiveClosed | 79         | 98         |
    When User hits createBenefitRuleAPI "createBenefitRuleForAContract" with Post Request
    Then user runs Deletes the BenefitRule Created by Executing the Query "deleteBenefitRulesCreated"
    Then User User Redo's Change made to Lifecycle Status for both ContractHeader "updateContractHeaderLifeCycleStatusToNew" and ContractDetail "updateContractDetailLifeCycleStausToNew"
    Then User verify the valid Response body key of CreateBenefitRuleAPI "recordSavingStatus" and expected value "true"

  @Regression @Functional
  Scenario: Create BenefitRule forall open,Closed,openClosed formularyType with both above and below Value and check the Response
    Given User runs the Query "contractIdForCreateBenefitRuleForContract"and Get the Contract ContractId
    And User runs Update Query to Update LifeCycleStatus of Contract ID of ContractHeader "updateContractHeaderLifeCycleStatusToInProgress" and ContractDetails "updateContractDetailLifeCycleStausToInProgress" to InProgress
    And User create the Benefitruledata
      | benefitRuleName | formularyType | aboveValue | belowValue |
      | BenefitRule1    | Open          |            |            |
    When User hits createBenefitRuleAPI "createBenefitRuleForAContract" with Post Request
    Then User verify the valid Response body key of CreateBenefitRuleAPI "recordSavingStatus" and expected value "true"
    And User create the Benefitruledata
      | benefitRuleName | formularyType | aboveValue | belowValue |
      | BenefitRule12   | Closed        |            |            |
    When User hits createBenefitRuleAPI "createBenefitRuleForAContract" with Post Request
    Then User verify the valid Response body key of CreateBenefitRuleAPI "recordSavingStatus" and expected value "true"
    And User create the Benefitruledata
      | benefitRuleName | formularyType | aboveValue | belowValue |
      | BenefitRule123  | OpenClosed    |            |            |
    When User hits createBenefitRuleAPI "createBenefitRuleForAContract" with Post Request
    Then User verify the valid Response body key of CreateBenefitRuleAPI "recordSavingStatus" and expected value "true"
    Then user runs Deletes the BenefitRule Created by Executing the Query "deleteBenefitRulesCreated"
    Then User User Redo's Change made to Lifecycle Status for both ContractHeader "updateContractHeaderLifeCycleStatusToNew" and ContractDetail "updateContractDetailLifeCycleStausToNew"

  @Regression @Functional
  Scenario: Create BenefitRule forall open,Closed,openClosed,Incentive,Incentiveopen,IncentiveClosed formularyType with both above and below Value and check the Response
    Given User runs the Query "contractIdForCreateBenefitRuleForContract"and Get the Contract ContractId
    And User runs Update Query to Update LifeCycleStatus of Contract ID of ContractHeader "updateContractHeaderLifeCycleStatusToInProgress" and ContractDetails "updateContractDetailLifeCycleStausToInProgress" to InProgress
    And User create the Benefitruledata
      | benefitRuleName | formularyType | aboveValue | belowValue |
      | BenefitRule1    | Open          |            |            |
    When User hits createBenefitRuleAPI "createBenefitRuleForAContract" with Post Request
    Then User verify the valid Response body key of CreateBenefitRuleAPI "recordSavingStatus" and expected value "true"
    And User create the Benefitruledata
      | benefitRuleName | formularyType | aboveValue | belowValue |
      | BenefitRule12   | Closed        |            |            |
    When User hits createBenefitRuleAPI "createBenefitRuleForAContract" with Post Request
    Then User verify the valid Response body key of CreateBenefitRuleAPI "recordSavingStatus" and expected value "true"
    And User create the Benefitruledata
      | benefitRuleName | formularyType | aboveValue | belowValue |
      | BenefitRule123  | OpenClosed    |            |            |
    When User hits createBenefitRuleAPI "createBenefitRuleForAContract" with Post Request
    Then User verify the valid Response body key of CreateBenefitRuleAPI "recordSavingStatus" and expected value "true"
    And User create the Benefitruledata
      | benefitRuleName | formularyType | aboveValue | belowValue |
      | BenefitRule1234 | Incentive     | 12         |            |
    When User hits createBenefitRuleAPI "createBenefitRuleForAContract" with Post Request
    Then User verify the valid Response body key of CreateBenefitRuleAPI "recordSavingStatus" and expected value "true"
    And User create the Benefitruledata
      | benefitRuleName | formularyType | aboveValue | belowValue |
      | BenefitRule1235 | IncentiveOpen | 13         |            |
    When User hits createBenefitRuleAPI "createBenefitRuleForAContract" with Post Request
    Then User verify the valid Response body key of CreateBenefitRuleAPI "recordSavingStatus" and expected value "true"
    And User create the Benefitruledata
      | benefitRuleName | formularyType   | aboveValue | belowValue |
      | BenefitRule1236 | IncentiveClosed | 12         | 14         |
    When User hits createBenefitRuleAPI "createBenefitRuleForAContract" with Post Request
    Then User verify the valid Response body key of CreateBenefitRuleAPI "recordSavingStatus" and expected value "true"
    Then user runs Deletes the BenefitRule Created by Executing the Query "deleteBenefitRulesCreated"
    Then User User Redo's Change made to Lifecycle Status for both ContractHeader "updateContractHeaderLifeCycleStatusToNew" and ContractDetail "updateContractDetailLifeCycleStausToNew"

  @Regression @Functional
  Scenario: Create BenefitRule to NameD Duplication and verify it
    Given User runs the Query "contractIdForCreateBenefitRuleForContract"and Get the Contract ContractId
    And User runs Update Query to Update LifeCycleStatus of Contract ID of ContractHeader "updateContractHeaderLifeCycleStatusToInProgress" and ContractDetails "updateContractDetailLifeCycleStausToInProgress" to InProgress
    And User create the Benefitruledata
      | benefitRuleName | formularyType | aboveValue | belowValue |
      | BenefitRule1    | Open          |            |            |
    When User hits createBenefitRuleAPI "createBenefitRuleForAContract" with Post Request
    Then User verify the valid Response body key of CreateBenefitRuleAPI "recordSavingStatus" and expected value "true"
    And User create the Benefitruledata
      | benefitRuleName | formularyType | aboveValue | belowValue |
      | BenefitRule1    | Closed        |            |            |
    When User hits createBenefitRuleAPI "createBenefitRuleForAContract" with Post Request
    Then User verify the valid Response body key of CreateBenefitRuleAPI "recordSavingStatus" and expected value "false"
    Then user runs Deletes the BenefitRule Created by Executing the Query "deleteBenefitRulesCreated"
    Then User User Redo's Change made to Lifecycle Status for both ContractHeader "updateContractHeaderLifeCycleStatusToNew" and ContractDetail "updateContractDetailLifeCycleStausToNew"
    Then User Verifies the headercode for this response is "503"

  # Negetive Scenarios
  @Regression @Functional
  Scenario: Create BenefitRule for Incentive formularyType with null Values and check the Response
    Given User runs the Query "contractIdForCreateBenefitRuleForContract"and Get the Contract ContractId
    And User runs Update Query to Update LifeCycleStatus of Contract ID of ContractHeader "updateContractHeaderLifeCycleStatusToInProgress" and ContractDetails "updateContractDetailLifeCycleStausToInProgress" to InProgress
    And User create the Benefitruledata
      | benefitRuleName | formularyType | aboveValue | belowValue |
      | BenefitRule123  | Incentive     |            |            |
    When User hits createBenefitRuleAPI "createBenefitRuleForAContract" with Post Request
    Then user runs Deletes the BenefitRule Created by Executing the Query "deleteBenefitRulesCreated"
    Then User User Redo's Change made to Lifecycle Status for both ContractHeader "updateContractHeaderLifeCycleStatusToNew" and ContractDetail "updateContractDetailLifeCycleStausToNew"
    Then User verify the valid Response body key of CreateBenefitRuleAPI "ivalidDataResponseMessage" and expected value "givingBothAboveAndBelowvalueNull"
    Then User Verifies the headercode for this response is "-1"

  @Regression @Functional
  Scenario: Create BenefitRule for Incentive formularyType with null Values and check the Response
    Given User runs the Query "contractIdForCreateBenefitRuleForContract"and Get the Contract ContractId
    And User runs Update Query to Update LifeCycleStatus of Contract ID of ContractHeader "updateContractHeaderLifeCycleStatusToInProgress" and ContractDetails "updateContractDetailLifeCycleStausToInProgress" to InProgress
    And User create the Benefitruledata
      | benefitRuleName | formularyType | aboveValue | belowValue |
      | BenefitRule123  | IncentiveOpen |            |            |
    When User hits createBenefitRuleAPI "createBenefitRuleForAContract" with Post Request
    Then user runs Deletes the BenefitRule Created by Executing the Query "deleteBenefitRulesCreated"
    Then User User Redo's Change made to Lifecycle Status for both ContractHeader "updateContractHeaderLifeCycleStatusToNew" and ContractDetail "updateContractDetailLifeCycleStausToNew"
    Then User verify the valid Response body key of CreateBenefitRuleAPI "ivalidDataResponseMessage" and expected value "givingBothAboveAndBelowvalueNull"
    Then User Verifies the headercode for this response is "-1"

  @Regression @Functional
  Scenario: Create BenefitRule for Incentive formularyType with null Values and check the Response
    Given User runs the Query "contractIdForCreateBenefitRuleForContract"and Get the Contract ContractId
    And User runs Update Query to Update LifeCycleStatus of Contract ID of ContractHeader "updateContractHeaderLifeCycleStatusToInProgress" and ContractDetails "updateContractDetailLifeCycleStausToInProgress" to InProgress
    And User create the Benefitruledata
      | benefitRuleName | formularyType   | aboveValue | belowValue |
      | BenefitRule123  | IncentiveClosed |            |            |
    When User hits createBenefitRuleAPI "createBenefitRuleForAContract" with Post Request
    Then user runs Deletes the BenefitRule Created by Executing the Query "deleteBenefitRulesCreated"
    Then User User Redo's Change made to Lifecycle Status for both ContractHeader "updateContractHeaderLifeCycleStatusToNew" and ContractDetail "updateContractDetailLifeCycleStausToNew"
    Then User verify the valid Response body key of CreateBenefitRuleAPI "ivalidDataResponseMessage" and expected value "givingBothAboveAndBelowvalueNull"
    Then User Verifies the headercode for this response is "-1"

  @Regression @Functional
  Scenario: Create BenefitRule for Incentive formularyType with invalidFormularyType
    Given User runs the Query "contractIdForCreateBenefitRuleForContract"and Get the Contract ContractId
    And User runs Update Query to Update LifeCycleStatus of Contract ID of ContractHeader "updateContractHeaderLifeCycleStatusToInProgress" and ContractDetails "updateContractDetailLifeCycleStausToInProgress" to InProgress
    And User create the Benefitruledata
      | benefitRuleName | formularyType | aboveValue | belowValue |
      | BenefitRule123  | ABcd          | 1          | 3          |
    When User hits createBenefitRuleAPI "createBenefitRuleForAContract" with Post Request
    Then user runs Deletes the BenefitRule Created by Executing the Query "deleteBenefitRulesCreated"
    Then User User Redo's Change made to Lifecycle Status for both ContractHeader "updateContractHeaderLifeCycleStatusToNew" and ContractDetail "updateContractDetailLifeCycleStausToNew"
    Then User verify the valid Response body key of CreateBenefitRuleAPI "ivalidDataResponseMessage" and expected value "invalidFormularyType"
    Then User Verifies the headercode for this response is "-1"

  @Regression @Functional
  Scenario: Create BenefitRule for Incentive formularyType with invalid Less than Minimum Character BenefitRulename
    Given User runs the Query "contractIdForCreateBenefitRuleForContract"and Get the Contract ContractId
    And User runs Update Query to Update LifeCycleStatus of Contract ID of ContractHeader "updateContractHeaderLifeCycleStatusToInProgress" and ContractDetails "updateContractDetailLifeCycleStausToInProgress" to InProgress
    And User create the Benefitruledata
      | benefitRuleName | formularyType   | aboveValue | belowValue |
      | Bene            | IncentiveClosed | 1          | 3          |
    When User hits createBenefitRuleAPI "createBenefitRuleForAContract" with Post Request
    Then user runs Deletes the BenefitRule Created by Executing the Query "deleteBenefitRulesCreated"
    Then User User Redo's Change made to Lifecycle Status for both ContractHeader "updateContractHeaderLifeCycleStatusToNew" and ContractDetail "updateContractDetailLifeCycleStausToNew"
    Then User verify the valid Response body key of CreateBenefitRuleAPI "ivalidDataResponseMessage" and expected value "invalidBenifitRulenameLessThanMinimumCharacters"
    Then User Verifies the headercode for this response is "-1"

  @Regression @Functional
  Scenario: Create BenefitRule for Incentive formularyType with invalid Less than Minimum Character BenefitRulename
    Given User runs the Query "contractIdForCreateBenefitRuleForContract"and Get the Contract ContractId
    And User runs Update Query to Update LifeCycleStatus of Contract ID of ContractHeader "updateContractHeaderLifeCycleStatusToInProgress" and ContractDetails "updateContractDetailLifeCycleStausToInProgress" to InProgress
    And User create the Benefitruledata
      | benefitRuleName                                                   | formularyType   | aboveValue | belowValue |
      | Benewertqyudgsgsgstsgstsgstsyshsyshsushsushsushsushsushsushsusuhs | IncentiveClosed | 1          | 3          |
    When User hits createBenefitRuleAPI "createBenefitRuleForAContract" with Post Request
    Then user runs Deletes the BenefitRule Created by Executing the Query "deleteBenefitRulesCreated"
    Then User User Redo's Change made to Lifecycle Status for both ContractHeader "updateContractHeaderLifeCycleStatusToNew" and ContractDetail "updateContractDetailLifeCycleStausToNew"
    Then User verify the valid Response body key of CreateBenefitRuleAPI "ivalidDataResponseMessage" and expected value "invalidBenifitruleMoreThanMaximumCharacters"
    Then User Verifies the headercode for this response is "-1"

  @Regression @Functional
  Scenario: Create BenefitRule for Contract having LifeCycle Status as New IncentiveClosed formularyType with both above and below Value and check the Response
    Given User runs the Query "contractIdForCreateBenefitRuleForContract"and Get the Contract ContractId
    And User create the Benefitruledata
      | benefitRuleName | formularyType   | aboveValue | belowValue |
      | BenefitRule123  | IncentiveClosed | 79         | 98         |
    When User hits createBenefitRuleAPI "createBenefitRuleForAContract" with Post Request
    Then user runs Deletes the BenefitRule Created by Executing the Query "deleteBenefitRulesCreated"
    Then User verify the valid Response body key of CreateBenefitRuleAPI "recordSavingStatus" and expected value "false"
    Then User Verifies the headercode for this response is "502"

  @Regression @Functional
  Scenario: Create BenefitRule for Incentive formularyType with Values more than 100 or equal to 100and check the Response
    Given User runs the Query "contractIdForCreateBenefitRuleForContract"and Get the Contract ContractId
    And User runs Update Query to Update LifeCycleStatus of Contract ID of ContractHeader "updateContractHeaderLifeCycleStatusToInProgress" and ContractDetails "updateContractDetailLifeCycleStausToInProgress" to InProgress
    And User create the Benefitruledata
      | benefitRuleName | formularyType   | aboveValue | belowValue |
      | BenefitRule123  | IncentiveClosed | 100        |            |
    When User hits createBenefitRuleAPI "createBenefitRuleForAContract" with Post Request
    Then user runs Deletes the BenefitRule Created by Executing the Query "deleteBenefitRulesCreated"
    Then User User Redo's Change made to Lifecycle Status for both ContractHeader "updateContractHeaderLifeCycleStatusToNew" and ContractDetail "updateContractDetailLifeCycleStausToNew"
    Then User verify the valid Response body key of CreateBenefitRuleAPI "ivalidDataResponseMessage" and expected value "invalidAboveOrBelowValue"
    Then User Verifies the headercode for this response is "-1"
