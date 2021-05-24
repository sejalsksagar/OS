/**
 *  OS Lab #4 24/05/21
 *  2372 SEJAL KSHIRSAGAR
 *  Title: Write a program to implement following disk scheduling algorithms:
		   First Come First Serve (FCFS), Shortest Seek Time First (SSTF)
 */

package diskScheduling;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

	static int head;
	static int maxCylinders;
	static int totCylinders;
	static ArrayList<Integer> sequence = new ArrayList<Integer>();
	
	public static void main(String[] args) {
		byte ch;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter maximum number of cylinders: ");
		maxCylinders = sc.nextInt();
		System.out.print("Enter total number of cylinders to be accessed: ");
		totCylinders = sc.nextInt();
		
		System.out.print("Enter the sequence of cylinders: ");
		for(int i=0; i<totCylinders; i++) {
			int cyl = sc.nextInt();
			sequence.add(i, cyl);
		}
		
		System.out.print("Enter starting location of head: ");
		head = sc.nextInt();
		
		System.out.println("Sequence is:");
		for(Integer c: sequence)
			System.out.print("\t"+c);
		System.out.println("\nStarting location of head: "+head);
		System.out.println("Maximum number of cylinders: "+maxCylinders);
		
		do {
			System.out.println("_________________________________");
			System.out.println("******* DISK SCHEDULING ********");
			System.out.println("0. Exit");
			System.out.println("1. First Come, First Serve (FCFS)");
			System.out.println("2. Shortest-Seek-Time-First (SSTF)");
			System.out.println("3. SCAN");
			System.out.println("4. Circular SCAN (CSCAN)");
			System.out.print("Enter your choice: ");
			ch = sc.nextByte();
			System.out.println("_________________________________");
			
			switch(ch) {
			case 0: System.out.println("********** PROGRAM END ***********");
			break;
			
			case 1: Main.FCFS();
			break;
			
			case 2: Main.SSTF();
			break;
			
			case 3: Main.SCAN(sc);
			break;
			
			case 4: Main.CSCAN(sc);
			break;
			
			default: System.out.println("Invalid choice.");
			}
		}while(ch != 0);
		sc.close();
	}
	
	static void FCFS() {
		System.out.println("Using First Come, First Serve (FCFS):\n");
		System.out.println("Distance calculation: ");
		
		int curHead = head;
		int dist;
		int totalDist = 0;
		
		for(Integer curTrack: sequence) {			
			dist = Math.abs(curTrack - curHead);
			System.out.println("|"+curTrack+" - "+curHead+"| = "+dist);
			totalDist += dist;
			curHead = curTrack;
		}
		System.out.println("\nTotal distance travelled by head: "+totalDist);
	}
	
	
	static void SSTF() {
		System.out.println("Using Shortest-Seek-Time-First (SSTF):\n");
		ArrayList<Integer> sortedSeq = new ArrayList<Integer>();

		int totalDist = 0;
		int curTrack = head;
		int curHead = head;
		int minDist = Integer.MAX_VALUE;
		
		//make copy of original sequence & 
		//also find track with min dist from head
		for(int i=0; i<sequence.size(); i++) {
			int c = sequence.get(i);
			sortedSeq.add(c);
			
			if(i == 0) { 
				minDist = Math.abs(c - curHead);
				curTrack = c;
			}
			else {
				int dist = Math.abs(c - curHead);
				if(dist < minDist) {
					minDist = dist;
					curTrack = c;
				}
			}
		}
		//sort the copy in ascending order
		Collections.sort(sortedSeq);
		int curSize = sortedSeq.size();
		
		while(!sortedSeq.isEmpty()) {
			//update total seek time
			totalDist += minDist;
			
			//get index of current track
			int ci = sortedSeq.indexOf((Integer)curTrack);
			
			//display
			System.out.println("Minimum distance is "+minDist+" for disk number "+curTrack);
			System.out.println("|"+curTrack+" - "+curHead+"| = "+minDist);
			
			//update curHead
			curHead = curTrack;
			
			boolean pFlag = false;
			boolean nFlag = false;
			int pDist = Integer.MAX_VALUE;
			int nDist = Integer.MAX_VALUE;
			
			//find which neighbor has min dist from current head
			if(ci-1 >= 0 && ci-1 < curSize) {
				pFlag = true;
				pDist = Math.abs(sortedSeq.get(ci-1) - curHead);
			}
			if(ci+1 >= 0 && ci+1 < curSize) {
				nFlag = true;
				nDist = Math.abs(sortedSeq.get(ci+1) - curHead);
			}
			
			//update min dist & current track
			if(pFlag && nFlag) {
				minDist = pDist < nDist ? pDist : nDist;
				curTrack = pDist < nDist ? sortedSeq.get(ci-1) : sortedSeq.get(ci+1);
			}
			else if(pFlag) {
				minDist = pDist;
				curTrack = sortedSeq.get(ci-1);
			}
			else if(nFlag){
				minDist = nDist;
				curTrack = sortedSeq.get(ci+1);
			}
			//remove curHead from the sorted list
			sortedSeq.remove(ci); 
			curSize--;
		}
		System.out.println("\nTotal distance travelled by head: "+totalDist);
	}
	
	static void SCAN(Scanner sc) {
		System.out.println("Using SCAN:\n");
		
		ArrayList<Integer> sortedSeq = new ArrayList<Integer>();
		for(int i=0; i<sequence.size(); i++)
			sortedSeq.add(sequence.get(i));
			
		System.out.print("Enter direction for head to move (L/R): ");
		char ch = sc.next().charAt(0);
		
		int i;
		int dist;
		int totalDist = 0;
		int curHead = head;
		int curTrack = head;
		
		if(ch == 'L') {
			//sort copy in ascending order
			Collections.sort(sortedSeq);
			sortedSeq.add(0, (Integer)0);
			
			//find the track at right of head
			for(i=1; i<sortedSeq.size(); i++) {
				curTrack = sortedSeq.get(i-1);
				if(sortedSeq.get(i) > curHead) break;
			}
		}
		else if(ch == 'R') {
			//sort copy in descending order
			Collections.sort(sortedSeq, Collections.reverseOrder());
			sortedSeq.add(0, (Integer)199);
			
			//find the track at right of head
			for(i=1; i<sortedSeq.size(); i++) {
				curTrack = sortedSeq.get(i-1);
				if(sortedSeq.get(i) < curHead) break;
			}
		}
		else {
			System.out.println("Invalid direction.");
			return;
		}	
		i--;
		while(!sortedSeq.isEmpty()) {
			dist = Math.abs(curTrack - curHead);
			System.out.println("|"+curTrack+" - "+curHead+"| = "+dist);
			totalDist += dist;
			
			sortedSeq.remove(i);
			if(i-1 >= 0) i--;
			
			curHead = curTrack;
			if(!sortedSeq.isEmpty()) curTrack = sortedSeq.get(i);	
		}
		System.out.println("\nTotal distance travelled by head: "+totalDist);
	}
	
	static void CSCAN(Scanner sc) {
		System.out.println("Using Circular SCAN (CSCAN):\n");
		
		ArrayList<Integer> sortedSeq = new ArrayList<Integer>();
		for(int i=0; i<sequence.size(); i++)
			sortedSeq.add(sequence.get(i));
			
		System.out.print("Enter direction for head to move (L/R): ");
		char ch = sc.next().charAt(0);
		
		int i;
		int dist;
		int totalDist = 0;
		int curHead = head;
		int curTrack = head;
		
		if(ch == 'L') { 
			//sort copy in ascending order
			Collections.sort(sortedSeq);
			sortedSeq.add(0, (Integer)0);
			sortedSeq.add((Integer)199);
			
			//find the track at left of head
			for(i=1; i<sortedSeq.size(); i++) {
				curTrack = sortedSeq.get(i-1);
				if(sortedSeq.get(i) > curHead) break;
			}
		}
		else if(ch == 'R') {
			//sort copy in descending order
			Collections.sort(sortedSeq, Collections.reverseOrder());
			sortedSeq.add((Integer)0);
			sortedSeq.add(0, (Integer)199);
			
			//find the track at right of head
			for(i=1; i<sortedSeq.size(); i++) {
				curTrack = sortedSeq.get(i-1);
				if(sortedSeq.get(i) < curHead) break;
			}
		}
		else {
			System.out.println("Invalid direction.");
			return;
		}	
		i--;
		while(!sortedSeq.isEmpty()) {
			dist = Math.abs(curTrack - curHead);
			System.out.println("|"+curTrack+" - "+curHead+"| = "+dist);
			totalDist += dist;
			
			sortedSeq.remove(i); 
			if(i-1 >= 0) i--;
			else i = sortedSeq.size()-1;
			
			curHead = curTrack;
			if(!sortedSeq.isEmpty()) curTrack = sortedSeq.get(i);	
		}
		System.out.println("\nTotal distance travelled by head: "+totalDist+" - 199 = "+(totalDist-199));
	}
}
