package pages;

import configuration.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class P05_ConfirmationPage extends AbstractComponent {

WebDriver driver;

    public P05_ConfirmationPage(WebDriver driver) {
        super(driver);

        this.driver=driver;
        PageFactory.initElements(driver,this);

    }
}







