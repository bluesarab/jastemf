package calculator.semantics;

/**
 * @author C. Bürger
 */
public class BooleanValue extends Value {
	public boolean value;
	public BooleanValue(boolean v) {value = v;}
	public BooleanValue asBooleanValue() {return this;}
	public String toString() {return ""+ value;}
}
