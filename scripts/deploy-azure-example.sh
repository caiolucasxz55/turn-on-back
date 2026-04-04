#!/usr/bin/env bash
set -euo pipefail

# Exemplo de deploy usando Azure CLI
# Preencha as variáveis abaixo ou exporte como env antes de executar.

RESOURCE_GROUP_NAME="rg-turnon"
WEBAPP_NAME="turnon-app-cp5"
APP_SERVICE_PLAN="plan-turnon"
LOCATION="brazilsouth"
RUNTIME="JAVA:21-java21"
APP_INSIGHTS_NAME="ai-turnon-app-cp5"

if ! command -v az >/dev/null 2>&1; then
  echo "Azure CLI não encontrado. Instale e faça login: az login"
  exit 1
fi

echo "Criando recursos (idempotente) e configurando appsettings..."

az group create --name "$RESOURCE_GROUP_NAME" --location "$LOCATION"

az monitor app-insights component create \
  --app "$APP_INSIGHTS_NAME" \
  --location "$LOCATION" \
  --resource-group "$RESOURCE_GROUP_NAME" \
  --application-type web || true

az appservice plan create --name "$APP_SERVICE_PLAN" --resource-group "$RESOURCE_GROUP_NAME" --location "$LOCATION" --sku F1 --is-linux || true

az webapp create --name "$WEBAPP_NAME" --resource-group "$RESOURCE_GROUP_NAME" --plan "$APP_SERVICE_PLAN" --runtime "$RUNTIME" || true

echo "Recuperando connection string do Application Insights..."
CONNECTION_STRING=$(az monitor app-insights component show --app "$APP_INSIGHTS_NAME" --resource-group "$RESOURCE_GROUP_NAME" --query connectionString --output tsv || echo "")

echo "Configure as seguintes variáveis de aplicação (na Web App ou via GitHub Secrets):"
echo "  SPRING_DATASOURCE_URL"
echo "  SPRING_DATASOURCE_USERNAME"
echo "  SPRING_DATASOURCE_PASSWORD"

echo "Exemplo: az webapp config appsettings set --resource-group $RESOURCE_GROUP_NAME --name $WEBAPP_NAME --settings SPRING_DATASOURCE_URL=\"<jdbc>\" SPRING_DATASOURCE_USERNAME=turnon@turnon SPRING_DATASOURCE_PASSWORD=turnon APPLICATIONINSIGHTS_CONNECTION_STRING=\"$CONNECTION_STRING\""

echo "Para fazer deploy do JAR, obtenha o arquivo build/libs/*.jar e use a action do GitHub (recomendado) ou:"
echo "  az webapp deploy --resource-group $RESOURCE_GROUP_NAME --name $WEBAPP_NAME --src-path build/libs/your.jar"
