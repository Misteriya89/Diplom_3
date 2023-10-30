package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
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
import pages.ProfilePage;

@RunWith(Parameterized.class)
public class TransitionInProfilePageTest {

    private WebDriver driver;
    private String driverType;
    private final static String EMAIL = "test@ya.ru";
    private final static String PASSWORD = "password";

    public TransitionInProfilePageTest(String driverType){
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
    @DisplayName("Переход на страницу личного кабинета.")
    @Description("Проверяем переход на страницу авторизации и видимость заголовка 'Вход' на ней.")
    public void transitionToProfilePageTest(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForLoadEntrance();
        Assert.assertTrue("Страница авторизации не отобразилась.",
                driver.findElement(loginPage.entrance).isDisplayed());
    }

    @Test
    @DisplayName("Переход на страницу личного кабинета с авторизацией.")
    @Description("Проверяем переход на страницу личного кабинета зарегистрированного и авторизованного пользователя и видимость заголовка 'Вход' на ней.")
    public void transitionToProfilePageWithAuthUserTest(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForLoadEntrance();
        loginPage.authorization(EMAIL, PASSWORD);
        mainPage.waitForLoadMainPage();
        mainPage.clickOnAccountButton();
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.waitForLoadProfilePage();
        Assert.assertTrue("Страница личного кабинета не прогрузилась",
                driver.findElement(profilePage.textOnProfilePage).isDisplayed());
    }

    @Test
    @DisplayName("Клик по логотипу 'Stellar Burgers'")
    @Description("Проверяем переход на главную страницу, кликнув по логотипу со страницы авторизации, и видимость текста на главной странице 'Соберите бургер'.")
    public void transitionToStellarBurgersFromProfilePageTest(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForLoadEntrance();
        loginPage.clickOnLogo();
        mainPage.waitForLoadMainPage();
        Assert.assertTrue("Главная страница не загрузилась", driver.findElement(mainPage.textBurgerMainPage).isDisplayed());
    }

    @Test
    @DisplayName("'Выход' из личного кабинета")
    @Description("Проверяем кнопку 'Выход' в личном кабинете и видимость текста заголовка 'Вход' на странице авторизации.")
    public void exitFromProfileTest(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForLoadEntrance();
        loginPage.authorization(EMAIL,PASSWORD);
        mainPage.waitForLoadMainPage();
        mainPage.clickOnAccountButton();
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.waitForLoadProfilePage();
        profilePage.clickOnExitButton();
        mainPage.waitForInvisibilityLoadingAnimation();
        Assert.assertTrue("Не удалось выйти из аккаунта", driver.findElement(loginPage.entrance).isDisplayed());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}