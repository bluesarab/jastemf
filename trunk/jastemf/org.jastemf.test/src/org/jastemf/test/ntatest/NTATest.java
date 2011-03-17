package org.jastemf.test.ntatest;

import static org.junit.Assert.*;


import org.jastemf.test.ntatest.impl.AImpl;
import org.jastemf.test.ntatest.impl.RootImpl;
import org.junit.Before;
import org.junit.Test;



public class NTATest {

	//Could be better tested with RTT
	private static String derivedANameJastAdd = "jastadd_getDerivedA";
	private static String derivedANameEMF = "getDerivedA";
	private static String derivedAReturnTypeJastAdd = "AImpl";
	private static String derivedAReturnTypeEMF = "A";
	
	private RootImpl root;
	
	@Before
	public void setUp() throws Exception {
		root = new RootImpl();
	}
	
	@Test
	public void testNTAAPI() throws Exception{
		
	}
	
	
	@Test
	public void testNTAFunction() throws Exception {
		//AImpl derivedA = root.getDerivedA();
		//AImpl derivedAImpl = root.getderivedA();
	}
}
