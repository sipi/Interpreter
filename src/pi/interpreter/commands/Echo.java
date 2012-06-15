package pi.interpreter.commands;

import pi.interpreter.Environment;

public class Echo implements Command
{
	public static final String LABEL = "echo";

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
    return "Syntaxe : echo [<param>]...";
  }

};
