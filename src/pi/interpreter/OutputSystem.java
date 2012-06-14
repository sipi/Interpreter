package pi.interpreter;


import java.io.OutputStream;
import java.io.PrintStream;

public class OutputSystem extends PrintStream implements Output
{
  public OutputSystem(OutputStream output_stream)
  {
    super(output_stream);
  }
}
