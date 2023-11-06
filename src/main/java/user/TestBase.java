package user;

import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;



import java.time.Duration;

public class TestBase {

    protected WebDriver driver;
    public static final String PATH_TO_PROPERTIES = "src/main/resources/config.properties";
    private static String browser;
    private static String pathToYandex;

    @Before
    public void configure() {
        FileInputStream fileInputStream;
        Properties prop = new Properties();
        try {
            fileInputStream = new FileInputStream(PATH_TO_PROPERTIES);
            prop.load(fileInputStream);
            pathToYandex = prop.getProperty("pathToYandex");
            browser = prop.getProperty("browser");
        } catch (IOException e) {e.printStackTrace();
        }
        selectBrowser();
    }

    public void selectBrowser() {
        if ("chrome".equals(browser))
            setUpChrome();
        else
            setUpYandex();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    // Драйвер для Chrome
    public void setUpChrome() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }

    // Драйвер для Yandex
    public void setUpYandex() {
        ChromeOptions options = new ChromeOptions();
        System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\yandexdriver.exe");
        options.addArguments("--remote-allow-origins=*");
        options.setBinary(pathToYandex);
        driver = new ChromeDriver(options);

    }


}