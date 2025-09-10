package prolang.runtime.internal;

import java.util.HashSet;
import java.util.Set;

public class Command {
    
    public final Set<String> commands = new HashSet<String>();
    
    public Command(){
        commands.add("HALT");
        commands.add("MOVE");
        commands.add("COPY");
        commands.add("ADD");
        commands.add("SUBTRACT");
        commands.add("COMPARE");
        commands.add("JUMP");
        commands.add("JUMPIF");
        commands.add("SYSCALL");
    }
}