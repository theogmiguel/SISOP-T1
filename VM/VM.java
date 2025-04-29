package VM;

import HW.CPU.CPU;
import HW.Memory.Memory;
import SW.OS;
import SW.Programs;

public class VM {

    public static final int MEMORY_SIZE = 1024;
    public static final int PARTITION_SIZE = 16;
    public static final Programs progs = new Programs();

    public Memory mem;
    public CPU cpu; 
    public OS system;

    public VM() {
        mem = new Memory(MEMORY_SIZE, PARTITION_SIZE);
        cpu = new CPU(mem, true );
        system = new OS(mem, cpu, progs);
    }

    public static void main(String args[]) {
        VM vm = new VM();
        vm.boot();
	}

    private void boot(){
        this.system.menu();
    }

}
