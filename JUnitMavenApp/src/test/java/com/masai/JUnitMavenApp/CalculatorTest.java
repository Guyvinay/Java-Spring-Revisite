package com.masai.JUnitMavenApp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

	@Test
	public void testDevide_whenValidValueProvided_shouldReturnExpectedValue() {
		//Arrange
		Calculator cal = new Calculator();
		
		//Act
		int divide = cal.divide(4, 2);
		
		//Assert
//		Assertions.assertEquals(3, divide, "4/2 Should return 2");
		
		Assertions.assertEquals(2, divide, () -> "4/2 Sould return 2");
		
		
	}
	
	/*
//here we should not worry about the complexities of the main business logic.
@Test
public void testDivide_whenValidValueProvided_shouldReturnExpectedResult() {
// Arrange
Calculator c = new Calculator();
// Act
int result = c.divide(4, 2);
// Assert
Assertions.assertEquals(2, result, "4/2 should have returned 2");
//here message is optional, but the default message is not much descriptive.
//we can define this message using Lambda expression also. example:
//Assertions.assertEquals(2, result, () -> "4/2 should have returned 2");
//now this Lambda message will be executed only at the time if this test method fails. it will improve the performance.
}
	 */
	
}
