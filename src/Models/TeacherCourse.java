package Models;

public class TeacherCourse {
    private int teacherId;
    private int courseId;

    public TeacherCourse(int teacherId, int courseId) {
        this.teacherId = teacherId;
        this.courseId = courseId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public int getCourseId() {
        return courseId;
    }
}
