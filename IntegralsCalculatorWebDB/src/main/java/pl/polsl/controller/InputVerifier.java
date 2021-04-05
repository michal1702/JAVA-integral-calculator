/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.controller;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Michał Opiełka
 * @version 1.0
 */
public class InputVerifier {

    /**
     * Beginning of an integration
     */
    private final String begin;
    /**
     * End of an integration
     */
    private final String end;
    /**
     * Integrated function
     */
    private final String function;
    /**
     * Method of an integration
     */
    private final String method;
    /**
     * List of available characters in a function
     */
    private final List<String> availableCharacters;

    /**
     * Parametrized constructor
     *
     * @param begin Beginning of an integration
     * @param end End of an integration
     * @param function Integrated function
     * @param method Method of an integration
     */
    public InputVerifier(String begin, String end, String function, String method) {
        this.begin = begin;
        this.end = end;
        this.function = function;
        this.method = method;
        String[] characters = new String[]{"+", "-", "*", "/", ".", "x"};
        availableCharacters = Arrays.asList(characters);
    }

    /**
     * non-parametized constructor
     */
    public InputVerifier() {
        this.begin = "";
        this.end = "";
        this.function = "";
        this.method = "";
        availableCharacters = null;
    }

    /**
     * Checks if both range parameters are ints
     *
     * @return if range is int
     */
    private boolean ifRangeIsInt() {
        if (begin == null || end == null) {
            return false;
        }
        try {
            Integer.parseInt(begin);
            Integer.parseInt(end);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /**
     * Checks if function characters are correct
     *
     * @return if function is correct
     */
    private boolean ifFunctionIsCorrect() {
        if (!function.equals("")) {
            for (char character : function.toCharArray()) {
                if (!(character >= '0' && character <= '9') && !availableCharacters.contains(String.valueOf(character))) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if an integration method is not null
     *
     * @return if a method is not null
     */
    private boolean ifMethodIsNull() {
        return method == null;
    }

    /**
     * Checks if every entered value is correct
     *
     * @return if values are correct
     */
    public boolean isEverythingCorrect() {
        return !ifMethodIsNull() && ifRangeIsInt() && ifFunctionIsCorrect();
    }

    /**
     * Checks if entered number of rows is correct
     *
     * @param rowCount number of rows to display
     * @return if correct
     */
    public boolean ifRowCountIsCorrect(String rowCount) {
        try {
            Integer.parseInt(rowCount);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
