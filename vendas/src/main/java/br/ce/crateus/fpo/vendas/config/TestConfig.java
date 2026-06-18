package br.ce.crateus.fpo.vendas.config;

import br.ce.crateus.fpo.vendas.model.*;
import br.ce.crateus.fpo.vendas.model.enums.Categoria;
import br.ce.crateus.fpo.vendas.repository.ClienteRepository;
import br.ce.crateus.fpo.vendas.repository.EstoqueRepository;
import br.ce.crateus.fpo.vendas.repository.ProdutoRepository;
import br.ce.crateus.fpo.vendas.repository.VendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class TestConfig implements CommandLineRunner {
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final EstoqueRepository estoqueRepository;
    private final VendaRepository vendaRepository;

    @Override
    public void run(String... args) throws Exception {

        // MOCKANDO CLIENTES
        Cliente c1 = Cliente.builder()
                .nome("Davi Marques")
                .email("davi.bezerra@alu.fpo.edu.br")
                .cpf("830.031.130-03")
                .telefone("88999886677")
                .dataCadastro(LocalDate.of(2026, 6, 16))
                .build();

        Cliente c2 = Cliente.builder()
                .nome("Maria Eduarda")
                .email("maria.eduarda@alu.fpo.edu.br")
                .cpf("222.881.820-83")
                .telefone("88999223344")
                .dataCadastro(LocalDate.of(2026, 6, 15))
                .build();

        Cliente c3 = Cliente.builder()
                .nome("Kauan Preto")
                .email("kauan.preto@alu.fpo.edu.br")
                .cpf("860.934.770-29")
                .telefone("88988223311")
                .dataCadastro(LocalDate.of(2026, 6, 14))
                .build();

        // MOCKANDO PRODUTOS
        Produto p1 = Produto.builder()
                .nome("Macbook Air M5")
                .descricao("Meia-noite")
                .preco(9499.00)
                .categoria(Categoria.ELETRONICO)
                .build();

        Produto p2 = Produto.builder()
                .nome("iPhone 17 Pro")
                .descricao("Titânio Natural")
                .preco(8999.00)
                .categoria(Categoria.ELETRONICO)
                .build();

        Produto p3 = Produto.builder()
                .nome("Tênis Adidas Dropset Base")
                .descricao("Cloud White")
                .preco(499.99)
                .categoria(Categoria.VESTUARIO)
                .build();

        // SALVANDO
        clienteRepository.saveAll(Arrays.asList(c1, c2, c3));
        produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

        // MOCKANDO ESTOQUES
        Estoque e1 = estoqueRepository.findByProdutoId(p1.getId())
                .orElse(Estoque.builder().produto(p1).build());
        e1.setQuantidade(10); // 10 macbooks em estoque

        Estoque e2 = estoqueRepository.findByProdutoId(p2.getId())
                .orElse(Estoque.builder().produto(p2).build());
        e2.setQuantidade(25); // 25 iPhones em estoque

        Estoque e3 = estoqueRepository.findByProdutoId(p3.getId())
                .orElse(Estoque.builder().produto(p3).build());
        e3.setQuantidade(50); // 50 tênis em estoque

        estoqueRepository.saveAll(Arrays.asList(e1, e2, e3));

        // MOCKANDO VENDAS

        // Venda 1: Davi compra 1 Macbook
        Venda v1 = Venda.builder()
                .cliente(c1)
                .valorTotal(BigDecimal.valueOf(9499.00))
                .build();

        ItemVenda iv1 = ItemVenda.builder()
                .venda(v1)
                .produto(p1)
                .quantidade(1)
                .build();

        v1.setItens(List.of(iv1));

        // Venda 2: Maria compra 1 iPhone e 2 Tênis Adidas
        double valorVenda2 = (8999.00 * 1) + (499.99 * 2);
        Venda v2 = Venda.builder()
                .cliente(c2)
                .valorTotal(BigDecimal.valueOf(valorVenda2))
                .build();

        ItemVenda iv2 = ItemVenda.builder()
                .venda(v2)
                .produto(p2)
                .quantidade(1)
                .build();

        ItemVenda iv3 = ItemVenda.builder()
                .venda(v2)
                .produto(p3)
                .quantidade(2)
                .build();

        v2.setItens(Arrays.asList(iv2, iv3));

        // SALVANDO VENDAS
        vendaRepository.saveAll(Arrays.asList(v1, v2));

        // Ajusta o estoque após as vendas mockadas acima
        e1.setQuantidade(e1.getQuantidade() - 1);
        e2.setQuantidade(e2.getQuantidade() - 1);
        e3.setQuantidade(e3.getQuantidade() - 2);
        estoqueRepository.saveAll(Arrays.asList(e1, e2, e3));
    }

}