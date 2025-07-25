package org.example;

//import org.openqa.selenium.By;

import com.sun.source.tree.AssertTree;
import com.sun.source.tree.Tree;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.lang.reflect.Array;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import static org.junit.jupiter.api.Assertions.*;

//import org.junit.jupiter.api.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

// 1️⃣ Build the prefs map to turn off the password manager
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);      // Disable credential service
        prefs.put("profile.password_manager_enabled", false); // Disable built‑in password manager

        // 2️⃣ Configure ChromeOptions
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);

        // (Optional) Launch in Incognito so nothing is stored between runs
        options.addArguments("--incognito");

        // 3️⃣ Spin up the driver with the customised options
        WebDriver driver = new ChromeDriver(options);


        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        driver.get("https://www.saucedemo.com/v1/index.html");
        driver.manage().window().maximize();
        String title = driver.getTitle();

        //login page
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        //inventory page

        //check dropDown filter
        WebElement staticDropdown = driver.findElement(By.className("product_sort_container"));
        Select dropdown = new Select(staticDropdown);

//check the filter z to a filter
        dropdown.selectByIndex(1);
        List<WebElement> products = driver.findElements(By.cssSelector("div.inventory_item_name"));

        String first_elemnt = String.valueOf(products.get(0));



        String temp = first_elemnt;
        for (int i = 1; i <products.size(); i++) {
            if (temp.compareTo(String.valueOf(products.get(i))) < 0) {
                System.out.println("failed in filter z to a  ");
                break;
            }

        }


        //check the filter a to z filter

        dropdown.selectByIndex(0);
        first_elemnt = String.valueOf(products.get(0));;

        temp = first_elemnt;
        for (int i = 1; i <= 3; i++) {
            if (temp.compareTo(String.valueOf(products.get(i)))> 0) {
                System.out.println("failed in filter a to z  ");
                break;
            }

        }


        //assertEquals("Sauce Labs Backpack",first_elemnt);

        // save products in list

        List <WebElement> addProduct= driver.findElements(By.xpath("//div[@class='pricebar']/button"));

       // the item you would to check in array

        String[] item_name = {"Backpack", "Bike light"};
        List item_name_list = Arrays.asList(item_name);

        //add items to cart
        for (int i = 0; i < products.size(); i++) {

            driver.findElements(By.xpath("//div[@class='pricebar']/button")).get(i).click();

        }

        driver.quit();
    }

}