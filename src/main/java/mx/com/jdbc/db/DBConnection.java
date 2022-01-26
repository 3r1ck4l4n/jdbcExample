package mx.com.jdbc.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class DBConnection {
    protected Connection connection;

    public boolean openConnection() throws SQLException {
        boolean status = false;

        String jdbcUrl = "jdbc:sqlserver://localhost;databaseName=jdbcTest;";
        String usr = "sa";
        String pass = "sa";
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(jdbcUrl, usr,pass);
            status = true;
            boolean valid = connection.isValid(50000);
            System.out.println(valid?"Test Ok" : "Test Fail");
        }catch (SQLException e){
            status = false;
            e.printStackTrace();
        }finally {
            return status;
        }
    }

    protected void closeConnection(){
        try {
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
