Feature: A description

  Scenario: Create an album
    Given The user wants to create an album with the artist "Camila", genre "Alternoraro", release year "2019", title "Camilita", track count ""
    When The post request to create an album is send
    Then The service should create the album
