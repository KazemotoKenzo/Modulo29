package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static Connection connection;
    
    private ConnectionFactory(Connection connection){
        
    }
    
    public static Connection getConnection() throws SQLException {
        if(connection == null){
            connection = initConnection();
        } else if (connection.isClosed()) {
            connection = initConnection();
        } else {
            return connection;
        }
        return connection;
    }

    private static Connection initConnection() {
        try {
            return DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/modulo28", "postgres", "admin"
            );
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
