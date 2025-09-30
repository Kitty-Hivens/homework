package oop.mapping;

import java.util.HashMap;
import java.util.Map;

/**
 * Генератор ASCII-арта для преобразования текста в красивое ASCII-представление.
 * Поддерживает буквы латинского алфавита, цифры и основные знаки препинания.
 */
public class AsciiArtGenerator {

    private final Map<Character, String[]> asciiMap;
    private static final int CHAR_HEIGHT = 7;
    private static final String SEPARATOR = "  ";

    public AsciiArtGenerator() {
        this.asciiMap = createAsciiMap();
    }

    /**
     * Создает полную карту ASCII-представлений для всех поддерживаемых символов.
     */
    private Map<Character, String[]> createAsciiMap() {
        Map<Character, String[]> map = new HashMap<>();

        // Буквы A-Z
        map.put('A', new String[]{
                "  ***  ",
                " ** ** ",
                "**   **",
                "*******",
                "**   **",
                "**   **",
                "**   **"
        });

        map.put('B', new String[]{
                "****** ",
                "**   **",
                "****** ",
                "**   **",
                "**   **",
                "**   **",
                "****** "
        });

        map.put('C', new String[]{
                " ***** ",
                "**   **",
                "**     ",
                "**     ",
                "**     ",
                "**   **",
                " ***** "
        });

        map.put('D', new String[]{
                "****** ",
                "**   **",
                "**   **",
                "**   **",
                "**   **",
                "**   **",
                "****** "
        });

        map.put('E', new String[]{
                "*******",
                "**     ",
                "**     ",
                "****** ",
                "**     ",
                "**     ",
                "*******"
        });

        map.put('F', new String[]{
                "*******",
                "**     ",
                "**     ",
                "****** ",
                "**     ",
                "**     ",
                "**     "
        });

        map.put('G', new String[]{
                " ***** ",
                "**   **",
                "**     ",
                "**  ***",
                "**   **",
                "**   **",
                " ***** "
        });

        map.put('H', new String[]{
                "**   **",
                "**   **",
                "**   **",
                "*******",
                "**   **",
                "**   **",
                "**   **"
        });

        map.put('I', new String[]{
                "*******",
                "  ***  ",
                "  ***  ",
                "  ***  ",
                "  ***  ",
                "  ***  ",
                "*******"
        });

        map.put('J', new String[]{
                "    ***",
                "     **",
                "     **",
                "     **",
                "     **",
                "**   **",
                " ***** "
        });

        map.put('K', new String[]{
                "**   **",
                "**  ** ",
                "** **  ",
                "*****  ",
                "** **  ",
                "**  ** ",
                "**   **"
        });

        map.put('L', new String[]{
                "**     ",
                "**     ",
                "**     ",
                "**     ",
                "**     ",
                "**     ",
                "*******"
        });

        map.put('M', new String[]{
                "**   **",
                "*** ***",
                "*******",
                "** * **",
                "**   **",
                "**   **",
                "**   **"
        });

        map.put('N', new String[]{
                "**   **",
                "***  **",
                "**** **",
                "** ****",
                "**  ***",
                "**   **",
                "**   **"
        });

        map.put('O', new String[]{
                " ***** ",
                "**   **",
                "**   **",
                "**   **",
                "**   **",
                "**   **",
                " ***** "
        });

        map.put('P', new String[]{
                "****** ",
                "**   **",
                "**   **",
                "****** ",
                "**     ",
                "**     ",
                "**     "
        });

        map.put('Q', new String[]{
                " ***** ",
                "**   **",
                "**   **",
                "**   **",
                "** * **",
                "**  ** ",
                " **** *"
        });

        map.put('R', new String[]{
                "****** ",
                "**   **",
                "**   **",
                "****** ",
                "** **  ",
                "**  ** ",
                "**   **"
        });

        map.put('S', new String[]{
                " ***** ",
                "**   **",
                "**     ",
                " ***** ",
                "     **",
                "**   **",
                " ***** "
        });

        map.put('T', new String[]{
                "*******",
                "  ***  ",
                "  ***  ",
                "  ***  ",
                "  ***  ",
                "  ***  ",
                "  ***  "
        });

        map.put('U', new String[]{
                "**   **",
                "**   **",
                "**   **",
                "**   **",
                "**   **",
                "**   **",
                " ***** "
        });

        map.put('V', new String[]{
                "**   **",
                "**   **",
                "**   **",
                "**   **",
                " ** ** ",
                "  ***  ",
                "   *   "
        });

        map.put('W', new String[]{
                "**   **",
                "**   **",
                "**   **",
                "** * **",
                "*******",
                "*** ***",
                "**   **"
        });

        map.put('X', new String[]{
                "**   **",
                " ** ** ",
                "  ***  ",
                "   *   ",
                "  ***  ",
                " ** ** ",
                "**   **"
        });

        map.put('Y', new String[]{
                "**   **",
                " ** ** ",
                "  ***  ",
                "   *   ",
                "   *   ",
                "   *   ",
                "   *   "
        });

        map.put('Z', new String[]{
                "*******",
                "    ** ",
                "   **  ",
                "  **   ",
                " **    ",
                "**     ",
                "*******"
        });

        // Цифры 0-9
        map.put('0', new String[]{
                " ***** ",
                "**   **",
                "**  ***",
                "** * **",
                "***  **",
                "**   **",
                " ***** "
        });

        map.put('1', new String[]{
                "  **   ",
                " ***   ",
                "  **   ",
                "  **   ",
                "  **   ",
                "  **   ",
                "*******"
        });

        map.put('2', new String[]{
                " ***** ",
                "**   **",
                "     **",
                "   *** ",
                " ***   ",
                "**     ",
                "*******"
        });

        map.put('3', new String[]{
                " ***** ",
                "**   **",
                "     **",
                "  **** ",
                "     **",
                "**   **",
                " ***** "
        });

        map.put('4', new String[]{
                "   *** ",
                "  **** ",
                " ** ** ",
                "**  ** ",
                "*******",
                "    ** ",
                "    ** "
        });

        map.put('5', new String[]{
                "*******",
                "**     ",
                "****** ",
                "     **",
                "     **",
                "**   **",
                " ***** "
        });

        map.put('6', new String[]{
                " ***** ",
                "**   **",
                "**     ",
                "****** ",
                "**   **",
                "**   **",
                " ***** "
        });

        map.put('7', new String[]{
                "*******",
                "     **",
                "    ** ",
                "   **  ",
                "  **   ",
                "  **   ",
                "  **   "
        });

        map.put('8', new String[]{
                " ***** ",
                "**   **",
                "**   **",
                " ***** ",
                "**   **",
                "**   **",
                " ***** "
        });

        map.put('9', new String[]{
                " ***** ",
                "**   **",
                "**   **",
                " ******",
                "     **",
                "**   **",
                " ***** "
        });

        // Знаки препинания и символы
        map.put(' ', new String[]{
                "    ",
                "    ",
                "    ",
                "    ",
                "    ",
                "    ",
                "    "
        });

        map.put('!', new String[]{
                "  **   ",
                "  **   ",
                "  **   ",
                "  **   ",
                "  **   ",
                "       ",
                "  **   "
        });

        map.put('?', new String[]{
                " ***** ",
                "**   **",
                "     **",
                "   *** ",
                "  **   ",
                "       ",
                "  **   "
        });

        map.put('.', new String[]{
                "       ",
                "       ",
                "       ",
                "       ",
                "       ",
                "  **   ",
                "  **   "
        });

        map.put(',', new String[]{
                "       ",
                "       ",
                "       ",
                "       ",
                "  **   ",
                "  **   ",
                " **    "
        });

        map.put(':', new String[]{
                "       ",
                "  **   ",
                "  **   ",
                "       ",
                "  **   ",
                "  **   ",
                "       "
        });

        map.put(';', new String[]{
                "       ",
                "  **   ",
                "  **   ",
                "       ",
                "  **   ",
                "  **   ",
                " **    "
        });

        map.put('-', new String[]{
                "       ",
                "       ",
                "       ",
                "*******",
                "       ",
                "       ",
                "       "
        });

        map.put('+', new String[]{
                "       ",
                "  **   ",
                "  **   ",
                "*******",
                "  **   ",
                "  **   ",
                "       "
        });

        map.put('*', new String[]{
                "       ",
                "** * **",
                " *****",
                "  ***  ",
                " *****",
                "** * **",
                "       "
        });

        map.put('=', new String[]{
                "       ",
                "       ",
                "*******",
                "       ",
                "*******",
                "       ",
                "       "
        });

        map.put('/', new String[]{
                "     **",
                "    ** ",
                "   **  ",
                "  **   ",
                " **    ",
                "**     ",
                "*      "
        });

        map.put('\\', new String[]{
                "**     ",
                " **    ",
                "  **   ",
                "   **  ",
                "    ** ",
                "     **",
                "      *"
        });

        map.put('(', new String[]{
                "   **  ",
                "  **   ",
                " **    ",
                " **    ",
                " **    ",
                "  **   ",
                "   **  "
        });

        map.put(')', new String[]{
                "  **   ",
                "   **  ",
                "    ** ",
                "    ** ",
                "    ** ",
                "   **  ",
                "  **   "
        });

        return map;
    }

    /**
     * Печатает текст в виде ASCII-арта.
     *
     * @param text текст для преобразования
     */
    public void printText(String text) {
        if (text == null || text.isEmpty()) {
            System.out.println("(Пустой текст)");
            return;
        }

        String upperCaseText = text.toUpperCase();

        for (int row = 0; row < CHAR_HEIGHT; row++) {
            StringBuilder lineBuilder = new StringBuilder();

            for (char c : upperCaseText.toCharArray()) {
                String[] charArt = asciiMap.get(c);

                if (charArt != null) {
                    lineBuilder.append(charArt[row]);
                } else {
                    // Для неизвестных символов - вопросительный знак
                    String[] questionMark = asciiMap.get('?');
                    lineBuilder.append(questionMark != null ? questionMark[row] : "       ");
                }

                lineBuilder.append(SEPARATOR);
            }

            System.out.println(lineBuilder);
        }
    }

    /**
     * Печатает текст с декоративной рамкой.
     *
     * @param text текст для печати
     */
    public void printTextWithBorder(String text) {
        if (text == null || text.isEmpty()) return;

        String upperCaseText = text.toUpperCase();
        int totalWidth = calculateWidth(upperCaseText);

        // Верхняя граница
        printBorderLine(totalWidth, '╔', '═', '╗');

        // Текст
        for (int row = 0; row < CHAR_HEIGHT; row++) {
            System.out.print("║ ");

            for (char c : upperCaseText.toCharArray()) {
                String[] charArt = asciiMap.get(c);
                if (charArt != null) {
                    System.out.print(charArt[row]);
                } else {
                    System.out.print("       ");
                }
                System.out.print(SEPARATOR);
            }

            System.out.println(" ║");
        }

        // Нижняя граница
        printBorderLine(totalWidth, '╚', '═', '╝');
    }

    /**
     * Вычисляет ширину текста в символах.
     */
    private int calculateWidth(String text) {
        int width = 0;
        for (char c : text.toCharArray()) {
            String[] charArt = asciiMap.get(c);
            if (charArt != null) {
                width += charArt[0].length() + SEPARATOR.length();
            }
        }
        return width + 2; // +2 для пробелов с краев
    }

    /**
     * Печатает декоративную линию.
     */
    private void printBorderLine(int width, char left, char middle, char right) {
        System.out.print(left);
        for (int i = 0; i < width; i++) {
            System.out.print(middle);
        }
        System.out.println(right);
    }

    /**
     * Демонстрация возможностей генератора.
     */
    public static void main(String[] args) {
        AsciiArtGenerator generator = new AsciiArtGenerator();

        System.out.println("\n========== ASCII ART GENERATOR ==========\n");

        // Пример 1: Простой текст
        System.out.println("--- Пример 1: Простой текст ---");
        generator.printText("HELLO");

        System.out.println("\n--- Пример 2: Текст с цифрами ---");
        generator.printText("CODE 2024");

        System.out.println("\n--- Пример 3: Алфавит ---");
        generator.printText("ABCDEFGHIJKLM");
        System.out.println();
        generator.printText("NOPQRSTUVWXYZ");

        System.out.println("\n--- Пример 4: Цифры ---");
        generator.printText("0123456789");

        System.out.println("\n--- Пример 5: Со знаками препинания ---");
        generator.printText("HELLO WORLD!");

        System.out.println("\n--- Пример 6: С рамкой ---");
        generator.printTextWithBorder("JAVA");

        System.out.println("\n--- Пример 7: Математическое выражение ---");
        generator.printText("2+2=4");

        System.out.println("\n========================================\n");
    }
}