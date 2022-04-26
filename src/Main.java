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

    private static void printMainMenu() {

    }
    // Add student
    // Delete student
    // Print students
    // Add course
    // Print courses
    // Add student to course
    // Ajouter une note a un etudiant
    // Print Students in a course
    // S'inscrire a donner un cour
    // Custom sql query ???
}