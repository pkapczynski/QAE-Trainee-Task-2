package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.Map;

import static files.helpers.convertValueToSimpleInt;
import static files.helpers.generateRandomString;
import static pages.BasketPage.checkoutValues;

/**
 * @author Mateusz Stolecki
 *
 * Below consist of locators and methods used in store homepage
 *
 */
public class CheckoutPage {

    WebDriver driver;
    WebDriverWait wait;

    public CheckoutPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    /**
     Step 1 - Checkout Options - WebElements
     */
    By registerAccountRadio = By.xpath("//input[(@type='radio') and(@value='register')]");
    By guestAccountRadio = By.xpath("//input[(@type='radio') and(@value='guest')]");
    By firstStepContinueButton = By.xpath("//input[(@type='button') and(@value='Continue') and(@id='button-account')]");
    By loginButton = By.xpath("//input[(@type='button') and(@value='Login')]");
    By emailInputField = By.xpath("//input[(@type='text') and(@name='email')]");
    By passwordInputField = By.xpath("//input[(@type='password') and(@name='password')]");

    /**
     Step 1 - Checkout Options - Methods
     */


    /** Below method is not used but I decided to not delete it for further testcases*/
    /** Method used to continuing through the checkout as a Guest*/
    public void continueAsAGuest() {
        wait.until(ExpectedConditions.elementToBeClickable(guestAccountRadio));
        boolean guestSelected = driver.findElement(guestAccountRadio).isSelected();
        if(guestSelected){
            System.out.println("Guest option is selected");
        }else{
            driver.findElement(guestAccountRadio).click();
        }
        driver.findElement(firstStepContinueButton).click();
    }

    /** Below method is not used but I decided to not delete it for further testcases*/
    /** Method used to continuing through the checkout with new account creation*/
    public void continueWithNewAccount() {
        wait.until(ExpectedConditions.elementToBeClickable(registerAccountRadio));
        boolean registerAccountSelected = driver.findElement(registerAccountRadio).isSelected();
        if(registerAccountSelected){
            System.out.println("Register new Account option is selected");
        }else{
            driver.findElement(registerAccountRadio).click();
        }
        driver.findElement(firstStepContinueButton).click();
    }

    /** Below boolean was prepared in case of logging in as a Guest/New Customer to properly fill the delivery
     *  and payment method sections as their structure vary on these scenarios*/
    boolean notReturningCustomerOption = true;

    /** Method used to log in as returning customer*/
    public void logAsReturningCustomer(String emailAddress, String password) {
        wait.until(ExpectedConditions.elementToBeClickable(emailInputField));
        System.out.println("Entering login credentials");
        driver.findElement(emailInputField).sendKeys(emailAddress);
        driver.findElement(passwordInputField).sendKeys(password);
        System.out.println("Logging in as returning customer");
        driver.findElement(loginButton).click();
        notReturningCustomerOption = false;
    }

    /**
     Step 2 - Billing Details - WebElements
     */
    By billingFirstName = By.xpath("//input[(@name='firstname') and contains(@id, 'input-payment')]");
    By billingLastName = By.xpath("//input[(@name='lastname') and contains(@id, 'input-payment')]");
    By billingEmail = By.xpath("//input[(@name='email') and contains(@id, 'input-payment')]");
    By billingTelephone = By.xpath("//input[(@name='telephone') and contains(@id, 'input-payment')]");
    By billingAddress = By.xpath("//input[(@name='address_1') and contains(@id, 'input-payment')]");
    By billingCity = By.xpath("//input[(@name='city') and contains(@id, 'input-payment')]");
    By billingPostCode = By.xpath("//input[(@name='postcode') and contains(@id, 'input-payment')]");
    By billingCountry = By.xpath("//select[(@name='country_id') and contains(@id, 'input-payment')]");
    By billingRegionState = By.xpath("//select[(@name='zone_id') and contains(@id, 'input-payment')]");
    By billingGuestContinueCheckbox = By.xpath("//input[(@type='button') and(@value='Continue') and(@id='button-guest')]");
    By billingAccountContinueCheckbox = By.xpath("//input[(@type='button') and(@value='Continue') and(@id='button-payment-address')]");
    By deliveryAddressSameAsBilling = By.xpath("//input[(@type='checkbox') and (@name='shipping_address')]");
    By billingUseNewAddress = By.xpath("//input[(@type='radio') and(@value='new') and(@name='payment_address')]");

    /**
     Step 2 - Billing Details - Methods
     */
    public void setNewBillingAddressOption() {
        wait.until(ExpectedConditions.elementToBeClickable(billingUseNewAddress));
        driver.findElement(billingUseNewAddress).click();
    }
    /** Below method is not used but I decided to not delete it for further testcases*/
    /** Method used for selecting deliver address and data same as billing ones*/
    public void setDeliveryAddressSameAsBilling() {
        boolean addressSameAsBillingSelected = driver.findElement(deliveryAddressSameAsBilling).isSelected();
        if(addressSameAsBillingSelected){
            System.out.println("'My delivery and billing addresses are the same' was pre-selected");
        }else{
            driver.findElement(deliveryAddressSameAsBilling).click();
        }
        driver.findElement(billingGuestContinueCheckbox).click();
    }

    /** Method used to fill the billing data. It has validation towards type of chosen checkout method*/
    public void fillTheBillingData(String bilFirNam, String bilLasNam, String bilEma, String bilTel, String bilAddr,
                                   String bilCit, String bilPosCod, String bilCou, String bilRegSta) {
        wait.until(ExpectedConditions.elementToBeClickable(billingAccountContinueCheckbox));
        System.out.println("Filling the Billing Data");
        driver.findElement(billingFirstName).sendKeys(bilFirNam);
        driver.findElement(billingLastName).sendKeys(bilLasNam);
        if(notReturningCustomerOption) {
            driver.findElement(billingEmail).sendKeys(bilEma);
            driver.findElement(billingTelephone).sendKeys(bilTel);
        }
        driver.findElement(billingAddress).sendKeys(bilAddr);
        driver.findElement(billingCity).sendKeys(bilCit);
        driver.findElement(billingPostCode).sendKeys(bilPosCod);
        Select country = new Select(driver.findElement(billingCountry));
        country.selectByVisibleText(bilCou);
        Select regionState = new Select(driver.findElement(billingRegionState));
        regionState.selectByVisibleText(bilRegSta);
    }

    /** Method used to proceed to Delivery Address input fields of the checkout*/
    public void proceedToDeliveryAddress() {
        System.out.println("Proceeding to Delivery Address");
        driver.findElement(billingAccountContinueCheckbox).click();
    }

    /**
     Step 3 - Delivery Details - WebElements
     */
    By deliveryFirstName = By.xpath("//input[(@name='firstname') and contains(@id, 'input-shipping')]");
    By deliveryLastName = By.xpath("//input[(@name='lastname') and contains(@id, 'input-shipping')]");
    By deliveryEmail = By.xpath("//input[(@name='email') and contains(@id, 'input-shipping')]");
    By deliveryTelephone = By.xpath("//input[(@name='telephone') and contains(@id, 'input-shipping')]");
    By deliveryAddress = By.xpath("//input[(@name='address_1') and contains(@id, 'input-shipping')]");
    By deliveryCity = By.xpath("//input[(@name='city') and contains(@id, 'input-shipping')]");
    By deliveryPostCode = By.xpath("//input[(@name='postcode') and contains(@id, 'input-shipping')]");
    By deliveryCountry = By.xpath("//select[(@name='country_id') and contains(@id, 'input-shipping')]");
    By deliveryRegionState = By.xpath("//select[(@name='zone_id') and contains(@id, 'input-shipping')]");
    By deliveryGuestContinueCheckbox = By.xpath("//input[(@type='button') and(@value='Continue') and(@id='button-guest')]");
    By deliveryAccountContinueCheckbox = By.xpath("//input[(@type='button') and(@value='Continue') and(@id='button-shipping-address')]");
    By deliveryUseNewAddress = By.xpath("//input[(@type='radio') and(@value='new') and(@name='shipping_address')]");

    /**
     Step 3 - Delivery Details - Methods
     */
    /** Method used to trigger delivery option input fields. By default this field is selected to use
     * the historical address associated with used account*/
    public void setNewDeliveryAddressOption() {
        wait.until(ExpectedConditions.elementToBeClickable(deliveryUseNewAddress));
        driver.findElement(deliveryUseNewAddress).click();
    }

    /** Method used to fill the delivery data. It has validation towards type of chosen checkout method*/
    public void fillDeliveryData(String delFirNam, String delLasNam, String delEma, String delTel, String delAddr,
                                   String delCit, String delPosCod, String delCou, String delRegSta) {
        wait.until(ExpectedConditions.elementToBeClickable(deliveryAccountContinueCheckbox));
        System.out.println("Filling the Delivery Data");
        driver.findElement(deliveryFirstName).sendKeys(delFirNam);
        driver.findElement(deliveryLastName).sendKeys(delLasNam);
        if(notReturningCustomerOption) {
            driver.findElement(deliveryEmail).sendKeys(delEma);
            driver.findElement(deliveryTelephone).sendKeys(delTel);
        }
        Select country = new Select(driver.findElement(deliveryCountry));
        country.selectByVisibleText(delCou);
        driver.findElement(deliveryAddress).sendKeys(delAddr);
        driver.findElement(deliveryCity).sendKeys(delCit);
        driver.findElement(deliveryPostCode).sendKeys(delPosCod);
        Select regionState = new Select(driver.findElement(deliveryRegionState));
        regionState.selectByVisibleText(delRegSta);
    }

    /** Method sed to proceed to Delivery Method step of the checkout*/
    public void proceedToDeliveryMethod() {
        System.out.println("Proceeding to Delivery Method");
        driver.findElement(deliveryAccountContinueCheckbox).click();
    }

    /**
     Step 4 - Delivery Method - WebElements
     */
    By firstDeliveryMethod = By.xpath("//input[(@type='radio') and (@name='shipping_method')]");
    By deliveryMethodComment = By.xpath("//div[@id='collapse-shipping-method']//p//textarea[@name='comment']");
    By deliveryMethodContinueButton = By.xpath("//input[(@type='button') and(@value='Continue') and(@id='button-shipping-method')]");
    By firstDeliveryMethodName = By.xpath("//div[@id='collapse-shipping-method']//div[@class='panel-body']//div[@class='radio']//label");

    /**
     Step 4 - Delivery Method - Methods
     */
    /** Method used to set desired delivery method. In current version it is always using the first delivery method available*/
    public void setDeliveryMethod() {
        wait.until(ExpectedConditions.elementToBeClickable(firstDeliveryMethod));
        boolean flatRateSelected = driver.findElement(firstDeliveryMethod).isSelected();
        if(flatRateSelected){
            System.out.println(driver.findElement(firstDeliveryMethodName).getText().trim() + " delivery method was pre selected");
        }else {
            System.out.println("Selecting first delivery method - " + driver.findElement(firstDeliveryMethodName).getText());
            driver.findElement(firstDeliveryMethod).click();
        }
    }

    /** Method used to catch delivery price in the delivery method step that will be used
     * for validation towards final price at the end of checking*/
    public void getDeliveryValueForValidation () {
        String totalPrice = driver.findElement(firstDeliveryMethodName).getText();
        checkoutValues.put("Deliver Method Price",convertValueToSimpleInt(totalPrice));
    }

    /** Method for writing custom comment with randomly generated String and storing it in collection
     * for further validation at the LatestOrderPage*/
    public void writeCustomDeliveryComment() {
        System.out.println("Writing Randomly Generated Delivery Comment");
        String randomComment = generateRandomString();
        int randomCommentAsInt = Integer.parseInt(randomComment);
        checkoutValues.put("Random Comment", randomCommentAsInt);
        driver.findElement(deliveryMethodComment).sendKeys(randomComment);
    }

    /** Method used to proceed to Payment Methods at the checkout*/
    public void moveToPaymentMethod() {
        System.out.println("Proceeding to Payment Method");
        driver.findElement(deliveryMethodContinueButton).click();
    }

    /**
     Step 5 - Payment Method - WebElements
     */
    By firstPaymentMethod = By.xpath("//input[(@type='radio') and (@name='payment_method')]");
    By paymentMethodComment = By.xpath("//div[@id='collapse-payment-method']//p//textarea[@name='comment']");
    By termsAndConditionsAgreement = By.xpath("//input[(@type='checkbox') and (@name='agree')]");
    By paymentMethodContinueButton = By.xpath("//input[(@type='button') and(@value='Continue') and(@id='button-payment-method')]");
    By firstPaymentMethodName = By.xpath("//div[@id='collapse-payment-method']//div[@class='panel-body']//div[@class='radio']//label");

    /**
     Step 5 - Payment Method - Methods
     */
    /** Method used to set desired payment method. In current version it is always using the first delivery method available*/
    public void setPaymentMethod() {
        wait.until(ExpectedConditions.elementToBeClickable(firstPaymentMethod));
        boolean flatRateSelected = driver.findElement(firstPaymentMethod).isSelected();
        if(flatRateSelected){
            System.out.println(driver.findElement(firstPaymentMethodName).getText() + " payment method was pre selected");
        }else {
            System.out.println("Selecting first delivery method - " + driver.findElement(firstPaymentMethodName).getText());
            driver.findElement(firstPaymentMethodName).click();
        }
    }

    /** Method used to agree with Terms and Coditions at the checkout*/
    public void agreeWithTermsAndConditions() {
        boolean termsAndConditionsSelected = driver.findElement(termsAndConditionsAgreement).isSelected();
        if(termsAndConditionsSelected){
            System.out.println("Terms and conditions agreement was pre selected");
        }else{
            driver.findElement(termsAndConditionsAgreement).click();
        }
    }

    /** Method used to proceed to final order placement step of the checkout*/
    public void moveToConfirmationOrderStep() {
        driver.findElement(paymentMethodContinueButton).click();
    }

    /**
     Step 6 - Confirm Order - WebElements
     */
    By confirmOrderButton = By.xpath("//input[(@type='button') and(@value='Confirm Order') and(@id='button-confirm')]");
    By finalTotalPrice = By.xpath("//div[(@class='table-responsive')]//table//tfoot//tr[last()]/td[last()]");


    /**
     Step 6 - Confirm Order - Methods
     */
    /** Below generated collection is used for gathering data from table just before final placement of the order*/
    public static Map<String, Integer> finalPrice = new HashMap<>();

    /** Method used to store final price in previously generated collection in order to validate it*/
    public void getFinalPriceForValidation() {
        wait.until(ExpectedConditions.elementToBeClickable(confirmOrderButton));
        String finalTotal = driver.findElement(finalTotalPrice).getText();
        finalPrice.put("Final price from last checkout step",convertValueToSimpleInt(finalTotal));
    }

    /** Method used to compare gathered data through the basket and checkout views with the final total price at checkout*/
    public void compareGatheredData() {
        int sumOfIndividualPrices = checkoutValues.get("Basket Products Total Price") +
                checkoutValues.get("Deliver Method Price");
        int valueAtEndOfCheckout = finalPrice.get("Final price from last checkout step");

        if(sumOfIndividualPrices == valueAtEndOfCheckout){
            System.out.println("Values Conform");
        }else{
            System.out.println("Validation failed");
        }
    }

    /** Method to finally place the order. Using this method transition the flow to PlacedOrderPage*/
    public void confirmTheOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(confirmOrderButton));
        driver.findElement(confirmOrderButton).click();
    }
}
