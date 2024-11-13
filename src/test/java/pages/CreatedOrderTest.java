package pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.CoreMatchers.startsWith;

@RunWith(Parameterized.class)
public class CreatedOrderTest {
    private WebDriver driver;
    private final String name;
    private final String secondname;
    private final String address;
    private final String nameStation;
    private final String telephoneNumber;
    private final int dayRent;
    private final String comment;
    private final String orderStatus;

    public CreatedOrderTest(String name, String secondname, String address, String nameStation, String telephoneNumber, int dayRent, String comment, String orderStatus) {
        this.name = name;
        this.secondname = secondname;
        this.address = address;
        this.nameStation = nameStation;
        this.telephoneNumber = telephoneNumber;
        this.dayRent = dayRent;
        this.comment = comment;
        this.orderStatus = orderStatus;

    }

    @Before
    public void prepare() {
        WebDriverManager.chromedriver().driverVersion("131.0.6778.70").setup();
        driver = new ChromeDriver();
    }

    @After
    public void teardown() {
        driver.quit();
    }

    @Parameterized.Parameters
    public static Object[][] getOrderDetails() {
        return new Object[][]{
                {"Иван", "Иванов", "Москва, ул. Ломоносова, д. 16, кв.5", "Красносельская"
                        , "89123456789", 12, "Саммокат оставить у подъезда", "Заказ оформлен"},
                {"Петр", "Петров", "Пенза, ул. Московская, д. 60, кв.2", "Красносельская"
                        , "81111111111", 12, "Самокат должен быть чистым", "Заказ не оформлен"},
                {"Ivan", "Ivanov", "Moscow, st. Lomonosova, h. 16. ap.5", "Чистые Пруды"
                        , "79999999999", 25, "Leave the scooter at the entrance", "Заказ оформлен"},

        };
    }

    @Test
    public void makeOrderTest() {
        driver.get("https://qa-scooter.praktikum-services.ru/");

        MainPage objMainPage = new MainPage(driver);
        objMainPage.waitForLoadTitleHeader(); //ждем когда загрузится заголовок на главной странице
        objMainPage.clickOrderButtonHeader(); //Нажимаем кнопку Заказать в шапке сайта

        AboutTenantPage objOrderPage = new AboutTenantPage(driver);
        objOrderPage.waitForLoadTitleHeader(); //ждем когда загрузится заголовок на странице "Для кого самокат"
        objOrderPage.setOrderDetails(name, secondname, address, nameStation, telephoneNumber); //Заполняем поля формы
        objOrderPage.clickFurtherButton(); //Нажимаем кнопку Далее

        AboutOrderPage objAboutOrderPage = new AboutOrderPage(driver);
        objAboutOrderPage.waitForLoadTitleHeader(); //ждем когда загрузится заголовок на странице "Про аренду"
        objAboutOrderPage.setRentalInformation(comment, dayRent); //Заполняем поля формы
        objAboutOrderPage.clickMakeOrderButton(); //Нажимаем кнопку Заказать

        ConfirmOrderCreationWindow objConfirmWindow = new ConfirmOrderCreationWindow(driver);
        objConfirmWindow.waitForLoadTitleConfirm(); //ждем когда загрузится заголовок на модальном окне с подтверждением создания заказа
        objConfirmWindow.clickConfirmButton(); //Нажимаем кнопку Да в модальном окне
        objConfirmWindow.waitForOrderSuccess(orderStatus); //ждем когда загрузится заголовок на модальном окне с подтверждением создания заказа
        String actualStatus = objConfirmWindow.statusOrder(); //Получаем текст со статусом заказа
        MatcherAssert.assertThat(actualStatus, startsWith(orderStatus)); //сравниваем статусы

    }


}