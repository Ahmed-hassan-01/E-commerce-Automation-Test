import TestComponent.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.P01_LoginPage;
import pages.P02_ProductsPage;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class P02_ProductsPageTC extends BaseTest {


    P02_ProductsPage productsPage;
    List<WebElement> products;

    @DataProvider
    public Object[][] getProductsData() throws IOException {

        List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + getJsonPath("productsData"));


        return data.stream()
                .map(entry -> new Object[]{entry})
                .toArray(Object[][]::new);
    }


    @BeforeClass
    public void setup() {
        productsPage = new P02_ProductsPage(driver);
        productsPage.goTo();

    }


    @Test(dataProvider = "getProductsData", priority = 1, groups = {"products", "smoke", "regression"})
    public void addToCart(HashMap<String, String> input) {

        if (input.get("action").equals("add")) {
            productsPage.addToCart(input.get("productName"));

        }
    }

    @Test( priority = 2, groups = {"products","regression"})
    public void verifyRemoveButtonDisappearsAfterReset()  {

      productsPage.clickBurgerIcon();
        productsPage.clickresetAppState();
        products = productsPage.getProductsList();
        for (WebElement prod : products) {
            Assert.assertFalse(productsPage.isARemoveButtonVisible(prod));

        }

    }

    @Test(dataProvider = "getProductsData", priority = 3, groups = {"products", "smoke", "regression"})
    public void addToCartAfterResset(HashMap<String, String> input) {
        productsPage.goTo();
        if (input.get("action").equals("add")) {
            productsPage.addToCart(input.get("productName"));

        }
    }

    @Test(priority = 4, groups = {"products","regression"})
    public void verifySortOrderAToZ() {
        productsPage.choosefilter("AToZ");
        products = productsPage.getProductsList();

        for (int i = 0; i < products.size() - 1; i++) {
            Assert.assertTrue(productsPage.getProductName(products.get(i)).compareTo(productsPage.getProductName(products.get(i + 1))) <= 0);
        }

    }

    @Test(priority = 5, groups = {"products", "regression"})
    public void verifySortOrderZToA() {
        productsPage.choosefilter("ZToA");
        products = productsPage.getProductsList();

        for (int i = 0; i < products.size() - 1; i++) {
            Assert.assertTrue(productsPage.getProductName(products.get(i)).compareToIgnoreCase(productsPage.getProductName(products.get(i + 1))) >= 0);
        }


    }

    @Test(priority = 6, groups = {"products", "regression"})
    public void verifySortOrderLowToHigh() {
        productsPage.choosefilter("LowToHigh");
        products = productsPage.getProductsList();
        for (int i = 0; i < products.size() - 1; i++) {
            Assert.assertTrue(productsPage.getProductPrice(products.get(i)) <= (productsPage.getProductPrice(products.get(i + 1))));
        }

    }

    @Test(priority = 7, groups = {"products", "regression"})
    public void verifySortOrderHighToLow() {
        productsPage.choosefilter("HighToLow");
        products = productsPage.getProductsList();
        for (int i = 0; i < products.size() - 1; i++) {
            Assert.assertTrue(productsPage.getProductPrice(products.get(i)) >= (productsPage.getProductPrice(products.get(i + 1))));
        }

    }


}