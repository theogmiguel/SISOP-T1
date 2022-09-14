package SW;

import java.util.Scanner;

import HW.CPU.CPU;
import HW.Memory.Memory;

public class OS {

    public MemoryManager memoryManager;
    public ProcessManager processManager;
	public Programs progs;

    public OS(Memory memory, CPU cpu, Programs progs){   // a VM com tratamento de interrupções
		this.memoryManager = new MemoryManager(memory);
		this.processManager = new ProcessManager(cpu,memoryManager);
		this.progs = progs;
	}

	public void menu(){
		try (Scanner input = new Scanner(System.in)) {
			while (true) {
				System.out.println("Informe um comando:");
				String data = input.nextLine();
				String[] inputList = data.split(" ");
				switch (inputList[0]) {
					case "cria":
						switch (inputList[1]) {
							case "fibonacci10":
								System.out.println(processManager.createProcess(progs.fibonacci10));
								
								break;
							case "progMinimo":
								System.out.println(processManager.createProcess(progs.progMinimo));
								break;
							case "fatorial":
								System.out.println(processManager.createProcess(progs.fatorial));
								break;
							case "fatorialTRAP":
								System.out.println(processManager.createProcess(progs.fibonacci10));
								break;
							case "fibonacciTRAP":
								System.out.println(processManager.createProcess(progs.fibonacci10));
								break;
							case "PC":
								System.out.println(processManager.createProcess(progs.PC));
								break;
							default:
								System.out.println("Programa não encontrado");
								break;
						}
						break;
					case "lista":
						System.out.println(processManager.pcbList);
						break;
					case "dump":
						break;
					case "desaloca":
						break;
					case "dumpM":
						break;
					case "executa":
						processManager.executeProcess(Integer.parseInt(inputList[1]));
						break;
					case "traceOn":
						break;
					case "traceOff":
						break;
					case "exit":
						System.exit(0);
						break;
					default:
						System.out.println("Comando inválido");
						break;
				}
			}
		}
	}

    /*public void loadProgram(Word[] p, Word[] m) {
		for (int i = 0; i < p.length; i++) {
			m[i].opc = p[i].opc;     m[i].r1 = p[i].r1;     m[i].r2 = p[i].r2;     m[i].p = p[i].p;
		}
	}

	public void loadProgram(Word[] p) {
		loadProgram(p, mem.m);
	}

	public void loadAndExec(Word[] p){
		loadProgram(p);    // carga do programa na memoria
				System.out.println("---------------------------------- programa carregado na memoria");
				mem.dump(0, p.length);            // dump da memoria nestas posicoes				
		cpu.setContext(0, VM.MEMORY_SIZE - 1, 0);      // seta estado da cpu ]
				System.out.println("---------------------------------- inicia execucao ");
		cpu.run();                                // cpu roda programa ate parar	
				System.out.println("---------------------------------- memoria após execucao ");
				mem.dump(0, p.length);            // dump da memoria com resultado
	}*/
}
