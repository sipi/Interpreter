package pi.interpreter.commands;

import pi.interpreter.Environment;

public class Let implements Command
{
  private static final String LABEL = "let";

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
    return "Syntax : let <var_name> <value>";
  }

}
