package pi.interpreter;

import java.util.Scanner;

public class InputStream implements Input
{
  private Scanner _scan;
  
  public InputStream(java.io.InputStream in)
  {
    _scan = new Scanner(in);
  }

  @Override
  public String readLine()
  {
    return _scan.nextLine();
  }
  
};

