🏦 Internet Banking System

Sistema bancário completo desenvolvido com Java e Spring Boot, oferecendo funcionalidades essenciais para operações bancárias digitais como gerenciamento de contas, transações financeiras e envio de notificações por e-mail.
📌 Funcionalidades

    🧑 Cadastro e gerenciamento de usuários

    💼 Abertura e gerenciamento de contas bancárias

    💳 Transações: depósito, saque e transferência entre contas

    🧾 Consulta de saldo e extrato bancário

    📬 Envio de notificações por e-mail (confirmação de transações, alertas, etc.)

    🔐 Autenticação e segurança de acesso via Spring Security

## 🔧 Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- Postgresql
- Maven

A API REST ficará disponível em: `http://localhost:8080`

## Endpoints principais

| Método | Endpoint               | Descrição                     |
| ------ | ---------------------- | ----------------------------- |
| POST   | /users                 | Cadastrar novo usuário        |
| GET    | /users/{id}            | Buscar informações do usuário |
| POST   | /accounts              | Criar nova conta bancária     |
| GET    | /accounts/{id}         | Consultar dados da conta      |
| POST   | /transactions/deposit  | Realizar um depósito          |
| POST   | /transactions/withdraw | Realizar um saque             |
| POST   | /transactions/transfer | Realizar uma transferência    |
| GET    | /accounts/{id}/history | Consultar extrato da conta    |
