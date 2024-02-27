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
public class SurnameErrorMessageTest {
    private static WebDriver driver;
    private final String surname;
    private HomePage homePage;
    private PageOrderForWhom dataClient;

    public SurnameErrorMessageTest(String surname) {
        this.surname = surname;
    }

    @Parameterized.Parameters
    public static Object[][] getFalseDataClient() {
        return new Object[][]{
                {"Vasin"},
                {"Васин2"},
                {"Салтыков-Щедрин"},
        };
    }

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        //WebDriverManager.firefoxdriver().setup();
        this.driver = new ChromeDriver();
        //this.driver = new FirefoxDriver();
        driver.get(ConstParam.PAGE_URL);//открыли сайт
        homePage = new HomePage(driver);
        dataClient = new PageOrderForWhom(driver);
    }

    @Test
    public void errorMessageSurname() {
        homePage.acceptCookies();
        homePage.clickButtonOrderUp();
        dataClient.errorMessageSurname(surname);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
