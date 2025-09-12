import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    
    public static Connection getConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/estoque_db";
            String user = "root";
            String password = "";
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);
            
        } catch (Exception e) {
            System.out.println("Erro ao conectar com o banco: " + e.getMessage());
            return null;
        }
    }
}