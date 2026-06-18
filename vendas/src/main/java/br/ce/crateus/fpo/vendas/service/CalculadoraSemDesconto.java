package br.ce.crateus.fpo.vendas.service;

import br.ce.crateus.fpo.vendas.model.ItemVenda;
import br.ce.crateus.fpo.vendas.service.interfaces.CalculadoraVenda;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.List;

@Component
public class CalculadoraSemDesconto implements CalculadoraVenda {
    @Override
    public BigDecimal calcularTotal(List<ItemVenda> itens) {
        return itens.stream()
                .map(i -> BigDecimal.valueOf(i.getProduto().getPreco()) 
                        .multiply(BigDecimal.valueOf(i.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}