package br.ce.crateus.fpo.vendas.service.interfaces;

import br.ce.crateus.fpo.vendas.model.ItemVenda;
import java.math.BigDecimal;
import java.util.List;

public interface ICalculadoraVenda {
    BigDecimal calcularTotal(List<ItemVenda> itens);
}