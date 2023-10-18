# Sistema Lista de Tarefas - Backend

Este repositório contém o backend do sistema Lista de Tarefas construído com Spring Boot.

## Descrição

Desenvolvido para gerenciar tarefas, este backend fornece a API que suporta todas as operações CRUD necessárias para o sistema.


## Endpoints

**GET /api/tarefas**: Retorna todas as tarefas em uma página.

**GET /api/tarefas/{id}**: Retorna a tarefa com o ID especificado.

**POST /api/tarefas**: Cria uma nova tarefa.

**PUT /api/tarefas/{id}**: Atualiza a tarefa com o ID especificado.

**DELETE /api/tarefas/{id}**: Exclui a tarefa com o ID especificado.

**POST /api/tarefas/{id}/reordenar**: Reordena uma tarefa. O parâmetro moverParaCima define se a tarefa será movida para cima ou para baixo na lista.

**GET /api/tarefas/check-name**: Verifica se um nome de tarefa já existe.

**GET /api/tarefas/search**: Busca tarefas com base em um termo.

## Tecnologias utilizadas

- Java
- Spring Data JPA (para persistência de dados)
- Maven
- Spring
- Banco H2(Memória)

## Execução Local
```
1. Garanta que você tenha o Java e o Maven instalados.
2. git clone https://github.com/Ronaldop12/tarefa-backend.git` para clonar o repositório.
3. Navegue até a raiz do projeto.
4. Execute `mvn spring-boot:run` para iniciar a aplicação.
5. Acesse http://localhost:8080/api/tarefas (ou a porta configurada) para acessar a API.


```

Desenvolvido  por<br>
Ronaldo Pereira<br>
ronaldo.carpediem12@gmail.com<br>

