package configuration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponent {

    WebDriver driver;
    public AbstractComponent(WebDriver driver){

        this.driver = driver;
        PageFactory.initElements(driver,this);

    }

    @FindBy(xpath = "//div[@class='shopping_cart_container']")
    WebElement cartHeader;

public void waitForElementToAppear(By findby){

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
    wait.until(ExpectedConditions.visibilityOfElementLocated(findby));

}
    public void wait(int sec) throws InterruptedException {
    Thread.sleep(sec*1000);
    }

public void goToCart(){

    cartHeader.click();

}
}
