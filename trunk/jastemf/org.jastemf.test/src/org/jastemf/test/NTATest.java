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
import org.jastemf.test.test.ntas.impl.AImpl;
import org.jastemf.test.test.ntas.impl.BImpl;
import org.jastemf.test.test.ntas.impl.CImpl;
import org.jastemf.test.test.ntas.impl.RootImpl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NTATest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testBasicNTAFunction() throws Exception {
		RootImpl root = new RootImpl();

		CImpl someC = new CImpl("");
		root.setContainmentC(someC);

		AImpl derivedA = root.getDerivedA();
		Assert.assertTrue("Value of derivedA should not be null.",
				derivedA != null);
		Assert.assertTrue("AImpl should have name 'test'.",
				"test".equals(derivedA.getName()));

		EList<BImpl> derivedB1 = root.getDerivedB();
		Assert.assertTrue(
				"Value of derivedB should contain exactly one 'B' entry, currently it has "
						+ derivedB1.size() + " entries.", derivedB1.size() == 1);

		ASTList<BImpl> derivedB2 = root.getderivedBs();
		Assert.assertTrue(
				"Value of getDerivedB and getderivedBs should use the same EList.",
				derivedB1 == derivedB2.delegatee);
	}

	@Test
	public void testNTAAcess() throws Exception {
		RootImpl root = new RootImpl();

		CImpl someC = new CImpl("");
		root.setContainmentC(someC);

		AImpl derivedA1 = (AImpl) root.getChild(3);
		Assert.assertNotNull("A call to getChild() which refers to a 1..1 NTA should return a non null value in the current implementation.",derivedA1);

		ASTList derivedB1 = (ASTList) root.getChild(4);
		Assert.assertTrue(derivedB1.getNumChild() == 0);

		ASTList derivedB2 = root.getderivedBs();

		ASTList derivedB3 = (ASTList) root.getChild(4);
		Assert.assertTrue(
				"A call to getChild() which refers to a List NTA should return the same ASTList as the direct call before.",
				derivedB3 == derivedB2);
	}

}
