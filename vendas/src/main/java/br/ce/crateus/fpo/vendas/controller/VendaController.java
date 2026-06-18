package br.ce.crateus.fpo.vendas.controller;

import br.ce.crateus.fpo.vendas.dto.request.VendaRequestDTO;
import br.ce.crateus.fpo.vendas.dto.response.VendaResponseDTO;
import br.ce.crateus.fpo.vendas.service.interfaces.IVendaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/vendas")
@RequiredArgsConstructor
@Tag(name = "Vendas", description = "Endpoints para registro e consulta de vendas")
public class VendaController {

    private final IVendaService service;

    @Operation(summary = "Registrar nova venda")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendaResponseDTO criar(@Valid @RequestBody VendaRequestDTO dto) {
        return service.registrarVenda(dto);
    }

    @Operation(summary = "Listar todas as vendas")
    @GetMapping
    public List<VendaResponseDTO> listar() {
        return service.listarTodas();
    }

    @Operation(summary = "Buscar venda por ID")
    @GetMapping("/{id}")
    public VendaResponseDTO buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @Operation(summary = "Listar vendas de um cliente")
    @GetMapping("/cliente/{clienteId}")
    public List<VendaResponseDTO> listarPorCliente(@PathVariable Long clienteId) {
        return service.listarPorCliente(clienteId);
    }
}