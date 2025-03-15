package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import model.Cliente;
import model.Quitacao;

public class QuitacaoService {
    private static Connection connection;

    static {
        try {
            // Carregar o driver JDBC do MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestao_dividas", "root", "root");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void quitarDivida(Cliente cliente, double valor, Date data) {
        Quitacao quitacao = new Quitacao();
        quitacao.setValor(valor);
        quitacao.setData(data);
        cliente.quitarDivida(quitacao);

        try {
            String sql = "UPDATE dividas SET quitada = 1, data_quitacao = ? WHERE cliente_id = ? AND valor = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDate(1, new java.sql.Date(data.getTime()));
            stmt.setInt(2, cliente.getId());
            stmt.setDouble(3, valor);
            stmt.executeUpdate();
            System.out.println("Dívida quitada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao quitar dívida: " + e.getMessage());
        }
    }
}
