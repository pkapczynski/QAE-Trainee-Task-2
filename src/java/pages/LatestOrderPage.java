package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static pages.BasketPage.checkoutValues;

/**
 * @author Mateusz Stolecki
 *
 * Below consist of locators and methods used in latest order view
 *
 */
public class LatestOrderPage {

    WebDriver driver;
    WebDriverWait wait;

    public LatestOrderPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    /** Locators that is using previously generated random number for validation */
    By orderCommentTextForValidation = By.xpath("//td[contains(text(), '"+ checkoutValues.get("Random Comment")  +"')]");

    /** Method used to see whether element with previously generated random number is preset at the current view*/
    public void checkIfNumberIsDisplayed() {
        try{
            driver.findElement(orderCommentTextForValidation).isDisplayed();
            System.out.println("Previously generated random number is displayed in latest order");
        }catch(NoSuchElementException e){
            System.out.println("ERROR - Previously generated random number is not in latest order");
        }
    }
}
