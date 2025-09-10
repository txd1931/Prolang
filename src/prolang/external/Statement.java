package prolang.external;

public class Statement{
    public int line = -1; 
    public Command command = Command._UNDEFINED_;
    public Object[] params = null;
    public Statement(int line, Command command, Object[] params) {
        this.line = line;
        this.command = command;
        this.params = params;
    }
}