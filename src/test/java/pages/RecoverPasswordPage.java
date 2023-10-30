package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RecoverPasswordPage {

    private WebDriver driver;

    // Поле "Email"
    private By emailField = By.xpath(".//div[./label[text()='Email']]/input[@name='name']");
    // Кнопка "Восстановить"
    private By recoverButton = By.xpath(".//form/button[text()='Восстановить']");
    // Заголовок "Восстановление пароля"
    public By recoverPassword = By.xpath(".//main/div/h2[text()='Восстановление пароля']");
    // Ссылка "Войти"
    private By loginLink = By.xpath(".//div/p/a[@href = '/login' and text() = 'Войти']");


    public RecoverPasswordPage(WebDriver driver){
        this.driver = driver;
    }


    @Step("Ввод Email")
    public void setEmail(String email){
        driver.findElement(emailField).sendKeys(email);
    }
    @Step("Клик по кнопке 'Восстановить'")
    public void clickOnRecoverButton(){
        driver.findElement(recoverButton).click();
        waitForInvisibilityLoadingAnimation();
    }
    @Step("Клик по ссылке 'Войти'")
    public void clickOnLoginLink(){
        driver.findElement(loginLink).click();
        waitForInvisibilityLoadingAnimation();
    }
    @Step("Восстановление пароля")
    public void recoverPassword(String email){
        setEmail(email);
        clickOnRecoverButton();
    }
    @Step("Ждем, когда загрузится страница полностью, исчезнет анимация")
    public  void waitForInvisibilityLoadingAnimation(){
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.invisibilityOfElementLocated
                        (By.xpath(".//img[@src='./static/media/loading.89540200.svg' and @alt='loading animation']")));
    }
    @Step("Ждем, когда загрузится страница восстановления пароля по тексту заголовка")
    public void waitForLoadedRecoverPassword(){
        new WebDriverWait(driver,3)
                .until(ExpectedConditions.visibilityOfElementLocated(recoverPassword));
    }
}