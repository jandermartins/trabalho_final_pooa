package br.ce.crateus.fpo.trabalho_final.controllers;

import br.ce.crateus.fpo.trabalho_final.dto.ClienteRequestDTO;
import br.ce.crateus.fpo.trabalho_final.dto.ClienteResponseDTO;
import br.ce.crateus.fpo.trabalho_final.exceptions.RecursoNaoEncontradoException;
import br.ce.crateus.fpo.trabalho_final.service.IClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
@Tag(name = "Clientes", description = "Endpoints para gerenciamento de clientes")
public class ClienteController {

    private final IClienteService service;

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
    public ClienteResponseDTO buscar(@Parameter(description = "ID do cliente", example = "1") @PathVariable Long id) throws RecursoNaoEncontradoException {
        return service.buscarPorId(id);
    }

    @Operation(summary = "Atualizar dados do cliente")
    @PutMapping("/{id}")
    public ClienteResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody ClienteRequestDTO dto) throws RecursoNaoEncontradoException {
        return service.atualizar(id, dto);
    }

    @Operation(summary = "Remover cliente")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) throws RecursoNaoEncontradoException {
        service.deletar(id);
    }
}
