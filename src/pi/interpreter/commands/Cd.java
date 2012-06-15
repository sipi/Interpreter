/**
 * @author Clément Sipieter <csipieter@gmail.com>
 */
package pi.interpreter.commands;

import java.io.File;
import java.io.IOException;

import pi.interpreter.Environment;

public class Cd implements Command
{

  private static final String LABEL = "cd";

  @Override
  public String getLabel()
  {
    return LABEL;
  }

  @Override
  public int exec(String[] args, Environment env)
  {
    File dir;
    String dir_name = env.get("_pwd").toString();

    if (args.length > 1)
      if (args[1].length() > 0)
        if (args[1].charAt(0) != '-')
          {
            if (args[1].charAt(0) == '/')
              dir_name = args[1];
            else
              dir_name += args[1];
          }

    dir = new File(dir_name);
    if (dir.isDirectory())
      {
        try{
          env.set("_pwd", dir.getCanonicalPath() + "/");
        }catch(IOException e){
          return Command.EXIT_FAILURE;
        }
      }
    else
      {
        env.err.println("cd: dir_name is not a directory");
        return Command.EXIT_FAILURE;
      }

    return Command.EXIT_SUCCESS;
  }

  @Override
  public String manual()
  {
    return "Syntaxe : cd <dir_path>";
  }
}
