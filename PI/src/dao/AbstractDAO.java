package dao;

import java.sql.*;

public abstract class AbstractDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/gestao_dividas";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    protected Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    protected void closePreparedStatement(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    protected void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    protected void closeResources(Connection conn, PreparedStatement ps, ResultSet rs) {
        closeResultSet(rs);
        closePreparedStatement(ps);
        closeConnection(conn);
    }

    protected void closeResources(Connection conn, PreparedStatement ps) {
        closePreparedStatement(ps);
        closeConnection(conn);
    }
}
