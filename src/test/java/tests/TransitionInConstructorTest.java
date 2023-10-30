package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import pages.MainPage;


@RunWith(Parameterized.class)
public class TransitionInConstructorTest {

    private final WebDriver driver;
    private final String driverType;

    public TransitionInConstructorTest(String driverType) {
        this.driverType = driverType;
        System.setProperty(
                "webdriver.chrome.driver",
                "src\\main\\resources\\drivers\\" + this.driverType + ".exe"
        );
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.get("https://stellarburgers.nomoreparties.site");
        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[contains(@class,'_menuContainer')]//ul)[last()]/a")));
    }

    @Parameterized.Parameters(name = "driver: {0}")
    public static Object[][] getDriver() {
        return new Object[][]{
                {"chromedriver"},
                {"yandexdriver"},
        };
    }

    @Test
    @DisplayName("Переход в конструктор из личного кабинета.")
    @Description("Проверка перехода на вкладку 'Конструктор' из страницы авторизации пользователя.")
    public void transitionToConstructorFromProfilePageTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForInvisibilityLoadingAnimation();
        mainPage.clickOnAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForLoadEntrance();
        loginPage.clickOnConstructorButton();
        mainPage.waitForLoadMainPage();
        Assert.assertTrue("Переход на страницу конструктора не прошел", driver.findElement(mainPage.textBurgerMainPage).isDisplayed());
    }

    @Test
    @DisplayName("Переход на вкладку 'Булки'")
    @Description("Проверяем переход на вкладку 'Булки' и видимость первой картинки с 'булкой'.")
    public void transitionToBunsInConstructorTest() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForInvisibilityLoadingAnimation();
        mainPage.clickOnFillingButton();
        mainPage.waitForLoadFillingsHeader();
        //без ожидания не работает
        Thread.sleep(100);
        mainPage.clickOnBunsButton();
        mainPage.waitForLoadBunsHeader();
        Assert.assertTrue("Блок с 'булками' не виден", driver.findElement(mainPage.bunsImg).isDisplayed());
    }

    @Test
    @DisplayName("Переход на вкладку 'Соусы'")
    @Description("Проверяем переход на вкладку 'Соусы' и видимость картинки с 'соусом'.")
    public void transitionToSaucesInConstructorTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForInvisibilityLoadingAnimation();
        mainPage.clickOnSaucesButton();
        mainPage.waitForLoadSaucesHeader();
        Assert.assertTrue("Блок с 'соусами' не виден", driver.findElement(mainPage.saucesImg).isDisplayed());
    }

    @Test
    @DisplayName("Переход на вкладку 'Начинки'")
    @Description("Проверяем переход на вкладку 'Начинки' и видимость картинки с 'начинкой'.")
    public void transitionToFillingsInConstructorTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForInvisibilityLoadingAnimation();
        mainPage.clickOnFillingButton();
        mainPage.waitForLoadFillingsHeader();
        Assert.assertTrue("Блок с 'соусами' не виден", driver.findElement(mainPage.fillingsImg).isDisplayed());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}