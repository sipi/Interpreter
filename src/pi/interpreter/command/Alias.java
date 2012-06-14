package pi.interpreter.command;

import java.util.Arrays;

import pi.interpreter.Command;
import pi.interpreter.CommandProcessor;
import pi.interpreter.Displayer;
import pi.interpreter.Interpreter;

public class Alias implements Command
{
  private static final String LABEL = "alias";
  private Interpreter _interpreter;

  public Alias(Interpreter interpreter)
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
      
    _interpreter.addAliases(cmd, Arrays.copyOfRange(args, 2, args.length));
    return CommandProcessor.EXIT_SUCCESS;
  }
  
  public String manual()
  {
    return "alias cmd_name alias [alias]...";
  }

}
