package br.com.avaliacao.api.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.avaliacao.api.dto.PratoDTO;
import br.com.avaliacao.api.model.Prato;
import br.com.avaliacao.api.model.Tipo;
import br.com.avaliacao.api.repository.PratoRepository;

@Service
public class PratoService {

    @Autowired
    private PratoRepository repository;

    public List<PratoDTO> buscarTodos() {
        return repository.findAll().stream()
                .map(p -> new PratoDTO(p.getId(), p.getNome(),
                        p.getDescricao(), p.getPreco(), p.getTipo()))
                .collect(Collectors.toList());
    }

    public Optional<PratoDTO> buscarPorId(Long id) {
        Optional<Prato> produto = repository.findById(id);

        if (produto.isPresent()) {
            return Optional.of(produto.get().toDTO());
        }
        return Optional.empty();
    }

    public List<PratoDTO> buscarPorNome(String nome) {
        return repository.findByNomeContaining(nome).stream()
                .map(p -> new PratoDTO(p.getId(), p.getNome(), p.getDescricao(), p.getPreco(), p.getTipo()))
                .collect(Collectors.toList());
    }

    public List<PratoDTO> buscarPorPreco(Double menor, Double maior) {
        return repository.findByPrecoBetween(menor, maior).stream()
                .map(p -> new PratoDTO(p.getId(), p.getNome(), p.getDescricao(), p.getPreco(), p.getTipo()))
                .collect(Collectors.toList());
    }

    public List<PratoDTO> buscarPorTipo(Tipo tipo) {
        return repository.findByTipo(tipo).stream()
                .map(p -> new PratoDTO(p.getId(), p.getNome(), p.getDescricao(), p.getPreco(), p.getTipo()))
                .collect(Collectors.toList());
    }

    public PratoDTO cadastrar(PratoDTO produto) {
        Prato produtoEntity = produto.toEntity();
        repository.save(produtoEntity);
        return produtoEntity.toDTO();
    }

    public Optional<PratoDTO> alterar(Long id, PratoDTO produto) {
        Prato produtoEntity = produto.toEntity();

        if (repository.existsById(id)) {
            produtoEntity.setId(id);
            repository.save(produtoEntity);
            return Optional.of(produtoEntity.toDTO());
        }
        return Optional.empty();
    }

    public Boolean deletar(Long id) {

        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }

        return false;
    }
}
