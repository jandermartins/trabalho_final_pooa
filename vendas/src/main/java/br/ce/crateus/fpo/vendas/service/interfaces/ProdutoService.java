package br.ce.crateus.fpo.vendas.service.interfaces;

import br.ce.crateus.fpo.vendas.dto.request.ProdutoRequestDTO;
import br.ce.crateus.fpo.vendas.dto.response.ProdutoResponseDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface ProdutoService {
    ProdutoResponseDTO criar(@Valid ProdutoRequestDTO dto);
    List<ProdutoResponseDTO> listarTodos();
    ProdutoResponseDTO buscarPorId(Long id);
    ProdutoResponseDTO atualizar(Long id, @Valid ProdutoRequestDTO dto);
    void deletar(Long id);
}
