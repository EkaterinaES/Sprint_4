package faildtest;

import constants.ConstParam;
import pageobject.HomePage;
import pageobject.PageOrderForWhom;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(Parameterized.class)
public class SurnameErrorMessageTest {
    public WebDriver driver;
    private final String SURNAME;
    private HomePage homePage;
    private PageOrderForWhom dataClient;

    public SurnameErrorMessageTest(String surname) {
        this.SURNAME = surname;
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
    public void errorMassageSurname() {
        driver.findElement(homePage.acceptCookies).click();
        homePage.clickButtonOrderUp();
        driver.findElement(dataClient.fieldSurname).sendKeys(SURNAME);
        driver.findElement(dataClient.fieldAddress).click();
        Assert.assertTrue(driver.findElement(dataClient.surnameErrorMessage).isDisplayed());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
