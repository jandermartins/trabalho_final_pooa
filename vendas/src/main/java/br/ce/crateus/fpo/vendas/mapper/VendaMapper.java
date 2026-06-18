package br.ce.crateus.fpo.vendas.mapper;

import br.ce.crateus.fpo.vendas.dto.response.ItemVendaResponseDTO;
import br.ce.crateus.fpo.vendas.dto.response.VendaResponseDTO;
import br.ce.crateus.fpo.vendas.model.Venda;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

@Component
public class VendaMapper {

    public VendaResponseDTO toResponseDTO(Venda venda) {
        if (venda == null) return null;
        return VendaResponseDTO.builder()
                .id(venda.getId())
                .clienteId(venda.getCliente().getId())
                .nomeCliente(venda.getCliente().getNome())
                .valorTotal(venda.getValorTotal())
                .itens(venda.getItens().stream().map(i -> ItemVendaResponseDTO.builder()
                        .produtoId(i.getProduto().getId())
                        .nomeProduto(i.getProduto().getNome())
                        .quantidade(i.getQuantidade())
                        .precoUnitario(i.getProduto().getPreco())
                        .build()).collect(Collectors.toList()))
                .build();
    }
}