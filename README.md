# 🚀 Workshop Spring Boot - REST API & Domain Model

<p align="center">
  <b>API RESTful robusta desenvolvida em Java 21 e Spring Boot para gerenciamento de pedidos, usuários, produtos e categorias de e-commerce.</b>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk" alt="Java 21">
  <img src="https://img.shields.io/badge/Spring%20Boot-4.1.1-brightgreen?style=for-the-badge&logo=springboot" alt="Spring Boot">
  <img src="https://img.shields.io/badge/Spring%20Data%20JPA-ORM-blue?style=for-the-badge&logo=spring" alt="Spring Data JPA">
  <img src="https://img.shields.io/badge/H2%20Database-In--Memory-lightgrey?style=for-the-badge&logo=h2" alt="H2 Database">
  <img src="https://img.shields.io/badge/PostgreSQL-Ready-blueviolet?style=for-the-badge&logo=postgresql" alt="PostgreSQL">
  <img src="https://img.shields.io/badge/Maven-Build-red?style=for-the-badge&logo=apachemaven" alt="Maven">
</p>

---

## 📌 Sumário
1. [Sobre o Projeto](#-sobre-o-projeto)
2. [Arquitetura do Sistema](#-arquitetura-do-sistema)
3. [Tecnologias Utilizadas](#-tecnologias-utilizadas)
4. [Modelo de Domínio & Diagrama Conceitual](#-modelo-de-domínio--diagrama-conceitual)
5. [Estrutura de Pacotes](#-estrutura-de-pacotes)
6. [Endpoints da API REST](#-endpoints-da-api-rest)
   - [Usuários (`/users`)](#users-users)
   - [Categorias (`/categories`)](#categories-categories)
   - [Produtos (`/products`)](#products-products)
   - [Pedidos (`/orders`)](#pedidos-orders)
7. [Tratamento de Exceções](#-tratamento-de-exceções)
8. [Configuração do Banco de Dados & H2 Console](#-configuração-do-banco-de-dados--h2-console)
9. [Como Executar o Projeto](#-como-executar-o-projeto)
10. [População Inicial de Dados (Seeding)](#-população-inicial-de-dados-seeding)
11. [Boas Práticas e Padrões Aplicados](#-boas-práticas-e-padrões-aplicados)

---

## 📖 Sobre o Projeto

Este repositório contém o desenvolvimento prático de uma aplicação backend baseada em arquitetura de microsserviços/monolito modular com **Spring Boot**. O sistema simula um ambiente completo de comércio eletrônico (*e-commerce*), permitindo o gerenciamento de clientes cadastrados, catálogo de produtos organizados por categorias, emissão e controle de status de pedidos, além de registros de pagamentos associados.

O projeto foi estruturado seguindo os padrões de desenvolvimento Java, priorizando a separação de responsabilidades (SoC), injeção de dependências desacoplada, mapeamento objeto-relacional (ORM) e tratamento centralizado de exceções HTTP.

---

## 🏛️ Arquitetura do Sistema

A aplicação adota o padrão arquitetural em **Camadas (Layered Architecture)**, garantindo alta coesão e baixo acoplamento entre os componentes:

```
┌────────────────────────────────────────┐
│         Presentation Layer             │  <-- REST Controllers (@RestController)
├────────────────────────────────────────┤
│           Business Layer               │  <-- Services (@Service)
├────────────────────────────────────────┤
│          Persistence Layer             │  <-- Spring Data JPA Repositories (@Repository)
├────────────────────────────────────────┤
│            Domain Layer                │  <-- JPA Entities & Value Objects (@Entity)
└────────────────────────────────────────┘
```

- **Controllers (`com.dev.workshop.controllers`)**: Exposição dos endpoints REST, recebimento de requisições HTTP (`GET`, `POST`, `PUT`, `DELETE`), validação básica e retorno de respostas formatadas em `ResponseEntity`.
- **Services (`com.dev.workshop.services`)**: Implementação das regras de negócio, transações, chamadas aos repositórios e tratamento de exceções de domínio.
- **Repositories (`com.dev.workshop.repositories`)**: Interfaces estendendo `JpaRepository` para operações CRUD automáticas e consultas personalizadas.
- **Entities (`com.dev.workshop.entities`)**: Modelagem das tabelas do banco de dados utilizando anotações JPA (`@Entity`, `@Table`, `@ManyToOne`, `@OneToMany`, `@ManyToMany`).
- **Config (`com.dev.workshop.config`)**: Configurações de beans, perfis de execução (`test`, `dev`, `prod`) e população de dados via `CommandLineRunner`.

---

## 🛠️ Tecnologias Utilizadas

- **Java 21**: Linguagem principal utilizando recursos modernos da plataforma.
- **Spring Boot (v4.1.1)**: Framework base para agilizar a configuração e inicialização da aplicação web.
- **Spring Data JPA / Hibernate**: Persistência de dados e mapeamento Objeto-Relacional (ORM).
- **H2 Database**: Banco de dados relacional em memória utilizado para testes rápidos e desenvolvimento.
- **PostgreSQL**: Driver configurado para suporte a banco de dados relacional em produção.
- **Spring Boot Actuator**: Monitoramento e métricas da saúde da aplicação.
- **Maven**: Gerenciamento de dependências e build do projeto (`mvnw`).

---

## 📊 Modelo de Domínio & Diagrama Conceitual

O sistema modela o ecossistema de vendas online através das seguintes entidades principais:

1. **User (Usuário)**: Representa o cliente que realiza compras no sistema.
   - Relacionamento `1-N` com `Order` (Um usuário pode fazer vários pedidos).
2. **Order (Pedido)**: Representa a compra efetuada por um usuário.
   - Contém momento do pedido (`Instant`), status (`OrderStatus`: `WAITING_PAYMENT`, `PAID`, `SHIPPED`, `DELIVERED`, `CANCELED`).
   - Relacionamento `N-1` com `User`.
   - Relacionamento `1-1` com `Payment`.
   - Relacionamento `1-N` com `OrderItem`.
3. **Payment (Pagamento)**: Representa a transação financeira de um pedido.
   - Contém momento do pagamento (`Instant`).
4. **Product (Produto)**: Itens disponíveis para compra no catálogo.
   - Contém nome, descrição, preço e URL de imagem.
   - Relacionamento `N-N` com `Category` através de tabela associativa.
5. **Category (Categoria)**: Classificação dos produtos.
   - Relacionamento `N-N` com `Product`.
6. **OrderItem (Item de Pedido)**: Entidade associativa entre `Order` e `Product`.
   - Contém quantidade, preço unitário no momento da compra e chave primária composta (`OrderItemPK`).

---

## 📁 Estrutura de Pacotes

```text
src/main/java/com/dev/workshop/
├── WorkshopSpringbootApplication.java
├── config/
│   └── TestConfig.java
├── controllers/
│   ├── CategoryController.java
│   ├── OrderController.java
│   ├── ProductController.java
│   ├── UserController.java
│   └── exceptions/
│       ├── ResourceExceptionHandler.java
│       └── StandardError.java
├── entities/
│   ├── Category.java
│   ├── Order.java
│   ├── OrderItem.java
│   ├── Payment.java
│   ├── Product.java
│   ├── User.java
│   ├── enums/
│   │   └── OrderStatus.java
│   └── pk/
│       └── OrderItemPK.java
├── repositories/
│   ├── CategoryRepository.java
│   ├── OrderItemRepository.java
│   ├── OrderRepository.java
│   ├── ProductRepository.java
│   └── UserRepository.java
└── services/
    ├── CategoryService.java
    ├── OrderService.java
    ├── ProductService.java
    ├── UserService.java
    └── exceptions/
        ├── DatabaseException.java
        └── ResourceNotFoundException.java
```

---

## 🔌 Endpoints da API REST

### 👤 Usuários (`/users`)
| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| `GET` | `/users` | Retorna lista completa de usuários cadastrados |
| `GET` | `/users/{id}` | Busca um usuário específico pelo ID |
| `POST` | `/users` | Cadastra um novo usuário no sistema |
| `PUT` | `/users/{id}` | Atualiza os dados de um usuário existente |
| `DELETE` | `/users/{id}` | Remove um usuário pelo ID |

### 🏷️ Categorias (`/categories`)
| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| `GET` | `/categories` | Retorna todas as categorias de produtos |
| `GET` | `/categories/{id}` | Busca uma categoria específica pelo ID |

### 📦 Produtos (`/products`)
| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| `GET` | `/products` | Retorna lista de todos os produtos |
| `GET` | `/products/{id}` | Busca um produto específico pelo ID |

### 🛒 Pedidos (`/orders`)
| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| `GET` | `/orders` | Retorna todos os pedidos realizados |
| `GET` | `/orders/{id}` | Busca um pedido detalhado pelo ID |

---

## 🚨 Tratamento de Exceções

A aplicação possui um mecanismo centralizado de tratamento de erros através da anotação `@ControllerAdvice` (`ResourceExceptionHandler`), que captura exceções lançadas nas camadas de serviço e retorna respostas HTTP padronizadas com o DTO `StandardError`:

- **`ResourceNotFoundException` (HTTP 404 - Not Found)**: Retornado quando um recurso solicitado (usuário, produto, pedido ou categoria) não é encontrado no banco de dados.
- **`DatabaseException` (HTTP 400 - Bad Request)**: Retornado quando ocorre violação de integridade referencial ao tentar excluir registros associados a outras tabelas.

Exemplo de resposta de erro JSON:
```json
{
  "timestamp": "2026-08-30T21:00:00Z",
  "status": 404,
  "error": "Resource not found",
  "message": "Resource not found. Id 99",
  "path": "/users/99"
}
```

---

## ⚙️ Configuração do Banco de Dados & H2 Console

Por padrão, a aplicação está configurada para utilizar o banco de dados em memória **H2 Database** sob o perfil `test` (`application-test.yaml`).

### Acessando o Console H2:
1. Inicie a aplicação.
2. Acesse no navegador: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
3. Utilize as seguintes credenciais de conexão:
   - **JDBC URL**: `jdbc:h2:mem:testdb`
   - **User Name**: `sa`
   - **Password**: *(vazio)*

---

## 🚀 Como Executar o Projeto

### Pré-requisitos
- **Java Development Kit (JDK 21+)** instalado na máquina.
- **Maven** (ou utilize o Maven Wrapper incluído no repositório).

### Passos para execução:
1. Clone o repositório ou navegue até a pasta raiz do projeto:
   ```bash
   cd /home/walasse/Dockers/studies/workshop-springboot
   ```

2. Compile e execute a aplicação utilizando o Maven Wrapper:
   - No **Linux/macOS**:
     ```bash
     ./mvnw spring-boot:run
     ```
   - No **Windows**:
     ```cmd
     mvnw.cmd spring-boot:run
     ```

3. A aplicação estará disponível em `http://localhost:8080`.

---

## 🌱 População Inicial de Dados (Seeding)

Para facilitar testes imediatos, a classe `TestConfig.java` implementa a interface `CommandLineRunner` (ativa apenas no perfil `test`). Ao iniciar a aplicação, ela popula automaticamente o banco de dados com:
- **2 Usuários de teste** (*Maria Brown*, *Alex Green*).
- **3 Categorias** (*Electronics*, *Books*, *Computers*).
- **5 Produtos** associados às suas respectivas categorias.
- **3 Pedidos** vinculados aos usuários com diferentes status de pagamento (`PAID`, `WAITING_PAYMENT`).
- **Itens de Pedido** e registros de **Pagamento**.

---

## 💡 Boas Práticas e Padrões Aplicados

- **Injeção de Dependências por Construtor**: Utilização de construtores em vez de `@Autowired` em campos, promovendo imutabilidade e facilidade em testes unitários.
- **DTO / Entidade Separation**: Isolamento das entidades de domínio e exposição controlada de dados.
- **Clean Code & SOLID**: Separação clara de responsabilidades entre as camadas da aplicação.
