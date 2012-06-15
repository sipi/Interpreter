package pi.interpreter;

import java.util.Arrays;
import java.util.Map;
import java.util.Collection;
import ori.machine.StateMachine;
import pi.interpreter.commands.Command;

public class CommandProcessor
{
  private static final String ERR_MSG = "Failure : ";
  
  private Map<String, Command> _dictionary;

  // *************************************************************************
  // CONSTRUCTORS
  // *************************************************************************

  public CommandProcessor()
  {
    _dictionary = new StateMachine<Command>();
  }

  public CommandProcessor(Map<String, Command> dictionary)
  {
    _dictionary = dictionary;
  }

  public Collection<Command> getCommands() {
	return _dictionary.values();
  }

  // *************************************************************************
  // METHODS
  // *************************************************************************

  public int exec(String cmd_line, Environment env)
  {
    String[] args = cmd_line.split("\\s");
    if(args.length == 0 || (args.length == 1  && args[0].length() == 0))
      return Command.EXIT_SUCCESS;
    
    if(args[0].length() == 0)
      args = Arrays.copyOfRange(args, 1, args.length);
    
    //@TODO do that before split
    Object tmp;
    for(int i = 0; i < args.length; ++i)
      if(args[i].length() > 0 && args[i].charAt(0) == '$')
        {
          tmp = env.get(args[i].substring(1));
          args[i] = (tmp != null)? tmp.toString() : "";
        }
    
    Command cmd = this.getCmd(args[0]);
    if (cmd == null)
      {
        env.out.println(args[0] + " : command not found");
        return Command.EXIT_FAILURE;
      }
    
    int return_val = cmd.exec(args, env);
    if(return_val < 0)
      env.err.println(ERR_MSG + return_val);
    
    return return_val;
  }
  
  public void addCmd(Command cmd)
  {
    this.addCmd(cmd, null);
  }
  
  public void addCmd(Command cmd, String[] aliases)
  {
    _dictionary.put(cmd.getLabel(), cmd);
    this.addAliases(cmd, aliases);
  }
  
  public void addAliases(Command cmd, String[] aliases)
  {
    if(aliases != null)
      for(String alias: aliases)
        _dictionary.put(alias, cmd);
  }
  
  public Command rmCmd(String cmd_name)
  {
    return _dictionary.remove(cmd_name);
  }

  public Command getCmd(String cmd_name)
  {
    return _dictionary.get(cmd_name);
  }
};
