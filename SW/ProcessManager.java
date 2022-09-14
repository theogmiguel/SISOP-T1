package SW;

import HW.CPU.CPU;

public class ProcessManager {
    
    private CPU cpu;
    private MemoryManager memoryManager;

    public ProcessManager(CPU cpu,MemoryManager memoryManager){
        this.cpu = cpu;
        this.memoryManager = memoryManager;
    }

}
