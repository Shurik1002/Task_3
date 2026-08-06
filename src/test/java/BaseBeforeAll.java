import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class BaseBeforeAll {

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "https://qa-stellarburgers.education-services.ru";
    }

}
