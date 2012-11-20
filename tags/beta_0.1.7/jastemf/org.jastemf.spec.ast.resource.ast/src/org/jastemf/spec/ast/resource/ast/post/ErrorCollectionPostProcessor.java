package org.jastemf.spec.ast.resource.ast.post;

import java.util.Collections;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.jastemf.spec.ast.Grammar;
import org.jastemf.spec.ast.validation.IProblemDescriptor;
import org.jastemf.spec.ast.impl.GrammarImpl;
import org.jastemf.spec.ast.resource.ast.IAstOptionProvider;
import org.jastemf.spec.ast.resource.ast.IAstOptions;
import org.jastemf.spec.ast.resource.ast.IAstResourcePostProcessor;
import org.jastemf.spec.ast.resource.ast.IAstResourcePostProcessorProvider;
import org.jastemf.spec.ast.resource.ast.mopp.AstResource;

public class ErrorCollectionPostProcessor implements 
IAstResourcePostProcessor, IAstResourcePostProcessorProvider, IAstOptionProvider {

	@Override
	public void process(AstResource resource) {
		EObject eo = resource.getContents().get(0);
		if(eo!=null&&eo instanceof Grammar){
			GrammarImpl grammar = (GrammarImpl)eo;
			System.out.println(grammar.astErrors());
			for(IProblemDescriptor desc : grammar.astErrors2()){
				resource.addError(desc.getMessage(),desc.getCause());
			}
		}
	}

	@Override
	public IAstResourcePostProcessor getResourcePostProcessor() {
		return this;
	}

	@Override
	public Map<?, ?> getOptions() {
		return Collections.singletonMap(IAstOptions.RESOURCE_POSTPROCESSOR_PROVIDER, this);
	}

}
