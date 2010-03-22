package calculator.semantics;

/**
 * @author C. Bürger
 */
public abstract class Value {
	public ErrorValue asErrorValue() {return null;}
	public BooleanValue asBooleanValue() {return null;}
	public IntegerValue asIntegerValue() {return null;}
	public FloatValue asFloatValue() {return null;}
	
	/**
	 * Parse a string and return a Value object
	 * The valueOf method is called by the create...FromString method
	 * in the ECore Factories.
	 * @param s
	 * @return a value object
	 */
	public static Value valueOf(String s) {
		if (s.equalsIgnoreCase("true"))
			return new BooleanValue(true);
		if (s.equalsIgnoreCase("false"))
			return new BooleanValue(false);
		Type t = Type.typeOf(s);
		if (t == Type.Integer)
			return new IntegerValue(Integer.parseInt(s));
		if (t == Type.Float)
			return new FloatValue(Float.parseFloat(s));
		
		return new ErrorValue();
	}
	
	public Value not() {
		if (asBooleanValue() == null)
			return new ErrorValue();
		return new BooleanValue(!asBooleanValue().value);
	}
	public Value uminus() {
		if (asErrorValue() != null || asBooleanValue() != null)
			return new ErrorValue();
		if (asIntegerValue() != null)
			return new IntegerValue(-asIntegerValue().value);
		return new FloatValue(-asFloatValue().value);
	}
	
	private Value isErrorOperation(Value v) {
		if (asErrorValue() != null || v.asErrorValue() != null)
			return new ErrorValue();
		if (asBooleanValue() != null)
			if (v.asBooleanValue() == null)
				return new ErrorValue();
		if (v.asBooleanValue() != null)
			if (asBooleanValue() == null)
				return new ErrorValue();
		return null;
	}
	public Value and(Value v) {
		if (isErrorOperation(v) != null)
			return new ErrorValue();
		return new BooleanValue(asBooleanValue().value && v.asBooleanValue().value);
	}
	public Value or(Value v) {
		if (isErrorOperation(v) != null)
			return new ErrorValue();
		return new BooleanValue(asBooleanValue().value || v.asBooleanValue().value);
	}
	public Value equal(Value v) {
		if (isErrorOperation(v) != null)
			return new ErrorValue();
		if (asIntegerValue() != null)
			if (v.asIntegerValue() != null)
				return new BooleanValue(asIntegerValue().value == v.asIntegerValue().value);
			else return new BooleanValue(asIntegerValue().value == v.asFloatValue().value);
		if (asFloatValue() != null)
			if (v.asIntegerValue() != null)
				return new BooleanValue(asFloatValue().value == v.asIntegerValue().value);
			else return new BooleanValue(asFloatValue().value == v.asFloatValue().value);
		return new BooleanValue(asBooleanValue().value == v.asBooleanValue().value);
	}
	public Value add(Value v) {
		if (isErrorOperation(v) != null)
			return new ErrorValue();
		if (asFloatValue() != null)
			if (v.asFloatValue() != null)
				return new FloatValue(asFloatValue().value + v.asFloatValue().value);
			else return new FloatValue(asFloatValue().value + v.asIntegerValue().value);
		if (v.asFloatValue() != null)
			return new FloatValue(asIntegerValue().value + v.asFloatValue().value);
		return new IntegerValue(asIntegerValue().value + v.asIntegerValue().value);
	}
	public Value sub(Value v) {
		if (isErrorOperation(v) != null)
			return new ErrorValue();
		if (asFloatValue() != null)
			if (v.asFloatValue() != null)
				return new FloatValue(asFloatValue().value - v.asFloatValue().value);
			else return new FloatValue(asFloatValue().value - v.asIntegerValue().value);
		if (v.asFloatValue() != null)
			return new FloatValue(asIntegerValue().value - v.asFloatValue().value);
		return new IntegerValue(asIntegerValue().value - v.asIntegerValue().value);
	}
	public Value mul(Value v) {
		if (isErrorOperation(v) != null)
			return new ErrorValue();
		if (asFloatValue() != null)
			if (v.asFloatValue() != null)
				return new FloatValue(asFloatValue().value * v.asFloatValue().value);
			else return new FloatValue(asFloatValue().value * v.asIntegerValue().value);
		if (v.asFloatValue() != null)
			return new FloatValue(asIntegerValue().value * v.asFloatValue().value);
		return new IntegerValue(asIntegerValue().value * v.asIntegerValue().value);
	}
	public Value div(Value v) {
		if (isErrorOperation(v) != null)
			return new ErrorValue();
		if (asFloatValue() != null)
			if (v.asFloatValue() != null)
				return new FloatValue(asFloatValue().value / v.asFloatValue().value);
			else return new FloatValue(asFloatValue().value / v.asIntegerValue().value);
		if (v.asFloatValue() != null)
			return new FloatValue(asIntegerValue().value / v.asFloatValue().value);
		return new IntegerValue(asIntegerValue().value / v.asIntegerValue().value);
	}
}
