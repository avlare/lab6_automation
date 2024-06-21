Feature: Finding recipes by ingredient count

  Scenario: Find recipes by specific ingredient count
    Given the following recipes exist:
      | Recipe | Ingredients                                     |
      | Cake   | magic flour, purple eggs, owl milk, salt, sugar |
      | Baozi  | eggs, flour, milk, meat, salt                   |
    When I search for recipes with 5 ingredients
    Then I should find 2 recipes