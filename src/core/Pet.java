package core;

import java.util.Formatter;

public class Pet {
  private String nome;
  private PetType tipo;
  private PetGender genero;
  private PetAddress endereco;
  private String idade;
  private String peso;
  private String raca;

  public static final String NoData = "NÃO INFORMADO";

  public Pet(String nome, PetType tipo,
             PetGender genero, PetAddress endereco,
             String idade, String peso, String raca) {

    if (!peso.equals(NoData)) verifyPeso(peso);

    this.nome = nome;
    this.tipo = tipo;
    this.genero = genero;
    this.endereco = endereco;
    this.idade = (!idade.equals(NoData))? formatIdade(idade) : idade;
    this.peso = peso;
    this.raca = raca;
  }

  public static void verifyPeso(String value) {
    String[] splitted = value.split(" ");

    double number = Double.parseDouble(splitted[0]);
    String unid = splitted[1];

    if (unid.equals("g") || unid.equals("gramas")) {
      if (number/1000 > 60 || number/1000 < 0.5)
        throw new IllegalArgumentException("O valor do peso esta fora da faixa de 500g a 60 Kg");
    } else {
      if (number > 60 || number < 0.5)
        throw new IllegalArgumentException("O valor do peso esta fora da faixa de 0,5 a 60 Kg");
    }

  }

  public String formatIdade(String idade) {
    double value = Double.parseDouble(idade.split(" ")[0]);

    double result = (idade.contains("mes")) ? value / 12.0 : value;

    return new Formatter().format("%.2f anos", result).toString();
  }

}
