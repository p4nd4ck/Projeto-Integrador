
# Sistema de Gestão de Dívidas

Este projeto é um sistema simples de gestão de dívidas de clientes, desenvolvido em Java. O sistema permite cadastrar clientes, adicionar dívidas, quitar dívidas, consultar e excluir clientes, além de gerar um extrato com o histórico de compras e pagamentos.

# teste






## 📌 Funcionalidades

1. **Cadastrar Cliente**: Permite o cadastro de um novo cliente com nome, contato, endereço, CPF, RG e data de nascimento.
2. **Consultar Cliente**: Busca um cliente pelo nome e exibe suas informações.
3. **Adicionar Dívida**: Adiciona uma nova dívida para um cliente, incluindo o nome do produto, valor e data.
4. **Quitar Dívida**: Permite que o cliente faça um pagamento parcial ou total da dívida, registrando a data da quitação.
5. **Excluir Cliente**: Remove um cliente do sistema.
6. **Extrato do Cliente**: Exibe o histórico de dívidas e pagamentos de um cliente.
7. **Sair**: Finaliza a execução do programa.

## 📂 Estrutura do Projeto

O projeto é composto pelos seguintes arquivos:

- **App.java**: Contém a classe principal que gerencia a interação com o usuário.
- **Cliente.java**: Representa um cliente e suas informações, incluindo dívidas e pagamentos.
- **Produto.java**: Representa um produto comprado pelo cliente.

## 🚀 Como Executar o Projeto

### **Pré-requisitos**
- Java instalado na máquina (versão 8 ou superior).

### **Passos para Execução**
1. Clone o repositório ou baixe os arquivos.
2. Compile os arquivos Java:
   ```sh
   javac App.java Cliente.java Produto.java
   ```
3. Execute o programa:
   ```sh
   java App
   ```

## 🌉 Estrutura das Classes

### **Classe App** (Gerenciamento do sistema)
- Contém um loop para interação com o usuário.
- Permite a execução das funcionalidades do sistema.

### **Classe Cliente** (Modelo de cliente)
- Armazena informações pessoais e dívidas do cliente.
- Possui listas de produtos adquiridos, datas das dívidas e valores quitados.

### **Classe Produto** (Modelo de produto)
- Contém informações sobre os produtos comprados, como nome e valor.

## 🔧 Melhorias Futuras
- Implementar persistência de dados (salvar informações em arquivos ou banco de dados).
- Criar uma interface gráfica para facilitar a interação com o usuário.
- Implementar autenticação para garantir mais segurança no acesso ao sistema.

## 📖 Referência
- Documentação oficial do Java
- Tutoriais de boas práticas de programação

## 🛠 Stack Utilizada
- **Java** - Linguagem principal do projeto.

## 📸 Screenshots
*(Em desenvolvimento - Aqui podem ser adicionadas capturas de tela do sistema rodando no terminal.)*

## 📒 Roadmap
- **Versão 1.0:** Funcionalidades básicas de cadastro e gestão de dívidas.
- **Versão 2.0:** Implementação de persistência de dados e melhorias na interface.
- **Versão 3.0:** Versão com interface gráfica e suporte a múltiplos usuários.

## 📋 Uso/Exemplos
### **Exemplo de Cadastro de Cliente**
```
Nome do Cliente: João Silva
Número para Contato: (11) 98765-4321
Endereço: Rua das Flores, 123
CPF: 123.456.789-00
RG: 12.345.678-9
Data de Nascimento: 15/08/1990
Cliente cadastrado com sucesso!
```

### **Exemplo de Adicionar Dívida**
```
Nome do Cliente: João Silva
Nome do Produto: Televisão
Valor do Produto: 1500.00
Data da Dívida: 01/02/2024
Dívida adicionada com sucesso!
```

## 🛠 Contribuindo
Pull requests são bem-vindos. Para mudanças importantes, abra uma issue primeiro para discutir o que você gostaria de alterar.

## ⚖️ Licença
MIT License

## 📞 Suporte
Para suporte, entre em contato via [email ou GitHub].

## 👤 Autores
Desenvolvido por [Seu Nome]

---

