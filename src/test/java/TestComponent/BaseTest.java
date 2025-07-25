package TestComponent;

import ch.qos.logback.core.util.FileUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import configuration.Config;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class BaseTest extends Config {
    public static WebDriver driver;
    WebDriverWait wait;

    @BeforeSuite
    public void launchBrowser() throws IOException {

        Properties prop = new Properties();
        FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\configuration\\GlobalData.properties");
        prop.load(fis);
String browserName=prop.getProperty("browser");
        if (driver == null) {

           Map<String, Object> prefs = new HashMap<>();
           prefs.put("credentials_enable_service", false);      // Disable credential service
            prefs.put("profile.password_manager_enabled", false); // Disable built‑in password manager

            // 2️⃣ Configure ChromeOptions
            ChromeOptions options = new ChromeOptions();
          //  options.setExperimentalOption("prefs", prefs);

            // (Optional) Launch in Incognito so nothing is stored between runs
            options.addArguments("--incognito");


            // 3️⃣ Spin up the driver with the customised options
            System.setProperty("webdriver.chrome.driver", "O:\\chromedriver\\chromedriver-win64\\chromedriver.exe");
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(options);
            System.out.println("Starting ChromeDriver...");
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
            PageFactory.initElements(driver, this);
        }
    }



    public  String getScreenShot(String testCaseName,WebDriver driver) throws IOException {
        TakesScreenshot ts= (TakesScreenshot) driver;
        File source =ts.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir")+"\\reports"+testCaseName + ".png");
        FileHandler.copy(source,file);
        return System.getProperty("user.dir")+"\\reports"+ testCaseName + ".png";
    }

    @Attachment(value = "Failure Screenshot", type = "image/png")
    public static byte[] attachScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }


    public List<HashMap<String,String>> getJsonDataToMap(String filePath) throws IOException {

        String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

        ObjectMapper mapper = new ObjectMapper();

       List <HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
       });

       return data;

    }


        @AfterSuite
        public void closeBrowser () {
            if (driver != null) {
                driver.quit();
            }
        }
    }



