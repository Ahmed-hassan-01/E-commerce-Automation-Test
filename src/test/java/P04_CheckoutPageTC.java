import TestComponent.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.P04_CheckoutPage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class P04_CheckoutPageTC extends BaseTest {

    P04_CheckoutPage checkoutPage;
    List<String> checkoutProductsName = new ArrayList<>();
    double subTotal = 0;
    double Total = 0;
    double Tax = 0;

    @BeforeClass(groups = {"smoke", "regression"})
    public void setup() {
        checkoutPage = new P04_CheckoutPage(driver);
        checkoutPage.goToCehckOutStepOne();
    }

    @DataProvider
    public Object[][] getProductsData() throws IOException {

        List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + getJsonPath("productsData"));


        return data.stream()
                .map(entry -> new Object[]{entry})
                .toArray(Object[][]::new);
    }

    @DataProvider
    public Object[][] getCheckOutData() throws IOException {

        List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + getJsonPath("checkOutData"));


        return data.stream()
                .map(entry -> new Object[]{entry})
                .toArray(Object[][]::new);
    }


    @Test(dataProvider = "getCheckOutData", groups = "smoke", priority = 1)
    public void checkOutValidData(HashMap<String, String> input) throws InterruptedException {
        checkoutPage.goToCehckOutStepOne();
        if (input.get("type").equals("valid")) {
            checkoutPage.addInfo(input.get("firstName"), input.get("lastName"), input.get("postalCode"));
            checkoutPage.confirm();
            Assert.assertEquals(driver.getCurrentUrl(), checkoutPage.getPageUrl("checkOutUrl_StepTwo"), "the user should checkout with valid data");


        }
    }


    @Test(dataProvider = "getCheckOutData", groups = "regression", priority = 2)
    public void checkOutEmptyFirstName(HashMap<String, String> input) throws InterruptedException {
        checkoutPage.goToCehckOutStepOne();
        if ((input.get("type").equals("inValid")) && input.get("TC").equals("empty_FirstName")) {

            checkoutPage.addInfo(input.get("firstName"), input.get("lastName"), input.get("postalCode"));
            checkoutPage.confirm();
            Assert.assertEquals(driver.getCurrentUrl(), checkoutPage.getPageUrl("checkOutUrl_StepOne"));
            Assert.assertEquals(checkoutPage.getErrorMessage(), "Error: First Name is required");
        }
    }

    @Test(dataProvider = "getCheckOutData", groups = "regression", priority = 2)
    public void checkOutEmptyLastName(HashMap<String, String> input) throws InterruptedException {
        checkoutPage.goToCehckOutStepOne();
        if ((input.get("type").equals("inValid")) && input.get("TC").equals("empty_LastName")) {

            checkoutPage.addInfo(input.get("firstName"), input.get("lastName"), input.get("postalCode"));
            checkoutPage.confirm();
            Assert.assertEquals(driver.getCurrentUrl(), checkoutPage.getPageUrl("checkOutUrl_StepOne"));
            Assert.assertEquals(checkoutPage.getErrorMessage(), "Error: Last Name is required");
        }
    }


    @Test(dataProvider = "getCheckOutData", groups = "regression", priority = 2)
    public void checkOutEmptyPostalCode(HashMap<String, String> input) throws InterruptedException {
        checkoutPage.goToCehckOutStepOne();
        if ((input.get("type").equals("inValid")) && input.get("TC").equals("PostalCode")) {

            checkoutPage.addInfo(input.get("firstName"), input.get("lastName"), input.get("postalCode"));
            checkoutPage.confirm();
            Assert.assertEquals(driver.getCurrentUrl(), checkoutPage.getPageUrl("checkOutUrl_StepOne"));
            Assert.assertEquals(checkoutPage.getErrorMessage(), "Error: Postal Code is required");
        }
    }


    @Test(dataProvider = "getCheckOutData", groups = "regression", priority = 2)
    public void checkOutspecialCharacterFirstName(HashMap<String, String> input) {
        checkoutPage.goToCehckOutStepOne();
        if ((input.get("type").equals("inValid")) && input.get("TC").equals("specialCharacter_FirstName")) {

            checkoutPage.addInfo(input.get("firstName"), input.get("lastName"), input.get("postalCode"));
            checkoutPage.confirm();
            Assert.assertEquals(driver.getCurrentUrl(), checkoutPage.getPageUrl("checkOutUrl_StepOne"));

        }
    }


    @Test(dataProvider = "getCheckOutData", groups = "regression", priority = 2)
    public void checkOutspecialCharacterLastName(HashMap<String, String> input) {
        checkoutPage.goToCehckOutStepOne();
        if ((input.get("type").equals("inValid")) && input.get("TC").equals("specialCharacter_LastName")) {

            checkoutPage.addInfo(input.get("firstName"), input.get("lastName"), input.get("postalCode"));
            checkoutPage.confirm();
            Assert.assertEquals(driver.getCurrentUrl(), checkoutPage.getPageUrl("checkOutUrl_StepOne"));

        }
    }


    @Test(dataProvider = "getCheckOutData", groups = "regression", priority = 2)
    public void checkOutspecialCharacterPostalCode(HashMap<String, String> input) {
        checkoutPage.goToCehckOutStepOne();
        if ((input.get("type").equals("inValid")) && input.get("TC").equals("specialCharacter_PostalCode")) {

            checkoutPage.addInfo(input.get("firstName"), input.get("lastName"), input.get("postalCode"));
            checkoutPage.confirm();
            Assert.assertEquals(driver.getCurrentUrl(), checkoutPage.getPageUrl("checkOutUrl_StepOne"));

        }
    }


    @Test(dataProvider = "getCheckOutData", groups = "regression", priority = 2)
    public void checkOutalphabeticalCharacterPostalCode(HashMap<String, String> input) {
        checkoutPage.goToCehckOutStepOne();
        if ((input.get("type").equals("inValid")) && input.get("TC").equals("alphabeticalCharacter_PostalCode")) {

            checkoutPage.addInfo(input.get("firstName"), input.get("lastName"), input.get("postalCode"));
            checkoutPage.confirm();
            Assert.assertEquals(driver.getCurrentUrl(), checkoutPage.getPageUrl("checkOutUrl_StepOne"));

        }
    }


    @Test(dataProvider = "getCheckOutData", groups = "regression", priority = 2)
    public void checkOutalnumericFirstName(HashMap<String, String> input) {
        checkoutPage.goToCehckOutStepOne();
        if ((input.get("type").equals("inValid")) && input.get("TC").equals("numeric_FirstName")) {

            checkoutPage.addInfo(input.get("firstName"), input.get("lastName"), input.get("postalCode"));
            checkoutPage.confirm();
            Assert.assertEquals(driver.getCurrentUrl(), checkoutPage.getPageUrl("checkOutUrl_StepOne"));

        }
    }


    @Test(dataProvider = "getCheckOutData", groups = "regression", priority = 2)
    public void checkOutalnumericLastName(HashMap<String, String> input) {
        checkoutPage.goToCehckOutStepOne();
        if ((input.get("type").equals("inValid")) && input.get("TC").equals("numeric_LastName")) {

            checkoutPage.addInfo(input.get("firstName"), input.get("lastName"), input.get("postalCode"));
            checkoutPage.confirm();
            Assert.assertEquals(driver.getCurrentUrl(), checkoutPage.getPageUrl("checkOutUrl_StepOne"));

        }
    }

    @Test(groups = {"somke", "regression"}, priority = 3)
    public void saveItemsCheckout() {
        checkoutPage.goToCehckOutSteptwo();

        checkoutPage.saveCheckoutProductsName(checkoutProductsName);
    }

    @Test(dataProvider = "getProductsData", groups = {"somke", "regression"}, priority = 3)
    public void verifyDisplay(HashMap<String, String> input) {
        checkoutPage.goToCehckOutSteptwo();

        if (input.get("action").equals("view")) {
            boolean match = checkoutPage.verifyProductdisplay(input.get("productName"));

            Assert.assertTrue(match);

        }

    }


    @Test(groups = {"somke", "regression"}, priority = 4)
    public void verifySubTotalPrice() {
        checkoutPage.goToCehckOutSteptwo();

        for (String name : checkoutProductsName) {

            subTotal += checkoutPage.getItemPriceByName(name);

        }

        Assert.assertEquals(subTotal, checkoutPage.getSubTotalPrice());


    }


    @Test(groups = {"somke", "regression"}, priority = 5)
    public void verifyTotalPrice() {
        checkoutPage.goToCehckOutSteptwo();

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