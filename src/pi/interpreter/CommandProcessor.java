package pi.interpreter;

import java.util.ArrayList;
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

  public Collection<Command> getCommands()
  {
    return _dictionary.values();
  }

  // *************************************************************************
  // METHODS
  // *************************************************************************

  public int exec(String cmd_line, Environment env)
  {
    String[] args = parseCmdLine(cmd_line, env);

    /* stop condition */
    if (args.length == 0 || args[0].length() == 0)
      return Command.EXIT_SUCCESS;

    Command cmd = this.getCmd(args[0]);
    if (cmd == null)
      {
        env.out.println(args[0] + " : command not found");
        return Command.EXIT_FAILURE;
      }

    /* treatment */
    int return_val = cmd.exec(args, env);
    if (return_val < 0)
      env.err.println(ERR_MSG + return_val);

    return return_val;
  }

  /**
   * Split the String on space characters and replace variables by their values
   * 
   * @param cmd_line
   *          the string to treat
   * @param env
   *          the variables environment
   * @return an array of words
   */
  public static String[] parseCmdLine(String cmd_line, Environment env)
  {
    final int SPACE = 0;
    final int IN_WORD = 1;
    final int STRING_SIMPLE_QUOTE = 2;
    final int STRING_QUOTES = 3;
    final int IN_VAR_NAME = 4;

    int current_state;
    ArrayList<String> args = new ArrayList<String>();
    StringBuilder word = new StringBuilder();
    StringBuilder var_name = new StringBuilder();

    char c;
    current_state = SPACE;
    for (int i = 0; i < cmd_line.length(); ++i)
      {
        c = cmd_line.charAt(i);
        switch (current_state)
          {
            case SPACE:
              if (Character.isWhitespace(c))
                ;
              else if (c == '\'')
                current_state = STRING_SIMPLE_QUOTE;
              else if (c == '"')
                current_state = STRING_QUOTES;
              else if (c == '$')
                current_state = IN_VAR_NAME;
              else
                {
                  current_state = IN_WORD;
                  word.append(c);
                }
              break;

            case IN_WORD:
              if (Character.isWhitespace(c))
                {
                  args.add(word.toString());
                  word = new StringBuilder();
                  current_state = SPACE;
                }
              else if (c == '\'')
                current_state = STRING_SIMPLE_QUOTE;
              else if (c == '"')
                current_state = STRING_QUOTES;
              else if (c == '$')
                current_state = IN_VAR_NAME;
              else
                word.append(c);

              break;

            case STRING_SIMPLE_QUOTE:
              if (c == '\'')
                current_state = IN_WORD;
              else
                word.append(c);

              break;

            case STRING_QUOTES:
              if (c == '"')
                current_state = IN_WORD;
              else
                word.append(c);

              break;

            case IN_VAR_NAME:
              if (Character.isWhitespace(c))
                current_state = SPACE;
              else if (c == '$')
                current_state = IN_VAR_NAME;
              else if (c == '\'')
                current_state = STRING_SIMPLE_QUOTE;
              else if (c == '"')
                current_state = STRING_QUOTES;
              else
                {
                  var_name.append(c);
                  break; // do not execute next code
                }

              // append variable value at the current word
              word.append(env.get(var_name.toString()).toString());
              var_name = new StringBuilder();

              break;

          }
      }

    /* final treatment */
    if (current_state == IN_VAR_NAME)
      {
        word.append(env.get(var_name.toString()).toString());
        current_state = IN_WORD;
      }

    if (current_state == IN_WORD)
      args.add(word.toString());

    return args.toArray(new String[args.size()]);
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
    if (aliases != null)
      for (String alias : aliases)
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
