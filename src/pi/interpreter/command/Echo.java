package pi.interpreter.command;

import pi.interpreter.Command;
import pi.interpreter.CommandProcessor;
import pi.interpreter.Displayer;

public class Echo implements Command
{
  public String getLabel()
  {
    return "echo";
  }
  public int exec(String[] args, Displayer displayer)
  {
    for(int i = 1; i < args.length; ++i)
      displayer.out.print(args[i]);
      
    
    displayer.out.println();
    return CommandProcessor.EXIT_SUCCESS;
  }

}
