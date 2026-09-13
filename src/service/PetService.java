package service;

import DTOs.PetInputDTO;
import core.Pet;
import core.PetRepository;

public class PetService {

  private final PetRepository repository;

  public PetService(PetRepository repository) {
    this.repository = repository;
  }

  public void save(PetInputDTO petInput) {

    Pet newPet = new Pet(
        petInput.nome(),
        petInput.tipo(),
        petInput.genero(),
        petInput.endereco(),
        petInput.idade(),
        petInput.peso(),
        petInput.raca());

    repository.save(newPet);

  }
}
