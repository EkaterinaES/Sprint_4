import constants.ConstParam;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageobject.HomePage;
import pageobject.PageOrderForWhom;
import pageobject.PageOrderRentData;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class OrderScooterTest {

    public WebDriver driver;
    private final String name;
    private final String surname;
    private final String address;
    private final String metroStation;
    private final String phone;
    private final String dayOfTheMonth;
    private final String month;
    private final String rentalPeriod;
    private final String colour;
    private final String comment;
    private HomePage homePage;
    private PageOrderForWhom dataClient;
    private PageOrderRentData oneOrder;


    public OrderScooterTest(String name, String surname, String address, String metroStation, String phone, String dayOfTheMonth, String month, String rentalPeriod, String colour, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.dayOfTheMonth = dayOfTheMonth;
        this.month = month;
        this.rentalPeriod = rentalPeriod;
        this.colour = colour;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getDataClient() {
        return new Object[][]{
                {"Алексей", "Иванов", "Мира, 6", "Чистые пруды", "89119112345", "17", "февраля", "двое суток", "чёрный жемчуг", "Очень жду"},
                {"Константин", "Петров", "Сиреневый бульвар, 5", "Митино", "89212213144", "2", "марта", "семеро суток", "серая безысходность", "Позвонить за 1 час"},
        };
    }

    @Before
    public void startUp() {
        //WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
        //this.driver = new ChromeDriver();
        this.driver = new FirefoxDriver();
        driver.get(ConstParam.PAGE_URL);//открыли сайт
        homePage = new HomePage(driver);
        dataClient = new PageOrderForWhom(driver);
        oneOrder = new PageOrderRentData(driver);
        homePage.acceptCookies(); //приняли куки
    }

    @Test
    public void upButtonOrder() { //заказ с верхней кнопки Заказать
        homePage.clickButtonOrderUp(); //кликнули на заказать
        dataClient.enterDataClient(name, surname, address, metroStation, phone);
        oneOrder.enterDataOrder(dayOfTheMonth, month, rentalPeriod, colour, comment);
        oneOrder.clickOnButtonYes();
        assertTrue(oneOrder.isOrderConfirmation());
    }

    @Test
    public void downButtonOrder() { //Проверка нижней кнопки "Заказать". Проверяем до момента загрузки страницы заказа
       homePage.clickButtonDownUp();
       assertTrue(dataClient.isOrderFormLoaded());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
