import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CadastroApplication {

  private final static File formularioSource = new File("src/resources/formulario.txt");

  public static void main(String[] args) {

    try (Scanner scanner = new Scanner(formularioSource)) {

      while (scanner.hasNext()) {
        System.out.println(scanner.nextLine());
      }

    } catch (FileNotFoundException fileError) {
      System.out.println("Arquivo formulario.txt não encontrado");
    }

  }
}