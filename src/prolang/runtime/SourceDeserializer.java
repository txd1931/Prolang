package prolang.runtime;

import prolang.external.Statement;

public class SourceDeserializer {
    public static Statement[] statements(){
        Statement[] tokens = new Statement[2];
        return tokens;
    }    
    public static int[][] functionPointers(){
        int[][] functionPointers = new int[2][1000];
        return functionPointers;
    }
}
