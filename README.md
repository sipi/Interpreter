Interpreter
===========

Generic interpreter for java


Simple example
--------------

code:
<pre>
import pi.interpreter.Environment;
import pi.interpreter.Interpreter;
import pi.interpreter.commands.Command;

public class Main {
	
	private static class MyCmd implements Command {

		private static final String LABEL = "mycmd";
		
		@Override
		public String getLabel() {
			return LABEL;
		}

		@Override
		public int exec(String[] args, Environment env) {
			env.out.println("Hello world !");
			return 0;
		}

		@Override
		public String manual() {
			return "This is a man page.";
		}

		@Override
		public String syntax() {
			return "This is command syntax description";
		}

		@Override
		public String shortDescription() {
			return "This is a short description of this command";
		}
		
	};
	
	public static void main(String[] args) {
		Interpreter ui = new Interpreter();
		ui.setBanner("    Hello world !\n" +
					 "=======================================");
		ui.addCmd(new MyCmd());
		ui.start();
	}
	
};
</pre>

result:
<pre>
   Hello world !
=======================================
> mycmd
Hello world !
> man mycmd
This is a man page
> help
Commands :
   alias       creates an alias
   cd          change current directory
   echo        print into the output
   env         display environment variables
   exit        exit the interpreter
   help        displays help
   let         set the value of an environment variable
   ls          display current directory
   man         open a command manual
   pwd         
   mycmd       This is a short description of this command
> 
</pre>
