import java.util.Scanner;

class Calculator {
    public static void main(String[] args) throws Exception {
        // ШАГ 1. Принять на вход строку выражение
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Введите два римских или арабских числа: ");
            String counting = sc.nextLine(); // [1. 5 + 5] [2. VII - IV] [3. 8 $ 4] [4. X - 6]
            System.out.println("Ответ: ");
            System.out.println(parse(counting));
        }
    }

    public static String parse(String counting) throws Exception {
        // ШАГ 2. Разбить на составляющие
        String[] split = counting.split(" ");
        // ШАГ 3. Работа с составляющими
        String num1 = split[0];
        String oper;
        oper = operation(counting);
        String num2 = split[2];
        //Вывод результата
        String result;
        // ШАГ 3.1 Проверить римская ли первая составляющая?
        if (roman(split[0])) {
        }
        // ШАГ 3.1.2 Проверить римская ли третья составляющая?
        if (roman(split[2])) {
        }
        // ШАГ 3.1.3 Проверить вторую составляющую
        String[] operands = counting.split("[+\\-*/]");
        // ШАГ 3.2 Если и первая сост. и вторая сост. римские
        // ШАГ 3.2.1 римские перевести в арабские
        if (roman(split[0]) && roman(split[2])) {
            num1 = String.valueOf(convertArab(split[0]));
            num2 = String.valueOf(convertArab(split[2]));
        }
        // ДОП ШАГ К 3.2.1 Условие что 1 и 2 составляющая явлется арабской
        else if (!roman(split[0]) && !roman(split[2])) {
            num1 = String.valueOf(Integer.parseInt(split[0]));
            num2 = String.valueOf(Integer.parseInt(split[2]));
        } else {
            throw new Exception("Числа должны быть в одном формате");
        }
        if (operands.length != 2)
            throw new Exception("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        // ШАГ 3.2.2 Посчитать арабские
        int arab = calculat(Integer.parseInt(num1), Integer.parseInt(num2), oper);
        // ШАГ 3.2.2.1 Определить вторую составляющую массива
        split[1] = operation(counting);
        //  ШАГ 4. Конвертировать ответ из арабского в римское
        if (roman(split[0]) && roman(split[2])) {
            result = Roman.convertToRoman(arab);
        } else {
            result = String.valueOf(arab);
        }
        return result;
    }

    // ШАГ 3.2.2.1.1 Если вторая сост. массива равна "+", то произвести сложение преобразованных чисел и тд
    static int calculat(int a, int b, String oper) {
        if (oper.equals("+")) return a + b;
        else if (oper.equals("-")) return a - b;
        else if (oper.equals("*")) return a * b;
        else return a / b;
    }

    static String operation(String counting) {
        if (counting.contains("+")) return "+";
        else if (counting.contains("-")) return "-";
        else if (counting.contains("*")) return "*";
        else if (counting.contains("/")) return "/";
        else return null;
    }

    //    Конвертация из римских чисел в арабские
    public static int convertArab(String numb) {
        return switch (numb) {
            case "I" -> 1;
            case "II" -> 2;
            case "III" -> 3;
            case "IV" -> 4;
            case "V" -> 5;
            case "VI" -> 6;
            case "VII" -> 7;
            case "VIII" -> 8;
            case "IX" -> 9;
            case "X" -> 10;
            default -> 0;
        };
    }

    public static boolean roman(String symb) {
        switch (symb) {
            case "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X" -> {
                return true;
            }
        }
        return false;
    }

}
class Roman {
    static String[] romanArray = new String[]{"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI",
            "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV",
            "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI",
            "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII",
            "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII",
            "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV",
            "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV",
            "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII",
            "XCVIII", "XCIX", "C"};

    public static boolean isRoman(String val) {
        for (int i = 0; i < romanArray.length; i++) {
            if (val.equals(romanArray[i])) {
                return true;
            }
        }
        return false;
    }

    public static String convertToRoman(int arab) {
        return Roman.romanArray[arab];
    }
}


