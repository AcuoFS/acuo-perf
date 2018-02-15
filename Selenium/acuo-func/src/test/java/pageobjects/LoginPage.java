package pageobjects;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class LoginPage extends BaseClass{

    private static WebDriverWait wait;

    public LoginPage(WebDriver driver){
		super(driver);
	}    

	//****************Place required to change when xpath or property changes

	@FindBy(how = How.XPATH,using = "//div[@class='Login__buttonHolder___3TWr4']")
	public static WebElement loginButton;

	@FindBy(how = How.XPATH,using = "//input[@type='text']")
	public static WebElement usernameInput;

	@FindBy(how = How.XPATH,using = "//input[@type='password']")
	public static WebElement passwordInput;

	@FindBy(how = How.XPATH,using = "//div[text()[contains(.,\'2FA SECURITY CODE\')]]")
	public static WebElement twoFAText;

	@FindBy(how = How.XPATH,using = "//input[@type='password']")
	public static WebElement twoFALogin;

	@FindBy(how = How.XPATH,using = "//div[@class='TwoFA__buttonHolder___23naz']")
	public static WebElement twoFAButton;


	//****************Place required to change when xpath or property changes

    //-------------------------Functions-----------------------------------//

    public static void input_userName(String userName,WebDriver driver)
    {
        System.out.println("Key in Username");
        usernameInput.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        //key in username
        usernameInput.sendKeys(userName);
    }

    public static void input_passWord(String passWord,WebDriver driver)
    {
        System.out.println("Key in Password");
        passwordInput.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        //key in the password
        passwordInput.sendKeys(passWord);
    }

    public static void check_loginError()
    {
        System.out.println("Check username and password");
        List<WebElement> foundElement = driver.findElements(By.xpath("//div[@class='Login__pw_error___3qDZz']"));
        if (foundElement.size() > 0)
        {
            System.out.println("Incorrect username or password");
            Assert.fail();
        } else {
            System.out.println("Login Successful");
        }
    }

    public static void input_twoFALogin(String twoFACode,WebDriver driver)
    {
        System.out.println("Key in the 2FA code");
        twoFALogin.isDisplayed();
        twoFALogin.sendKeys(Keys.chord(Keys.CONTROL,"a"));
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        twoFALogin.sendKeys(twoFACode);
    }

    public static void url_check(String correctURL,WebDriver driver)
    {
        String expected_URL = correctURL;
        System.out.println("Check navigation");
        System.out.println("Expected URL is " + expected_URL);
        System.out.println("Current Url is " + driver.getCurrentUrl());
        //Proceed if URL is correct
        //Fail if the URL is incorrect

        try{
            Assert.assertEquals(expected_URL,driver.getCurrentUrl());
            System.out.println("\nNavigated to correct webpage");
        }
        catch(Throwable pageNavigationError){
            System.out.println("\nIncorrect webpage");
            Assert.fail();
        }
    }
}
		

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
