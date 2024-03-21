import org.example.Calculator;
import org.example.Login;
import org.example.Operation;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CalculatorTest {
    public static WebDriver browser;
    Login newUser = new Login("Test03", "Test03");
    Login userNeg = new Login("Test03", "test03"); // Neteisingi vartotojo duomenys, neigiamam prisijungimo testui
    Operation operation = new Operation(22, 2, '*');
    Operation operationNeg = new Operation(10, -1, '+');


    @BeforeTest
    void setup() {
        Calculator.setup();
    }

    @AfterTest
    void close() {
        Calculator.logout();
        Calculator.close();
    }

    @Test(priority = 1)
    void userRegistrationPositive() {
        String actualResult = Calculator.createNewAccount(newUser);
        String expectedResult = "http://localhost:8080/skaiciuotuvas";
        // http://localhost:8080/skaiciuotuvas {teig} arba http://localhost:8080/registruoti (neig)
        Calculator.logout();

        Assert.assertEquals(actualResult, expectedResult, "Naujas vartotojas užregistruotas.");
    }

    @Test(priority = 2)
    void userRegistrationNegative() {
        String actualResult = Calculator.createNewAccount(newUser);
        String expectedResult = "http://localhost:8080/skaiciuotuvas";

        Assert.assertNotEquals(actualResult, expectedResult, "Vartotojas neužregistruotas (tokiu vardu vartotojas jau egzistuoja).");
    }

    @Test(priority = 4)
    void userLoginPositive() {
        String actualResult = Calculator.login(newUser);
        String expectedResult = "http://localhost:8080/";
        // http://localhost:8080/ (teig) http://localhost:8080/prisijungti?error (neig)

        Assert.assertEquals(actualResult, expectedResult, "Vartotojas prisijungė sėkmingai.");
    }

    @Test(priority = 3)
    void userLoginNegative() {
        String actualResult = Calculator.login(userNeg);
        String expectedResult = "http://localhost:8080/";

        Assert.assertNotEquals(actualResult, expectedResult, "Vartotojui prsijungti nepavyko.");
    }

    @Test(priority = 5)
    void createOperationPositive() {
        String actualResult = Calculator.createOperation(operation);
        String expectedResult = "22 * 2 = 44";

        Assert.assertEquals(actualResult, expectedResult, "Operacija sukurta ir atlikta sėkmingai.");
    }

    @Test(priority = 6)
    void createOperationNegative() {
        String actualResult = Calculator.createOperation(operation);
        String expectedResult = "10 + -1 = 9";

        Assert.assertNotEquals(actualResult, expectedResult, "Tokia operacija negalima.");
    }

    @Test(priority = 7)
    void searchOperationPositive() {
        String operationId = Calculator.searchOperation(operation);
        String actualResult = Calculator.actionWithOperation(operationId, "rodyti");
        String expectedResult = "http://localhost:8080/rodyti?id=" + operationId;

        Assert.assertEquals(actualResult, expectedResult, "Rodomas įrašas, kurio id =" + operationId);
    }

    @Test(priority = 8)
    void searchOperationNegative() {
        String operationId = Calculator.searchOperation(operationNeg);
        String actualResult = Calculator.actionWithOperation(operationId, "rodyti");
        String expectedResult = "http://localhost:8080/rodyti?id=" + operationId;

        Assert.assertNotEquals(actualResult, expectedResult, "Tokio įrašo nėra");
    }

}

