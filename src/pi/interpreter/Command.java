package pi.interpreter;

public interface Command
{
  public static final int EXIT_SUCCESS = 0;
  public static final int EXIT_FAILURE = -1;
  
  public String getLabel();
  public int exec(String[] args, Environment env);
  public String manual();
};
