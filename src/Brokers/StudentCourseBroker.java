package Brokers;

import Models.Course;
import Models.Student;
import Models.StudentCourse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentCourseBroker {
    Connection connection;

    public StudentCourseBroker(Connection connection) {
        this.connection = connection;
    }

    public void register(StudentCourse studentCourse) {
        String query = "INSERT INTO StudentCourse (studentId, courseId, grade, state) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, studentCourse.getStudentId());
            preparedStatement.setInt(2, studentCourse.getCourseId());
            preparedStatement.setDouble(3, studentCourse.getGrade());
            preparedStatement.setString(4, studentCourse.getState());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            System.out.println(exception);
        }
    }

    public List<Student> getStudentsInCourse(String courseCode) {
        List<Student> students = new ArrayList<>();
        // get course by code
        String query = "SELECT * FROM Student s JOIN StudentCourse sc ON s.id = sc.studentId WHERE sc.courseId = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, 1);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Student student = new Student(resultSet.getInt("id"), resultSet.getString("studentId"), resultSet.getString("firstName"), resultSet.getString("lastName"), resultSet.getString("birthDay"));
                students.add(student);
            }
            resultSet.close();
        } catch (SQLException exception) {
            System.out.println(exception);
        }
        return students;
    }

    public List<Course> getCourseByStudent(String studentId) {
        List<Course> courses = new ArrayList<>();
        // get student by id
        String query = "SELECT * FROM Course c JOIN StudentCourse sc ON c.id = sc.CourseId WHERE sc.studentId = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, 1);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Course course = new Course(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("code"), resultSet.getInt("credit"), resultSet.getDouble("passingGrade"));
                courses.add(course);
            }
            resultSet.close();
        } catch (SQLException exception) {
            System.out.println(exception);
        }
        return courses;
    }
}
