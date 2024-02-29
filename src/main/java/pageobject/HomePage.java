package pageobject;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;


public class HomePage {
    private final WebDriver driver;

    //конструкторы
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    //кнопка Статус заказа
    private final By statusOrderButton = By.xpath(".//button[text() = 'Статус заказа']");
    //поле для ввода статуса заказа
    private final By fieldStatusOrder = By.xpath(".//*[@class = 'Input_Input__1iN_Z Header_Input__xIoUq']");
    //кнопка для проверки статуса заказа Go!
    private final By buttonGo = By.xpath(".//*[@class = 'Button_Button__ra12g Header_Button__28dPO']");
    //логотип Самокат
    private final By logoScooter = By.xpath(".//*[@alt='Scooter']");
    //логотип Яндекс
    private final By logoYandex = By.xpath(".//*[@alt='Yandex']");
    //текст Самокат на пару дней
    private final By textScooterForTwoDays = By.xpath(".//*[@class = 'Home_Header__iJKdX']");
    //вопрос Сколько это стоит? И как оплатить?
    private final By whatIsThePrice = By.xpath(".//*[@id='accordion__heading-0']");
    //текст ответа Сутки — 400 рублей. Оплата курьеру — наличными или картой.
    private final By price = By.xpath(".//*[@id='accordion__panel-0']/p");
    // вопрос Хочу сразу несколько самокатов! Так можно?
    private final By someScooter = By.xpath(".//*[@id='accordion__heading-1']");
    //текст ответа Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим    //
    private final By howManyScooters = By.xpath(".//*[@id='accordion__panel-1']/p");
    //Вопрос "Как рассчитывается время аренды?"
    private final By rentalTimeCalculation = By.xpath(".//*[@id='accordion__heading-2']");
    // текст ответа Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.
    private final By answerAboutRentalTime = By.xpath(".//*[@id='accordion__panel-2']/p");
    //вопрос Можно ли заказать самокат прямо на сегодня?
    private final By orderForToday = By.xpath(".//*[@id='accordion__heading-3']");
    //текст ответа Только начиная с завтрашнего дня. Но скоро станем расторопнее.
    private final By onlyFromTomorrow = By.xpath(".//*[@id='accordion__panel-3']/p");
    //Вопрос Можно ли продлить самокат или вернуть раньше?
    private final By changeTheRentalPeriod = By.xpath(".//*[@id='accordion__heading-4']");
    // текст ответа Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.
    private final By callSupport = By.xpath(".//*[@id='accordion__panel-4']/p");
    // вопрос Вы привозите зарядку вместе с самокатом?
    private final By aboutCharging = By.xpath(".//*[@id='accordion__heading-5']");
    // текст ответа Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.
    private final By withCharging = By.xpath(".//*[@id='accordion__panel-5']/p");
    // вопрос Можно ли отменить заказ?
    private final By howToCancelTheOrder = By.xpath(".//*[@id='accordion__heading-6']");
    // текст ответа Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.
    private final By beCanceledBeforeDelivery = By.xpath(".//*[@id='accordion__panel-6']/p");
    // вопрос Я живу за МКАДом, привезёте?
    private final By deliveryOutsideTheMKAD = By.xpath(".//*[@id='accordion__heading-7']");
    // текст ответа Да, обязательно. Всем самокатов! И Москве, и Московской области.
    private final By deliveryInMoscowAndTheRegion = By.xpath(".//*[@id='accordion__panel-7']/p");
    // кнопка Заказать вверху страницы
    private final By buttonOrderUp = By.xpath(".//*[@class='Button_Button__ra12g']");
    // кнопка Заказать внизу страницы
    private final By buttonOrderDown = By.xpath(".//*[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    //принять куки
    private final By acceptCookies = By.xpath(".//*[@id = 'rcc-confirm-button']");
    //картинка "Такого заказа нет"
    private final By noSuchOrder = By.xpath(".//*[@src='/assets/not-found.png']");

    public String questionAnswer(By question, By answer) {
        WebElement stringQuestion = driver.findElement(question);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", stringQuestion);
        stringQuestion.click();
        driver.findElement(answer).click();
        return driver.findElement(answer).getText();//получили текст ответа
    }

    public void clickButtonOrderUp() {
        driver.findElement(buttonOrderUp).click();
    }

    public void clickButtonDownUp() {
        driver.findElement(buttonOrderDown).click();
    }

    public void acceptCookies() {
        driver.findElement(acceptCookies).click();
    }

    public void clickLogoScooter() {
        driver.findElement(logoScooter).click();
    }

    public boolean loadingMainPageAfterClickLogoScooter() {
        return driver.findElement(textScooterForTwoDays).isDisplayed();
    }

    public void checkOrderStatus(String orderNumber) {
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(statusOrderButton).click();//клик на кнопку Статус заказа
        driver.findElement(fieldStatusOrder).sendKeys(orderNumber);//ввести номер заказа
        driver.findElement(buttonGo).click();
    }

    public boolean isNoSuchOrder() {//проверка появления сообщения "такого заказа нет"
        return driver.findElement(noSuchOrder).isDisplayed();
    }

    //методы для получения значения приватных полей
    public By getWhatIsThePrice() {
        return whatIsThePrice;
    }

    public By getPrice() {
        return price;
    }

    public By getSomeScooter() {
        return someScooter;
    }

    public By getHowManyScooters() {
        return howManyScooters;
    }

    public By getRentalTimeCalculation() {
        return rentalTimeCalculation;
    }

    public By getAnswerAboutRentalTime() {
        return answerAboutRentalTime;
    }

    public By getOrderForToday() {
        return orderForToday;
    }

    public By getOnlyFromTomorrow() {
        return onlyFromTomorrow;
    }

    public By getChangeTheRentalPeriod() {
        return changeTheRentalPeriod;
    }

    public By getCallSupport() {
        return callSupport;
    }

    public By getAboutCharging() {
        return aboutCharging;
    }

    public By getWithCharging() {
        return withCharging;
    }

    public By getHowToCancelTheOrder() {
        return howToCancelTheOrder;
    }

    public By getBeCanceledBeforeDelivery() {
        return beCanceledBeforeDelivery;
    }

    public By getDeliveryOutsideTheMKAD() {
        return deliveryOutsideTheMKAD;
    }

    public By getDeliveryInMoscowAndTheRegion() {
        return deliveryInMoscowAndTheRegion;
    }

}

