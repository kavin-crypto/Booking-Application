package testcase;

import Utilities.browser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObj.FromTo;
import pageObj.HotelFilter;
import pageObj.HotelReservation;

import java.io.IOException;
import java.util.List;


public class TestFlights extends browser {
    private static final Logger log = LogManager.getLogger(TestFlights.class.getName());
    public static WebDriver driver;
    FromTo ft;
    HotelReservation hr;
    HotelFilter hotelFilter;


    @BeforeTest
    public void invoke() throws IOException {
        driver = browserInvoke();
        driver.get(props.getProperty("url"));
        driver.manage().window().fullscreen();
    }

    @Test
    public void FlightDeparture() throws InterruptedException {
        ft = new FromTo(driver);
        ft.getBody().click();
        log.info("Verifying whether logo is displayed or not");
        Assert.assertTrue(ft.getLogo().isDisplayed());
        ft.getFrom().click();
        log.info("Selecting the departure location");
        ft.getLocation().sendKeys("Du");
        List<WebElement> location = ft.getCountry();
        Wait("//*[@role='listbox']/li/div/div[2]");
        Thread.sleep(1000);
        for (WebElement i : location) {
            if (i.getText().contains("DUB")) {
                i.click();
                break;
            }
        }
    }

    @Test(dependsOnMethods = "FlightDeparture")
    public void FlightDestination() throws InterruptedException {
        log.info("Selecting the destination location");
        ft.getTo().sendKeys("Aus");
        List<WebElement> toLocation = ft.getCountry();
        Wait("//*[@role='listbox']/li/div/div[2]");
        for (WebElement j : toLocation) {
            if (j.getText().contains("SYD")) {
                j.click();
                break;
            }
        }
    }

    @Test(dependsOnMethods = "FlightDestination")
    public void FlightData() throws InterruptedException {
        while (!ft.getMonth().getText().contains("December")) {
            ft.getNext().click();
        }
        log.info("Selecting departure day and month");
        List<WebElement> date = ft.getDay();

        for (int i = 0; i < date.size(); i++) {
            String dates = date.get(i).getText();
            if (dates.contains("27")) {
                date.get(i).click();
                break;
            }
        }
        log.info("Selecting special fare");
        ft.getFare().click();
        log.info("Verifying the selected location is displayed correctly");
        String from = ft.getFromCity().getAttribute("value");
        String to = ft.getToCity().getAttribute("value");
        Assert.assertEquals("Dublin", from);
        Assert.assertEquals("Sydney", to);
    }

    @Test(dependsOnMethods = "FlightData")
    public void HotelLocation() throws InterruptedException {
        log.info("Selecting hotels");
        hr = ft.getHotel();
        log.info("Selecting location");
        hr.getCity().click();
        List<WebElement> con = hr.getCountry();
        Wait("//*[contains(@class,'locusLabel appendBottom5')]");
        for (WebElement o : con) {
            if (o.getText().equalsIgnoreCase("Dubai")) {
                o.click();
                break;
            }
        }
        log.info("Selecting checkIn date");
        while (!ft.getMonth().getText().contains("October")) {
            ft.getNext().click();
        }
        List<WebElement> checkIn = hr.getData();
        Wait("//*[@class='DayPicker-Month']/div[3]/div/div");

        for (int i = 0; i < checkIn.size(); i++) {
            String dates = checkIn.get(i).getText();
            if (dates.contains("15")) {
                checkIn.get(i).click();
                break;
            }
        }
        log.info("Selecting checkOut date");
        List<WebElement> checkOut = hr.getData();
        for (int i = 0; i < checkOut.size(); i++) {
            String dates = checkOut.get(i).getText();
            if (dates.contains("18")) {
                checkOut.get(i).click();
                break;
            }
        }

        hr.getRooms().click();
        log.info("Selecting number of guest");
        hr.getCount().click();
        hr.getApplyButton().click();

        hotelFilter = hr.getSearch();
    }

    @Test(dependsOnMethods = "HotelLocation")
    public void HotelFilters() throws InterruptedException {
        log.info("Filtering");
        hotelFilter.getFilters().click();
        List<WebElement> popular = hotelFilter.popularFilters();
        Wait("//*[@id ='POPULAR']/ul/li/span/label/p");

        for (WebElement pop : popular) {
            if (pop.getText().equals("Free Breakfast")) {
                pop.click();
                break;
            }
        }
        driver.quit();

    }

}
