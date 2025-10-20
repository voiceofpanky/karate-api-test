Feature: Sample API test with Karate

  Background:
    * url 'https://jsonplaceholder.typicode.com'

  Scenario: Get user details by ID
    Given path 'users', 1
    When method GET
    Then status 200
    And match response.id == 1

  Scenario: Invalid user should return 404
    Given path 'users', 9999
    When method GET
    Then status 404
