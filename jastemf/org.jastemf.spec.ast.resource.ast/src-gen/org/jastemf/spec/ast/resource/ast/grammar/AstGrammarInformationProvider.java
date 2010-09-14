/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.jastemf.spec.ast.resource.ast.grammar;

public class AstGrammarInformationProvider {
	
	public final static org.eclipse.emf.ecore.EStructuralFeature ANONYMOUS_FEATURE = org.eclipse.emf.ecore.EcoreFactory.eINSTANCE.createEAttribute();
	static {
		ANONYMOUS_FEATURE.setName("_");
	}
	
	public static class Rule extends org.jastemf.spec.ast.resource.ast.grammar.AstSyntaxElement {
		
		private final org.eclipse.emf.ecore.EClass metaclass;
		
		public Rule(org.eclipse.emf.ecore.EClass metaclass, org.jastemf.spec.ast.resource.ast.grammar.AstChoice choice, org.jastemf.spec.ast.resource.ast.grammar.AstCardinality cardinality) {
			super(cardinality, new org.jastemf.spec.ast.resource.ast.grammar.AstSyntaxElement[] {choice});
			this.metaclass = metaclass;
		}
		
		public org.eclipse.emf.ecore.EClass getMetaclass() {
			return metaclass;
		}
		
		public org.jastemf.spec.ast.resource.ast.grammar.AstChoice getDefinition() {
			return (org.jastemf.spec.ast.resource.ast.grammar.AstChoice) getChildren()[0];
		}
	}
	
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstContainment AST_0_0_0_0 = new org.jastemf.spec.ast.resource.ast.grammar.AstContainment(org.jastemf.spec.ast.AstPackage.eINSTANCE.getGrammar().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.GRAMMAR__TYPE_DECL), org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.STAR, 0);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstSequence AST_0_0_0 = new org.jastemf.spec.ast.resource.ast.grammar.AstSequence(org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE, AST_0_0_0_0);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstChoice AST_0_0 = new org.jastemf.spec.ast.resource.ast.grammar.AstChoice(org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE, AST_0_0_0);
	public final static Rule AST_0 = new Rule(org.jastemf.spec.ast.AstPackage.eINSTANCE.getGrammar(), AST_0_0, org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstContainment AST_1_0_0_0 = new org.jastemf.spec.ast.resource.ast.grammar.AstContainment(org.jastemf.spec.ast.AstPackage.eINSTANCE.getASTDecl().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.AST_DECL__ABSTRACT), org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.QUESTIONMARK, 0);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstContainment AST_1_0_0_1 = new org.jastemf.spec.ast.resource.ast.grammar.AstContainment(org.jastemf.spec.ast.AstPackage.eINSTANCE.getASTDecl().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.AST_DECL__ID_DECL), org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE, 0);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstKeyword AST_1_0_0_2_0_0_0 = new org.jastemf.spec.ast.resource.ast.grammar.AstKeyword(":", org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstContainment AST_1_0_0_2_0_0_1 = new org.jastemf.spec.ast.resource.ast.grammar.AstContainment(org.jastemf.spec.ast.AstPackage.eINSTANCE.getASTDecl().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.AST_DECL__SUPER_CLASS_ID), org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE, 0);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstSequence AST_1_0_0_2_0_0 = new org.jastemf.spec.ast.resource.ast.grammar.AstSequence(org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE, AST_1_0_0_2_0_0_0, AST_1_0_0_2_0_0_1);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstChoice AST_1_0_0_2_0 = new org.jastemf.spec.ast.resource.ast.grammar.AstChoice(org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE, AST_1_0_0_2_0_0);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstCompound AST_1_0_0_2 = new org.jastemf.spec.ast.resource.ast.grammar.AstCompound(AST_1_0_0_2_0, org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.QUESTIONMARK);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstKeyword AST_1_0_0_3_0_0_0 = new org.jastemf.spec.ast.resource.ast.grammar.AstKeyword("::=", org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstContainment AST_1_0_0_3_0_0_1 = new org.jastemf.spec.ast.resource.ast.grammar.AstContainment(org.jastemf.spec.ast.AstPackage.eINSTANCE.getASTDecl().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.AST_DECL__COMPONENTS), org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.PLUS, 0);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstSequence AST_1_0_0_3_0_0 = new org.jastemf.spec.ast.resource.ast.grammar.AstSequence(org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE, AST_1_0_0_3_0_0_0, AST_1_0_0_3_0_0_1);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstChoice AST_1_0_0_3_0 = new org.jastemf.spec.ast.resource.ast.grammar.AstChoice(org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE, AST_1_0_0_3_0_0);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstCompound AST_1_0_0_3 = new org.jastemf.spec.ast.resource.ast.grammar.AstCompound(AST_1_0_0_3_0, org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.QUESTIONMARK);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstKeyword AST_1_0_0_4 = new org.jastemf.spec.ast.resource.ast.grammar.AstKeyword(";", org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstSequence AST_1_0_0 = new org.jastemf.spec.ast.resource.ast.grammar.AstSequence(org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE, AST_1_0_0_0, AST_1_0_0_1, AST_1_0_0_2, AST_1_0_0_3, AST_1_0_0_4);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstChoice AST_1_0 = new org.jastemf.spec.ast.resource.ast.grammar.AstChoice(org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE, AST_1_0_0);
	public final static Rule AST_1 = new Rule(org.jastemf.spec.ast.AstPackage.eINSTANCE.getASTDecl(), AST_1_0, org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstContainment AST_2_0_0_0 = new org.jastemf.spec.ast.resource.ast.grammar.AstContainment(org.jastemf.spec.ast.AstPackage.eINSTANCE.getListComponents().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.LIST_COMPONENTS__ID), org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE, 0);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstKeyword AST_2_0_0_1 = new org.jastemf.spec.ast.resource.ast.grammar.AstKeyword("*", org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstSequence AST_2_0_0 = new org.jastemf.spec.ast.resource.ast.grammar.AstSequence(org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE, AST_2_0_0_0, AST_2_0_0_1);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstChoice AST_2_0 = new org.jastemf.spec.ast.resource.ast.grammar.AstChoice(org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE, AST_2_0_0);
	public final static Rule AST_2 = new Rule(org.jastemf.spec.ast.AstPackage.eINSTANCE.getListComponents(), AST_2_0, org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstKeyword AST_3_0_0_0 = new org.jastemf.spec.ast.resource.ast.grammar.AstKeyword("/", org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstContainment AST_3_0_0_1 = new org.jastemf.spec.ast.resource.ast.grammar.AstContainment(org.jastemf.spec.ast.AstPackage.eINSTANCE.getListComponentsNTA().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.LIST_COMPONENTS_NTA__ID), org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE, 0);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstKeyword AST_3_0_0_2 = new org.jastemf.spec.ast.resource.ast.grammar.AstKeyword("*", org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstKeyword AST_3_0_0_3 = new org.jastemf.spec.ast.resource.ast.grammar.AstKeyword("/", org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstSequence AST_3_0_0 = new org.jastemf.spec.ast.resource.ast.grammar.AstSequence(org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE, AST_3_0_0_0, AST_3_0_0_1, AST_3_0_0_2, AST_3_0_0_3);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstChoice AST_3_0 = new org.jastemf.spec.ast.resource.ast.grammar.AstChoice(org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE, AST_3_0_0);
	public final static Rule AST_3 = new Rule(org.jastemf.spec.ast.AstPackage.eINSTANCE.getListComponentsNTA(), AST_3_0, org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstKeyword AST_4_0_0_0 = new org.jastemf.spec.ast.resource.ast.grammar.AstKeyword("<", org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstContainment AST_4_0_0_1 = new org.jastemf.spec.ast.resource.ast.grammar.AstContainment(org.jastemf.spec.ast.AstPackage.eINSTANCE.getTokenComponent().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.TOKEN_COMPONENT__TOKEN_ID), org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE, 0);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstKeyword AST_4_0_0_2 = new org.jastemf.spec.ast.resource.ast.grammar.AstKeyword(">", org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstSequence AST_4_0_0 = new org.jastemf.spec.ast.resource.ast.grammar.AstSequence(org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE, AST_4_0_0_0, AST_4_0_0_1, AST_4_0_0_2);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstChoice AST_4_0 = new org.jastemf.spec.ast.resource.ast.grammar.AstChoice(org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE, AST_4_0_0);
	public final static Rule AST_4 = new Rule(org.jastemf.spec.ast.AstPackage.eINSTANCE.getTokenComponent(), AST_4_0, org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstKeyword AST_5_0_0_0 = new org.jastemf.spec.ast.resource.ast.grammar.AstKeyword("/", org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstKeyword AST_5_0_0_1 = new org.jastemf.spec.ast.resource.ast.grammar.AstKeyword("<", org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstContainment AST_5_0_0_2 = new org.jastemf.spec.ast.resource.ast.grammar.AstContainment(org.jastemf.spec.ast.AstPackage.eINSTANCE.getTokenComponentNTA().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.TOKEN_COMPONENT_NTA__TOKEN_ID), org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE, 0);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstKeyword AST_5_0_0_3 = new org.jastemf.spec.ast.resource.ast.grammar.AstKeyword(">", org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstKeyword AST_5_0_0_4 = new org.jastemf.spec.ast.resource.ast.grammar.AstKeyword("/", org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstSequence AST_5_0_0 = new org.jastemf.spec.ast.resource.ast.grammar.AstSequence(org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE, AST_5_0_0_0, AST_5_0_0_1, AST_5_0_0_2, AST_5_0_0_3, AST_5_0_0_4);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstChoice AST_5_0 = new org.jastemf.spec.ast.resource.ast.grammar.AstChoice(org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE, AST_5_0_0);
	public final static Rule AST_5 = new Rule(org.jastemf.spec.ast.AstPackage.eINSTANCE.getTokenComponentNTA(), AST_5_0, org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstKeyword AST_6_0_0_0 = new org.jastemf.spec.ast.resource.ast.grammar.AstKeyword("[", org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstContainment AST_6_0_0_1 = new org.jastemf.spec.ast.resource.ast.grammar.AstContainment(org.jastemf.spec.ast.AstPackage.eINSTANCE.getOptionalComponent().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.OPTIONAL_COMPONENT__ID), org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE, 0);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstKeyword AST_6_0_0_2 = new org.jastemf.spec.ast.resource.ast.grammar.AstKeyword("]", org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstSequence AST_6_0_0 = new org.jastemf.spec.ast.resource.ast.grammar.AstSequence(org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE, AST_6_0_0_0, AST_6_0_0_1, AST_6_0_0_2);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstChoice AST_6_0 = new org.jastemf.spec.ast.resource.ast.grammar.AstChoice(org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE, AST_6_0_0);
	public final static Rule AST_6 = new Rule(org.jastemf.spec.ast.AstPackage.eINSTANCE.getOptionalComponent(), AST_6_0, org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstKeyword AST_7_0_0_0 = new org.jastemf.spec.ast.resource.ast.grammar.AstKeyword("/", org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstKeyword AST_7_0_0_1 = new org.jastemf.spec.ast.resource.ast.grammar.AstKeyword("[", org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstContainment AST_7_0_0_2 = new org.jastemf.spec.ast.resource.ast.grammar.AstContainment(org.jastemf.spec.ast.AstPackage.eINSTANCE.getOptionalComponentNTA().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.OPTIONAL_COMPONENT_NTA__ID), org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE, 0);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstKeyword AST_7_0_0_3 = new org.jastemf.spec.ast.resource.ast.grammar.AstKeyword("]", org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstKeyword AST_7_0_0_4 = new org.jastemf.spec.ast.resource.ast.grammar.AstKeyword("/", org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstSequence AST_7_0_0 = new org.jastemf.spec.ast.resource.ast.grammar.AstSequence(org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE, AST_7_0_0_0, AST_7_0_0_1, AST_7_0_0_2, AST_7_0_0_3, AST_7_0_0_4);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstChoice AST_7_0 = new org.jastemf.spec.ast.resource.ast.grammar.AstChoice(org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE, AST_7_0_0);
	public final static Rule AST_7 = new Rule(org.jastemf.spec.ast.AstPackage.eINSTANCE.getOptionalComponentNTA(), AST_7_0, org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstContainment AST_8_0_0_0 = new org.jastemf.spec.ast.resource.ast.grammar.AstContainment(org.jastemf.spec.ast.AstPackage.eINSTANCE.getAggregateComponents().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.AGGREGATE_COMPONENTS__ID), org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE, 0);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstSequence AST_8_0_0 = new org.jastemf.spec.ast.resource.ast.grammar.AstSequence(org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE, AST_8_0_0_0);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstChoice AST_8_0 = new org.jastemf.spec.ast.resource.ast.grammar.AstChoice(org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE, AST_8_0_0);
	public final static Rule AST_8 = new Rule(org.jastemf.spec.ast.AstPackage.eINSTANCE.getAggregateComponents(), AST_8_0, org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstKeyword AST_9_0_0_0 = new org.jastemf.spec.ast.resource.ast.grammar.AstKeyword("/", org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstContainment AST_9_0_0_1 = new org.jastemf.spec.ast.resource.ast.grammar.AstContainment(org.jastemf.spec.ast.AstPackage.eINSTANCE.getAggregateComponentsNTA().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.AGGREGATE_COMPONENTS_NTA__ID), org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE, 0);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstKeyword AST_9_0_0_2 = new org.jastemf.spec.ast.resource.ast.grammar.AstKeyword("/", org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstSequence AST_9_0_0 = new org.jastemf.spec.ast.resource.ast.grammar.AstSequence(org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE, AST_9_0_0_0, AST_9_0_0_1, AST_9_0_0_2);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstChoice AST_9_0 = new org.jastemf.spec.ast.resource.ast.grammar.AstChoice(org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE, AST_9_0_0);
	public final static Rule AST_9 = new Rule(org.jastemf.spec.ast.AstPackage.eINSTANCE.getAggregateComponentsNTA(), AST_9_0, org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstKeyword AST_10_0_0_0 = new org.jastemf.spec.ast.resource.ast.grammar.AstKeyword("abstract", org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstSequence AST_10_0_0 = new org.jastemf.spec.ast.resource.ast.grammar.AstSequence(org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE, AST_10_0_0_0);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstChoice AST_10_0 = new org.jastemf.spec.ast.resource.ast.grammar.AstChoice(org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE, AST_10_0_0);
	public final static Rule AST_10 = new Rule(org.jastemf.spec.ast.AstPackage.eINSTANCE.getAbstract(), AST_10_0, org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstContainment AST_11_0_0_0_0_0_0 = new org.jastemf.spec.ast.resource.ast.grammar.AstContainment(org.jastemf.spec.ast.AstPackage.eINSTANCE.getId().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.ID__NAME_NODE), org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE, 0);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstKeyword AST_11_0_0_0_0_0_1 = new org.jastemf.spec.ast.resource.ast.grammar.AstKeyword(":", org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstSequence AST_11_0_0_0_0_0 = new org.jastemf.spec.ast.resource.ast.grammar.AstSequence(org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE, AST_11_0_0_0_0_0_0, AST_11_0_0_0_0_0_1);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstChoice AST_11_0_0_0_0 = new org.jastemf.spec.ast.resource.ast.grammar.AstChoice(org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE, AST_11_0_0_0_0_0);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstCompound AST_11_0_0_0 = new org.jastemf.spec.ast.resource.ast.grammar.AstCompound(AST_11_0_0_0_0, org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.QUESTIONMARK);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstContainment AST_11_0_0_1 = new org.jastemf.spec.ast.resource.ast.grammar.AstContainment(org.jastemf.spec.ast.AstPackage.eINSTANCE.getId().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.ID__ID_USE), org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE, 0);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstSequence AST_11_0_0 = new org.jastemf.spec.ast.resource.ast.grammar.AstSequence(org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE, AST_11_0_0_0, AST_11_0_0_1);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstChoice AST_11_0 = new org.jastemf.spec.ast.resource.ast.grammar.AstChoice(org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE, AST_11_0_0);
	public final static Rule AST_11 = new Rule(org.jastemf.spec.ast.AstPackage.eINSTANCE.getId(), AST_11_0, org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstPlaceholder AST_12_0_0_0 = new org.jastemf.spec.ast.resource.ast.grammar.AstPlaceholder(org.jastemf.spec.ast.AstPackage.eINSTANCE.getNameNode().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.NAME_NODE__ID), "IDENT", org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE, 0);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstSequence AST_12_0_0 = new org.jastemf.spec.ast.resource.ast.grammar.AstSequence(org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE, AST_12_0_0_0);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstChoice AST_12_0 = new org.jastemf.spec.ast.resource.ast.grammar.AstChoice(org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE, AST_12_0_0);
	public final static Rule AST_12 = new Rule(org.jastemf.spec.ast.AstPackage.eINSTANCE.getNameNode(), AST_12_0, org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstPlaceholder AST_13_0_0_0 = new org.jastemf.spec.ast.resource.ast.grammar.AstPlaceholder(org.jastemf.spec.ast.AstPackage.eINSTANCE.getTokenId().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.TOKEN_ID__ID), "IDENT", org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE, 0);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstKeyword AST_13_0_0_1_0_0_0 = new org.jastemf.spec.ast.resource.ast.grammar.AstKeyword(":", org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstPlaceholder AST_13_0_0_1_0_0_1 = new org.jastemf.spec.ast.resource.ast.grammar.AstPlaceholder(org.jastemf.spec.ast.AstPackage.eINSTANCE.getTokenId().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.TOKEN_ID__TYPE), "IDENT", org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE, 0);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstSequence AST_13_0_0_1_0_0 = new org.jastemf.spec.ast.resource.ast.grammar.AstSequence(org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE, AST_13_0_0_1_0_0_0, AST_13_0_0_1_0_0_1);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstChoice AST_13_0_0_1_0 = new org.jastemf.spec.ast.resource.ast.grammar.AstChoice(org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE, AST_13_0_0_1_0_0);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstCompound AST_13_0_0_1 = new org.jastemf.spec.ast.resource.ast.grammar.AstCompound(AST_13_0_0_1_0, org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.QUESTIONMARK);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstSequence AST_13_0_0 = new org.jastemf.spec.ast.resource.ast.grammar.AstSequence(org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE, AST_13_0_0_0, AST_13_0_0_1);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstChoice AST_13_0 = new org.jastemf.spec.ast.resource.ast.grammar.AstChoice(org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE, AST_13_0_0);
	public final static Rule AST_13 = new Rule(org.jastemf.spec.ast.AstPackage.eINSTANCE.getTokenId(), AST_13_0, org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstPlaceholder AST_14_0_0_0 = new org.jastemf.spec.ast.resource.ast.grammar.AstPlaceholder(org.jastemf.spec.ast.AstPackage.eINSTANCE.getIdUse().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.ID_USE__ID), "IDENT", org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE, 0);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstSequence AST_14_0_0 = new org.jastemf.spec.ast.resource.ast.grammar.AstSequence(org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE, AST_14_0_0_0);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstChoice AST_14_0 = new org.jastemf.spec.ast.resource.ast.grammar.AstChoice(org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE, AST_14_0_0);
	public final static Rule AST_14 = new Rule(org.jastemf.spec.ast.AstPackage.eINSTANCE.getIdUse(), AST_14_0, org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstPlaceholder AST_15_0_0_0 = new org.jastemf.spec.ast.resource.ast.grammar.AstPlaceholder(org.jastemf.spec.ast.AstPackage.eINSTANCE.getIdDecl().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.ID_DECL__ID), "IDENT", org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE, 0);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstSequence AST_15_0_0 = new org.jastemf.spec.ast.resource.ast.grammar.AstSequence(org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE, AST_15_0_0_0);
	public final static org.jastemf.spec.ast.resource.ast.grammar.AstChoice AST_15_0 = new org.jastemf.spec.ast.resource.ast.grammar.AstChoice(org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE, AST_15_0_0);
	public final static Rule AST_15 = new Rule(org.jastemf.spec.ast.AstPackage.eINSTANCE.getIdDecl(), AST_15_0, org.jastemf.spec.ast.resource.ast.grammar.AstCardinality.ONE);
}
