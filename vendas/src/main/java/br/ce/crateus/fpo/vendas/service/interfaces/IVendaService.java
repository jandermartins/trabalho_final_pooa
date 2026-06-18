package br.ce.crateus.fpo.vendas.service.interfaces;

import br.ce.crateus.fpo.vendas.dto.request.VendaRequestDTO;
import br.ce.crateus.fpo.vendas.dto.response.VendaResponseDTO;
import java.util.List;

public interface IVendaService {
    VendaResponseDTO registrarVenda(VendaRequestDTO dto);
    List<VendaResponseDTO> listarTodas();
    VendaResponseDTO buscarPorId(Long id);
    List<VendaResponseDTO> listarPorCliente(Long clienteId);
}