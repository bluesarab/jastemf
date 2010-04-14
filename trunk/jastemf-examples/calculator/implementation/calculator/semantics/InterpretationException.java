package calculator.semantics;

/**
 * Exception type thrown iff an error occurs during a <i>SIPLE</i> program's
 * interpretation. In general, it is impossible to statically guarantee
 * successful program execution. E.g. a devision by 0 can only be statically
 * detected if the divisor is constant.
 * @author C. Bürger
 */
public class InterpretationException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public InterpretationException(String message) {super(message);}
	public InterpretationException() {
		super("There exist unresolved compilation errors.");
	}
	public String getMessage() {
		return "Interpretation Error: " + super.getMessage();
	}
}
