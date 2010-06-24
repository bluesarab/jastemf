package exampleprogs;

import statemachine.ast.Opt;
import statemachine.impl.ActionImpl;
import statemachine.impl.NormalStateImpl;
import statemachine.impl.StateImpl;
import statemachine.impl.StateMachineImpl;
import statemachine.impl.TransitionImpl;

public class MainProgram {

	public static void main(String[] args) {
		// Construct the AST
		StateMachineImpl m = new StateMachineImpl();
		m.adddeclarations(new NormalStateImpl("S1", new Opt<ActionImpl>(null)));
		m.adddeclarations(new NormalStateImpl("S2", new Opt<ActionImpl>(null)));
		m.adddeclarations(new NormalStateImpl("S3", new Opt<ActionImpl>(null)));
		m.adddeclarations(new TransitionImpl("a", "S1", "S2", "", ""));
		m.adddeclarations(new TransitionImpl("b", "S2", "S1", "", ""));
		TransitionImpl transition = new TransitionImpl("a", "S2", "S3", "a+b", "Write t;");
		m.adddeclarations(transition);

		
		// Print reachable information for all states
		m.printReachable();
		
		System.out.println("Parsed transition Action: " + transition.getActionStatement());
		System.out.println("Parsed transiton Guard: " + transition.getGuardExpression());
	}

}
