TodoListAPI
===========

Esta é uma API simples de lista de tarefas (todo list), desenvolvida com o Spring Boot e banco de dados H2 em memória. O objetivo desta API é fornecer funcionalidades básicas de gerenciamento de tarefas, como criação, atualização, exclusão e busca.

Tecnologias Utilizadas
----------------------

*   Java 11
*   Spring Boot
*   H2 Database
*   Maven
*   Swagger

Como Executar
-------------

Para executar a API, você pode clonar este repositório em sua máquina local e executar o seguinte comando na raiz do projeto:

bashCopy code

`mvn spring-boot:run`


Endpoints
---------

A API possui os seguintes endpoints:

### Criação de Tarefas

bashCopy code

`POST /tarefas/list`

Cria uma nova tarefa.

### Atualização de Tarefas

bashCopy code

`PUT /tarefas/list/{id}`

Atualiza uma tarefa existente.

### Exclusão de Tarefas

bashCopy code

`DELETE /tarefas/list/{id}`

Exclui uma tarefa existente.

### Busca de Todas as Tarefas

bashCopy code

`GET /tarefas/list`

Retorna uma lista com todas as tarefas existentes.

### Busca de Tarefa por ID

bashCopy code

`GET /tarefas/list/{id}`

Retorna uma tarefa específica com base em seu ID.