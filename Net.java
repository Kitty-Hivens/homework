package oop.net;

import java.util.ArrayList;
import java.util.List;

public class Net {
    public static void main(String[] args) {
        var list = new ArrayList<Integer>(1_000);
        List<Integer> squared;

        for (int i = 0; i <= 1_000; i++) {
            list.add(i);
        }

        squared = list.stream().map(n -> n * n).toList();

        list.forEach(System.out::println);
        squared.forEach(System.out::println);

    }

}
