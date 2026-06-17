package br.ce.crateus.fpo.vendas.config;

import br.ce.crateus.fpo.vendas.model.Cliente;
import br.ce.crateus.fpo.vendas.model.Produto;
import br.ce.crateus.fpo.vendas.model.enums.Categoria;
import br.ce.crateus.fpo.vendas.repository.ClienteRepository;
import br.ce.crateus.fpo.vendas.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Arrays;

@Configuration
@RequiredArgsConstructor
public class TestConfig implements CommandLineRunner {
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;

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
    }

}