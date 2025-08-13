package pages;

import configuration.AbstractComponent;
import configuration.Config;
import lombok.extern.java.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.LogsUtil;

import java.util.List;

public class P03_CartPage extends AbstractComponent {

    WebDriver driver;
    Config config = new Config();

    public P03_CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    @FindBy(xpath = "//div[@class='shopping_cart_container']")
    WebElement cart;
    By cartt = By.xpath("//div[@class='shopping_cart_container']");

    @FindBy(className = "cart_item")
    List<WebElement> items;

    @FindBy(className = "inventory_item_name")
    List<WebElement> cartProductsName;

    @FindBy(xpath = "//div[@class='item_pricebar']/button")
    List<WebElement> cartremoveButtons;

    By remove = By.xpath("//div[@class='item_pricebar']/button");

    @FindBy(css = ".btn_action.checkout_button")
    WebElement checkout;

    @FindBy(css = "a[class='btn_secondary']")
    WebElement continueShopping;

    public void goTo() {
        waitForElementToAppear(cartt);
        cart.click();

    }

    public boolean verifyProductdisplay(String productName) {

        boolean match = cartProductsName.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
        return match;
    }

    public void removeCartProduct(String productName) {
        WebElement prod = cartProductsName.stream().filter(cartProduct -> cartProduct.getText().equals(productName)).findFirst().orElse(null);
        if(prod!=null)
        {
            prod.findElement(remove).click();
            LogsUtil.info("clicked on remove button");
        }
        else {
            LogsUtil.info("cant click on remove button becase there is no products in cart");

        }

    }

    public void removeAllCartProduct() {
        for (WebElement prod : cartProductsName) {
            prod.findElement(remove).click();

        }
        LogsUtil.info("removed all product from cart");
    }


    public void continueShopping() {

        continueShopping.click();
        LogsUtil.info("get back to product page ");

    }

    public void Checkout() {

        checkout.click();

    }


    public String getPathJson(String productsData) {
        LogsUtil.info("getting Json Path for productData");
        return config.getJsonPath(productsData);
    }

    public String getPageUrl(String page) {

        return config.getPageUrl(page);
    }


    public void saveCartProductsName(List<String> saveProductsName) {

        for (int i = 0; i < cartProductsName.size(); i++) {

            LogsUtil.info("save product name : ", cartProductsName.get(i).getText(), "to list array :", saveProductsName.toString());
            saveProductsName.add(cartProductsName.get(i).getText());
        }

    }


}
