package pi.interpreter.commands;

import pi.interpreter.Environment;
import pi.interpreter.Interpreter;

public class Man implements Command
{

  public static final String LABEL = "man";
  public static final String SHORT_DESC	= "open a command manual";
  public static final String SYNTAX = "<command_name>";
  private Interpreter _interpreter;

  public Man(Interpreter interpreter)
  {
    _interpreter = interpreter;
  }

  public String getLabel()
  {
    return LABEL;
  }

  public int exec(String[] args, Environment env)
  {
    if(args.length < 2)
      {
        env.out.println(this.manual());
        return 1;
      }
    
    Command cmd = _interpreter.getCmd(args[1]);
    if (cmd == null)
      {
        env.out.println(args[1] + " : command not found");
        return Command.EXIT_FAILURE;
      }

    env.out.println(cmd.manual());
    return Command.EXIT_SUCCESS;
  }

  public String manual()
  {
    return syntax();
  }

	public String syntax() {
		return SYNTAX_KEYWORD + getLabel() + SYNTAX;
	}
	public String shortDescription() {
		return SHORT_DESC;
	}
}
