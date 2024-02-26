import constants.ConstParam;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageobject.HomePage;
import pageobject.PageOrderForWhom;
import pageobject.PageOrderRentData;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;

@RunWith(Parameterized.class)
public class OrderScooterTest {

    public WebDriver driver;
    private final String NAME;
    private final String SURNAME;
    private final String ADDRESS;
    private final String METRO_STATION;
    private final String PHONE;
    private final String DAY_OF_THE_MONTH;
    private final String MONTH;
    private final String RENTAL_PERIOD;
    private final String COLOUR;
    private final String COMMENT;
    private HomePage homePage;
    private PageOrderForWhom dataClient;
    private PageOrderRentData oneOrder;


    public OrderScooterTest(String name, String surname, String address, String metroStation, String phone, String dayOfTheMonth, String month, String rentalPeriod, String colour, String comment) {
        this.NAME = name;
        this.SURNAME = surname;
        this.ADDRESS = address;
        this.METRO_STATION = metroStation;
        this.PHONE = phone;
        this.DAY_OF_THE_MONTH = dayOfTheMonth;
        this.MONTH = month;
        this.RENTAL_PERIOD = rentalPeriod;
        this.COLOUR = colour;
        this.COMMENT = comment;
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
    }

    @Test
    public void upButtonOrder() { //заказ с верхней кнопки Заказать
        homePage.acceptСookies(); //приняли куки
        homePage.clickButtonOrderUp(); //кликнули на заказать
        dataClient.enterDataClient(NAME, SURNAME, ADDRESS, METRO_STATION, PHONE);
        oneOrder.enterDataOrder(DAY_OF_THE_MONTH, MONTH, RENTAL_PERIOD, COLOUR, COMMENT);
        oneOrder.confirmOrder();//подтвердили желание сделать заказ
        Assert.assertTrue(driver.findElement(oneOrder.messageOrderDone).isDisplayed());
    }

    @Test
    public void downButtonOrder() { //заказ с нижней кнопки "Заказать" проверяем до момента загрузки страницы заказа
        homePage.acceptСookies();
        homePage.clickButtonDownUp();
        Assert.assertTrue(driver.findElement(dataClient.textForWhomScooter).isDisplayed());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
