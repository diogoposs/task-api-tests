Feature: Task Management

  Scenario: Should create a task successfully
    When I create a task with title "Study API"
    Then the response status should be 201
    And the field "title" should be "Study API"

  Scenario: Should retrieve a task by id
    Given I have created a task with title "Retrieve task"
    When I retrieve the task by id
    Then the response status should be 200
    And the field "title" should be "Retrieve task"

  Scenario: Should update a task successfully
    Given I have created a task with title "Old task"
    When I update the task title to "New task"
    Then the response status should be 200
    And the field "title" should be "New task"

  Scenario: Should delete a task successfully
    Given I have created a task with title "Task to delete"
    When I delete the task by id
    Then the response status should be 204

  Scenario: Create task and validate directly in database
    When I create a task with title "Database Validation"
    Then the response status should be 201
    And the task should exist in the database