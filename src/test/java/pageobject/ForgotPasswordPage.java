package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {

    private WebDriver driver;
    private By loginButton = By.className("Auth_link__1fOlj");

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Кликаю по кнопке 'Войти' на странице восстановления пароля")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }
}
