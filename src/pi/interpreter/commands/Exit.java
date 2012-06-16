package pi.interpreter.commands;

import pi.interpreter.Environment;
import pi.interpreter.Interpreter;

public class Exit implements Command
{
  public static final String LABEL = "exit";
  public static final String SHORT_DESC	= "exit the interpreter";
  public static final String SYNTAX	= "";
  private Interpreter _interpreter;

  public Exit(Interpreter interpreter)
  {
    _interpreter = interpreter;
  }

  public String getLabel()
  {
    return LABEL;
  }

  public int exec(String[] args, Environment env)
  {
    _interpreter.exit();
    return Command.EXIT_SUCCESS;
  }

  public String manual()
  {
    return syntax();
  }

	public String shortDescription() {
		return SHORT_DESC;
	}

	public String syntax() {
		return SYNTAX_KEYWORD + SYNTAX;
	}
}
