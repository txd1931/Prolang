package prolang.tests.runtime.internal;

//import java.util.HashMap;

import prolang.runtime.internal.VirtualHeap;

public class VirtualHeapTest {
    
    //private static HashMap<Integer,Integer> testCases = new HashMap<>();

    public static void main(String[] args) {
        heapAllocation();
        System.out.println("Hello, world!");
    }
  
    private static void heapAllocation(){
        VirtualHeap virtualHeap = new VirtualHeap();
        int firstNumber;
        firstNumber = virtualHeap.allocate(1);
        System.out.println(firstNumber);
    }
}
