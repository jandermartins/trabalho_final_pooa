package br.ce.crateus.fpo.vendas.mapper;

import br.ce.crateus.fpo.vendas.dto.response.EstoqueResponseDTO;
import br.ce.crateus.fpo.vendas.model.Estoque;
import org.springframework.stereotype.Component;

@Component
public class EstoqueMapper {

    public EstoqueResponseDTO toResponseDTO(Estoque estoque) {
        if (estoque == null) return null;
        return EstoqueResponseDTO.builder()
                .id(estoque.getId())
                .produtoId(estoque.getProduto().getId())
                .nomeProduto(estoque.getProduto().getNome())
                .quantidade(estoque.getQuantidade())
                .build();
    }
}