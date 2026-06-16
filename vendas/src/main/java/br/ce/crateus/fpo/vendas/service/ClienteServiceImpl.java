package br.ce.crateus.fpo.vendas.service;

import br.ce.crateus.fpo.vendas.ClienteRepository;
import br.ce.crateus.fpo.vendas.dto.ClienteRequestDTO;
import br.ce.crateus.fpo.vendas.dto.ClienteResponseDTO;
import br.ce.crateus.fpo.vendas.exception.RecursoNaoEncontradoException;
import br.ce.crateus.fpo.vendas.mapper.ClienteMapper;
import br.ce.crateus.fpo.vendas.model.Cliente;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClienteServiceImpl implements  ClienteService {
    @Override
    public List<ClienteResponseDTO> listarTodos() {
        return repository.findAll().stream().map(mapper::toResponseDTO).collect(Collectors.toList());
    }

    @Override
    public ClienteResponseDTO buscarPorId(Long id) {
        Cliente cliente = repository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado"));
        return mapper.toResponseDTO(cliente);
    }

    @Override
    public ClienteResponseDTO atualizar(Long id, ClienteRequestDTO dto) {
        Cliente cliente = repository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado"));
        cliente.setNome(dto.getNome());
        cliente.setEmail(dto.getEmail());
        cliente.setCpf(dto.getCpf());
        cliente.setTelefone(dto.getTelefone());
        return mapper.toResponseDTO(repository.save(cliente));
    }

    @Override
    public void deletar(Long id) throws RecursoNaoEncontradoException{
        if (!repository.existsById(id)) throw new RecursoNaoEncontradoException("Cliente não encontrado");
        repository.deleteById(id);
    }

    private final ClienteRepository repository;
    private final ClienteMapper mapper;

    @Transactional
    public ClienteResponseDTO criar(ClienteRequestDTO dto) {
        if (repository.existsByEmail(dto.getEmail()))
            throw new RuntimeException("E-mail já cadastrado");
        if (repository.existsByCpf(dto.getCpf()))
            throw new RuntimeException("CPF já cadastrado");
        Cliente cliente = mapper.toEntity(dto);
        return mapper.toResponseDTO(repository.save(cliente));
    }
}
