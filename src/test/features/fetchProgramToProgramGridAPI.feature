#This feature file defines the automation scenarios to be developed for ProgramsToDisplay on Grid
Feature:Retrieve and validate ProgramsToDisplay on Grid
  Scenario: Hit fetchProgramsToGrid Api and validate Status Code
    Given User Runs the Query "getContractIDOfProgram" and Get the Contract Id of Program
    When User hits the "fetchProgramsToGrid" Endpoint with get request
    Then User Verifies the API response Status code is "200" for FetchProgramsToGridApi

  Scenario: Hit fetchProgramsToGrid Api and validate Response Format
    Given User Runs the Query "getContractIDOfProgram" and Get the Contract Id of Program
    When User hits the "fetchProgramsToGrid" Endpoint with get request
    Then Verify response is in JSON format

    Scenario Outline: Hit the fetchProgramsToGrid Api and validate Details of the Program for the Grid
      Given User Runs the Query "getContractIDOfProgram" and Get the Contract Id of Program
      When User hits the "fetchProgramsToGrid" Endpoint with get request
      Then User Runs the Query "getProgramDetailsToProgamGrid" and matches the Colums "<columnName>" and JSON from response
      Examples:
        |columnName|
        |manufactuererProgramID|
        |startDate      |
        |endDate        |
        |contractID     |

    Scenario Outline: Hit the fetchProgramsToGrid Api and validate Details in ProgramDetailJSON of the Program for the Grid
      Given User Runs the Query "getContractIDOfProgram" and Get the Contract Id of Program
      And User executes the query "getProgramDetailJSONValues" and get the ProgramDetailJSON from DB
      When User hits the "fetchProgramsToGrid" Endpoint with get request
      Then User validate the details with JSON by "<JSONPaths>" JSonpaths for Progarms to programgrid
      Examples:
      |JSONPaths|
      |programName|
      |pricingBasis|
      |pricingDate |
      |programLevelPSF|
      |legacyContractID|

  Scenario Outline: Hit the fetchProgramsToGrid Api and validate ManufacturerDrugListID in ProgramDetailJSON of the Program for the Grid
    Given User Runs the Query "getContractIDOfProgram" and Get the Contract Id of Program
    And User executes the query "getProgramDetailJSONValues" and get the ProgramDetailJSON from DB
    When User hits the "fetchProgramsToGrid" Endpoint with get request
    Then User validate the DrugGroupID Details with "<DBJSONPaths>" and "<APIJSONPaths>"
    Examples:
    |DBJSONPaths|APIJSONPaths|
    |rebatebleDrugGroupIDDB|rebatebleDrugGroupIDAPI|
    |compitatorDrugGroupIDDB|compitatorDrugGroupIDAPI|
    |marketBasketDrugGroupIDDB|marketBasketDrugGroupIDAPI|

  Scenario Outline: Hit the fetchProgramsToGri API and validate DrugListName with DB
    Given User Runs the Query "getContractIDOfProgram" and Get the Contract Id of Program
    And User executes the query "getProgramDetailJSONValues" and get the ProgramDetailJSON from DB
    And User takes the DrugGroupID of drugs by "<JSONPaths>" from Program DetailJSON
    And User executes the query"getDrugGroupJSONfromDbForProgramIntialSplit" ,"getDrugGroupJSONfromDbForProgramFinalSplit"and get drugDetailJSON from DB
    When User hits the "fetchProgramsToGrid" Endpoint with get request
    Then User validate the ListName with "<DBJSONPaths>" and "<APIJSONPaths>"
    Examples:
    |JSONPaths|DBJSONPaths|APIJSONPaths|
    |rebatebleDrugGroupIDDB|rebatebleDrugGroupListNameDBJSONPath|rebatebleDrugGroupListNameAPIJSONPath|
    |compitatorDrugGroupIDDB|compitatorDrugGroupListNameDBJSONPath|compitatorDrugGroupListNameAPIJSONPath|
    |marketBasketDrugGroupIDDB|marketBasketDrugGroupListNameDBJSONPath|marketBasketDrugGroupListNameAPIJSONPath|

  Scenario Outline: Hit the fetchProgramsToGri API and validate DrugListType with DB
    Given User Runs the Query "getContractIDOfProgram" and Get the Contract Id of Program
    And User executes the query "getProgramDetailJSONValues" and get the ProgramDetailJSON from DB
    And User takes the DrugGroupID of drugs by "<JSONPaths>" from Program DetailJSON
    And User executes the query"getDrugGroupJSONfromDbForProgramIntialSplit" ,"getDrugGroupJSONfromDbForProgramFinalSplit"and get drugDetailJSON from DB
    When User hits the "fetchProgramsToGrid" Endpoint with get request
    Then User validate the ListType with "<DBJSONPaths>" and "<APIJSONPaths>"
    Examples:
      |JSONPaths|DBJSONPaths|APIJSONPaths|
      |rebatebleDrugGroupIDDB|rebatebleDrugGroupListTypeDBJSONPath|rebatebleDrugGroupListTypeAPIJSONPath|
      |compitatorDrugGroupIDDB|compitatorDrugGroupListTypeDBJSONPath|compitatorDrugGroupListTypeAPIJSONPath|
      |marketBasketDrugGroupIDDB|marketBasketDrugGroupListTypeDBJSONPath|marketBasketDrugGroupListTypeAPIJSONPath|