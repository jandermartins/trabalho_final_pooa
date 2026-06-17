package br.ce.crateus.fpo.vendas.dto.request;

import br.ce.crateus.fpo.vendas.model.enums.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ProdutoRequestDTO {
    @NotBlank(message = "O nome do produto é obrigatório")
    private String nome;

    private String descricao;

    @NotNull(message = "O preço é obrigatório")
    @Positive(message = "O valor deve ser maior que zero")
    private Double preco;

    @NotNull(message = "A categoria é obrigatória")
    private Categoria categoria;
}
