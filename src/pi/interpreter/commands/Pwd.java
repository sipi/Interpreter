/**
 * @author Clément Sipieter <csipieter@gmail.com>
 */
package pi.interpreter.commands;

import pi.interpreter.Environment;

public class Pwd implements Command
{

  private static final String LABEL = "pwd";
  private static final String SYNTAX = "";
  private static final String SHORT_DESC = "";

  @Override
  public String getLabel()
  {
    return LABEL;
  }

  @Override
  public int exec(String[] args, Environment env)
  {
    env.out.println(env.get(Environment.PWD_KEY).toString());
    return Command.EXIT_SUCCESS;
  }

  @Override
  public String manual()
  {
    return syntax();
  }

	public String syntax() {
		return SYNTAX_KEYWORD + getLabel() + " " +SYNTAX;
	}

	public String shortDescription() {
		return SHORT_DESC;
	}
}
