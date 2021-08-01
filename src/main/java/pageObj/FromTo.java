package pageObj;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class FromTo {
    public WebDriver driver;

    public FromTo(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[contains(@class,'desktop in')]")
    WebElement body;

    @FindBy(css = ".mmtLogo.makeFlex")
    WebElement logo;

    @FindBy(css = "[for='fromCity']")
    WebElement from;

    @FindBy(xpath = "//*[@placeholder='From']")
    WebElement location;

    By country = By.xpath("//*[@role='listbox']/li/div/div[2]");

    @FindBy(xpath = "//*[@placeholder='To']")
    WebElement to;

    By toLoc = By.xpath("//*[@role='option']/div/div[2]");

    @FindBy(css = ".DayPicker-Caption")
    WebElement date;

    @FindBy(css = ".DayPicker-NavButton--next")
    WebElement nextDate;

    By day = By.cssSelector(".dateInnerCell");

    @FindBy(xpath = "//*[@class = 'specialFare']/li[3]")
    WebElement fare;

    @FindBy(id = "fromCity")
    WebElement fromCity;

    @FindBy(id = "toCity")
    WebElement toCity;

    By hotel = By.cssSelector(".menu_Hotels a");


    public WebElement getBody() {
        return body;
    }

    public WebElement getLogo() {
        return logo;
    }

    public WebElement getFrom() {
        return from;
    }

    public WebElement getLocation() {
        return location;
    }

    public List<WebElement> getCountry() {
        return driver.findElements(country);
    }

    public WebElement getTo() {
        return to;
    }

    public WebElement getMonth() {
        return date;
    }

    public WebElement getNext() {
        return nextDate;
    }

    public List<WebElement> getDay() {
        return driver.findElements(day);
    }

    public WebElement getFare() {
        return fare;
    }

    public WebElement getFromCity() {
        return fromCity;
    }

    public WebElement getToCity() {
        return toCity;
    }

    public HotelReservation getHotel() {
        driver.findElement(hotel).click();
        HotelReservation hot = new HotelReservation(driver);
        return hot;
    }

}
