package br.com.avaliacao.api.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.avaliacao.api.dto.ClienteDTO;
import br.com.avaliacao.api.model.Cliente;
import br.com.avaliacao.api.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<ClienteDTO> buscarTodos() {
        return clienteRepository.findAll().stream()
                .map(Cliente::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<ClienteDTO> buscarPorId(Long id) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        return clienteOptional.map(Cliente::toDTO);
    }

    public ClienteDTO cadastrar(ClienteDTO clienteDTO) {
        Cliente cliente = clienteDTO.toEntity();
        clienteRepository.save(cliente);
        return cliente.toDTO();
    }

    public Optional<ClienteDTO> alterar(Long id, ClienteDTO clienteDTO) {
        if (clienteRepository.existsById(id)) {
            Cliente cliente = clienteDTO.toEntity();
            cliente.setId(id);
            clienteRepository.save(cliente);
            return Optional.of(cliente.toDTO());
        }
        return Optional.empty();
    }

    public boolean deletar(Long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            return true;
        }
        return false;
    }

	public List<ClienteDTO> buscarPorNome(String nome) {
		return clienteRepository.findByNomeContaining(nome).stream().map(Cliente::toDTO).collect(Collectors.toList());
	}

	public List<ClienteDTO> buscarPorEmail(String email) {
		return clienteRepository.findByEmail(email).stream().map(Cliente::toDTO).collect(Collectors.toList());
	}

	public List<ClienteDTO> buscarPorTelefone(String telefone) {
		return clienteRepository.findByTelefone(telefone).stream().map(Cliente::toDTO).collect(Collectors.toList());
	}
}