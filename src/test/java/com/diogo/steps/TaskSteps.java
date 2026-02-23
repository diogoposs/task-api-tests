package com.diogo.steps;

import com.diogo.service.TaskService;
import io.cucumber.java.en.*;
import io.restassured.response.Response;

import static org.junit.jupiter.api.Assertions.*;

public class TaskSteps {

    TaskService taskService = new TaskService();
    Response response;
    int taskId;

    @Given("que eu criei uma tarefa com titulo {string}")
    @When("eu crio uma tarefa com titulo {string}")
    public void criarTarefa(String titulo) {
        response = taskService.createTask(titulo);
        taskId = response.jsonPath().getInt("id");
    }

    @When("eu busco a tarefa pelo id")
    public void buscarTarefa() {
        response = taskService.getTaskById(taskId);
    }

    @When("eu atualizo a tarefa para titulo {string}")
    public void atualizarTarefa(String novoTitulo) {
        response = taskService.updateTask(taskId, novoTitulo);
    }

    @When("eu deleto a tarefa pelo id")
    public void deletarTarefa() {
        response = taskService.deleteTask(taskId);
    }

    @Then("o status da resposta deve ser {int}")
    public void validarStatus(int statusCode) {
        assertEquals(statusCode, response.getStatusCode());
    }

    @Then("o campo {string} deve ser {string}")
    public void validarCampo(String campo, String valorEsperado) {
        String valorAtual = response.jsonPath().getString(campo);
        assertEquals(valorEsperado, valorAtual);
    }
}