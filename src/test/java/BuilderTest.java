import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.WebDriver;
import pageobject.*;
import pojo.User;
import steps.UserSteps;
import static driver.WebDriverCreator.createWebDriver;

@Execution(ExecutionMode.CONCURRENT)
@DisplayName("Функциональность: конструктор")
public class BuilderTest {

    private static String accessToken;
    private static String email;
    private static String password;
    private static final String NAME = "Sasha";
    private static final String BASE_URI = "https://qa-stellarburgers.education-services.ru";

    private static UserSteps userSteps = new UserSteps();
    private HomePage homePage;
    private WebDriver driver;
    private LoginPage loginPage;
    private Header header;

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = BASE_URI;
        email = userSteps.createEmail();
        password = userSteps.createPassword();
        Response response = userSteps.userRegister(new User(email, password, NAME));
        accessToken = userSteps.getToken(response);

    }

    @BeforeEach
    public void setUpTest() {
        driver = createWebDriver();
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        header = new Header(driver);
        driver.get(BASE_URI);

    }

    @Test
    @DisplayName("Переход на главную страницу через логотип: переключение меню кликом")
    public void redirectOnHomePageAfterClickLogoAndClickMenu() {
        homePage.clickLoginButton();
        loginPage.setEmail(email);
        loginPage.setPassword(password);
        loginPage.clickLoginButton();
        header.clickPersonalAccount();
        header.clickLogo();
        homePage.checkBunSelected();
        homePage.clickSauseButtonAndCheckNavigation();
        homePage.clickFillingsButtonAndCheckNavigation();
        homePage.clickBunButtonAndCheckNavigation();

    }

    @Test
    @DisplayName("Переход на главную страницу через кнопку 'Конструктор': пролистывание меню скроллом")
    public void redirectOnHomePageAfterClickConstructorAndScrollMenu() {
        homePage.clickLoginButton();
        loginPage.setEmail(email);
        loginPage.setPassword(password);
        loginPage.clickLoginButton();
        header.clickPersonalAccount();
        header.clickConstructor();
        homePage.scrollToFillings();
        homePage.scrollToSauce();
        homePage.scrollToBuns();

    }


    @AfterEach
    public void tearDownTest() {
        driver.quit();
    }

    @AfterAll
    public static void tearDown() {
        userSteps.deleteUser(accessToken);
    }
}
