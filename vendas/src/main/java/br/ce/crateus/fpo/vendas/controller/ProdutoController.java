package br.ce.crateus.fpo.vendas.controller;

import br.ce.crateus.fpo.vendas.dto.request.ProdutoRequestDTO;
import br.ce.crateus.fpo.vendas.dto.response.ProdutoResponseDTO;
import br.ce.crateus.fpo.vendas.service.interfaces.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@AllArgsConstructor
@Tag(name = "Produtos", description = "Endpoints para gerenciamento de produtos")
public class ProdutoController {

    private final ProdutoService service;

    @Operation(summary = "Cadastrar novo produto")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoResponseDTO criar(@Valid @RequestBody ProdutoRequestDTO dto) {
        return service.criar(dto);
    }

    @Operation(summary = "Listar todos os produtos")
    @GetMapping
    public List<ProdutoResponseDTO> listar() {
        return service.listarTodos();
    }

    @Operation(summary = "Buscar produto por ID")
    @GetMapping("/{id}")
    public ProdutoResponseDTO buscar(@Parameter(description = "ID do produto", example = "1") @PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @Operation(summary = "Atualizar dados do produto")
    @PutMapping("/{id}")
    public ProdutoResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody ProdutoRequestDTO dto) {
        return service.atualizar(id, dto);
    }

    @Operation(summary = "Remover produto")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }

}
