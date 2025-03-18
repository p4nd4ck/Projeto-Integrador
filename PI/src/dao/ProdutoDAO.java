package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Produto;

public class ProdutoDAO extends AbstractDAO {

     public Produto consultarProduto(String nome) {
        Connection conn = getConnection();
        String sql = "SELECT id, nome, valor FROM produtos WHERE nome LIKE ?";
        Produto produto = null;
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getDouble("valor")); 
            }
            closeResources(conn, stmt, rs);
        } catch (SQLException e) {
            System.out.println("Erro ao consultar produto: " + e.getMessage());
        }

        return produto;
    }

    public List<Produto> consultarProdutos() {
        Connection conn = getConnection();
        String sql = "SELECT id, nome, valor FROM produtos ORDER BY nome";
        List<Produto> produtos = new ArrayList<>();

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getDouble("valor"));
                produtos.add(produto);
            }
            closeResources(conn, stmt, rs);
        } catch (SQLException e) {
            System.out.println("Erro ao listar produtos: " + e.getMessage());
        }

        return produtos;
    }

}
