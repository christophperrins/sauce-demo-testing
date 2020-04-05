Feature: Login

  Scenario Outline: Scenario Outline name: Entering in the correct credentials for a user and clicking login
    Given you are on the login page
    When you enter the username <username>
    When you enter the password <password>
    When you click login button
    Then the application should bring you to the inventory screen

    Examples:
    | username | password |
    |    "standard_user" |   "secret_sauce" |
    |    "problem_user" |   "secret_sauce" |
    |    "performance_glitch_user" |   "secret_sauce" |

  Scenario Outline: Scenario Outline name: Entering in the correct credentials for a user and submit with enter key
    Given you are on the login page
    When you enter the username <username>
    When you enter the password <password>
    When you hit the enter key in the password input
    Then the application should bring you to the inventory screen

    Examples:
    | username | password |
    |    "standard_user" |   "secret_sauce" |
    |    "problem_user" |   "secret_sauce" |
    |    "performance_glitch_user" |   "secret_sauce" |

  Scenario: A locked out user tries to login
    Given you are on the login page
    When you enter the username "locked_out_user"
    When you enter the password "secret_sauce"
    When you click login button
    Then an error message should pop up stating you are locked out