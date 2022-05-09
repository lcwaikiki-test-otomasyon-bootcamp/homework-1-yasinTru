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

        //Setup adımları.
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled"); //removes the detection
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"}); //removes the banner
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
        Actions actions= new Actions(driver);   //giriş sayfasındaki kulllanıcı logosunu hover'lamak için.

        //Siteye giriş işlemleri.
        driver.navigate().to("https://www.lcwaikiki.com/tr-TR/TR/");
        driver.manage().window().maximize();
        Thread.sleep(2000);
        WebElement girisLogo= driver.findElement(By.xpath("//header/div[2]/div[3]/div[1]/span[1]/div[1]/a[1]"));
        actions.moveToElement(girisLogo).perform();

        WebElement mainGirisButon= driver.findElement(By.cssSelector(".cart-action__btn.cart-action__btn--bg-white"));
        Thread.sleep(3000);
        System.out.println("main butonu");
        mainGirisButon.click();


        //Üye ol sayfası işlemleri
        WebElement ePostaInput= driver.findElement(By.cssSelector("#RegisterFormView_RegisterEmail"));
        ePostaInput.sendKeys("kesinlikleyanlis");
        WebElement sifreInput= driver.findElement(By.cssSelector("#RegisterFormView_Password"));
        sifreInput.sendKeys("yanlisTabii");
        WebElement telNo= driver.findElement(By.cssSelector("#RegisterPhoneNumberTR"));
        telNo.sendKeys("1111111111");
        WebElement privacyLabel= driver.findElement(By.cssSelector("label[for='member-privacy-approve'] ins[class='iCheck-helper']"));
        privacyLabel.click();
        // login-form__button.login-form__button--bg-blue    --> Buton ama cssSelector
        // driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();     --> Buton ama xpath
       // driver.findElement(By.cssSelector(".button.bc-blue.register-button-link.submit-button")).click();
        Thread.sleep(2000);

        //Console çıktıları.
        String mailHataMesaji=driver.findElement(By.cssSelector("#RegisterFormView_RegisterEmail-error")).getText();
        System.out.println(mailHataMesaji);
        String sifreHataMesaji=driver.findElement(By.id("RegisterFormView_Password-error")).getText();
        System.out.println(sifreHataMesaji);
        String telHataMesaji=driver.findElement(By.id("RegisterPhoneNumberTR-error")).getText();
        System.out.println(telHataMesaji);

    }
}
