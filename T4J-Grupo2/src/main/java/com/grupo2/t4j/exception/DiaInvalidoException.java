package com.grupo2.t4j.exception;

public class DiaInvalidoException extends IllegalArgumentException{

    public DiaInvalidoException() {
        super("O dia indicado não é válido!");
    }

    public DiaInvalidoException(String mensagem) {
        super(mensagem);
    }

}
