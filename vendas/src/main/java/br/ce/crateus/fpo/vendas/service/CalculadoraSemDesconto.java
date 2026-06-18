package br.ce.crateus.fpo.vendas.service;

import br.ce.crateus.fpo.vendas.model.ItemVenda;
import br.ce.crateus.fpo.vendas.service.interfaces.ICalculadoraVenda;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.List;

@Component
public class CalculadoraSemDesconto implements ICalculadoraVenda {
    @Override
    public BigDecimal calcularTotal(List<ItemVenda> itens) {
        return itens.stream()
                .map(i -> BigDecimal.valueOf(i.getProduto().getPreco()) 
                        .multiply(BigDecimal.valueOf(i.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}