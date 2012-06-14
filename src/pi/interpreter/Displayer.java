package pi.interpreter;

import java.io.PrintStream;

public class Displayer
{
  public final Output out;
  public final Output err;
  
  public Displayer(Output out, Output err)
  {
    this.out = out;
    this.err = err;
  }
  
  public Displayer(PrintStream out, PrintStream err)
  {
    this.out = new OutputSystem(out);
    this.err = new OutputSystem(err);
  }
}
