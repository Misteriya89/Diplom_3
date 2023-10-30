package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage {

    private WebDriver driver;

    // Кнопка "Конструктор"
    private By constructorButton = By.xpath(".//a[@href='/']/p[text()='Конструктор']");
    // Кнопка "Выход"
    private By exitButton = By.xpath(".//li/button[text()='Выход']");
    // Проверочная надпись для перехода в Личный кабинет
    public By textOnProfilePage = By.xpath(".//nav/p[text()='В этом разделе вы можете изменить свои персональные данные']");


    public ProfilePage(WebDriver driver){
        this.driver = driver;
    }


    @Step("Клик по кнопке 'Конструктор'")
    public void clickOnConstructorButton(){
        driver.findElement(constructorButton).click();
        waitForInvisibilityLoadingAnimation();
    }
    @Step("Клик по кнопке выйти")
    public void clickOnExitButton(){
        driver.findElement(exitButton).click();
        waitForInvisibilityLoadingAnimation();
    }
    @Step("Ждем, когда загрузится страница личного кабинета с текстом изменения персональных данных")
    public void waitForLoadProfilePage(){
        // подожди 3 секунды, чтобы элемент с нужным текстом стал видимым
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(textOnProfilePage));
    }
    @Step("Ждем, когда загрузится страница полностью, исчезнет анимация")
    public  void waitForInvisibilityLoadingAnimation() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.invisibilityOfElementLocated
                        (By.xpath(".//img[@src='./static/media/loading.89540200.svg' and @alt='loading animation']")));
    }

}