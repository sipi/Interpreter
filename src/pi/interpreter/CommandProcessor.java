package pi.interpreter;

import java.util.Map;
import ori.machine.StateMachine;
import pi.interpreter.commands.Command;

public class CommandProcessor
{
 
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

  // *************************************************************************
  // METHODS
  // *************************************************************************

  public int exec(String cmd_line, Environment env)
  {
    String[] args = cmd_line.split("\\s");
    
    //@TODO do that before split
    Object tmp;
    for(int i = 0; i < args.length; ++i)
      if(args[i].charAt(0) == '$')
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
    
    return cmd.exec(args, env);
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
