Feature: Checkout

    Scenario: I enter normal parameters and then click continue
        Given I am on the checkout page
        When I enter the firstname "Chris"
        And I enter the lastname "Perrins"
        And i enter the Zip/Postal code "DY82BG"
        And I click continue
        Then I am brought to the checkout overview page

    Scenario Outline: Scenario Outline name: I forget to enter a field
        Given I am on the checkout page
        When I enter the firstname <firstname>
        And I enter the lastname <lastname>
        And i enter the Zip/Postal code <zippostalcode>
        And I click continue
        Then an error message should pop up
    
        Examples:
        | firstname | lastname | zippostalcode |
        | "" | "Perrins" | "DY82BG" |
        | "Chris" | "" | "DY82BG" |
        | "Chris" | "Perrins" | "" |
            
    Scenario Outline: I put the wrong data in
        Given I am on the checkout page
        When I enter the firstname <firstname>
        And I enter the lastname <lastname>
        And i enter the Zip/Postal code <zippostalcode>
        And I click continue
        Then an error message should pop up
    
        Examples:
        | firstname | lastname | zippostalcode |
        | "DY82BG" | "Perrins" | "Chris" |
        | "Christopher Christopher Christopher Christopher Christopher Christopher Christopher Christopher" | "Perrins" | "DY82BG" |
        | "C" | "Perrins" | "DY82BG" |
        | "45" | "Perrins" | "DY82BG" |

            