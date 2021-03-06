package gestisimal.util;

import java.util.Scanner;

/**
 * Conjunto de funciones genéricas útiles para simplificar procesos.
 *
 *  @author Maria Cervilla Alcalde
 *
 */
public class Util {
  private static Scanner in = new Scanner(System.in);

  public static String readStr(String prompt) {
    System.out.print(prompt + ": ");
    return in.nextLine();
  }

  public static String readStr(String prompt, String end) {
    String str = readStr(prompt);
    System.out.print(end);
    return str;
  }

  public static int readInt(String prompt) {
    System.out.print(prompt + ": ");
    int n = in.nextInt();
    in.nextLine(); // sacamos \n del buffer de teclado
    return n;
  }
  
  public static double readDouble(String prompt) {
    System.out.print(prompt + ": ");
    double n = in.nextInt();
    in.nextLine(); // sacamos \n del buffer de teclado
    return n;
  }

  public static int readInt(String prompt, String end) {
    int n = readInt(prompt);
    System.out.print(end);
    return n;
  }

  public static int randomInt(int min, int max) {
    return min + (int) (Math.random() * (max - min + 1));
  }
}
