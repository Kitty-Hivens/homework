package oop.mapping;

import java.util.HashMap;

public record Translator(HashMap<String, String> dictionary) {

    private String capitalize(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }

    // Функция для перевода одного слова с сохранением регистра
    private String translateWord(String originalWord) {
        if (originalWord.isEmpty()) {
            return "";
        }

        String lowerCaseWord = originalWord.toLowerCase();

        // 1. Получаем перевод или оригинал
        String translatedWord = dictionary.getOrDefault(lowerCaseWord, originalWord);

        // 2. Если слово было переведено И оригинальное слово начиналось с заглавной буквы,
        // делаем заглавной первую букву перевода
        if (dictionary.containsKey(lowerCaseWord) && Character.isUpperCase(originalWord.charAt(0))) {
            return capitalize(translatedWord);
        }

        // 3. Если слово не было переведено, оставляем оригинал
        return translatedWord;
    }

    public String translate(String text) {
        if (text == null || text.isEmpty()) return "";

        var resultBuilder = new StringBuilder();
        int length = text.length();
        int i = 0;

        while (i < length) {
            char currentChar = text.charAt(i);

            if (Character.isLetter(currentChar)) {
                // Нашли начало слова. Ищем его конец.
                int startOfWord = i;
                // Ищем до тех пор, пока символ - это буква или цифра (для сокращений типа C++ / 1st)
                while (i < length && (Character.isLetter(text.charAt(i)) || Character.isDigit(text.charAt(i)))) {
                    i++;
                }
                int endOfWord = i;

                String originalWord = text.substring(startOfWord, endOfWord);

                // Переводим и добавляем в результат
                resultBuilder.append(translateWord(originalWord));

            } else {
                // Нашли пробел или знак препинания. Ищем конец этой "группы" разделителей.
                int startOfSeparator = i;

                // Ищем до тех пор, пока символ НЕ является буквой или цифрой
                while (i < length && !(Character.isLetter(text.charAt(i)) || Character.isDigit(text.charAt(i)))) {
                    i++;
                }
                int endOfSeparator = i;

                String separator = text.substring(startOfSeparator, endOfSeparator);

                // Просто добавляем разделитель как есть
                resultBuilder.append(separator);
            }
        }

        return resultBuilder.toString();
    }
}