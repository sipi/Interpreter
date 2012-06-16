package pi.interpreter.commands;

import java.util.Collection;
import java.util.TreeSet;
import pi.interpreter.Environment;
import pi.interpreter.CommandProcessor;

public class Help implements Command {
	
	public static final String LABEL 		= "help";
	public static final String SHORT_DESC	= "displays help";
	public static final String SYNTAX		= "";

	public Help(CommandProcessor processor) {
		_processor = processor;
	}

	public String getLabel() {
		return LABEL;
	}

	public String manual() {
		return syntax();
	}

	public String syntax() {
		return SYNTAX_KEYWORD + SYNTAX;
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
				env.out.print(c.getLabel());
				env.out.print("    ");
				env.out.println(c.shortDescription());
				added.add(c.getLabel());
			}
		}
		return EXIT_SUCCESS;
	}

	public String shortDescription() {
		return SHORT_DESC;
	}

	private CommandProcessor _processor;

}

