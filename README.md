TodoListAPI
===========

Esta é uma API simples de lista de tarefas (todo list), desenvolvida com o Spring Boot e banco de dados H2 em memória. O objetivo desta API é fornecer funcionalidades básicas de gerenciamento de tarefas, como criação, atualização, exclusão e busca.

Tecnologias Utilizadas
----------------------

*   Java 17
*   Spring Boot
*   H2 Database
*   Maven

Como Executar
-------------

Para executar a API, você pode clonar este repositório em sua máquina local e executar o seguinte comando na raiz do projeto:

`mvn spring-boot:run`

Modelo de Dados
---------------

Para criar uma lista de tarefas você deve definir o objeto com os seguintes campos:

```
{
    "descricao": "Lista de ferramentas para comprar",
    "dataLimite": "2023-04-27",
    "tarefas": [
        {
            "descricao": "Comprar martelo",
            "done": false
        },
        {
            "descricao": "Comprar maquita",
            "done": false
        }
    ]
}
```
Endpoints
---------

A API possui os seguintes endpoints:

### Criação de Tarefas

`POST /tarefas/list`

Cria uma nova tarefa.

### Atualização de Tarefas

`PUT /tarefas/list/{id}`

Atualiza uma tarefa existente.

### Exclusão de Tarefas

`DELETE /tarefas/list/{id}`

Exclui uma tarefa existente.

### Busca de Todas as Tarefas

`GET /tarefas/list`

Retorna uma lista com todas as tarefas existentes.

### Busca de Tarefa por ID

`GET /tarefas/list/{id}`

Retorna uma tarefa específica com base em seu ID.
