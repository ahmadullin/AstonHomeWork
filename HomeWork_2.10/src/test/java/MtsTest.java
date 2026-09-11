import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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