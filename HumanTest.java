package oop.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HumanTest {

    @Test
    void testDefaultConstructor() {
        Human human = new Human();
        assertEquals("Undefined", human.getName());
        assertEquals("Undefined", human.getLastName());
        assertEquals(Gender.UNKNOWN, human.getGender());
    }

    @Test
    void testParameterizedConstructor() {
        Human human = new Human("John", "Doe", Gender.MAN);
        assertEquals("John", human.getName());
        assertEquals("Doe", human.getLastName());
        assertEquals(Gender.MAN, human.getGender());
    }

    @Test
    void testSetNameValid() {
        Human human = new Human();
        human.setName("Jane");
        assertEquals("Jane", human.getName());
    }

    @Test
    void testSetNameNullThrowsException() {
        Human human = new Human();
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> human.setName(null));
        // assertEquals("Name cannot be null or empty", thrown.getMessage()); // If you add message to exception
    }

    @Test
    void testSetNameEmptyThrowsException() {
        Human human = new Human();
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> human.setName(""));
        // assertEquals("Name cannot be null or empty", thrown.getMessage()); // If you add message to exception
    }

    @Test
    void testSetNameBlankThrowsException() {
        Human human = new Human();
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> human.setName("   "));
        // assertEquals("Name cannot be null or empty", thrown.getMessage()); // If you add message to exception
    }

    @Test
    void testSetLastNameValid() {
        Human human = new Human();
        human.setLastName("Smith");
        assertEquals("Smith", human.getLastName());
    }

    @Test
    void testSetLastNameNullThrowsException() {
        Human human = new Human();
        assertThrows(IllegalArgumentException.class, () -> human.setLastName(null));
    }

    @Test
    void testSetGenderValid() {
        Human human = new Human();
        human.setGender(Gender.WOMAN);
        assertEquals(Gender.WOMAN, human.getGender());
    }

    @Test
    void testSetGenderNullDefaultsToUnknown() {
        Human human = new Human();
        human.setGender(null);
        assertEquals(Gender.UNKNOWN, human.getGender());
    }

    @Test
    void testEqualsSameObject() {
        Human human = new Human("John", "Doe", Gender.MAN);
        assertEquals(human, human);
    }

    @Test
    void testEqualsEqualObjects() {
        Human human1 = new Human("John", "Doe", Gender.MAN);
        Human human2 = new Human("John", "Doe", Gender.MAN);
        assertEquals(human1, human2);
        assertEquals(human2, human1);
    }

    @Test
    void testEqualsDifferentName() {
        Human human1 = new Human("John", "Doe", Gender.MAN);
        Human human2 = new Human("Jane", "Doe", Gender.MAN);
        assertNotEquals(human1, human2);
    }

    @Test
    void testEqualsDifferentLastName() {
        Human human1 = new Human("John", "Doe", Gender.MAN);
        Human human2 = new Human("John", "Smith", Gender.MAN);
        assertNotEquals(human1, human2);
    }

    @Test
    void testEqualsDifferentGender() {
        Human human1 = new Human("John", "Doe", Gender.MAN);
        Human human2 = new Human("John", "Doe", Gender.WOMAN);
        assertNotEquals(human1, human2);
    }

    @Test
    void testEqualsDifferentType() {
        Human human = new Human("John", "Doe", Gender.MAN);
        Object obj = new Object();
        assertNotEquals(human, obj);
    }

    @Test
    void testEqualsNull() {
        Human human = new Human("John", "Doe", Gender.MAN);
        assertNotEquals(null, human);
    }

    @Test
    void testHashCodeConsistency() {
        Human human1 = new Human("John", "Doe", Gender.MAN);
        Human human2 = new Human("John", "Doe", Gender.MAN);
        assertEquals(human1.hashCode(), human2.hashCode());
    }

    @Test
    void testHashCodeDifferentObjects() {
        Human human1 = new Human("John", "Doe", Gender.MAN);
        Human human2 = new Human("Jane", "Smith", Gender.WOMAN);
        assertNotEquals(human1.hashCode(), human2.hashCode());
    }

    @Test
    void testToString() {
        Human human = new Human("John", "Doe", Gender.MAN);
        String expected = "Human{name='John', lastName='Doe', gender=MAN}";
        assertEquals(expected, human.toString());
    }
}
