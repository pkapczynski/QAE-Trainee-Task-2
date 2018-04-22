package testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class OrderProcessTest {

    String currentDir = System.getProperty("user.dir");
    String chromeDriverLocation
            = currentDir +
            "/chromedriver.exe";
    Properties prop = new Properties();
    WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, 10);

    @BeforeTest
    public void prepareForOrderPlacement() throws IOException {
        System.setProperty("webdriver.chrome.driver", chromeDriverLocation);
        FileInputStream fis = new FileInputStream(currentDir + "\\src\\java\\files\\data.properties");
        prop.load(fis);

        driver.manage().window().maximize();
        driver.get(prop.getProperty("TESTSTORE"));

        HomePage home = new HomePage(driver, wait);
        home.addToCart();
        home.moveToBasketPage();
    }

    @Test
    public void placeTheOrder() {
        BasketPage basket = new BasketPage(driver, wait);
        basket.validateMinimumQuantity();
        basket.getBasketValueForValidation();
        basket.moveToCheckout();

        CheckoutPage checkout = new CheckoutPage(driver, wait);
        checkout.logAsReturningCustomer(prop.getProperty("EMAIL"), prop.getProperty("PASSWORD"));
        checkout.setNewBillingAddressOption();
        checkout.fillTheBillingData(
                prop.getProperty("BILFIRSTNAME"),prop.getProperty("BILLASTNAME"), prop.getProperty("BILEMAIL"),
                prop.getProperty("BILTELEPHONE"),prop.getProperty("BILADDRESS"), prop.getProperty("BILCITY"),
                prop.getProperty("BILPOSTCODE"),prop.getProperty("BILCOUNTRY"), prop.getProperty("BILREGIONSTATE"));
        checkout.proceedToDeliveryAddress();
        checkout.setNewDeliveryAddressOption();
        checkout.fillDeliveryData(
                prop.getProperty("DELIFIRSTNAME"),prop.getProperty("DELILASTNAME"), prop.getProperty("DELIEMAIL"),
                prop.getProperty("DELITELEPHONE"),prop.getProperty("DELIADDRESS"), prop.getProperty("DELICITY"),
                prop.getProperty("DELIPOSTCODE"),prop.getProperty("DELICOUNTRY"), prop.getProperty("DELIREGIONSTATE"));
        checkout.proceedToDeliveryMethod();
        checkout.setDeliveryMethod();
        checkout.getDeliveryValueForValidation();
        checkout.writeCustomDeliveryComment();
        checkout.moveToPaymentMethod();
        checkout.setPaymentMethod();
        checkout.agreeWithTermsAndConditions();
        checkout.moveToConfirmationOrderStep();
        checkout.getFinalPriceForValidation();
        checkout.compareGatheredData();
        checkout.confirmTheOrder();

        PlacedOrderPage placed = new PlacedOrderPage(driver, wait);
        placed.confirmCorrectSuccessTextIsDisplayed(prop.getProperty("SUCCESSTEXT"));
        placed.goToHistoryOfOrders();

        HistoryPage history = new HistoryPage(driver, wait);
        history.goToNewestOrder();

        LatestOrderPage latest = new LatestOrderPage(driver, wait);
        latest.checkIfNumberIsDisplayed();
    }

    @AfterTest
    public void finalizeTheTest() {
        driver.manage().deleteAllCookies();
        driver.close();
        driver.quit();
    }
}
