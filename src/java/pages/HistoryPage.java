package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Mateusz Stolecki
 *
 * Below consist of locators and methods used in historical orders page
 *
 */
public class HistoryPage {

    WebDriver driver;
    WebDriverWait wait;

    public HistoryPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    By latestOrder = By.xpath("//a[@data-toggle='tooltip'][1]");

    /** Method used to proceed to newest order placed withing concerned account*/
    public void goToNewestOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(latestOrder));
        driver.findElement(latestOrder).click();
    }
}
