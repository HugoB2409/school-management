package Models;

public class StudentCourse {
    private final int studentId;
    private final int courseId;
    private final double grade;
    private final String state; // Reussi, en cours, Echoue

    public StudentCourse(int studentId, int courseId, double grade, String state) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.grade = grade;
        this.state = state;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public double getGrade() {
        return grade;
    }

    public String getState() {
        return state;
    }
}
