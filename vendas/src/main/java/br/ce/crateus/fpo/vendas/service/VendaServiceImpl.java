package br.ce.crateus.fpo.vendas.service;

import br.ce.crateus.fpo.vendas.dto.request.VendaRequestDTO;
import br.ce.crateus.fpo.vendas.dto.response.VendaResponseDTO;
import br.ce.crateus.fpo.vendas.exception.RecursoNaoEncontradoException;
import br.ce.crateus.fpo.vendas.mapper.VendaMapper;
import br.ce.crateus.fpo.vendas.model.*;
import br.ce.crateus.fpo.vendas.repository.*;
import br.ce.crateus.fpo.vendas.service.interfaces.CalculadoraVenda;
import br.ce.crateus.fpo.vendas.service.interfaces.IEstoqueService;
import br.ce.crateus.fpo.vendas.service.interfaces.IVendaService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VendaServiceImpl implements IVendaService {

    private final VendaRepository repository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final IEstoqueService estoqueService;
    private final CalculadoraVenda calculadoraVenda;
    private final VendaMapper mapper;

    @Override
    @Transactional
    public VendaResponseDTO registrarVenda(VendaRequestDTO dto) {
        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado"));

        Venda venda = Venda.builder()
                .cliente(cliente)
                .valorTotal(BigDecimal.ZERO)
                .build();

        List<ItemVenda> itens = new ArrayList<>();

        for (var itemDto : dto.getItens()) {
            Produto produto = produtoRepository.findById(itemDto.getProdutoId())
                    .orElseThrow(() -> new RecursoNaoEncontradoException("Produto não encontrado"));

            estoqueService.decrementarEstoque(produto.getId(), itemDto.getQuantidade());

            ItemVenda item = ItemVenda.builder()
                    .venda(venda)
                    .produto(produto)
                    .quantidade(itemDto.getQuantidade())
                    .build();

            itens.add(item);
        }

        venda.setItens(itens);
        venda.setValorTotal(calculadoraVenda.calcularTotal(itens));

        return mapper.toResponseDTO(repository.save(venda));
    }

    @Override
    public List<VendaResponseDTO> listarTodas() {
        return repository.findAll().stream().map(mapper::toResponseDTO).collect(Collectors.toList());
    }

    @Override
    public VendaResponseDTO buscarPorId(Long id) {
        Venda venda = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Venda não encontrada"));
        return mapper.toResponseDTO(venda);
    }

    @Override
    public List<VendaResponseDTO> listarPorCliente(Long clienteId) {
        return repository.findByClienteId(clienteId).stream().map(mapper::toResponseDTO).collect(Collectors.toList());
    }
}