package pages;

import com.google.common.annotations.VisibleForTesting;
import configuration.AbstractComponent;
import configuration.Config;
import lombok.extern.java.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utils.LogsUtil;

import java.util.List;

public class P02_ProductsPage extends AbstractComponent {

    WebDriver driver;
    Config config = new Config();

    public P02_ProductsPage(WebDriver driver) {
        super(driver);

        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    public void goTo() {

        driver.get(config.getPageUrl("products_url"));
    }

    @FindBy(xpath = "//select[@class='product_sort_container']")
    WebElement staticDropdown;
    By dropdownBy = By.xpath("//select[@class='product_sort_container']");


    //get all products  in list
    @FindBy(css = "div.inventory_item")
    List<WebElement> products;
    By productsBy = By.cssSelector("div.inventory_item");


    //get all products name in list
    @FindBy(css = "div.inventory_item_name")
    List<WebElement> productsName;
    By productNameBy = By.cssSelector("div.inventory_item_name");

    //get all add buttons
    @FindBy(xpath = "//div[@class='pricebar']/button")
    List<WebElement> addButton;
    By addBy = By.xpath(".//div[@class='pricebar']/button");

    @FindBy(xpath = "//div[@class='inventory_item_label']/a")
    WebElement productlink;

    @FindBy(className = "inventory_item_price")
    List<WebElement> productsPrice;
    By productpriceBy = By.className("inventory_item_price");

    @FindBy(css = ".btn_secondary.btn_inventory")
    List<WebElement> removeproduct;
    By removeButtonBy = By.cssSelector(".btn_secondary.btn_inventory");

    @FindBy(css = ".btn_primary.btn_inventory")
    List<WebElement> addbutton;
    By addbuttonBy = By.cssSelector(".btn_primary.btn_inventory");


    @FindBy(xpath = "(//div[@class='bm-burger-button'])[1]")
    WebElement burgerButton;

    @FindBy(xpath = "//a[text()='All Items']")
    WebElement allItems;

    @FindBy(xpath = "//a[text()='About']")
    WebElement about;

    @FindBy(xpath = "//a[text()='Logout']")
    WebElement logout;

    @FindBy(xpath = "//a[text()='Reset App State']")
    WebElement resetAppState;

    @FindBy(xpath = "//button[text()='Close Menu']")
    WebElement closeBurgerIcon;

    public void clickBurgerIcon() {

        burgerButton.click();
    }

    public void clickAllItems() {

        allItems.click();
    }

    public void clickAbout() {

        about.click();
    }

    public void clicklogout() {

        logout.click();
    }

    public void clickresetAppState() {

        resetAppState.click();
        closeBurgerIcon.click();
    }


    //get all products
    public List<WebElement> getProductsList() {
        waitForElementToAppear(productsBy);
        return products;
    }

    //get product Name
    public String getProductName(WebElement product) {
        LogsUtil.info("getting product name ", product.findElement(productNameBy).getText());
        return product.findElement(productNameBy).getText();
    }

    //get product price
    public double getProductPrice(WebElement product) {
        String priceText = product.findElement(productpriceBy).getText();

        LogsUtil.info("getting product price and remove ($)   ", priceText);
        return Double.parseDouble(priceText.replace("$", "").trim());
    }

    //check remove button return to addToCart
    public boolean isARemoveButtonVisible(WebElement product) {
        List<WebElement> removeButton = product.findElements(removeButtonBy);
        return !removeButton.isEmpty();
    }


    //get product using stream
    public WebElement getProductByName(String productName) {
        WebElement prod = (WebElement) getProductsList().stream().filter(product ->
                product.findElement(productNameBy).getText().contains(productName)).findFirst().orElse(null);
        return prod;
    }

    public void addToCart(String productName) {

        WebElement prod = getProductByName(productName);
        if (prod.equals(null)) {
            LogsUtil.info("product Doesn't exist :", productName);

        }
        prod.findElement(addbuttonBy).click();
        LogsUtil.info("Clicked on add button for product name :", productName);
    }

    //open product link
    public void openProduct() {
        productlink.click();
    }

    //choose filter
    public void choosefilter(String filter) {
        waitForElementToAppear(dropdownBy);
        Select dropdown = new Select(staticDropdown);
        switch (filter) {
            case "AToZ": {
                dropdown.selectByIndex(0);
                break;
            }
            case "ZToA": {
                dropdown.selectByIndex(1);
                break;
            }
            case "LowToHigh": {
                dropdown.selectByIndex(2);
                break;
            }
            case "HighToLow": {
                dropdown.selectByIndex(3);
                break;
            }
        }

        LogsUtil.info("Clicked on Filter :", filter);


    }

    public void addallProductToCart() {

        //add all items to cart
        for (int i = 0; i < productsName.size(); i++) {
            addButton.get(i).click();
        }
        LogsUtil.info("added all product to cart");

    }


    public String getPathJson(String productsData) {

        LogsUtil.info("getting json path for ", productsData);
        return config.getJsonPath(productsData);
    }


}
