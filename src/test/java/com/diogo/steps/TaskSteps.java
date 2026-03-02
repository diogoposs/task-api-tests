package com.diogo.steps;

import com.diogo.service.TaskService;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import com.diogo.database.DatabaseUtils;

import static org.junit.jupiter.api.Assertions.*;

public class TaskSteps {

    TaskService taskService = new TaskService();
    Response response;
    int taskId;

    @Given("I have created a task with title {string}")
    @When("I create a task with title {string}")
    public void createTask(String title) {
        response = taskService.createTask(title);
        taskId = response.jsonPath().getInt("id");
    }

    @When("I retrieve the task by id")
    public void getTaskById() {
        response = taskService.getTaskById(taskId);
    }

    @When("I update the task title to {string}")
    public void updateTask(String newTitle) {
        response = taskService.updateTask(taskId, newTitle);
    }

    @When("I delete the task by id")
    public void deleteTask() {
        response = taskService.deleteTask(taskId);
    }

    @Then("the response status should be {int}")
    public void validateStatus(int statusCode) {
        assertEquals(statusCode, response.getStatusCode());
    }

    @Then("the field {string} should be {string}")
    public void validateField(String field, String expectedValue) {
        String actualValue = response.jsonPath().getString(field);
        assertEquals(expectedValue, actualValue);
    }

    @Then("the task should exist in the database")
    public void validateTaskInDatabase() throws Exception {

        String titleFromDatabase = DatabaseUtils.getTaskTitleById(taskId);

        assertNotNull(titleFromDatabase);
        assertEquals(response.jsonPath().getString("title"), titleFromDatabase);
    }
}