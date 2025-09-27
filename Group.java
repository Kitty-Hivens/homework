package oop.generics;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Group {
    private String groupName;
    private List<Student> students = new ArrayList<>();

    public Group(String groupName) {
        setGroupName(groupName);
    }

    public Group() {
        this.groupName = "Undefined";
    }

    public String getGroupName() {
        return groupName;
    }

    public List<Student> getStudents() {
        return new ArrayList<>(students); // Возвращает копию
    }

    public void setGroupName(String groupName) {
        if (groupName == null || groupName.trim().isEmpty()) {
            throw new IllegalArgumentException("Group name cannot be null or empty");
        } else {
            this.groupName = groupName;
        }
    }

    public void setStudents(List<Student> students) {
        this.students = new ArrayList<>(students);
    }

    public void addStudent(Student student) {
        if (student != null) {
            this.students.add(student);
        }
    }

    public Student searchStudentByLastName(String lastName) throws StudentNotFoundException {
        if (lastName == null) {
            throw new StudentNotFoundException();
        }
        for (Student student : students) {
            if (student != null && student.getLastName().equals(lastName)) {
                return student;
            }
        }
        throw new StudentNotFoundException();
    }

    public boolean removeStudentByID (int studentID) {
        return students.removeIf(student -> student != null && student.getId() == studentID);
    }

    /**
     * Проверяет наличие эквивалентных студентов (дубликатов) в группе.
     * Эквивалентность определяется на основе метода equals() класса Student.
     * @return true, если в группе есть хотя бы два эквивалентных студента (дубликата), false в противном случае.
     */
    public boolean hasEquivalentStudents() {
        // Упрощенная реализация с использованием List
        for (int i = 0; i < students.size(); i++) {
            Student studentA = students.get(i);

            // Проверка на null
            if (studentA == null) {
                continue;
            }

            for (int j = i + 1; j < students.size(); j++) {
                Student studentB = students.get(j);

                if (studentB == null) {
                    continue;
                }

                if (studentA.equals(studentB)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Group group)) return false;

        return getGroupName().equals(group.getGroupName()) && students.equals(group.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupName, students);
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupName='" + groupName + '\'' +
                ", students=" + students +
                '}';
    }
}