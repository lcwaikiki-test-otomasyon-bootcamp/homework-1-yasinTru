import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class TestScript {
    public static void main(String[] args) throws InterruptedException
    {

        //Setup things.

        //Chrome and Webdriver setups.
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled"); //removes the detection
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"}); //removes the banner
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
        Actions actions= new Actions(driver);   //to hover the user logo on the login page.

        //into the Main page.
        driver.navigate().to("https://www.lcwaikiki.com/tr-TR/TR/");  //Navigating to the page.
        driver.manage().window().maximize();   //fullscreen.
        Thread.sleep(2000);
        WebElement mainLogo= driver.findElement(By.xpath("//header/div[2]/div[3]/div[1]/span[1]/div[1]/a[1]"));    //logo element for Register process.
        actions.moveToElement(mainLogo).perform();     //logo's hover.
        WebElement mainLoginButton= driver.findElement(By.cssSelector(".cart-action__btn.cart-action__btn--bg-white"));
        Thread.sleep(3000);
        mainLoginButton.click();    //clicking on the button.


        //Register steps.
        WebElement ePostaInput= driver.findElement(By.cssSelector("#RegisterFormView_RegisterEmail"));
        ePostaInput.sendKeys("kesinlikleyanlis");
        WebElement passwordInput= driver.findElement(By.cssSelector("#RegisterFormView_Password"));
        passwordInput.sendKeys("yanlisTabii");
        WebElement phoneNumber= driver.findElement(By.cssSelector("#RegisterPhoneNumberTR"));
        phoneNumber.sendKeys("1111111111");
        WebElement privacyLabel= driver.findElement(By.cssSelector("label[for='member-privacy-approve'] ins[class='iCheck-helper']"));
        privacyLabel.click();
        // login-form__button.login-form__button--bg-blue    --> Button but cssSelector
        // driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();     --> Button but xpath
       // driver.findElement(By.cssSelector(".button.bc-blue.register-button-link.submit-button")).click();
        Thread.sleep(2000);

        //Writing error messages to the console.
        String mailErrorMessage=driver.findElement(By.cssSelector("#RegisterFormView_RegisterEmail-error")).getText();
        System.out.println(mailErrorMessage);
        String passErrorMessage=driver.findElement(By.id("RegisterFormView_Password-error")).getText();
        System.out.println(passErrorMessage);
        String phoneErrorMessage=driver.findElement(By.id("RegisterPhoneNumberTR-error")).getText();
        System.out.println(phoneErrorMessage);

    }
}
