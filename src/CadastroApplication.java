import DTOs.PetInputDTO;
import core.*;
import repository.PetFileRepository;
import core.PetRepository;
import service.PetService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CadastroApplication {

  private final static File formularioSource = new File("src/resources/formulario.txt");
  private final static String storagePath = "src/petsCadastrados";
  private final static Scanner terminalScanner = new Scanner(System.in);

  public static void main(String[] args) {

    PetRepository repository = new PetFileRepository(storagePath);
    PetService petService = new PetService(repository);

    switch (menu()) {
      case op1 -> petService.save(getDataInput());
    }

    cleanup();
  }

  public static MenuOptions menu() {

    MenuOptions optionSelected;

    while (true) {
      menuPrint();
      System.out.print("Escolha um opção: ");

      try {
        int indexSelect = Integer.parseInt(terminalScanner.next());

        optionSelected = MenuOptions.optionOf(indexSelect);

        terminalScanner.nextLine();
        break;
      }
      catch (IllegalArgumentException ex ) {
        System.out.println("Forneça um valor dentro das opções\n\n");
      }
    }

    return optionSelected;
  }

  public static void menuPrint() {

    System.out.println("\n|                           MENU                         |");

    for (MenuOptions op: MenuOptions.values()) {
      System.out.printf("| %d: %-51s |%n", op.getIndex(), op.getLabel());
    }
  }

  public static PetInputDTO getDataInput() {

    List<String> asnwers = new ArrayList<>();

    PetInputDTO petInput = null;

    while (true) {
      try (Scanner fileScanner = new Scanner(formularioSource)) {
        String input = "";

        while (fileScanner.hasNext()) {
          String question = fileScanner.nextLine();

            System.out.print(question + " ");

            if (question.contains("endereço")) {
              System.out.print("\n    Numero: ");
              input = terminalScanner.nextLine();

              System.out.print("    Cidade: ");
              input += "," + terminalScanner.nextLine();

              System.out.print("    Rua: ");
              input += "," + terminalScanner.nextLine();

            } else {
              input = terminalScanner.nextLine();
            }

            asnwers.add(input);
        }

      } catch (FileNotFoundException ex) {
        System.out.println("ERROR: O arquivo do formulario não existe");
        break;
      }
      
      try {

        PetType type = PetType.typeOf(asnwers.get(1));
        PetGender gender = PetGender.genderOf(asnwers.get(2));
        
        String[] splitted = asnwers.get(3).split(",");
        
        PetAddress address = PetAddress.of(
            Integer.valueOf(splitted[0]),
            splitted[1],
            splitted[2]);
        
        petInput = new PetInputDTO(
            asnwers.get(0),
            type,
            gender,
            address,
            asnwers.get(4),
            asnwers.get(5),
            asnwers.get(6)
        );

      } catch (IllegalArgumentException ex) {

        System.out.println("Erro " + ex.getMessage());
        System.out.println("Tente Novamente");
        continue;
      }

      break;
    }

    return petInput;
  }

  private static void cleanup() {
    terminalScanner.close();
  }
}