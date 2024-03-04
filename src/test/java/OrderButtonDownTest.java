import constants.ConstParam;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageobject.HomePage;
import pageobject.PageOrderForWhom;

import static org.junit.Assert.assertTrue;

public class OrderButtonDownTest {
    public WebDriver driver;
    private HomePage homePage;
    private PageOrderForWhom dataClient;

    @Before
    public void startUp() {
        //WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
        //this.driver = new ChromeDriver();
        this.driver = new FirefoxDriver();
        driver.get(ConstParam.PAGE_URL);//открыли сайт
        homePage = new HomePage(driver);
        dataClient = new PageOrderForWhom(driver);
        homePage.acceptCookies(); //приняли куки
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
