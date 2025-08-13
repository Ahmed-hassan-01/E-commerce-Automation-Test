import TestComponent.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.P02_ProductsPage;
import pages.P03_CartPage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class P03_CartPageTC extends BaseTest {

    //WebDriver driver = new ChromeDriver();
    P03_CartPage cartPage;
    P02_ProductsPage productsPage;
    List<String> savedCartProductsName = new ArrayList<>();


    @BeforeMethod
    public void setup() {
        cartPage = new P03_CartPage(getDriver());
        productsPage = new P02_ProductsPage(getDriver());
        productsPage.goTo();
        cartPage.goTo();
        List<String> savedProductsName = new ArrayList<>();
    }


    @DataProvider
    public Object[][] getProductsData() throws IOException {
        P03_CartPage localCartPage = new P03_CartPage(getDriver());

        List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + localCartPage.getPathJson("productsData"));


        return data.stream()
                .map(entry -> new Object[]{entry})
                .toArray(Object[][]::new);
    }

    @Test(priority = 1, groups = {"cart", "smoke", "regression"}, dataProvider = "getProductsData")
    public void verifyDisplay(HashMap<String, String> input) {
        productsPage.goTo();
        if (input.get("action").equals("add")) {
            productsPage.addToCart(input.get("productName"));
        }
        cartPage.goTo();
        if (input.get("action").equals("add")) {
            boolean match = cartPage.verifyProductdisplay(input.get("productName"));
            Assert.assertTrue(match);

        }

    }

    @Test(priority = 2, groups = {"cart", "regression"}, dataProvider = "getProductsData")
    public void removeProduct(HashMap<String, String> input) {
        productsPage.goTo();
        if (input.get("action").equals("add")) {
            productsPage.addToCart(input.get("productName"));
        }
        cartPage.goTo();
        if (input.get("action").equals("remove")) {

            cartPage.removeCartProduct(input.get("productName"));


        }


    }

    @Test(priority = 3, groups = {"cart", "regression"}, dataProvider = "getProductsData")
    public void verifyProductsRemoved(HashMap<String, String> input) {
        cartPage.goToCart();
        if (input.get("action").equals("remove")) {
            boolean match = cartPage.verifyProductdisplay(input.get("productName"));
            Assert.assertFalse(match);

        }
    }

    @Test(priority = 4, groups = {"cart", "regression"})
    public void saveCartProductsName() {
        cartPage.saveCartProductsName(savedCartProductsName);

    }


    @Test(priority = 5, groups = {"cart", "regression"})
    public void checkValidationButtoncontinueshopping() {
        cartPage.continueShopping();
        Assert.assertEquals(getDriver().getCurrentUrl(), cartPage.getPageUrl("products_url"));
    }

    @Test(priority = 6, groups = {"cart", "smoke", "regression"})
    public void Checkout() {
        cartPage.goTo();
        cartPage.Checkout();
        Assert.assertEquals(getDriver().getCurrentUrl(), cartPage.getPageUrl("checkOutUrl_StepOne"));
    }
}
