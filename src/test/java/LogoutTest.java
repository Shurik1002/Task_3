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
@DisplayName("Функциональность: выход из системы")
public class LogoutTest extends BaseBeforeAll {

    private static String accessToken;
    private static String email;
    private static String password;
    private static final String NAME = "Sasha";
    private static final String BASE_URI = "https://qa-stellarburgers.education-services.ru";
    private WebDriver driver;
    private HomePage homePage;
    private ProfilePage profilePage;
    private LoginPage loginPage;
    private Header header;
    private static UserSteps userSteps = new UserSteps();

    @BeforeAll
    public static void setUp() {
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
        profilePage = new ProfilePage(driver);
        driver.get(BASE_URI);

    }

    @Test
    @DisplayName("Выход из системы")
    public void logoutSuccess() {
        header.clickPersonalAccount();;
        loginPage.setEmail(email);
        loginPage.setPassword(password);
        loginPage.clickLoginButton();
        header.clickPersonalAccount();
        profilePage.waitLogOutButton();
        profilePage.clickLogOutButton();
        loginPage.checkLoginPageFinishedLoaded();
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
