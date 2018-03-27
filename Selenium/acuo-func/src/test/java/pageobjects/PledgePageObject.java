package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import java.util.List;

public class PledgePageObject extends BaseClass {

    public PledgePageObject(WebDriver driver){
        super(driver);
    }

    @FindBy(how = How.XPATH,using = "//div[@class='OptimisationWidget__optItemSlider___WVZyY liquidity']")
    public static WebElement slider_Liquidity;

    @FindBy(how = How.XPATH,using = "//div[@class='OptimisationWidget__optItemSlider___WVZyY cost']")
    public static WebElement slider_Cost;

    @FindBy(how = How.XPATH,using = "//img[@class='Pledge__tick___21G4m']")
    public static WebElement tick_All;

    @FindBy(how = How.XPATH,using = "//div[@class='OptimisationWidget__optButton___fXMOm allocate ']")
    public static WebElement button_Allocate;

    @FindBy(how = How.XPATH,using = "//div[@class='OptimisationWidget__optButton___fXMOm pledge OptimisationWidget__optBtnPledge___3swZC']")
    public static WebElement button_Pledge;

    @FindBy(how = How.XPATH,using = "//input[@class='Constraints__constraintsNumberBox___1_lqq']")
    public static WebElement constraint_Movement;

    @FindBy(how =How.XPATH,using = "//div[@class='OptimisationWidget__tab___3nTmq fonts__baseFontBold___1NYJU false']")
    public static WebElement constraint_tab;

    static int allocatedAmountIndicator_Nr = 0;

    public static String allocatedAmount_path = "//div[@class='Selection__bigFig___3kj9G Selection__bold___1Sk2i fonts__baseFontBold___1NYJU']";
    public static String rightExpandBtn_path = ".//img[@class='Selection__cursorPointer___2Nxdi']";
    public static String fxValue_path=".//td[@class='fx']";
    public static String adjustedValue_path = ".//td[@class='adjusted_value']";
    public static String totalValue_path=".//td[@class='Selection__totalTable1___2ZlWw Selection__notAll___2S81v']";


    public static void checkAllocatedAmount(WebDriver driver){
        List<WebElement> listOfAllocatedAmount = driver.findElements(By.xpath(allocatedAmount_path));
        for (WebElement allocatedAmount : listOfAllocatedAmount) {
            allocatedAmountIndicator_Nr = allocatedAmountIndicator_Nr + 1;
            if (allocatedAmount.getText().equals("0")) {
                System.out.println("This is Element " + allocatedAmountIndicator_Nr + " and The allocated Amount is " + allocatedAmount.getText());
                Assert.fail();
            } else {
                System.out.println("This is Element " + allocatedAmountIndicator_Nr + " and The allocated Amount is " + allocatedAmount.getText());
            }
        }
        allocatedAmountIndicator_Nr =0;
    }




}
