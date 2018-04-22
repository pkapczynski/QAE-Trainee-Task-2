package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.Map;

import static files.helpers.convertValueToSimpleInt;
import static files.helpers.getMinimumQuantityValue;

/**
 * @author Mateusz Stolecki
 *
 * Below consist of locators and methods used in store homepage
 *
 */

public class BasketPage {

    WebDriver driver;
    WebDriverWait wait;

    By checkoutButton = By.xpath("//a[contains(@class, 'btn btn-primary') and contains(text(), 'Checkout')]");
    By mininumOrderQuantityAlert = By.xpath("//div[contains(@class, 'alert-dismissible')]");
    By quantityInput = By.xpath("//input[contains(@name, 'quantity') and(@type='text')]");
    By refreshQuantity = By.xpath("//button[(@type='submit') and(@data-original-title='Update')]");
    By minimumOrderQuantityAlertText = By.xpath("//*[@id='checkout-cart']/div[1]");
    By basketProductsValue = By.xpath("//div[(@class='table-responsive')]//table//tbody//tr/td[last()]");

    public BasketPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    /** Method used for checking whether minimum quantity of product in the basket got triggered*/
    public void validateMinimumQuantity() {
        boolean triggeredMinimumOrderQuantity = true;
        try {
            driver.findElement(mininumOrderQuantityAlert).isDisplayed();
        } catch(NoSuchElementException e) {
            triggeredMinimumOrderQuantity = false;
        }
        if(triggeredMinimumOrderQuantity) {
            System.out.println("Minimum quantity of product is not met");
            String fullMessage = driver.findElement(minimumOrderQuantityAlertText).getText();
            System.out.println("Displayed Notification: " + fullMessage);
            changeToMinimumQuantityInput();
        }
    }

    /** Method used to set proper quantity of product when minimum quantity of product in the basket got triggered*/
    public void changeToMinimumQuantityInput() {
        System.out.println("Clearing currently inputted quantity");
        driver.findElement(quantityInput).clear();
        System.out.println("Setting quantity to meet the minimum order requirement");
        driver.findElement(quantityInput).sendKeys(getMinimumQuantityValue(driver.findElement(minimumOrderQuantityAlertText).getText()));
        driver.findElement(refreshQuantity).click();
    }
    /** Below generated collection is used for gathering data from basket and checkout steps for final price validation*/
    public static Map<String, Integer> checkoutValues = new HashMap<>();

    /** Method used to catch total price of product in the basket that will be used
     * for validation towards final price at the end of checking*/
    public void getBasketValueForValidation () {
        String totalPrice = driver.findElement(basketProductsValue).getText();
        checkoutValues.put("Basket Products Total Price",convertValueToSimpleInt(totalPrice));
    }

    /** Method used to move to from BasketPage to CheckoutPage */
    public void moveToCheckout() {
        System.out.println("Proceeding to checkout");
        driver.findElement(checkoutButton).click();
    }
}