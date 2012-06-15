package pi.interpreter.commands;

import java.util.Arrays;

import pi.interpreter.Environment;
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
  
  public int exec(String[] args, Environment env)
  {
    Command cmd = _interpreter.getCmd(args[1]);
    if (cmd == null)
      {
        env.out.println(args[1] + " : command not found");
        return Command.EXIT_FAILURE;
      }
      
    _interpreter.addAliases(cmd, Arrays.copyOfRange(args, 2, args.length));
    return Command.EXIT_SUCCESS;
  }
  
  public String manual()
  {
    return "Syntax : alias <cmd_name> <alias> [<alias>]...";
  }

}
