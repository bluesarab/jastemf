package calculator.semantics;

/**
 * @author C. Bürger
 */
public enum Type {
	Bool,
	Integer,
	Float,
	ERROR_TYPE;
	public static Type computeCompatibleType(Type t1, Type t2) {
		if (t1 == t2)
			return t1;
		if (t1 == ERROR_TYPE || t2 == ERROR_TYPE)
			return ERROR_TYPE;
		if (t1 == Bool || t2 == Bool)
			return ERROR_TYPE;
		if (t1 == Float || t2 == Float)
			return Float;
		return Integer;
	}
	
	public static Type typeOf(String s){
		if (s.equalsIgnoreCase("true"))
			return Bool;
		if (s.equalsIgnoreCase("false"))
			return Bool;
		boolean numbers = true;
		boolean dot = false;
		boolean firstchar = true;
		for (char c: s.toCharArray())
		{
			if (firstchar)
			{
				firstchar = false;
				if (c == '+' || c == '-')
					continue;
			}
			if (c == '.' && dot)
				numbers = false;
			if ((c < '0' || c > '9') && (c != '.'))
				numbers = false;
			if (c == '.')
				dot = true;
		}
		if (numbers && !dot)
			return Integer;
		if (numbers && dot)
			return Float;
		
		return ERROR_TYPE;
	}
	
}
