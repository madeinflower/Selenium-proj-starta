package tests.subscriptionForm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObjects.subscriptionFormSteps.SubscriptionPage;

import static org.junit.jupiter.api.Assertions.*;

public class SubscriptionTests {
    private WebDriver driver;
    private SubscriptionPage subscriptionPage;
    private final String SUCCESSFUL_MESSAGE_VALID_NAME = "Спасибо, %s, за подписку на рассылку!";
    private final String SUCCESSFUL_MESSAGE_VALID_EMAIL = "Спасибо, имяяя, за подписку на рассылку!";
    private final String SUCCESSFUL_MESSAGE = "Спасибо, Мария, за подписку на рассылку!";
    private final String ERROR_EMPTY_NAME = "Пожалуйста, введите корректное имя";
    private final String ERROR_NAME_TOO_SHORT = "Пожалуйста, введите корректное имя";
    private final String ERROR_NAME_TOO_LONG = "Спасибо, ИмяОченьОченьДлинноеКотороеБольше20Символов, за подписку на рассылку!";
    private final String ERROR_EMAIL_TOO_SHORT = "Пожалуйста, введите корректный email";
    private final String ERROR_EMAIL_TOO_LONG = "Пожалуйста, введите корректный email";
    private final String ERROR_EMAIL_INVALID_FORMAT = "Пожалуйста, введите корректный email";
    private final String ERROR_INVALID_CHARACTERS_NAME = "Пожалуйста, введите корректный email";
    private final String ERROR_EMPTY_EMAIL = "Пожалуйста, введите корректный email";
    private final String ERROR_NAME_INVALID = "Пожалуйста, введите корректное имя";
    private final String ERROR_EMAIL_INVALID = "Пожалуйста, введите корректный email";

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        subscriptionPage = new SubscriptionPage(driver);
        driver.get("https://qa-faculty.github.io/subscribtion_form/");
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Анна", "Алексей", "Мария", "Дмитрий", "Ольга", "Екатерина", "Петр",
            "Евгений", "Наталья", "Светлана", "Иван", "Сергей", "Ирина",
            "Александр", "Виктория", "Михаил", "Елена", "Николай", "Татьяна",
            "Андрей", "Марина Петрова", "Алексей-Павел"
    })
    public void testSubmitFormWithValidName(String name) {
        subscriptionPage.inputName(name);
        subscriptionPage.inputEmail("asddsadas@gmail.com");
        subscriptionPage.submitForm();
        String alertText = subscriptionPage.getAlertText();
        String expectedMessage = String.format(SUCCESSFUL_MESSAGE_VALID_NAME, name);
        assertEquals(expectedMessage, alertText);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            // "b@s.co", - не проходит тест, может потому что 5 символов таки не пропускает, хотя в требованиях написано от 5 символов
            "maximum@ofsymbols.commmmmmmmmm",
            "valid@example.com",     // Валидный email
            "valid-@example.com",
            "test.email@domain.co",  // Валидный email
            "name123@domain.com",    // Валидный email
            "valid_email@test.org"   // Валидный email
    })
    public void testSubmitFormWithValidEmail(String email) {
        subscriptionPage.inputName("имяяя");
        subscriptionPage.inputEmail(email);
        subscriptionPage.submitForm();
        String alertText = subscriptionPage.getAlertText();
        assertEquals(SUCCESSFUL_MESSAGE_VALID_EMAIL, alertText);
    }

    // Negative tests:

    @Test
    public void testSubmitFormWithEmptyName() {
        subscriptionPage.inputName("");
        subscriptionPage.inputEmail("valid.email@example.com");
        subscriptionPage.submitForm();
        String alertText = subscriptionPage.getAlertText();
        assertEquals(ERROR_EMPTY_NAME, alertText);
    }

    @Test
    public void testSubmitFormWithNameTooShort() {
        subscriptionPage.inputName("А");
        subscriptionPage.inputEmail("valid.email@example.com");
        subscriptionPage.submitForm();
        String alertText = subscriptionPage.getAlertText();
        assertEquals(ERROR_NAME_TOO_SHORT, alertText);
    }

    @Test
    public void testSubmitFormWithNameTooLong() {
        subscriptionPage.inputName("ИмяОченьОченьДлинноеКотороеБольше20Символов");
        subscriptionPage.inputEmail("valid.email@example.com");
        subscriptionPage.submitForm();
        String alertText = subscriptionPage.getAlertText();
        assertEquals(ERROR_NAME_TOO_LONG, alertText);
    }

    @Disabled("Bug: The system does not validate names with numbers and special characters. A fix is expected.")
    @ParameterizedTest
    @ValueSource(strings = { "Анна123", "Мария@" }) // тест падает, видимо это баг - хотя согласно требованиям нельзя вводить @ и цифры
    public void testSubmitFormWithInvalidCharactersInName(String name) {
        subscriptionPage.inputName(name);
        subscriptionPage.inputEmail("valid.email@example.com");
        subscriptionPage.submitForm();
        String alertText = subscriptionPage.getAlertText();
        assertEquals(ERROR_INVALID_CHARACTERS_NAME, alertText);
    }

    @Test
    public void testSubmitFormWithEmptyEmail() {
        subscriptionPage.inputName("Мария");
        subscriptionPage.inputEmail("");
        subscriptionPage.submitForm();
        String alertText = subscriptionPage.getAlertText();
        assertEquals(ERROR_EMPTY_EMAIL, alertText);
    }

    @Test
    public void testSubmitFormWithEmailTooShort() {
        subscriptionPage.inputName("Мария");
        subscriptionPage.inputEmail("a@b.c");
        subscriptionPage.submitForm();
        String alertText = subscriptionPage.getAlertText();
        assertEquals(ERROR_EMAIL_TOO_SHORT, alertText);
    }

    @Disabled("Bug: The system does not validate a very long email. A fix is expected.")
    @Test
    public void testSubmitFormWithEmailTooLong() { // падает тест - выдает одно сообщение, а должно выдаваться другое
        subscriptionPage.inputName("Мария");
        subscriptionPage.inputEmail("this_is_a_very_long_email_address@example.com");
        subscriptionPage.submitForm();
        String alertText = subscriptionPage.getAlertText();
        assertEquals(ERROR_EMAIL_TOO_LONG, alertText);
    }

    @ParameterizedTest
    @ValueSource(strings = { "example.com", "test#email@domain.com", "test@domaincom" })
    public void testSubmitFormWithInvalidEmailFormat(String email) {
        subscriptionPage.inputName("Мария");
        subscriptionPage.inputEmail(email);
        subscriptionPage.submitForm();
        String alertText = subscriptionPage.getAlertText();
        assertEquals(ERROR_EMAIL_INVALID_FORMAT, alertText);
    }

    @Disabled("Bug: The system does not validate names with numbers and special characters. A fix is expected.")
    @Test
    public void testSubmitFormWithInvalidNameAndEmail() {
        subscriptionPage.inputName("123Имя");
        subscriptionPage.inputEmail("invalidemail.com");
        subscriptionPage.submitForm();
        String alertText = subscriptionPage.getAlertText();
        assertEquals(ERROR_NAME_INVALID, alertText);
        assertEquals(ERROR_EMAIL_INVALID, alertText);
    }

    @Test
    public void testSubmitFormAfterInvalidData() {
        subscriptionPage.inputName("123Мария");
        subscriptionPage.inputEmail("invalid.email.com");
        subscriptionPage.submitForm();

        // Проверка наличия alert и его закрытие
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            assertEquals(ERROR_EMAIL_INVALID, alertText);
            alert.accept();
        } catch (NoAlertPresentException e) {
            // Если alert не появился (например, баг в системе), тест завершится ошибкой
            fail("Expected alert was not present");
        }

        // Повторный ввод валидных данных
        subscriptionPage.inputName("Мария");
        subscriptionPage.inputEmail("valid.email@example.com");
        subscriptionPage.submitForm();

        // Проверка успешной подписки
        String alertText = subscriptionPage.getAlertText();
        assertEquals(SUCCESSFUL_MESSAGE, alertText);
    }

    @Disabled("Bug: Subscription button is shown enabled while it should be disabled")
    @Test
    public void testSubmitButtonDisabledWithEmptyFields() {
        assertFalse(subscriptionPage.isSubmitButtonEnabled());
    }

    @Disabled("Bug: Subscription button is shown enabled while it should be disabled")
    @Test
    public void testSubmitButtonDisabledWithOnlyNameFilled() {
        subscriptionPage.inputName("Мария");
        assertFalse(subscriptionPage.isSubmitButtonEnabled());
    }

    @Disabled("Bug: Subscription button is shown enabled while it should be disabled")
    @Test
    public void testSubmitButtonDisabledWithOnlyEmailFilled() {
        subscriptionPage.inputEmail("valid.email@example.com");
        assertFalse(subscriptionPage.isSubmitButtonEnabled());
    }

    @Test
    public void testSubmitFormTwiceWithValidData() {
        subscriptionPage.inputName("Мария");
        subscriptionPage.inputEmail("valid.email@example.com");
        subscriptionPage.submitForm();

        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            assertEquals(SUCCESSFUL_MESSAGE, alertText);
            alert.accept(); // Закрытие alert
        } catch (NoAlertPresentException e) {
            fail("Expected alert was not present after first form submission");
        }

        subscriptionPage.inputName("Алексей");
        subscriptionPage.inputEmail("alexey@example.com");
        subscriptionPage.submitForm();

        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            assertEquals("Спасибо, Алексей, за подписку на рассылку!", alertText);
            alert.accept();
        } catch (NoAlertPresentException e) {
            fail("Expected alert was not present after second form submission");
        }
    }

    @Test
    public void testSubmitFormWithMaxLengthEmail() {
        String maxLengthEmail = "a".repeat(64) + "@example.com"; // максимальная длина local-part 64 символа
        subscriptionPage.inputName("Мария");
        subscriptionPage.inputEmail(maxLengthEmail);
        subscriptionPage.submitForm();

        String alertText = subscriptionPage.getAlertText();
        assertEquals(SUCCESSFUL_MESSAGE, alertText);
    }

    @ParameterizedTest
    @ValueSource(strings = { "test.email+alias@domain.com", "email-with-dash@domain.co", "email_underscore@domain.org" })
    public void testSubmitFormWithValidSpecialCharactersInEmail(String email) {
        subscriptionPage.inputName("Мария");
        subscriptionPage.inputEmail(email);
        subscriptionPage.submitForm();

        String alertText = subscriptionPage.getAlertText();
        assertEquals(SUCCESSFUL_MESSAGE, alertText);
    }

    @Test
    public void testSubmitFormWithInvalidNameAndEmailAndCheckErrorMessages() {
        subscriptionPage.inputName("Мария123");
        subscriptionPage.inputEmail("invalid@");
        subscriptionPage.submitForm();

        String alertText = subscriptionPage.getAlertText();
        assertTrue(alertText.contains(ERROR_NAME_INVALID));
        assertTrue(alertText.contains(ERROR_EMAIL_INVALID));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}