# LicitMatch - Backend (API)

![Badge de Status](https://img.shields.io/badge/status-em%20desenvolvimento-yellow)
![Badge da Disciplina](https://img.shields.io/badge/disciplina-Projeto%20Integrador%20VI-blue)

## 📄 Sobre o Projeto

Este projeto se refere a API do sistema **LicitMatch** um sistema de gerenciamento de licitações. Esta API RESTful é responsável por toda a lógica de negócio, processamento de dados, comunicação com o banco de dados e por servir todas as informações necessárias para o [Frontend do LicitMatch](https://github.com/PedroAmbiel/licitmatch-frontend).

Este projeto foi desenvolvido como avaliação para a disciplina de **Projeto Integrador VI**.

---

## ✨ Funcionalidades da API

A API foi estruturada para ser consumida por dois tipos de clientes (Órgãos Públicos e Empresas), oferecendo os seguintes endpoints e funcionalidades:

### Autenticação e Usuários
* 👤 **Gerenciamento de Perfis:** Rotas para cadastro, login e obtenção de dados de usuários, com separação de perfis (empresa vs. órgão público).

### Gerenciamento de Licitações
* 📋 **Endpoints para Licitações:** Conjunto completo de rotas `CRUD` (Create, Read, Update, Delete) para que órgãos públicos possam gerenciar os processos licitatórios.
* 🔍 **Filtros e Busca:** Rotas públicas e privadas para listar e filtrar licitações por status, tipo, data, etc., permitindo que as empresas encontrem processos de seu interesse.
* ➕ **Inscrição de Empresas:** Endpoint para que uma empresa possa se registrar como participante em uma licitação específica.

### Módulo de Lances em Tempo Real
* 💸 **Registro de Lances:** Endpoint para receber, validar e registrar os lances das empresas em um processo licitatório.
* 📢 **Transmissão de Atualizações:** O servidor transmite automaticamente cada novo lance para todos os participantes conectados na sala daquela licitação, garantindo que todos tenham a informação mais recente.

---

## 💻 Tecnologias Utilizadas

Este projeto foi construído utilizando as seguintes tecnologias:

[Em Desenvolvimento]

---

## 🚀 Como Executar o Projeto

[Em Desenvolvimento]

---
👥 Equipe / Contribuidores Este projeto foi desenvolvido por:

AMANDA POLPETA TEODORO - RA23016417

JOSÉ LUIS ISAYAMA DE ANDRADE - RA23028145

PEDRO AMBIEL MASCHIETTO - RA23008811

RICARDO DE OLIVEIRA LEANDRO - RA22005234

VINÍCIUS JOSÉ DA SILVA - RA23006169
