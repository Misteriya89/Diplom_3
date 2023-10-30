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
import pages.RecoverPasswordPage;
import pages.RegisterPage;

@RunWith(Parameterized.class)
public class LoginTest {

    private WebDriver driver;
    private String driverType;
    private final static String EMAIL = "test@ya.ru";
    private final static String PASSWORD = "password";

    public LoginTest(String driverType){
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
                {"yandexdriver"},
        };
    }


    @Test
    @DisplayName("Авторизация через кнопку 'Войти в аккаунт'.")
    @Description("Проверяем вход через кнопку 'Войти в аккаунт' и видимость элемента текста 'Соберите бургер' на главной странице.")
    public void authorizationByLoginButtonTest(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.authorization(EMAIL, PASSWORD);
        mainPage.waitForLoadMainPage();
        Assert.assertTrue("Авторизация не прошла успешно",
                driver.findElement(mainPage.textBurgerMainPage).isDisplayed());
    }

    @Test
    @DisplayName("Авторизация через кнопку 'Личный кабинет'.")
    @Description("Проверяем вход через кнопку 'Личный кабинет' и видимость элемента текста 'Соберите бургер' на главной странице.")
    public void authorizationByAccountButtonTest(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.authorization(EMAIL,PASSWORD);
        mainPage.waitForLoadMainPage();
        Assert.assertTrue("Авторизация не прошла успешно",
                driver.findElement(mainPage.textBurgerMainPage).isDisplayed());
    }

    @Test
    @DisplayName("Авторизация через форму регистрации.")
    @Description("Проверяем вход через форму регистрации и видимость элемента текста 'Соберите бургер' на главной странице.")
    public void authorizationByRegisterFormTest(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickOnRegister();
        RegisterPage registerPage = new RegisterPage(driver);
        String name = RandomStringUtils.randomAlphanumeric(4,6);
        String email = RandomStringUtils.randomAlphanumeric(5,8)+"@mail.ru";
        String password = RandomStringUtils.randomAlphanumeric(6,8);
        registerPage.registration(name, email, password);
        loginPage.waitForLoadEntrance();
        loginPage.authorization(email, password);
        mainPage.waitForLoadMainPage();
        Assert.assertTrue("Авторизация не прошла успешно",
                driver.findElement(mainPage.textBurgerMainPage).isDisplayed());
    }

    @Test
    @DisplayName("Авторизация через форму восстановления пароля.")
    @Description("Проверяем вход через форму восстановления пароля и видимость элемента текста 'Соберите бургер' на главной странице.")
    public void authorizationByRecoverPasswordTest(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickOnForgotPasswordLink();
        RecoverPasswordPage recoverPasswordPage = new RecoverPasswordPage(driver);
        recoverPasswordPage.clickOnLoginLink();
        loginPage.authorization(EMAIL,PASSWORD);
        mainPage.waitForLoadMainPage();
        Assert.assertTrue("Авторизация не прошла успешно",
                driver.findElement(mainPage.textBurgerMainPage).isDisplayed());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}