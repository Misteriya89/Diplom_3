package test;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.*;
import pageobject.LoginPage;
import pageobject.MainPage;
import pageobject.ProfilePage;
import user.TestBase;
import user.User;
import user.UserDataGenerator;
import user.UserSteps;

import java.time.Duration;

public class ConstructorEnterTest extends TestBase {

    private User user;
    private String accessToken;



    @Before
    public void setUp() {
        driver.get(UserSteps.baseURL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10L));
        RestAssured.baseURI = UserSteps.baseURL;
        user = UserDataGenerator.getRandomUser();
        accessToken = UserSteps.createNewUser(user).then().extract().path("accessToken");


    }
    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на «Конструктор»")
    public void enterToConstructorFromButtonTest(){
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        mainPage.clickAccountButton();
        loginPage.setUserLoginInfo(user.getEmail(), user.getPassword());
        loginPage.clickLoginButton();
        mainPage.clickAccountButton();
        profilePage.clickConstructorButton();
        String text = mainPage.getCreateOrderButtonText();
        Assert.assertEquals("Оформить заказ", text);
    }
    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на логотип Stellar Burgers")
    public void enterToConstructorFromLogoTest(){
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        mainPage.clickAccountButton();
        loginPage.setUserLoginInfo(user.getEmail(), user.getPassword());
        loginPage.clickLoginButton();
        mainPage.clickAccountButton();
        profilePage.clickLogoButton();
        String text = mainPage.getCreateOrderButtonText();
        Assert.assertEquals("Оформить заказ", text);
    }
    @After
    public void tearDown(){
        driver.quit();
        if (accessToken != null) {
            UserSteps.deleteUser(accessToken);
        }
    }
}