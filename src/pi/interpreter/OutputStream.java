package pi.interpreter;

import java.io.PrintStream;

public class OutputStream extends PrintStream implements Output
{
  public OutputStream(java.io.OutputStream output_stream)
  {
    super(output_stream);
  }
};

