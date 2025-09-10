package prolang.runtime.internal;

public enum ProcessStatus {
    INITIALIZING,
    RUNNING_ACTIVE, 
    RUNNING_INACTIVE,
    INTERRUPTED,
    TERMINATING,
}
