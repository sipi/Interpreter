package pi.interpreter;

public class Displayer
{
  public final Output out;
  public final Output err;
  
  public Displayer(Output out, Output err)
  {
    this.out = out;
    this.err = err;
  }
  
  public Displayer(java.io.OutputStream out, java.io.OutputStream err)
  {
    this.out = new OutputStream(out);
    this.err = new OutputStream(err);
  }
}
