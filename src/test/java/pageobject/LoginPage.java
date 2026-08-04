package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;

    private By emailInput = By.xpath(".//fieldset[1]//input");
    private By passwordInput = By.xpath(".//fieldset[2]//input");
    private By loginButton = By.xpath(".//button[text()='Войти']");
    private By LoginHeadline = By.xpath(".//h2[text()='Вход']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Проверяю переход на страницу авторизации")
    public void checkLoginPageFinishedLoaded() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(LoginHeadline));
    }

    @Step("Ввожу email")
    public void setEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    @Step("Ввожу пароль")
    public void setPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    @Step("Кликаю по кнопке 'Войти'")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }


}
