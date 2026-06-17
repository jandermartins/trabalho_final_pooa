package br.ce.crateus.fpo.vendas.controller;

import br.ce.crateus.fpo.vendas.dto.request.ClienteRequestDTO;
import br.ce.crateus.fpo.vendas.dto.response.ClienteResponseDTO;
import br.ce.crateus.fpo.vendas.service.interfaces.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/clientes")
@AllArgsConstructor
@Tag(name = "Clientes", description = "Endpoints para gerenciamento de clientes")
public class ClienteController {

    private final ClienteService service;

    @Operation(summary = "Cadastrar novo cliente")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteResponseDTO criar(@Valid @RequestBody ClienteRequestDTO dto) {
        return service.criar(dto);
    }

    @Operation(summary = "Listar todos os clientes")
    @GetMapping
    public List<ClienteResponseDTO> listar() {
        return service.listarTodos();
    }

    @Operation(summary = "Buscar cliente por ID")
    @GetMapping("/{id}")
    public ClienteResponseDTO buscar(@Parameter(description = "ID do cliente", example = "1") @PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @Operation(summary = "Atualizar dados do cliente")
    @PutMapping("/{id}")
    public ClienteResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody ClienteRequestDTO dto) {
        return service.atualizar(id, dto);
    }

    @Operation(summary = "Remover cliente")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
