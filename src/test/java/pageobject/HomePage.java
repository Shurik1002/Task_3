package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private WebDriver driver;

    private By logInButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private By bunSelected = By.xpath(".//div[contains(@class, 'tab_tab_type_current__2BEPc')]//span[text()='Булки']");
    private By sauseSelected = By.xpath(".//div[contains(@class, 'tab_tab_type_current__2BEPc')]//span[text()='Соусы']");
    private By fillingsSelected = By.xpath(".//div[contains(@class, 'tab_tab_type_current__2BEPc')]//span[text()='Начинки']");
    private By bunButton = By.xpath(".//span[text()='Булки']");
    private By sauseButton = By.xpath(".//span[text()='Соусы']");
    private By fillingsButton = By.xpath(".//span[text()='Начинки']");
    private By menu = By.className("BurgerIngredients_ingredients__menuContainer__Xu3Mo");



    public HomePage(WebDriver driver) {
        this.driver = driver;
    }


    @Step("Кликаю по кнопке 'Войти в аккаунт'")
    public void clickLoginButton() {
        driver.findElement(logInButton).click();
    }

    @Step("Перехожу к разделу Булки")
    public void clickBunButtonAndCheckNavigation() {
        driver.findElement(bunButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(bunSelected));
    }

    @Step("Перехожу к разделу Соусы")
    public void clickSauseButtonAndCheckNavigation() {
        driver.findElement(sauseButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(sauseSelected));
    }

    @Step("Перехожу к разделу Начинки")
    public void clickFillingsButtonAndCheckNavigation() {
        driver.findElement(fillingsButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(fillingsSelected));
    }

    @Step("Проверяю, что выбран раздел Булки")
    public void checkBunSelected() {
        driver.findElement(bunSelected);
    }

    @Step("Скроллю к разделу 'Начинки'")
    public void scrollToFillings() {

        WebElement container = driver.findElement(menu);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollTop += 2000",
                container
        );
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(fillingsSelected));


    }

    @Step("Скроллю к разделу 'Соусы'")
    public void scrollToSauce() {

        WebElement container = driver.findElement(menu);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollTop -= 1300",
                container
        );
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(sauseSelected));


    }

    @Step("Скроллю к разделу 'Булки'")
    public void scrollToBuns() {

        WebElement container = driver.findElement(menu);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollTop -= 350",
                container
        );
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(bunSelected));

    }


}
