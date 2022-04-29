package Models;

import Brokers.CourseBroker;
import Brokers.StudentBroker;
import Brokers.TeacherBroker;

import java.sql.Connection;
import java.util.Scanner;

public class Builder {

    public Student buildStudent() {
        String firstName = askForValue("Prénom de l'étudiant");
        String lastName = askForValue("Nom Famille de l'étudiant");
        String birthDay;
        do {
            birthDay = askForValue("Date de naissance (YYYY-MM-DD) de l'étudiant");
            if (!validBirthDay(birthDay)) {
                System.out.println("ERREUR: Date de naissance invalide format requis: YYYY-MM-DD");
            }
        } while (!validBirthDay(birthDay));
        String studentId = generateStudentId(firstName, lastName, birthDay).toLowerCase();
        return new Student(null, studentId, firstName, lastName, birthDay);
    }

    public Course buildCourse() {
        String courseName = askForValue("Nom du cours");
        String code = askForValue("Code du cour");
        int passingGrade = askForValue("Note de passage du cour", 0, 100);
        int credits = askForValue("Nombre de crédits du cours", 1, 10);
        return new Course(null, code, courseName, passingGrade, credits);
    }

    public StudentCourse buildStudentCourse(Connection connection) {
        String studentId = askForValue("Code permanent de l'etudiant");
        String courseCode = askForValue("Code du cours");
        int year = askForValue("Annee durant laquelle le cours sera donnee", 1950, 2500);

        return new StudentCourse(new StudentBroker(connection).getStudentByStudentId(studentId).getId(), new CourseBroker(connection).getCourseByCode(courseCode).getId(), null, "Inscrit", year);
    }

    public TeacherCourse buildTeacherCourse(Connection connection) {
        String teacherId = askForValue("Code permanent du professeur");
        String courseCode = askForValue("Code du cours");

        return new TeacherCourse(new TeacherBroker(connection).getTeacherByTeacherId(teacherId).getId(), new CourseBroker(connection).getCourseByCode(courseCode).getId());
    }

    private String askForValue(String valueName) {
        System.out.print(valueName + ": ");
        return new Scanner(System.in).nextLine();
    }

    private int askForValue(String valueName, int min, int max) {
        System.out.print(valueName + " : ");
        String input = new Scanner(System.in).nextLine();
        int value = -1;
        try {
            value = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("ERREUR: " + valueName + " doit être un nombre entier");
        }
        if (value < min || value > max) {
            System.out.println("ERREUR: Valeur invalide doit etre entre " + min + " et " + max);
            return askForValue(valueName, min, max);
        }
        return value;
    }

    private String generateStudentId(String firstName, String lastName, String birthDay) {
        return firstName.substring(0, 2) + lastName.substring(0, 2) + birthDay.substring(7, 9);
    }

    private boolean validBirthDay(String birthDay) {
        return birthDay.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}");
    }
}
