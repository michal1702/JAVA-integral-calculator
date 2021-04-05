/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.exceptions;

/**
 *
 * @author Michał Opiełka
 * @version 1.0
 */
public class RangeException extends Exception{
    /**
     * Non-parameter constructor
     */
    public RangeException() {

    }

    /**
     * Exception class constructor
     *
     * @param message message about wrong range of integration
     */
    public RangeException(String message) {
        super(message);
    }
}
