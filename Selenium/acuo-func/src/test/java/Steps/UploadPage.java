package Steps;

import Base.BaseUtil;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

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
        String file = "demo.xlsx";
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
        base.Driver.findElement(By.xpath("//div[@class='MarginCall__container___2rypT ']"));

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

    }

    @And("^I want to generate a MarginCalls$")
    public void iWantToGenerateAMarginCalls() throws Throwable {
    }

    @Then("^I want to send MarginCalls$")
    public void iWantToSendMarginCalls() throws Throwable {
    }
}
