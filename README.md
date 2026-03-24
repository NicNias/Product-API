<div align="center">

# 📦 Product API

![Status](https://img.shields.io/badge/STATUS-FINALIZADO-brightgreen?style=for-the-badge)

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)
![Prometheus](https://img.shields.io/badge/Prometheus-E6522C?style=for-the-badge&logo=prometheus&logoColor=white)

</div>

---

API REST para gerenciamento de produtos, desenvolvida como projeto de estudos de **Arquitetura Hexagonal** com **Domain-Driven Design (DDD)**.

---

## 🏛️ Arquitetura

O projeto aplica os princípios da **Arquitetura Hexagonal (Ports & Adapters)**, isolando a lógica de negócio de qualquer detalhe de infraestrutura. O domínio não conhece o banco de dados, o framework web nem qualquer tecnologia externa.

```
src/main/java/com/app/products/
│
├── domain/                         # Núcleo da aplicação (sem dependências externas)
│   ├── model/
│   │   └── Product.java            # Entidade de domínio com regras de negócio
│   ├── command/
│   │   └── CreateProductCommand.java
│   └── ports/
│       ├── in/                     # Portas de entrada (casos de uso)
│       │   ├── query/
│       │   │   ├── FindAllProductsPort.java
│       │   │   └── FindProductByIdPort.java
│       │   └── store/
│       │       ├── CreateProductPort.java
│       │       └── DeleteProductPort.java
│       └── out/                    # Portas de saída (repositórios)
│           └── repository/
│               └── ProductRepository.java
│
├── application/                    # Casos de uso (orquestra o domínio)
│   ├── usecase/
│   │   ├── CreateProductUseCase.java
│   │   ├── DeleteProductUseCase.java
│   │   ├── FindAllProductsUseCase.java
│   │   └── FindProductByIdUseCase.java
│   ├── dto/
│   │   └── ProductDto.java
│   ├── mappers/
│   │   └── ProductMapper.java
│   └── exceptions/
│       ├── BaseException.java
│       ├── BusinessException.java
│       └── product/
│           └── ProductNotFoundException.java
│
└── infrastructure/                 # Adaptadores (detalhes externos)
    ├── adapter/
    │   ├── in/
    │   │   └── web/
    │   │       ├── controllers/
    │   │       │   └── ProductController.java   # Adaptador HTTP (entrada)
    │   │       └── exceptions/
    │   │           └── GlobalExceptionHandler.java
    │   └── out/
    │       └── persistence/
    │           ├── ProductPersistenceAdapter.java  # Adaptador JPA (saída)
    │           ├── entity/
    │           │   └── ProductEntity.java
    │           ├── repository/
    │           │   └── JpaProductRepository.java
    │           └── mappers/
    │               └── ProductPersistenceMapper.java
    └── config/
        └── OpenApiConfig.java
```

### Conceitos aplicados

- **Domain Model** — A entidade `Product` contém validações de negócio no próprio construtor (preço não negativo, nome obrigatório), sem depender de frameworks.
- **Ports & Adapters** — As interfaces `*Port` definem contratos. Os use cases implementam as portas de entrada; os adapters implementam as portas de saída.
- **Commands** — Objetos imutáveis (`CreateProductCommand`) transportam a intenção do usuário até o domínio.
- **Separação de mappers** — Há mapeadores distintos para a camada de aplicação (DTO ↔ domínio) e para a camada de persistência (domínio ↔ entidade JPA).

---

## 🛠️ Tecnologias

| Tecnologia | Versão |
|---|---|
| Java | 21 |
| Spring Boot | 4.0.3 |
| Spring Data JPA | — |
| PostgreSQL | — |
| MapStruct | 1.5.5 |
| Lombok | — |
| Springdoc OpenAPI (Swagger) | 2.8.6 |
| Spring Boot Actuator & Micrometer | — |
| Gradle | — |

---

## ▶️ Como executar

### Pré-requisitos

- Java 21+
- PostgreSQL em execução
- Gradle (ou use o wrapper `./gradlew`)

### Configuração das variáveis de ambiente

A aplicação espera as seguintes variáveis de ambiente para conexão com o banco de dados:

| Variável | Descrição | Exemplo |
|---|---|---|
| `DB_URL` | URL JDBC do banco | `jdbc:postgresql://localhost:5432/products_db` |
| `DB_USERNAME` | Usuário do banco | `postgres` |
| `DB_PASSWORD` | Senha do banco | `postgres` |
| `DB_SCHEMA` | Schema do banco | `public` |

### Executando a aplicação

```bash
# Clone o repositório
git clone https://github.com/NicNias/Product-API.git
cd Product-API

# Execute com as variáveis de ambiente configuradas
export DB_URL=jdbc:postgresql://localhost:5432/products_db
export DB_USERNAME=postgres
export DB_PASSWORD=postgres
export DB_SCHEMA=public

./gradlew bootRun
```

A API estará disponível em: `http://localhost:8080/api-products`

---

## 📡 Endpoints

Base path: `/api-products/products`

| Método | Rota | Descrição | Status |
|---|---|---|---|
| `GET` | `/products` | Lista todos os produtos | `200 OK` |
| `GET` | `/products/{id}` | Busca produto por ID | `200 OK` |
| `POST` | `/products` | Cria um novo produto | `201 Created` |
| `DELETE` | `/products/{id}` | Remove um produto | `204 No Content` |

### Exemplo de payload — criação de produto

```json
{
  "name": "Teclado Mecânico",
  "description": "Teclado com switches Cherry MX Red",
  "price": 349.90
}
```

---

## 📖 Documentação interativa (Swagger)

Com a aplicação em execução, acesse:

```
http://localhost:8080/api-products/swagger-ui.html
```

---

## 📊 Métricas e Monitoramento

A aplicação utiliza o **Spring Boot Actuator** em conjunto com o **Micrometer** para expor métricas da JVM e da API em um formato compatível com o Prometheus.

Com a aplicação em execução, as métricas podem ser acessadas em:

```
http://localhost:8080/api-products/actuator/prometheus
```

Esses dados estão prontos para serem raspados (scraping) pelo **Prometheus** e visualizados em ferramentas como o **Grafana**.

---

## 🧪 Testes

```bash
./gradlew test
```

---

## 👤 Autor

<table>
  <tr>
    <td align="center">
      <a href="https://github.com/NicNias">
        <img src="https://avatars.githubusercontent.com/u/156093546?v=4" width="100px;" alt="Ananias Nicolau"/><br />
        <sub><b>Ananias Nicolau</b></sub>
      </a><br />
      <a href="https://github.com/NicNias">GitHub</a> •
      <a href="https://www.linkedin.com/in/naniasnic/">LinkedIn</a>
    </td>
  </tr>
</table>
