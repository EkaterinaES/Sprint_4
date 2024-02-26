package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    WebDriver driver;

    //конструкторы
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    //кнопка Статус заказа
    public By statusOrderButton = By.xpath(".//button[text() = 'Статус заказа']");
    //поле для ввода статуса заказа
    public By fieldStatusOrder = By.xpath(".//*[@class = 'Input_Input__1iN_Z Header_Input__xIoUq']");
    //кнопка для проверки статуса заказа Go!
    public By buttonGo = By.xpath(".//*[@class = 'Button_Button__ra12g Header_Button__28dPO']");
    //логотип Самокат
    public By logoScooter = By.xpath(".//*[@alt='Scooter']");
    //логотип Яндекс
    public By logoYandex = By.xpath(".//*[@alt='Yandex']");
    //текст Самокат на пару дней
    public By textScooterForTwoDays = By.xpath(".//*[@class = 'Home_Header__iJKdX']");
    //вопрос Сколько это стоит? И как оплатить?
    public By whatIsThePrice = By.xpath(".//*[@id='accordion__heading-0']");
    //текст ответа Сутки — 400 рублей. Оплата курьеру — наличными или картой.
    public By price = By.xpath(".//*[@id='accordion__panel-0']/p");
    // вопрос Хочу сразу несколько самокатов! Так можно?
    public By someScooter = By.xpath(".//*[@id='accordion__heading-1']");
    //текст ответа Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим    //
    public By howManyScooters = By.xpath(".//*[@id='accordion__panel-1']/p");
    //Вопрос "Как расчитывается время аренды?"
    public By rentalTimeCalculation = By.xpath(".//*[@id='accordion__heading-2']");
    // текст ответа Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.
    public By answerAboutRentalTime = By.xpath(".//*[@id='accordion__panel-2']/p");
    //вопрос Можно ли заказать самокат прямо на сегодня?
    public By orderForToday = By.xpath(".//*[@id='accordion__heading-3']");
    //текст ответа Только начиная с завтрашнего дня. Но скоро станем расторопнее.
    public By onlyFromTomorrow = By.xpath(".//*[@id='accordion__panel-3']/p");
    //Вопрос Можно ли продлить самокат или вернуть раньше?
    public By changeTheRentalPeriod = By.xpath(".//*[@id='accordion__heading-4']");
    // текст ответа Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.
    public By callSupport = By.xpath(".//*[@id='accordion__panel-4']/p");
    // вопрос Вы привозите зарядку вместе с самокатом?
    public By aboutСharging = By.xpath(".//*[@id='accordion__heading-5']");
    // текст ответа Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.
    public By withСharging = By.xpath(".//*[@id='accordion__panel-5']/p");
    // вопрос Можно ли отменить заказ?
    public By howToCancelTheOrder = By.xpath(".//*[@id='accordion__heading-6']");
    // текст ответа Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.
    public By beCanceledBeforeDelivery = By.xpath(".//*[@id='accordion__panel-6']/p");
    // вопрос Я живу за МКАДом, привезёте?
    public By deliveryOutsideTheMKAD = By.xpath(".//*[@id='accordion__heading-7']");
    // текст ответа Да, обязательно. Всем самокатов! И Москве, и Московской области.
    public By deliveryInMoscowAndTheRegion = By.xpath(".//*[@id='accordion__panel-7']/p");
    // кнопка Заказать вверху страницы
    public By buttonOrderUp = By.xpath(".//*[@class='Button_Button__ra12g']");
    // кнопка Заказать внизу страницы
    public By buttonOrderDown = By.xpath(".//*[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    //принять куки
    public By acceptCookies = By.xpath(".//*[@id = 'rcc-confirm-button']");
    //картинка "Такого заказа нет"
    public By noSuchOrder = By.xpath(".//*[@src='/assets/not-found.png']");

    public String questionAnswer(By question, By answer) {
        WebElement stringQuestion = driver.findElement(question);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", stringQuestion);
        stringQuestion.click();
        driver.findElement(answer).click();
        return driver.findElement(answer).getText();
    }

    public void clickButtonOrderUp() {
        driver.findElement(buttonOrderUp).click();
    }

    public void clickButtonDownUp() {
        driver.findElement(buttonOrderDown).click();
    }

    public void acceptСookies() {
        driver.findElement(acceptCookies).click();
    }

}

