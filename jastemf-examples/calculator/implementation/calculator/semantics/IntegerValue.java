package calculator.semantics;

/**
 * @author C. Bürger
 */
public class IntegerValue extends Value {
	public int value;
	public IntegerValue(int v) {value = v;}
	public IntegerValue asIntegerValue() {return this;}
	public String toString() {return ""+ value;}
}
