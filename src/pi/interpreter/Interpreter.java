package pi.interpreter;

public class Interpreter
{

  // *************************************************************************
  // ATTRIBUTS
  // *************************************************************************

  private Displayer _displayer;
  private Input _in;

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
    _displayer = new Displayer(System.out, System.err);
    _in = new InputSystem(System.in);
  }

  // *************************************************************************
  // METHODS
  // *************************************************************************

  public void start()
  {
    _exit = false;

    this._displayer.out.println(_banner);

    while (!_exit)
      {
        this._displayer.out.print(_prompt);
        _cmd_processor.exec(_in.readLine(), _displayer);
      }
  }

  public void stop()
  {
    _exit = true;
  }
  
  
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

  public void setDisplayer(Displayer displayer)
  {
    this._displayer = displayer;
  }

  public void setIn(Input in)
  {
    this._in = in;
  }

  public void setPrompt(String prompt)
  {
    this._prompt = prompt;
  }

};

