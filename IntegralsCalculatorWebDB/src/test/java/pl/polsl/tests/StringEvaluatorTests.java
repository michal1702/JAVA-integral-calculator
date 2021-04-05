/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.tests;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import static org.junit.jupiter.api.Assertions.*;

import pl.polsl.model.StringEvaluator;
/**
 *
 * @author Michał Opiełka
 * @version 1.0
 */
public class StringEvaluatorTests {
    
    /**
     * Tests parser from string evaluator
     * @param mathematicalExpression mathematical expression
     * @param expected expected value
     */
    @ParameterizedTest
    @CsvSource({"0*0,0","1*0,0","-1*-1,1","-6.123/5.324,-1.15","2*3.33*3.33/4.91+1,5.516"})
    public void testParser(String mathematicalExpression, double expected){
        StringEvaluator evaluator = new StringEvaluator(mathematicalExpression);
        assertEquals(expected,evaluator.parse(),0.001);
    }
}
