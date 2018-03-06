Feature: ValuatedFlow

  Scenario Outline: Valuated Portfolio with Recon Pledge
    Given Clean the history "<history>" before test
    And Navigate to login page
    And Login with username "<username>" and password "<password>"
    And Login TwoFA
    And Landed to Dashboard
    And Navigate to Upload Portfolio Page
    And Upload a Valuated portfolio "<portfolio>"
    And Generate Margin Calls
    And Send Margin Calls
    And Navigate to Recon Page
    And Reconcile a Call
    And Navigate to Pledge Page
#    And Allocate Collateral
#    Then Pledge Collateral
    Examples:
      | username          | password   | portfolio                               | history                                                                          |
      | user@palo.com     | @Password1 | Demo-TradePortfolio_Palo_yesterday.xlsx | https://uat.acuo.com/valuation/acuo/api/import/load/client/Palo?file=deleteCalls |
      | user@acuocpty.com | @Password1 | Demo-TradePortfolio_ACUO_yesterday.xlsx | https://uat.acuo.com/valuation/acuo/api/import/load/client/ACUO?file=deleteCalls |
