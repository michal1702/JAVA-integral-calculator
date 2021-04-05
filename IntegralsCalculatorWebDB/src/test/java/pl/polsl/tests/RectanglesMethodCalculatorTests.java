/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.tests;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import static org.junit.jupiter.api.Assertions.*;
import pl.polsl.model.*;
import pl.polsl.exceptions.RangeException;
//import pl.polsl.exceptions.*;
/**
 *
 * @author Michał Opiełka
 * @version 1.0
 */
public class RectanglesMethodCalculatorTests {
     /**
     * Checks exception when begin>end for a rectangle method
     * @param begin beginning of integration
     * @param end end of integration
     * @param function integrated function
     */
    @ParameterizedTest
    @CsvSource({"10,-5,x+2","0,-1,x+2","23,14,x+2"})
    public void testRectanglesMethodRangeException(int begin, int end, String function) {
        RectanglesMethodCalculator calculator = new RectanglesMethodCalculator(function, begin, end);
        Throwable exception = assertThrows(RangeException.class, () -> calculator.integrate());
        assertEquals("Wrong range of integration", exception.getMessage());
    }
    
    /**
     * Should not return a range exception when begin<end or begin==end for a rectangles method
     * @param begin beginning of integration
     * @param end end of integration
     * @param function integrated function
     */
    @ParameterizedTest
    @CsvSource({"2,12,x+2","2,2,x+2","3,23,x+2"})
    public void testRectanglesMethodNoRangeException(int begin, int end, String function){
        RectanglesMethodCalculator calculator = new RectanglesMethodCalculator(function, begin, end);
        assertDoesNotThrow(()->calculator.integrate());
    }
    
    /**
     * Tests integrals calculations using rectangles method
     * @param begin beginning of integration
     * @param end end of integration
     * @param function integrated function
     * @param expected expected result
     * @throws RangeException 
     */
    @ParameterizedTest
    @CsvSource({"2,12,x*x+3.23/11*7.12,594.24","-23,-1,5*x*x-7.88/x-10,20081.4"})
    public void testRectanglesMethod(int begin, int end, String function, double expected)throws RangeException{
        RectanglesMethodCalculator calculator = new RectanglesMethodCalculator(function, begin, end);
        calculator.integrate();
        assertEquals(expected, calculator.getResult(), 0.1);
    }
}
