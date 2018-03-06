package pageobjects;
import helpers.Variables;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class UploadPageObject extends BaseClass{

    static int marginCall_generatedNr = 0;

    public UploadPageObject(WebDriver driver){
		super(driver);
	}    

	//****************Place required to change when xpath or property changes

	@FindBy(how = How.XPATH,using = "//div[@class='UploadPortfolio__text___3USYJ']")
	public static WebElement uploadPortfolio;

    @FindBy(how = How.XPATH,using = "//input[@type='file']")
    public static WebElement uploadFileLink;

    @FindBy(how = How.XPATH,using = "//button[@class='UploadWidget__textBold___1FA3V fonts__baseFontBold___1NYJU UploadWidget__enabled___37zIO']")
    public static WebElement uploadButton;

    @FindBy(how = How.XPATH,using = "//div[@class='VariableCheckbox__checkboxArrowContainer___nmTMI']")
    public static WebElement checkboxAll;

    @FindBy(how = How.XPATH,using = "//div[text()[contains(.,\'Request Valuation\')]]")
    public static WebElement valuationButton;

    @FindBy(how = How.XPATH,using = "//div[text()[contains(.,\'Generate Margin Calls\')]]")
    public static WebElement generateMarginButton;

    @FindBy(how = How.XPATH,using = "//div[text()[contains(.,\'Send Margin Calls\')]]")
    public static WebElement sendMarginButton;

    @FindBy(how = How.XPATH,using ="//div[text()[contains(.,\'Reconcile\')]]")
    public static WebElement reconcilePageButton;



    //****************Place required to change when xpath or property changes

    //-------------------------Functions-----------------------------------//
    public static void upload_a_File(String file,WebDriver driver)
    {
        uploadFileLink.isDisplayed();
        String File_path = Variables.path + file;
        uploadFileLink.sendKeys(File_path);
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        driver.findElement((By.xpath("//span[text()[contains(.," + "'" + file + "'" + ")]]")));
        uploadButton.isDisplayed();
        uploadButton.click();
        WebDriverWait wait = new WebDriverWait(driver,120);
        System.out.println("Wait for Upload to happens.........");
        wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//div[@class='MarginCall__container___2rypT ']"))));
        System.out.println("Upload Completed");
    }


    public static void verify_numberOfMarginCall(WebDriver driver)
    {
        List callTypes = new ArrayList<String>();
        List callDates = new ArrayList<String>();
        List<WebElement> listOfCallDates = driver.findElements(By.xpath("//div[@class='MarginCall__cell___1qVDS MarginCall__dateCell___1a8Bj MarginCall__boldCellText___394uJ fonts__baseFontBold___1NYJU callDate']"));
        List<WebElement> listOfCallTypes = driver.findElements(By.xpath("//div[@class='MarginCall__cell___1qVDS MarginCall__callTypeCell___2XUpD callType']"));

        for(WebElement a:listOfCallTypes){
            callTypes.add(a.getText());
        }
        for(WebElement b: listOfCallDates){
            callDates.add(b.getText());
        }

        for(int i=0; i < callTypes.size(); i++){
            if(callTypes.get(i).equals("Variation")){
                if(!callDates.get(i).equals("-")){
                    marginCall_generatedNr = marginCall_generatedNr + 1;
                    System.out.println("\nThis is element " + i);
                    System.out.println("MarginCall is generated");
                    System.out.println("Call Types = " + callTypes.get(i));
                    System.out.println("Call Dates = " + callDates.get(i));
                }else{
                    System.out.println("\nThis is element " + i);
                    System.out.println("MarginCall is not generated");
                    System.out.println("Call Types = " + callTypes.get(i));
                    System.out.println("No Call Dates");
                }
            }else if(callTypes.get(i).equals("Initial")){
                if(callDates.get(i).equals("-")){
                    System.out.println("\nThis is element " + i);
                    System.out.println("MarginCall is not generated");
                    System.out.println("Call Types = " + callTypes.get(i));
                    System.out.println("No Call Dates");
                }else{
                    System.out.println("\nThis is element " + i);
                    System.out.println("MarginCall is generated");
                    System.out.println("Call Types = " + callTypes.get(i));
                    System.out.println("Call Dates = " + callDates.get(i));
                }
            }
        }

    }

    public static void navigate_ReconPage()
    {
        reconcilePageButton.isDisplayed();
        reconcilePageButton.click();
    }

    public static void verify_ReconActionPair()
    {
        List<WebElement> listOfActionWrap = driver.findElements(By.xpath("//div[@class='MarginAgreementList__actionWrap___lmpcN']"));
        if (marginCall_generatedNr!=(listOfActionWrap.size())) {
            System.out.println("\nIncorrect Number of Reconcile Action Pair");
            System.out.println("\nTotal Reconciliation Pair found = " + listOfActionWrap.size() + " Versus Total MarginCall Generated = " + marginCall_generatedNr);
            marginCall_generatedNr = 0;
            Assert.fail();
        }else{
            System.out.println("\nCorrect Number of Reconcile Action Pair");
            System.out.println("\nTotal Reconciliation Pair found = " + listOfActionWrap.size() + " Versus Total MarginCall Generated = " + marginCall_generatedNr);
            marginCall_generatedNr = 0;
        }

    }
}
		

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
