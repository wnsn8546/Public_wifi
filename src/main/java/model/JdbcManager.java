package model;

import java.sql.*;

public class JdbcManager {
    public Connection createConnection() throws ClassNotFoundException, SQLException {
        String url = "jdbc:mariadb://localhost:3306/wifi_db";
        String dbUserId = "wifi_user";
        String dbPassword = "zerobase";

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return DriverManager.getConnection(url, dbUserId, dbPassword);
    }

    public void closeResultSet(ResultSet rs){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void closeStatement(PreparedStatement st){
        if(st!=null){
            try {
                st.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void closeConnection(Connection con){
        if(con!=null){
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
