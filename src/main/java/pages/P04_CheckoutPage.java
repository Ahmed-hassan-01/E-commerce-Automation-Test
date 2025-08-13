package pages;

import configuration.AbstractComponent;
import configuration.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.LogsUtil;

import java.util.List;

public class P04_CheckoutPage extends AbstractComponent {

    WebDriver driver;
    Config config = new Config();

    public P04_CheckoutPage(WebDriver driver) {

        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    @FindBy(id = "first-name")
    WebElement firstName;

    @FindBy(id = "last-name")
    WebElement lastName;

    @FindBy(id = "postal-code")
    WebElement postalCode;

    @FindBy(css = ".cart_cancel_link.btn_secondary")
    WebElement cancel;

    @FindBy(css = "input[value='CONTINUE']")
    WebElement confirm;

    @FindBy(css = "h3[data-test='error']")
    WebElement errorMessage;

    @FindBy(className = "cart_item")
    List<WebElement> items;
    @FindBy(className = "inventory_item_name")
    List<WebElement> cartProductsName;

    @FindBy(className = "inventory_item_price")
    List<WebElement> itemPrice;

    @FindBy(className = "summary_subtotal_label")
    WebElement subTotalPrice;

    @FindBy(className = "summary_tax_label")
    WebElement tax;

    @FindBy(className = "summary_total_label")
    WebElement totalPrice;

    @FindBy(css = ".btn_action.cart_button")
    WebElement finish;

    @FindBy(css = "h2.complete-header")
    WebElement confirmationMessage;


    public void addInfo(String firstNameText, String lastNameText, String postalCodeText) {
        firstName.sendKeys(firstNameText);
        LogsUtil.info("send first name :", firstNameText);
        lastName.sendKeys(lastNameText);
        LogsUtil.info("send last name :", lastNameText);
        postalCode.sendKeys(postalCodeText);
        LogsUtil.info("send postal code :", postalCodeText);

    }

    public void confirm() {
        confirm.click();
        LogsUtil.info("clicked confirm");

    }

    public void returnToCart() {
        cancel.click();
        LogsUtil.info("clicked cancel");

    }

    public void goToCehckOutStepOne() {

        driver.get(config.getPageUrl("checkOutUrl_StepOne"));
        LogsUtil.info("go to check OutUrl StepOne", config.getPageUrl("checkOutUrl_StepOne"));

    }

    public void goToCehckOutSteptwo() {

        driver.get(config.getPageUrl("checkOutUrl_StepTwo"));
        LogsUtil.info("go to check OutUrl Step Two", config.getPageUrl("checkOutUrl_StepTwo"));

    }

    public String getErrorMessage() {
        LogsUtil.info("getting error message: ", errorMessage.getText());
        return errorMessage.getText();

    }

    public String getPathJson(String whichData) {
        LogsUtil.info("getting json path ", whichData);
        return config.getJsonPath(whichData);
    }

    public String getPageUrl(String page) {
        LogsUtil.info("get page url :", config.getPageUrl(page));
        return config.getPageUrl(page);
    }


    public boolean verifyProductdisplay(String productName) {

        boolean match = cartProductsName.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
        LogsUtil.info("verify product display: ");
        return match;
    }


    public void saveCheckoutProductsName(List<String> saveProductsName) {

        for (int i = 0; i < cartProductsName.size(); i++) {
            LogsUtil.info("save product name: ", cartProductsName.get(i).getText(), "to list arrray ", saveProductsName.toString());
            saveProductsName.add(cartProductsName.get(i).getText());
        }

    }


    public Double getItemPriceByName(String itemName) {
        for (WebElement item : items) {
            WebElement nameElement = item.findElement(By.className("inventory_item_name"));
            if (nameElement.getText().equalsIgnoreCase(itemName)) {
                WebElement priceElement = item.findElement(By.className("inventory_item_price"));
                LogsUtil.info("getting inventory item price", priceElement.getText());
                return Double.parseDouble(priceElement.getText().replace("$", ""));
            }
        }
        throw new RuntimeException("Product not found: " + itemName);
    }


    public Double getSubTotalPrice() {

        String subTotal = subTotalPrice.getText();
        LogsUtil.info("getting sub tootal price", subTotal);
        String numeric = subTotal.replace("Item total: $", "").trim();
        return Double.parseDouble(numeric);


    }

    public Double getTax() {

        String Tax = tax.getText();
        LogsUtil.info("getting tax ", Tax);
        String numeric = Tax.replace("Tax: $", "").trim();
        return Double.parseDouble(numeric);


    }


    public Double getTotalPrice() {

        String total = totalPrice.getText();
        LogsUtil.info("getting total price", total);
        String numeric = total.replace("Total: $", "").trim();
        return Double.parseDouble(numeric);


    }

    public void finish() {
        finish.click();
        LogsUtil.info("click finish");
    }

    public String getConfirmationMessage() {
        LogsUtil.info("get confirmation message : ", confirmationMessage.getText());
        return confirmationMessage.getText();
    }

}


