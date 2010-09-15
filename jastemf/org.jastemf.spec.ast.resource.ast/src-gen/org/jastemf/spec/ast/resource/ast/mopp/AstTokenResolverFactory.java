/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.jastemf.spec.ast.resource.ast.mopp;

public class AstTokenResolverFactory implements org.jastemf.spec.ast.resource.ast.IAstTokenResolverFactory {
	
	private java.util.Map<java.lang.String, org.jastemf.spec.ast.resource.ast.IAstTokenResolver> tokenName2TokenResolver;
	private java.util.Map<java.lang.String, org.jastemf.spec.ast.resource.ast.IAstTokenResolver> featureName2CollectInTokenResolver;
	private static org.jastemf.spec.ast.resource.ast.IAstTokenResolver defaultResolver = new org.jastemf.spec.ast.resource.ast.analysis.AstDefaultTokenResolver();
	
	public AstTokenResolverFactory() {
		tokenName2TokenResolver = new java.util.LinkedHashMap<java.lang.String, org.jastemf.spec.ast.resource.ast.IAstTokenResolver>();
		featureName2CollectInTokenResolver = new java.util.LinkedHashMap<java.lang.String, org.jastemf.spec.ast.resource.ast.IAstTokenResolver>();
		registerTokenResolver("IDENT", new org.jastemf.spec.ast.resource.ast.analysis.AstIDENTTokenResolver());
		registerTokenResolver("QIDENT", new org.jastemf.spec.ast.resource.ast.analysis.AstQIDENTTokenResolver());
	}
	
	public org.jastemf.spec.ast.resource.ast.IAstTokenResolver createTokenResolver(java.lang.String tokenName) {
		return internalCreateResolver(tokenName2TokenResolver, tokenName);
	}
	
	public org.jastemf.spec.ast.resource.ast.IAstTokenResolver createCollectInTokenResolver(java.lang.String featureName) {
		return internalCreateResolver(featureName2CollectInTokenResolver, featureName);
	}
	
	protected boolean registerTokenResolver(java.lang.String tokenName, org.jastemf.spec.ast.resource.ast.IAstTokenResolver resolver){
		return internalRegisterTokenResolver(tokenName2TokenResolver, tokenName, resolver);
	}
	
	protected boolean registerCollectInTokenResolver(java.lang.String featureName, org.jastemf.spec.ast.resource.ast.IAstTokenResolver resolver){
		return internalRegisterTokenResolver(featureName2CollectInTokenResolver, featureName, resolver);
	}
	
	protected org.jastemf.spec.ast.resource.ast.IAstTokenResolver deRegisterTokenResolver(java.lang.String tokenName){
		return tokenName2TokenResolver.remove(tokenName);
	}
	
	private org.jastemf.spec.ast.resource.ast.IAstTokenResolver internalCreateResolver(java.util.Map<java.lang.String, org.jastemf.spec.ast.resource.ast.IAstTokenResolver> resolverMap, String key) {
		if (resolverMap.containsKey(key)){
			return resolverMap.get(key);
		} else {
			return defaultResolver;
		}
	}
	
	private boolean internalRegisterTokenResolver(java.util.Map<java.lang.String, org.jastemf.spec.ast.resource.ast.IAstTokenResolver> resolverMap, java.lang.String key, org.jastemf.spec.ast.resource.ast.IAstTokenResolver resolver) {
		if (!resolverMap.containsKey(key)) {
			resolverMap.put(key,resolver);
			return true;
		}
		return false;
	}
	
}
