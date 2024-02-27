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
        HomePage sectionWithQuestion = new HomePage(driver);
        return new Object[][]{
                {sectionWithQuestion.getWhatIsThePrice(), sectionWithQuestion.getPrice(), ConstParam.PRICE_TEXT},
                {sectionWithQuestion.getSomeScooter(), sectionWithQuestion.getHowManyScooters(), ConstParam.HOW_MANY_SCOOTERS_TEXT},
                {sectionWithQuestion.getRentalTimeCalculation(), sectionWithQuestion.getAnswerAboutRentalTime(), ConstParam.ANSWER_ABOUT_RENTAL_TIME_TEXT},
                {sectionWithQuestion.getOrderForToday(), sectionWithQuestion.getOnlyFromTomorrow(), ConstParam.ONLY_FROM_TOMORROW_TEXT},
                {sectionWithQuestion.getChangeTheRentalPeriod(), sectionWithQuestion.getCallSupport(), ConstParam.CALL_SUPPORT_TEXT},
                {sectionWithQuestion.getAboutCharging(), sectionWithQuestion.getWithCharging(), ConstParam.WITH_CHARGING_TEXT},
                {sectionWithQuestion.getHowToCancelTheOrder(), sectionWithQuestion.getBeCanceledBeforeDelivery(), ConstParam.BE_CANCELED_BEFORE_DELIVERY_TEXT},// передали тестовые данные
                {sectionWithQuestion.getDeliveryOutsideTheMKAD(), sectionWithQuestion.getDeliveryInMoscowAndTheRegion(), ConstParam.DELIVERY_IN_MOSCOW_AND_THE_REGION_TEXT},
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
        String answerText = homePage.questionAnswer(question, answer);
        Assert.assertEquals(expected, answerText);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}