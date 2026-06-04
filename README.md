# GS Carbono API

API REST desenvolvida em **Java 17 + Spring Boot 3** para monitoramento e gestão de emissões de carbono, integrada ao contexto da **indústria espacial** — Global Solution FIAP 2025.

## Objetivo da Solução

A plataforma GS Carbono conecta dados de emissão de CO₂ de diferentes setores industriais com tecnologias espaciais (dados de satélite, monitoramento remoto) para permitir rastreamento, análise e relatórios de impacto ambiental em escala global. Alinhada aos ODS 9, 11 e 13 da ONU.

---

## Tecnologias

- Java 17
- Spring Boot 3.2.5
- Spring Data JPA + Hibernate
- MySQL 8
- ModelMapper
- SpringDoc OpenAPI (Swagger UI)
- Lombok

---

## Pré-requisitos

- Java 17+
- Maven 3.8+
- Docker (para o banco de dados)

---

## 1. Subindo o MySQL com Docker

```bash
docker run --name gs-carbono-mysql \
  -e MYSQL_ROOT_PASSWORD=root \
  -e MYSQL_DATABASE=gs_carbono \
  -p 3306:3306 \
  -d mysql:8.0
```

Aguarde alguns segundos para o container inicializar. Para verificar:

```bash
docker ps
```

---

## 2. Rodando a aplicação Spring Boot

Clone o repositório e execute:

```bash
# Na raiz do projeto
mvn clean install

mvn spring-boot:run
```

A aplicação sobe na porta **9000**.

---

## 3. Acessando a documentação

Após subir a aplicação, acesse:

```
http://localhost:9000/
```

Você será redirecionado automaticamente para o **Swagger UI**, onde todos os endpoints estão documentados e podem ser testados interativamente.

---

## 4. Endpoints disponíveis (versão 2.0)

| Recurso    | Base URL              | Métodos                        |
|------------|-----------------------|--------------------------------|
| Usuários   | `/v2/usuarios`        | GET, GET/{id}, POST, PUT, DELETE |
| Setores    | `/v2/setores`         | GET, GET/{id}, POST, PUT, DELETE |
| Emissões   | `/v2/emissoes`        | GET, GET/{id}, POST, PUT, DELETE |
| Relatórios | `/v2/relatorios`      | GET, GET/{id}, POST, PUT, DELETE |

---

## 5. Exemplo de payload — criar Emissão

```json
POST http://localhost:9000/v2/emissoes

{
  "fonteEmissao": "Lançamento de satélite - Rocket Lab",
  "quantidadeCo2Toneladas": 120.5,
  "dataEmissao": "2025-06-01",
  "tipoGas": "CO2",
  "descricao": "Emissão registrada via sensor orbital",
  "latitude": -23.5505,
  "longitude": -46.6333,
  "usuarioId": 1,
  "setorId": 1
}
```

---

## Configuração do banco

As credenciais padrão estão em `src/main/resources/application.properties`:

```
spring.datasource.url=jdbc:mysql://localhost:3306/gs_carbono
spring.datasource.username=root
spring.datasource.password=root
server.port=9000
```

O Hibernate cria/atualiza as tabelas automaticamente (`ddl-auto=update`).
