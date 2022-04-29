import Brokers.CourseBroker;
import Brokers.StudentBroker;
import Brokers.StudentCourseBroker;
import Brokers.TeacherBroker;
import Models.Builder;
import Models.Course;
import Models.Student;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class Menu {
    Connection connection;

    public Menu(Connection connection) {
        this.connection = connection;
        getUserInput();
    }

    private void getUserInput() {
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

    private void executeOption(int option) {
        switch (option) {
            case 1 -> new Builder().buildStudent().saveToDatabase(connection);
            case 2 -> new StudentBroker(connection).delete(askForStudentId());
            case 3 -> printStudents(new StudentBroker(connection).findAll("SELECT * FROM Student"));
            case 4 -> new Builder().buildCourse().saveToDatabase(connection);
            case 5 -> printCourses(new CourseBroker(connection).list());
            case 6 -> new StudentCourseBroker(connection).register(new Builder().buildStudentCourse(connection));
            case 7 -> addGrade();
            case 8 -> printStudentInCourse();
            case 9 -> printCourseByStudent();
            case 10 -> new TeacherBroker(connection).register(new Builder().buildTeacherCourse(connection));
            case 11 -> printGoodbyeMessage();
            default -> printInvalidMessage();
        }
    }

    private void addGrade() {
        Student student = new StudentBroker(connection).getStudentByStudentId(askForStudentId());
        Course course = new CourseBroker(connection).getCourseByCode(askForCourseCode());
        int year = Integer.parseInt(askForYear());
        double grade = Double.parseDouble(askForGrade());

        new StudentCourseBroker(connection).updateGrade(student.getId(), course, year, grade);
    }

    private void printStudentInCourse() {
        List<Student> students = new StudentCourseBroker(connection).getStudentsInCourse(askForCourseCode());
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private void printCourseByStudent() {
        List<Course> courses = new StudentCourseBroker(connection).getCourseByStudent(askForStudentId());
        for (Course course : courses) {
            System.out.println(course);
        }
    }

    private String askForStudentId() {
        System.out.print("Entrer l'id de l'étudiant: ");
        return new Scanner(System.in).nextLine();
    }

    private String askForCourseCode() {
        System.out.print("Entrer le code du cours: ");
        return new Scanner(System.in).nextLine();
    }

    private String askForYear() {
        System.out.print("Entrer l'annee auquel l'etudiant a suivi le cours: ");
        return new Scanner(System.in).nextLine();
    }

    private String askForGrade() {
        System.out.print("Entrer la note obtenue: ");
        return new Scanner(System.in).nextLine();
    }

    private void printStudents(List<Student> list) {
        for (Student student : list)
            System.out.println(student);
    }

    private void printCourses(List<Course> list) {
        for (Course course : list)
            System.out.println(course);
    }

    private void printMainMenu() {
        System.out.println("Menu Principal");
        System.out.println("1. Ajouter un étudiant");
        System.out.println("2. Supprimer un étudiant");
        System.out.println("3. Afficher les étudiants");
        System.out.println("4. Ajouter un cours");
        System.out.println("5. Afficher les cours");
        System.out.println("6. Inscrire un étudiant au cour");
        System.out.println("7. Ajouter une note a un étudiant");
        System.out.println("8. Afficher étudiants suivant un cour");
        System.out.println("9. Afficher cours suivi par etudiant");
        System.out.println("10. S'inscrire pour donner un cour");
        System.out.println("11. Quitter");
        System.out.println();
        System.out.print("Entrer vote choix: ");
    }

    private void printInvalidMessage() {
        System.out.println("ERREUR: Veuillez entrer un option valide");
    }

    private void printGoodbyeMessage() {
        System.out.println("Au revoir!");
    }
}
