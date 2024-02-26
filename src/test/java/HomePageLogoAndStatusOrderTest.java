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
    private WebDriver driver;
    HomePage homePage;

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
    public void loadingMainPageFromHomePage() {
        driver.findElement(homePage.logoScooter).click();
        Assert.assertTrue(driver.findElement(homePage.textScooterForTwoDays).isDisplayed());
    }

    @Test
    public void loadingMainPageFromOrderPage() { //проверка загрузки главной страницы Самоката при нажатии на слово Самокат со страницы заказа
        homePage.clickButtonOrderUp();
        driver.findElement(homePage.logoScooter).click();
        Assert.assertTrue(driver.findElement(homePage.textScooterForTwoDays).isDisplayed());
    }

    @Test
    public void orderNumberFalseTest() {
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(homePage.statusOrderButton).click();
        driver.findElement(homePage.fieldStatusOrder).sendKeys("456746");
        driver.findElement(homePage.buttonGo).click();
        Assert.assertTrue(driver.findElement(homePage.noSuchOrder).isDisplayed());
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
