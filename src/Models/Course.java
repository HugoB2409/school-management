package Models;

import Brokers.CourseBroker;

import java.sql.Connection;

public class Course {
    private final int id;
    private final String name;
    private final String code;
    private final int credit;
    private final double passingGrade;

    public Course(Integer id, String name, String code, int credit, double passingGrade) {
        this.id = (id == null) ? -1 : id;
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

    public String toString() {
        return name + " " + code + " " + credit + " " + passingGrade;
    }

    public void saveToDatabase(Connection connection) {
        new CourseBroker(connection).add(this);
    }
}
