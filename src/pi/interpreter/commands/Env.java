package pi.interpreter.commands;

import pi.interpreter.Environment;
import java.util.Set;
import java.util.Map;

public class Env implements Command
{
  public static final String LABEL = "env";
  public static final String SHORT_DESC	= "display environment variables";
  public static final String SYNTAX	= "";

  public String getLabel()
  {
    return LABEL;
  }
  
  private static final int LEFT_SPACES = 3;
  private static final int MAX_SIZE = 20;
  public static final String NO_STRING_VALUE = "not a string value";

  public int exec(String[] args, Environment env)
  {
	int i;
	Set<Map.Entry<String,Object> > entries = env.entrySet();
	for (Map.Entry<String,Object> pair : entries) {
		for (i = 0 ; i < LEFT_SPACES ; i++)
			env.out.print(' ');
		env.out.print(pair.getKey());
		for (i = pair.getKey().length() ; i < MAX_SIZE ; i++)
			env.out.print(' ');
		if (pair.getValue() instanceof String)
			env.out.println(pair.getValue());
		else
			env.out.println(NO_STRING_VALUE);
	}
    return EXIT_SUCCESS;
  }
  
  public String manual()
  {
  	return syntax();
  }

	public String shortDescription() {
		return SHORT_DESC;
	}
	
	public String syntax() {
		return SYNTAX_KEYWORD + getLabel() + " " + SYNTAX;
	}
}
