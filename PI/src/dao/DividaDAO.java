package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Divida;

public class DividaDAO extends AbstractDAO {
    public void adicionarDivida(Divida divida) {
        Connection conn = getConnection();
        String sql = "INSERT INTO dividas (cliente_id, produto_id, valor, data) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, divida.getCliente().getId());
            stmt.setInt(2, divida.getProduto().getId());
            stmt.setDouble(3, divida.getValor());
            stmt.setDate(4, Date.valueOf(divida.getData()));
            stmt.executeUpdate();
            closeResources(conn, stmt);
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar dívida: " + e.getMessage());
        }
    }

    public void quitarDivida(Divida divida) {
        Connection conn = getConnection();
        String sql = "UPDATE dividas SET quitada = 1, data_quitacao = ? WHERE cliente_id = ? AND valor = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDate(1, Date.valueOf(divida.getData()));
            stmt.setInt(2, divida.getCliente().getId());
            stmt.setDouble(3, divida.getValor());
            stmt.executeUpdate();
            closeResources(conn, stmt);
        } catch (SQLException e) {
            System.out.println("Erro ao quitar dívida: " + e.getMessage());
        }
    }
}
