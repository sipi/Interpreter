package pi.interpreter.commands;

import java.util.Collection;
import pi.interpreter.Environment;
import pi.interpreter.CommandProcessor;

public class Help implements Command
{
  private CommandProcessor _processor;
  public static final String LABEL = "help";

  public Help(CommandProcessor processor)
  {
    _processor = processor;
  }

  public String getLabel()
  {
    return LABEL;
  }

  public int exec(String args[], Environment env)
  {
    if (args.length != 1)
      return EXIT_FAILURE;
    env.out.println("Commands :");
    Collection<Command> cmds = _processor.getCommands();
    for (Command c : cmds)
      {
        env.out.print("    ");
        env.out.println(c.getLabel());
      }
    return EXIT_SUCCESS;
  }

  public String manual()
  {
    return "Syntaxe : help";
  }


}
