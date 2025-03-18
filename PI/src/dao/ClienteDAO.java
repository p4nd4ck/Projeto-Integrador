package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import model.Divida;
import model.Produto;
import model.Quitacao;

public class ClienteDAO extends AbstractDAO {

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
            System.out.println("Erro ao cadastrar cliente: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar cliente: " + e.getMessage());
        }
        
    }

    public Cliente consultarCliente(String nome) {
        Connection conn = getConnection();
        String sql = "SELECT * FROM clientes WHERE nome LIKE ?";
        Cliente cliente = null;
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int clienteId = rs.getInt("id");
                nome =  rs.getString("nome");
                String contato = rs.getString("contato");
                String endereco = rs.getString("endereco");
                String cpf = rs.getString("cpf");
                String rg = rs.getString("rg");
                LocalDate dataNascimento = rs.getDate("data_nascimento").toLocalDate();
         
                cliente = new Cliente(clienteId, nome, contato, endereco, cpf, rg, dataNascimento);
            }
            closeResources(conn, stmt);
        } catch (SQLException e) {
            System.out.println("Erro ao consultar cliente: " + e.getMessage());
        }

        return cliente;
    }

    public boolean excluirCliente(String nome) {
        Connection conn = getConnection();
        String sql = "DELETE FROM clientes WHERE nome = ?";
        boolean deletado = false;
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.executeUpdate();
            closeResources(conn, stmt);
            deletado = true;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir cliente: " + e.getMessage());
        }
        return deletado;
    }

    public Cliente gerarRelatorioCliente(String nome) {
        Connection conn = getConnection();
        
        // SQL Query - clientes, dividas, produtos
        String sqlDividas = "SELECT c.id AS cliente_id, c.nome, c.contato, c.endereco, c.cpf, c.rg, c.data_nascimento, " +
                            "d.id AS divida_id, d.data AS divida_data, " +
                            "p.id AS produto_id, p.nome AS produto_nome, p.valor AS produto_valor " +
                            "FROM clientes c " +
                            "LEFT JOIN dividas d ON c.id = d.cliente_id " +
                            "LEFT JOIN produtos p ON d.produto_id = p.id " +
                            "WHERE c.nome = ? " +
                            "ORDER BY d.data ASC";
        
        // SQL Query clientes, quitacoes
        String sqlQuitacoes = "SELECT c.id AS cliente_id, q.id AS quitacao_id, q.valor AS quitacao_valor, q.data AS quitacao_data " +
                              "FROM clientes c " +
                              "LEFT JOIN quitacoes q ON c.id = q.cliente_id " +
                              "WHERE c.nome = ?";
        
        Cliente cliente = null;
        List<Divida> dividas = new ArrayList<>();
        List<Quitacao> quitacoes = new ArrayList<>();
    
        try {
            //Dividas e Produtos
            PreparedStatement stmtDividas = conn.prepareStatement(sqlDividas);
            stmtDividas.setString(1, nome);
            ResultSet rsDividas = stmtDividas.executeQuery();
    
            while (rsDividas.next()) {
                if (cliente == null) {
                    cliente = new Cliente();
                    cliente.setId(rsDividas.getInt("cliente_id"));
                    cliente.setNome(rsDividas.getString("nome"));
                    cliente.setContato(rsDividas.getString("contato"));
                    cliente.setEndereco(rsDividas.getString("endereco"));
                    cliente.setCpf(rsDividas.getString("cpf"));
                    cliente.setRg(rsDividas.getString("rg"));
                    cliente.setDataNascimento(rsDividas.getDate("data_nascimento").toLocalDate());
                }
    
                int dividaId = rsDividas.getInt("divida_id");
                if (dividaId > 0) {
                    Divida divida = new Divida();
                    divida.setId(dividaId);
                    divida.setData(rsDividas.getDate("divida_data").toLocalDate());
                    
                    Produto produto = new Produto();
                    produto.setId(rsDividas.getInt("produto_id"));
                    produto.setNome(rsDividas.getString("produto_nome"));
                    produto.setValor(rsDividas.getDouble("produto_valor"));
                    divida.setProduto(produto);
    
                    dividas.add(divida);
                }
            }
    
            //Quitacoes
            PreparedStatement stmtQuitacoes = conn.prepareStatement(sqlQuitacoes);
            stmtQuitacoes.setString(1, nome);
            ResultSet rsQuitacoes = stmtQuitacoes.executeQuery();
    
            while (rsQuitacoes.next()) {
                if (cliente == null) {
                    cliente = new Cliente();
                    cliente.setId(rsQuitacoes.getInt("cliente_id"));
                    cliente.setNome(rsQuitacoes.getString("nome"));
                }
    
                int quitacaoId = rsQuitacoes.getInt("quitacao_id");
                if (quitacaoId > 0) {
                    Quitacao quitacao = new Quitacao();
                    quitacao.setId(quitacaoId);
                    quitacao.setValor(rsQuitacoes.getDouble("quitacao_valor"));
                    quitacao.setData(rsQuitacoes.getDate("quitacao_data").toLocalDate());
    
                    quitacoes.add(quitacao);
                }
            }
    
            if (cliente != null) {
                cliente.setDividas(dividas);
                cliente.setQuitacoes(quitacoes);
            }
    
            //Calcular dívida ou saldo
            double totalDividas = cliente.getDividas().stream().mapToDouble(divida -> divida.getProduto().getValor()).sum();
            double totalQuitacoes = cliente.getQuitacoes().stream().mapToDouble(Quitacao::getValor).sum();
            cliente.setDividaOuSaldo(totalQuitacoes - totalDividas);
    
            closeResources(conn, stmtDividas, rsDividas);
            closeResources(conn, stmtQuitacoes, rsQuitacoes);
            
        } catch (SQLException e) {
            System.out.println("Erro ao gerar relatório do cliente: " + e.getMessage());
        }
    
        return cliente;
    }
}
