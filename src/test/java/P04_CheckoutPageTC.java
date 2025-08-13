import TestComponent.BaseTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import configuration.EndToEndData;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.P02_ProductsPage;
import pages.P03_CartPage;
import pages.P04_CheckoutPage;
import utils.LogsUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class P04_CheckoutPageTC extends BaseTest {

    P04_CheckoutPage checkoutPage;
    P02_ProductsPage productsPage;
    P03_CartPage cartPage;
    List<String> checkoutProductsName = new ArrayList<>();
    ObjectMapper mapper = new ObjectMapper();
    File file = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\end2endData.json");
    EndToEndData testData = mapper.readValue(file, EndToEndData.class);
    double subTotal = 0;
    double Total = 0;
    double Tax = 0;

    public P04_CheckoutPageTC() throws IOException {
    }

    @BeforeMethod
    public void setup() {
        checkoutPage = new P04_CheckoutPage(getDriver());
        productsPage =new P02_ProductsPage(getDriver());
        cartPage =new P03_CartPage(getDriver());
        checkoutPage.goToCehckOutStepOne();
    }

    @DataProvider
    public Object[][] getProductsData() throws IOException {

        List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + getJsonPath("productsData"));


        return data.stream().map(entry -> new Object[]{entry}).toArray(Object[][]::new);
    }

    @DataProvider
    public Object[][] getCheckOutData() throws IOException {

        List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + getJsonPath("checkOutData"));


        return data.stream().map(entry -> new Object[]{entry}).toArray(Object[][]::new);
    }

    @DataProvider
    public Object[][] getvalidCheckOutData() throws IOException {

        List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + getJsonPath("validcheckOutData"));


        return data.stream().map(entry -> new Object[]{entry}).toArray(Object[][]::new);
    }


    @Test(dataProvider = "getvalidCheckOutData", groups = "smoke", priority = 1)
    public void checkOutValidData(HashMap<String, String> input) throws InterruptedException {
        checkoutPage.goToCehckOutStepOne();
        checkoutPage.addInfo(input.get("firstName"), input.get("lastName"), input.get("postalCode"));
        checkoutPage.confirm();
        Assert.assertEquals(getDriver().getCurrentUrl(), checkoutPage.getPageUrl("checkOutUrl_StepTwo"), "the user should checkout with valid data");


    }


    @Test(dataProvider = "getvalidCheckOutData", groups = "regression", priority = 2)
    public void checkOutEmptyFirstName(HashMap<String, String> input) throws InterruptedException {
        checkoutPage.goToCehckOutStepOne();


        checkoutPage.addInfo("", input.get("lastName"), input.get("postalCode"));
        LogsUtil.info("send Data to check out step one :", "", input.get("lastName"), input.get("postalCode"));

        checkoutPage.confirm();
        Assert.assertEquals(getDriver().getCurrentUrl(), checkoutPage.getPageUrl("checkOutUrl_StepOne"));
        Assert.assertEquals(checkoutPage.getErrorMessage(), "Error: First Name is required");

    }

    @Test(dataProvider = "getvalidCheckOutData", groups = "regression", priority = 2)
    public void checkOutEmptyLastName(HashMap<String, String> input) throws InterruptedException {
        checkoutPage.goToCehckOutStepOne();

        checkoutPage.addInfo(input.get("firstName"), "", input.get("postalCode"));
        LogsUtil.info("send Data to check out step one :", input.get("firstName"), "", input.get("postalCode"));
        checkoutPage.confirm();
        Assert.assertEquals(getDriver().getCurrentUrl(), checkoutPage.getPageUrl("checkOutUrl_StepOne"));
        Assert.assertEquals(checkoutPage.getErrorMessage(), "Error: Last Name is required");

    }


    @Test(dataProvider = "getvalidCheckOutData", groups = "regression", priority = 2)
    public void checkOutEmptyPostalCode(HashMap<String, String> input) throws InterruptedException {
        checkoutPage.goToCehckOutStepOne();


        checkoutPage.addInfo(input.get("firstName"), input.get("lastName"), "");
        LogsUtil.info("send Data to check out step one :", input.get("firstName"), input.get("lastName"), "");

        checkoutPage.confirm();
        Assert.assertEquals(getDriver().getCurrentUrl(), checkoutPage.getPageUrl("checkOutUrl_StepOne"));
        Assert.assertEquals(checkoutPage.getErrorMessage(), "Error: Postal Code is required");

    }


    @Test(dataProvider = "getCheckOutData", groups = "regression", priority = 2)
    public void checkOutspecialCharacterFirstName(HashMap<String, String> input) {
        checkoutPage.goToCehckOutStepOne();
        if ((input.get("type").equals("inValid")) && input.get("TC").equals("specialCharacter_FirstName")) {

            checkoutPage.addInfo(input.get("firstName"), input.get("lastName"), input.get("postalCode"));
            checkoutPage.confirm();
            Assert.assertEquals(getDriver().getCurrentUrl(), checkoutPage.getPageUrl("checkOutUrl_StepOne"));

        }
    }


    @Test(dataProvider = "getCheckOutData", groups = "regression", priority = 2)
    public void checkOutspecialCharacterLastName(HashMap<String, String> input) {
        checkoutPage.goToCehckOutStepOne();
        if ((input.get("type").equals("inValid")) && input.get("TC").equals("specialCharacter_LastName")) {

            checkoutPage.addInfo(input.get("firstName"), input.get("lastName"), input.get("postalCode"));
            checkoutPage.confirm();
            Assert.assertEquals(getDriver().getCurrentUrl(), checkoutPage.getPageUrl("checkOutUrl_StepOne"));

        }
    }


    @Test(dataProvider = "getCheckOutData", groups = "regression", priority = 2)
    public void checkOutspecialCharacterPostalCode(HashMap<String, String> input) {
        checkoutPage.goToCehckOutStepOne();
        if ((input.get("type").equals("inValid")) && input.get("TC").equals("specialCharacter_PostalCode")) {

            checkoutPage.addInfo(input.get("firstName"), input.get("lastName"), input.get("postalCode"));
            checkoutPage.confirm();
            Assert.assertEquals(getDriver().getCurrentUrl(), checkoutPage.getPageUrl("checkOutUrl_StepOne"));

        }
    }


    @Test(dataProvider = "getCheckOutData", groups = "regression", priority = 2)
    public void checkOutalphabeticalCharacterPostalCode(HashMap<String, String> input) {
        checkoutPage.goToCehckOutStepOne();
        if ((input.get("type").equals("inValid")) && input.get("TC").equals("alphabeticalCharacter_PostalCode")) {

            checkoutPage.addInfo(input.get("firstName"), input.get("lastName"), input.get("postalCode"));
            checkoutPage.confirm();
            Assert.assertEquals(getDriver().getCurrentUrl(), checkoutPage.getPageUrl("checkOutUrl_StepOne"));

        }
    }


    @Test(dataProvider = "getCheckOutData", groups = "regression", priority = 2)
    public void checkOutalnumericFirstName(HashMap<String, String> input) {
        checkoutPage.goToCehckOutStepOne();
        if ((input.get("type").equals("inValid")) && input.get("TC").equals("numeric_FirstName")) {

            checkoutPage.addInfo(input.get("firstName"), input.get("lastName"), input.get("postalCode"));
            checkoutPage.confirm();
            Assert.assertEquals(getDriver().getCurrentUrl(), checkoutPage.getPageUrl("checkOutUrl_StepOne"));

        }
    }


    @Test(dataProvider = "getCheckOutData", groups = "regression", priority = 2)
    public void checkOutalnumericLastName(HashMap<String, String> input) {
        checkoutPage.goToCehckOutStepOne();
        if ((input.get("type").equals("inValid")) && input.get("TC").equals("numeric_LastName")) {

            checkoutPage.addInfo(input.get("firstName"), input.get("lastName"), input.get("postalCode"));
            checkoutPage.confirm();
            Assert.assertEquals(getDriver().getCurrentUrl(), checkoutPage.getPageUrl("checkOutUrl_StepOne"));

        }
    }

    @Test(groups = {"somke", "regression"}, priority = 3)
    public void saveItemsCheckout() {
        checkoutPage.goToCehckOutSteptwo();

        checkoutPage.saveCheckoutProductsName(checkoutProductsName);
    }

    @Test(dataProvider = "getProductsData", groups = {"somke", "regression"}, priority = 3)
    public void verifyDisplay(HashMap<String, String> input) {
        productsPage.goTo();
        productsPage.addToCart(input.get("productName"));
        checkoutPage.goToCehckOutSteptwo();
        boolean match = checkoutPage.verifyProductdisplay(input.get("productName"));
        Assert.assertTrue(match);

    }


    @Test(groups = {"somke", "regression"}, priority = 4)
    public void verifySubTotalPrice() {
        productsPage.goTo();
        productsPage.addToCart(testData.productName_1);
        productsPage.addToCart(testData.productName_2);
        productsPage.addToCart(testData.productName_3);
        checkoutPage.goToCehckOutStepOne();
        checkoutPage.addInfo(testData.validCheckoutFirstName,testData.validCheckoutLastName,testData.validCheckoutPostalCode);
        checkoutPage.confirm();

        checkoutPage.saveCheckoutProductsName(checkoutProductsName);

        for (String name : checkoutProductsName) {

            subTotal += checkoutPage.getItemPriceByName(name);

        }

        Assert.assertEquals(subTotal, checkoutPage.getSubTotalPrice());

    }


    @Test(groups = {"somke", "regression"}, priority = 5)
    public void verifyTotalPrice() {

        productsPage.goTo();
        productsPage.addToCart(testData.productName_1);
        productsPage.addToCart(testData.productName_2);
        productsPage.addToCart(testData.productName_3);
        checkoutPage.goToCehckOutStepOne();
        checkoutPage.addInfo(testData.validCheckoutFirstName,testData.validCheckoutLastName,testData.validCheckoutPostalCode);
        checkoutPage.confirm();
        subTotal = checkoutPage.getSubTotalPrice();

        Tax = checkoutPage.getTax();
        Total = subTotal + Tax;
        Assert.assertEquals(Total, checkoutPage.getTotalPrice());


    }


    @Test(groups = {"somke", "regression"}, priority = 6)
    public void checkTheOrderConfirmed() {
        checkoutPage.goToCehckOutSteptwo();
        checkoutPage.finish();
        Assert.assertEquals(checkoutPage.getConfirmationMessage(), "THANK YOU FOR YOUR ORDER");

    }


}