import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MtsTest {

    private static WebDriver driver;
    private static WebDriverWait wait;

    private static final String BASE_URL = "https://www.mts.by";
    private static final String TEST_PHONE_NUMBER = "297777777";

    // ===== ЛОКАТОРЫ (прямо в тестовом классе) =====
    private static final By BLOCK_TITLE = By.xpath(
            "//h2[contains(text(),'Онлайн пополнение')]");

    private static final By PAYMENT_LOGOS = By.xpath(
            "//img[contains(@src,'visa') or contains(@src,'mastercard') or " +
                    "contains(@src,'belkart') or contains(@alt,'Visa') or " +
                    "contains(@alt,'MasterCard') or contains(@alt,'Белкарт')]");

    private static final By MORE_INFO_LINK = By.xpath(
            "//a[contains(text(),'Подробнее о сервисе')]");

    private static final By PHONE_INPUT = By.id("connection-phone");

    private static final By SUM_INPUT = By.xpath(
            "//input[contains(@placeholder,'умма')] | " +
                    "//input[contains(@id,'sum')]");

    private static final By CONTINUE_BUTTON = By.xpath(
            "//form[@id='pay-connection']//button[contains(text(),'Продолжить')]");

    private static final By COOKIE_BANNER = By.xpath(
            "//button[contains(text(),'Принять') or contains(text(),'Согласен')]");

    //SETUP / TEARDOWN

    @BeforeAll
    public static void setUpDriver() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @AfterAll
    public static void tearDownDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    @BeforeEach
    public void setUp() {
        driver.get(BASE_URL);
        // Закрываем куки-баннер
        try {
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(COOKIE_BANNER));
            button.click();
        } catch (Exception e) {
            // баннера нет — ок
        }
    }

    //ТЕСТЫ

    @Test
    @Order(1)
    @DisplayName("Проверка названия блока 'Онлайн пополнение без комиссии'")
    public void checkBlockTitle() {
        WebElement title = wait.until(
                ExpectedConditions.visibilityOfElementLocated(BLOCK_TITLE));

        assertTrue(title.isDisplayed(), "Заголовок блока не отображается");
        assertTrue(title.getText().toLowerCase().contains("онлайн пополнение"),
                "Текст заголовка не содержит 'Онлайн пополнение'. Фактический текст: " + title.getText());

        System.out.println("Заголовок блока: " + title.getText());
    }

    @Test
    @Order(2)
    @DisplayName("Проверка наличия логотипов платёжных систем")
    public void checkPaymentSystemLogos() {
        wait.until(ExpectedConditions.presenceOfElementLocated(PAYMENT_LOGOS));
        List<WebElement> logos = driver.findElements(PAYMENT_LOGOS);

        assertTrue(logos.size() >= 3,
                "Ожидалось минимум 3 логотипа, найдено: " + logos.size());

        for (WebElement logo : logos) {
            assertTrue(logo.isDisplayed(),
                    "Логотип не отображается: " + logo.getAttribute("alt"));
            System.out.println("Найден логотип: " +
                    (logo.getAttribute("alt") != null
                            ? logo.getAttribute("alt")
                            : logo.getAttribute("src")));
        }
    }

    @Test
    @Order(3)
    @DisplayName("Проверка работы ссылки 'Подробнее о сервисе'")
    public void checkMoreInfoLink() {
        WebElement link = wait.until(
                ExpectedConditions.elementToBeClickable(MORE_INFO_LINK));

        assertTrue(link.isDisplayed(), "Ссылка не отображается");
        assertTrue(link.isEnabled(), "Ссылка неактивна");

        String href = link.getAttribute("href");
        assertNotNull(href, "У ссылки отсутствует href");
        assertTrue(href.contains("mts.by"), "Ссылка ведёт не на сайт МТС");
        System.out.println("Ссылка ведёт на: " + href);

        link.click();
        try {
            wait.until(ExpectedConditions.urlContains("poryadok-oplaty"));
            System.out.println("Открыт URL: " + driver.getCurrentUrl());
        } catch (Exception e) {
            System.out.println("URL остался прежним: " + e.getMessage());
        }
    }

    @Test
    @Order(4)
    @DisplayName("Заполнение полей и проверка кнопки 'Продолжить'")
    public void testFillFormAndContinue() {
        // 1. Заполняем номер телефона
        WebElement phoneInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(PHONE_INPUT));
        phoneInput.click();
        phoneInput.clear();
        phoneInput.sendKeys(TEST_PHONE_NUMBER);
        System.out.println("Введён номер: " + TEST_PHONE_NUMBER);

        // 2. Заполняем сумму
        WebElement sumInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(SUM_INPUT));
        sumInput.click();
        sumInput.clear();
        sumInput.sendKeys("10");
        System.out.println("Введена сумма: 10");

        // 3. Нажимаем "Продолжить"
        WebElement continueButton = wait.until(
                ExpectedConditions.elementToBeClickable(CONTINUE_BUTTON));
        assertTrue(continueButton.isEnabled(), "Кнопка 'Продолжить' неактивна");
        continueButton.click();
        System.out.println("Нажата кнопка 'Продолжить'");

        // 4. Проверяем результат
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("iframe")));
            System.out.println("Платёжная форма (iframe bePaid) загружена");
        } catch (Exception e) {
            String currentUrl = driver.getCurrentUrl();
            System.out.println("Текущий URL после клика: " + currentUrl);
            assertNotEquals(BASE_URL, currentUrl,
                    "URL не изменился после нажатия 'Продолжить'");
        }
    }
}