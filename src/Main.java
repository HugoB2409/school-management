import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Database database = new Database();
        Connection connection = database.getConnection();
        String query = "SELECT * FROM Student";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getInt("id"));
        }
    }
}