package pi.interpreter.commands;

import pi.interpreter.Environment;

public interface Command
{
  public static final int EXIT_SUCCESS = 0;
  public static final int EXIT_FAILURE = -1;
  
  public String getLabel();
  public int exec(String[] args, Environment env);
  public String manual();
  public String syntax();
  public String shortDescription();
};
