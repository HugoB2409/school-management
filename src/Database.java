import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_URL = "jdbc:oracle:thin:@//localhost:1521";
    private static final String DB_USERNAME = "System";
    private static final String DB_PASSWORD = "oracle";
    private Connection connection;

    public Database() {
        connect();
    }

    private void connect() {
        Connection conn = null;
        try {
            //Register the JDBC driver
            Class.forName(DB_DRIVER);

            //Open the connection
            conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            if (conn != null) {
                System.out.println("Successfully connected.");
            } else {
                System.out.println("Failed to connect.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        connection = conn;
    }

    public Connection getConnection() {
        return connection;
    }
}
