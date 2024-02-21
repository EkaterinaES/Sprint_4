import PageObject.HomePage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.firefox.FirefoxDriver;

@RunWith(Parameterized.class)
public class HomePageQuestionsTest {
    private WebDriver driver;
    private final By question;
    private final By answer;
    private final String expected;
    private HomePage homePage;

    public HomePageQuestionsTest(By question, By answer, String expected) {
        this.question = question;
        this.answer = answer;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        HomePage sectionWithQuestion = new HomePage();
        return new Object[][]{
                {sectionWithQuestion.whatIsThePrice, sectionWithQuestion.price, sectionWithQuestion.priceText},
                {sectionWithQuestion.someScooter, sectionWithQuestion.howManyScooters, sectionWithQuestion.howManyScootersText},
                {sectionWithQuestion.rentalTimeCalculation, sectionWithQuestion.answerAboutRentalTime, sectionWithQuestion.answerAboutRentalTimeText},
                {sectionWithQuestion.orderForToday, sectionWithQuestion.onlyFromTomorrow, sectionWithQuestion.onlyFromTomorrowText},
                {sectionWithQuestion.changeTheRentalPeriod, sectionWithQuestion.callSupport, sectionWithQuestion.callSupportText},
                {sectionWithQuestion.aboutСharging, sectionWithQuestion.withСharging, sectionWithQuestion.withСhargingText},
                {sectionWithQuestion.howToCancelTheOrder, sectionWithQuestion.beCanceledBeforeDelivery, sectionWithQuestion.beCanceledBeforeDeliveryText},// передали тестовые данные
                {sectionWithQuestion.deliveryOutsideTheMKAD, sectionWithQuestion.deliveryInMoscowAndTheRegion, sectionWithQuestion.deliveryInMoscowAndTheRegionText},
        };
    }

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        //WebDriverManager.firefoxdriver().setup();
        this.driver = new ChromeDriver();
        //this.driver = new FirefoxDriver();
        homePage = new HomePage(driver);
    }

    @Test
    public void testQuestionAnswer() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
        homePage.questionAnswer(question, answer);
        String actual = driver.findElement(answer).getText();
        Assert.assertEquals(expected, actual);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}