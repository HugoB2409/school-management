package Brokers;

import Models.Teacher;
import Models.TeacherCourse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherBroker {
    Connection connection;

    public TeacherBroker(Connection connection) {
        this.connection = connection;
    }

    public void register(TeacherCourse teacherCourse) {
        String query = "INSERT INTO TeacherCourse (teacherId, courseId) VALUES (?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, teacherCourse.getTeacherId());
            preparedStatement.setInt(2, teacherCourse.getCourseId());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            System.out.println(exception);
        }
    }

    public Teacher getTeacherByTeacherId(String teacherId) {
        Teacher teacher = null;
        String query = "SELECT * FROM Teacher WHERE teacherId = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, teacherId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                teacher = new Teacher(
                        resultSet.getInt("id"),
                        resultSet.getString("teacherId"),
                        resultSet.getString("firstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("email"),
                        resultSet.getInt("yearJoined")
                );
            }
            resultSet.close();
        } catch (SQLException exception) {
            System.out.println(exception);
        }
        return teacher;
    }
}
