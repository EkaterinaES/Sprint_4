import constants.ConstParam;
import pageobject.HomePage;
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

@RunWith(Parameterized.class)
public class HomePageQuestionsTest {
    private static WebDriver driver;
    private final By QUESTION;
    private final By ANSWER;
    private final String EXPECTED;
    private HomePage homePage;

    public HomePageQuestionsTest(By question, By answer, String expected) {
        this.QUESTION = question;
        this.ANSWER = answer;
        this.EXPECTED = expected;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        HomePage sectionWithQuestion = new HomePage(driver);
        return new Object[][]{
                {sectionWithQuestion.whatIsThePrice, sectionWithQuestion.price, ConstParam.priceText},
                {sectionWithQuestion.someScooter, sectionWithQuestion.howManyScooters, ConstParam.HOW_MANY_SCOOTERS_TEXT},
                {sectionWithQuestion.rentalTimeCalculation, sectionWithQuestion.answerAboutRentalTime, ConstParam.ANSWER_ABOUT_RENTAL_TIME_TEXT},
                {sectionWithQuestion.orderForToday, sectionWithQuestion.onlyFromTomorrow, ConstParam.ONLY_FROM_TOMORROW_TEXT},
                {sectionWithQuestion.changeTheRentalPeriod, sectionWithQuestion.callSupport, ConstParam.CALL_SUPPORT_TEXT},
                {sectionWithQuestion.aboutСharging, sectionWithQuestion.withСharging, ConstParam.WITH_СHARGING_TEXT},
                {sectionWithQuestion.howToCancelTheOrder, sectionWithQuestion.beCanceledBeforeDelivery, ConstParam.BE_CANCELED_BEFORE_DELIVERY_TEXT},// передали тестовые данные
                {sectionWithQuestion.deliveryOutsideTheMKAD, sectionWithQuestion.deliveryInMoscowAndTheRegion, ConstParam.DELIVERY_IN_MOSCOW_AND_THE_REGION_TEXT},
        };
    }

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        //WebDriverManager.firefoxdriver().setup();
        this.driver = new ChromeDriver();
        //this.driver = new FirefoxDriver();
        homePage = new HomePage(driver);
        driver.get(ConstParam.PAGE_URL);//открыли сайт
    }

    @Test
    public void testQuestionAnswer() {
        String AnswerText = homePage.questionAnswer(QUESTION, ANSWER);
        Assert.assertEquals(EXPECTED, AnswerText);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}