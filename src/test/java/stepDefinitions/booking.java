package stepDefinitions;

import Utilities.browser;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageObj.FromTo;
import pageObj.HotelFilter;
import pageObj.HotelReservation;

import java.util.List;

public class booking extends browser {
    FromTo ft;
    HotelReservation hr;
    HotelFilter hotelFilter;

    @Given("^Invoking the browser with chrome$")
    public void invoking_the_browser_with_chrome() throws Throwable {
        driver = browserInvoke();
    }

    @And("^Navigate to \"([^\"]*)\" site$")
    public void navigate_to_something_site(String strArg1) throws Throwable {
        driver.get(strArg1);
    }

    @When("^Verifying whether logo is displayed or not$")
    public void verifying_whether_logo_is_displayed_or_not() throws Throwable {
        ft = new FromTo(driver);
        ft.getBody().click();
        Assert.assertTrue(ft.getLogo().isDisplayed());
    }

    @And("^Selecting departure and destination location$")
    public void selecting_departure_and_destination_location() throws Throwable {
        ft.getFrom().click();
        ft.getLocation().sendKeys("Du");
        List<WebElement> location = ft.getCountry();
        //Wait.hold(driver, "//*[@role='listbox']/li/div/div[2]");
        Thread.sleep(1000);
        for (WebElement i : location) {
            if (i.getText().contains("DUB")) {
                i.click();
                break;
            }
        }
        ft.getTo().sendKeys("Aus");
        List<WebElement> toLocation = ft.getCountry();
        //Wait.hold(driver, "//*[@role='listbox']/li/div/div[2]");
        Thread.sleep(1000);
        for (WebElement j : toLocation) {
            if (j.getText().contains("SYD")) {
                j.click();
                break;
            }
        }
    }

    @And("^selecting day and month$")
    public void selecting_day_and_month() throws Throwable {
        while (!ft.getMonth().getText().contains("December")) {
            ft.getNext().click();
        }
        //log.info("Selecting departure day and month");
        List<WebElement> date = ft.getDay();
        Thread.sleep(1000);
        for (int i = 0; i < date.size(); i++) {
            String dates = date.get(i).getText();
            if (dates.contains("14")) {
                date.get(i).click();
                break;
            }
        }
    }


    @Then("^Selecting special fare and verifying the selected location is displayed correctly$")
    public void selecting_special_fare_and_verifying_the_selected_location_is_displayed_correctly() throws Throwable {
        //log.info("Selecting special fare");
        ft.getFare().click();
        //log.info("Verifying the selected location is displayed correctly");
        String from = ft.getFromCity().getAttribute("value");
        String to = ft.getToCity().getAttribute("value");
        Assert.assertEquals("Dublin", from);
        Assert.assertEquals("Sydney", to);
    }

    @Given("^Selecting hotels$")
    public void selecting_hotels() throws Throwable {
        hr = ft.getHotel();
    }

    @When("^User should select location$")
    public void user_should_select_location() throws Throwable {
        hr.getCity().click();
        List<WebElement> con = hr.getCountry();
        Thread.sleep(1000);
        for (WebElement o : con) {
            if (o.getText().equalsIgnoreCase("Dubai")) {
                o.click();
                break;
            }
        }
    }

    @And("^Selecting checkIn date,checkOut date and number of guest$")
    public void selecting_checkin_datecheckout_date_and_number_of_guest() throws Throwable {
        while (!ft.getMonth().getText().contains("October")) {
            ft.getNext().click();
        }
        List<WebElement> checkIn = hr.getData();
        Thread.sleep(1000);
        for (int i = 0; i < checkIn.size(); i++) {
            String dates = checkIn.get(i).getText();
            if (dates.contains("15")) {
                checkIn.get(i).click();
                break;
            }
        }
        //log.info("Selecting checkOut date");
        List<WebElement> checkOut = hr.getData();
        Thread.sleep(1000);
        for (int i = 0; i < checkOut.size(); i++) {
            String dates = checkOut.get(i).getText();
            if (dates.contains("18")) {
                checkOut.get(i).click();
                break;
            }
        }
    }

    @Then("^Click on search button$")
    public void click_on_search_button() throws Throwable {
        hr.getRooms().click();
        //log.info("Selecting number of guest");
        hr.getCount().click();
        hr.getApplyButton().click();
        hotelFilter = hr.getSearch();
    }

    @And("^Filtering$")
    public void filtering() throws Throwable {
        hotelFilter.getFilters().click();
        List<WebElement> popular = hotelFilter.popularFilters();
        //Wait.hold(driver, "//*[@id ='POPULAR']/ul/li/span/label/p");
        Thread.sleep(1000);
        for (WebElement pop : popular) {
            if (pop.getText().equals("Free Breakfast")) {
                pop.click();
                break;
            }
        }
    }

    @And("^Close the browser$")
    public void close_the_browser() throws Throwable {
        driver.quit();
    }

}