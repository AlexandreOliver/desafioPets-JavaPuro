package core;

import java.util.Arrays;
import java.util.Objects;

public enum PetGender {
  MALE("Macho"),
  FEMALE("Femea");

  private final String label;

  PetGender(String label) {
    this.label = label;
  }

  public String getLabel() {
    return this.label;
  }

  public static PetGender genderOf(String label) throws IllegalArgumentException {
    for (PetGender op: PetGender.values()) {
      if (Objects.equals(op.label.toLowerCase(), label.toLowerCase())) return op;
    }

    throw new IllegalArgumentException("Gênero Incorreto. Gêneros Permitidos: " + Arrays.toString(PetGender.values()));
  }

 }
