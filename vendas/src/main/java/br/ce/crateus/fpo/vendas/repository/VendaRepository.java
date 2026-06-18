package br.ce.crateus.fpo.vendas.repository;

import br.ce.crateus.fpo.vendas.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VendaRepository extends JpaRepository<Venda, Long> {
    List<Venda> findByClienteId(Long clienteId);
}