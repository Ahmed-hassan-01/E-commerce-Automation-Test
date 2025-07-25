import ExtentReports.ExtentReport;
import TestComponent.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.P01_LoginPage;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class P01_LoginPageTC extends BaseTest {
    ExtentReport extent =new ExtentReport();
    P01_LoginPage LoginPage;
    @BeforeClass
    public void setup() {
        LoginPage = new P01_LoginPage(driver);
    }


    @BeforeMethod
    public void goToLogin() {
        LoginPage.goTo();
    }

@DataProvider
    public Object[][] getValidData() throws IOException {

        List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+getJsonPath("loginValidData"));


    return data.stream()
            .map(entry -> new Object[]{entry})
            .toArray(Object[][]::new);
    }
    @DataProvider
    public Object[][] getInvalidData() throws IOException {

        List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+getJsonPath("loginInvalidData"));


        return data.stream()
                .map(entry -> new Object[]{entry})
                .toArray(Object[][]::new);
    }


    @Test(dataProvider="getValidData" ,priority = 1, groups = {"login","smoke"})
    public void validUserNameAndPassword(HashMap<String,String> input)  {
        //LoginPage.goTo();
       // ExtentTest test = extent.createTest("init");
        LoginPage.login(input.get("user_Name"),input.get("password") );
        LoginPage.sub();
        Assert.assertEquals(driver.getCurrentUrl(),input.get("afterLoginUrl"));
    }

    @Test(dataProvider = "getValidData",priority = 2 ,groups = {"login","regression"})
    public void validUserNameAndemptyPassword(HashMap<String,String> input) {
        //LoginPage.goTo();
        LoginPage.login(input.get("user_Name"), "");
        LoginPage.sub();
        Assert.assertEquals(driver.getCurrentUrl(),input.get("LoginUrl"));
        String errorMessage = LoginPage.getErrorMessage();
        Assert.assertEquals(errorMessage,"Epic sadface: Password is required");


    }

    @Test(dataProvider = "getValidData",priority = 3,groups = {"login","regression"})
    public void validPasswordAndemptyUsername(HashMap<String,String> input) {
        //LoginPage.goTo();
        LoginPage.login("", input.get("password"));
        LoginPage.sub();
     String errorMessage = LoginPage.getErrorMessage();
     Assert.assertEquals(errorMessage,"Epic sadface: Username is required");
    }

    @Test(dataProvider = "getInvalidData",priority = 4,groups = {"login","regression"})
    public void vlidationLockeUser(HashMap<String,String> input) {

        LoginPage.login(input.get("lockedName"), input.get("password"));
        LoginPage.sub();
    }




}
