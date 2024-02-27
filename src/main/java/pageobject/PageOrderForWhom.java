package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.*;

public class PageOrderForWhom {
    private final WebDriver driver;

    //конструктор
    public PageOrderForWhom(WebDriver driver) {
        this.driver = driver;
    }

    //поле Имя
    private final By fieldName = By.xpath(".//input[@placeholder='* Имя']");
    private final By nameErrorMessage = By.xpath(".//*[@class = 'Input_ErrorMessage__3HvIb Input_Visible___syz6' and text()='Введите корректное имя']"); //<div class="Input_ErrorMessage__3HvIb Input_Visible___syz6">Введите корректное имя</div>
    //поле Фамилия
    private final By fieldSurname = By.xpath(".//input[@placeholder='* Фамилия']");
    private final By surnameErrorMessage = By.xpath(".//*[@class = 'Input_ErrorMessage__3HvIb Input_Visible___syz6' and text()='Введите корректную фамилию']");
    //поле Адрес: куда привезти
    private final By fieldAddress = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By addressErrorMessage = By.xpath(".//*[@class = 'Input_ErrorMessage__3HvIb Input_Visible___syz6' and text()='Введите корректный адрес']");
    //поле Станция метро
    private final By metroErrorMessage = By.xpath(".//*[@class = 'Input_ErrorMessage__3HvIb Input_Visible___syz6' and text()='Выберите станцию']");
    private final By fieldMetroStation = By.xpath(".//*[@class = 'select-search']"); //".//*[@class = 'select-search']" .//input[@placeholder='* Станция метро']

    //поле Телефон: на него позвонит курьер
    private final By fieldPhone = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By phoneErrorMessage = By.xpath(".//*[@class = 'Input_ErrorMessage__3HvIb Input_Visible___syz6' and text()='Введите корректный номер']");
    //кнопка Далее
    private final By buttonFurther = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    //текст "Для кого самокат"
    private final By textForWhomScooter = By.xpath(".//div[@class='Order_Header__BZXOb']");

    public void enterDataClient(String name, String surname, String address, String metroStation, String phone) {
        driver.findElement(fieldName).sendKeys(name);
        driver.findElement(fieldSurname).sendKeys(surname);
        driver.findElement(fieldAddress).sendKeys(address);
        driver.findElement(fieldMetroStation).click();
        String nameStation = ".//div[contains(text(), '" + metroStation + "')]";
        WebElement metroStationInListItem = driver.findElement(By.xpath(nameStation));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", metroStationInListItem);
        driver.findElement(By.xpath(nameStation)).click();
        driver.findElement(fieldPhone).sendKeys(phone);
        driver.findElement(buttonFurther).click();
    }

    public boolean errorMessageSurname(String surname) {
        driver.findElement(fieldSurname).sendKeys(surname);
        driver.findElement(fieldAddress).click();
        return driver.findElement(surnameErrorMessage).isDisplayed();
    }

    public boolean orderFormLoaded() {
        return driver.findElement(textForWhomScooter).isDisplayed();
    }

    public boolean errorMessageName(String name) {//Проверяем появление ошибки при введении недопустимого значения в поле имя
        driver.findElement(fieldName).sendKeys(name);
        driver.findElement(fieldSurname).click();
        return driver.findElement(nameErrorMessage).isDisplayed();
    }
}
