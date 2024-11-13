package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AboutTenantPage extends BasePage {

    //Название страницы
    private final By titleHeader = By.className("Order_Header__BZXOb");

    //Инпут Имя
    private final By nameInput = By.xpath(".//input[@placeholder='* Имя']");

    //Инпут Фамилия
    private final By secondNameInput = By.xpath(".//input[@placeholder='* Фамилия']");

    //Инпут Адрес доставки
    private final By addressInput = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");

    //Выпадающий список станция
    private final By stationInput = By.className("select-search__input");

    //Инпут Номер телефона
    private final By telephoneNumberInput = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");

    //Кнопка Далее
    private final By furtherButton = By.xpath(".//button[text()='Далее']");

    public AboutTenantPage(WebDriver driver) {
        super(driver);
    }


    //Метод ожидает загрузку заголовка "Для кого самокат"
    public void waitForLoadTitleHeader() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(driver -> (driver.findElement(titleHeader).getText() != null));
    }

    //Метод выбирает станцию метро из выпадающего списка
    public void selectStation(String nameStation) {
        driver.findElement(stationInput).click();
        driver.findElement(By.xpath(".//div[text()='" + nameStation + "']")).click();
    }

    //Метод заполняет данные заказа
    public void setOrderDetails(String name, String secondName, String address, String nameStation, String telephoneNumber) {
        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(secondNameInput).sendKeys(secondName);
        driver.findElement(addressInput).sendKeys(address);
        selectStation(nameStation);
        driver.findElement(telephoneNumberInput).sendKeys(telephoneNumber);
    }

    //Метод нажимает кнопку Далее
    public void clickFurtherButton() {
        driver.findElement(furtherButton).click();
    }

}
