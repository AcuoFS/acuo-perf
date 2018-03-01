package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class PledgePageObject extends BaseClass {

    public PledgePageObject(WebDriver driver){
        super(driver);
    }

    @FindBy(how = How.XPATH,using = "//div[@class='OptimisationWidget__optItemSlider___WVZyY']")
    public static WebElement slider_Liquidity;

    @FindBy(how = How.XPATH,using = "//div[@class='OptimisationWidget__optItemSlider___WVZyY cost']")
    public static WebElement slider_Cost;

    @FindBy(how = How.XPATH,using = "//img[@class='Pledge__tick___21G4m']")
    public static WebElement tick_All;

    @FindBy(how = How.XPATH,using = "//div[@class='OptimisationWidget__optButton___fXMOm ']")
    public static WebElement button_Allocate;

    @FindBy(how = How.XPATH,using = "//input[@class='Constraints__constraintsNumberBox___1_lqq']")
    public static WebElement constraint_Movement;

    @FindBy(how =How.XPATH,using = "//div[@class='OptimisationWidget__tab___3nTmq fonts__baseFontBold___1NYJU false']")
    public static WebElement constraint_tab;




}
