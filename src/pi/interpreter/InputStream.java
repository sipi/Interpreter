package pi.interpreter;

import java.util.Scanner;

public class InputStream implements Input
{
  private Scanner _scan;
  
  public InputStream(InputStream in)
  {
    _scan = new Scanner(in);
  }

  @Override
  public String readLine()
  {
    return _scan.nextLine();
  }
  
};

