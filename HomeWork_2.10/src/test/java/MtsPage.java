import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MtsPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public MtsPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    // ===== ЛОКАТОРЫ (взяты из DevTools www.mts.by) =====

    // Заголовок «Онлайн пополнение без комиссии»
    private final By blockTitle = By.xpath(
            "//h2[contains(text(),'Онлайн пополнение')]");

    // Логотипы платёжных систем
    private final By paymentLogos = By.xpath(
            "//img[contains(@src,'visa') or contains(@src,'mastercard') or " +
                    "contains(@src,'belkart') or contains(@alt,'Visa') or " +
                    "contains(@alt,'MasterCard') or contains(@alt,'Белкарт') or " +
                    "contains(@alt,'БЕЛКАРТ')]");

    // Ссылка «Подробнее о сервисе»
    private final By moreInfoLink = By.xpath(
            "//a[contains(text(),'Подробнее о сервисе')]");

    // Вкладка «Услуги связи» — это выпадающий список (select)
    // На скриншоте видно поле с текстом «Услуги связи» — это селект
    private final By servicesTab = By.xpath(
            "//div[contains(@class,'select')] | " +
                    "//*[contains(text(),'Услуги связи')]");

    // Поле «Номер телефона» — ТОЧНЫЙ ID из скриншота
    private final By phoneInput = By.id("connection-phone");

    // Поле «Сумма» — рядом с phone, ищем по placeholder
    // На скриншоте placeholder «Сумма» — с большой буквы
    private final By sumInput = By.xpath(
            "//input[contains(@placeholder,'умма')] | " +
                    "//input[contains(@id,'sum')] | " +
                    "//input[contains(@name,'sum')]");

    // Кнопка «Продолжить» — внутри формы pay-connection
    private final By continueButton = By.xpath(
            "//form[@id='pay-connection']//button[contains(text(),'Продолжить')] | " +
                    "//button[contains(text(),'Продолжить')]");

    // Куки-баннер
    private final By cookieBanner = By.xpath(
            "//button[contains(text(),'Принять') or contains(text(),'Согласен') or contains(text(),'Соглашаюсь')]");
    // ===== ДЕЙСТВИЯ =====

    public void closeCookieBannerIfPresent() {
        try {
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(cookieBanner));
            button.click();
        } catch (Exception e) {
            // Баннера нет — игнорируем
        }
    }

    public WebElement getBlockTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(blockTitle));
    }

    public List<WebElement> getPaymentLogos() {
        wait.until(ExpectedConditions.presenceOfElementLocated(paymentLogos));
        return driver.findElements(paymentLogos);
    }

    public WebElement getMoreInfoLink() {
        return wait.until(ExpectedConditions.elementToBeClickable(moreInfoLink));
    }

    public void clickServicesTab() {
        wait.until(ExpectedConditions.elementToBeClickable(servicesTab)).click();
    }

    public WebElement getPhoneInput() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(phoneInput));
    }

    public WebElement getSumInput() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(sumInput));
    }

    public WebElement getContinueButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(continueButton));
    }
}