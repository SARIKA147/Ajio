import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

public class HomePage extends BasePage{

    private WebDriver driver;
    private By searchText=By.name("searchVal");
    private By headerText=By.className("header2");

    private By imageItem=By.xpath("//img[@class='rilrtl-lazy-img  rilrtl-lazy-img-loaded']");
    private By size=By.xpath("//div[@class='size-variant-block']/div[@class='size-swatch']/div[.='M']");
    private By addtToCartButton=By.xpath("//*[@class='pdp-addtocart-button']");
    private By gotToCartButton=By.xpath("//*[@class='btn-cart']");
    private By emailIdTextBox=By.name("username");

    private By price=By.xpath("//span[contains(text(),'price')]");
    private By minTextBox=By.id("minPrice");
    private By maxTextBox=By.id("maxPrice");
    private By priceFilterButton=By.xpath("//*[@class='rilrtl-button ic-toparw']");
    private By priceMinMaxDisplay=By.xpath("//*[@class='pull-left']");

    private By boysCheckBox=By.xpath("//*[@class='facet-linkname facet-linkname-genderfilter facet-linkname-Boys']");
    private By count=By.xpath("//*[@class='length']");

    private By customerCareLink=By.linkText("Customer Care");
    private By cancellationFaqLink=By.id("left-tabs-example-tab-1");
    private By shippingFaqLink=By.id("left-tabs-example-tab-0");

    private By filterDropdown=By.xpath("//*[@id=\"products\"]/div[2]/div/div[3]/div/select");

    private By menLink=By.linkText("MEN");
    private By categoriesLink=By.linkText("CATEGORIES");
    private By womenLink=By.linkText("WOMEN");
    private By kidsLink=By.linkText("KIDS");
    private By kitchenlink=By.linkText("HOME AND KITCHEN");
    private By indielink=By.linkText("INDIE");
    private By jeansLink=By.linkText("Jeans");
    private By shirtLink=By.linkText("Shirts");

    private By proceedShipingButton=By.xpath("//button[@class='rilrtl-button button shipping-button']");

    private By who_we_areLink=By.linkText("Who We Are");
    private By join_our_teamLink=By.linkText("Join Our Team");
    private By terms_and_conditionsLink=By.linkText("Terms & Conditions");

    private By returnsLink=By.linkText("RETURNS");
    private By returnsdiv=By.xpath("//div[@class='return-desc']");

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver=driver;
    }
    /*------------------------Commonly Used Methods--------------------------------------------------------*/
    public void search(String searchKeyword) throws IOException {
        try{
            locateElement(searchText);
            findElement(searchText);
            sendKeys(searchText,searchKeyword);
            implicitWait(10);
            EnterSendKeys(searchText);
        }
        catch (Exception e){
            System.out.println(e);
        }

    }

    public void addToBag(String searchKeyword) throws IOException{
        search(searchKeyword);
        locateElement(imageItem);
        findElement(imageItem);
        click(imageItem);
        String mainWindow = driver.getWindowHandle();
        Set<String> set = driver.getWindowHandles();
        Iterator<String> itr = set.iterator();
        while (itr.hasNext()) {
            String childWindow = itr.next();
            if (!mainWindow.equals(childWindow)) {
                driver.switchTo().window(childWindow);
                driver.switchTo().window(childWindow).findElement(size);
                locateElement(size);
                click(size);
                implicitWait(10);
                driver.switchTo().window(childWindow).findElement(addtToCartButton);
                locateElement(addtToCartButton);
                click(addtToCartButton);
                locateElement(gotToCartButton);
                driver.switchTo().window(childWindow).findElement(gotToCartButton);
                click(gotToCartButton);
            }
        }
    }

    public void verifyMenuMenCategory() throws IOException{
        try{
            locateElement(menLink);
            findElement(menLink);
            mousehoverMainMenu(menLink);
            implicitWait(10);
            locateElement(categoriesLink);
            findElement(categoriesLink);
            mousehoverMainMenu(categoriesLink);
            //implicitWait(10);
            Thread.sleep(2000);
        }
        catch(Exception e){
            System.out.println(e);
        }

    }
    /*------------------------------------------------------------------------------------------  */
    public void searchItem(String searchKeyword) throws IOException {
        try{
            search(searchKeyword);
            Assert.assertEquals(getText(headerText).toLowerCase(),searchKeyword.toLowerCase(),"Displayed item is not "+searchKeyword);
            Reports.extentTest.log(Status.PASS,"Selected Item : "+searchKeyword, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
        catch(AssertionError e){
            Reports.extentTest.log(Status.FAIL,"Selected Item : "+searchKeyword,MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
            Assert.fail();
        }
        catch (Exception e)
        {
            Reports.extentTest.log(Status.FAIL,"Selected Item : "+searchKeyword,MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
            Assert.fail();
        }
    }

    public void searchUnavailableItem(String searchKeyword) throws IOException {
        try {
            search(searchKeyword);
            Assert.assertEquals(getText(headerText).toLowerCase(),searchKeyword.toLowerCase(),"Entered item is "+searchKeyword);
            Reports.extentTest.log(Status.PASS,"Searched Item: "+searchKeyword,MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
        catch(AssertionError e){
            Reports.extentTest.log(Status.FAIL,"Searched Item: "+searchKeyword,MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
            Assert.fail();
        }
        catch (Exception e)
        {
            Reports.extentTest.log(Status.FAIL,"Searched Item: "+searchKeyword,MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
            Assert.fail();
        }
    }

    public void searchBrand(String searchKeyword) throws IOException {
        try {
            search(searchKeyword);
            Assert.assertEquals(getText(headerText).toLowerCase(),searchKeyword.toLowerCase(),"Displayed Brand is not "+searchKeyword);
            Reports.extentTest.log(Status.PASS,"Selected Brand: "+searchKeyword,MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
        catch(AssertionError e){
            Reports.extentTest.log(Status.FAIL,"Selected Brand: "+searchKeyword,MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
            Assert.fail();
        }
        catch (Exception e)
        {
            Reports.extentTest.log(Status.FAIL,"Selected Brand: "+searchKeyword,MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
            Assert.fail();
        }
    }

    public void addItemToBag(String searchKeyword) throws IOException {
        try {
            addToBag(searchKeyword);
            Reports.extentTest.log(Status.PASS,"Selected Item "+searchKeyword,MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
        catch (Exception e)
        {
            Reports.extentTest.log(Status.FAIL,"Selected Item "+searchKeyword,MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
            Assert.fail();
        }
    }

    public void proceedShipping(String searchKeyword) throws IOException {
        try{
            addToBag(searchKeyword);
            locateElement(proceedShipingButton);
            findElement(proceedShipingButton);
            click(proceedShipingButton);
            implicitWait(10);
            boolean b=findElement(emailIdTextBox).isEnabled();
            Assert.assertTrue(b=true,"Email Id field is not enabled");
            Reports.extentTest.log(Status.PASS,"Selected Item "+searchKeyword,MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
        catch(AssertionError e){
            Reports.extentTest.log(Status.FAIL,"Selected Item "+searchKeyword,MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
            Assert.fail();
        }
       catch (Exception e)
        {
            Reports.extentTest.log(Status.FAIL,"Selected Item "+searchKeyword,MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
            Assert.fail();
        }
    }

    public void PriceMinAndMax(String searchKeyword) throws IOException {
        try{
            search(searchKeyword);
            locateElement(price);
            findElement(price);
            click(price);
            locateElement(minTextBox);
            findElement(minTextBox);
            sendKeys(minTextBox,"100");

            locateElement(maxTextBox);
            findElement(maxTextBox);
            sendKeys(maxTextBox,"500");

            findElement(priceFilterButton);
            click(priceFilterButton);
            //implicitWait(10);
            Thread.sleep(2000);
            String assertText=getText(priceMinMaxDisplay);
            Assert.assertTrue(assertText.contains("Rs.100 - Rs.500"),"Displayed items are not in the range 100-500");
            Reports.extentTest.log(Status.PASS,"Displayed " +searchKeyword+" for price between:100-500",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
        catch(AssertionError e){
            Reports.extentTest.log(Status.FAIL,"Displayed " +searchKeyword+" for price between:100-500",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
            Assert.fail();
        }
        catch (Exception e)
        {
            Reports.extentTest.log(Status.FAIL,"Displayed " +searchKeyword+" for price between:100-500",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
            Assert.fail();
        }
    }

    public void filterItemBasedOnGender(String searchKeyword) throws IOException {
        try{
            search(searchKeyword);
            String countinitial=getText(count);
            findElement(boysCheckBox);
            click(boysCheckBox);
            //implicitWait(10);
            Thread.sleep(2000);
            String countfinal=getText(count);
            Assert.assertTrue((countinitial!=countfinal),"Elements not filtered properly");
            Reports.extentTest.log(Status.PASS,"Selected Gender is Boys and verified the count",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
        catch(AssertionError e){
            Reports.extentTest.log(Status.FAIL,"Selected Gender is Boys and verified the count",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
            Assert.fail();
        }
        catch (Exception e)
        {
            Reports.extentTest.log(Status.FAIL,"Selected Gender is Boys and verified the count",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
            Assert.fail();
        }
    }

    public void verifyFaq() throws IOException {
        try{
            JavascriptExecutor js = (JavascriptExecutor) driver;
            locateElement(customerCareLink);
            findElement(customerCareLink);
            click(customerCareLink);
            implicitWait(10);
            js.executeScript("window.scrollBy(0,250)");

            locateElement(cancellationFaqLink);
            findElement(cancellationFaqLink);
            click(cancellationFaqLink);
            implicitWait(10);

            locateElement(shippingFaqLink);
            findElement(shippingFaqLink);
            click(shippingFaqLink);
            implicitWait(10);

            boolean b=findElement(shippingFaqLink).isDisplayed()&&
                    findElement(cancellationFaqLink).isDisplayed();
            Assert.assertTrue(b=true,"Shipping FAQ Link and Cancellation FAQ Link not visible");
            Reports.extentTest.log(Status.PASS,"Verify Cancellation and Shipping Faq Link ",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
        catch(AssertionError e){
            Reports.extentTest.log(Status.FAIL,"Verify Cancellation and Shipping Faq Link",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
            Assert.fail();
        }
        catch (Exception e)
        {
            Reports.extentTest.log(Status.FAIL,"Verify Cancellation and Shipping Faq Link",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
            Assert.fail();
        }
    }

    public void filterPriceLowest(String searchKeyword) throws IOException {
        try{
            search(searchKeyword);
            selectDropDown(filterDropdown,"Price (lowest first)");
            Thread.sleep(1000);
            //implicitWait(10);
            String text=getSelectedTextFromDropDown(filterDropdown);
            Assert.assertEquals(text,"Price (lowest first)","Items not Sorted Properly");
            Reports.extentTest.log(Status.PASS,"Selected Item:"+searchKeyword+" and sorted by lowest first",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
        catch(AssertionError e){
            Reports.extentTest.log(Status.FAIL,"Selected Item:"+searchKeyword+" and sorted by lowest first",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
            Assert.fail();
        }
        catch (Exception e)
        {
            Reports.extentTest.log(Status.FAIL,"Selected Item:"+searchKeyword+" and sorted by lowest first",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
            Assert.fail();
        }
    }

    public void filterPriceHighest(String searchKeyword) throws IOException {
        try{
            String text="";
            search(searchKeyword);
            selectDropDown(filterDropdown,"Price (highest first)");
            Thread.sleep(1000);
            //implicitWait(10);
            text=getSelectedTextFromDropDown(filterDropdown);
            Assert.assertEquals(text,"Price (highest first)","Items not Sorted Properly");
            Reports.extentTest.log(Status.PASS,"Selected Item:"+searchKeyword+" and sorted by highest first",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
        catch(AssertionError e){
            Reports.extentTest.log(Status.FAIL,"Selected Item:"+searchKeyword+" and sorted by highest first",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
            Assert.fail();
        }
        catch (Exception e)
        {
            Reports.extentTest.log(Status.FAIL,"Selected Item:"+searchKeyword+" and sorted by highest first",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
            Assert.fail();
        }
    }

    public void verifyFooter() throws IOException {
        try
        {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
            implicitWait(10);
            locateElement(who_we_areLink);
            findElement(who_we_areLink);
            locateElement(terms_and_conditionsLink);
            findElement(terms_and_conditionsLink);
            locateElement(join_our_teamLink);
            findElement(join_our_teamLink);
            Boolean b=(findElement(who_we_areLink).isEnabled())
                    &&(findElement(join_our_teamLink).isEnabled()
                    &&(findElement(terms_and_conditionsLink).isEnabled()));
            Assert.assertTrue(b=true,"Links not visible");
            Reports.extentTest.log(Status.PASS,"Verify the text 'Who We Are', 'Join Our Team',  'Terms & Conditions'" ,MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
        catch(AssertionError e){
            Reports.extentTest.log(Status.FAIL,"Verify the text 'Who We Are', 'Join Our Team',  'Terms & Conditions'" ,MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
            Assert.fail();
        }
        catch(Exception e)
        {
            Reports.extentTest.log(Status.FAIL,"Verify the text 'Who We Are', 'Join Our Team',  'Terms & Conditions'" ,MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
            Assert.fail();
        }
    }

    public void verifyReturnpolicy(String searchKeyword) throws IOException {
        try {
            search(searchKeyword);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            locateElement(imageItem);
            findElement(imageItem);
            click(imageItem);
            implicitWait(10);
            String mainWindow = driver.getWindowHandle();
            Set<String> set = driver.getWindowHandles();
            Iterator<String> itr = set.iterator();
            while (itr.hasNext()) {
                String childWindow = itr.next();
                if (!mainWindow.equals(childWindow)) {
                    driver.switchTo().window(childWindow).getWindowHandle();
                    js.executeScript("window.scrollBy(0,500)");
                    implicitWait(10);
                    boolean b=driver.switchTo().window(childWindow).findElement(returnsdiv).isDisplayed()&&
                            driver.switchTo().window(childWindow).findElement(returnsLink).isDisplayed();
                    Assert.assertTrue(b=true,"Return Policy not displayed");
                    Reports.extentTest.log(Status.PASS,"Selected Item:"+searchKeyword+" and viewed the return policy",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
                }
            }
        }
        catch(AssertionError e){
            Reports.extentTest.log(Status.FAIL,"Selected Item:"+searchKeyword+" and viewed the return policy",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
            Assert.fail();
        }
        catch(Exception e)
        {
            Reports.extentTest.log(Status.FAIL,"Selected Item:"+searchKeyword+" and viewed the return policy",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
            Assert.fail();
        }
    }

    public void verifyMenuItemForMen() throws IOException {

        try {
            verifyMenuMenCategory();
            implicitWait(10);
            locateElement(jeansLink);
            findElement(jeansLink);
            locateElement(shirtLink);
            findElement(shirtLink);
            boolean b= findElement(jeansLink).isDisplayed()&&findElement(shirtLink).isDisplayed();
            Assert.assertTrue(b=true,"Links not visible");
            Reports.extentTest.log(Status.PASS,"Verified Men's Jeans and Shirts link ",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
        catch(AssertionError e){
            Reports.extentTest.log(Status.FAIL,"Verified Men's Jeans and Shirts link",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
            Assert.fail();
        }
        catch(Exception e){
            Reports.extentTest.log(Status.FAIL,"Verified Men's Jeans and Shirts link",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
            Assert.fail();
        }


    }

    public void verifyUrlchanges() throws IOException{
        try{
            locateElement(menLink);
            findElement(menLink);
            click(menLink);
            implicitWait(10);
            String url1= driver.getCurrentUrl();

            locateElement(womenLink);
            findElement(womenLink);
            click(womenLink);
            implicitWait(10);
            String url2= driver.getCurrentUrl();

            locateElement(kidsLink);
            findElement(kidsLink);
            click(kidsLink);
            //implicitWait(10);
            Thread.sleep(1000);
            String url3= driver.getCurrentUrl();
            Assert.assertTrue((!(url1.equals(url2))&&!(url2.equals(url3))&&!(url1.equals(url3))),"Urls cannot be the same");
            Reports.extentTest.log(Status.PASS,"Verify Url Changes For Men, Women and Kids",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());

        }
        catch(AssertionError e){
            Reports.extentTest.log(Status.FAIL,"Verify Url Changes For Men, Women and Kids",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
            Assert.fail();
        }
        catch(Exception e){
            Reports.extentTest.log(Status.FAIL,"Verify Url Changes For Men, Women and Kids",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
            Assert.fail();
        }

    }

    public void verifyMenu() throws IOException{

        try{
            locateElement(menLink);
            findElement(menLink);
            mousehoverMainMenu(menLink);
            implicitWait(10);
            //Thread.sleep(2000);

            locateElement(womenLink);
            findElement(womenLink);
            mousehoverMainMenu(womenLink);
            implicitWait(10);
            //Thread.sleep(2000);

            locateElement(kidsLink);
            findElement(kidsLink);
            mousehoverMainMenu(kidsLink);
            implicitWait(10);
            //Thread.sleep(2000);

            locateElement(indielink);
            findElement(indielink);
            mousehoverMainMenu(indielink);
            implicitWait(10);
            //Thread.sleep(2000);

            locateElement(kitchenlink);
            findElement(kitchenlink);
            mousehoverMainMenu(kitchenlink);
            //implicitWait(10);
            Thread.sleep(2000);

            boolean b=findElement(menLink).isDisplayed()&&
                    findElement(womenLink).isDisplayed()&&
                    findElement(kidsLink).isDisplayed()&&
                    findElement(indielink).isDisplayed()&&
                    findElement(kitchenlink).isDisplayed();
            Assert.assertTrue(b=true,"All menu items not visible");
           Reports.extentTest.log(Status.PASS,"Verify Menu For Men, Women, Kids, indie and Home & Kitchen",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
        catch(AssertionError e){
            Reports.extentTest.log(Status.FAIL,"Verify Menu For Men, Women, Kids, indie and Home & Kitchen",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
            Assert.fail();
        }
        catch (Exception e){
           Reports.extentTest.log(Status.FAIL,"Verify Menu For Men, Women, Kids, indie and Home & Kitchen",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
            Assert.fail();
        }

    }
}
