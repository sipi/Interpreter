package pi.interpreter.commands;

import pi.interpreter.Environment;
import pi.interpreter.Interpreter;

public class Exit implements Command
{
  private static final String LABEL = "exit";
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
    return "Syntax : exit";
  }
}
