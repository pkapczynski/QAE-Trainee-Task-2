package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Mateusz Stolecki
 *
 * Below consist of locators and methods used in Success Order Page
 *
 */
public class PlacedOrderPage {
    WebDriver driver;
    WebDriverWait wait;

    public PlacedOrderPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    By successElement = By.xpath("//div[(@id='common-success')]//div[@class='row']/div[@id='content']/h1");
    By orderHistoryLink = By.xpath("//a[contains(text(), 'history')]");

    /** Method checks whether success order text is displayed at the page*/
    public void confirmCorrectSuccessTextIsDisplayed(String successExpectedText) {
        wait.until(ExpectedConditions.elementToBeClickable(successElement));

        if(successExpectedText.equals(driver.findElement(successElement).getText())){
            System.out.println("Validation Successful, displayed - " + driver.findElement(successElement).getText());
        }else{
            System.out.println(successExpectedText + " does not equal " + driver.findElement(successElement).getText());
        }
    }

    /** Method used to proceed to history of the orders associated with concerned account*/
    public void goToHistoryOfOrders() {
        wait.until(ExpectedConditions.elementToBeClickable(orderHistoryLink));
        driver.findElement(orderHistoryLink).click();
    }
}
