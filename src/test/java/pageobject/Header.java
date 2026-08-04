package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Header {

    WebDriver driver;

    private By personalAccount = By.xpath(".//p[text()='Личный Кабинет']");
    private By logo = By.className("AppHeader_header__logo__2D0X2");
    private By constructor = By.className("AppHeader_header__link__3D_hX");


    public Header(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Кликаю по кнопке 'Личный кабинет'")
    public void clickPersonalAccount() {
        driver.findElement(personalAccount).click();
    }

    @Step("Кликаю по логотипу")
    public void clickLogo() {
        driver.findElement(logo).click();
    }

    @Step("Кликаю по кнопке 'Конструктор'")
    public void clickConstructor() {
        driver.findElement(constructor).click();
    }



}
