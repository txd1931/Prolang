package prolang.runtime.internal;

public class ExecutionProtocol{
    public final String sourcePath;
    public final ProcessConfig processConfig;
    public final long timeLimit;
    public final int statementCounterLimit;

    public ExecutionProtocol(String sourcePath, ProcessConfig processConfig, long timeLimit, int statementCounterLimit) {
        this.sourcePath = sourcePath;
        this.processConfig = processConfig;
        this.timeLimit = timeLimit;
        this.statementCounterLimit = statementCounterLimit;
    }
}