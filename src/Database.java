import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_URL = "jdbc:oracle:thin:@//172.16.25.43:1521/coursbd.uqtr.ca";
    private static final String DB_USERNAME = "SMI1002_028";
    private static final String DB_PASSWORD = "49gxje97";
    private Connection connection;

    public Database() {
        connect();
    }

    private void connect() {
        Connection conn = null;
        try{
            //Register the JDBC driver
            Class.forName(DB_DRIVER);

            System.out.println("AVANT");
            //Open the connection
            conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println("APRES");
            if(conn != null){
                System.out.println("Successfully connected.");
            }else{
                System.out.println("Failed to connect.");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        connection = conn;
    }

    public Connection getConnection() {
        return connection;
    }
}
