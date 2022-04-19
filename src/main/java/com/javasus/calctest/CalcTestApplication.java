package com.javasus.calctest;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CalcTestApplication {
  static Map<Integer, String> arabRomanMap = new HashMap<>();
  static Boolean isFirstArabic;
  static Boolean isSecondArabic;
  static {
    arabRomanMap.put(1, "I");
    //arabRomanMap.put(4, "IV");
    arabRomanMap.put(5, "V");
    //arabRomanMap.put(9, "IX");
    arabRomanMap.put(10, "X");
//    arabRomanMap.put(40, "XL");
//    arabRomanMap.put(50, "L");
//    arabRomanMap.put(90, "XC");
//    arabRomanMap.put(100, "C");
  }


  public static void main(String[] args) throws Exception {
    //first comment
    Scanner textScan = new Scanner(System.in);
    String line = textScan.nextLine();

    //Парсим строку
    String symbol1;
    String symbol2;
    String result;
    char sign;

    String[] subStr;
    String delimeter = " ";
    subStr = line.split(delimeter); // Разделения строки line с помощью метода split()

    symbol1 = subStr[0];
    symbol2 = subStr[2];
    sign = subStr[1].charAt(0);
    int numb1 = parseSymbToNumb(symbol1, isFirstArabic);
    int numb2 = parseSymbToNumb(symbol2, isSecondArabic);

    if (isFirstArabic != isSecondArabic) {
      throw new Exception("Калькулятор принимает цифры одинакового типа!");
    }

    result = calculate(sign, numb1, numb2);
    System.out.println(result);

    //System.out.println(line + " " + numb1 + " " + numb2 + " " + sign);



    System.out.println(result);

  }

  private static String calculate(char sign, int numb1, int numb2) {
    String result;
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
    return result;
  }

  // Преобразует символ в число
  private static int parseSymbToNumb(String symbol, Boolean isArabic) throws Exception {
    int numb = 0;

    try {
      numb = Integer.parseInt(symbol);
      isArabic = true;
    } catch (NumberFormatException e) {
      numb = romeToArab(symbol);
      isArabic = false;
    }

    //Проверяем число от 1 до 10
    if (numb < 1 || numb > 10) {
      throw new Exception("Неверное число, калькулятор должен принимать на вход числа от 1 до 10 включительно, не более!");
    } else {
      return numb;
    }

//    if (isNumeric(symbol)) {
//      numb = Integer.parseInt(symbol);
//      //Проверяем число от 1 до 10
//      if (numb < 1 || numb > 10) {
//        throw new Exception("Неверное число, калькулятор должен принимать на вход числа от 1 до 10 включительно, не более!");
//      } else {
//        return numb;
//      }
//    } else {
//      // отправляем римски(или нет) числа для решения
//      getRomeToArab(symbol);
//    }

    return numb;
  }

  // Преобразуем римские цифры в арабские
  private static int romeToArab(String symbl) throws Exception {
    int result = 0;

    int i = 0;
    while (i < symbl.length()) {
      char letter = symbl.charAt(i);
      int numb = letterToNumber(letter);
      if (numb < 0) {
        throw new Exception("Не верный римский символ");
      }
      i++;
      if (i == symbl.length()) {
        result +=numb;
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
  // Ищем букву в map
  private static int letterToNumber (char letter) {
    int result = -1;
    for (Map.Entry<Integer, String> entry : arabRomanMap.entrySet()) {
      if (entry.getValue().equals(String.valueOf(letter))) {
        result = entry.getKey();
      }
    }
    return result;
  }

  //метод для проверки символа, число ли это?
  private static boolean isNumeric (String string){
      int intValue;
      try {
        intValue = Integer.parseInt(string);
        return true;
      } catch (NumberFormatException e) {
        return false;
      }
    }


}

