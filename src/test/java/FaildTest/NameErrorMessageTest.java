package FaildTest;

import PageObject.HomePage;
import PageObject.PageOrderForWhom;
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
    public WebDriver driver;
    private String name;
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
        driver.get("https://qa-scooter.praktikum-services.ru/");//открыли сайт
    }

    @Test
    public void errorMassageName() {
        driver.findElement(homePage.acceptCookies).click();
        homePage.clickButtonOrderUp();
        driver.findElement(dataClient.fieldName).sendKeys(name);
        driver.findElement(dataClient.fieldSurname).click();
        driver.findElement(dataClient.nameErrorMessage);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
