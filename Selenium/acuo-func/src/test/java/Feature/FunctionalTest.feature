Feature: FunctionalTest
  This feature deals with Acuo functional test.

  Scenario:Functional Test with Non-Valuated Portfolio
    Given Navigate to login prompt
    And I enter the username and password
    And I click login button
    And I should see the dashboard
    And Navigate to upload page
    And I want to upload a portfolio
    And I want to valued a portfolio
    And I want to generate a MarginCalls NonValuated Portfolio
    Then I want to send MarginCalls

#  Scenario:Functional Test with Valuated Portfolio
#    Given Navigate to login prompt
#    And I enter the username and password
#    And I click login button
#    And I should see the dashboard
#    And Navigate to upload page
#    And I want to upload a portfolio
#    And I want to valued a portfolio
#    And I want to generate a MarginCalls Valuated Portfolio
#    Then I want to send MarginCalls