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

    //ЛОКАТОРЫ

    private final By blockTitle = By.xpath(
            "//h2[contains(text(),'Онлайн пополнение')]");

    private final By paymentLogos = By.xpath(
            "//img[contains(@src,'visa') or contains(@src,'mastercard') or " +
                    "contains(@src,'belkart') or contains(@alt,'Visa') or " +
                    "contains(@alt,'MasterCard') or contains(@alt,'Белкарт')]");

    private final By moreInfoLink = By.xpath(
            "//a[contains(text(),'Подробнее о сервисе')]");

    // ВКЛАДКИ — ищем <a> с вложенным <h3>, содержащим текст
    private final By tabServices = By.xpath(
            "//a[h3[contains(normalize-space(text()),'Услуги связи')]]");
    private final By tabInternet = By.xpath(
            "//a[h3[contains(normalize-space(text()),'Домашний интернет')]]");
    private final By tabInstallment = By.xpath(
            "//a[h3[contains(normalize-space(text()),'Рассрочка')]]");
    private final By tabArrears = By.xpath(
            "//a[h3[contains(normalize-space(text()),'Задолженность')]]");

    private final By phoneInput = By.id("connection-phone");
    private final By sumInput = By.xpath(
            "//input[contains(@placeholder,'умма')] | " +
                    "//input[contains(@id,'sum')]");
    private final By continueButton = By.xpath(
            "//form[@id='pay-connection']//button[contains(text(),'Продолжить')]");

    private final By cookieBanner = By.xpath(
            "//button[contains(text(),'Принять') or contains(text(),'Согласен')]");

    // Элементы в окне bePaid (iframe)
    private final By paymentIframe = By.tagName("iframe");
    private final By windowTotalAmount = By.xpath(
            "//*[contains(text(),'10') and (contains(text(),'BYN') or contains(text(),'руб'))]");
    private final By windowPhone = By.xpath(
            "//*[contains(text(),'297777777')]");
    private final By windowCardPlaceholder = By.xpath(
            "//input[contains(@placeholder,'Номер карты') or contains(@placeholder,'номер карты')]");

    // МЕТОДЫ

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

    public void clickTab(String tabName) {
        By locator;
        switch (tabName) {
            case "Услуги связи":     locator = tabServices; break;
            case "Домашний интернет": locator = tabInternet; break;
            case "Рассрочка":        locator = tabInstallment; break;
            case "Задолженность":    locator = tabArrears; break;
            default: throw new IllegalArgumentException("Неизвестная вкладка: " + tabName);
        }
        try {
            WebElement tab = wait.until(ExpectedConditions.elementToBeClickable(locator));
            tab.click();
            System.out.println("Открыта вкладка: " + tabName);
        } catch (Exception e) {
            System.out.println("Не удалось открыть вкладку '" + tabName + "': " + e.getMessage());
        }
    }

    public WebElement getPhoneInput() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(phoneInput));
    }

    public WebElement getSumInput() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(sumInput));
    }

    public void clickContinue() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(continueButton));
        button.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(paymentIframe));
        System.out.println("Нажата кнопка 'Продолжить', загружено окно bePaid");
    }

    public WebElement getWindowTotalAmount() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(windowTotalAmount));
    }

    public WebElement getWindowPhone() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(windowPhone));
    }

    public WebElement getWindowCardPlaceholder() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(windowCardPlaceholder));
    }
}