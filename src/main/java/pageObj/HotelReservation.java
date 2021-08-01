package pageObj;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HotelReservation {
    public static WebDriver driver;
    public HotelReservation(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(css = "[for='city']")
    WebElement city;

    By country = By.cssSelector(".locusLabel.appendBottom5");
    By day = By.xpath("//*[@class='DayPicker-Month']/div[3]/div/div");

    @FindBy(css = ".hsw_inputBox.roomGuests  ")
    WebElement rooms;

    @FindBy(css = "[data-cy='adults-1']")
    WebElement count;

    @FindBy(css=".primaryBtn.btnApply")
    WebElement apply;

    @FindBy(css = "[id='hsw_search_button']")
    WebElement search;

    public WebElement getCity() {
        return city;
    }

    public List<WebElement> getCountry() {
        return driver.findElements(country);
    }

    public List<WebElement> getData() {
        return driver.findElements(day);
    }

    public WebElement getRooms() {
        return rooms;
    }

    public WebElement getCount() {
        return count;
    }

    public WebElement getApplyButton() {
        return apply;
    }

    public HotelFilter getSearch() {
        search.click();
        HotelFilter hotelFilter = new HotelFilter(driver);
        return hotelFilter;
    }
}
