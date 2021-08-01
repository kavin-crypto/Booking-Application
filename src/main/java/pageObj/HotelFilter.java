package pageObj;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HotelFilter {
    WebDriver driver;

    public HotelFilter(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="//*[@id='GLAZE']//li[2]/span[1]")
    WebElement filters;

    By pFilter = By.cssSelector("#POPULAR ul li span label p");

    public WebElement getFilters() {
        return filters;
    }

    public List<WebElement> popularFilters() {
        return driver.findElements(pFilter);
    }


}
