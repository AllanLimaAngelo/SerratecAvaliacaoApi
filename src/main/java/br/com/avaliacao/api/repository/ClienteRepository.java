package br.com.avaliacao.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.avaliacao.api.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	List<Cliente> findByNomeContaining(String nome);

	List<Cliente> findByEmail(String email);

	List<Cliente> findByTelefone(String telefone);
}