package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageOrderRentData {
    private WebDriver driver;

    public PageOrderRentData(WebDriver driver) {
        this.driver = driver;
    }
    //поле Когда привезти самокат
    public By fieldWhenToBring = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    //поле срок аренды
    public By fieldRentalPeriod = By.xpath(".//div[@class='Dropdown-placeholder']");

    //комментарии
    public By commentFor = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    //кнопка Заказать
    public By buttonOrder = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    public By buttonYes = By.xpath(".//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM' and text() = 'Да']");
    public By messageOrderDone = By.xpath(".//div[text() = 'Заказ оформлен']");
    public void enterDataOrder(String dayOfTheMonth, String month, String rentalPeriod, String colour, String comment) {
        driver.findElement(fieldWhenToBring).click();
        String selectedDate = ".//div[contains(@aria-label, '" + dayOfTheMonth + "') and contains(@aria-label, '" + month + "')]";
        WebElement dateListItem = driver.findElement(By.xpath(selectedDate));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", dateListItem);
        driver.findElement(By.xpath(selectedDate)).click();
        driver.findElement(fieldRentalPeriod).click();
        String selectRentalPeriod = ".//div[contains(text(), '" + rentalPeriod + "')]";
        WebElement rentalPeriodInListItem = driver.findElement(By.xpath(selectRentalPeriod));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", rentalPeriodInListItem);
        driver.findElement(By.xpath(selectRentalPeriod)).click();
        driver.findElement(By.xpath(".//label[contains(text(), '" + colour + "')]")).click();
        driver.findElement(commentFor).sendKeys(comment);
    }
}