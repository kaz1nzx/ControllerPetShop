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

import com.petShop.Entity.Vacinacao;
import com.petShop.Service.VacinacaoService;

@RestController
@RequestMapping
public class VacinacaoController {

	private final VacinacaoService vacinacaoService;

	public VacinacaoController(VacinacaoService vacinacaoService) {
		this.vacinacaoService = vacinacaoService;
	}

	@GetMapping
	public ResponseEntity<List<Vacinacao>> getAllVacinacao() {
		List<Vacinacao> vacinacoes = vacinacaoService.getAllVacinacao();
		return ResponseEntity.ok(vacinacoes);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Vacinacao> getVacinacaoById(@PathVariable Long id) {
		Vacinacao vacinacao = vacinacaoService.getVacinacaoById(id);
		if (vacinacao != null) {
			return ResponseEntity.ok(vacinacao);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<Vacinacao> criarVacinacao(@RequestBody Vacinacao vacinacao) {
		Vacinacao criarVacinacao = vacinacaoService.saveVacinacao(vacinacao);
		return ResponseEntity.status(HttpStatus.CREATED).body(criarVacinacao);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Vacinacao> alteraVacinacao(@PathVariable Long id, @RequestBody Vacinacao vacinacao) {
		Vacinacao alteraVacinacao = vacinacaoService.alteraVacinacao(id, vacinacao);
		if (alteraVacinacao != null) {
			return ResponseEntity.ok(alteraVacinacao);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping
	public ResponseEntity<String> deletarVacinacao(@PathVariable Long id) {
		boolean deleted = vacinacaoService.deleteVacinacao(id);
		if (deleted) {
			return ResponseEntity.ok().body("Vacinação deletada");
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
