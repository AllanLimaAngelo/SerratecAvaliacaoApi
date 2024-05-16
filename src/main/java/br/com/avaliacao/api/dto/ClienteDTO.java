package br.com.avaliacao.api.dto;

import br.com.avaliacao.api.model.Cliente;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ClienteDTO(
        Long id,
        @NotBlank String nome,
        @NotBlank @Email String email,
        @NotBlank @Pattern(regexp="\\d{10,11}") String telefone) {

    public Cliente toEntity() {
        return new Cliente(this.id, this.nome, this.email, this.telefone);
    }
}