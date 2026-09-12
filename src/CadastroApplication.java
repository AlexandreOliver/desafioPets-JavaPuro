import core.MenuOptions;

import java.io.File;
import java.util.Scanner;

public class CadastroApplication {

  private final static File formularioSource = new File("src/resources/formulario.txt");
  private final static Scanner terminalScanner = new Scanner(System.in);
  private static MenuOptions optionSelect;

  public static void main(String[] args) {

    menu();

    cleanup();
  }

  public static void menu() {

    while (true) {
      menuPrint();
      System.out.print("Escolha um opção: ");

      try {
        int indexSelect = Integer.parseInt(terminalScanner.next());

        optionSelect = MenuOptions.optionOf(indexSelect);
        break;
      }
      catch (IllegalArgumentException ex ) {
        System.out.println("Forneça um valor dentro das opções\n\n");
      }
    }
  }

  public static void menuPrint() {

    System.out.println("|                           MENU                         |");

    for (MenuOptions op: MenuOptions.values()) {
      System.out.printf("| %d: %-51s |%n", op.getIndex(), op.getLabel());
    }
  }

  private static void cleanup() {
    terminalScanner.close();
  }
}