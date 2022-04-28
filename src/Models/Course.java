package Models;

public class Course {
    private final int id;
    private final String name;
    private final String code;
    private final int credit;
    private final double passingGrade;

    public Course(int id, String name, String code, int credit, double passingGrade) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.credit = credit;
        this.passingGrade = passingGrade;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public int getCredit() {
        return credit;
    }

    public double getPassingGrade() {
        return passingGrade;
    }
}
