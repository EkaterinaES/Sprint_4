import PageObject.HomePage;
import PageObject.PageOrderForWhom;
import PageObject.PageOrderRentData;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

@RunWith(Parameterized.class)
public class OrderScooterTest {
    public WebDriver driver;
    private String name;
    private String surname;
    private String address;
    private String metroStation;
    private String phone;
    private String dayOfTheMonth;
    private String month;
    private String rentalPeriod;
    private String colour;
    private String comment;
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
                {"Константин", "Петров", "Синеревый бульвар, 5", "Митино", "89212213144", "2", "марта", "семеро суток", "серая безысходность", "Позвонить за 1 час"},
        };
    }

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        //WebDriverManager.firefoxdriver().setup();
        this.driver = new ChromeDriver();
        //this.driver = new FirefoxDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");//открыли сайт
        homePage = new HomePage(driver);
        dataClient = new PageOrderForWhom(driver);
        oneOrder = new PageOrderRentData(driver);
    }

    @Test
    public void upButtonOrder() { //заказ с верхней кнопки Заказать
        driver.findElement(homePage.acceptCookies).click();
        homePage.clickButtonOrderUp();
        dataClient.enterDataClient(name, surname, address, metroStation, phone);
        driver.findElement(dataClient.buttonFurther).click();
        oneOrder.enterDataOrder(dayOfTheMonth, month, rentalPeriod, colour, comment);
        driver.findElement(oneOrder.buttonOrder).click();
        driver.findElement(oneOrder.buttonYes).click();
        Assert.assertTrue(driver.findElement(oneOrder.messageOrderDone).isDisplayed());
    }
@Test
public void downButtonOrder() { //заказ с нижней кнопки Заказать
    driver.findElement(homePage.acceptCookies).click();
    homePage.clickButtonDownUp();
    dataClient.enterDataClient(name, surname, address, metroStation, phone);
    driver.findElement(dataClient.buttonFurther).click();
    oneOrder.enterDataOrder(dayOfTheMonth, month, rentalPeriod, colour, comment);
    driver.findElement(oneOrder.buttonOrder).click();
    driver.findElement(oneOrder.buttonYes).click();
    Assert.assertTrue(driver.findElement(oneOrder.messageOrderDone).isDisplayed());
}

    @After
    public void tearDown() {
        driver.quit();
    }
}
