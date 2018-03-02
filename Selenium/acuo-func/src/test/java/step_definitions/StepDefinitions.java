package step_definitions;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import helpers.Variables;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pageobjects.LoginPageObject;
import pageobjects.PledgePageObject;
import pageobjects.ReconPageObject;
import pageobjects.UploadPageObject;

import java.util.ArrayList;
import java.util.List;

public class StepDefinitions {
    public WebDriver driver;

    public StepDefinitions() {
        driver = Hooks.driver;
    }


//------------------------------Login and DashBoard------------------------------------------------------------

    @Given("^Clean the history before test$")
    public void cleanTheHistoryBeforeTest() throws Throwable {
        System.out.println("-----------------------------------------------------------------");
        System.out.println("Clean History");
        driver.get("https://uat.acuo.com/valuation/acuo/api/import/load/client/Palo?file=deleteCalls");
        driver.get("https://uat.acuo.com/valuation/acuo/api/import/load/client/Acuo?file=deleteCalls");
        driver.get("https://uat.acuo.com/valuation/acuo/api/import/load/client/Reuters?file=deleteCalls");

    }

    @And("^Navigate to login page$")
    public void navigateToLoginPrompt() throws Throwable {
        System.out.println("-----------------------------------------------------------------");
        System.out.println("Navigate to main page");
        Thread.sleep(300);
        driver.get(Variables.main_URL);
        LoginPageObject.url_check(Variables.expected_main_URL, driver);
    }

    @And("^Login with username and password$")
    public void LoginWithUsernameAndPassword() throws Throwable {
        PageFactory.initElements(driver, LoginPageObject.class);
        System.out.println("-----------------------------------------------------------------");
        System.out.println("Find Login Button");
        LoginPageObject.loginButton.isDisplayed();
        LoginPageObject.input_userName(Variables.username, driver);
        LoginPageObject.input_passWord(Variables.password, driver);
        System.out.println("Click the Login Button");
        LoginPageObject.loginButton.click();
        LoginPageObject.check_loginError();
    }

    @And("^Login TwoFA$")
    public void loginTwoFA() throws Throwable {
        PageFactory.initElements(driver, LoginPageObject.class);
        System.out.println("-----------------------------------------------------------------");
        System.out.println("2FA Login");
        System.out.println("Find the 2FA Element");
        LoginPageObject.twoFAText.isDisplayed();
        LoginPageObject.input_twoFALogin(Variables.twoFACode, driver);
        System.out.println("Click the 2FA Button");
        LoginPageObject.twoFAButton.click();

    }

    @And("^Landed to Dashboard$")
    public void landedToDashboard() throws Throwable {
        System.out.println("-----------------------------------------------------------------");
        System.out.println("Navigate to Dashboard");
        LoginPageObject.url_check(Variables.expected_dashboard_URL, driver);
    }

//-----------------------------Upload Page----------------------------------------------------------------


    @Given("^Navigate to Upload Portfolio Page$")
    public void navigate_to_Upload_Portfolio_Page() throws Throwable {
        System.out.println("-----------------------------------------------------------------");
        System.out.println("Navigate to Upload Page");
        PageFactory.initElements(driver, UploadPageObject.class);

        System.out.println("Navigate to Upload Pages");
        UploadPageObject.uploadPortfolio.click();
        LoginPageObject.url_check(Variables.expected_Upload_URL, driver);
    }

    @And("^Upload a nonValuated portfolio$")
    public void uploadANonValuatedPortfolio() throws Throwable {
        System.out.println("-----------------------------------------------------------------");
        System.out.println("Upload a Non-Valuated Portfolio");
        PageFactory.initElements(driver, UploadPageObject.class);
        String file = Variables.nonValuated_Portfolio;
        UploadPageObject.upload_a_File(file, driver);

    }

    @And("^Upload a Valuated portfolio$")
    public void uploadAValuatedPortfolio() throws Throwable {
        System.out.println("-----------------------------------------------------------------");
        System.out.println("Upload a Valuated Portfolio");
        PageFactory.initElements(driver, UploadPageObject.class);
        String file = Variables.valuated_Portfolio;
        UploadPageObject.upload_a_File(file, driver);
        System.out.println("check Call Types and Exposure");
//        Verify the behaviour of valuated portfolio
        List callTypes = new ArrayList<String>();
        List exposures = new ArrayList<String>();
        List callDates = new ArrayList<String>();
        List<WebElement> listOfExposures = driver.findElements(By.xpath("//div[@class='MarginCall__cell___1qVDS MarginCall__largeCell___3VAoO exposure']"));
        List<WebElement> listOfCallTypes = driver.findElements(By.xpath("//div[@class='MarginCall__cell___1qVDS MarginCall__callTypeCell___2XUpD callType']"));
        List<WebElement> listOfCallDates = driver.findElements(By.xpath("//div[@class='MarginCall__cell___1qVDS MarginCall__dateCell___1a8Bj MarginCall__boldCellText___394uJ fonts__baseFontBold___1NYJU callDate']"));
        for (WebElement a : listOfCallTypes) {
            callTypes.add(a.getText());
        }
        for (WebElement b : listOfExposures) {
            exposures.add(b.getText());
        }
        for (WebElement c : listOfCallDates) {
            callDates.add(c.getText());
        }
        for (int i = 0; i < callTypes.size(); i++) {
            if (callTypes.get(i).equals("Variation")) {
                if (!exposures.get(i).equals("0")) {
                    System.out.println("\nThis is element " + i);
                    System.out.println("Outcome is expected");
                    System.out.println("Call Types = " + callTypes.get(i));
                    System.out.println("Exposure = " + exposures.get(i));
                } else {
                    System.out.println("\nThis is element " + i);
                    System.out.println("Outcome is incorrect");
                    System.out.println("Call Types = " + callTypes.get(i));
                    System.out.println("Exposure = " + exposures.get(i));
                    Assert.fail();
                }
            } else if (callTypes.get(i).equals("Initial")) {
                if (exposures.get(i).equals("0")) {
                    System.out.println("\nThis is element " + i);
                    System.out.println("Outcome is expected");
                    System.out.println("Call Types = " + callTypes.get(i));
                    System.out.println("Exposure = " + exposures.get(i));
                } else {
                    System.out.println("\nThis is element " + i);
                    System.out.println("Outcome is incorrect");
                    System.out.println("Call Types = " + callTypes.get(i));
                    System.out.println("Exposure = " + exposures.get(i));
                    Assert.fail();
                }
            }
        }
        UploadPageObject.checkboxAll.isDisplayed();
        UploadPageObject.checkboxAll.click();

    }

    @And("^Valuate a portfolio$")
    public void valuateAPortfolio() throws Throwable {
        System.out.println("-----------------------------------------------------------------");
        System.out.println("Valuate a portfolio");
        PageFactory.initElements(driver, UploadPageObject.class);
        UploadPageObject.checkboxAll.isDisplayed();
        UploadPageObject.checkboxAll.click();
        UploadPageObject.valuationButton.isDisplayed();
        UploadPageObject.valuationButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 480);
        System.out.println("Valuation in Progress.....");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='MarginCall__content___2Epb0 ']")));
        System.out.println("Valuation is completed");

        System.out.println("Check Call Types and Exposure");

//        Verify the behaviour of valuation
        List callTypes = new ArrayList<String>();
        List exposures = new ArrayList<String>();
        List callDates = new ArrayList<String>();
        List<WebElement> listOfExposures = driver.findElements(By.xpath("//div[@class='MarginCall__cell___1qVDS MarginCall__largeCell___3VAoO exposure']"));
        List<WebElement> listOfCallTypes = driver.findElements(By.xpath("//div[@class='MarginCall__cell___1qVDS MarginCall__callTypeCell___2XUpD callType']"));
        List<WebElement> listOfCallDates = driver.findElements(By.xpath("//div[@class='MarginCall__cell___1qVDS MarginCall__dateCell___1a8Bj MarginCall__boldCellText___394uJ fonts__baseFontBold___1NYJU callDate']"));
        for (WebElement a : listOfCallTypes) {
            callTypes.add(a.getText());
        }
        for (WebElement b : listOfExposures) {
            exposures.add(b.getText());
        }
        for (WebElement c : listOfCallDates) {
            callDates.add(c.getText());
        }
        for (int i = 0; i < callTypes.size(); i++) {
            if (callTypes.get(i).equals("Variation")) {
                if (!exposures.get(i).equals("0")) {
                    System.out.println("\nThis is element " + i);
                    System.out.println("Outcome is expected");
                    System.out.println("Call Types = " + callTypes.get(i));
                    System.out.println("Exposure = " + exposures.get(i));
                } else {
                    System.out.println("\nThis is element " + i);
                    System.out.println("Outcome is incorrect");
                    System.out.println("Call Types = " + callTypes.get(i));
                    System.out.println("Exposure = " + exposures.get(i));
                    Assert.fail();
                }
            } else if (callTypes.get(i).equals("Initial")) {
                if (exposures.get(i).equals("0")) {
                    System.out.println("\nThis is element " + i);
                    System.out.println("Outcome is incorrect");
                    System.out.println("Call Types = " + callTypes.get(i));
                    System.out.println("Exposure = " + exposures.get(i));
                    Assert.fail();
                } else {
                    System.out.println("\nThis is element " + i);
                    System.out.println("Outcome is expected");
                    System.out.println("Call Types = " + callTypes.get(i));
                    System.out.println("Exposure = " + exposures.get(i));
                }
            }
        }

    }

    @And("^Generate Margin Calls$")
    public void generateMarginCalls() throws Throwable {
        PageFactory.initElements(driver, UploadPageObject.class);
        UploadPageObject.generateMarginButton.isDisplayed();
        UploadPageObject.generateMarginButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 360);
        System.out.println("Generating Margin Calls");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='MarginCall__content___2Epb0 ']")));
        UploadPageObject.verify_numberOfMarginCall(driver);
    }


    @And("^Send Margin Calls$")
    public void sendMarginCalls() throws Throwable {
        System.out.println("Send Margin Call");
        PageFactory.initElements(driver, UploadPageObject.class);
        UploadPageObject.sendMarginButton.isDisplayed();
        UploadPageObject.sendMarginButton.click();

    }

//---------------------------Recon Dispute Page-----------------------------------------------------------------------

    @And("^Navigate to Recon Page$")
    public void navigateToReconPage() throws Throwable {
        System.out.println("Navigate to Recon Page");
        UploadPageObject.navigate_ReconPage();
        LoginPageObject.url_check(Variables.expected_Recon_URL, driver);
        Thread.sleep(500);
        UploadPageObject.verify_ReconActionPair();
    }

    @And("^Reconcile a Call")
    public void ReconcileACall() throws Throwable {
        System.out.println("Reconcile");
        PageFactory.initElements(driver, ReconPageObject.class);
//        ReconPageObject.count_directionOutNumber(driver);
//        ReconPageObject.reconcileACall(driver);
        ReconPageObject.reconcileCall(driver);
    }

    @And("^Dispute a Call$")
    public void disputeACall() throws Throwable {
        PageFactory.initElements(driver, ReconPageObject.class);
        ReconPageObject.disputeACall(driver);

    }

//------------------------------------------Pledge Page---------------------------------------------------

    @And("^Navigate to Pledge Page$")
    public void navigateToPledgePage() throws Throwable {
        PageFactory.initElements(driver, ReconPageObject.class);
        System.out.println("Navigate to Pledge Page");
        ReconPageObject.navigate_PledgePage();
        LoginPageObject.url_check(Variables.expected_pledge_URL, driver);
//        ReconPageObject.verify_PledgeElementNumber();
    }

    @And("^Setup the Optimization widget eight to two$")
    public void setupTheOptimizationWidgetEighttoTwo() throws Throwable {
        PageFactory.initElements(driver, PledgePageObject.class);
        PledgePageObject.slider_Liquidity.isDisplayed();
        PledgePageObject.slider_Cost.isDisplayed();
        Thread.sleep(500);
        Actions move = new Actions(driver);
        Action slide_liquidity = move.dragAndDropBy(PledgePageObject.slider_Liquidity, 140, 0).build();
        Action slide_cost = move.dragAndDropBy(PledgePageObject.slider_Cost,-135,0).build();
        slide_liquidity.perform();
        slide_cost.perform();

    }

    @And("^Setup the Optimization widget two to eight$")
    public void setupTheOptimizationWidgetTwoToEight() throws Throwable {
        PageFactory.initElements(driver, PledgePageObject.class);
        PledgePageObject.slider_Liquidity.isDisplayed();
        PledgePageObject.slider_Cost.isDisplayed();
        Thread.sleep(500);
        Actions move = new Actions(driver);
        Action slide_liquidity = move.dragAndDropBy(PledgePageObject.slider_Liquidity, -135, 0).build();
        Action slide_cost = move.dragAndDropBy(PledgePageObject.slider_Cost,140,0).build();
        slide_liquidity.perform();
        slide_cost.perform();

    }

    @And("^Setup the Optimization widget five to five$")
    public void setupTheOptimizationWidgetFiveToFive() throws Throwable {
        PageFactory.initElements(driver, PledgePageObject.class);
//        PledgePageObject.slider_Liquidity.isDisplayed();
//        PledgePageObject.slider_Cost.isDisplayed();
        Thread.sleep(500);
        Actions move = new Actions(driver);
        Action slide_liquidity = move.dragAndDropBy(PledgePageObject.slider_Liquidity, 5, 0).build();
        Action slide_cost = move.dragAndDropBy(PledgePageObject.slider_Cost,140,0).build();
        slide_liquidity.perform();
        slide_cost.perform();
    }

    @And("^Allocate Collateral$")
    public void allocateCollateral() throws Throwable {
        PageFactory.initElements(driver, PledgePageObject.class);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        System.out.println("Allocate Collateral to all the Calls");
        Thread.sleep(500);
        PledgePageObject.tick_All.click();
        wait.until(ExpectedConditions.elementToBeClickable(PledgePageObject.button_Allocate));
        PledgePageObject.button_Allocate.click();
        Thread.sleep(500);
        PledgePageObject.checkAllocatedAmount(driver);
    }


    @And("^Setup Constraint Movement$")
    public void setupConstraintMovement() throws Throwable {
        PageFactory.initElements(driver, PledgePageObject.class);
        PledgePageObject.constraint_tab.isDisplayed();
        PledgePageObject.constraint_tab.click();
        PledgePageObject.constraint_Movement.isDisplayed();
        PledgePageObject.constraint_Movement.sendKeys("5");
    }

    @And("^Pledge Collateral$")
    public void pledgeCollateral() throws Throwable {
        PageFactory.initElements(driver, PledgePageObject.class);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        System.out.println("Pledge all");
        wait.until(ExpectedConditions.elementToBeClickable(PledgePageObject.button_Pledge));
        PledgePageObject.button_Pledge.click();
    }
}
