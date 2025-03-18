-- Apagar o banco de dados se ele já existir (⚠️ CUIDADO: Isso removerá todos os dados!)
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

-- Modificar a coluna 'id' da tabela 'clientes' para garantir AUTO_INCREMENT
ALTER TABLE clientes MODIFY COLUMN id INT AUTO_INCREMENT;

-- Criar tabela Produto
CREATE TABLE IF NOT EXISTS produtos (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    valor DECIMAL(10,2) NOT NULL
);

-- Criar tabela Divida
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

-- Criar tabela Quitacao
CREATE TABLE IF NOT EXISTS quitacoes (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    cliente_id INT NOT NULL,
    valor DECIMAL(10,2) NOT NULL,
    data DATE NOT NULL,
    FOREIGN KEY (cliente_id) REFERENCES clientes(id) ON DELETE CASCADE
);

-- Inserção de dados de exemplo

-- Inserir um cliente
INSERT INTO clientes (nome, contato, endereco, cpf, rg, data_nascimento) 
VALUES ('João Silva', '123456789', 'Rua A, 123', '123.456.789-00', 'MG-12.345.678', '1980-01-01');

-- Inserir um produto
INSERT INTO produtos (nome, valor) 
VALUES ('Produto A', 10.00);

-- Inserir uma dívida (associada ao cliente e produto cadastrados)
INSERT INTO dividas (cliente_id, produto_id, data, valor, quitada) 
VALUES (1, 1, '2025-01-01', 10.00, 0);

-- Inserir uma quitação para o cliente
INSERT INTO quitacoes (cliente_id, valor, data) 
VALUES (1, 10.00, '2025-01-10');

-- Consultas para verificação

-- Selecionar todos os clientes
SELECT * FROM clientes;

-- Selecionar dívidas de um cliente específico pelo nome
SELECT * FROM dividas WHERE cliente_id = (SELECT id FROM clientes WHERE nome = 'João Silva');

-- Selecionar quitações de um cliente específico pelo nome
SELECT * FROM quitacoes WHERE cliente_id = (SELECT id FROM clientes WHERE nome = 'João Silva');

-- Selecionar informações completas de um cliente, incluindo dívidas e quitações pelo nome
SELECT 
    clientes.nome, 
    clientes.contato, 
    clientes.endereco, 
    dividas.valor AS valor_divida, 
    dividas.quitada, 
    quitacoes.valor AS valor_quitacao, 
    quitacoes.data AS data_quitacao
FROM clientes
LEFT JOIN dividas ON clientes.id = Divida.cliente_id
LEFT JOIN quitacoes ON clientes.id = Quitacao.cliente_id
WHERE clientes.nome = 'João Silva';
