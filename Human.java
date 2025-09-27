package oop.generics;

import java.util.Objects;

public class Human {
    private String name;
    private String lastName;
    private Gender gender;

    public Human(String name, String lastName, Gender gender) {
        setName(name);
        setLastName(lastName);
        setGender(gender);
    }

    public Human() {
        this("Undefined", "Undefined", Gender.UNKNOWN);
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
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
