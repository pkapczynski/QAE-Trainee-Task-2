package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Mateusz Stolecki
 *
 * Below consist of locators and methods used in store homepage
 *
 */

public class HomePage {

    WebDriver driver;
    WebDriverWait wait;

    By firstFeaturedProduct = By.xpath("//span[contains(text(), 'Add to Cart')]");
    By addToCartAlert = By.xpath("//div[@class='alert alert-success alert-dismissible']");
    By toBasketTransition = By.xpath("//a[contains(text(), 'shopping cart')]");

    public HomePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    /** Method used to add the first diplayed featured product to the basket.
     * This would have to be remastered once client decides to use banners/category links at this place*/
    public void addToCart () {
        System.out.println("Adding first displayed featured product to the basket");
        driver.findElement(firstFeaturedProduct).click();
    }

    /** Method used to proceed to basket once product have been added to it at the homepage view*/
    public void moveToBasketPage() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartAlert));
        wait.until(ExpectedConditions.elementToBeClickable(toBasketTransition));
        System.out.println("Proceeding to the basket");
        driver.findElement(toBasketTransition).click();
    }
}

