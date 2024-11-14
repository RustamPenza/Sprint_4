package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AboutOrderPage extends BasePage {

    //Название страницы
    private final By titleHeader = By.className("Order_Header__BZXOb");

    //Инпут даты
    private final By dateInput = By.xpath(".//input[@placeholder='* Когда привезти самокат']");

    //Дата в календаре
    private String dayInCalendar = "div.react-datepicker__day--0%d";

    //Инпут срока
    private final By periodInput = By.className("Dropdown-placeholder");

    //Срок аренды 2 суток
    private final By rentalPeriod = By.xpath(".//div[@class='Dropdown-option'][2]");

    //Чекбокс с выпобром цвета самоката Черный жемчуг
    private final By colorCheckbox = By.xpath(".//label[text()='чёрный жемчуг']");

    //Инпут комментария для курьера
    private final By commentInput = By.xpath(".//input[@placeholder='Комментарий для курьера']");

    //Кнопка Заказать для создания заказа
    private final By makeOrderButton = By.xpath(".//button[contains(@class,'Button_Middle__1CSJM') and text()='Заказать']");


    public AboutOrderPage(WebDriver driver) {
        super(driver);
    }


    //Метод ожидает загрузку заголовка "Про аренду"
    public void waitForLoadTitleHeader() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(driver -> (driver.findElement(titleHeader).getText() != null));
    }

    //Метод заполняет дату заказа "30.11.2024"
    public void selectDate(int day) {
        driver.findElement(dateInput).click();
        driver.findElement(By.cssSelector(String.format(dayInCalendar, day))).click();
    }

    //Метод заполняет срок аренды "двое суток"
    public void selectRentalPeriod() {
        driver.findElement(periodInput).click();
        driver.findElement(rentalPeriod).click();
    }

    //Метод заполняет данные об аренде
    public void setRentalInformation(String comment, int day) {
        selectDate(day);
        selectRentalPeriod();
        driver.findElement(colorCheckbox).click();
        driver.findElement(commentInput).sendKeys(comment);
    }

    //Метод нажимает кнопку "Заказать"
    public void clickMakeOrderButton() {
        driver.findElement(makeOrderButton).click();
    }

}
