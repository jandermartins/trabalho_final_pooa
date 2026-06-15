package br.ce.crateus.fpo.trabalho_final.service;

import br.ce.crateus.fpo.trabalho_final.dto.ClienteRequestDTO;
import br.ce.crateus.fpo.trabalho_final.dto.ClienteResponseDTO;
import br.ce.crateus.fpo.trabalho_final.exceptions.RecursoNaoEncontradoException;

import java.util.List;

public interface IClienteService {
    ClienteResponseDTO criar(ClienteRequestDTO dto);
    List<ClienteResponseDTO> listarTodos();
    ClienteResponseDTO buscarPorId(Long id) throws RecursoNaoEncontradoException;
    ClienteResponseDTO atualizar(Long id, ClienteRequestDTO dto) throws RecursoNaoEncontradoException;
    void deletar(Long id) throws RecursoNaoEncontradoException;
}
