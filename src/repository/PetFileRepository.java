package repository;

import core.Pet;
import core.PetRepository;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PetFileRepository implements PetRepository {

  private final String storagePath;
  private static final DateTimeFormatter formatterDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

  public PetFileRepository(String storagePath) {
    this.storagePath = storagePath;
  }

  private String genFileName(Pet pet) {
    StringBuilder fileName = new StringBuilder();

    LocalDateTime date = LocalDateTime.now();
    String dateFormated = formatterDateTime.format(date).replaceAll("[-:]", "");

    String name = pet.getNome().toUpperCase().replace(" ", "");

    return fileName.append(dateFormated).append("-").append(name).append(".txt").toString();

  }


  @Override
  public void save(Pet pet) {

    try (FileWriter file = new FileWriter(storagePath + "/" + genFileName(pet))) {

      file.write("1 - " + pet.getNome() + System.lineSeparator());
      file.write("2 - " + pet.getTipo().getLabel() + System.lineSeparator());
      file.write("3 - " + pet.getGenero().getLabel() + System.lineSeparator());
      file.write("4 - " + pet.getEndereco().toString() + System.lineSeparator());

      String[] splited = pet.getIdade().replace("anos", "").split("\\.");

      if (splited[1].trim().equals("00") && !pet.getIdade().equals(Pet.NoData)) {
        file.write("5 - " + splited[0] + " anos" + System.lineSeparator());
      } else {
        file.write("5 - " + pet.getIdade() + System.lineSeparator());
      }

      file.write("6 - " + pet.getPeso() + System.lineSeparator());
      file.write("7 - " + pet.getRaca());

    } catch (IOException ex) {
      throw new RuntimeException("Erro ao salvar pet: " + ex.getMessage());
    }

  }
}
