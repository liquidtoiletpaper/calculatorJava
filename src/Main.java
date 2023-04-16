import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static String calc(String input) throws CalculatorException {
        String[] romanNums = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        String[] arabicNums = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        String[] operations = {"+", "-", "*", "/"};
        String[] splited = input.split(" ");
        if (splited.length < 3 || !Arrays.asList(operations).contains(splited[1])) {
            throw new CalculatorException("Строка не является математической операцией");
        }
        if (splited.length > 3) {
            throw new CalculatorException("Строка не подходит по формату");
        }
        if (Arrays.asList(romanNums).contains(splited[0]) && Arrays.asList(romanNums).contains(splited[2])) {
            Roman firstRoman = Roman.valueOf(splited[0]);
            Roman secondRoman = Roman.valueOf(splited[2]);
            int first = firstRoman.getValue();
            int second = secondRoman.getValue();
            switch (splited[1]) {
                case "+":
                    System.out.println(convert(first + second));
                    break;
                case "-":
                    if (first - second < 1) {
                        throw new CalculatorException("В римской системе нет отрицательных чисел");
                    }
                    System.out.println(convert(first - second));
                    break;
                //System.out.println();
                case "*":
                    System.out.println(convert(first * second));
                    break;
                case "/":
                    if (first / second < 1) {
                        throw new CalculatorException("В римской системе нет нуля");
                    }
                    System.out.println(convert(first / second));
                    break;
            }
            return input;
        }
        try {
            if (splited[0].contains(".") || splited[2].contains(".")) {
                throw new CalculatorException("Введите целые числа");
            }
            if (Integer.parseInt(splited[0]) > 10 || Integer.parseInt(splited[2]) > 10 ||
                    Integer.parseInt(splited[0]) < 1 || Integer.parseInt(splited[2]) < 1) {
                throw new CalculatorException("Введите числа от 1 до 10");
            }
        /*
    // Т.к. в условии написано, что передаются только 2 числа, могу не стыдясь использовать в решении if с contain)))
    if(Arrays.asList(splited).contains("+")) {
        //Т.к. знак всегда в середине, могу сократить код, не добавляя новый array без знака, а сразу совершить сложение
        int result = Integer.parseInt(splited[0]) + Integer.parseInt(splited[2]);
        System.out.println(result);
    }
    else if(Arrays.asList(splited).contains("-")) {
        int result = Integer.parseInt(splited[0]) - Integer.parseInt(splited[2]);
        System.out.println(result);
    }
    else if(Arrays.asList(splited).contains("*")) {
        int result = Integer.parseInt(splited[0]) * Integer.parseInt(splited[2]);
        System.out.println(result);
    }
    else if(Arrays.asList(splited).contains("/")) {
        int result = Integer.parseInt(splited[0]) / Integer.parseInt(splited[2]);
        System.out.println(result);
    }
     */
            switch (splited[1]) {
                case "+" -> System.out.println(Integer.parseInt(splited[0]) + Integer.parseInt(splited[2]));
                case "-" -> System.out.println(Integer.parseInt(splited[0]) - Integer.parseInt(splited[2]));
                case "*" -> System.out.println(Integer.parseInt(splited[0]) * Integer.parseInt(splited[2]));
                case "/" -> System.out.println(Integer.parseInt(splited[0]) / Integer.parseInt(splited[2]));
            }
        } catch(NumberFormatException ex) {
            throw new CalculatorException("Операция возможна только с одинаковыми системами счисления");
        }
        return input;
    }
    public static String romanDigit(int n, String one, String five, String ten) {

        if (n >= 1) {
            if (n == 1) {
                return one;
            } else if (n == 2) {
                return one + one;
            } else if (n == 3) {
                return one + one + one;
            } else if (n == 4) {
                return one + five;
            } else if (n == 5) {
                return five;
            } else if (n == 6) {
                return five + one;
            } else if (n == 7) {
                return five + one + one;
            } else if (n == 8) {
                return five + one + one + one;
            } else if (n == 9) {
                return one + ten;
            }

        }
        return "";
    }
    public static String convert(int number) {
        String romanOnes = romanDigit(number % 10, "I", "V", "X");
        number /= 10;

        String romanTens = romanDigit(number % 10, "X", "L", "C");
        number /= 10;

        String romanHundreds = romanDigit(number % 10, "C", "D", "M");
        number /= 10;

        String romanThousands = romanDigit(number % 10, "M", "", "");
        number /= 10;

        return romanThousands + romanHundreds + romanTens + romanOnes;
    }
    public static void main(String[] args) throws CalculatorException {
        Scanner s = new Scanner(System.in);
        String inp = s.nextLine();
        calc(inp);
    }
}