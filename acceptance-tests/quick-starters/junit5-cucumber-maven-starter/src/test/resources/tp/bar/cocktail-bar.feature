Feature: cocktail bar

  Background: 
    Given the bar "Le Juste" is a cocktail bar
    And the bar has only 10 seats

  Scenario: Mr Pignon and Mr Leblanc are refused entry because the bar is almost full
    Given there are already 9 people in the bar
    When Mr Pignon and Mr Leblanc try to enter the bar
    Then they are denied entry

  Scenario: Mr Pignon and Mr Leblanc enter, order cocktails, and Mr Leblanc pays
    Given there are already 8 people in the bar
    When Mr Pignon and Mr Leblanc enter the bar
    Then the person behind them is denied entry
    When Mr Pignon orders the cocktail of the month at 10€
    And Mr Leblanc orders the cocktail of the month at 10€
    Then Mr Leblanc pays the full bill
    And the bill shows 20€
    And Mr Pignon is happy because he only had one drink
