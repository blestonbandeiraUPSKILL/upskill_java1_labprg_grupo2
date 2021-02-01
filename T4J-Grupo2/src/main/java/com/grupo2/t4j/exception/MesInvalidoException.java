package com.grupo2.t4j.exception;

public class MesInvalidoException extends IllegalArgumentException{
    public MesInvalidoException() {
        super("O mês introduzido é inválido!");
    }

    public MesInvalidoException(String mensagem) {
        super(mensagem);
    }
}
