package pi.interpreter.commands;

import pi.interpreter.Environment;

public class Echo implements Command
{
	public static final String LABEL = "echo";
  public static final String SHORT_DESC	= "print into the output";
  public static final String SYNTAX	= "[<text>]";

  public String getLabel()
  {
    return LABEL;
  }
  
  public int exec(String[] args, Environment env)
  {
    for(int i = 1; i < args.length; ++i)
        env.out.print(((i>1)? " " : "") + args[i]);  
    
    env.out.println();
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
};
