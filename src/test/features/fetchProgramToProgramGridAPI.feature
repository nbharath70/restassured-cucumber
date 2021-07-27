#This feature file defines the automation scenarios to be developed for ProgramsToDisplay on Grid
Feature:Retrieve and validate ProgramsToDisplay on Grid
  @Smoke
  Scenario: Hit fetchProgramsToGrid Api and validate Status Code
    Given User Runs the Query "getContractIDOfProgram" and Get the Contract Id of Program
    When User hits the "fetchProgramsToGrid" Endpoint with get request
    Then User Verifies the API response Status code is "200" for FetchProgramsToGridApi

  @Smoke
  Scenario: Hit fetchProgramsToGrid Api and validate Response Format
    Given User Runs the Query "getContractIDOfProgram" and Get the Contract Id of Program
    When User hits the "fetchProgramsToGrid" Endpoint with get request
    Then Verify response is in JSON format

  @Regression @Functional
  Scenario Outline: Hit the fetchProgramsToGrid Api and validate Details of the Program for the Grid
    Given User Runs the Query "getContractIDOfProgram" and Get the Contract Id of Program
    When User hits the "fetchProgramsToGrid" Endpoint with get request
    Then User Runs the Query "getProgramDetailsToProgamGrid" and matches the Colums "<columnName>" and JSON from response
    Examples:
      | columnName                     |
      | manufactuererProgramID         |
      | startDateForFetchProgramToGrid |
      | endDateForFetchProgramToGrid   |
      | contractID                     |

  @Functional
  Scenario Outline: Hit the fetchProgramsToGrid Api and validate Details in ProgramDetailJSON of the Program for the Grid
    Given User Runs the Query "getContractIDOfProgram" and Get the Contract Id of Program
    And User executes the query "getProgramDetailJSONValues" and get the ProgramDetailJSON from DB
    When User hits the "fetchProgramsToGrid" Endpoint with get request
    Then User validate the details with JSON by "<JSONPaths>" JSonpaths for Progarms to programgrid
    Examples:
      | JSONPaths        |
      | programName      |
      | pricingBasis     |
      | pricingDate      |
      | programLevelPSF  |
      | legacyContractID |

  @Regression @Functional
  Scenario Outline: Hit the fetchProgramsToGrid Api and validate ManufacturerDrugListID in ProgramDetailJSON of the Program for the Grid
    Given User Runs the Query "getContractIDOfProgram" and Get the Contract Id of Program
    And User executes the query "getProgramDetailJSONValues" and get the ProgramDetailJSON from DB
    When User hits the "fetchProgramsToGrid" Endpoint with get request
    Then User validate the DrugGroupID Details with "<DBJSONPaths>" and "<APIJSONPaths>"
    Examples:
      | DBJSONPaths               | APIJSONPaths               |
      | rebatebleDrugGroupIDDB    | rebatebleDrugGroupIDAPI    |
      | compitatorDrugGroupIDDB   | compitatorDrugGroupIDAPI   |
      | marketBasketDrugGroupIDDB | marketBasketDrugGroupIDAPI |

  @Regression @Functional
  Scenario Outline: Hit the fetchProgramsToGri API and validate DrugListName with DB
    Given User Runs the Query "getContractIDOfProgram" and Get the Contract Id of Program
    And User executes the query "getProgramDetailJSONValues" and get the ProgramDetailJSON from DB
    And User takes the DrugGroupID of drugs by "<JSONPaths>" from Program DetailJSON
    And User executes the query"getDrugGroupJSONfromDbForProgramIntialSplit" ,"getDrugGroupJSONfromDbForProgramFinalSplit"and get drugDetailJSON from DB
    When User hits the "fetchProgramsToGrid" Endpoint with get request
    Then User validate the ListName with "<DBJSONPaths>" and "<APIJSONPaths>"
    Examples:
      | JSONPaths                 | DBJSONPaths                             | APIJSONPaths                             |
      | rebatebleDrugGroupIDDB    | rebatebleDrugGroupListNameDBJSONPath    | rebatebleDrugGroupListNameAPIJSONPath    |
      | compitatorDrugGroupIDDB   | compitatorDrugGroupListNameDBJSONPath   | compitatorDrugGroupListNameAPIJSONPath   |
      | marketBasketDrugGroupIDDB | marketBasketDrugGroupListNameDBJSONPath | marketBasketDrugGroupListNameAPIJSONPath |

  @Regression @Functional
  Scenario Outline: Hit the fetchProgramsToGri API and validate DrugListType with DB
    Given User Runs the Query "getContractIDOfProgram" and Get the Contract Id of Program
    And User executes the query "getProgramDetailJSONValues" and get the ProgramDetailJSON from DB
    And User takes the DrugGroupID of drugs by "<JSONPaths>" from Program DetailJSON
    And User executes the query"getDrugGroupJSONfromDbForProgramIntialSplit" ,"getDrugGroupJSONfromDbForProgramFinalSplit"and get drugDetailJSON from DB
    When User hits the "fetchProgramsToGrid" Endpoint with get request
    Then User validate the ListType with "<DBJSONPaths>" and "<APIJSONPaths>"
    Examples:
      | JSONPaths                 | DBJSONPaths                             | APIJSONPaths                             |
      | rebatebleDrugGroupIDDB    | rebatebleDrugGroupListTypeDBJSONPath    | rebatebleDrugGroupListTypeAPIJSONPath    |
      | compitatorDrugGroupIDDB   | compitatorDrugGroupListTypeDBJSONPath   | compitatorDrugGroupListTypeAPIJSONPath   |
      | marketBasketDrugGroupIDDB | marketBasketDrugGroupListTypeDBJSONPath | marketBasketDrugGroupListTypeAPIJSONPath |

  @Regression @Functional
  Scenario: Validate error code for invalid programID
    When User hits the "fetchProgramsToGrid" Endpoint with get request for given programID "Test"
    Then User Verifies the API response Status code is "200" for FetchProgramsToGridApi

  @Regression @Functional
  Scenario: Verify Contract Id that has Completed and InProgress Programs
    Given User Runs the Query "getContractIDOfProgramHavingCompletedAndInProgressProgramms" and Get the Contract Id of Program
    When User hits the "fetchProgramsToGrid" Endpoint with get request
    Then User Verifies the API response Status code is "200" for FetchProgramsToGridApi
    And User executes the query "getProgramDetailJSONValues" and get the ProgramDetailJSON from DB
    Then User validate the details with JSON by "programName" JSonpaths for Progarms to programgrid

  @Regression @Functional
  Scenario: Verify Contract Id with IsCurrentFlag has Zero
    Given User Runs the Query "gteContractIDWithIsCurrentFlagZero" and Get the Contract Id of Program
    When User hits the "fetchProgramsToGrid" Endpoint with get request
    Then User Verifies the API response Status code is "200" for FetchProgramsToGridApi
    And User executes the query "getProgramDetailJSONValues" and get the ProgramDetailJSON from DB
    Then User validate the details with JSON by "programName" JSonpaths for Progarms to programgrid



