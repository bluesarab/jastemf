package org.jastemf.siple.converter;

import org.eclipse.xtext.conversion.IValueConverter;
import org.eclipse.xtext.conversion.ValueConverter;
import org.eclipse.xtext.conversion.ValueConverterException;
import org.eclipse.xtext.nodemodel.INode;
import org.jastemf.siple.semantics.Type;

public class PrimitiveTypesConverter {

	@ValueConverter(rule = "Type")
	public IValueConverter<Type> getMyRuleNameConverter() { 
		return new IValueConverter<Type>(){

			@Override
			public Type toValue(String string, INode node)
					throws ValueConverterException {
				if(string.equals("Boolean")){
					return Type.Boolean;
				}
				else if(string.equals("Real")){
					return Type.Real;
				}
				else if(string.equals("Integer")){
					return Type.Integer;
				}
				return Type.ERROR_TYPE;
			}

			@Override
			public String toString(Type value) throws ValueConverterException {
				// TODO Auto-generated method stub
				return null;
			}
			
		};
	}
	
	
	
}
