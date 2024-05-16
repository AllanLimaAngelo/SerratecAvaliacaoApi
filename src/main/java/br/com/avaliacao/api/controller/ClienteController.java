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

import br.com.avaliacao.api.dto.ClienteDTO;
import br.com.avaliacao.api.service.ClienteService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping
	public ResponseEntity<List<ClienteDTO>> buscarTodos() {
		List<ClienteDTO> clientes = clienteService.buscarTodos();
		return ResponseEntity.ok(clientes);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClienteDTO> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.of(clienteService.buscarPorId(id));
	}

	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<ClienteDTO>> buscarPorNome(@PathVariable String nome) {
		List<ClienteDTO> clientes = clienteService.buscarPorNome(nome);
		return ResponseEntity.ok(clientes);
	}

	@GetMapping("/email/{email}")
	public ResponseEntity<List<ClienteDTO>> buscarPorEmail(@PathVariable String email) {
		List<ClienteDTO> clientes = clienteService.buscarPorEmail(email);
		return ResponseEntity.ok(clientes);
	}

	@GetMapping("/telefone/{telefone}")
	public ResponseEntity<List<ClienteDTO>> buscarPorTelefone(@PathVariable String telefone) {
		List<ClienteDTO> clientes = clienteService.buscarPorTelefone(telefone);
		return ResponseEntity.ok(clientes);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ClienteDTO> cadastrar(@Valid @RequestBody ClienteDTO cliente) {
		return ResponseEntity.ok(clienteService.cadastrar(cliente));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ClienteDTO> alterar(@PathVariable Long id, @Valid @RequestBody ClienteDTO clienteAlterado) {
		return ResponseEntity.of(clienteService.alterar(id, clienteAlterado));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		if (!clienteService.deletar(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}

}
