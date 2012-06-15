package pi.interpreter;

import java.util.Map;
import java.util.TreeMap;

public class Environment
{
  public final Output out;
  public final Output err;
  public final Input in;

  protected int return_value;
  
  private Map<String, Object> _map;

  public Environment(Input in, Output out, Output err)
  {
    this.in = in;
    this.out = out;
    this.err = err;
    this.init();
  }

  public Environment(java.io.InputStream in, java.io.OutputStream out,
      java.io.OutputStream err)
  {
    this.in = new InputStream(in);
    this.out = new OutputStream(out);
    this.err = new OutputStream(err);
    this.init();
  }

  private void init()
  {
    _map = new TreeMap<String, Object>();
  }
  
  // *************************************************************************
  //  METHODS
  // *************************************************************************
  
  public void set(String var, Object val)
  {
    _map.put(var, val);
  }
  
  public Object get(String var)
  {
    return _map.get(var);
  }
}
