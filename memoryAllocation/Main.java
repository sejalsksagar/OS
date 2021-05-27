/**
 *  OS LAB #5 27/05/2021
	2372 SEJAL KSHIRSAGAR
	Title: Write a Java program to implement following memory
	allocation strategies: First Fit, Best Fit and Worst Fit.
 */

package memoryAllocation;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

class Process{
	int id;
	int size;
	
}

class Block{
	int id;
	int size;
	boolean free;
	
	Block(){
		id = 0;
		size = 0;
		free = true;
	}
}

class MemoryAlloc{
	int numB; //number of holes/blocks
	int numP; //number of processes
	Block[] B;
	Process[] P; 
	
	
	MemoryAlloc(Scanner sc){
		System.out.print("Enter no. of memory blocks available: ");
		numB = sc.nextInt();
		B = new Block[numB];
		acceptBlockSize(sc);
		
		System.out.print("Enter no. of processes available: ");
		numP = sc.nextInt();
		P = new Process[numP];
		acceptProcessSize(sc);
	}
	
	void acceptBlockSize(Scanner sc) {
		for(int i=0; i<numB; i++) {
			Block b = new Block();
			System.out.print("Enter the block id: ");
			b.id = sc.nextInt();
			System.out.print("Enter the size of memory block "+b.id+" in KB: ");
			b.size = sc.nextInt();
			B[i] = b;
		}
	}
	
	void acceptProcessSize(Scanner sc) {
		for(int i=0; i<numP; i++) {
			Process p = new Process();
			System.out.print("Enter the process id: ");
			p.id = sc.nextInt();
			System.out.print("Enter the size of process "+p.id+" in KB: ");
			p.size = sc.nextInt();
			P[i] = p;
		}
	}
	
	void firstFit() {
		
		//Find first hole which is big enough and free to accommodate the process
		for(Process p : P) {
			boolean allocated = false;
			
			for(Block b : B) {
				if(b.size>=p.size && b.free) {
					b.free = false;
					allocated = true;
					System.out.println("Process "+p.id+" of size "+p.size+" is allocated to memory block "+b.id+" of size "+b.size+" KB");
					break;
				}
			}
			if(!allocated)
				System.out.println("Process "+p.id+" is not allocated to any memory block");
		}
		for(Block b : B)
			b.free = true;
	}
	
	void bestFit() {
		//Sort array of holes in ascending order
		Arrays.sort(B, Comparator.comparing(b->b.size));
		
		//Find smallest hole which is big enough and free to accommodate the process
		firstFit();
	}
	
	void worstFit() {
		//Sort array of holes in descending order
		Arrays.sort(B, Collections.reverseOrder(Comparator.comparing(b->b.size)));
		
		//Find largest hole and free to accommodate the process
		firstFit();
	}
	
}

public class Main {

	public static void main(String[] args) {
		byte ch;
		Scanner sc = new Scanner(System.in);
		MemoryAlloc M = new MemoryAlloc(sc);
		
		do {
			System.out.println("____________________________________________");
			System.out.println("****** MEMORY ALLOCATION STRATEGIES ******");
			System.out.println("0. Exit");
			System.out.println("1. First Fit");
			System.out.println("2. Best Fit");
			System.out.println("3. Worst Fit");
			System.out.print("Enter your choice: ");
			ch = sc.nextByte();
			System.out.println("____________________________________________");
			
			switch(ch) {
			case 0: System.out.println("*********** EXECUTION COMPLETED *************");
					break;
					
			case 1: M.firstFit(); break;
			case 2: M.bestFit(); break;
			case 3: M.worstFit(); break;
			default: System.out.println("Invalid choice");
			}
		}while(ch!=0);
	}

}
