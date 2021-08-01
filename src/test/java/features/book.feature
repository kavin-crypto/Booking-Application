Feature:Booking Application

  Scenario: Flight
    Given Invoking the browser with chrome
    And Navigate to "https://www.makemytrip.com/" site
    When Verifying whether logo is displayed or not
    And Selecting departure and destination location
    And selecting day and month
    Then Selecting special fare and verifying the selected location is displayed correctly

    Given Selecting hotels
    When User should select location
    And Selecting checkIn date,checkOut date and number of guest
    Then Click on search button
    And Filtering
    And Close the browser
