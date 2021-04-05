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
 * @version 1.0
 */
public class TrapezoidalMethodCalculator extends Calculator{
    
    /**
     * Constructor 
     * @param function Integrated function
     * @param begin beginning of integration
     * @param end end of integration
     */
    public TrapezoidalMethodCalculator(String function, int begin, int end){
        super(function, begin, end);
    }
    
    /**
     * Method perrforms integration
     */
    @Override
    public void integrate()throws RangeException{
        if (end < begin) {
            throw new RangeException("Wrong range of integration");
        }
        doubleMath height = (a, b) -> {
            return (b - a) / (double) this.quadrangleCount;
        };
        double h = calcHeight(begin, end, height);
        double areaSum = 0.0;
        double baseA = calculateFunctionValue(begin);
        double baseB;

        for (int i = 0; i <= quadrangleCount; ++i) {
            baseB = calculateFunctionValue(begin + h * i);
            areaSum = areaSum + baseA + baseB;
            baseA = baseB;
        }
        result = areaSum * 0.5 * h;
    }
}
