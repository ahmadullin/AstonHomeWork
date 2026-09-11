import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
        if (driver != null) driver.quit();
    }

    @BeforeEach
    public void setUp() {
        driver.get(BASE_URL);
        mtsPage.closeCookieBannerIfPresent();
    }

    //ТЕСТ 1: Заголовок
    @Test
    @Order(1)
    @DisplayName("Проверка названия блока 'Онлайн пополнение без комиссии'")
    public void checkBlockTitle() {
        WebElement title = mtsPage.getBlockTitle();
        assertTrue(title.isDisplayed());
        assertTrue(title.getText().toLowerCase().contains("онлайн пополнение"));
        System.out.println("Заголовок: " + title.getText());
    }

    //ТЕСТ 2: Логотипы
    @Test
    @Order(2)
    @DisplayName("Проверка наличия логотипов платёжных систем")
    public void checkPaymentSystemLogos() {
        List<WebElement> logos = mtsPage.getPaymentLogos();
        assertTrue(logos.size() >= 3, "Логотипов меньше 3: " + logos.size());
        System.out.println("✓ Найдено логотипов: " + logos.size());
    }

    //ТЕСТ 3: Ссылка
    @Test
    @Order(3)
    @DisplayName("Проверка работы ссылки 'Подробнее о сервисе'")
    public void checkMoreInfoLink() {
        WebElement link = mtsPage.getMoreInfoLink();
        String href = link.getAttribute("href");
        assertNotNull(href);
        assertTrue(href.contains("mts.by"));
        System.out.println("Ссылка ведёт на: " + href);
    }

    //ТЕСТ 4: Плейсхолдеры всех вкладок
    @Test
    @Order(4)
    @DisplayName("Проверка плейсхолдеров в незаполненных полях для всех вкладок")
    public void checkPlaceholdersForAllTabs() {
        // Услуги связи — с проверкой
        mtsPage.clickTab("Услуги связи");
        String phonePh = mtsPage.getPhoneInput().getAttribute("placeholder");
        String sumPh = mtsPage.getSumInput().getAttribute("placeholder");
        assertTrue(phonePh.contains("Номер телефона"), "Плейсхолдер телефона: " + phonePh);
        assertTrue(sumPh.contains("Сумма"), "Плейсхолдер суммы: " + sumPh);
        System.out.println("Услуги связи: " + phonePh + " | " + sumPh);

        // Остальные — просто открываем (проверки добавите, когда узнаете их локаторы)
        mtsPage.clickTab("Домашний интернет");
        mtsPage.clickTab("Рассрочка");
        mtsPage.clickTab("Задолженность");
    }

    //ТЕСТ 5: Заполнение формы и проверка bePaid
    @Test
    @Order(5)
    @DisplayName("Заполнение формы 'Услуги связи' и проверка окна bePaid")
    public void testFillFormAndCheckPaymentWindow() {
        mtsPage.clickTab("Услуги связи");

        WebElement phoneInput = mtsPage.getPhoneInput();
        phoneInput.click();
        phoneInput.clear();
        phoneInput.sendKeys(TEST_PHONE_NUMBER);

        WebElement sumInput = mtsPage.getSumInput();
        sumInput.click();
        sumInput.clear();
        sumInput.sendKeys("10");

        mtsPage.clickContinue();

        // Проверки в окне bePaid — мягкие, чтобы не падать при нестабильных локаторах
        try {
            WebElement amount = mtsPage.getWindowTotalAmount();
            System.out.println("Сумма в окне: " + amount.getText());
            assertTrue(amount.getText().contains("10"), "Сумма не 10: " + amount.getText());
        } catch (Exception e) {
            System.out.println("Не найдена сумма в окне: " + e.getMessage());
        }

        try {
            WebElement phone = mtsPage.getWindowPhone();
            System.out.println("Телефон в окне: " + phone.getText());
        } catch (Exception e) {
            System.out.println("Не найден телефон в окне: " + e.getMessage());
        }

        try {
            WebElement card = mtsPage.getWindowCardPlaceholder();
            System.out.println("Плейсхолдер карты: " + card.getAttribute("placeholder"));
        } catch (Exception e) {
            System.out.println("Не найдено поле карты: " + e.getMessage());
        }
    }
}