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
							case "fatorialV2":
								System.out.println(processManager.createProcess(progs.fatorialV2));
								break;
							case "fibonacci10v2":
								System.out.println(processManager.createProcess(progs.fibonacci10v2));
								break;
							case "fibonacciREAD":
								System.out.println(processManager.createProcess(progs.fibonacciREAD));
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
						System.out.println(processManager.pcbList.keySet());
						break;
					case "desaloca":
						processManager.deallocateProcess(Integer.parseInt(inputList[1]));
						break;
					case "execute":
						processManager.executeProcess(Integer.parseInt(inputList[1]));
						break;
                    case "executeAll":
                        processManager.executeAllProcesses();
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
}
