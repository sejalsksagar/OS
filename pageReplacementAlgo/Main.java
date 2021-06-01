/*
OS LAB #8 01/06/2021
2372 SEJAL KSHIRSAGAR
Title: Write a Java program to implement following page replacement algorithms:
First In First Out (FIFO) and Least Recently Used (LRU)
*/

package pageReplacementAlgo;


import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		int r, k;
		char ch;
		int nFrames;
		int nPages;
		int refString[];
		int output[][];
		int pageFault;
		ArrayList<Integer> visited;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter the number of frames: ");
		nFrames = sc.nextInt();
		System.out.print("Enter the number of pages in the reference string: ");
		nPages = sc.nextInt();
		System.out.print("Enter the reference string: ");
		refString = new int[nPages];
		
		for(int i=0; i<nPages; i++)
			refString[i] = sc.nextInt();
		
		output = new int[nFrames+1][nPages];
		
		ArrayList<Integer> Q = new ArrayList<Integer>();
		for(int i=0; i<nFrames; i++) Q.add(-1);
		
		do { 
			System.out.println("******* PAGE REPLACEMENT ALGORITHMS ******");
			System.out.println("0. Exit");
			System.out.println("1. FIFO");
			System.out.println("2. LRU");
			System.out.print("Enter your choice: ");
			ch = sc.next().charAt(0);
			
			if(ch=='1' || ch=='2') {
				System.out.print("Reference string: "); 
				for(int i=0; i<nPages; i++) System.out.print(refString[i]+" ");
				System.out.println();
				for(int i=0; i<nFrames; i++) Q.set(i, -1);
			}
			switch(ch) {
			case '0': System.out.println("********** PROGRAM END *************");
			break;
			
			case '1':   r = 0;
						pageFault = 0;
					  	for(int j=0; j<nPages; j++) {
					  		if(!Q.contains(refString[j])) {
					  			pageFault++;
					  			Q.set(r, refString[j]);
					  			r = (r + 1)%(nFrames);
					  		}
					  		
					  		for(k=0; k<nFrames; k++) 
					  			output[k][j] = Q.get(k);
					  		output[k][j] = pageFault;
					  	}
					  	break;
					  	
			case '2':	boolean flag = false;
						pageFault = 0;
						r = 0;
					  	for(int j=0; j<nPages; j++) {
					  		if(!Q.contains(refString[j])) {
					  			if(!flag) {
					  				pageFault++;
					  				Q.set(r, refString[j]);
					  				r = (r + 1);
					  				if(r > nFrames-1) flag = true;
					  			}
					  			else {
					  				//lookback
					  				visited = new ArrayList<Integer>();
					  				for(int i=0; i<nFrames; i++) visited.add( -1);
					  				int c = 0; r = 0;
					  				for(int i=j-1; i>-1 && c<nFrames; i--) {
					  					if(Q.contains(refString[i]) && !visited.contains(refString[i])) {
					  						r = Q.indexOf(refString[i]);
					  						visited.set(c++, refString[i]);
					  					}
					  				}
					  				pageFault++;
					  				Q.set(r, refString[j]);
					  			}
					  		}
					  		for(k=0; k<nFrames; k++) 
					  			output[k][j] = Q.get(k);
					  		output[k][j] = pageFault;
					  	}
					  	break;
			}
			
			if(ch=='1' || ch=='2') {
				for(int i=0; i<nFrames+1; i++) {
					if(i!=nFrames) System.out.print("\nP"+i+"\t");
					else System.out.print("\nPF\t");
					
					for(int j=0; j<nPages; j++) {
						 if(output[i][j] == -1) System.out.print("-\t");
						 else System.out.print(output[i][j]+"\t");
					}
				}
				System.out.println("\n");
			}
		}while(ch!='0');
		sc.close();
	}

}
