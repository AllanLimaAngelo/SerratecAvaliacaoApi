package br.com.avaliacao.api.controller;

import java.time.LocalDateTime;
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

import br.com.avaliacao.api.dto.PedidoDTO;
import br.com.avaliacao.api.service.PedidoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;

	@GetMapping
	public ResponseEntity<List<PedidoDTO>> buscarTodosPedidos() {
		List<PedidoDTO> pedidos = pedidoService.buscarTodosPedidos();
		return ResponseEntity.ok(pedidos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PedidoDTO> buscarPedidoPorId(@PathVariable Long id) {
		return ResponseEntity.of(pedidoService.buscarPedidoPorId(id));
	}

	@GetMapping("/data/{dataPedido}")
	public ResponseEntity<List<PedidoDTO>> buscarPedidosPorData(@PathVariable LocalDateTime dataPedido) {
		List<PedidoDTO> pedidos = pedidoService.buscarPedidosPorData(dataPedido);
		return ResponseEntity.ok(pedidos);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<PedidoDTO> cadastrarPedido(@Valid @RequestBody PedidoDTO pedido) {
		return ResponseEntity.ok(pedidoService.cadastrarPedido(pedido));
	}

	@PutMapping("/{id}")
	public ResponseEntity<PedidoDTO> atualizarPedido(@PathVariable Long id,
			@Valid @RequestBody PedidoDTO pedidoAtualizado) {
		return ResponseEntity.of(pedidoService.atualizarPedido(id, pedidoAtualizado));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarPedido(@PathVariable Long id) {
		if (!pedidoService.deletarPedido(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}
}
