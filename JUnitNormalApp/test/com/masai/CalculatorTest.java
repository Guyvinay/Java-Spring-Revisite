package com.masai;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class CalculatorTest {

	@Test
	public void testDivide_whenValidValueProvided_shouldReturnExpectedResult() {
		//Arrange
				Calculator cal = new Calculator();
				
				//Act
				int divide = cal.divide(4, 2);
				
				//Assert
//				Assertions.assertEquals(3, divide, "4/2 Should return 2");
				
				Assertions.assertEquals(3, divide, () -> "4/2 Sould return 2");
	}
	
}
