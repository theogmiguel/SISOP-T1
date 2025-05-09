package HW.Memory;

import HW.CPU.Opcode;

public class Memory {
    public int partitionSize;
    public boolean[] frames;
    public int tamMem;    
    public Word[] m;                  // m representa a memória fisica:   um array de posicoes de memoria (word)

    public Memory(int size, int partitionSize){
        tamMem = size;
        m = new Word[tamMem];
        for (int i=0; i<tamMem; i++) { m[i] = new Word(Opcode.___,-1,-1,-1); };

        this.partitionSize = partitionSize;
        frames = new boolean[tamMem/partitionSize];  
        for (int i=0; i<tamMem/partitionSize; i++) { frames[i] = true ; };
    }
    
    public void dump(Word w) {        // funcoes de DUMP nao existem em hardware - colocadas aqui para facilidade
                    System.out.print("[ "); 
                    System.out.print(w.opc); System.out.print(", ");
                    System.out.print(w.r1);  System.out.print(", ");
                    System.out.print(w.r2);  System.out.print(", ");
                    System.out.print(w.p);  System.out.println("  ] ");
    }
    public void dump(int ini, int fim) {
        for (int i = ini; i < fim; i++) {		
            System.out.print(i); System.out.print(":  ");  dump(m[i]);
        }
    }
}