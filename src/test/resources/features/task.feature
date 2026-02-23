Feature: Gerenciamento de tarefas

  Scenario: Criar tarefa com sucesso
    When eu crio uma tarefa com titulo "Estudar API"
    Then o status da resposta deve ser 201
    And o campo "title" deve ser "Estudar API"

  Scenario: Buscar tarefa por id
    Given que eu criei uma tarefa com titulo "Buscar tarefa"
    When eu busco a tarefa pelo id
    Then o status da resposta deve ser 200
    And o campo "title" deve ser "Buscar tarefa"

  Scenario: Atualizar tarefa
    Given que eu criei uma tarefa com titulo "Tarefa antiga"
    When eu atualizo a tarefa para titulo "Tarefa nova"
    Then o status da resposta deve ser 200
    And o campo "title" deve ser "Tarefa nova"

  Scenario: Deletar tarefa
    Given que eu criei uma tarefa com titulo "Para deletar"
    When eu deleto a tarefa pelo id
    Then o status da resposta deve ser 204