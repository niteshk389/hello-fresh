Feature: Booking api tests

  Scenario: Get all Bookings should return atleast 1 booking
    When I make request to fetch all bookings
    Then I get repsponse code as "200"
    And I get at least 1 booking in response

  Scenario: Details of Bookings should match with details fetched using ID
    Given I make a bookings with below details
      | email     | hf_challenge_123456@hf123456.com |
      | firstname | Joe                              |
      | lastname  | Black                            |
      | phone     | 123456777777777                  |
      | roomid    | random                           |
      | checkin   | 2019-12-21T16:57:36.148Z         |
      | checkout  | 2019-12-22T16:57:36.148Z         |
    And I get repsponse code as "201"
    When I make request to fetch created booking by id
    Then I get repsponse code as "200"
    And booking details matches

  Scenario: A room cannot be booked more than once for a given date.
    Given I make a bookings with below details
      | email     | hf_challenge_123456@hf123456.com |
      | firstname | Joe                              |
      | lastname  | Black                            |
      | phone     | 123456777777777                  |
      | roomid    | 1                                |
      | checkin   | 2019-12-21T16:57:36.148Z         |
      | checkout  | 2019-12-22T16:57:36.148Z         |
    When I make a bookings with below details
      | email     | hf_challenge_1234567@hf123456.com |
      | firstname | Joe                               |
      | lastname  | Black                             |
      | phone     | 123456777777777                   |
      | roomid    | 1                                 |
      | checkin   | 2019-12-21T16:57:36.148Z          |
      | checkout  | 2019-12-22T16:57:36.148Z          |
    Then I get repsponse code as "409"

  Scenario: The check-out date must be greater than the check-in date.
    When I make a bookings with below details
      | email     | hf_challenge_1234567@hf123456.com |
      | firstname | Joe                               |
      | lastname  | Black                             |
      | phone     | 123456777777777                   |
      | roomid    | random                            |
      | checkin   | 2019-12-21T16:57:36.148Z          |
      | checkout  | 2019-12-21T16:57:36.148Z          |
    Then I get repsponse code as "409"
