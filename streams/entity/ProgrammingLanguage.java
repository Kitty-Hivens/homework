package oop.stream.entity;

public record ProgrammingLanguage(String name, Difficulty difficulty) {
    public enum Difficulty { EASY, MEDIUM, HARD }
}