package DTOs;

import core.Pet;
import core.PetAddress;
import core.PetGender;
import core.PetType;

public record PetInputDTO
    (
        String nome,
        PetType tipo,
        PetGender genero,
        PetAddress endereco,
        String idade,
        String peso,
        String raca
    )
{
  public PetInputDTO {
    nome = (nome == null || nome.isBlank()) ? Pet.NoData : nome.toLowerCase().trim();
    raca = (raca == null || raca.isBlank()) ? Pet.NoData : raca.toLowerCase().trim();
    idade = (idade == null || idade.isBlank()) ? Pet.NoData : idade.replace(",", ".").toLowerCase().trim();
    peso = (peso == null || peso.isBlank()) ? Pet.NoData : peso.replace(",", ".").toLowerCase().trim();

    if (!nome.equals((Pet.NoData))) verifyNome(nome);

    if (!idade.equals((Pet.NoData))) verifyIdade(idade);

    if (!raca.equals(Pet.NoData)) verifyRaca(raca);

    if (!peso.equals((Pet.NoData))) verifyPeso(peso);
  }

  private static void verifyOnlyLetters(String value) {
    if (!value.matches("^[a-zA-Z ]+$")) //
      throw new IllegalArgumentException("Forneça apenas letras", new Throwable("Valor informado: " + value));
  }

  private static void verifyOnlyNumbers(String value) {
    if (!value.matches("^\\d+([.,]\\d+)?$"))
      throw new IllegalArgumentException("Forneça apenas numeros", new Throwable("Valor informado: " + value));
  }

  public static void verifyNome(String nome) {
    try {
      verifyOnlyLetters(nome);

      if (nome.split(" ").length <= 1)
        throw new IllegalArgumentException("Forneça nome e sobrenome", new Throwable("Valor informado: " + nome));
    } catch (IllegalArgumentException ex) {
      throw new IllegalArgumentException("Nome incorreto: " + ex.getMessage() + " " + ex.getCause().getMessage());
    }

  }

  public static void verifyIdade(String idade) {
    try {

      if (!idade.toLowerCase().matches("^(?:\\d+\\s+(?:mes|meses)|\\d+(?:\\.\\d+)?\\s+(?:ano|anos))$")) {
        throw new IllegalArgumentException("Forneça um valor valido para a idade. Exemplo: '2 meses', '1.2 anos'");
      }

      verifyOnlyNumbers(idade.split(" ")[0]);

    } catch (IllegalArgumentException ex) {
      throw new IllegalArgumentException("Idade incorreta: " + ex.getMessage());
    }


  }

  public static void verifyPeso(String peso) {
    try {

      if (!peso.toLowerCase().matches("^(\\d+(\\.\\d+)?(g|kg))$"))  {
        throw new IllegalArgumentException("Forneça um valor valido para o peso. Exemplo: '2kg', '2.4kg', '600g', '200g'");
      }

      verifyOnlyNumbers(peso.split(" ")[0]);

    } catch (IllegalArgumentException ex) {
      throw new IllegalArgumentException("Peso incorreto: " + ex.getMessage());
    }

  }

  public static void verifyRaca(String raca) {
    try {
      verifyOnlyLetters(raca.concat(" "));
    } catch (IllegalArgumentException ex) {
      throw new IllegalArgumentException("Raça incorreta: " + ex.getMessage());
    }

  }

}
