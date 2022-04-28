import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
//        Database database = new Database();
//        Connection connection = database.getConnection();
//        Statement stmt = connection.createStatement();
//        ResultSet rs = stmt.executeQuery("SELECT * FROM STUDENT");
//
//
//        while (rs.next()) {
//            System.out.println(rs.getInt(1));
//
//        }
        getUserInput();
    }

    private static void getUserInput() {
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        while (option != 10) {
            printMainMenu();
            String input = scanner.nextLine();
            try {
                option = Integer.parseInt(input);
                executeOption(option);
            } catch (NumberFormatException e) {
                printInvalidMessage();
            }
        }
    }

    private static void executeOption(int option) {
        switch (option) {
//            case 1 -> // Add Student
//            case 2 -> // Modify Student
//            case 3 -> // Print Students
//            case 4 -> // Add Course
//            case 5 -> // Print Courses
//            case 6 -> // Signup student to class
//            case 7 -> // Add grade to student
//            case 8 -> // Print Students following class
//            case 9 -> // Signup to teach class
            case 10 -> printGoodbyeMessage();
            default -> printInvalidMessage();
        }
    }

    private static void printMainMenu() {
        System.out.println("Menu Principal");
        System.out.println("1. Ajouter un étudiant");
        System.out.println("2. Modifier un étudiant");
        System.out.println("3. Afficher les étudiants");
        System.out.println("4. Ajouter un cours");
        System.out.println("5. Afficher les cours");
        System.out.println("6. Inscrire un étudiant au cour");
        System.out.println("7. Ajouter une note a un étudiant");
        System.out.println("8. Afficher étudiants suivant un cour");
        System.out.println("9. S'inscrire pour donner un cour");
        System.out.println("10. Quitter");
        System.out.println();
        System.out.print("Entrer vote choix: ");
    }

    private static void printInvalidMessage() {
        System.err.println("Erreur: Veuillez entrer un option valide");
    }

    private static void printGoodbyeMessage() {
        System.out.println("Au revoir!");
    }
}