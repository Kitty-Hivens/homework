package oop.stream.entity;

import java.util.Objects;

public class Human {
    private String name;
    private String lastName;
    private int age;
    private Gender gender;

    public Human(String name, String lastName, int age, Gender gender) {
        setName(name);
        setLastName(lastName);
        setAge(age);
        setGender(gender);
    }

    public Human() {
        this("Undefined", "Undefined", 0, Gender.UNKNOWN);
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        } else {
            this.name = name.trim();
        }
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("LastName cannot be null or empty");
        } else {
            this.lastName = lastName.trim();
        }
    }

    public void setAge(int age) {
        if (age < 0 || age > 100) {
            throw new IllegalArgumentException("Age must be between 0 and 100");
        } else  {
            this.age = age;
        }
    }

    public void setGender(Gender gender) {
        this.gender = Objects.requireNonNullElse(gender, Gender.UNKNOWN);
    }

    @Override
    public String toString() {
        return "Human{" +
                "name='" + getName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", gender=" + getGender() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Human human)) return false;

        return getName().equals(human.getName()) &&
                getLastName().equals(human.getLastName()) &&
                getGender() == human.getGender();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getLastName(), getGender());
    }
}
