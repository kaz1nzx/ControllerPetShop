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

import com.petShop.Entity.Tutor;
import com.petShop.Service.TutorService;

@RestController
@RequestMapping
public class TutorController {
	private final TutorService tutorService;

	public TutorController(TutorService tutorService) {
		this.tutorService = tutorService;
	}

	@GetMapping
	public ResponseEntity<List<Tutor>> getAllTutor() {
		List<Tutor> tutores = tutorService.getAllTutor();
		return ResponseEntity.ok(tutores);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Tutor> getTutorById(@PathVariable Long id) {
		Tutor tutor = tutorService.getTutorById(id);
		if (tutor != null) {
			return ResponseEntity.ok(tutor);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<Tutor> criarTutor(@RequestBody Tutor tutor) {
		Tutor criarTutor = tutorService.saveTutor(tutor);
		return ResponseEntity.status(HttpStatus.CREATED).body(criarTutor);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Tutor> alteraTutor(@PathVariable Long id, @RequestBody Tutor tutor) {
		Tutor alteraTutor = tutorService.alteraTutor(id, tutor);
		if (alteraTutor != null) {
			return ResponseEntity.ok(alteraTutor);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping
	public ResponseEntity<String> deleteTutor(@PathVariable Long id) {
		boolean deleted = tutorService.deleteTutor(id);
		if (deleted) {
			return ResponseEntity.ok("Tutor Excluído");
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
