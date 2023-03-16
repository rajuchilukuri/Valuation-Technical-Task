Feature: Vehicle valuation test

  @validation
  Scenario: Validate Vehicle Registration Number
    Given Read data from input text file
    When Use input data in the application and compare vechicle details with output data file