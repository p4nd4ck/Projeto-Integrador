package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Produto;

public class ProdutoDAO extends AbstractDAO {

     public Produto consultarProduto(String nome) {
        Connection conn = getConnection();
        String sql = "SELECT id, nome, valor FROM produtos WHERE nome = ?";
        Produto produto = null;
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
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
}
