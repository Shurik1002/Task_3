import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.WebDriver;
import pageobject.*;
import pojo.User;
import steps.UserSteps;import static driver.WebDriverCreator.createWebDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;


@Execution(ExecutionMode.CONCURRENT)
@DisplayName("Функциональность: авторизация пользователя")
public class LoginTest extends BaseBeforeAll {

    private static String accessToken;
    private static String email;
    private static String password;
    private static final String NAME = "Sasha";
    private static final String BASE_URI = "https://qa-stellarburgers.education-services.ru";
    private static final String FORGOT_PASSWORD_URL = "https://qa-stellarburgers.education-services.ru/forgot-password";
    private static final String REGISTER_URL = "https://qa-stellarburgers.education-services.ru/register";
    private static final String TEXT = "В этом разделе вы можете изменить свои персональные данные";

    private static UserSteps userSteps = new UserSteps();
    private HomePage homePage;
    private WebDriver driver;
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private ForgotPasswordPage forgotPasswordPage;
    private Header header;
    private ProfilePage profilePage;

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
        registerPage = new RegisterPage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);
        header = new Header(driver);
        profilePage = new ProfilePage(driver);

    }

    @Test
    @DisplayName("Авторизация пользователя: через Личный кабинет")
    public void loginFromThePersonalAccount() {
        driver.get(BASE_URI);
        header.clickPersonalAccount();
        loginPage.setEmail(email);
        loginPage.setPassword(password);
        loginPage.clickLoginButton();
        header.clickPersonalAccount();
        assertEquals(TEXT, profilePage.getInfoText());

    }

    @Test
    @DisplayName("Авторизация пользователя: через кнопку 'Войти в аккаунт'")
    public void loginFromTheLoginButton() {
        driver.get(BASE_URI);
        homePage.clickLoginButton();
        loginPage.setEmail(email);
        loginPage.setPassword(password);
        loginPage.clickLoginButton();
        header.clickPersonalAccount();
        assertEquals(TEXT, profilePage.getInfoText());

    }

    @Test
    @DisplayName("Авторизация пользователя: через форму регистрации")
    public void loginFromTheRegisterPage() {
        driver.get(REGISTER_URL);
        registerPage.clickLoginButton();
        loginPage.setEmail(email);
        loginPage.setPassword(password);
        loginPage.clickLoginButton();
        header.clickPersonalAccount();
        assertEquals(TEXT, profilePage.getInfoText());

    }

    @Test
    @DisplayName("Авторизация пользователя: через форму восстановления пароля")
    public void loginFromTheForgotPasswordPage() {
        driver.get(FORGOT_PASSWORD_URL);
        forgotPasswordPage.clickLoginButton();
        loginPage.setEmail(email);
        loginPage.setPassword(password);
        loginPage.clickLoginButton();
        header.clickPersonalAccount();
        assertEquals(TEXT, profilePage.getInfoText());

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
