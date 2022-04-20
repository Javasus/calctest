package com.javasus.calctest;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CalcTestApplication {
  static Map<Integer, String> arabRomanMap = new HashMap<>();
  static Boolean isFirstArabic;
  static Boolean isSecondArabic;
  static Boolean isArabic;
  static String [] romeArray = {"O", "I", "II", "III", "IV", "V", "VI", "VII",
      "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII",
      "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI",
      "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV",
      "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII",
      "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI",
      "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI",
      "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
      "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII",
      "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI",
      "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV",
      "XCVI", "XCVII", "XCVIII", "XCIX", "C"};

  static {
    arabRomanMap.put(1, "I");
    arabRomanMap.put(5, "V");
    arabRomanMap.put(10, "X");
  }

  public static void main(String[] args) throws Exception {
    // Чтение строки из консоли
    Scanner textScan = new Scanner(System.in);
    String line = textScan.nextLine();

    String symbol1;
    String symbol2;
    String result;
    char sign;

    String[] subStr;
    String delimiter = " ";
    subStr = line.split(delimiter); // Разделения строки line с помощью метода split()

    if (subStr.length < 3) {
      throw new Exception("Строка не является математической операцией");
    } else if (subStr.length > 3) {
      throw new Exception("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
    }

    symbol1 = subStr[0];
    symbol2 = subStr[2];
    sign = subStr[1].charAt(0);

    if (sign != '+' && sign != '-' && sign != '*' && sign != '/') {
      throw new Exception("Неверная арифметическая операция!");
    }

    int numb1 = parseSymbolToNumb(symbol1);
    isFirstArabic = isArabic;
    int numb2 = parseSymbolToNumb(symbol2);
    isSecondArabic = isArabic;

    if (isFirstArabic != isSecondArabic) {
      throw new Exception("Используются одновременно разные системы счисления!");
    }

    result = calculate(sign, numb1, numb2, isFirstArabic);
    System.out.println(result);
  }

  // Производит вычисления
  private static String calculate(char sign, int numb1, int numb2, boolean isFirstArabic) throws Exception {
    String resultStr;
    int result = 0;

    switch (sign) {
    case '+':
      result = numb1 + numb2;
      break;
    case '-':
      result = numb1 - numb2;
      break;
    case '*':
      result = numb1 * numb2;
      break;
    case '/':
      result = numb1 / numb2;
    }

    if (isFirstArabic) {
      resultStr = String.valueOf(result);
    } else {
      resultStr = arabToRome(result);
    }

    return resultStr;
  }

  // Преобразует арабские цифры в римские
  private static String arabToRome(int arabNumber) throws Exception {
    if (arabNumber < 1) {
      throw new Exception("В римской системе нет отрицательных чисел и нуля!");
    }
    return  romeArray[arabNumber];
  }

  // Преобразует символ в число
  private static int parseSymbolToNumb(String symbol) throws Exception {
    int numb = 0;

    try {
      numb = Integer.parseInt(symbol);
      isArabic = true;
    } catch (NumberFormatException e) {
      numb = romeToArab(symbol);
      isArabic = false;
    }

    //Проверяет число от 1 до 10
    if (numb < 1 || numb > 10) {
      throw new Exception("Неверное число, калькулятор должен принимать на вход числа от 1 до 10 включительно, не более!");
    } else {
      return numb;
    }
  }

  // Преобразует римские цифры в арабские
  private static int romeToArab(String symbl) throws Exception {
    int result = 0;

    int i = 0;
    while (i < symbl.length()) {
      char letter = symbl.charAt(i);
      int numb = letterToNumber(letter);

      if (numb < 0) {
        throw new Exception("Не верный символ");
      }

      i++;
      if (i == symbl.length()) {
        result += numb;
      } else {
        int nextNumb = letterToNumber(symbl.charAt(i));

        if (nextNumb > numb) {
          result += (nextNumb - numb);
          i++;
        } else {
          result += numb;
        }
      }
    }
    return result;
  }

  // Возвращает арабскую цифру, соотвествующую римской
  private static int letterToNumber(char letter) {
    int result = -1;
    for (Map.Entry<Integer, String> entry : arabRomanMap.entrySet()) {

      if (entry.getValue().equals(String.valueOf(letter))) {
        result = entry.getKey();
      }
    }
    return result;
  }
}