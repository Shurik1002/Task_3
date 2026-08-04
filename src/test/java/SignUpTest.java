import io.restassured.RestAssured;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import pageobject.LoginPage;
import pageobject.RegisterPage;
import steps.UserSteps;
import java.util.stream.Stream;
import static driver.WebDriverCreator.createWebDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Execution(ExecutionMode.CONCURRENT)
@DisplayName("Функциональность: регистрация пользователя")
public class SignUpTest {

    private static final String NAME = "Sasha";
    private static final String EMAIL1 = "shurikOk105@yandex.ru";
    private static final String EMAIL2 = "moshka15_91@yandex.ru";
    private static final String PASSWORD_6 = "1f3kk8";
    private static final String PASSWORD_15 = "1f_3kk87Qu$k18A";
    private static final String PASSWORD_5 = "1f3kk";

    private WebDriver driver;
    private RegisterPage registerPage;
    private LoginPage loginPage;
    private static UserSteps userSteps = new UserSteps();

    private static Stream<Arguments> registerData() {
        return Stream.of(
                Arguments.of("пароль 6 символов", EMAIL1, PASSWORD_6),
                Arguments.of("пароль 15 символов", EMAIL2, PASSWORD_15)
        );
    }


    @BeforeEach()
    public void setUp() {
        driver = createWebDriver();
        driver.get("https://qa-stellarburgers.education-services.ru/register");
        registerPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver);
    }



    @ParameterizedTest(name = "{0}")
    @MethodSource("registerData")
    @DisplayName("Регистрация пользователя: ")
    public void registerSuccess(String testName, String email, String password) {

        registerPage.setName(NAME);
        registerPage.setEmail(email);
        registerPage.setPassword(password);
        registerPage.clickSignUpButton();
        loginPage.checkLoginPageFinishedLoaded();

    }

    @Test
    @DisplayName("Регистрация пользователя: некорректный пароль")
    public void registerWithIncorrectPasswordError() {
        registerPage.setName(NAME);
        registerPage.setEmail(EMAIL1);
        registerPage.setPassword(PASSWORD_5);
        registerPage.clickSignUpButton();
        registerPage.checkErrorMessage();
    }

    @AfterEach
    public void tearDownTests() {
        driver.quit();
    }

    @AfterAll
    public static void tearDown() {
        RestAssured.baseURI = "https://qa-stellarburgers.education-services.ru";
        userSteps.deleteTestUser(EMAIL1, PASSWORD_6);
        userSteps.deleteTestUser(EMAIL2, PASSWORD_15);

    }

}
