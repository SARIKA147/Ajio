import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.io.IOException;
@Listeners(AjioListeners.class)
public class Ajio {
    private WebDriver driver;

    @BeforeMethod
    @Parameters("url")
    public void openBrowser(String url){
        driver = Browser.openBrowser(url);
    }

    //Scenario 1:Search Product
    @Test(dataProvider = "ajio-data-provider",dataProviderClass = AjioDataProvider.class)
    public void SearchItemPresent(String item) throws IOException {

        Reports.createTest("Search Available Item");
        HomePage homePage=new HomePage(driver);
        homePage.searchItem(item);
    }

    //Scenario 2:Search Unavailable Product
    @Test(dataProvider = "ajio-data-provider",dataProviderClass = AjioDataProvider.class)
    public void SearchItemNotPresent(String item) throws IOException {
        Reports.createTest("Search Unavailable Present");
        HomePage homePage=new HomePage(driver);
        homePage.searchUnavailableItem(item);
    }

    //Scenario 3: Add to Bag
    @Test(dataProvider = "ajio-data-provider",dataProviderClass = AjioDataProvider.class)
    public void addItemToBag(String item) throws IOException {
        Reports.createTest("Add To Bag");
        HomePage homePage=new HomePage(driver);
        homePage.addItemToBag(item);
    }

    //Scenario 4: Verify Menu
    @Test
    public void verifyMenu() throws IOException{
        Reports.createTest("Verify Menu");
        HomePage homePage=new HomePage(driver);
        homePage.verifyMenu();
    }

    //Scenario 5: Sort By Price-Lowest
    @Test(dataProvider = "ajio-data-provider",dataProviderClass = AjioDataProvider.class)
    public void sortByPriceLowest(String item) throws IOException{
        Reports.createTest("Sort By Lowest Price");
        HomePage homePage=new HomePage(driver);
        homePage.filterPriceLowest(item);
    }

    //Scenario 6: Sort By Price Highest
    @Test(dataProvider = "ajio-data-provider",dataProviderClass = AjioDataProvider.class)
    public void sortByPriceHighest(String item) throws IOException {
        Reports.createTest("Sort By Highest Price");
        HomePage homePage=new HomePage(driver);
        homePage.filterPriceHighest(item);
    }
    //Scenario 7: Verify Menu items for Men
    @Test
    public void verifyMenuItemForMen() throws IOException{
        Reports.createTest("Verify Menu Item ForMen");
        HomePage homePage=new HomePage(driver);
        homePage.verifyMenuItemForMen();
    }

    //Scenario 8: Proceed to Shipping
    @Test(dataProvider = "ajio-data-provider",dataProviderClass = AjioDataProvider.class)
    public void proceedToShiping(String item) throws IOException {
        Reports.createTest("Proceed to Shipping");
        HomePage homePage=new HomePage(driver);
        homePage.proceedShipping(item);
    }

    //Scenario 9: Verify Footer
    @Test
    public void verifyFooter() throws IOException {
        Reports.createTest("verify Footer");
        HomePage homePage=new HomePage(driver);
        homePage.verifyFooter();
    }

    //Scenario 10: Verify Return Policy
    @Test(dataProvider = "ajio-data-provider",dataProviderClass = AjioDataProvider.class)
    public void verifyReturnPolicy(String item) throws IOException {

        Reports.createTest("Verify Return Policy");
        HomePage homePage=new HomePage(driver);
        homePage.verifyReturnpolicy(item);
    }

    //Scenario 11:Verify FAQ in Customer Care
    @Test
    public void verifyFAQInCustomerCare() throws IOException {
        Reports.createTest("verify FAQ In CustomerCare");
        HomePage homePage=new HomePage(driver);
        homePage.verifyFaq();
    }

    //Scenario 12: Filter Items
    @Test(dataProvider = "ajio-data-provider",dataProviderClass = AjioDataProvider.class)
    public void filterByGender(String item) throws IOException {
        Reports.createTest("Filter By Gender");
        HomePage homePage=new HomePage(driver);
        homePage.filterItemBasedOnGender(item);
    }

    //Scenario 13: Search Brand
    @Test(dataProvider = "ajio-data-provider",dataProviderClass = AjioDataProvider.class)
    public void searchBrand(String item) throws IOException {
        Reports.createTest("Search By Brand");
        HomePage homePage=new HomePage(driver);
        homePage.searchBrand(item);
    }

    //Scenario 14: Filter By Price-Min and Max
    @Test(dataProvider = "ajio-data-provider",dataProviderClass = AjioDataProvider.class)
    public void filterByPriceMinAndMax(String item) throws IOException {
        Reports.createTest("Filter By Min And Max Price");
        HomePage homePage=new HomePage(driver);

        homePage.PriceMinAndMax(item);
    }

    //Scenario 15: Verify URL changes
    @Test
    public void verifyUrlchanges() throws IOException {
        Reports.createTest("Verify Url Changes");
        HomePage homePage=new HomePage(driver);
        homePage.verifyUrlchanges();
    }

    @AfterMethod
    public void closeBrowser(){


        Browser.closeBrowser(driver);
    }
}
