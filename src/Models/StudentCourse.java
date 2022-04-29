package Models;

public class StudentCourse {
    private final int studentId;
    private final int courseId;
    private final double grade;
    private final String state; // Reussi, en cours, Echoue
    private final int year;

    public StudentCourse(int studentId, int courseId, Double grade, String state, int year) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.grade = (grade == null) ? -1 : grade;
        this.state = state;
        this.year = year;
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

    public int getYear() {
        return year;
    }
}
