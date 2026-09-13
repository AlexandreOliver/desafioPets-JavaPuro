package core;

import java.util.Arrays;
import java.util.Objects;

public enum PetType {
  DOG("Cachorro"),
  CAT("Gato");

  private final String label;

  PetType(String label) {
    this.label = label;
  }

  public String getLabel() {
    return this.label;
  }

  public static PetType typeOf(String label) throws IllegalArgumentException {
    for (PetType op: PetType.values()) {
      if (Objects.equals(op.label.toLowerCase(), label.toLowerCase())) return op;
    }

    throw new IllegalArgumentException("Tipo Incorreto. Tipos Existentes: " + Arrays.toString(PetType.values()));
  }
}
