import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class Browser {
    public static WebDriver openBrowser(String url){

        String baseDirectory = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", baseDirectory + "/src/main/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.navigate().to(url);
        return driver;
    }

    public static void closeBrowser(WebDriver driver){
        driver.quit();
    }
}
