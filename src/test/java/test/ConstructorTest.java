package test;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import java.time.Duration;
import org.junit.Test;
import pageobject.MainPage;
import user.TestBase;
import user.UserDataGenerator;
import user.UserSteps;
import user.User;

public class ConstructorTest extends TestBase {

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
    @DisplayName("Переход к разделу Булки на главной страннице")
    public void bunChapterTest(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSauceButton();
        mainPage.clickBunButton();
        String text = mainPage.getMenuText();
        Assert.assertEquals("Булки", text);
    }
    @Test
    @DisplayName("Переход к разделу Соусы на главной страннице")
    public void sauceChapterTest(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSauceButton();
        String text = mainPage.getMenuText();
        Assert.assertEquals("Соусы", text);
    }
    @Test
    @DisplayName("Переход к разделу Начинки на главной страннице")
    public void fillingChapterTest(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickFillingButton();
        String text = mainPage.getMenuText();
        Assert.assertEquals("Начинки", text);
    }
    @After
    public void tearDown(){
        driver.quit();
        if (accessToken != null) {
            UserSteps.deleteUser(accessToken);
        }
    }
}