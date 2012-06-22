package pi.interpreter.commands;

import java.util.Arrays;

import pi.interpreter.Environment;
import pi.interpreter.Interpreter;

public class Alias implements Command
{
  public static final String LABEL		= "alias";
  public static final String SHORT_DESC	= "creates an alias";
  public static final String SYNTAX		= "<cmd_name> <alias> [<alias> ...]";
  private Interpreter _interpreter;

  public Alias(Interpreter interpreter)
  {
    _interpreter = interpreter;
  }

  
  public String getLabel()
  {
    return LABEL;
  }
  
  public int exec(String[] args, Environment env)
  {
    Command cmd = _interpreter.getCmd(args[1]);
    if (cmd == null)
      {
        env.out.println(args[1] + " : command not found");
        return Command.EXIT_FAILURE;
      }
      
    _interpreter.addAliases(cmd, Arrays.copyOfRange(args, 2, args.length));
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
		return SYNTAX_KEYWORD + getLabel() + " " + SYNTAX;
	}

}
