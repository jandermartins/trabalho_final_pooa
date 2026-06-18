package br.ce.crateus.fpo.vendas.model;

import br.ce.crateus.fpo.vendas.model.enums.Categoria;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private String descricao;

    @Column(nullable = false)
    @Positive(message = "O valor deve ser maior que zero")
    private Double preco;

    @Column(nullable = false)
    private Categoria categoria;

    @OneToOne(mappedBy = "produto", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Estoque estoque;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ItemVenda> itensVenda;
}
