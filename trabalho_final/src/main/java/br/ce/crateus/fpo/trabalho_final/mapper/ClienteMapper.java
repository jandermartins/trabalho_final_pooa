package br.ce.crateus.fpo.trabalho_final.mapper;

import br.ce.crateus.fpo.trabalho_final.dto.ClienteRequestDTO;
import br.ce.crateus.fpo.trabalho_final.dto.ClienteResponseDTO;
import br.ce.crateus.fpo.trabalho_final.model.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {
    public Cliente toEntity(ClienteRequestDTO dto) {
        return Cliente.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .cpf(dto.getCpf())
                .telefone(dto.getTelefone())
                .build();
    }

    public ClienteResponseDTO toResponseDTO(Cliente cliente) {
        return ClienteResponseDTO.builder()
                .id(cliente.getId())
                .nome(cliente.getNome())
                .email(cliente.getEmail())
                .cpf(cliente.getCpf())
                .telefone(cliente.getTelefone())
                .dataCadastro(cliente.getDataCadastro())
                .build();
    }
}
