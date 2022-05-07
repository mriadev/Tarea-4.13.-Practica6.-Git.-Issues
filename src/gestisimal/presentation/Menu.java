package gestisimal.presentation;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Clase utilizada para la gestión de un menú. Se dedica a:
 * <ol>
 * 
 * <li>Mostrar las opciones del menú.</li>
 * 
 * <li>Recoger y devolver las opciones de un menú.</li>
 * </ol>
 * 
 * @author Maria Cervilla Alcalde
 *
 */
public class Menu {
  private String title;
  private String[] options;
  private Scanner in = new Scanner(System.in);

  /**
   * @param title título del menú
   * @param options opciones del menú
   */
  public Menu(String title, String... options) {
    this.title = title;
    this.options = options;
  }

  /**
   * Asigna el título del menú
   * 
   * @param title título del menú
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Añade la opción al menú
   * 
   * @param option opción del menú
   */
  public void add(String option) {
    String[] options = Arrays.copyOf(this.options, this.options.length + 1);
    options[options.length - 1] = option;
    this.options = options;
  }

  /**
   * Elimina la opción del menú
   * 
   * @param option opción del menú
   */
  public void remove(int option) { // las opciones empiezan en 1
    if (option < 1 || option > this.options.length) {
      throw new IndexOutOfBoundsException("Opción de menú a borrar fuera de rango.");
    }
    String[] options = Arrays.copyOf(this.options, this.options.length - 1);
    for (int i = option; i < this.options.length; i++) {
      options[i - 1] = this.options[i];
    }
    this.options = options;
  }

  /**
   * Elige la opción del menú
   * 
   * @return option opción del menú
   */
  public int choose() {
    while (true) {
      printTitle();
      printOptions();
      int option = readOption();
      if (option >= 1 && option <= this.options.length) {
        return option;
      }
      printError();
    }
  }

  /**
   * Imprime el título del menú
   */
  private void printTitle() {
    if (title != null && !title.isEmpty()) {
      System.out.println(title);
      System.out.println("=".repeat(title.length()));
    }
  }

  /**
   * Imprime las opciones del menú
   */
  private void printOptions() {
    int option = 0;
    for (String menuOption : this.options) {
      System.out.println(++option + ". " + menuOption);
    }
    System.out.println();
  }


  /**
   * Lee la opción introducida
   * 
   * @return option opción del menú
   */
  private int readOption() {
    int option;
    System.out.print("Introduzca una opción (1-" + options.length + "): ");
    try {
      option = in.nextInt();
    } catch (InputMismatchException e) {
      option = 0;
    }
    in.nextLine(); // sacamos el salto de línea del buffer de teclado
    System.out.println();
    return option;
  }

  /**
   * Devuelve la última opción del menú
   * 
   * @return option opción del menú
   */
  public int lastOption() {
    return options.length;
  }

  /**
   * Imprime un error al introducir la opción incorrecta
   */
  private void printError() {
    System.out.println("Opción incorrecta. Pulse Intro para continuar...");
    in.nextLine();
    System.out.println();
  }

  @Override
  public String toString() {
    return "Menu [title=" + title + ", options=" + Arrays.toString(options) + "]";
  }


}
