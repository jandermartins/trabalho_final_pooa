package br.ce.crateus.fpo.vendas.service;

import br.ce.crateus.fpo.vendas.dto.request.ProdutoRequestDTO;
import br.ce.crateus.fpo.vendas.dto.response.ProdutoResponseDTO;
import br.ce.crateus.fpo.vendas.exception.RecursoNaoEncontradoException;
import br.ce.crateus.fpo.vendas.mapper.ProdutoMapper;
import br.ce.crateus.fpo.vendas.model.Estoque;
import br.ce.crateus.fpo.vendas.model.Produto;
import br.ce.crateus.fpo.vendas.repository.EstoqueRepository;
import br.ce.crateus.fpo.vendas.repository.ProdutoRepository;
import br.ce.crateus.fpo.vendas.service.interfaces.ProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository repository;
    private final ProdutoMapper mapper;
    private final EstoqueRepository estoqueRepository;

    @Override
    public List<ProdutoResponseDTO> listarTodos() {
        return repository.findAll().stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProdutoResponseDTO buscarPorId(Long id) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto não encontrado"));
        return mapper.toResponseDTO(produto);
    }

    @Override
    @Transactional
    public ProdutoResponseDTO atualizar(Long id, ProdutoRequestDTO dto) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto não encontrado"));

        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setPreco(dto.getPreco());
        produto.setCategoria(dto.getCategoria());

        return mapper.toResponseDTO(repository.save(produto));
    }

    @Override
    @Transactional
    public void deletar(Long id) throws RecursoNaoEncontradoException {
        if (!repository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Produto não encontrado");
        }
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public ProdutoResponseDTO criar(ProdutoRequestDTO dto) {
        if (repository.existsByNome(dto.getNome())) {
            throw new RuntimeException("Produto com este nome já cadastrado");
        }
        Produto produto = mapper.toEntity(dto);
        Produto produtoSalvo = repository.save(produto);

        estoqueRepository.save(Estoque.builder().produto(produtoSalvo).quantidade(0).build());

        return mapper.toResponseDTO(produtoSalvo);
    }
}
