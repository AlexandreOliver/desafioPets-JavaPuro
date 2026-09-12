package core;

public enum MenuOptions {
  op1(1, "Cadastrar um novo pet"),
  op2(2, "Alterar os dados do pet cadastrado"),
  op3(3, "Deletar um pet cadastrado"),
  op4(4, "Listar todos os pets cadastrados"),
  op5(5, "Listar pets por algum critério (idade, nome, raça)"),
  op6(6, "Sair");

  private final int index;
  private final String label;

  MenuOptions(int index, String label) {
    this.index = index;
    this.label = label;
  }

  public int getIndex() {
    return index;
  }

  public String getLabel() {
   return label;
  }

  public static MenuOptions optionOf(int index) {
    for (MenuOptions op: MenuOptions.values()) {
      if (op.index == index) return op;
    }

    throw new IllegalArgumentException("Não há opção para o index fornecido");
  }

}
