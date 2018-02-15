Feature: Functional Test Case

  Scenario: Non-Valuated Portfolio with Recon Pledge
    Given Navigate to login page
    And Login with username and password
    And Login TwoFA
    And Landed to Dashboard
    And Navigate to Upload Portfolio Page
    And Upload a nonValuated portfolio
#    And Upload a Valuated portfolio
    And Valuate a portfolio
    And Generate Margin Calls
#    And Send Margin Calls
    And Navigate to Recon Page
    And Reconcile a Call
    And Navigate to Pledge Page

#  Examples:
#    |  |

#    Scenario: Valuated Portfolio with Recon Pledge
#    Given Navigate to login page
#    And Login with username and password
#    And Login TwoFA
#    And Landed to Dashboard
#    And Navigate to Upload Portfolio Page
##    And Upload a nonValuated portfolio
#    And Upload a Valuated portfolio
##    And Valuate a portfolio
#    And Generate Margin Calls
##    And Send Margin Calls
#    And Navigate to Recon Page
#    And Reconcile a Call
#    And Navigate to Pledge Page


