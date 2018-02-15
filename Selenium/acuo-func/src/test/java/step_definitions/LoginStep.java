package step_definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import helpers.Variables;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pageobjects.LoginPage;


public class LoginStep {
    public WebDriver driver;

    public LoginStep()
    {
        driver = Hooks.driver;
    }

    //-------------------------------Login--------------------------------------------//
    @Given("^Navigate to login page$")
    public void navigateToLoginPrompt() throws Throwable {
//        Clean Valuation History
        driver.get("https://uat.acuo.com/valuation/acuo/api/import/load/client/Palo?file=deleteCalls");
        Thread.sleep(300);
        driver.get(Variables.main_URL);
        LoginPage.url_check(Variables.expected_main_URL,driver);

    }

    @And("^Login with username and password$")
    public void LoginWithUsernameAndPassword() throws Throwable {
        PageFactory.initElements(driver,LoginPage.class);

        System.out.println("Check Login Button");
        LoginPage.loginButton.isDisplayed();
        LoginPage.input_userName(Variables.username,driver);
        LoginPage.input_passWord(Variables.password,driver);
        System.out.println("Click the Login Button");
        LoginPage.loginButton.click();
        LoginPage.check_loginError();
    }

    @And("^Login TwoFA$")
    public void loginTwoFA() throws Throwable {
        PageFactory.initElements(driver,LoginPage.class);

        System.out.println("Check the 2FA Element\n");
        LoginPage.twoFAText.isDisplayed();
        LoginPage.input_twoFALogin(Variables.twoFACode,driver);
        System.out.println("Click the 2FA Button");
        LoginPage.twoFAButton.click();

    }

    @And("^Landed to Dashboard$")
    public void landedToDashboard() throws Throwable {
        LoginPage.url_check(Variables.expected_dashboard_URL,driver);
    }

    //-------------------------UploadPage----------------------------------------------//

}