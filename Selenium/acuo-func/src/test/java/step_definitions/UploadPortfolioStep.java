package step_definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import helpers.Variables;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pageobjects.LoginPage;
import pageobjects.UploadPage;

import java.util.ArrayList;
import java.util.List;

public class UploadPortfolioStep {
    public WebDriver driver;

    public UploadPortfolioStep()
    {
        driver = Hooks.driver;
    }

    @Given("^Navigate to Upload Portfolio Page$")
    public void navigate_to_Upload_Portfolio_Page() throws Throwable {
        PageFactory.initElements(driver,UploadPage.class);

        System.out.println("Navigate to Upload Pages");
        UploadPage.uploadPortfolio.click();
        LoginPage.url_check(Variables.expected_Upload_URL,driver);
    }

    @And("^Upload a nonValuated portfolio$")
    public void uploadANonValuatedPortfolio() throws Throwable {
        PageFactory.initElements(driver,UploadPage.class);
        String file = Variables.nonValuated_Portfolio;
        UploadPage.upload_a_File(file,driver);

    }

    @And("^Upload a Valuated portfolio$")
    public void uploadAValuatedPortfolio() throws Throwable {
        PageFactory.initElements(driver,UploadPage.class);
        String file = Variables.valuated_Portfolio;
        UploadPage.upload_a_File(file,driver);

//        Verify the behaviour of valuated portfolio
        List callTypes = new ArrayList<String>();
        List exposures = new ArrayList<String>();
        List callDates = new ArrayList<String>();
        List<WebElement> listOfExposures = driver.findElements(By.xpath("//div[@class='MarginCall__cell___1qVDS MarginCall__largeCell___3VAoO exposure']"));
        List<WebElement> listOfCallTypes = driver.findElements(By.xpath("//div[@class='MarginCall__cell___1qVDS MarginCall__callTypeCell___2XUpD callType']"));
        List<WebElement> listOfCallDates = driver.findElements(By.xpath("//div[@class='MarginCall__cell___1qVDS MarginCall__dateCell___1a8Bj MarginCall__boldCellText___394uJ fonts__baseFontBold___1NYJU callDate']"));
        for(WebElement a:listOfCallTypes){
            callTypes.add(a.getText());
        }
        for(WebElement b:listOfExposures){
            exposures.add(b.getText());
        }
        for(WebElement c:listOfCallDates){
            callDates.add(c.getText());
        }
        for(int i=0; i < callTypes.size(); i++){
            if(callTypes.get(i).equals("Variation")){
                if(!exposures.get(i).equals("0")){
                    System.out.println("\nThis is element " + i);
                    System.out.println("Outcome is expected");
                    System.out.println("Call Types = " + callTypes.get(i));
                    System.out.println("Exposure = " + exposures.get(i));
                }else{
                    System.out.println("\nThis is element " + i);
                    System.out.println("Outcome is incorrect");
                    System.out.println("Call Types = " + callTypes.get(i));
                    System.out.println("Exposure = " + exposures.get(i));
                    Assert.fail();
                }
            }else if(callTypes.get(i).equals("Initial")){
                if(exposures.get(i).equals("0")){
                    System.out.println("\nThis is element " + i);
                    System.out.println("Outcome is expected");
                    System.out.println("Call Types = " + callTypes.get(i));
                    System.out.println("Exposure = " + exposures.get(i));
                }else{
                    System.out.println("\nThis is element " + i);
                    System.out.println("Outcome is incorrect");
                    System.out.println("Call Types = " + callTypes.get(i));
                    System.out.println("Exposure = " + exposures.get(i));
                    Assert.fail();
                }
            }
        }
        UploadPage.checkboxAll.isDisplayed();
        UploadPage.checkboxAll.click();

    }

    @And("^Valuate a portfolio$")
    public void valuateAPortfolio() throws Throwable {
        PageFactory.initElements(driver,UploadPage.class);
//        UploadPage.value_a_portfolio(driver);
        UploadPage.checkboxAll.isDisplayed();
        UploadPage.checkboxAll.click();
        UploadPage.valuationButton.isDisplayed();
        UploadPage.valuationButton.click();
        WebDriverWait wait = new WebDriverWait(driver,480);
        System.out.println("Valuation in Progress");
        WebElement waitElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='MarginCall__content___2Epb0 ']")));
        System.out.println("Valuation is completed");

//        Verify the behaviour of valuation
        List callTypes = new ArrayList<String>();
        List exposures = new ArrayList<String>();
        List callDates = new ArrayList<String>();
        List<WebElement> listOfExposures = driver.findElements(By.xpath("//div[@class='MarginCall__cell___1qVDS MarginCall__largeCell___3VAoO exposure']"));
        List<WebElement> listOfCallTypes = driver.findElements(By.xpath("//div[@class='MarginCall__cell___1qVDS MarginCall__callTypeCell___2XUpD callType']"));
        List<WebElement> listOfCallDates = driver.findElements(By.xpath("//div[@class='MarginCall__cell___1qVDS MarginCall__dateCell___1a8Bj MarginCall__boldCellText___394uJ fonts__baseFontBold___1NYJU callDate']"));
        for(WebElement a:listOfCallTypes){
            callTypes.add(a.getText());
        }
        for(WebElement b:listOfExposures){
            exposures.add(b.getText());
        }
        for(WebElement c:listOfCallDates){
            callDates.add(c.getText());
        }
        for(int i=0; i < callTypes.size(); i++){
            if(callTypes.get(i).equals("Variation")){
                if(!exposures.get(i).equals("0")){
                    System.out.println("\nThis is element " + i);
                    System.out.println("Outcome is expected");
                    System.out.println("Call Types = " + callTypes.get(i));
                    System.out.println("Exposure = " + exposures.get(i));
                }else{
                    System.out.println("\nThis is element " + i);
                    System.out.println("Outcome is incorrect");
                    System.out.println("Call Types = " + callTypes.get(i));
                    System.out.println("Exposure = " + exposures.get(i));
                    Assert.fail();
                }
            }else if(callTypes.get(i).equals("Initial")){
                if(exposures.get(i).equals("0")){
                    System.out.println("\nThis is element " + i);
                    System.out.println("Outcome is incorrect");
                    System.out.println("Call Types = " + callTypes.get(i));
                    System.out.println("Exposure = " + exposures.get(i));
                    Assert.fail();
                }else{
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
        PageFactory.initElements(driver,UploadPage.class);
        UploadPage.generateMarginButton.isDisplayed();
        UploadPage.generateMarginButton.click();
        WebDriverWait wait = new WebDriverWait(driver,360);
        System.out.println("Generating Margin Calls");
        WebElement waitElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='MarginCall__content___2Epb0 ']")));
        UploadPage.verify_numberOfMarginCall(driver);
    }


    @And("^Send Margin Calls$")
    public void sendMarginCalls() throws Throwable {
        PageFactory.initElements(driver,UploadPage.class);
        UploadPage.sendMarginButton.isDisplayed();
        UploadPage.sendMarginButton.click();

    }

    @And("^Navigate to Recon Page$")
    public void navigateToReconPage() throws Throwable {
        UploadPage.navigate_ReconPage();
        LoginPage.url_check(Variables.expected_Recon_URL,driver);
        Thread.sleep(500);
        UploadPage.verify_ReconActionPair();
    }
}
