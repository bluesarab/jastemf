package tests;

import junit.framework.TestCase;
import statemachine.ast.Opt;
import statemachine.impl.ActionImpl;
import statemachine.impl.DeclarationImpl;
import statemachine.impl.StateImpl;
import statemachine.impl.StateMachineImpl;
import statemachine.impl.TransitionImpl;

public class StateMachineTests extends TestCase {
	StateMachineImpl m;
	StateImpl S1, S2, S3;

	public void setUp() {
		m = new StateMachineImpl();
		m.adddeclarations(new StateImpl("S1", new Opt<ActionImpl>(null)));
		m.adddeclarations(new StateImpl("S2", new Opt<ActionImpl>(null)));
		m.adddeclarations(new StateImpl("S3", new Opt<ActionImpl>(null)));
		m.adddeclarations(new TransitionImpl("a", "S1", "S2", "", ""));
		m.adddeclarations(new TransitionImpl("b", "S2", "S1", "", ""));
		m.adddeclarations(new TransitionImpl("a", "S2", "S3", "", ""));
		DeclarationImpl d = m.getdeclarations(0);
		S1 = d.lookup("S1");
		S2 = d.lookup("S2");
		S3 = d.lookup("S3");
	}

	public void testNameAnalysis() {
		for (DeclarationImpl d : m.getdeclarationsList()) {
			if (d instanceof TransitionImpl) {
				TransitionImpl t = (TransitionImpl) d;
				assertEquals(t.getSourceLabel(), t.getSource().getLabel());
				assertEquals(t.getTargetLabel(), t.getTarget().getLabel());
			}
		}
	}

	public void testSuccessors() {
		assertFalse(S1.getSuccessors().contains(S1));
		assertTrue(S1.getSuccessors().contains(S2));
		assertFalse(S1.getSuccessors().contains(S3));
		assertTrue(S2.getSuccessors().contains(S1));
		assertFalse(S2.getSuccessors().contains(S2));
		assertTrue(S2.getSuccessors().contains(S3));
		assertFalse(S3.getSuccessors().contains(S1));
		assertFalse(S3.getSuccessors().contains(S2));
		assertFalse(S3.getSuccessors().contains(S3));
	}

	public void testReachable() {
		assertTrue(S1.getReachable().contains(S1));
		assertTrue(S1.getReachable().contains(S2));
		assertTrue(S1.getReachable().contains(S3));
		assertTrue(S2.getReachable().contains(S1));
		assertTrue(S2.getReachable().contains(S2));
		assertTrue(S2.getReachable().contains(S3));
		assertFalse(S3.getReachable().contains(S1));
		assertFalse(S3.getReachable().contains(S2));
		assertFalse(S3.getReachable().contains(S3));
	}
}
