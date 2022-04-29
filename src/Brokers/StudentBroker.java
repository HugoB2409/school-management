package Brokers;

import Models.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentBroker {
    Connection connection;

    public StudentBroker(Connection connection) {
        this.connection = connection;
    }

    public void add(Student student) {
        String query = "INSERT INTO Student (studentId, firstName, lastName, birthday) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, student.getStudentId());
            preparedStatement.setString(2, student.getFirstName());
            preparedStatement.setString(3, student.getLastName());
            preparedStatement.setDate(4, student.getBirthDay());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            System.out.println(exception);
        }
    }

    public void delete(String studentId) {
        String query = "DELETE FROM Student WHERE studentId = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, studentId);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            System.out.println(exception);
        }
    }

    public List<Student> findAll(String query) {
        List<Student> students = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Student student = new Student(
                        resultSet.getInt("id"),
                        resultSet.getString("studentId"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getDate("birthday").toString()
                );
                students.add(student);
            }
            resultSet.close();
        } catch (SQLException exception) {
            System.out.println(exception);
        }
        return students;
    }

    public Student getStudentByStudentId(String studentId) {
        Student student = null;
        String query = "SELECT * FROM Student WHERE studentId = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, studentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                student = new Student(
                        resultSet.getInt("id"),
                        resultSet.getString("studentId"),
                        resultSet.getString("firstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("birthDay")
                );
            }
            resultSet.close();
        } catch (SQLException exception) {
            System.out.println(exception);
        }
        return student;
    }
}
