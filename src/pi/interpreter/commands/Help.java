package pi.interpreter.commands;

import java.util.Collection;
import java.util.TreeSet;
import pi.interpreter.Environment;
import pi.interpreter.CommandProcessor;

public class Help implements Command {
	
	public static final String LABEL = "help";

	public Help(CommandProcessor processor) {
		_processor = processor;
	}

	public String getLabel() {
		return LABEL;
	}

	public String manual() {
		return LABEL;
	}

	public int exec(String args[], Environment env) {
		if (args.length != 1)
			return EXIT_FAILURE;
		env.out.println("Commands :");
		Collection<Command> cmds = _processor.getCommands();
		TreeSet<String> added = new TreeSet<String>();
		for (Command c : cmds) {
			if (!added.contains(c.getLabel())) {
				env.out.print("    ");
				env.out.println(c.getLabel());
				added.add(c.getLabel());
			}
		}
		return EXIT_SUCCESS;
	}

	private CommandProcessor _processor;

}

