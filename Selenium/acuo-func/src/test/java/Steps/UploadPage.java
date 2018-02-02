package Steps;

import Base.BaseUtil;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class UploadPage extends BaseUtil {
    private BaseUtil base;

    public UploadPage(BaseUtil base) {
        this.base = base;
    }

    @Given("^Navigate to upload page$")
    public void navigateToUploadPage() throws Throwable {
        WebElement element;
        element = base.Driver.findElement(By.xpath("//div[@class='UploadPortfolio__text___3USYJ']"));
        element.click();
        //Check if browser navigate to correct page/URL.
        //If the URL is incorrect, fail the test.
        String expectedUrl = "https://uat.acuo.com/#/upload_portfolio";
        try{
            Assert.assertEquals(expectedUrl,base.Driver.getCurrentUrl());
            System.out.println("Navigated to correct webpage\n");
            System.out.println(base.Driver.getCurrentUrl());
        }
        catch(Throwable pageNavigationError){
            System.out.println("Incorrect webpage\n");
            Assert.fail();
        }
    }

    @And("^I want to upload a portfolio$")
    public void iWantToUploadAPortfolio() throws Throwable {

        WebElement uploadFile;
        // define file name
        String file = "Demo.xlsx";
        uploadFile = base.Driver.findElement(By.xpath("//input[@type='file']"));
        base.Driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //set up relative path for upload
        String path = System.getProperty("user.dir") + "\\attachment\\" + file;
        System.out.println(path);
        uploadFile.sendKeys(path);
        base.Driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // Check if correct file is uploaded
        base.Driver.findElement((By.xpath("//span[text()[contains(.," + "'" + file + "'" + ")]]")));
        WebElement upload = base.Driver.findElement(By.xpath("//button[@class='UploadWidget__textBold___1FA3V fonts__baseFontBold___1NYJU UploadWidget__enabled___37zIO']"));
        upload.click();
        // Check if file is upload without error
        WebDriverWait wait = new WebDriverWait(base.Driver,30);
        System.out.println("Wait");
        WebElement waitElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='MarginCall__container___2rypT ']")));
        System.out.println("Done");

    }

    @And("^I want to valued a portfolio$")
    public void iWantToValuedAPortfolio() throws Throwable {
        //find MarginCall Container
        //Fail if not found
        base.Driver.findElement(By.xpath("//div[@class='MarginCall__container___2rypT ']"));
        //SelectALL Transaction
        WebElement checkBoxALL;
        checkBoxALL = base.Driver.findElement(By.xpath("//div[@class='VariableCheckbox__checkboxArrowContainer___nmTMI']"));
        checkBoxALL.click();
        //Click on Request Valuation
        WebElement valuationButton;
        System.out.println("Click on Request Valuation");
        base.Driver.findElement(By.xpath("//div[text()[contains(.,\'Request Valuation\')]]"));
        valuationButton = base.Driver.findElement(By.xpath("//div[text()[contains(.,\'Request Valuation\')]]"));
        valuationButton.click();

        //Wait for valuation to happens
        WebDriverWait wait = new WebDriverWait(base.Driver,120);
        System.out.println("Wait for Valuation Response");
        WebElement waitElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='MarginCall__content___2Epb0 ']")));
        System.out.println("Completed");
    }

    @And("^I want to generate a MarginCalls NonValuated Portfolio$")
    public void iWantToGenerateAMarginCallsNonValuatedPortfolio() throws Throwable {

        //Verify Correct Portfolio is uploaded
        //ValuePortfolio Behavior
        List callTypes = new ArrayList<String>();
        List exposures = new ArrayList<String>();
        List<WebElement> listOfExposures = base.Driver.findElements(By.xpath("//div[@class='MarginCall__cell___1qVDS MarginCall__largeCell___3VAoO exposure']"));
        List<WebElement> listOfCallTypes = base.Driver.findElements(By.xpath("//div[@class='MarginCall__cell___1qVDS MarginCall__callTypeCell___2XUpD callType']"));

        for(WebElement a:listOfCallTypes){
            callTypes.add(a.getText());
        }
        for(WebElement b: listOfExposures){
            exposures.add(b.getText());
        }

        System.out.println(Arrays.toString(callTypes.toArray()));
        System.out.println(Arrays.toString(exposures.toArray()));
        System.out.println(callTypes.size());

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
                }
            }else if(callTypes.get(i).equals("Initial")){
                if(exposures.get(i).equals("0")){
                    System.out.println("\nThis is element " + i);
                    System.out.println("Outcome is incorrect");
                    System.out.println("Call Types = " + callTypes.get(i));
                    System.out.println("Exposure = " + exposures.get(i));
                }else{
                    System.out.println("\nThis is element " + i);
                    System.out.println("Outcome is expected");
                    System.out.println("Call Types = " + callTypes.get(i));
                    System.out.println("Exposure = " + exposures.get(i));
                }
            }
        }

        //Click on Generate Margin Call button
        WebElement generateMarginButton=base.Driver.findElement(By.xpath("//div[text()[contains(.,\'Generate Margin Calls\')]]"));
        generateMarginButton.click();

    }

    @And("^I want to generate a MarginCalls Valuated Portfolio$")
    public void iWantToGenerateAMarginCallsValuatedPortfolio() throws Throwable {
        //Verify Correct Portfolio is uploaded
        //NonValue Portfolio Behaviour
        List callTypes = new ArrayList<String>();
        List exposures = new ArrayList<String>();
        List callDates = new ArrayList<String>();
        List<WebElement> listOfExposures = base.Driver.findElements(By.xpath("//div[@class='MarginCall__cell___1qVDS MarginCall__largeCell___3VAoO exposure']"));
        List<WebElement> listOfCallTypes = base.Driver.findElements(By.xpath("//div[@class='MarginCall__cell___1qVDS MarginCall__callTypeCell___2XUpD callType']"));
        List<WebElement> listOfCallDates = base.Driver.findElements(By.xpath("//div[@class='MarginCall__cell___1qVDS MarginCall__dateCell___1a8Bj MarginCall__boldCellText___394uJ fonts__baseFontBold___1NYJU callDate']"));
        for(WebElement a:listOfCallTypes){
            callTypes.add(a.getText());
        }
        for(WebElement b:listOfExposures){
            exposures.add(b.getText());
        }
        for(WebElement c:listOfCallDates){
            callDates.add(c.getText());
        }

        System.out.println(Arrays.toString(callTypes.toArray()));
        System.out.println(Arrays.toString(exposures.toArray()));

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
                }
            }
        }

        //Click on Generate Margin Call button
        WebElement generateMarginButton=base.Driver.findElement(By.xpath("//div[text()[contains(.,\'Generate Margin Calls\')]]"));
        generateMarginButton.click();

        for(int j=0; j < callDates.size(); j++){
            if(callDates.get(j).equals("-"))
            {
                System.out.println("MarginCalls is not generated");
            }else{
                System.out.println("MarginCalls is generated");
            }
        }

    }

    @Then("^I want to send MarginCalls$")
    public void iWantToSendMarginCalls() throws Throwable {
        WebElement sendMarginButton=base.Driver.findElement(By.xpath("//div[text()[contains(.,\'Send Margin Calls\')]]"));
        sendMarginButton.click();

        WebElement reconcilePage = base.Driver.findElement(By.xpath("//div[text()[contains(.,\'Reconcile\')]]"));
        reconcilePage.click();

        base.Driver.findElement(By.xpath("//div[@class='MarginAgreementList__actionContainer___RdRCJ']"));
        System.out.println("MarginCall Sent");
    }

}
