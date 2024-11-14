package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static pages.constants.LocatorOrderButton.*;

public class MainPage extends BasePage {

    //Заголовок Самокат на пару дней
    private final By titleHeader = By.className("Home_Header__iJKdX");

    // Кнопка "Заказать" в верху страницы
    private final By orderButtonHeader = By.xpath(".//div[@class='Header_Nav__AGCXC']//button[@class='Button_Button__ra12g']");

    // Кнопка "Заказать" в середине страницы
    private final By orderButtonMiddle = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button");

    // Кнопка "Статус заказа"
    private final By dropdownTable = By.className("accordion");

    // пункты выпадающего списка
    private final String dropdownQuestion = ".//div[@class='accordion__button' and text()='%s']";

    // Содержимое раскрытого списка
    private final String dropdownAnswer = "accordion__panel-%d";


    public MainPage(WebDriver driver) {
        super(driver);
    }


    //Метод ожидает загрузку заголовка Самокат на пару дней
    public void waitForLoadTitleHeader() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(driver -> (driver.findElement(titleHeader).getText() != null));
    }

    // Метод кликает на кнопку "Заказать" в зависимости от тестового набора
    public void clickOrderButtonHeader(Enum placeButton) {
        if (placeButton == BUTTON_ORDER_HEADER) {
            driver.findElement(orderButtonHeader).click();
        } else if (placeButton == BUTTON_ORDER_MIDDLE) {
            WebElement element = driver.findElement(orderButtonMiddle);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
            new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(ExpectedConditions.elementToBeClickable(orderButtonMiddle)).click();
        }
    }

    // Метод дожитается когда элемент будет кликабельным и нажимает на вопрос из выпадающего списка
    public void clickQuestion(String question) {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(driver.findElement
                        (By.xpath(String.format(dropdownQuestion, question))))).click();
    }

    //Метод возвращает текст ответа
    public String getAnswerText(int index) {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id(String.format(dropdownAnswer, index))));
        return driver.findElement(By.id(String.format(dropdownAnswer, index))).getText();
    }

    //Метод прокручивает страницу до выпадающего списка
    public void scrollToDropdownList() {
        WebElement element = driver.findElement(dropdownTable);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

}
