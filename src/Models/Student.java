package Models;

public class Student {
    private final int id;
    private final String studentId;
    private final String firstName;
    private final String lastName;
    private final String birthDay;

    public Student(int id, String studentId, String firstName, String lastName, String birthDay) {
        this.id = id;
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
    }

    public int getId() {
        return id;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthDay() {
        return birthDay;
    }
}
