package pages;

import configuration.AbstractComponent;
import configuration.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
        lastName.sendKeys(lastNameText);
        postalCode.sendKeys(postalCodeText);

    }

    public void confirm() {
        confirm.click();

    }

    public void returnToCart() {
        cancel.click();

    }

    public void goToCehckOutStepOne() {

        driver.get(config.getPageUrl("checkOutUrl_StepOne"));
    }

    public void goToCehckOutSteptwo() {

        driver.get(config.getPageUrl("checkOutUrl_StepTwo"));
    }

    public String getErrorMessage() {
        return errorMessage.getText();

    }

    public String getPathJson(String whichData) {

        return config.getJsonPath(whichData);
    }

    public String getPageUrl(String page) {

        return config.getPageUrl(page);
    }


    public boolean verifyProductdisplay(String productName) {

        boolean match = cartProductsName.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
        return match;
    }


    public void saveCheckoutProductsName(List<String> saveProductsName) {

        for (int i = 0; i < cartProductsName.size(); i++) {

            saveProductsName.add(cartProductsName.get(i).getText());
        }

    }




    public Double getItemPriceByName(String itemName) {
        for (WebElement item : items) {
            WebElement nameElement = item.findElement(By.className("inventory_item_name"));
            if (nameElement.getText().equalsIgnoreCase(itemName)) {
                WebElement priceElement = item.findElement(By.className("inventory_item_price"));
                return Double.parseDouble(priceElement.getText().replace("$", ""));
            }
        }
        throw new RuntimeException("Product not found: " + itemName);
    }



    public Double getSubTotalPrice() {

        String subTotal = subTotalPrice.getText();
        String numeric = subTotal.replace("Item total: $", "").trim();
        return Double.parseDouble(numeric);


    }

    public Double getTax() {

        String Tax = tax.getText();
        String numeric = Tax.replace("Tax: $", "").trim();
        return Double.parseDouble(numeric);


    }


    public Double getTotalPrice() {

        String total = totalPrice.getText();
        String numeric = total.replace("Total: $", "").trim();
        return Double.parseDouble(numeric);


    }
    public void finish(){
        finish.click();
    }
    public String getConfirmationMessage(){

      return  confirmationMessage.getText();
    }

}


