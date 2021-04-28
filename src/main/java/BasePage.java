import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class BasePage {
    public static WebDriver driver;


    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement locateElement(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));

    }

    public void implicitWait(int seconds)
    {
        driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }
    public void mousehoverMainMenu(By locator)
    {
        Actions actions = new Actions(driver);
        WebElement mainMenu = findElement(locator);
        Action mouseOverHome = actions.moveToElement(mainMenu).build();
        mouseOverHome.perform();
    }
    public void mousehoverSubMenu(By locator)
    {
        Actions actions = new Actions(driver);
        WebElement subMenu = findElement(locator);
        actions.moveToElement(subMenu);
        actions.click().build().perform();
    }

    public WebElement findElement(By locator) {
        return driver.findElement(locator);
    }
    public void EnterSendKeys(By locator)
    {
        locateElement(locator).sendKeys(Keys.ENTER);
    }
    public void click(By locator){
        locateElement(locator).click();
    }

    public void selectDropDown(By locator, String text)
    {
        WebElement drop=findElement(locator);
        Select dropdown= new Select(drop);
        dropdown.selectByVisibleText(text);
    }
    public String getSelectedTextFromDropDown(By locator)    {
        WebElement drop=findElement(locator);
        Select dropdown= new Select(drop);
        WebElement option = dropdown.getFirstSelectedOption();
        String defaultItem = option.getText();
        return defaultItem;
    }
    public void sendKeys(By locator,String text){
        locateElement(locator).sendKeys(text);
    }

    public String takeScreenshot() throws IOException {

        File screenshot= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

        Random random=new Random();
        String fileName="Screenshot"+random.nextInt(10000);


        Files.move
                (Paths.get(screenshot.getAbsolutePath()),
                        Paths.get(System.getProperty("user.dir")+"/Report/"+fileName+".png"));
        return  fileName+".png";
    }

    public String getText(By locator){
        return locateElement(locator).getText();
    }
}
