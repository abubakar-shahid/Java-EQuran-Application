import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    private static Connection connection;
    public static void main(String[] args) throws SQLException {
        DBConnection();
        HomePage obj = new HomePage();
        obj.runApplication(connection);
    }

    public static void DBConnection(){
        try{
            String driver = "com.mysql.cj.jdbc.Driver";
            String DBurl = "jdbc:mysql://localhost:3306/EQuraanApp";
            String username = "root";
            String password = "69AGREJGY5J";
            Class.forName(driver);
            connection = DriverManager.getConnection(DBurl, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}