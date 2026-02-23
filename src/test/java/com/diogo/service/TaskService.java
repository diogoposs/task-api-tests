package com.diogo.service;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TaskService {

    private final String BASE_URL = "http://localhost:8080/tasks";

    public Response createTask(String title) {
        return given()
                .contentType("application/json")
                .body("{\"title\":\"" + title + "\"}")
                .when()
                .post(BASE_URL);
    }

    public Response getTaskById(int id) {
        return given()
                .when()
                .get(BASE_URL + "/" + id);
    }

    public Response updateTask(int id, String title) {
        return given()
                .contentType("application/json")
                .body("{\"title\":\"" + title + "\"}")
                .when()
                .put(BASE_URL + "/" + id);
    }

    public Response deleteTask(int id) {
        return given()
                .when()
                .delete(BASE_URL + "/" + id);
    }
}