package br.com.avaliacao.api.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.avaliacao.api.dto.PedidoDTO;
import br.com.avaliacao.api.model.Pedido;
import br.com.avaliacao.api.repository.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	public List<PedidoDTO> buscarTodosPedidos() {
		return pedidoRepository.findAll().stream().map(Pedido::toDTO).collect(Collectors.toList());
	}

	public Optional<PedidoDTO> buscarPedidoPorId(Long id) {
		Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);
		return pedidoOptional.map(Pedido::toDTO);
	}

	public PedidoDTO cadastrarPedido(PedidoDTO pedidoDTO) {
		Pedido pedido = pedidoDTO.toEntity();
		pedidoRepository.save(pedido);
		return pedido.toDTO();
	}

	public Optional<PedidoDTO> atualizarPedido(Long id, PedidoDTO pedidoDTO) {
		if (pedidoRepository.existsById(id)) {
			Pedido pedido = pedidoDTO.toEntity();
			pedido.setId(id);
			pedidoRepository.save(pedido);
			return Optional.of(pedido.toDTO());
		}
		return Optional.empty();
	}

	public boolean deletarPedido(Long id) {
		if (pedidoRepository.existsById(id)) {
			pedidoRepository.deleteById(id);
			return true;
		}
		return false;
	}

	  public List<PedidoDTO> buscarPedidosPorData(LocalDateTime dataPedido) {
	        List<Pedido> pedidos = pedidoRepository.findByDataPedido(dataPedido);
	        return pedidos.stream().map(Pedido::toDTO).collect(Collectors.toList());
	    }
}
