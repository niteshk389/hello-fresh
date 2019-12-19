Feature: Booking api tests

  Scenario: Get all Bookings should return atleast 1 booking
    When I make request to fetch all bookings
    Then I get repsponse code as "200"
    And I get atleast 1 booking in response

  Scenario: Details of Bookings should match with details fetched using ID
    When I make request to fetch all bookings
    Then I get repsponse code as "200"
    And I get atleast 1 booking in response

  Scenario: A room cannot be booked more than once for a given date.
    When I make request to fetch all bookings
    Then I get repsponse code as "409"
    And I get atleast 1 booking in response

  Scenario: The check-out date must be greater than the check-in date.
    When I make request to fetch all bookings
    Then I get repsponse code as "409"
    And I get atleast 1 booking in response