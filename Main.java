package oop.mapping;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        // 1. Загрузка словаря при старте
        HashMap<String, String> dictionary = DictionaryManager.loadDictionary();
        Scanner scanner = new Scanner(System.in);

        // 2. Предложение режима работы
        System.out.println("\nВыберите действие:");
        System.out.println("1. Перевести текст из English.in");
        System.out.println("2. Ручное наполнение словаря");
        System.out.print("Ваш выбор: ");

        String choice = scanner.nextLine().trim();
        
        if (choice.equals("2")) {
            // РУЧНОЕ НАПОЛНЕНИЕ
            DictionaryManager.manualFillDictionary(dictionary, scanner);
            // После наполнения сохраняем словарь
            DictionaryManager.saveDictionary(dictionary);
            
        } else if (choice.equals("1")) {
            // ПЕРЕВОД ТЕКСТА (Задача 1)
            var translator = new Translator(dictionary);
            
            String inputFile = "English.in";
            String outputFile = "Ukrainian.out";

            try {
                String englishText = Files.readString(Paths.get(inputFile));
                String ukrainianText = translator.translate(englishText);
                Files.writeString(Paths.get(outputFile), ukrainianText);
                
                System.out.println("\nПеревод завершен и сохранен в: " + outputFile);
            } catch (IOException e) {
                System.err.println("\nОшибка при работе с файлами: " + e.getMessage());
            }
        } else {
            System.out.println("Неверный выбор.");
        }
        
        scanner.close();
    }
}