/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 *
 * @author blest
 */
public class ConversaoException extends RuntimeException {

    public ConversaoException(String classe) {
        super("Erro a converter a classe: " + classe);
    }
}
