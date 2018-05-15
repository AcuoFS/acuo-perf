Feature: ValuatedFlow

  @Valuated
  Scenario Outline: Valuated Portfolio with Recon Pledge
    Given Clean the history "<history>" before test
    And Update Url Variable based on Environment
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
    And Setup the Optimization widget eight to two
    And Setup the Optimization widget two to eight
    And Setup the Optimization widget five to five
    And Allocate Collateral
    And Check FX Calculation
    Then Pledge Collateral

    Examples:
      | username          | password    | portfolio                               | history                                                                         |
      | user@acuocpty.com | @Password1  | Demo-TradePortfolio_ACUO_yesterday.xlsx | https://qa.acuo.com/valuation/acuo/api/import/load/client/ACUO?file=deleteCalls |
#      | user@palo.com     | @Password1 | Demo-TradePortfolio_Palo_yesterday.xlsx | https://qa.acuo.com/valuation/acuo/api/import/load/client/Palo?file=deleteCalls |

