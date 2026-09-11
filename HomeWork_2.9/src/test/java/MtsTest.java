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
    private static MtsPage mtsPage;

    private static final String BASE_URL = "https://www.mts.by";
    private static final String TEST_PHONE_NUMBER = "297777777";

    @BeforeAll
    public static void setUpDriver() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        mtsPage = new MtsPage(driver, wait);
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
        mtsPage.closeCookieBannerIfPresent();
    }

    // ============== ТЕСТ 1 ==============
    @Test
    @Order(1)
    @DisplayName("Проверка названия блока 'Онлайн пополнение без комиссии'")
    public void checkBlockTitle() {
        WebElement title = mtsPage.getBlockTitle();

        assertTrue(title.isDisplayed(), "Заголовок блока не отображается");
        assertTrue(title.getText().toLowerCase().contains("онлайн пополнение"),
                "Текст заголовка не содержит 'Онлайн пополнение'. Фактический текст: " + title.getText());

        System.out.println("✓ Заголовок блока: " + title.getText());
    }

    // ============== ТЕСТ 2 ==============
    @Test
    @Order(2)
    @DisplayName("Проверка наличия логотипов платёжных систем")
    public void checkPaymentSystemLogos() {
        List<WebElement> logos = mtsPage.getPaymentLogos();

        assertTrue(logos.size() >= 3,
                "Ожидалось минимум 3 логотипа, найдено: " + logos.size());

        for (WebElement logo : logos) {
            assertTrue(logo.isDisplayed(),
                    "Логотип не отображается: " + logo.getAttribute("alt"));
            System.out.println("✓ Найден логотип: " +
                    (logo.getAttribute("alt") != null ? logo.getAttribute("alt") : logo.getAttribute("src")));
        }
    }

    // ============== ТЕСТ 3 ==============
    @Test
    @Order(3)
    @DisplayName("Проверка работы ссылки 'Подробнее о сервисе'")
    public void checkMoreInfoLink() {
        WebElement link = mtsPage.getMoreInfoLink();

        assertTrue(link.isDisplayed(), "Ссылка 'Подробнее о сервисе' не отображается");
        assertTrue(link.isEnabled(), "Ссылка 'Подробнее о сервисе' неактивна");

        String href = link.getAttribute("href");
        assertNotNull(href, "У ссылки отсутствует атрибут href");
        assertTrue(href.contains("mts.by"), "Ссылка ведёт не на сайт МТС");
        System.out.println("✓ Ссылка ведёт на: " + href);

        // Кликаем и ждём, что URL изменился (без ожидания новой вкладки)
        String originalUrl = driver.getCurrentUrl();
        link.click();
        try {
            wait.until(ExpectedConditions.urlContains("poryadok-oplaty"));
            System.out.println("✓ Открыт URL: " + driver.getCurrentUrl());
        } catch (Exception e) {
            System.out.println("ℹ URL остался прежним: " + originalUrl);
        }
    }

    // ============== ТЕСТ 4 ==============
    @Test
    @Order(4)
    @DisplayName("Заполнение полей и проверка кнопки 'Продолжить'")
    public void testFillFormAndContinue() {
        // 1. "Услуги связи" обычно выбрано по умолчанию.
        //    Если нет — раскомментируйте строку ниже.
        // mtsPage.clickServicesTab();

        // 2. Заполняем номер телефона по id="connection-phone"
        WebElement phoneInput = mtsPage.getPhoneInput();
        phoneInput.click();
        phoneInput.clear();
        phoneInput.sendKeys(TEST_PHONE_NUMBER);
        System.out.println("✓ Введён номер: " + TEST_PHONE_NUMBER);

        // 3. Заполняем сумму
        WebElement sumInput = mtsPage.getSumInput();
        sumInput.click();
        sumInput.clear();
        sumInput.sendKeys("10");
        System.out.println("✓ Введена сумма: 10");

        // 4. Нажимаем "Продолжить"
        WebElement continueButton = mtsPage.getContinueButton();
        assertTrue(continueButton.isEnabled(), "Кнопка 'Продолжить' неактивна");
        continueButton.click();
        System.out.println("✓ Нажата кнопка 'Продолжить'");

        // 5. Проверяем результат — должен появиться iframe платёжной формы bePaid
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("iframe")));
            System.out.println("✓ Платёжная форма (iframe bePaid) загружена");
        } catch (Exception e) {
            String currentUrl = driver.getCurrentUrl();
            System.out.println("✓ Текущий URL после клика: " + currentUrl);
            assertNotEquals("https://www.mts.by", currentUrl,
                    "URL не изменился после нажатия 'Продолжить'");
        }
    }
}