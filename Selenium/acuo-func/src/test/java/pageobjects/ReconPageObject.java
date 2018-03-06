package pageobjects;

import helpers.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class ReconPageObject extends BaseClass {

    public ReconPageObject(WebDriver driver) {
        super(driver);
    }

    //****************Place required to change when xpath or property changes
    @FindBy(how = How.XPATH, using = "//div[text()[contains(.,\'Pledge\')]]")
    public static WebElement pledgePageButton;

    static String actionWrap_path = "//div[@class='MarginAgreementList__actionWrap___lmpcN']";
    static String direction_path = "//div[@class='MarginAgreementList__actPanel___2BH6p MarginAgreementList__act_L___3wA7N']//div[@class='MarginAgreementList__direction___qdfaj']/div";
    static String adjustmentAmount_path = ".//button[@class='MarginAgreementAssets__btnAddAdj___3bm1z']";
    static String noCounterParty_path = ".//div[@class='MarginAgreementList__legalEntity___37RNC fonts__baseFontBold___1NYJU MarginAgreementList__noMatched___gXbNU']";
    static String uncheckbox_path = ".//div[@class='MarginAgreementList__packageCheckBox___13J90 MarginAgreementList__show___39nQF left_unchecked']//img[@src='./images/reconcile/checkbox.png']";
    static String greenbtn_path = ".//div[@class='MarginAgreementList__actBtn___34K6V MarginAgreementList__actBtnGreen___11ohp']";
    static String orangebtn_path = ".//div[@class='MarginAgreementList__actBtn___34K6V MarginAgreementList__actBtnOrange___136xv']";
    static String redbtn_path = ".//div[@class='MarginAgreementList__actBtn___34K6V MarginAgreementList__actBtnRed___iTCpg']";
    static String checkbox_fullpath = "//div[@class='MarginAgreementList__actionWrap___lmpcN']//div[@class='MarginAgreementList__packageCheckBox___13J90 MarginAgreementList__show___39nQF left_unchecked']//img[@src='./images/reconcile/checkbox.png']";
    static String pledgeElement_path = "//div[@class='Selection__panel___3v30b']";
    static int outDirection_Nr = 0;
    static int totalOut_Nr = 0;

//    ---------------------------------------------------------------------------


    public static void navigate_PledgePage() {
        pledgePageButton.isDisplayed();
        pledgePageButton.click();
    }

    public static void verify_PledgeElementNumber() {
        List<WebElement> listOfActionWrap = driver.findElements(By.xpath(pledgeElement_path));
        if (totalOut_Nr != (listOfActionWrap.size())) {
            System.out.println("\nIncorrect Number of Pledge Element Number");
            System.out.println("Total Pledge Element found = " + listOfActionWrap.size() + " Versus Total Out Direction = " + totalOut_Nr);
            Assert.fail();
        } else {
            System.out.println("\nCorrect Number of Pledge Element Number");
            System.out.println("Total Pledge Element found = " + listOfActionWrap.size() + " Versus Total Out Direction = " + totalOut_Nr);
        }

    }

    //-----------------------------Check Reconcile ----------------------------------------------------------------
    public static void reconcileCall(WebDriver driver) throws InterruptedException {
        List<WebElement> listOfAction = driver.findElements(By.xpath(actionWrap_path));
        outDirection_Nr=0;
        int count = 0;
        WebDriverWait wait = new WebDriverWait(driver, 5);
        for (WebElement action : listOfAction) {
            count = count + 1;
            Thread.sleep(500);
            System.out.println("This is Element " + count);
            List<WebElement> listOfCheckbox = action.findElements(By.xpath(uncheckbox_path));
            List<WebElement> listOfAddButtons = action.findElements(By.xpath(adjustmentAmount_path));

            //-------------------
            List<WebElement> listOfNoMatchedPortfolio = action.findElements(By.xpath(noCounterParty_path));

            if (listOfNoMatchedPortfolio.size() == 0) {
                System.out.println("Found a Match Portfolio");
                int checkboxNr = listOfCheckbox.size();
                System.out.println("Total Checkbox uncheck in this action is " + checkboxNr);
                for (int checkboxCount = 0; checkboxCount < checkboxNr; checkboxCount = checkboxCount + 1) {
                    wait.until(ExpectedConditions.elementToBeClickable(By.xpath(checkbox_fullpath)));
                    listOfCheckbox.get(checkboxCount).click();
                    System.out.println("Clicking checkbox " + checkboxCount);
                }

                for (WebElement addButton : listOfAddButtons) {
                    System.out.println("Adding the adjustment amount");
                    addButton.click();
                }

                List directionType = new ArrayList<String>();
                WebElement direction = driver.findElement(By.xpath(direction_path));
                if (direction.getText().equals("OUT")) {
                    System.out.println("Direction is " + direction.getText());
                    outDirection_Nr = outDirection_Nr + 1;
                    directionType.add(direction.getText());
                } else {
                    directionType.add(direction.getText());
                    System.out.println("Direction is " + direction.getText());
                }


//            Check visibility of OK Button
                List<WebElement> listOfOKButtonsGreen = action.findElements(By.xpath(greenbtn_path));
                List<WebElement> listOfOKButtonsOrange = action.findElements(By.xpath(orangebtn_path));
                List<WebElement> listOfOKButtonsRed = action.findElements(By.xpath(redbtn_path));
                if (listOfOKButtonsGreen.size() != 0) {
                    Log.info("Reconcile an Element");
                    action.findElement(By.xpath(greenbtn_path)).click();
                    System.out.println("Click on Green Button");
                } else if (listOfOKButtonsOrange.size() != 0) {
                    Log.info("Reconcile an Element");
                    action.findElement(By.xpath(orangebtn_path)).click();
                    System.out.println("Click on Orange Button");
                } else if (listOfOKButtonsRed.size() != 0) {
                    Log.info("Reconcile an Element");
                    action.findElement(By.xpath(redbtn_path)).click();
                    System.out.println("Click on Red Button");
                } else {
                    System.out.println("Element not Found?");
                    Assert.fail();
                }

            } else {
                System.out.println("No Matched Portfolio");
            }
        }
        totalOut_Nr = outDirection_Nr;
        System.out.println("Total Out Element=" + totalOut_Nr);
    }

}
