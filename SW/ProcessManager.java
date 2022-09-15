package SW;

import java.util.HashMap;

import HW.CPU.CPU;
import HW.Memory.Word;

public class ProcessManager {

    public class PCB{
        public int id;
        public int[] partitions;
        public int programCounter;
        public int base;
        public int limit;

        public PCB(){
            id = pcbId;
            pcbId++;
            partitions = new int[0];
            programCounter = 0;
            base = 0;
            limit = 0;
        }

    }
    
    private int pcbId;    
    private CPU cpu;
    private MemoryManager memoryManager;
    public HashMap<Integer, PCB> pcbList;
    private HashMap<Integer, PCB> readyList;
    private int runningPCB;

    public ProcessManager(CPU cpu, MemoryManager memoryManager){
        this.pcbId = 1;
        this.cpu = cpu;
        this.memoryManager = memoryManager;
        this.readyList = new HashMap<>();
        this.pcbList = new HashMap<>();
        this.runningPCB = 0;
    }

    public boolean createProcess(Word[] program){

        if(program.length > memoryManager.memSize) return false;

        int[] allocation = memoryManager.allocate(program.length);
        if(allocation.length == 0) return false;

        memoryManager.loadProgram(program, allocation);

        PCB newPCB = new PCB();
        newPCB.programCounter = memoryManager.translate(0, allocation);
        newPCB.base = newPCB.programCounter;
        newPCB.limit = memoryManager.translate(program.length, allocation);
        newPCB.partitions = allocation;

        pcbList.put(newPCB.id, newPCB);
        readyList.put(newPCB.id, newPCB);

        return true;
    }

    public void deallocateProcess(int id){

        PCB pcb = pcbList.get(id);
        if(pcb == null) return;

        memoryManager.deallocate(pcb.partitions);

        pcbList.remove(id);
        readyList.remove(id);
        if(runningPCB == id) runningPCB = 0;

    }

    public void executeProcess(int processId){

        PCB process = pcbList.get(processId);

        if (process == null){
          System.out.println("Processo inexistente");
          return; 
        }
        cpu.setContext(0, memoryManager.memSize-1, process.programCounter);
        cpu.run();

    }
}