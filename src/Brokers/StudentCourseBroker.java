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
        String query = "INSERT INTO StudentCourse (studentId, courseId, grade, state, year) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, studentCourse.getStudentId());
            preparedStatement.setInt(2, studentCourse.getCourseId());
            preparedStatement.setDouble(3, studentCourse.getGrade());
            preparedStatement.setString(4, studentCourse.getState());
            preparedStatement.setInt(5, studentCourse.getYear());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            System.out.println(exception);
        }
    }

    public void updateGrade(int studentId, Course course, int year, double grade) {
        String query = "UPDATE StudentCourse SET grade = ?, state = ? WHERE studentId = ? AND courseId = ? AND year = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDouble(1, grade);
            preparedStatement.setString(2, grade >= course.getPassingGrade() ? "Reussi" : "Echoue");
            preparedStatement.setInt(3, studentId);
            preparedStatement.setInt(4, course.getId());
            preparedStatement.setInt(5, year);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            System.out.println(exception);
        }
    }

    public List<Student> getStudentsInCourse(String courseCode) {
        List<Student> students = new ArrayList<>();
        Course course = new CourseBroker(connection).getCourseByCode(courseCode);
        String query = "SELECT * FROM Student s JOIN StudentCourse sc ON s.id = sc.studentId WHERE sc.courseId = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, course.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Student student = new Student(
                        resultSet.getInt("id"),
                        resultSet.getString("studentId"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("birthDay")
                );
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
        Student student = new StudentBroker(connection).getStudentByStudentId(studentId);
        String query = "SELECT * FROM Course c JOIN StudentCourse sc ON c.id = sc.CourseId WHERE sc.studentId = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, student.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Course course = new Course(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("code"),
                        resultSet.getInt("credit"),
                        resultSet.getDouble("passingGrade")
                );
                courses.add(course);
            }
            resultSet.close();
        } catch (SQLException exception) {
            System.out.println(exception);
        }
        return courses;
    }
}
