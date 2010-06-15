package siple.semantics;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import siple.semantics.ast.*;
import siple.semantics.interfaces.*;

/**
 * Simple dialog used throughout interpretation to ask the user for
 * a new value for a certain variable, such that a SIPLE <tt>Read</tt>
 * statement can be processed.
 * @author C. Bürger
 */
public class ReadDialog {
	private static Object result = null;
	
	/**
	 * Open a new input dialog for a SIPLE <tt>Read</tt> statement. The dialog
	 * forces the user to enter a valid value of a certain type. The value will
	 * be stored in a certain variable by the interpreter.
	 * @param toRead The variable for which the user must enter a new value.
	 * @param vmOutput The interpreter's, so far throughout program execution
	 * produced, output.
	 * @return A valid value for the given variable.
	 */
	public static Object execute(VariableDeclaration toRead, String vmOutput) {
		result = null;
		new ReadDialog(toRead, vmOutput);
		while(result == null) {}
		return result;
	}
	
	private ReadDialog(final VariableDeclaration toRead, String vmOutput) {
		final JFrame dialog = new JFrame("Read Input");
		
		dialog.setUndecorated(true);
		dialog.setAlwaysOnTop(true);
		dialog.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		dialog.setLayout(new FlowLayout());
		dialog.setSize(600, 400);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		dialog.setLocation((dimension.width - dialog.getSize().width ) / 2,
				(dimension.height - dialog.getSize().height) / 3);
		
		final JLabel description = new JLabel(
				"Read ["+ toRead.getName() +":"+ toRead.Type() +"].");
		dialog.add(description);
		final JTextField input = new JTextField(25);
		dialog.add(input);
		final JTextArea console = new JTextArea(vmOutput, 20, 50);
		dialog.add(console);
		JButton submit = new JButton("Submit");
		dialog.add(submit);
		submit.addActionListener(new ActionListener() {
			private int retries = 0;
			public void actionPerformed(ActionEvent e) {
				String inputText = input.getText();
				if (inputText == null || inputText.length() == 0)
					return;
				Constant inputValue = new Constant(inputText);
				switch (toRead.Type()) {
				case Boolean:
					result = inputValue.Type() == Type.Boolean ?
							inputValue.AsBoolean() : null;
					break;
				case Real:
					result = inputValue.Type() == Type.Real ?
							inputValue.AsReal() : null;
					break;
				case Integer:
					result = inputValue.Type() == Type.Integer ?
							inputValue.AsInteger() : null;
					break;
				}
				if (result != null)
					dialog.dispose();
				else description.setText("Read ["+ toRead.getName() +":"+
						toRead.Type() +"]; "+ ++retries +"'th retry.");
			}
		});
		
		dialog.validate();
		dialog.setVisible(true); 
		dialog.toFront();
	}
}
