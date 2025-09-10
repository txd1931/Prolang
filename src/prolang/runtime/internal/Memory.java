package prolang.runtime.internal;
import java.lang.Math;
import java.util.AbstractMap;
public class Memory{
    private static byte[] m;
    private static int kbsize;
    private static int textRegionLimit = 256;
    private static int dataRegionLimit = 768;
    public Memory(int kbsize){
        this.kbsize = kbsize;
        m = new byte[kbsize*(int)Math.pow(2, kbsize)];
        textRegionLimit = textRegionLimit * kbsize -1;
        dataRegionLimit = dataRegionLimit * kbsize -1;
    } 
    public static int getKbsize(){
        return kbsize;
    }
    public static void write(final int address, final byte value){
        if(address <= textRegionLimit){
            System.err.println("Invalid memory access at: " + address + "\nTryed to modify the text segment");
        }else if (address < dataRegionLimit){
            System.err.println("Invalid memory access at: " + address + "\nTryed to modify the program stack segment");
        }
        m[address] = value;
    }
    public static byte read(final int address){
        if(address <= textRegionLimit){
            System.err.println("Invalid memory access at: " + address + "\nTryed to access the text segment");
        }else if (address < dataRegionLimit){
            System.err.println("Invalid memory access at: " + address + "\nTryed to access the program stack segment");
        }
        return m[address];
    }
    public static void loadTextSegment(final int[][] instructions){
        
    }
}