package SW;

import java.util.ArrayList;

import HW.Memory.Memory;
import HW.Memory.Word;

public class MemoryManager {
    
    public Memory memory;
    public int memSize;
    public int partitionSize;

    public MemoryManager(Memory memory){
        this.memory = memory;
        this.memSize = memory.tamMem;
        this.partitionSize = memory.partitionSize;
    }

    public int[] allocate(int wordNumber){

        ArrayList<Integer> freeFrames = new ArrayList<>();
        int requiredFrames = (int)Math.ceil(wordNumber/(partitionSize*1.0));

        for (int i = 0; i < memory.frames.length && requiredFrames != 0; i++) {
            if(memory.frames[i] == true) freeFrames.add(i);
            requiredFrames--;
        }

        if(requiredFrames != 0) return new int[0];

        else{
            freeFrames.forEach((value) -> memory.frames[value] = false);
            return freeFrames.stream().mapToInt(i -> i).toArray();
        }
    } 

    public void deallocate(int[] partitions){

        for (int i : partitions) {
            memory.frames[i] = true;
        }
        
    }

    public void loadProgram(Word[] program, int[] partitions){

        for (int i = 0; i < program.length; i++) {

            int translatedPosition = translate(i, partitions);
            memory.m[translatedPosition].opc = program[i].opc;
            memory.m[translatedPosition].r1 = program[i].r1;
            memory.m[translatedPosition].r2 = program[i].r2;
            memory.m[translatedPosition].p = program[i].p;    
            
        }
        
    }

    public int translate(int logicPosition, int[] partitions){
		int p = logicPosition / partitionSize; // p = contagem de posicao no array da tabela
		int offset = logicPosition % partitionSize; // offset desclocamente dentro do frame
		int frameInMemory = partitions[p]; //obtem no indice de paginas o frame real da memoria 
		int positionInMemory = partitionSize * frameInMemory + offset;

        return positionInMemory;
    }


}
