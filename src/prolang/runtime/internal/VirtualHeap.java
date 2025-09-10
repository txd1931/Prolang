package prolang.runtime.internal;

import java.util.HashMap;
import java.util.Map;

public class VirtualHeap{
    public class HeapObject{
        private Object value;
        
        HeapObject(Object value){
            this.value = value;
        }
        
        public Object getValue() {
            return value;
        }
        
        public void setValue(Object value) {
            this.value = value;
        }
    }
        
    private static Map<Integer, HeapObject> heap = new HashMap<>();
    
    private static int nextId = 1;

    public int allocate(Object value){
        int id = nextId++;
        heap.put(id, new HeapObject(value));
        return id;
    }

    public static Object read(int pointer){
        HeapObject object = heap.get(pointer);
        if(object == null){
            throw new RuntimeException("Null reference exeption at " +  pointer);
        }
        return object.getValue();
    }

    public static void free(int pointer){
        if(!heap.containsKey(pointer)){
            throw new RuntimeException("Invalid delete at " + pointer);
        }
        heap.remove(pointer);
    }

    public static void debugDump(){
        System.out.println("Virtual heap memory: " + heap.keySet());
    }
}