import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:sqlserver://localhost:1433;databaseName=JavaAdv;encrypt=true;trustServerCertificate=true;",
                "sa", "SQL"
        );
    }
}
