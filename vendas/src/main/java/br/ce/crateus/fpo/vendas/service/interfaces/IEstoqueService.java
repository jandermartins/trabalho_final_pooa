package br.ce.crateus.fpo.vendas.service.interfaces;

import br.ce.crateus.fpo.vendas.dto.response.EstoqueResponseDTO;
import java.util.List;

public interface IEstoqueService {
    EstoqueResponseDTO consultarEstoque(Long produtoId);
    EstoqueResponseDTO atualizarEstoque(Long produtoId, Integer quantidade);
    void decrementarEstoque(Long produtoId, Integer quantidade);
    List<EstoqueResponseDTO> listarTodos();
}