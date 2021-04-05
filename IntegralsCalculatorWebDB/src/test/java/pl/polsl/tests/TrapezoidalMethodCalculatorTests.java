/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.tests;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import static org.junit.jupiter.api.Assertions.*;
import pl.polsl.exceptions.RangeException;
import pl.polsl.model.TrapezoidalMethodCalculator;

/**
 *
 * @author Michał Opiełka
 * @version 1.0
 */
public class TrapezoidalMethodCalculatorTests {
    /**
     * Checks exception when begin>end for a trapezoidal method
     * @param begin beginning of integration
     * @param end end of integration
     * @param function integrated function
     */
    @ParameterizedTest
    @CsvSource({"10,-5,x+2","0,-1,x+2","23,14,x+2"})
    public void testTrapezoidalMethodRangeException(int begin, int end, String function) {
        TrapezoidalMethodCalculator calculator = new TrapezoidalMethodCalculator(function, begin, end);
        Throwable exception = assertThrows(RangeException.class, () -> calculator.integrate());
        assertEquals("Wrong range of integration", exception.getMessage());
    }
    
    /**
     * Should not return a range exception when begin<end or begin==end for a trapezoidal method
     * @param begin beginning of integration
     * @param end end of integration
     * @param function integrated function
     */
    @ParameterizedTest
    @CsvSource({"2,12,x+2","2,2,x+2","3,23,x+2"})
    public void testTrapezoidalMethodNoRangeException(int begin, int end, String function){
        TrapezoidalMethodCalculator calculator = new TrapezoidalMethodCalculator(function, begin, end);
        assertDoesNotThrow(()->calculator.integrate());
    }
    
    /**
     * Tests integrals calculations using trapezoidal method
     * @param begin beginning of integration
     * @param end end of integration
     * @param function integrated function
     * @param expected expected result
     * @throws RangeException 
     */
    @ParameterizedTest
    @CsvSource({"2,12,x*x+3.23/11*7.12,594.24","-23,-1,5*x*x-7.88/x-10,20081.4"})
    public void testTrapezoidalMethod(int begin, int end, String function, double expected)throws RangeException{
        TrapezoidalMethodCalculator calculator = new TrapezoidalMethodCalculator(function, begin, end);
        calculator.integrate();
        assertEquals(expected, calculator.getResult(), 0.1);
    }
}
