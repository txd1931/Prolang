package prolang.runtime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.Scanner;

import prolang.runtime.internal.ProcessConfig;
import prolang.runtime.internal.ProcessStatus;
import prolang.runtime.internal.Command;
import prolang.runtime.internal.ExecutionProtocol;
import prolang.runtime.SourceLoader;

public class Interpreter {
    private ProcessStatus processStatus = null;

    private Scanner scanner = new Scanner(System.in);
    
    private HashMap<String, Byte> registers = new HashMap<>();
    private int statementCounter = 0;
    private int pointerToCurrentStatement = -1;
    private ExecutionProtocol executionProtocol = null;
    
    private long invokeTime = 0;

    private long startTime = 0;
    private long lastTime = 0;
    private long currentTime = 0;
    private long deltaTime = 0;
    private int exitCode;
    
    public Interpreter(String[] args){
        processStatus = ProcessStatus.INITIALIZING;
        invokeTime = System.currentTimeMillis();
        executionProtocol = new ExecutionProtocol(args[0], 
            ProcessConfig.valueOf(args[1]), 
            Long.parseLong(args[2]), 
            Integer.parseInt(args[3]));
        initRegs(this);
        mainLoop();
    }

    private static void initRegs(Interpreter instance){
        instance.registers.put("reg1", (byte)0);
        instance.registers.put("reg2", (byte)0);
        instance.registers.put("reg3", (byte)0);
        instance.registers.put("pc",   (byte)0);
        instance.registers.put("ir",   (byte)0);
        instance.registers.put("sr",   (byte)0);
    }

    public String getStatus(){
        return String.valueOf(processStatus);
    }

    public long timeSinceInvoked(){
        return System.currentTimeMillis() - invokeTime;
    }

    public long timeRunning(){
        return System.currentTimeMillis() - startTime; 
    }
    

    private void mainLoop(){
        startTime = System.currentTimeMillis();
        
        while(executionProtocol.timeLimit >= timeSinceInvoked() && executionProtocol.commandCounterLimit >= statementCounter && processStatus == ProcessStatus.RUNNING_INACTIVE){
        	
        	currentTime = System.currentTimeMillis();
            deltaTime = currentTime - lastTime;
            lastTime = currentTime;
            
            processStatus = ProcessStatus.RUNNING_ACTIVE;
            if(processStatus == ProcessStatus.RUNNING_ACTIVE)
            processStatus = ProcessStatus.RUNNING_INACTIVE;
            
            statementCounter++;
        }
        terminateProcess();
    }

    private void terminateProcess(){
        System.out.println("Terminating interpreter");
    }
    
    private void parseStatement() {
    	
    }

    private void commandHalt(){
        processStatus = ProcessStatus.TERMINATING;
    }
}
