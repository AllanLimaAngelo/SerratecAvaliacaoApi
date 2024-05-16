package br.com.avaliacao.api.dto;

import br.com.avaliacao.api.model.Pedido;
import java.time.LocalDateTime;

public record PedidoDTO(
        Long id,
        LocalDateTime dataPedido,
        String prato) {

    public Pedido toEntity() {
        return new Pedido(this.id, this.dataPedido, this.prato);
    }
}
