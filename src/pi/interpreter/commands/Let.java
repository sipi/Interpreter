package pi.interpreter.commands;

import pi.interpreter.Environment;

public class Let implements Command
{
  public static final String LABEL = "let";
  public static final String SHORT_DESC	= "set the value of an environment variable";
  public static final String SYNTAX	= "<variable_name> <value>";

  public String getLabel()
  {
    return LABEL;
  }
  
  public int exec(String[] args, Environment env)
  {
    env.set(args[1], args[2]);
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
