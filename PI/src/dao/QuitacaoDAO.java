package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Quitacao;

public class QuitacaoDAO extends AbstractDAO {
    public void adicionarQuitacao(Quitacao quitacao) {
        Connection conn = getConnection();
        String sql = "INSERT INTO quitacoes (cliente_id, valor, data) VALUES (?, ?, ?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, quitacao.getCliente().getId());
            stmt.setDouble(2, quitacao.getValor());
            stmt.setDate(3, Date.valueOf(quitacao.getData()));
            stmt.executeUpdate();
            closeResources(conn, stmt);
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar quitação: " + e.getMessage());
        }
    }
}
