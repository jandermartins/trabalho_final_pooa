package br.ce.crateus.fpo.vendas.service;

import br.ce.crateus.fpo.vendas.dto.request.ClienteRequestDTO;
import br.ce.crateus.fpo.vendas.dto.response.ClienteResponseDTO;
import jakarta.validation.Valid;
import java.util.List;

public interface ClienteService {
    ClienteResponseDTO criar(@Valid ClienteRequestDTO dto);
    List<ClienteResponseDTO> listarTodos();
    ClienteResponseDTO buscarPorId(Long id);
    ClienteResponseDTO atualizar(Long id, @Valid ClienteRequestDTO dto);
    void deletar(Long id);
}
