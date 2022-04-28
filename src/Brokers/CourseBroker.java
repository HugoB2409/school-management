package Brokers;

import Models.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseBroker {
    Connection connection;

    public CourseBroker(Connection connection) {
        this.connection = connection;
    }

    public void add(Course course) {
        String query = "INSERT INTO Course (name, code, passingGrade, credit) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, course.getName());
            preparedStatement.setString(2, course.getCode());
            preparedStatement.setDouble(3, course.getPassingGrade());
            preparedStatement.setInt(4, course.getCredit());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            System.out.println(exception);
        }
    }

    public List<Course> list() {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT * FROM Course";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
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

    public Course getCourseByCode(String courseCode) {
        Course course = null;
        String query = "SELECT * FROM Course WHERE code = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, courseCode);
            ResultSet resultSet = preparedStatement.executeQuery();
            course = new Course(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("code"),
                    resultSet.getInt("credit"),
                    resultSet.getDouble("passingGrade")
            );
            resultSet.close();
        } catch (SQLException exception) {
            System.out.println(exception);
        }
        return course;
    }
}
