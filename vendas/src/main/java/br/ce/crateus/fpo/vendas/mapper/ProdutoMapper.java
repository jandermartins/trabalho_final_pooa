package br.ce.crateus.fpo.vendas.mapper;

import br.ce.crateus.fpo.vendas.dto.request.ProdutoRequestDTO;
import br.ce.crateus.fpo.vendas.dto.response.ProdutoResponseDTO;
import br.ce.crateus.fpo.vendas.model.Produto;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {
    public Produto toEntity(ProdutoRequestDTO dto) {
        // EVITA NullPointerException
        if (dto == null) {
            return null;
        }

        return Produto.builder()
                .nome(dto.getNome())
                .descricao(dto.getDescricao())
                .preco(dto.getPreco())
                .categoria(dto.getCategoria())
                .build();
    }

    public ProdutoResponseDTO toResponseDTO(Produto produto) {
        // EVITA NullPointerException
        if (produto == null) {
            return null;
        }

        return ProdutoResponseDTO.builder()
                .id(produto.getId())
                .nome(produto.getNome())
                .descricao(produto.getDescricao())
                .preco(produto.getPreco())
                .categoria(produto.getCategoria())
                .build();
    }
}
