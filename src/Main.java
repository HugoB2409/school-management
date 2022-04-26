import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        Database database = new Database();
        Connection connection = database.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM STUDENT");


        while (rs.next()) {
            System.out.println(rs.getInt(1));

        }
    }
}