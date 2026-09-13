package core;

import java.util.Objects;

public class PetAddress {
  private final String numero;
  private final String cidade;
  private final String rua;


  public PetAddress(int numero, String cidade, String rua) {
    this.numero = String.valueOf(numero);
    this.cidade = cidade;
    this.rua = rua;
  }

  public PetAddress(String cidade, String rua) {
    this.numero = Pet.NoData;
    this.cidade = cidade;
    this.rua = rua;
  }

  public static PetAddress of(Integer numero, String cidade, String rua) {

    if (Objects.nonNull(numero)) {
      return  new PetAddress(numero, cidade, rua);
    }

    return new PetAddress(cidade, rua);
  }
}
