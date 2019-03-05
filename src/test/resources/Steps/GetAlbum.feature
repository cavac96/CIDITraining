Feature: Login

 Scenario: Get the the song
    Given I look for album with Id "1199777c-d56d-431f-809a-69e69f493e40"
    When The album api is called
    Then The song information must be shown
