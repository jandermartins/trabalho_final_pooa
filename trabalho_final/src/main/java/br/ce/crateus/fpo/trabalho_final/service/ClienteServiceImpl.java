package br.ce.crateus.fpo.trabalho_final.service;

import br.ce.crateus.fpo.trabalho_final.dto.ClienteRequestDTO;
import br.ce.crateus.fpo.trabalho_final.dto.ClienteResponseDTO;
import br.ce.crateus.fpo.trabalho_final.exceptions.RecursoNaoEncontradoException;
import br.ce.crateus.fpo.trabalho_final.mapper.ClienteMapper;
import br.ce.crateus.fpo.trabalho_final.model.Cliente;
import br.ce.crateus.fpo.trabalho_final.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements IClienteService {
    private final ClienteRepository repository;
    private final ClienteMapper mapper;

    public ClienteServiceImpl(ClienteRepository repository, ClienteMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public ClienteResponseDTO criar(ClienteRequestDTO dto) {
        if (repository.existsByEmail(dto.getEmail()))
            throw new RuntimeException("E-mail já cadastrado");
        if (repository.existsByCpf(dto.getCpf()))
            throw new RuntimeException("CPF já cadastrado");
        Cliente cliente = mapper.toEntity(dto);
        return mapper.toResponseDTO(repository.save(cliente));
    }

    @Override
    public List<ClienteResponseDTO> listarTodos() {
        return repository.findAll().stream().map(mapper::toResponseDTO).collect(Collectors.toList());
    }

    @Override
    public ClienteResponseDTO buscarPorId(Long id) throws RecursoNaoEncontradoException {
        Cliente cliente = repository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado"));
        return mapper.toResponseDTO(cliente);
    }

    @Override
    @Transactional
    public ClienteResponseDTO atualizar(Long id, ClienteRequestDTO dto) throws RecursoNaoEncontradoException {
        Cliente cliente = repository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado"));
        cliente.setNome(dto.getNome());
        cliente.setEmail(dto.getEmail());
        cliente.setCpf(dto.getCpf());
        cliente.setTelefone(dto.getTelefone());
        return mapper.toResponseDTO(repository.save(cliente));
    }

    @Override
    @Transactional
    public void deletar(Long id) throws RecursoNaoEncontradoException {
        if (!repository.existsById(id)) throw new RecursoNaoEncontradoException("Cliente não encontrado");
        repository.deleteById(id);
    }
}
