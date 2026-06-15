package br.ce.crateus.fpo.trabalho_final.exceptions;

import org.springframework.data.crossstore.ChangeSetPersister;

public class RecursoNaoEncontradoException extends ChangeSetPersister.NotFoundException {
    public RecursoNaoEncontradoException(String clienteNãoEncontrado) {
    }
}
