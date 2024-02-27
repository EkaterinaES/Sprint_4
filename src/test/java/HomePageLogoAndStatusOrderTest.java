import constants.ConstParam;
import pageobject.HomePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class HomePageLogoAndStatusOrderTest {
    private static WebDriver driver;
    private HomePage homePage;

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        // WebDriverManager.firefoxdriver().setup();
        this.driver = new ChromeDriver();
        //this.driver = new FirefoxDriver();
        homePage = new HomePage(driver);
        driver.get(ConstParam.PAGE_URL);//открыли сайт
    }

    @Test
    public void loadingMainPageFromHomePage() { //проверка загрузки главной страницы Самоката при нажатии на слово Самокат на главной странице
        homePage.loadingMainPageAfterClickLogoScooter();
    }

    @Test
    public void loadingMainPageFromOrderPage() { //проверка загрузки главной страницы Самоката при нажатии на слово Самокат со страницы заказа
        homePage.clickButtonOrderUp();
        homePage.loadingMainPageAfterClickLogoScooter();
    }

    @Test
    public void orderNumberFalseTest() {
        homePage.orderNumberFalse();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
