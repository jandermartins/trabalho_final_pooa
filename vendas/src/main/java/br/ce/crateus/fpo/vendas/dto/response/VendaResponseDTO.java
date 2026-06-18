package br.ce.crateus.fpo.vendas.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class VendaResponseDTO {
    private Long id;
    private Long clienteId;
    private String nomeCliente;
    private List<ItemVendaResponseDTO> itens;
    private BigDecimal valorTotal;
}