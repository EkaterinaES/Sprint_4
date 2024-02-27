package faildtest;

import constants.ConstParam;
import pageobject.HomePage;
import pageobject.PageOrderForWhom;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


@RunWith(Parameterized.class)
public class NameErrorMessageTest {
    private static WebDriver driver;
    private final String name;
    private HomePage homePage;
    private PageOrderForWhom dataClient;

    public NameErrorMessageTest(String name) {
        this.name = name;
    }


    @Parameterized.Parameters
    public static Object[][] getFalseDataClient() {
        return new Object[][]{
                {"Vasy"},
                {"Петя1"},
                {"Мария-Луиза"},
        };
    }

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        //WebDriverManager.firefoxdriver().setup();
        this.driver = new ChromeDriver();
        //this.driver = new FirefoxDriver();
        homePage = new HomePage(driver);
        dataClient = new PageOrderForWhom(driver);
        driver.get(ConstParam.PAGE_URL);//открыли сайт
    }

    @Test
    public void errorMessageName() {
        homePage.acceptCookies();
        homePage.clickButtonOrderUp();
        dataClient.errorMessageName(name);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
