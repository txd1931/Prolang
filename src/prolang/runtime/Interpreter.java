package prolang.runtime;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Scanner;

import prolang.runtime.internal.ProcessConfig;
import prolang.runtime.internal.ProcessStatus;
import prolang.runtime.internal.ExecutionProtocol;
import prolang.runtime.SourceDeserializer;
import prolang.runtime.internal.VirtualHeap;
import prolang.external.Command;
import prolang.external.Statement;
import prolang.external.data.DataType;

public class Interpreter {
    private ProcessStatus processStatus = null;

    private Scanner scanner = new Scanner(System.in);

    private final Statement[] statements;

    private VirtualHeap virtualHeap = new VirtualHeap();
    
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
        statements = SourceDeserializer.statements();

        mainLoop();
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
    
    private Statement getCurrentToken() {
    	if(pointerToCurrentStatement < 0 || pointerToCurrentStatement >= statements.length) {
    		return null;
    	}
    	return statements[pointerToCurrentStatement];
    }

    private void mainLoop(){
        startTime = System.currentTimeMillis();
        
        while(executionProtocol.timeLimit >= timeSinceInvoked() && executionProtocol.statementCounterLimit >= statementCounter && processStatus == ProcessStatus.RUNNING_INACTIVE){
        	
        	currentTime = System.currentTimeMillis();
            deltaTime = currentTime - lastTime;
            lastTime = currentTime;
            
            processStatus = ProcessStatus.RUNNING_ACTIVE;
            parseStatement(getCurrentToken());
            processStatus = ProcessStatus.RUNNING_INACTIVE;
            
            statementCounter++;
        }
        processStatus = ProcessStatus.TERMINATING;
        terminateProcess();
    }

    private void terminateProcess(){
        System.out.println("Terminating interpreter");
    }
    
    private void parseStatement(final Statement currentStatement) {
        Command command = currentStatement.command;
    	switch(command){
            case FN -> {

            }
            case CALL -> {

            }
            case LET -> {

            }
            case SET -> {

            }
            case NEW -> {

            }
            case FREE -> {

            }
            case IF -> {

            }
            case ELSE -> {

            }
            case ELSEIF -> {

            }
            case LOOP -> {

            }
            case CONTINUE -> {

            }
            case BREAK -> {

            }
            case OUT -> {
                outputMessageToConsole((String)currentStatement.params[0]);
            }
            case IN -> {
                getInputFromConsole((int)currentStatement.params[0]);
            }
            case EXIT -> {
                final int exitCode = (int)currentStatement.params[0];
                codeExit(exitCode);
            }
            case SLEEP -> {
                try {
                    Thread.sleep((int)currentStatement.params[0]);
                }
                catch(InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            default -> {
                System.err.println("Unidentified command.");
            }
    	}
    }

    private void allocateVarOnHeap(DataType type, String identifier, String ininitializer){
        virtualHeap.allocate(
            switch (type){
                case TEXT -> ininitializer;
                case NUM  -> Float.parseFloat(ininitializer);
                case BOOL -> Integer.parseInt(ininitializer) != 0;
                default   -> { throw new RuntimeException("Unspecified initializer type"); }
            }
        );
    }

    private void pushNewVarOnStack(DataType type, String identifier, String ininitializer){
    	
    }
    
    private void dealocateVarFromHeap(String identifier) {
    	
    }

    private void outputMessageToConsole(String message){
        System.out.println(message);
    }

    private void getInputFromConsole(int targetVariable){
        //scanner.nextLine(); -> targetVariable
    }


    private void codeExit(final int exitCode){
        this.exitCode = exitCode;
        processStatus = ProcessStatus.TERMINATING;
    }
}
