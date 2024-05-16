package br.com.avaliacao.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.avaliacao.api.dto.valorEntre;
import br.com.avaliacao.api.dto.PratoDTO;
import br.com.avaliacao.api.dto.TipoDTO;
import br.com.avaliacao.api.service.PratoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pratos")
public class PratoController {

	@Autowired
	private PratoService service;

	@GetMapping
	public ResponseEntity<List<PratoDTO>> buscarTodos() {
		return ResponseEntity.ok(service.buscarTodos());
	}

	@GetMapping("/{id}")
	public ResponseEntity<PratoDTO> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.of(service.buscarPorId(id));
	}

	@GetMapping("/nome")
	public ResponseEntity<List<PratoDTO>> buscarPorNome(@RequestBody String nome) {
		return ResponseEntity.ok(service.buscarPorNome(nome));
	}

	@GetMapping("/tipo")
	public ResponseEntity<List<PratoDTO>> buscarPorTipo(@RequestBody TipoDTO dto) {
		return ResponseEntity.ok(service.buscarPorTipo(dto.tipo()));
	}

	@GetMapping("/preco")
	public ResponseEntity<List<PratoDTO>> buscarPorPreco(@RequestBody valorEntre dto) {
		return ResponseEntity.ok(service.buscarPorPreco(dto.menor(), dto.maior()));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<PratoDTO> cadastrar(@Valid @RequestBody PratoDTO produto) {
		return ResponseEntity.ok(service.cadastrar(produto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<PratoDTO> AlterarProduto(@Valid @PathVariable Long id,
			@RequestBody PratoDTO produtoAlterado) {
		return ResponseEntity.of(service.alterar(id, produtoAlterado));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
		if (!service.deletar(id)) {
			return ResponseEntity.notFound().build();
		}
		;
		return ResponseEntity.noContent().build();
	}
}