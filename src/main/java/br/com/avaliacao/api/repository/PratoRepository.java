package br.com.avaliacao.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.avaliacao.api.model.Prato;
import br.com.avaliacao.api.model.Tipo;

public interface PratoRepository extends JpaRepository<Prato, Long> {
	List<Prato> findByNomeContaining(String nome);

	List<Prato> findByTipo(Tipo tipo);

	List<Prato> findByPrecoBetween(Double menor, Double maior);
}
