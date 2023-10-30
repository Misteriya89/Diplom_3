package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.LoginPage;
import pages.MainPage;
import pages.RegisterPage;

@RunWith(Parameterized.class)
public class RegistrationTest {

    private WebDriver driver;
    private String driverType;

    public RegistrationTest(String driverType){
        this.driverType = driverType;
        System.setProperty(
                "webdriver.chrome.driver",
                "src\\main\\resources\\drivers\\" + this.driverType + ".exe"
        );
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.get("https://stellarburgers.nomoreparties.site");
    }

    @Parameterized.Parameters(name="driver: {0}")
    public static Object[][] getDriver(){
        return new Object[][]{
                {"chromedriver"},
                {"yandexdriver"}

        };
    }

    @Test
    @DisplayName("Успешная регистрация пользователя.")
    @Description("Проверяем успешную регистрацию пользователя и видимость текста 'Вход' на странице авторизации.")
    public void successRegistrationTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickOnRegister();
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.waitForLoadRegisterPage();
        String name = RandomStringUtils.randomAlphanumeric(4, 8);
        String email = RandomStringUtils.randomAlphanumeric(6, 10) + "@" + RandomStringUtils.randomAlphanumeric(2, 8) + ".ru";
        String password = RandomStringUtils.randomAlphanumeric(10, 20);
        registerPage.registration(name, email, password);
        loginPage.waitForLoadEntrance();
        Assert.assertTrue("Поля регистрации заполнены некорректно. Страница авторизации не отобразилась.",
                driver.findElement(loginPage.entrance).isDisplayed());
    }


    @Test
    @DisplayName("Неуспешная регистрация пользователя.")
    @Description("Проверяем неуспешную регистрацию пользователя и ловим ошибку текста 'Некорректный пароль' на странице регистрации при вводе пароля меньше 6 символов.")
    public void failedRegistrationTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickOnRegister();
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.waitForLoadRegisterPage();
        String name = RandomStringUtils.randomAlphanumeric(4, 8);
        String email = RandomStringUtils.randomAlphanumeric(6, 10) + "@" + RandomStringUtils.randomAlphanumeric(2, 8) + ".ru";
        String password = RandomStringUtils.randomAlphanumeric(0, 5);
        registerPage.registration(name, email, password);
        Assert.assertTrue("Пароль введен корректно. Регистрация прошла успешно.",
                driver.findElement(registerPage.errorPasswordText).isDisplayed());
    }

    @After
    public void tearDown() {
        driver.quit();
    }


}