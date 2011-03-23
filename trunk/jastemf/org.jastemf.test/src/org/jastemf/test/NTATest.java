package org.jastemf.test;

import static org.junit.Assert.*;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.jastemf.test.ast.ASTList;
import org.jastemf.test.test.ntas.impl.AImpl;
import org.jastemf.test.test.ntas.impl.BImpl;
import org.jastemf.test.test.ntas.impl.CImpl;
import org.jastemf.test.test.ntas.impl.RootImpl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NTATest {

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
	public void testNTAFunction() throws Exception {
		CImpl someC = new CImpl("");
		root.setContainmentC(someC);
		
		AImpl derivedA = root.getDerivedA();
		Assert.assertTrue("Value of derivedA should not be null.",derivedA!=null);
		Assert.assertTrue("AImpl should have name 'test'.","test".equals(derivedA.getName()));
		
		EList<BImpl> derivedB1 = root.getDerivedB();
		Assert.assertTrue("Value of derivedB should contain exactly one 'B' entry, currently it has " + derivedB1.size() +  " entries.",derivedB1.size()==1);

		ASTList<BImpl> derivedB2 = root.getderivedBs();
		Assert.assertTrue("Value of getDerivedB and getderivedBs should use the same EList.",derivedB1==derivedB2.delegatee);

	
	}
	
	
}
