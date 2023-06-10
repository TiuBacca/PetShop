#!/bin/bash
trap "echo 'Inicializacao interrompida pelo usuário (principal)';docker-compose down; exit" SIGINT SIGTERM
echo "Atualizando imagens das aplicações"
docker-compose pull
echo "Iniciando imagens"
docker-compose --compatibility -f docker-compose.yml up -d --build 
echo "Containers iniciados"