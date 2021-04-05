/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.model;

import pl.polsl.exceptions.RangeException;
/**
 *
 * @author Michał Opiełka
 */
public abstract class Calculator {

    /**
     * Number of quadrangles(rectangles or trapezoids) used for integration
     */
    protected int quadrangleCount;
    /**
     * Integrated function
     */
    protected String function;

    /**
     * Result of integration
     */
    protected double result;

    /**
     * Beginning of the integration
     */
    protected int begin;

    /**
     * End of the integration
     */
    protected int end;

    /**
     * Lambda interface
     */
    interface doubleMath {

        double operation(int begin, int end);
    }

    /**
     * Constructor
     *
     * @param function integrated function
     * @param begin beginning of integration
     * @param end end of integration
     */
    public Calculator(String function, int begin, int end) {
        this.function = function;
        this.end = end;
        this.begin = begin;
        this.quadrangleCount = 500000;
    }

    /**
     * Calculates function value based on given argument
     *
     * @param argument argument of a function
     * @return functions value
     */
    protected double calculateFunctionValue(double argument) {
        result = 0.0;
        StringBuilder sbFunction = new StringBuilder();
        StringBuilder sbArgument = new StringBuilder();
        sbArgument.append(argument);
        sbFunction.append(function);
        int position;
        while (sbFunction.toString().contains("x")) {
            position = sbFunction.indexOf("x");
            sbFunction.replace(position, position + 1, sbArgument.toString());
        }
        StringEvaluator evaluator = new StringEvaluator(sbFunction.toString());
        result = evaluator.parse();
        return result;
    }

    /**
     * Calculates height of polygon
     *
     * @param begin beginning of integration
     * @param end end of integration
     * @param lambda lambda interface
     * @return lambda operation
     */
    protected double calcHeight(int begin, int end, doubleMath lambda) {
        return lambda.operation(begin, end);
    }

    /**
     * Returns result of the integration
     *
     * @return result as double
     */
    public double getResult() {
        return result;
    }

    /**
     * Abstract method responsible for integration
     *
     * @throws pl.polsl.exceptions.RangeException range exception
     */
    public abstract void integrate() throws RangeException;
}
