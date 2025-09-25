package oop.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void testDefaultConstructor() {
        Student student = new Student();
        assertEquals("Undefined", student.getName());
        assertEquals("Undefined", student.getLastName());
        assertEquals(Gender.UNKNOWN, student.getGender());
        assertEquals(0, student.getId());
        assertEquals("Undefined", student.getGroupName());
    }

    @Test
    void testParameterizedConstructor() {
        Student student = new Student("Alice", "Smith", Gender.WOMAN, 101, "GroupA");
        assertEquals("Alice", student.getName());
        assertEquals("Smith", student.getLastName());
        assertEquals(Gender.WOMAN, student.getGender());
        assertEquals(101, student.getId());
        assertEquals("GroupA", student.getGroupName());
    }

    @Test
    void testSetIdValid() {
        Student student = new Student();
        student.setId(50);
        assertEquals(50, student.getId());
    }

    @Test
    void testSetIdNegativeThrowsException() {
        Student student = new Student();
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> student.setId(-1));
        assertEquals("ID cannot be negative", thrown.getMessage());
    }

    @Test
    void testSetGroupNameValid() {
        Student student = new Student();
        student.setGroupName("GroupB");
        assertEquals("GroupB", student.getGroupName());
    }

    @Test
    void testSetGroupNameNullThrowsException() {
        Student student = new Student();
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> student.setGroupName(null));
        assertEquals("Group name cannot be null or empty", thrown.getMessage());
    }

    @Test
    void testSetGroupNameEmptyThrowsException() {
        Student student = new Student();
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> student.setGroupName(""));
        assertEquals("Group name cannot be null or empty", thrown.getMessage());
    }

    @Test
    void testSetGroupNameBlankThrowsException() {
        Student student = new Student();
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> student.setGroupName("   "));
        assertEquals("Group name cannot be null or empty", thrown.getMessage());
    }

    @Test
    void testEqualsSameObject() {
        Student student = new Student("Alice", "Smith", Gender.WOMAN, 101, "GroupA");
        assertEquals(student, student);
    }

    @Test
    void testEqualsEqualObjects() {
        Student student1 = new Student("Alice", "Smith", Gender.WOMAN, 101, "GroupA");
        Student student2 = new Student("Alice", "Smith", Gender.WOMAN, 101, "GroupA");
        assertEquals(student1, student2);
        assertEquals(student2, student1);
    }

    @Test
    void testEqualsDifferentId() {
        Student student1 = new Student("Alice", "Smith", Gender.WOMAN, 101, "GroupA");
        Student student2 = new Student("Alice", "Smith", Gender.WOMAN, 102, "GroupA");
        assertNotEquals(student1, student2);
    }

    @Test
    void testEqualsDifferentGroupName() {
        Student student1 = new Student("Alice", "Smith", Gender.WOMAN, 101, "GroupA");
        Student student2 = new Student("Alice", "Smith", Gender.WOMAN, 101, "GroupB");
        assertNotEquals(student1, student2);
    }

    @Test
    void testEqualsDifferentHumanFields() {
        Student student1 = new Student("Alice", "Smith", Gender.WOMAN, 101, "GroupA");
        Student student2 = new Student("Bob", "Smith", Gender.WOMAN, 101, "GroupA");
        assertNotEquals(student1, student2);
    }

    @Test
    void testEqualsDifferentType() {
        Student student = new Student("Alice", "Smith", Gender.WOMAN, 101, "GroupA");
        Human human = new Human("Alice", "Smith", Gender.WOMAN);
        assertNotEquals(student, human); // Student is not equal to Human even if fields match
    }

    @Test
    void testEqualsNull() {
        Student student = new Student("Alice", "Smith", Gender.WOMAN, 101, "GroupA");
        assertNotEquals(null, student);
    }

    @Test
    void testHashCodeConsistency() {
        Student student1 = new Student("Alice", "Smith", Gender.WOMAN, 101, "GroupA");
        Student student2 = new Student("Alice", "Smith", Gender.WOMAN, 101, "GroupA");
        assertEquals(student1.hashCode(), student2.hashCode());
    }

    @Test
    void testHashCodeDifferentObjects() {
        Student student1 = new Student("Alice", "Smith", Gender.WOMAN, 101, "GroupA");
        Student student2 = new Student("Bob", "Doe", Gender.MAN, 102, "GroupB");
        assertNotEquals(student1.hashCode(), student2.hashCode());
    }

    @Test
    void testToString() {
        Student student = new Student("Alice", "Smith", Gender.WOMAN, 101, "GroupA");
        // Note: Fix the toString() format in Student class first for this to pass
        String expected = "Student{name='Alice', lastName='Smith', gender=WOMAN, id=101, groupName='GroupA'}";
        assertEquals(expected, student.toString());
    }
}
