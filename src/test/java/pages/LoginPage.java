package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private WebDriver driver;

    //Заголовок "Вход"
    public By entrance = By.xpath(".//main/div/h2[text()='Вход']");
    //Поле "Email"
    private By emailField = By.xpath(".//div[@class='input pr-6 pl-6 input_type_text input_size_default']/input[@name='name']");
    //Поле "Пароль"
    private By passwordField = By.xpath(".//div[@class='input pr-6 pl-6 input_type_password input_size_default']/input[@name='Пароль']");
    //Логотип "Stellar Burgers"
    public By logo = By.xpath(".//div/a[@href='/']");
    //Кнопка "Войти"
    private By loginButton = By.xpath(".//button[text()='Войти']");
    //Кнопка "Конструктор"
    private  By constructorButton = By.xpath(".//a/p[text()='Конструктор']");
    //Ссылка "Зарегистрироваться"
    private By registerLink = By.xpath(".//a[@href='/register' and text()='Зарегистрироваться']");
    //Ссылка "Восстановить пароль"
    private By forgotPasswordLink = By.xpath(".//a[@href='/forgot-password' and text()='Восстановить пароль']");


    public LoginPage(WebDriver driver){
        this.driver = driver;
    }


    @Step("Ввод Email-а")
    public void setEmail(String email){
        driver.findElement(emailField).sendKeys(email);
    }
    @Step("Ввод пароля")
    public void setPassword(String password){
        driver.findElement(passwordField).sendKeys(password);
    }
    @Step("Клик по кнопке 'Войти'")
    public void clickOnLoginButton(){
        driver.findElement(loginButton).click();
        waitForInvisibilityLoadingAnimation();
    }
    @Step("Клик по ссылке 'Зарегистрироваться'")
    public void clickOnRegister(){
        driver.findElement(registerLink).click();
        waitForInvisibilityLoadingAnimation();
    }
    @Step("Клик по ссылке 'Восстановить пароль'")
    public void clickOnForgotPasswordLink(){
        driver.findElement(forgotPasswordLink).click();
        waitForInvisibilityLoadingAnimation();
    }
    @Step("Клик по кнопке 'Конструктор'")
    public void clickOnConstructorButton(){
        driver.findElement(constructorButton).click();
        waitForInvisibilityLoadingAnimation();
    }
    @Step("Клик по кнопке 'Stellar Burgers'")
    public void clickOnLogo() {
        driver.findElement(logo).click();
        waitForInvisibilityLoadingAnimation();
    }
    @Step("Авторизация пользователя")
    public void authorization(String email, String password){
        setEmail(email);
        setPassword(password);
        clickOnLoginButton();
    }
    @Step("Ждем загрузки элемента с текстом 'Вход'")
    public void waitForLoadEntrance(){
        // подожди 3 секунды, чтобы элемент с нужным текстом стал видимым
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(entrance));
    }
    @Step("Ждем, когда загрузится страница полностью, исчезнет анимация")
    public  void waitForInvisibilityLoadingAnimation() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.invisibilityOfElementLocated
                        (By.xpath(".//img[@src='./static/media/loading.89540200.svg' and @alt='loading animation']")));
    }




}