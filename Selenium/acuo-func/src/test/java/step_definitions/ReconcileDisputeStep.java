package step_definitions;

import cucumber.api.java.en.And;
import helpers.Variables;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pageobjects.LoginPage;
import pageobjects.ReconPage;

public class ReconcileDisputeStep {

    public WebDriver driver;

    public ReconcileDisputeStep() {
        driver = Hooks.driver;
    }

    @And("^Reconcile a Call")
    public void ReconcileACall() throws Throwable {
        System.out.println("Reconcile");
        PageFactory.initElements(driver, ReconPage.class);
        ReconPage.count_directionOutNumber(driver);
        ReconPage.reconcileACall(driver);
    }

    @And("^Navigate to Pledge Page$")
    public void navigateToPledgePage() throws Throwable {
        PageFactory.initElements(driver, ReconPage.class);
        ReconPage.navigate_PledgePage();
        LoginPage.url_check(Variables.expected_pledge_URL,driver);
        ReconPage.verify_PledgeElementNumber();



    }
}
