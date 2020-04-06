Feature: Cart

    Scenario: Clicking checkout brings you to the checkout page
        Given there are items in my basket
        And I am on the cart page
        When I click checkout 
        Then I am brought to the checkout page

    Scenario: Clicking continue shopping doesnt empty my basket
        Given there are items in my basket
        And I am on the cart page
        When I click continue shopping
        And I click on the cart logo
        Then the same number of items should be visible in my cart

    Scenario: Clicking checkout without any items
        Given I am on the cart page
        And there are no items in my cart
        When I click continue shopping
        Then an error message should pop up saying I need to add something to my cart

    

            