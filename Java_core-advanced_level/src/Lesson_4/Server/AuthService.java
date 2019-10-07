package Lesson_4.Server;

import java.sql.*;

class AuthService {
    private static Connection connection;
    private static Statement stmt;

    static void connect(){
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:mainDB.db");
            stmt = connection.createStatement();
        } catch (ClassNotFoundException|SQLException e) {
            e.printStackTrace();
        }
    }

    static String getNickByLoginAndPass(String login, String pass) throws SQLException {
        String sql = String.format("SELECT nickname FROM main WHERE login='%s' AND password='%s'",login,pass);

        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next()){
            return rs.getString("nickname");
        }
        return null;
    }

    static void disconnect(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
