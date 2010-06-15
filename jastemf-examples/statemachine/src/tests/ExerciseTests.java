package tests;

import junit.framework.TestCase;
import statemachine.ast.Opt;
import statemachine.impl.ActionExpressionImpl;
import statemachine.impl.ActionImpl;
import statemachine.impl.DeclarationImpl;
import statemachine.impl.GuardExpressionImpl;
import statemachine.impl.StateImpl;
import statemachine.impl.StateMachineImpl;
import statemachine.impl.TransitionImpl;

public class ExerciseTests extends TestCase {
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

	/*
	 * public void testAltTransitions(){ assertEquals(S1.transitions(),
	 * S1.altTransitions()); assertEquals(S2.transitions(),
	 * S2.altTransitions()); assertEquals(S3.transitions(),
	 * S3.altTransitions()); }
	 */

	/*
	 * public void testAltSuccessors(){ assertEquals(S1.getSuccessors(),
	 * S1.altSuccessors()); assertEquals(S2.getSuccessors(),
	 * S2.altSuccessors()); assertEquals(S3.getSuccessors(),
	 * S3.altSuccessors()); }
	 */

	public void testPredecessors() {
		assertFalse(S1.predecessors().contains(S1));
		assertTrue(S1.predecessors().contains(S2));
		assertFalse(S1.predecessors().contains(S3));
		assertTrue(S2.predecessors().contains(S1));
		assertFalse(S2.predecessors().contains(S2));
		assertFalse(S2.predecessors().contains(S3));
		assertFalse(S3.predecessors().contains(S1));
		assertTrue(S3.predecessors().contains(S2));
		assertFalse(S3.predecessors().contains(S3));
	}
	/*
	 * public void testAltReachable(){ assertEquals(S1.getReachable(),
	 * S1.altReachable()); assertEquals(S2.getReachable(), S2.altReachable());
	 * assertEquals(S3.getReachable(), S3.altReachable()); }
	 */
}
