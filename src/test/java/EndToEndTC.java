import TestComponent.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.P01_LoginPage;
import pages.P02_ProductsPage;
import pages.P03_CartPage;
import pages.P04_CheckoutPage;

public class EndToEndTC extends BaseTest {

    P01_LoginPage loginPage;
    P02_ProductsPage productsPage;
    P03_CartPage cartPage;
   P04_CheckoutPage  checkoutPage;

    @BeforeClass
    public void setup() {

        loginPage = new P01_LoginPage(driver);
        productsPage = new P02_ProductsPage(driver);
        cartPage = new P03_CartPage(driver);
        checkoutPage = new P04_CheckoutPage(driver);
    }

    @Test
    public void testEndToEndScenario() {
        loginPage.goTo();
        loginPage.login("standard_user", "secret_sauce");
        loginPage.sub();
        productsPage.addToCart("Sauce Labs Backpack");
        cartPage.goToCart();
        cartPage.Checkout();
        checkoutPage.addInfo("mohamed ", "salah", "12345");
        checkoutPage.confirm();
        checkoutPage.finish();

        Assert.assertTrue(checkoutPage.getConfirmationMessage().contains("THANK YOU"));
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }



}
