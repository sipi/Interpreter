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
		return SYNTAX_KEYWORD + getLabel() + " " + SYNTAX;
	}

	public String shortDescription() {
		return SHORT_DESC;
	}

	public int exec(String args[], Environment env) {
		if (args.length != 1)
			return EXIT_FAILURE;
		int i;
		String label;
		Collection<Command> cmds = _processor.getCommands();
		TreeSet<String> added = new TreeSet<String>();
		env.out.println("Commands :");
		for (Command c : cmds) {
			label = c.getLabel();
			if (!added.contains(label)) {
				for (i = 0 ; i < RIGHT_SPACES ; i++)
					env.out.print(' ');
				env.out.print(label);
				for (i = label.length() ; i < LABEL_MAX_SIZE ; i++)
					env.out.print(' ');
				env.out.println(c.shortDescription());
				added.add(label);
			}
		}
		return EXIT_SUCCESS;
	}

	private static final int LABEL_MAX_SIZE = 12;
	private static final int RIGHT_SPACES	= 3;

	private CommandProcessor _processor;

}

