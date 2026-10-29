# Task API Tests

Automação de testes de integração da [Task API](https://github.com/diogoposs/task-api), com cenários BDD, validação de respostas HTTP e consulta direta ao banco de dados.

**Tecnologias:** Java 17, Maven, REST Assured, Cucumber, JUnit 5 e MySQL JDBC.

## Cenários implementados

| Cenário                 | Verificações                                        |
| ----------------------- | --------------------------------------------------- |
| Criar tarefa            | Status 201 e título retornado                       |
| Consultar tarefa por ID | Status 200 e título retornado                       |
| Atualizar tarefa        | Status 200 e título atualizado                      |
| Excluir tarefa          | Status 204                                          |
| Validar persistência    | Status 201 e comparação do título entre API e MySQL |

Os cenários estão em [task.feature](src/test/resources/features/task.feature). A cobertura atual se concentra nos fluxos de sucesso.

## Executar localmente

Requisitos: JDK 17, Maven, MySQL e a [Task API](https://github.com/diogoposs/task-api) em execução em `http://localhost:8080`.

1. Prepare o banco e inicie a API conforme o README do backend.
2. Baixe a suíte:

   ```bash
   git clone https://github.com/diogoposs/task-api-tests.git
   cd task-api-tests
   ```

3. Confira a configuração de conexão em [DatabaseUtils.java](src/test/java/com/diogo/database/DatabaseUtils.java). A versão atual usa `taskdb` na porta 3306 e credenciais locais definidas no arquivo; ajuste-as ao seu ambiente e não publique credenciais reais.
4. Execute:

   ```bash
   mvn test
   ```

A URL HTTP utilizada está em [TaskService.java](src/test/java/com/diogo/service/TaskService.java). Alterar apenas `BaseTest` não altera essa URL.

## Organização

| Caminho                             | Responsabilidade                                |
| ----------------------------------- | ----------------------------------------------- |
| `src/test/resources/features/`      | Cenários em Gherkin                             |
| `src/test/java/com/diogo/runner/`   | Descoberta e execução do Cucumber               |
| `src/test/java/com/diogo/steps/`    | Ligação entre cenários, requisições e asserções |
| `src/test/java/com/diogo/service/`  | Requisições HTTP com REST Assured               |
| `src/test/java/com/diogo/database/` | Consulta SQL parametrizada por ID               |
| `src/test/java/com/diogo/base/`     | Configuração auxiliar de REST Assured           |

## Resultados

O Cucumber imprime os cenários no terminal. O Maven Surefire grava os resultados em `target/surefire-reports/`.

A configuração atual não gera relatório HTML do Cucumber nem mede percentual de cobertura de código.

## Limitações e evolução

- Os testes criam registros no banco e não incluem limpeza automática de todos os dados criados. Execute em um banco exclusivo de desenvolvimento/testes.
- Ainda não há cenários para entradas inválidas, tarefa inexistente ou consulta após exclusão.
- URLs e conexão JDBC estão definidas no código; externalizá-las facilita a execução em outros ambientes.
- A suíte ainda não possui workflow de CI neste repositório.

## Projeto relacionado

[Task API — backend Spring Boot](https://github.com/diogoposs/task-api)
