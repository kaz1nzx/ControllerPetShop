package com.petShop.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petShop.Entity.Pet;
import com.petShop.Service.PetService;

@RestController
@RequestMapping
public class PetController {
	private final PetService petService;

	public PetController(PetService petService) {
		this.petService = petService;
	}

	@GetMapping
	public ResponseEntity<List<Pet>> getAllPet() {
		List<Pet> pets = petService.getAllPet();
		return ResponseEntity.ok(pets);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pet> getPetById(@PathVariable Long id) {
		Pet pet = petService.getPetById(id);
		if (pet != null) {
			return ResponseEntity.ok(pet);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<Pet> criarEndereco(@RequestBody Pet pet) {
		Pet criarPet = petService.savePet(pet);
		return ResponseEntity.status(HttpStatus.CREATED).body(criarPet);
	}

	@PutMapping
	public ResponseEntity<Pet> alteraPet(@PathVariable Long id, @RequestBody Pet pet) {
		Pet alteraPet = petService.alteraPet(id, pet);
		if (alteraPet != null) {
			return ResponseEntity.ok(alteraPet);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping
	public ResponseEntity<String> deleteUsuario(@PathVariable Long id) {
		boolean deleted = petService.deletePet(id);
		if (deleted) {
			return ResponseEntity.ok("Usuário excluído com sucesso!");
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
