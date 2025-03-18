-- Apagar o banco de dados se ele já existir
DROP DATABASE IF EXISTS gestao_dividas;

-- Criar o banco de dados
CREATE DATABASE gestao_dividas;
USE gestao_dividas;

-- Apagar tabelas caso já existam para evitar erros de duplicação
DROP TABLE IF EXISTS quitacoes;
DROP TABLE IF EXISTS dividas;
DROP TABLE IF EXISTS produtos;
DROP TABLE IF EXISTS clientes;

-- Criar tabela Cliente
CREATE TABLE IF NOT EXISTS clientes (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    contato VARCHAR(255) NOT NULL,
    endereco VARCHAR(255) NOT NULL,
    cpf VARCHAR(14) NOT NULL,
    rg VARCHAR(20) NOT NULL,
    data_nascimento DATE NOT NULL
);

-- Criar tabela produtos
CREATE TABLE IF NOT EXISTS produtos (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    valor DECIMAL(10,2) NOT NULL
);

-- Criar tabela dividas
CREATE TABLE IF NOT EXISTS dividas (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    cliente_id INT NOT NULL,
    produto_id INT NOT NULL,
    data DATE NOT NULL,
    valor DECIMAL(10,2) NOT NULL,
    quitada TINYINT(1) NOT NULL DEFAULT 0,
    data_quitacao DATE NULL,
    FOREIGN KEY (cliente_id) REFERENCES clientes(id) ON DELETE CASCADE,
    FOREIGN KEY (produto_id) REFERENCES produtos(id) ON DELETE CASCADE
);

-- Criar tabela quitacoes
CREATE TABLE IF NOT EXISTS quitacoes (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    cliente_id INT NOT NULL,
    valor DECIMAL(10,2) NOT NULL,
    data DATE NOT NULL,
    FOREIGN KEY (cliente_id) REFERENCES clientes(id) ON DELETE CASCADE
);

-- Inserir produtos
INSERT INTO produtos (nome, valor) VALUES ('Geladeira Frost Free', 3200);
INSERT INTO produtos (nome, valor) VALUES ('Fogão 4 Bocas', 1200);
INSERT INTO produtos (nome, valor) VALUES ('Máquina de Lavar 10kg', 2500);
INSERT INTO produtos (nome, valor) VALUES ('Micro-ondas 30L', 900);
INSERT INTO produtos (nome, valor) VALUES ('Aspirador de Pó', 700);
INSERT INTO produtos (nome, valor) VALUES ('Liquidificador', 250);
INSERT INTO produtos (nome, valor) VALUES ('Cafeteira Elétrica', 300);
INSERT INTO produtos (nome, valor) VALUES ('Sanduicheira', 200);
INSERT INTO produtos (nome, valor) VALUES ('Batedeira Planetária', 450);
INSERT INTO produtos (nome, valor) VALUES ('Forno Elétrico 45L', 1500);
INSERT INTO produtos (nome, valor) VALUES ('Ferro de Passar a Vapor', 280);
INSERT INTO produtos (nome, valor) VALUES ('Lavadora de Alta Pressão', 1100);
INSERT INTO produtos (nome, valor) VALUES ('Climatizador de Ar', 1300);
INSERT INTO produtos (nome, valor) VALUES ('Ar-Condicionado Split 12000 BTUs', 2800);
INSERT INTO produtos (nome, valor) VALUES ('Ventilador de Mesa', 250);
INSERT INTO produtos (nome, valor) VALUES ('Cervejeira', 2200);
INSERT INTO produtos (nome, valor) VALUES ('Purificador de Água', 850);
INSERT INTO produtos (nome, valor) VALUES ('Cooktop 5 Bocas', 1800);
INSERT INTO produtos (nome, valor) VALUES ('Exaustor de Cozinha', 750);
INSERT INTO produtos (nome, valor) VALUES ('Adega Climatizada 12 Garrafas', 2100);
INSERT INTO produtos (nome, valor) VALUES ('Churrasqueira Elétrica', 600);
INSERT INTO produtos (nome, valor) VALUES ('Fritadeira Air Fryer', 700);
INSERT INTO produtos (nome, valor) VALUES ('Processador de Alimentos', 550);
INSERT INTO produtos (nome, valor) VALUES ('Espremedor de Frutas', 280);
INSERT INTO produtos (nome, valor) VALUES ('Secadora de Roupas', 3500);
INSERT INTO produtos (nome, valor) VALUES ('Tanquinho 10kg', 750);
INSERT INTO produtos (nome, valor) VALUES ('Geladeira Side by Side', 7500);
INSERT INTO produtos (nome, valor) VALUES ('Forno de Embutir', 2800);
INSERT INTO produtos (nome, valor) VALUES ('Cooktop de Indução', 2300);
INSERT INTO produtos (nome, valor) VALUES ('Mixer 3 em 1', 320);
INSERT INTO produtos (nome, valor) VALUES ('Chaleira Elétrica', 200);
INSERT INTO produtos (nome, valor) VALUES ('Grill Elétrico', 480);
INSERT INTO produtos (nome, valor) VALUES ('Panela de Pressão Elétrica', 650);
INSERT INTO produtos (nome, valor) VALUES ('Lava-Louças 12 Serviços', 4200);
INSERT INTO produtos (nome, valor) VALUES ('Termoventilador', 430);
INSERT INTO produtos (nome, valor) VALUES ('Umidificador de Ar', 500);
INSERT INTO produtos (nome, valor) VALUES ('Desumidificador de Ar', 1300);
INSERT INTO produtos (nome, valor) VALUES ('Aquecedor Elétrico', 900);
INSERT INTO produtos (nome, valor) VALUES ('Bebedouro de Coluna', 1400);
INSERT INTO produtos (nome, valor) VALUES ('Torradeira', 300);
INSERT INTO produtos (nome, valor) VALUES ('Centrífuga de Alimentos', 800);
INSERT INTO produtos (nome, valor) VALUES ('Máquina de Waffle', 450);
INSERT INTO produtos (nome, valor) VALUES ('Sorveteira', 700);
INSERT INTO produtos (nome, valor) VALUES ('Fogão Industrial', 3800);
INSERT INTO produtos (nome, valor) VALUES ('Máquina de Gelo', 2500);
INSERT INTO produtos (nome, valor) VALUES ('Moedor de Carne Elétrico', 1300);
INSERT INTO produtos (nome, valor) VALUES ('Seladora a Vácuo', 900);
INSERT INTO produtos (nome, valor) VALUES ('Máquina de Pão', 750);

