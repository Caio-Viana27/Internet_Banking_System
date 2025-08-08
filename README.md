
# 💻 Internet Banking System

Este repositório contém um sistema bancário simples com arquitetura modular, incluindo um serviço dedicado para envio de e-mails. O objetivo é simular funcionalidades essenciais de um sistema bancário, como comunicação com usuários através de e-mails.

## 📦 Estrutura do Projeto

- **EmailSenderService/**: Serviço responsável por enviar e-mails (notificações, confirmações, etc.).
  - Usa Spring Boot
  - API REST para envio de e-mails
  - Integração com banco de dados
- Outros módulos podem ser adicionados para expandir o sistema (ex: gerenciamento de contas, autenticação, transferências bancárias).

## 🚀 Funcionalidades

- ✅ Envio de e-mails com base em dados fornecidos via API REST
- ✅ Armazenamento de status dos e-mails (ENVIADO, ERRO, etc.)
- ✅ DTOs para comunicação entre camadas
- ✅ Logs e estrutura padrão do Spring Boot

## 🔧 Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- Postgresql
- Maven

## 📂 Como rodar o projeto

### Pré-requisitos

- Java JDK 17+
- Maven

### Passos

```bash
# Clone o repositório
git clone https://github.com/Caio-Viana27/Internet_Banking_System.git
cd Internet_Banking_System/EmailSenderService

# Compile e rode o projeto
./mvnw spring-boot:run
```

A API REST ficará disponível em: `http://localhost:8080`

## 📬 Endpoints principais (EmailSenderService)

| Método | Endpoint       | Descrição                  |
|--------|----------------|----------------------------|
| POST   | /sending-email | Envia um e-mail            |

### Exemplo de Requisição

```json
POST /sending-email
{
  "ownerRef": "Banco XYZ",
  "emailFrom": "banco@xyz.com",
  "emailTo": "cliente@email.com",
  "subject": "Confirmação de Ação",
  "text": "Seu saldo foi atualizado com sucesso."
}
```

## 🧪 Testes

Execute os testes com:

```bash
./mvnw test
```
