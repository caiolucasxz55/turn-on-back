#!/usr/bin/env bash
set -euo pipefail

# Script para rodar a aplicação localmente via Docker Compose
# Requisitos: docker, docker-compose, Java 21 (para build local sem Docker)

echo "Building project (Gradle)..."
./gradlew clean build -x test

echo "Subindo serviços Docker (Postgres + app via Dockerfile)..."
docker compose up -d --build

echo "Aguarde a aplicação inicializar (logs abaixo). Para acompanhar:"
echo "  docker compose logs -f"

echo "Aplicação disponível em http://localhost:8080"
