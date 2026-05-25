# 🌴 Turismo MA API

> API REST para descoberta de destinos turísticos do Maranhão — construída com Java, Spring Boot e boas práticas de desenvolvimento backend.

---

## 📖 Sobre o Projeto

O **Turismo MA API** é um serviço backend que centraliza informações sobre pontos turísticos, eventos culturais e rotas de viagem do estado do Maranhão. O projeto nasceu da combinação entre experiência em desenvolvimento backend e vivência no universo audiovisual e criativo, trazendo um olhar centrado no usuário para a arquitetura de APIs.

Este projeto foi desenvolvido como portfólio técnico, demonstrando na prática o uso de tecnologias como Spring Boot, Spring Security com JWT, cache com Redis e containerização com Docker.

---

## 🚀 Tecnologias

| Camada | Tecnologia |
|---|---|
| Linguagem | Java 17 |
| Framework | Spring Boot 3 |
| Segurança | Spring Security + JWT |
| Banco de dados | MySQL 8 |
| Cache | Redis 7 |
| Documentação | Swagger (SpringDoc OpenAPI) |
| Container | Docker + Docker Compose |
| Testes | JUnit 5 + Mockito |
| Build | Maven |

---

## 📦 Funcionalidades

- ✅ CRUD completo de destinos turísticos
- ✅ Filtragem por categoria (Praia, Histórico, Cultural, Natureza...)
- ✅ Cadastro e listagem de eventos regionais
- ✅ Rotas turísticas sugeridas
- ✅ Sistema de avaliações com cálculo de média automático
- ✅ Autenticação e autorização com JWT
- ✅ Cache de listagens com Redis
- ✅ Documentação interativa com Swagger UI

---

## 🗂️ Estrutura do Projeto

```
src/
└── main/java/com/maranhao/turismoapi/
    ├── config/          # Configurações de segurança, Swagger e Redis
    ├── controller/      # Endpoints REST
    ├── service/         # Regras de negócio
    ├── repository/      # Acesso ao banco de dados
    ├── model/           # Entidades JPA
    ├── dto/             # Objetos de transferência de dados
    ├── exception/       # Tratamento global de erros
    └── security/        # Filtros JWT e UserDetails
```

---

## ⚙️ Como rodar localmente

### Pré-requisitos

- Java 17+
- Docker e Docker Compose
- Maven

### 1. Clone o repositório

```bash
git clone https://github.com/SEU_USUARIO/turismo-ma-api.git
cd turismo-ma-api
```

### 2. Suba os containers

```bash
docker compose up -d
```

Isso iniciará o MySQL e o Redis automaticamente.

### 3. Configure as variáveis de ambiente (opcional)

Por padrão, a aplicação usa valores locais. Para customizar, defina:

```bash
export DB_URL=jdbc:mysql://localhost:3306/turismo_ma
export DB_USERNAME=turismo
export DB_PASSWORD=turismo123
export REDIS_HOST=localhost
export REDIS_PORT=6379
```

### 4. Rode a aplicação

```bash
./mvnw spring-boot:run
```

### 5. Acesse o Swagger

```
http://localhost:8080/swagger-ui.html
```

---

## 🛣️ Endpoints principais

### Destinos
```
GET    /api/destinos                    Lista todos os destinos
GET    /api/destinos/{id}               Busca por ID
GET    /api/destinos/categoria/{cat}    Filtra por categoria
POST   /api/destinos                    Cria novo destino (admin)
PUT    /api/destinos/{id}               Atualiza destino (admin)
DELETE /api/destinos/{id}               Remove destino (admin)
```

### Eventos
```
GET    /api/eventos                     Lista todos os eventos
GET    /api/eventos/proximos            Eventos dos próximos 30 dias
POST   /api/eventos                     Cria novo evento (admin)
```

### Autenticação
```
POST   /api/auth/register               Cadastro de usuário
POST   /api/auth/login                  Login e geração de token JWT
```

---

## 🔐 Segurança

A API utiliza autenticação stateless com **JWT (JSON Web Token)**. Para acessar endpoints protegidos, inclua o token no header:

```
Authorization: Bearer {seu_token}
```

---

## 📊 Categorias disponíveis

```
PRAIA · HISTORICO · CULTURAL · NATUREZA · GASTRONOMIA · RELIGIOSO · AVENTURA
```

---

## 🧪 Rodando os testes

```bash
./mvnw test
```

---

## 🐳 Docker Compose

O arquivo `docker-compose.yml` sobe dois serviços:

| Serviço | Porta |
|---|---|
| MySQL | 3306 |
| Redis | 6379 |

---

## 👨‍💻 Autor

**Aécio Guterres**
Desenvolvedor Back-End Java | Spring Boot | APIs REST

[![LinkedIn](https://img.shields.io/badge/LinkedIn-aecioguterres-blue?style=flat&logo=linkedin)](https://www.linkedin.com/in/aecioguterres)

---

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.