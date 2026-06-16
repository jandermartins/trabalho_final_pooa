```markdown
# 🛒 Sistema de Vendas API – SOLID com Spring Boot

## 📌 Descrição do Projeto

API RESTful para gestão de vendas, clientes, produtos e estoque, desenvolvida como trabalho prático da disciplina **Orientação a Objetos Avançada**.  
O projeto aplica de forma clara e estruturada os **cinco princípios SOLID**, utilizando **Spring Boot 2.7**, **H2 Database** e **Swagger** para documentação interativa.

---

## 🚀 Tecnologias Utilizadas

| Tecnologia          | Versão      |
|---------------------|-------------|
| Java                | 17 (LTS)    |
| Spring Boot         | 2.7.5       |
| Spring Data JPA     | 2.7.5       |
| H2 Database         | (runtime)   |
| Lombok              | 1.18.30     |
| Springdoc OpenAPI   | 1.6.15      |
| Maven               | 3.8+        |

---

## 📦 Pré‑requisitos

- **JDK 17** instalado e configurado (`JAVA_HOME`)
- **Maven** (ou utilizar o wrapper `mvnw` incluso no projeto)
- **Navegador** para acessar Swagger UI e H2 Console

---

## 🔧 Configuração e Execução

### 1. Clonar o repositório
```bash
git clone https://github.com/seu-usuario/trabalho_final.git
cd trabalho_final
```

### 2. Compilar e executar com Maven
```bash
mvn clean spring-boot:run
```

A aplicação iniciará na porta `8080`.

### 3. Acessar a aplicação
- **Swagger UI**: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- **H2 Console**: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
  - JDBC URL: `jdbc:h2:mem:vendas`
  - Usuário: `sa`
  - Senha: *(deixar vazio)*

---

## 📂 Estrutura de Pacotes

```
br.ce.crateus.fpo.trabalho_final/
├── TrabalhoFinalApplication.java          # Classe principal
├── config/
│   └── OpenApiConfig.java                 # Configuração do Swagger
├── controller/
│   ├── ClienteController.java
│   ├── ProdutoController.java
│   ├── EstoqueController.java
│   └── VendaController.java
├── service/
│   ├── IClienteService.java               # Interface
│   ├── ClienteServiceImpl.java            # Implementação
│   ├── IProdutoService.java
│   ├── ProdutoServiceImpl.java
│   ├── IEstoqueConsultaService.java       # Interface segregada (ISP)
│   ├── IEstoqueAtualizacaoService.java    # Interface segregada (ISP)
│   ├── EstoqueServiceImpl.java
│   ├── IVendaService.java
│   ├── VendaServiceImpl.java
│   ├── CalculadoraVenda.java              # Interface OCP
│   └── CalculadoraSemDesconto.java        # Implementação padrão
├── repository/
│   ├── ClienteRepository.java
│   ├── ProdutoRepository.java
│   ├── EstoqueRepository.java
│   └── VendaRepository.java
├── model/
│   ├── Cliente.java
│   ├── Produto.java                       # Classe abstrata (LSP)
│   ├── ProdutoPerecivel.java
│   ├── ProdutoNaoPerecivel.java
│   ├── Estoque.java
│   ├── Venda.java
│   ├── ItemVenda.java
│   └── enums/
│       └── Categoria.java
├── dto/
│   ├── request/
│   │   ├── ClienteRequestDTO.java
│   │   ├── ProdutoRequestDTO.java
│   │   ├── EstoqueUpdateDTO.java
│   │   ├── ItemVendaRequestDTO.java
│   │   └── VendaRequestDTO.java
│   └── response/
│       ├── ClienteResponseDTO.java
│       ├── ProdutoResponseDTO.java
│       ├── EstoqueResponseDTO.java
│       ├── ItemVendaResponseDTO.java
│       ├── VendaResponseDTO.java
│       └── ErroResponse.java
├── mapper/
│   ├── ClienteMapper.java
│   ├── ProdutoMapper.java
│   ├── EstoqueMapper.java
│   └── VendaMapper.java
└── exception/
    ├── RecursoNaoEncontradoException.java
    ├── EstoqueInsuficienteException.java
    └── GlobalExceptionHandler.java
```

---

## 📋 Endpoints da API

Todos os endpoints estão documentados no Swagger. Abaixo, uma lista completa com exemplos de payloads.

---

### 1. Clientes

| Método | Endpoint              | Descrição                |
|--------|-----------------------|--------------------------|
| POST   | `/api/clientes`       | Cadastrar cliente        |
| GET    | `/api/clientes`       | Listar todos             |
| GET    | `/api/clientes/{id}`  | Buscar por ID            |
| PUT    | `/api/clientes/{id}`  | Atualizar cliente        |
| DELETE | `/api/clientes/{id}`  | Remover cliente          |

**Exemplo de POST**:
```json
{
  "nome": "Maria Oliveira",
  "email": "maria@email.com",
  "cpf": "123.456.789-00",
  "telefone": "(85) 99999-1234"
}
```

**Exemplo de resposta (GET /api/clientes/1)**:
```json
{
  "id": 1,
  "nome": "Maria Oliveira",
  "email": "maria@email.com",
  "cpf": "123.456.789-00",
  "telefone": "(85) 99999-1234",
  "dataCadastro": "2026-06-16"
}
```

---

### 2. Produtos

| Método | Endpoint              | Descrição                         |
|--------|-----------------------|-----------------------------------|
| POST   | `/api/produtos`       | Cadastrar produto (estoque 0)     |
| GET    | `/api/produtos`       | Listar todos                      |
| GET    | `/api/produtos/{id}`  | Buscar por ID                     |
| PUT    | `/api/produtos/{id}`  | Atualizar dados do produto        |
| DELETE | `/api/produtos/{id}`  | Remover produto                   |

**Exemplo de POST (Produto Perecível)**:
```json
{
  "nome": "Iogurte Natural",
  "descricao": "Iogurte integral 200ml",
  "preco": 4.50,
  "categoria": "ALIMENTO",
  "tipo": "PERECIVEL",
  "dataValidade": "2025-12-31"
}
```

**Exemplo de POST (Produto Não Perecível)**:
```json
{
  "nome": "Notebook Dell",
  "descricao": "Core i7, 16GB RAM, SSD 512GB",
  "preco": 3500.00,
  "categoria": "ELETRONICO",
  "tipo": "NAO_PERECIVEL",
  "garantiaMeses": 12
}
```

**Exemplo de resposta**:
```json
{
  "id": 1,
  "nome": "Notebook Dell",
  "descricao": "Core i7, 16GB RAM, SSD 512GB",
  "preco": 3500.00,
  "categoria": "ELETRONICO",
  "tipo": "NAO_PERECIVEL",
  "atributoEspecifico": 12
}
```

---

### 3. Estoque

| Método | Endpoint                    | Descrição                         |
|--------|-----------------------------|-----------------------------------|
| GET    | `/api/estoque/{produtoId}`  | Consultar estoque de um produto   |
| GET    | `/api/estoque`              | Listar todos os estoques          |
| PUT    | `/api/estoque/{produtoId}`  | Atualizar quantidade em estoque   |

**Exemplo de PUT**:
```json
{
  "quantidade": 50
}
```

**Exemplo de resposta**:
```json
{
  "produtoId": 1,
  "produtoNome": "Notebook Dell",
  "quantidade": 50
}
```

---

### 4. Vendas

| Método | Endpoint                               | Descrição                            |
|--------|----------------------------------------|--------------------------------------|
| POST   | `/api/vendas`                          | Registrar nova venda                 |
| GET    | `/api/vendas`                          | Listar todas as vendas               |
| GET    | `/api/vendas/{id}`                     | Buscar venda por ID                  |
| GET    | `/api/vendas/cliente/{clienteId}`      | Listar vendas de um cliente          |

**Exemplo de POST**:
```json
{
  "clienteId": 1,
  "itens": [
    { "produtoId": 1, "quantidade": 2 },
    { "produtoId": 3, "quantidade": 1 }
  ]
}
```

**Exemplo de resposta**:
```json
{
  "id": 1,
  "clienteId": 1,
  "clienteNome": "Maria Oliveira",
  "data": "2026-06-16T12:30:00",
  "total": 180.00,
  "itens": [
    {
      "produtoId": 1,
      "produtoNome": "Notebook Dell",
      "quantidade": 2,
      "precoUnitario": 3500.00,
      "subtotal": 7000.00
    },
    {
      "produtoId": 3,
      "produtoNome": "Iogurte Natural",
      "quantidade": 1,
      "precoUnitario": 4.50,
      "subtotal": 4.50
    }
  ]
}
```

---

## 🧪 Tratamento de Erros

A API retorna respostas padronizadas para erros comuns:

| Status | Erro                     | Exemplo de corpo                                   |
|--------|--------------------------|----------------------------------------------------|
| 404    | Recurso não encontrado   | `{"status":404,"error":"Recurso não encontrado","message":"Cliente com ID 99 não encontrado"}` |
| 422    | Estoque insuficiente     | `{"status":422,"error":"Estoque insuficiente","message":"Produto 'Notebook' possui apenas 1 unidade(s)"}` |
| 400    | Dados inválidos          | `{"status":400,"error":"Erro de negócio","message":"E-mail já cadastrado"}` |

---

## 🧱 Princípios SOLID – Onde foram aplicados

| Princípio | Explicação | Localização no código |
|-----------|------------|------------------------|
| **S – Single Responsibility** | Cada classe tem uma única responsabilidade: Controller (HTTP), Service (regras), Repository (DB), Mapper (conversão), ExceptionHandler (erros). | `ClienteController`, `ClienteServiceImpl`, `ClienteRepository`, `ClienteMapper`, `GlobalExceptionHandler` |
| **O – Open/Closed** | Interface `CalculadoraVenda` permite novas estratégias de cálculo (desconto, taxa, etc.) sem modificar `VendaServiceImpl`. Basta criar uma nova implementação. | `CalculadoraVenda`, `CalculadoraSemDesconto` |
| **L – Liskov Substitution** | `Produto` é abstrata; `ProdutoPerecivel` e `ProdutoNaoPerecivel` podem ser usadas em qualquer lugar onde `Produto` é esperado (ex: `ProdutoServiceImpl`, `ProdutoMapper`). | `Produto`, `ProdutoPerecivel`, `ProdutoNaoPerecivel` |
| **I – Interface Segregation** | Interfaces de estoque segregadas: `IEstoqueConsultaService` (leitura) e `IEstoqueAtualizacaoService` (escrita). Nenhuma classe é forçada a implementar métodos que não usa. | `IEstoqueConsultaService`, `IEstoqueAtualizacaoService` |
| **D – Dependency Inversion** | Controllers dependem de interfaces de serviço; Services dependem de interfaces de repositório. Injeção via construtor com `@RequiredArgsConstructor`. | `ClienteController` depende de `IClienteService`; `ClienteServiceImpl` depende de `ClienteRepository` (interface) |

---

## 🧩 Decisões de Design

- **Herança com `SINGLE_TABLE`**: Facilita consultas polimórficas e exemplifica LSP.
- **Criação automática de estoque**: Ao salvar um `Produto`, um `Estoque` com quantidade `0` é criado (responsabilidade do serviço de produto).
- **DTOs separados**: Request e Response DTOs garantem que as entidades JPA não sejam expostas diretamente.
- **Tratamento global de exceções**: `GlobalExceptionHandler` padroniza respostas de erro (SRP).
- **Uso de Lombok**: Reduz código boilerplate (`@Data`, `@Builder`, `@RequiredArgsConstructor`).

---

## 📌 Configuração do Banco de Dados (H2)

O banco H2 em memória é criado automaticamente quando a aplicação sobe. Para visualizar os dados, acesse:

- **Console**: `http://localhost:8080/h2-console`
- **JDBC URL**: `jdbc:h2:mem:vendas`
- **Usuário**: `sa`
- **Senha**: (em branco)

As tabelas são geradas automaticamente com `ddl-auto=create-drop` (os dados são perdidos ao reiniciar a aplicação).

---

## 🔗 Acessando a Documentação Interativa (Swagger)

- **Swagger UI**: `http://localhost:8080/swagger-ui.html`
- **OpenAPI JSON**: `http://localhost:8080/api-docs`

A documentação descreve todos os endpoints, parâmetros, códigos de resposta e exemplos de payload.

---

## ✅ Possíveis Melhorias Futuras

- Adicionar autenticação e autorização (Spring Security).
- Migrar para PostgreSQL em produção.
- Implementar testes unitários e de integração.
- Adicionar paginação nas listagens.
- Incluir logs estruturados.
- Implementar estratégias diferentes de cálculo de venda (ex: desconto progressivo, impostos).

---

## 📎 Licença

Este projeto foi desenvolvido para fins acadêmicos – disciplina **Orientação a Objetos Avançada**.

