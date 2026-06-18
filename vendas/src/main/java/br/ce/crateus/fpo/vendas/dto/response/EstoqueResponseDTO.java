package br.ce.crateus.fpo.vendas.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EstoqueResponseDTO {
    private Long id;
    private Long produtoId;
    private String nomeProduto;
    private Integer quantidade;
}