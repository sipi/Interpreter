/**
 * @author Clément Sipieter <csipieter@gmail.com>
 */
package pi.interpreter.commands;

import pi.interpreter.Environment;

public class Pwd implements Command
{

  private static final String LABEL = "pwd";

  @Override
  public String getLabel()
  {
    return LABEL;
  }

  @Override
  public int exec(String[] args, Environment env)
  {
    env.out.println(env.get("_pwd").toString());
    return Command.EXIT_SUCCESS;
  }

  @Override
  public String manual()
  {
    return "Syntaxe : pwd";
  }
}