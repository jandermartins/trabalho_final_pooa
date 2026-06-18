package br.ce.crateus.fpo.vendas.service;

import br.ce.crateus.fpo.vendas.dto.response.EstoqueResponseDTO;
import br.ce.crateus.fpo.vendas.exception.EstoqueInsuficienteException;
import br.ce.crateus.fpo.vendas.exception.RecursoNaoEncontradoException;
import br.ce.crateus.fpo.vendas.mapper.EstoqueMapper;
import br.ce.crateus.fpo.vendas.model.Estoque;
import br.ce.crateus.fpo.vendas.repository.EstoqueRepository;
import br.ce.crateus.fpo.vendas.service.interfaces.IEstoqueService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EstoqueServiceImpl implements IEstoqueService {

    private final EstoqueRepository repository;
    private final EstoqueMapper mapper;

    @Override
    public EstoqueResponseDTO consultarEstoque(Long produtoId) {
        Estoque estoque = repository.findByProdutoId(produtoId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Estoque não encontrado para o produto ID: " + produtoId));
        return mapper.toResponseDTO(estoque);
    }

    @Override
    @Transactional
    public EstoqueResponseDTO atualizarEstoque(Long produtoId, Integer quantidade) {
        Estoque estoque = repository.findByProdutoId(produtoId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Estoque não encontrado"));
        estoque.setQuantidade(quantidade);
        return mapper.toResponseDTO(repository.save(estoque));
    }

    @Override
    @Transactional
    public void decrementarEstoque(Long produtoId, Integer quantidade) {
        Estoque estoque = repository.findByProdutoId(produtoId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Estoque não encontrado"));

        if (estoque.getQuantidade() < quantidade) {
            throw new EstoqueInsuficienteException(
                    "Produto '" + estoque.getProduto().getNome() + "' possui apenas " + estoque.getQuantidade() + " unidade(s) em estoque."
            );
        }
        estoque.setQuantidade(estoque.getQuantidade() - quantidade);
        repository.save(estoque);
    }

    @Override
    public List<EstoqueResponseDTO> listarTodos() {
        return repository.findAll().stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}