package br.ce.crateus.fpo.vendas.controller;

import br.ce.crateus.fpo.vendas.dto.request.EstoqueRequestDTO;
import br.ce.crateus.fpo.vendas.dto.response.EstoqueResponseDTO;
import br.ce.crateus.fpo.vendas.service.interfaces.IEstoqueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/estoque")
@RequiredArgsConstructor
@Tag(name = "Estoque", description = "Endpoints para controle de estoque")
public class EstoqueController {

    private final IEstoqueService service;

    @Operation(summary = "Consultar estoque de um produto")
    @GetMapping("/produto/{produtoId}")
    public EstoqueResponseDTO consultar(@PathVariable Long produtoId) {
        return service.consultarEstoque(produtoId);
    }

    @Operation(summary = "Atualizar quantidade em estoque")
    @PutMapping("/produto/{produtoId}")
    public EstoqueResponseDTO atualizar(@PathVariable Long produtoId, @Valid @RequestBody EstoqueRequestDTO dto) {
        return service.atualizarEstoque(produtoId, dto.getQuantidade());
    }

    @Operation(summary = "Listar todos os estoques")
    @GetMapping
    public List<EstoqueResponseDTO> listar() {
        return service.listarTodos();
    }
}