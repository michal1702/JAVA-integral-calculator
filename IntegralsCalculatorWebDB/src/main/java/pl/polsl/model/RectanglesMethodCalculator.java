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
public class RectanglesMethodCalculator extends Calculator{
    
    /**
     * constructor
     * @param function Integrated function 
     * @param begin beginning of integration
     * @param end end of integration
     */
    public RectanglesMethodCalculator(String function, int begin, int end){
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
        double areaSum = 0.0;
        double rectangleSide = calcHeight(begin, end, height);
        double midOfSide = begin + (end - begin) / (2.0 * quadrangleCount);

        for (int i = 0; i < quadrangleCount; ++i) {

            areaSum += calculateFunctionValue(midOfSide);
            midOfSide += rectangleSide;
        }
        result =  areaSum * rectangleSide;
    }
}
