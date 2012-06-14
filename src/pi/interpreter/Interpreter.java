package pi.interpreter;

import pi.interpreter.commands.*;

public class Interpreter
{

  // *************************************************************************
  // ATTRIBUTS
  // *************************************************************************

  public final Environment env;

  private String _banner = "";
  private String _prompt = "> ";
  private boolean _exit;

  private CommandProcessor _cmd_processor;

  // *************************************************************************
  // CONSTRUCTORS
  // *************************************************************************

  public Interpreter()
  {
    _cmd_processor = new CommandProcessor();
    this.env = new Environment(System.in, System.out, System.err);
    this.init();
  }

  public Interpreter(Input in, Output out) {
    _cmd_processor = new CommandProcessor();
    this.env = new Environment(in, out, out);
    this.init();
  }

  public Interpreter(Input in, Output out, Output err) {
  	_cmd_processor = new CommandProcessor();
  	this.env = new Environment(in, out, out);
  	this.init();
  }
  
  private void init()
  {
    this.initCmd();
  }
  
  protected void initCmd()
  {
    this.addCmd(new Alias(this));
    this.addCmd(new Man(this));
    
    this.addCmd(new Echo());
    this.addCmd(new Let());
  }

  // *************************************************************************
  // METHODS
  // *************************************************************************

  public void start()
  {
    _exit = false;

    this.env.out.println(_banner);

    while (!_exit)
      {
        this.env.out.print(_prompt);
        _cmd_processor.exec(this.env.in.readLine(), this.env);
      }
  }

  public void stop()
  {
    _exit = true;
  }
  
  // *************************************************************************
  //  METHODS OF COMMAND PROCESSOR
  // *************************************************************************
  
  public void addCmd(Command cmd)
  {
    _cmd_processor.addCmd(cmd);
  }
  
  public void addCmd(Command cmd, String[] aliases)
  {
    _cmd_processor.addCmd(cmd, aliases);
  }
  
  public void addAliases(Command cmd, String[] aliases)
  {
    _cmd_processor.addAliases(cmd, aliases);
  }
  
  public Command rmCmd(String cmd_name)
  {
    return _cmd_processor.rmCmd(cmd_name);
  }

  public Command getCmd(String cmd_name)
  {
    return _cmd_processor.getCmd(cmd_name);
  }

  // *************************************************************************
  // SETTERS
  // *************************************************************************

  public void setBanner(String banner)
  {
    this._banner = banner;
  }

  public void setPrompt(String prompt)
  {
    this._prompt = prompt;
  }

};

