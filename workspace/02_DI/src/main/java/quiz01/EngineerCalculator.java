package quiz01;

import org.springframework.beans.factory.annotation.Autowired;

public class EngineerCalculator {

	// field
	@Autowired
	private Calculator calculator;
	
	public void add(int a, int b) {
		calculator.add(a, b);
	}
	public void subtract(int a, int b) {
		calculator.subtract(a, b);
	}
	public void multiply(int a, int b) {
		calculator.multiply(a, b);
	}
	public void divide(int a, int b) {
		calculator.divide(a, b);
	}
	
}