package oop.io;

import java.util.Objects;

public class Student extends Human implements CSVConverter{
    private int id;
    private String groupName;

    public Student(String name, String lastName, Gender gender, int id, String groupName) {
        super(name, lastName, gender);
        this.id = id;
        this.groupName = groupName;
    }

    public Student() {
        super();
        this.id = 0;
        this.groupName = "Undefined";
    }

    public int getId() {
        return id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("ID cannot be negative");
        }
        this.id = id;
    }

    public void setGroupName(String groupName) {
        if (groupName == null ||  groupName.trim().isEmpty()) {
            throw new IllegalArgumentException("Group name cannot be null or empty");
        }
        this.groupName = groupName;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Student student)) return false;
        if (!super.equals(o)) return false;

        return getId() == student.getId() && getGroupName().equals(student.getGroupName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, groupName);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + getName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", gender=" + getGender() +
                ", id=" + getId() +
                ", groupName='" + getGroupName() + '\'' +
                '}';
    }

    @Override
    public String toCSVString() {
        return String.join(",", getName(), getLastName(), getGender().name(), String.valueOf(id), getGroupName());
    }

    @Override
    public Student fromCSVString(String str) {
        if (str == null || str.trim().isEmpty()) {
            throw new IllegalArgumentException("CSV string cannot be null or empty.");
        }
        String[] parts = str.split(",");
        if (parts.length != 5) {
            throw new IllegalArgumentException("Invalid CSV string format.");
        }

        try {
            String name = parts[0];
            String lastName = parts[1];
            Gender gender = Gender.valueOf(parts[2].toUpperCase());
            int id = Integer.parseInt(parts[3]);
            String groupName = parts[4];

            return new Student(name, lastName, gender, id, groupName);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid ID format in CSV string.", e);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid gender or other data in CSV string.", e);
        }
    }
}
