package prolang.runtime;

import prolang.runtime.Interpreter;
public class EntryPoint {
    public static void main(String[] args) {
        Interpreter interpreter = new Interpreter(args);
    }
}
