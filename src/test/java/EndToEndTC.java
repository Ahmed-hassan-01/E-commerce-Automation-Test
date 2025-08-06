import TestComponent.BaseTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import configuration.EndToEndData;
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

import java.io.File;
import java.io.IOException;

public class EndToEndTC extends BaseTest {

    P01_LoginPage loginPage;
    P02_ProductsPage productsPage;
    P03_CartPage cartPage;
   P04_CheckoutPage  checkoutPage;

   ObjectMapper mapper = new ObjectMapper();
   File file = new File(System.getProperty("user.dir")+"\\src\\main\\resources\\end2endData.json");
    EndToEndData testData = mapper.readValue(file,EndToEndData.class);

    public EndToEndTC() throws IOException {
    }

    @BeforeClass(groups = {"smoke"})
    public void setup() {

        loginPage = new P01_LoginPage(driver);
        productsPage = new P02_ProductsPage(driver);
        cartPage = new P03_CartPage(driver);
        checkoutPage = new P04_CheckoutPage(driver);
    }

    @Test(groups = {"smoke"})
    public void validTestEndToEndScenario() {
        loginPage.goTo();
        loginPage.login(testData.validLoginUser_Name, testData.validLoginPassword);
        loginPage.submit();
        productsPage.addToCart(testData.productName_1);
        cartPage.goToCart();
        cartPage.Checkout();
        checkoutPage.addInfo(testData.validCheckoutFirstName, testData.validCheckoutLastName, testData.validCheckoutPostalCode);
        checkoutPage.confirm();
        checkoutPage.finish();
        attachScreenshot(driver);
        Assert.assertTrue(checkoutPage.getConfirmationMessage().contains("THANK YOU"));
    }


    @Test()
    public void TestEndToEndScenarioWithFiletring() {
        loginPage.goTo();
        loginPage.login(testData.validLoginUser_Name, testData.validLoginPassword);
        loginPage.submit();
        productsPage.choosefilter("ZToA");
        productsPage.addToCart(testData.productName_2);
        cartPage.goToCart();
        cartPage.Checkout();
        checkoutPage.addInfo(testData.validCheckoutFirstName, testData.validCheckoutLastName, testData.validCheckoutPostalCode);
        checkoutPage.confirm();
        checkoutPage.finish();
        attachScreenshot(driver);

        Assert.assertTrue(checkoutPage.getConfirmationMessage().contains("THANK YOU"));
    }

    @Test()
    public void TestEndToEndScenario3() {
        loginPage.goTo();
        loginPage.login(testData.validLoginUser_Name, testData.validLoginPassword);
        loginPage.submit();
        productsPage.choosefilter("AToZ");
        productsPage.addToCart(testData.productName_3);
        cartPage.goToCart();
        cartPage.verifyProductdisplay(testData.productName_3);
        cartPage.Checkout();
        checkoutPage.addInfo(testData.validCheckoutFirstName, testData.validCheckoutLastName, testData.validCheckoutPostalCode);
        checkoutPage.confirm();
        checkoutPage.finish();
        attachScreenshot(driver);
        Assert.assertTrue(checkoutPage.getConfirmationMessage().contains("THANK YOU"));
    }

    @Test()
    public void TestEndToEndScenario4()  {
        loginPage.goTo();
        loginPage.login(testData.validLoginUser_Name, testData.validLoginPassword);
        loginPage.submit();
        productsPage.choosefilter("ZToA");
        productsPage.addToCart(testData.productName_1);
        productsPage.addToCart(testData.productName_2);
        productsPage.addToCart(testData.productName_3);
        cartPage.goToCart();
        cartPage.removeAllCartProduct();
        cartPage.Checkout();
        checkoutPage.addInfo(testData.validCheckoutFirstName, testData.validCheckoutLastName, testData.validCheckoutPostalCode);
        checkoutPage.confirm();
        checkoutPage.finish();
        attachScreenshot(driver);
        Assert.assertFalse(checkoutPage.getConfirmationMessage().contains("THANK YOU"));
    }




    @AfterClass
    public void tearDown() {
        driver.quit();
    }



}
