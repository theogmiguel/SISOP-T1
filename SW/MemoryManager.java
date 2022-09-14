package SW;

import java.util.ArrayList;

import HW.Memory.Memory;

public class MemoryManager {
    
    private Memory memory;
    public int memSize = memory.tamMem;

    public MemoryManager(Memory memory){
        this.memory = memory;
    }

    public int[] allocate(int wordNumber){

        ArrayList<Integer> neededFrames = new ArrayList<>();

        for (int i = 0; i < memory.frames.length; i++) {
            if(memory.frames[i] == true) neededFrames.add(i);
        }

        if(neededFrames.size() != 0) return new int[0];
        else{
            neededFrames.forEach((value) -> memory.frames[value] = false);
            return neededFrames.stream().mapToInt(i -> i).toArray();
        }
    } 

    public void deallocate(int[] partitions){

        for (int i : partitions) {
            memory.frames[i] = true;
        }
        
    }

}
