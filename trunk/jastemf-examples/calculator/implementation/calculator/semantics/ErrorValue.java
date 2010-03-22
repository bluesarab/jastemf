package calculator.semantics;

/**
 * @author C. Bürger
 */
public class ErrorValue extends Value {
	public ErrorValue asErrorValue() {return this;}
	public String toString() {return "Error";}
}
