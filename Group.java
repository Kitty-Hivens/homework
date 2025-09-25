package oop.exception;

import java.util.Arrays;
import java.util.Objects;

public class Group {
    private String groupName;
    private Student[] students =  new Student[10];

    public Group(String groupName) {
        setGroupName(groupName);
    }

    public Group() {
        this.groupName = "Undefined";
    }

    public String getGroupName() {
        return groupName;
    }

    public Student[] getStudents() {
        return students;
    }

    public void setGroupName(String groupName) {
        if (groupName == null || groupName.trim().isEmpty()) {
            throw new IllegalArgumentException("Group name cannot be null or empty");
        } else {
            this.groupName = groupName;
        }
    }

    public void setStudents(Student[] students) throws GroupOverflowException {
        if (students.length > 10) {
            throw new GroupOverflowException();
        } else {
            this.students = students;
        }
    }

    public void addStudent(Student student) throws GroupOverflowException {
        for (int i = 0; i < students.length; i++) {
            if (students[i] == null) {
                students[i] = student;
                return;
            }
        }
        throw new GroupOverflowException();
    }

    public Student searchStudentByLastName(String lastName) throws StudentNotFoundException {
        for (Student student : students) {
            if (student != null && student.getLastName().equals(lastName)) {
                return student;
            }
        }
        throw new StudentNotFoundException();
    }

    public boolean removeStudentByID (int studentID) {
        for (int i = 0; i < students.length; i++) {
            if (students[i] != null && students[i].getId() == studentID) {
                students[i] = null;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Group group)) return false;

        return getGroupName().equals(group.getGroupName()) && Arrays.equals(getStudents(), group.getStudents());
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.hashCode(students), groupName);
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupName='" + groupName + '\'' +
                ", students=" + Arrays.toString(students) +
                '}';
    }
}
