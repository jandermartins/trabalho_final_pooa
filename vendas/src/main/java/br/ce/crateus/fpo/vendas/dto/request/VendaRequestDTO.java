package br.ce.crateus.fpo.vendas.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.util.List;

@Data
public class VendaRequestDTO {
    @NotNull(message = "O ID do cliente é obrigatório")
    private Long clienteId;

    @NotNull(message = "A venda deve conter itens")
    @Size(min = 1, message = "A venda precisa ter pelo menos 1 item")
    @Valid
    private List<ItemVendaRequestDTO> itens;
}