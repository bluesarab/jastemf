package org.jastemf.test;

import static org.junit.Assert.*;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.jastemf.test.ast.ASTList;
import org.jastemf.test.ast.ASTNode;
import org.jastemf.test.test.ast.D;
import org.jastemf.test.test.ast.impl.DImpl;
import org.jastemf.test.test.ntas.impl.AImpl;
import org.jastemf.test.test.ntas.impl.BImpl;
import org.jastemf.test.test.ntas.impl.CImpl;
import org.jastemf.test.test.ntas.impl.RootImpl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class APITest {

	private static interface Signature {
		public String getMethodName();

		public String getReturnTypeName();
	}

	private List<Signature> rootImplAccessors;
	{
		rootImplAccessors = new LinkedList<Signature>();
		rootImplAccessors.add(new Signature() {
			public String getReturnTypeName() {
				return "C";
			}

			public String getMethodName() {
				return "getContainmentC";
			}
		});
		rootImplAccessors.add(new Signature() {
			public String getReturnTypeName() {
				return "AImpl";
			}

			public String getMethodName() {
				return "getDerivedA";
			}
		});
		rootImplAccessors.add(new Signature() {
			public String getReturnTypeName() {
				return "CImpl";
			}

			public String getMethodName() {
				return "jastadd_getContainmentC";
			}
		});
		
		rootImplAccessors.add(new Signature() {
			public String getReturnTypeName() {
				return "EList";
			}

			public String getMethodName() {
				return "getDerivedB";
			}
		});
		
		rootImplAccessors.add(new Signature() {
			public String getReturnTypeName() {
				return "ASTList";
			}

			public String getMethodName() {
				return "getderivedBs";
			}
		});
		
		rootImplAccessors.add(new Signature() {
			public String getReturnTypeName() {
				return "EList";
			}

			public String getMethodName() {
				return "getContainmentD";
			}
		});
		
		rootImplAccessors.add(new Signature() {
			public String getReturnTypeName() {
				return "ASTList";
			}

			public String getMethodName() {
				return "getcontainmentDs";
			}
		});

	}

	private RootImpl root;

	@Before
	public void setUp() throws Exception {
		root = new RootImpl();
		root.getContainmentD().add(new DImpl());
		root.setMyA(new AImpl("MyA"));
	}

	@Test
	public void testAPI() throws Exception {
		for (Signature signature : rootImplAccessors) {
			Method accessor = null;
			try {
				accessor = RootImpl.class.getMethod(signature.getMethodName());
			} catch (NoSuchMethodException e) {
				Assert.fail("Expected accessor " + signature.getMethodName()
						+ " was not found.");
			}
			if (accessor != null) {
				Assert.assertTrue(
						"Expected return type name "
								+ signature.getReturnTypeName() + ", but was "
								+ accessor.getReturnType().getSimpleName()
								+ ".",
						signature.getReturnTypeName().equals(
								accessor.getReturnType().getSimpleName()));
			}
		}

	}

	
	@Test
	public void testNumChildren() throws Exception {
		Iterator<EObject> it = root.eAllContents();
		EObject o = root;
		do{
			Assert.assertTrue("Each node in this model should be an ASTNode.",o instanceof ASTNode);
			ASTNode node = (ASTNode)o;
			Assert.assertTrue("Child number should be the same as defined in the EClass.",node.getNumChild()==node.eClass().getEAllContainments().size());
			o = it.hasNext()?it.next():null;
		}
		while(o!=null);
	}
	
	@Test
	public void testRef() throws Exception {
		DImpl d = (DImpl) root.getContainmentD().get(0);
		Assert.assertTrue("Name was '"+ d.getRefToSomeA().getName() +"' exptected 'MyA'.","MyA".equals(d.getRefToSomeA().getName()));
	}
}

