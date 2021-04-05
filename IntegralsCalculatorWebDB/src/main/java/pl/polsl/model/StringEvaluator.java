/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.model;
/**
 *
 * @author Michał Opiełka
 * @version 1.0
 */
public class StringEvaluator {

    /**
     * Mathematical expression
     */
    private final String mathExpression;
    /**
     * Actual position in expression
     */
    private int position;
    /**
     * Character in expression
     */
    private int character;

    /**
     * Constructor
     *
     * @param function Function as string
     */
    public StringEvaluator(String function) {
        this.position = -1;
        this.mathExpression = function;
    }

    /**
     * Method determines if checked position is in the string
     */
    private void nextCharacter() {
        position++;
        if (position < mathExpression.length()) {
            character = mathExpression.charAt(position);
        } else {
            character = -1;
        }
    }

    /**
     * Method skips character given as parameter
     * @param characterToSkip character to skip
     * @return if skipped
     */
    private boolean skip(char characterToSkip) {
        while (character == ' ') {
            nextCharacter();
        }
        if (character == characterToSkip) {
            nextCharacter();
            return true;
        }
        return false;
    }

    /**
     * Main parsing method responsible for transforming string expression into double
     * @return result of a transformation
     */
    public double parse(){
        nextCharacter();
        double result = parseExpression();
        return result;
    }

    /**
     * Method responsible for parsing expressions
     * @return parsing result
     */
    private double parseExpression() {
        double result = parseTerm();
        while (true) {
            if (skip('+')) {
                result += parseTerm();
            } else if (skip('-')) {
                result -= parseTerm();
            } else {
                return result;
            }
        }
    }

    /**
     * Method responsible for parsing terms
     * @return parsing result
     */
    private double parseTerm() {
        double result = parseFactor();
        while(true) {
            if (skip('*')) {
                result *= parseFactor();
            } else if (skip('/')) {
                result /= parseFactor();
            } else {
                return result;
            }
        }
    }

    /**
     * Method responsible for parsing factors
     * @return parsing result
     */
    private double parseFactor() {
        if (skip('+')) {
            return parseFactor();
        }
        if (skip('-')) {
            return -parseFactor();
        }
        double parseResult;
        int startPos = this.position;
        if ((character >= '0' && character <= '9') || character == '.') {
            while ((character >= '0' && character <= '9') || character == '.') {
                nextCharacter();
            }
            parseResult = Double.parseDouble(mathExpression.substring(startPos, this.position));
        } else {
            throw new RuntimeException("Unexpected: " + (char) character);
        }
        return parseResult;
    }
}
