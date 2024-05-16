package br.com.avaliacao.api.model;

import java.time.LocalDateTime;

import br.com.avaliacao.api.dto.PedidoDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataPedido;
    private String prato;

    public Pedido() {}

    public Pedido(Long id, LocalDateTime dataPedido, String prato) {
        this.id = id;
        this.dataPedido = dataPedido;
        this.prato = prato;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDateTime dataPedido) {
        this.dataPedido = dataPedido;
    }

    public String getPrato() {
        return prato;
    }

    public void setPrato(String prato) {
        this.prato = prato;
    }

    public PedidoDTO toDTO() {
        return new PedidoDTO(this.id, this.dataPedido, this.prato);
    }
}
