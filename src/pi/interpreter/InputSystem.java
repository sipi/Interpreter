package pi.interpreter;

import java.io.InputStream;
import java.util.Scanner;

public class InputSystem implements Input
{
  private Scanner _scan;
  
  public InputSystem(InputStream in)
  {
    _scan = new Scanner(in);
  }

  @Override
  public String readLine()
  {
    return _scan.nextLine();
  }
  
  
}
