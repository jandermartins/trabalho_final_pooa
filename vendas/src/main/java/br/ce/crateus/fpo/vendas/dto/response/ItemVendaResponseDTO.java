package br.ce.crateus.fpo.vendas.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemVendaResponseDTO {
    private Long produtoId;
    private String nomeProduto;
    private Integer quantidade;
    private Double precoUnitario;
}