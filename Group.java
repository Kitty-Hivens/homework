package oop.io;

import java.io.*; // Додано імпорти для роботи з файлами
import java.util.Arrays;
import java.util.Comparator;
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
        return Arrays.copyOf(students, students.length);
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

    public void sortStudentsByName() {
        Arrays.sort(students, Comparator.nullsLast(Comparator.comparing(Student::getName)));
    }

    public void sortStudentsByLastName() {
        Arrays.sort(students, Comparator.nullsLast(Comparator.comparing(Student::getLastName)));
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

    public void saveToFile(File file) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(this.groupName);
            writer.newLine();
            for (Student student : students) {
                if (student != null) {
                    writer.write(student.toCSVString());
                    writer.newLine();
                }
            }
        }
    }

    public static Group loadFromFile(File file) throws IOException, GroupOverflowException {
        Group group;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            if (line == null || line.trim().isEmpty()) {
                throw new IOException("Файл пустой, или отсутствует название группы.");
            }
            String groupName = line.trim();
            group = new Group(groupName);

            Student tempStudentConverter = new Student();

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                try {
                    Student student = tempStudentConverter.fromCSVString(line);
                    group.addStudent(student);
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("Ошибка при разборе студента с CSV ряда '" + line + "'", e);
                }
            }
        }
        return group;
    }
}
