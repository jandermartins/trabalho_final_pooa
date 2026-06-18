package br.ce.crateus.fpo.vendas.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ClienteRequestDTO {

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "E-mail é obrigatório")
    @Email(message = "E-mail inválido")
    private String email;

    @NotBlank(message = "CPF é obrigatório")
    @Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}", message = "CPF inválido")
    private String cpf;

    // Aceita apenas vazio ou 11 dígitos
    @Pattern(regexp = "^$|^\\d{11}$", message = "O telefone deve conter exatamente 11 números digitados (DDD + Número)")
    private String telefone;
}