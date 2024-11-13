package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ConfirmOrderCreationWindow extends BasePage {

    //Заголовок окна подтверждения создания заказа
    private final By titleConfirmWindow = By.className("Order_ModalHeader__3FDaJ");

    //Кнопка подтверждения создания заказа
    private final By confirmButton = By.xpath(".//button[text()='Да']");

    //Заголовок окна при успшном создании заказа
    private final By titleOrderStatus = By.xpath(".//div[(text()= 'Заказ оформлен')][1]");

    public ConfirmOrderCreationWindow(WebDriver driver) {
        super(driver);
    }


    //Метод ожидает загрузку заголовка в модальном окне подтверждения создания заказа
    public void waitForLoadTitleConfirm() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(driver -> (driver.findElement(titleConfirmWindow).getText() != null));
    }

    //Метод проверяет, что заказ создан/ не создан
    public void waitForOrderSuccess(String orderStatus) {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.textToBePresentInElementLocated(titleOrderStatus, orderStatus));

    }

    //Метод нажимает кнопку "Да" в модальном окне подтвержения
    public void clickConfirmButton() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(confirmButton)).click();
    }

    //Метод возвращает текст статуса заказа
    public String statusOrder() {
        return driver.findElement(titleOrderStatus).getText();
    }
}
