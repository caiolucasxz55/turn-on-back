# TurnOn / MusicApp - Guia de implantação e testes

Este repositório contém a API backend (Java + Spring Boot / Gradle) usada no projeto TurnOn / MusicApp.

Conteúdo adicionado nesta pasta:

- `docs/ScriptBanco.sql` - DDL (PostgreSQL) das tabelas.
- `scripts/run-local.sh` - Script para build e subir via `docker compose`.
- `scripts/deploy-azure-example.sh` - Script exemplo para deploy no Azure (usa `az` CLI).
- `.github/workflows/main_turnon-app-cp5.yml` - Workflow CI/CD (Gradle, Java 21) já configurado.
- `API_operations.json` - Exemplos de operações GET/POST/PUT/DELETE (no formato JSON).

## Requisitos

- Java 21 (JDK)
- Gradle (wrapper incluído: use `./gradlew`)
- Docker & Docker Compose (para execução local)
- Azure CLI (para deploy em Azure) — opcional
- Conta Azure e um App Service se desejar deploy automático

## Rodar localmente (modo recomendado)

1. Build do projeto:

```bash
./gradlew clean build -x test
```

2. Subir com Docker Compose:

```bash
./scripts/run-local.sh
```

3. Ver logs:

```bash
docker compose logs -f
```

4. A API ficará em `http://localhost:8080`.

## Variáveis de ambiente e secrets

Para rodar localmente com a stack Docker, o `docker-compose.yml` já define um serviço Postgres.

Para deploy (GitHub Actions / Azure) defina os seguintes secrets no repositório (Settings → Secrets):

- `SPRING_DATASOURCE_URL` — exemplo para SQL Server (produção):
  `jdbc:sqlserver://turnon.database.windows.net:1433;database=turnon;user=turnon@turnon;password=turnon;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;`
- `SPRING_DATASOURCE_USERNAME` — `turnon@turnon`
- `SPRING_DATASOURCE_PASSWORD` — `turnon`
- `AzureAppService_PublishProfile_<id>` — publish profile se usar deploy direto pela Action (o workflow atual já referencia o secret `AzureAppService_PublishProfile_f027bb274548417b8b32bd8151aa5be5`).

O workflow `.github/workflows/main_turnon-app-cp5.yml` exporta estes valores como `env` no job.

## Deploy no Azure (exemplo rápido)

1. Faça login na Azure CLI:

```bash
az login
```

2. Execute o script de exemplo (edite variáveis no topo do script):

```bash
./scripts/deploy-azure-example.sh
```

3. Configure as app settings na Web App (veja README e script para o comando `az webapp config appsettings set`).

## Testes manuais (exemplos)

API base local: `http://localhost:8080`

- Listar gêneros (GET):

```bash
curl -s http://localhost:8080/genres | jq
```

- Criar usuário (POST /auth/register):

```bash
curl -X POST http://localhost:8080/auth/register \
  -H 'Content-Type: application/json' \
  -d '{"name":"João","email":"joao@example.com","password":"123456"}'
```

- Login (POST /auth/login):

```bash
curl -X POST http://localhost:8080/auth/login \
  -H 'Content-Type: application/json' \
  -d '{"email":"joao@example.com","password":"123456"}'
```

Use o token retornado em `Authorization: Bearer <token>` para rotas protegidas.

Mais exemplos de payloads e respostas estão em `API_operations.json`.

## Script de Banco (migrations)

O projeto usa Flyway para migrations. Para rodar localmente com o banco Postgres (docker-compose) a aplicação aplicará as migrations automaticamente no startup.

Se desejar aplicar manualmente (usar `psql` no container):

```bash
docker exec -it musicapp-postgres psql -U turnon -d turnon -f /path/to/ScriptBanco.sql
```

## Testes automatizados

Executar unit/integration tests via Gradle:

```bash
./gradlew test
```

## Observações

- O repositório contém um `docker-compose.yml` que monta um Postgres para desenvolvimento.
- Em produção o sistema foi configurado para usar Azure SQL (string de conexão no `application.yml` e no CI).
