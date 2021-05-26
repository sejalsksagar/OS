/**
 *  OS Lab #4 26/05/21
 *  2372 SEJAL KSHIRSAGAR
 *  Title: Write a Java program to implement following scheduling algorithms First
	Come First Serve (FCFS)(Non-Pre-emptive), Shortest Remaining Time First
	(SRTF) (Pre-emptive).
 */
package cpuScheduling;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		byte ch;
		Scanner sc = new Scanner(System.in);
		SchedulingAlgo A = new SchedulingAlgo();
		
		do {
			System.out.println("_________________________________");
			System.out.println("******* CPU SCHEDULING ********");
			System.out.println("0. Exit");
			System.out.println("1. First Come, First Serve (FCFS)");
			System.out.println("2. Shortest-Remaining-Time-First (SRTF)");
			System.out.print("Enter your choice: ");
			ch = sc.nextByte();
			System.out.println("_________________________________");
			
			switch(ch) {
			case 0: System.out.println("********** PROGRAM END ***********");
			break;
			
			case 1: A.accept(sc);
					A.FCFS();
					A.display();
					break;
			
			case 2: A.accept(sc);
					A.SRTF();
					A.display();
			break;
			
			
			default: System.out.println("Invalid choice.");
			}
		}while(ch != 0);
		sc.close();
	}

}


