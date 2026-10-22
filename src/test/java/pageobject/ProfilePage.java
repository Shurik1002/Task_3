package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {

    WebDriver driver;

    private By logOutButton = By.xpath(".//button[text()='Выход']");
    private By infoText = By.cssSelector(".Account_text__fZAIn");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Получаю текст в личном кабинете")
    public String getInfoText() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(infoText));
        return driver.findElement(infoText).getText();
    }

    @Step("Жду появения кнопки 'Выход'")
    public void waitLogOutButton() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(logOutButton));
    }

    @Step("Кликаю кнопку 'Выход'")
    public void clickLogOutButton() {
        driver.findElement(logOutButton).click();
    }

}
