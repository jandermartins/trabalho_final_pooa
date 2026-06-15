# trabalho_final_pooa

# 🛒 Sistema de Vendas API - Spring Boot com SOLID

API REST para gestão de vendas, clientes, produtos e estoque, desenvolvida em Java 17 com Spring Boot 3, seguindo rigorosamente os **princípios SOLID**.

## 🚀 Tecnologias

- Java 17
- Spring Boot 3.2.5
- PostgreSQL (banco de dados)
- Spring Data JPA / Hibernate
- Spring Validation
- Lombok
- OpenAPI (Swagger UI) – bônus
- Maven

## 📦 Pré-requisitos

- JDK 17+
- PostgreSQL (local ou Docker)
- Maven (opcional, pode usar wrapper)

## 🗄️ Configuração do Banco de Dados

1. Crie um banco de dados no PostgreSQL:

```sql
CREATE DATABASE salesdb;

# 🛒 Sistema de Vendas API - Spring Boot com SOLID

API REST para gestão de vendas, clientes, produtos e estoque, desenvolvida em Java 17 com Spring Boot 3, seguindo rigorosamente os **princípios SOLID**.

## 🚀 Tecnologias

- Java 17
- Spring Boot 3.2.5
- PostgreSQL (banco de dados)
- Spring Data JPA / Hibernate
- Spring Validation
- Lombok
- OpenAPI (Swagger UI) – bônus
- Maven

## 📦 Pré-requisitos

- JDK 17+
- PostgreSQL (local ou Docker)
- Maven (opcional, pode usar wrapper)

## 🗄️ Configuração do Banco de Dados

1. Crie um banco de dados no PostgreSQL:

```sql
CREATE DATABASE salesdb;

Altere o arquivo src/main/resources/application.properties com suas credenciais:


spring.datasource.url=jdbc:postgresql://localhost:5432/salesdb
spring.datasource.username=postgres
spring.datasource.password=seu_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

▶️ Como executar

mvn clean spring-boot:run

A aplicação iniciará em http://localhost:8080.

