package by.it.a_dubatovka.lesson03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

// Lesson 3. B_Huffman.
// Восстановите строку по её коду и беспрефиксному коду символов.

// В первой строке входного файла заданы два целых числа
// kk и ll через пробел — количество различных букв, встречающихся в строке,
// и размер получившейся закодированной строки, соответственно.
//
// В следующих kk строках записаны коды букв в формате "letter: code".
// Ни один код не является префиксом другого.
// Буквы могут быть перечислены в любом порядке.
// В качестве букв могут встречаться лишь строчные буквы латинского алфавита;
// каждая из этих букв встречается в строке хотя бы один раз.
// Наконец, в последней строке записана закодированная строка.
// Исходная строка и коды всех букв непусты.
// Заданный код таков, что закодированная строка имеет минимальный возможный размер.
//
//        Sample Input 1:
//        1 1
//        a: 0
//        0

//        Sample Output 1:
//        a


//        Sample Input 2:
//        4 14
//        a: 0
//        b: 10
//        c: 110
//        d: 111
//        01001100100111

//        Sample Output 2:
//        abacabad

public class B_Huffman {

    private Map<String, Character> map = new HashMap<>();
    private StringBuilder pattern = new StringBuilder();

    String decode(File file) throws FileNotFoundException {
        StringBuilder result = new StringBuilder();
        //прочитаем строку для кодирования из тестового файла
        Scanner scanner = new Scanner(file);
        Integer count = scanner.nextInt();
        Integer length = scanner.nextInt();

        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! НАЧАЛО ЗАДАЧИ !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
        // заполняем Map c кодами символов в качестве ключей и самими символами в качестве значения
        for (int i = 0; i < count; i++) {
            Character character = scanner.next().charAt(0);
            ;
            String value = scanner.next();
            map.put(value, character);
        }

        // читаем закодированную строку
        String encodedString = scanner.next();
        for (Character character : encodedString.toCharArray()) {
            parseHuffmanString(character, result); // парсим закодированную строку
        }

        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! КОНЕЦ ЗАДАЧИ !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
        return result.toString(); //01001100100111
    }

    void parseHuffmanString(Character newCharacter, StringBuilder result) {
        pattern.append(newCharacter); // добавляем новый символ к паттерну
        if (map.containsKey(pattern.toString())) { // ищем совпадение в Мар
            Character ch = map.get(pattern.toString());
            result.append(ch);
            pattern.setLength(0); // если нашли, чистим буффер паттерна
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        File f = new File(root + "by/it/a_dubatovka/lesson03/encodeHuffman.txt");
        B_Huffman instance = new B_Huffman();

        String result = instance.decode(f);

        System.out.println(result);
    }


}
