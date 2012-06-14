package pi.interpreter.command;

import pi.interpreter.Command;
import pi.interpreter.CommandProcessor;
import pi.interpreter.Displayer;
import pi.interpreter.Interpreter;

public class Man implements Command
{

  private static final String LABEL = "man";
  private Interpreter _interpreter;

  public Man(Interpreter interpreter)
  {
    _interpreter = interpreter;
  }

  public String getLabel()
  {
    return LABEL;
  }

  public int exec(String[] args, Displayer displayer)
  {
    Command cmd = _interpreter.getCmd(args[1]);
    if (cmd == null)
      {
        displayer.out.println(args[1] + " : command not found");
        return CommandProcessor.EXIT_FAILURE;
      }

    displayer.out.println(cmd.manual());
    return CommandProcessor.EXIT_SUCCESS;
  }

  public String manual()
  {
    return "";
  }
}
