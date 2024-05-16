package br.com.avaliacao.api.dto;

import br.com.avaliacao.api.model.Prato;
import br.com.avaliacao.api.model.Tipo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record PratoDTO(
        Long id,
        @NotBlank String nome,
        @NotBlank String descricao,
        @NotNull @PositiveOrZero Double preco,
        @NotNull Tipo tipo) {

    public Prato toEntity() {
        return new Prato(this.id, this.nome, this.descricao, this.preco, this.tipo);
    }

}
