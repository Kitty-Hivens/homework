package oop.io;

public interface CSVConverter {
    String toCSVString();
    Student fromCSVString(String str);
}
