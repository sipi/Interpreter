/**
 * @author Clément Sipieter <csipieter@gmail.com>
 */
package pi.interpreter;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class Environment
{
	public static final String PWD_KEY = "PWD";
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
    try
      {
        this.set(PWD_KEY, (new File(".")).getCanonicalPath());
      }
    catch (IOException e)
      {
      }
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
    Object o = _map.get(var);
    return (o != null)? o : "";
  }

	public Set<String> keySet() {
		return _map.keySet();
	}

	public Collection<Object> values() {
		return _map.values();
	}

	public Set<Map.Entry<String,Object> > entrySet() {
		return _map.entrySet();
	}
  
}
