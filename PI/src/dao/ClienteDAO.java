package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import model.Produto;

public class ClienteDAO extends AbstractDAO {
    // ...existing code...
    

    // private Connection connect() {
    //     // Implement your connection logic here
    //     // For example, using DriverManager to get a connection
    //     Connection conn = null;
    //     try {
    //         // Replace with your database URL, username, and password
    //         String url = "jdbc:your_database_url";
    //         String user = "your_database_user";
    //         String password = "your_database_password";
    //         conn = DriverManager.getConnection(url, user, password);
    //     } catch (SQLException e) {
    //         System.out.println(e.getMessage());
    //     }
    //     return conn;
    // }

    public void cadastrarCliente(Cliente cliente) {
        Connection conn = getConnection();
        String sql = "INSERT INTO clientes (nome, contato, endereco, cpf, rg, data_nascimento) VALUES (?, ?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getContato());
            stmt.setString(3, cliente.getEndereco());
            stmt.setString(4, cliente.getCpf());
            stmt.setString(5, cliente.getRg());
            stmt.setDate(6, Date.valueOf(cliente.getDataNascimento()));
            stmt.executeUpdate();
            closeResources(conn, stmt);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
    }

    public Cliente consultarCliente(int id) {
        Cliente cliente = null;
        String sql = "SELECT * FROM clientes WHERE id = ?";  // Certifique-se de que a coluna 'id' exista na tabela 'clientes'
        
        try {
            Connection conn = getConnection();
            PreparedStatement pstmt  = conn.prepareStatement(sql);
            
            pstmt.setInt(1, id);
            ResultSet rs  = pstmt.executeQuery();
            
            if (rs.next()) {
                // Supondo que o Cliente tenha um construtor que aceite esses parâmetros
                cliente = new Cliente(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("contato"),
                    rs.getString("endereco"),
                    rs.getString("cpf"),
                    rs.getString("rg"),
                    rs.getDate("dataNascimento").toLocalDate()
                );
                closeResources(conn, pstmt, rs);
                // Preencha outros campos conforme necessário
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return cliente;
    }

    public List<Produto> consultarProdutosComprados(int clienteId) {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produtos WHERE cliente_id = ?";  // Certifique-se de que a coluna 'cliente_id' exista na tabela 'produtos'
        
        try {            
            Connection conn = getConnection();
            PreparedStatement pstmt  = conn.prepareStatement(sql);

            pstmt.setInt(1, clienteId);
            ResultSet rs  = pstmt.executeQuery();
            
            while (rs.next()) {
                Produto produto = new Produto(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getDouble("valor")
                );
                produtos.add(produto);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return produtos;
    }

    public void adicionarDivida(int clienteId, Produto produto, String data) {
        String sql = "INSERT INTO dividas (cliente_id, produto_id, data) VALUES (?, ?, ?)";
        
        try {
            Connection conn = getConnection();
            PreparedStatement pstmt  = conn.prepareStatement(sql);
            pstmt.setInt(1, clienteId);
            pstmt.setInt(2, produto.getId());
            pstmt.setString(3, data);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void quitarDivida(int clienteId, double valor, String dataQuitacao) {
        String sql = "UPDATE clientes SET divida = divida - ? WHERE id = ?";
        
        try {
            Connection conn = getConnection();
            PreparedStatement pstmt  = conn.prepareStatement(sql);
            pstmt.setDouble(1, valor);
            pstmt.setInt(2, clienteId);
            pstmt.executeUpdate();
            
            // Adiciona a quitação ao histórico
            String sqlHistorico = "INSERT INTO quitacoes (cliente_id, valor, data) VALUES (?, ?, ?)";
            try (PreparedStatement pstmtHistorico = conn.prepareStatement(sqlHistorico)) {
                pstmtHistorico.setInt(1, clienteId);
                pstmtHistorico.setDouble(2, valor);
                pstmtHistorico.setString(3, dataQuitacao);
                pstmtHistorico.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void excluirCliente(int clienteId) {
        String sql = "DELETE FROM clientes WHERE id = ?";
        
        try {
            Connection conn = getConnection();
            PreparedStatement pstmt  = conn.prepareStatement(sql);
            pstmt.setInt(1, clienteId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // ...existing code...
}