package exampleprogs;
import statemachine.impl.*;

public class MainProgram {

	public static void main(String[] args) {
	    // Construct the AST
		StateMachineImpl m = new StateMachineImpl(); 
		m.adddeclarations(new StateImpl("S1")); 
		m.adddeclarations(new StateImpl("S2")); 
		m.adddeclarations(new StateImpl("S3")); 
		m.adddeclarations(new TransitionImpl("a", "S1", "S2")); 
		m.adddeclarations(new TransitionImpl("b", "S2", "S1")); 
		m.adddeclarations(new TransitionImpl("a", "S2", "S3")); 

	    // Print reachable information for all states
	    m.printReachable();

	}

}
