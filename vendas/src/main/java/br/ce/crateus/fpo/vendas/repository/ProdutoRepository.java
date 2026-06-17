package br.ce.crateus.fpo.vendas.repository;

import br.ce.crateus.fpo.vendas.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    boolean existsByNome(String nome);
}
