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
public class DataInvalidaException extends RuntimeException {

    public DataInvalidaException(String string) {
        super(string);
    }
}
