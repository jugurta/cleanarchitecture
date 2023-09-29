Feature: Adding a Person  to the catalog

  Scenario: A user publish a new Person
    Given A user publish a new Person named John
    When A user asks for the person with id 1
    Then The following person should be in the response content
      | id | firstName | lastName | birthDate  |
      | 1  | John      | Smith    | 2000-10-20 |