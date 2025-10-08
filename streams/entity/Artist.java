package oop.stream.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Artist {
    String name;
    List<String> songs;

    public Artist(String name, List<String> songs) {
        setName(name);
        setSongs(songs);
    }

    public Artist() {
        this.name = "Unknown";
        this.songs = new ArrayList<>();
    }

    public List<String> getSongs() {
        return songs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSongs(List<String> songs) {
        this.songs = songs;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Artist artist)) return false;

        return Objects.equals(getName(), artist.getName()) && Objects.equals(getSongs(), artist.getSongs());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getSongs());
    }

    @Override
    public String toString() {
        return "Artist{" +
                "name='" + name + '\'' +
                ", songs=" + songs +
                '}';
    }
}