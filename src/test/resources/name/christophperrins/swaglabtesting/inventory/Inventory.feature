Feature: Inventory

    Scenario: Clicking add to cart on an item adds it to the cart
    Given I am on the inventory webpage
    When I click "ADD TO CART" on an item
    Then it should display 1 next to my cart
    When I click "ADD TO CART" on an item 
    Then it should display 2 next to my cart

    Scenario: Clicking add to cart on an item adds it to session storage
    Given I am on the inventory webpage
    When I click "ADD TO CART" on an item
    Then the session storage key "cart-contents" should be of length 1
    When I click "ADD TO CART" on an item
    Then the session storage key "cart-contents" should be of length 2

    Scenario: Clicking an inventory item takes me to the page about that item
    Given I am on the inventory webpage
    When i click on an inventory item
    Then i am brought to an inventory-item page with the same title
    
    Scenario: I click add to cart on multiple items and then view them in the cart
    Given I am on the inventory webpage
    When I click "ADD TO CART" on an item
    And I click "ADD TO CART" on an item
    And I click "ADD TO CART" on an item
    And I click on the cart icon
    Then I am taken to the cart webpage
    And there are three items inside

    # Scenario: I sort by alphabetically A to Z
    # Given I am on the inventory webpage
    # When I sort A to Z
    # Then i the inventory items should appear in alphabetical order

    # Scenario: I sort by alphabetically Z to A
    # Given I am on the inventory webpage
    # When I sort Z to A
    # Then i the inventory items should appear in reverse alphabetical order

    # Scenario: I sort price low to high
    # Given I am on the inventory webpage
    # When I sort low to high
    # Then i the inventory items should appear in order from least expensive to most expensive

    # Scenario: I sort price high to low
    # Given I am on the inventory webpage
    # When I sort high to low
    # Then I the inventory items should appear in order from most expensive to least expensive

    # Scenario: I am on a small screen
    # Given I am on the inventory webpage
    # When I am on a screen less than 1200 px in width
    # Then each item should appear below one another

    # Scenario: I am on a large screen
    # Given I am on the inventory webpage
    # When I am on a screen more than 1300 px in width
    # Then each item should be next to another item
    
     