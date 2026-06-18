package br.ce.crateus.fpo.vendas.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ErrorResponse {
    private int status;
    private String erro;
    private String mensagem;
    private LocalDateTime timestamp;
}
