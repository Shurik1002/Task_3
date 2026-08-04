package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {

    private WebDriver driver;

    private By nameInput = By.xpath(".//fieldset[1]//input");
    private By emailInput = By.xpath(".//fieldset[2]//input");
    private By passwordInput = By.xpath(".//fieldset[3]//input");
    private By signUpButton = By.xpath(".//button[text()='Зарегистрироваться']");
    private By errorMessage = By.xpath(".//p[text()='Некорректный пароль']");
    private By loginButton = By.className("Auth_link__1fOlj");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ввожу имя")
    public void setName(String name) {
        driver.findElement(nameInput).sendKeys(name);
    }

    @Step("Ввожу email")
    public void setEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    @Step("Ввожу пароль")
    public void setPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    @Step("Кликаю по кнопке 'Зарегистрироваться'")
    public void clickSignUpButton() {
        driver.findElement(signUpButton).click();
    }

    @Step("Проверяю валидационное сообщение о некорректном пароле")
    public void checkErrorMessage() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.textToBePresentInElementLocated(
                        errorMessage,
                        "Некорректный пароль"
                ));
    }

    @Step("Кликаю по кнопке 'Войти' на странице регистрации")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

}
