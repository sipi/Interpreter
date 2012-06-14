package pi.interpreter;

public interface Command
{
  public String getLabel();
  public int exec(String[] args, Displayer displayer);
  public String manual();
};
