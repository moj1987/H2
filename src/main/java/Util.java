import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "";
    private static final String CONN_STRING = "jdbc:h2:~/explorecalifornia";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
    }

}
