package Models;

public class Teacher {
    private final int id;
    private final String teacherId;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final int yearJoined;

    public Teacher(int id, String teacherId, String firstName, String lastName, String email, int yearJoined) {
        this.id = id;
        this.teacherId = teacherId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.yearJoined = yearJoined;
    }

    public int getId() {
        return id;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public int getYearJoined() {
        return yearJoined;
    }
}
