package br.ce.crateus.fpo.vendas.model;

import br.ce.crateus.fpo.vendas.model.enums.Categoria;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.*;

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
}
