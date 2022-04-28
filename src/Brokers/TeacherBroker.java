package Brokers;

import Models.TeacherCourse;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
}
