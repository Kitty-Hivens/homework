package oop.generics;

import java.util.Objects;

public class Student extends Human {
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
}
