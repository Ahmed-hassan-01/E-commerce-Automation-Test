package pages;

import configuration.AbstractComponent;
import configuration.Config;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.LogsUtil;

import java.time.Duration;

public class P01_LoginPage extends AbstractComponent {

    Config config = new Config();
    WebDriver driver;

    public P01_LoginPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);

    }


   @FindBy(id = "user-name")
    WebElement userName;

    @FindBy(id = "password")
    WebElement password;

    @FindBy(id ="login-button")
    WebElement submit;

    @FindBy(css = "h3[data-test='error']")
    WebElement errorMessage;
   // WebElement nm =driver.findElement(By.xpath("//input[@placeholder='Username']"));
    public void login(String usernameText,String passwordText){

        userName.sendKeys(usernameText);
        password.sendKeys(passwordText);



    }
    public void submit(){
        submit.click();

    }

    public String  getErrorMessage (){
       return errorMessage.getText();
    }

    public void goTo(){

        driver.get(config.getPageUrl("login_url"));
        LogsUtil.info("go to page login url ",config.getPageUrl("login_url"));
    }

    public String getPathJson(String whichData){

        LogsUtil.info("getting json path for ",whichData);

        return config.getJsonPath(whichData);

    }


}
