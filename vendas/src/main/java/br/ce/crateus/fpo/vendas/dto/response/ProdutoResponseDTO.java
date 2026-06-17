package br.ce.crateus.fpo.vendas.dto.response;

import br.ce.crateus.fpo.vendas.model.enums.Categoria;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProdutoResponseDTO {
    private Long id;
    private String nome;
    private String descricao;
    private Double preco;
    private Categoria categoria;
}
