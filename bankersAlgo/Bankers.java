package bankersAlgo;

import java.util.Scanner;

public class Bankers {
	int n;		   //no. of processes in the system
	int m; 			//no. of resources
	int res[]; 		//no. of instances of each resource
	int max[][]; 	//maximum matrix
	int alloc[][]; 	//allocation matrix
	int need[][]; 	//need matrix
	int avail[];
	
	Bankers(int numProcesses, int numResources){
		n = numProcesses;
		m = numResources;
		res = new int[m];
		max = new int[n][m];
		alloc = new int[n][m];
		need = new int[n][m];
		avail = new int[m];
	}
	
	void calculateNeed() {
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) 
				need[i][j] = max[i][j] - alloc[i][j];
		}
	}
	
	void calculateAvailable() {
		
		int allocated[] = new int[m]; //creates & initializes all to 0
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) 
				allocated[j] = allocated[j] + alloc[i][j];
		}
		
		for(int i=0; i<m; i++)
			avail[i] = res[i] - allocated[i];
	}
	
	static Bankers acceptDetails(Scanner sc) {
		
		System.out.print("Enter number of processes: ");
		int numProcesses = sc.nextInt();
		System.out.print("Enter number of resources: ");
		int numResources = sc.nextInt();
		Bankers B = new Bankers(numProcesses, numResources);
		
		System.out.println("Enter the number of instances for each resource: ");
		for(int i=0; i<B.m; i++) {
			System.out.print("For resource "+i+": ");
			B.res[i] = sc.nextInt();
		}
		
		System.out.println("Enter the elements of maximum matrix: ");
		for(int i=0; i<B.n; i++) {
			System.out.print("For process P"+i+": ");
			
			for(int j=0; j<B.m; j++) 
				B.max[i][j] = sc.nextInt();
		}
		
		System.out.println("Enter the elements of allocation matrix: ");
		for(int i=0; i<B.n; i++) {
			System.out.print("For process P"+i+": ");
			
			for(int j=0; j<B.m; j++) 
				B.alloc[i][j] = sc.nextInt();
		}
		
		B.calculateNeed();
		B.calculateAvailable();
		return B;
	}
	
	void displayDetails() {
		System.out.println("----------------------------------------------------------------------------------------------------");
		System.out.format("%15s %20s %20s %20s %20s", "Process", "Allocation", "Maximum", "Available", "Need");
		System.out.println(" ");
		
		String Allocation = "";
		String Max = "";
		String Available = "";
		String Need = "";
		
		for(int i=0; i<n; i++) {
			Allocation = "";
			Max = "";
			Available = "";
			Need = "";
			
			for(int j=0; j<m; j++) {
				Allocation = Allocation + alloc[i][j] + " ";
				Max = Max + max[i][j] + " ";
				Need = Need + need[i][j] + " ";
				
				if(i == 0)
					Available = Available + avail[j] + " ";	
			}
			System.out.format("%15s %20s %20s %20s %20s", "P"+i, Allocation, Max, Available, Need);
			System.out.println("");
		}
		System.out.println("----------------------------------------------------------------------------------------------------");
	}
	
	//Safe State Algorithm
	boolean safeState() {
		System.out.println("Using the Safe State Algorithm...");
		
		int work[] = avail.clone();	//creates copy of avail
		boolean finish[] = new boolean[n]; //creates & initializes all to false
		int safeSeq[] = new int[n];
		
		int count = 0;
		boolean pFound;
		boolean enoughWork;
		
		while(count < n) {
			pFound = false;
			
			for(int i=0; i<n; i++) {
				if(finish[i] == false) {
					enoughWork = true;
				
					//CHECK if for ith process need for all resources <= work for all resources
					for(int j=0; j<m; j++) {
						if(!(need[i][j] <= work[j])) {
							enoughWork = false;
							break;
						}
					}
					
					if(enoughWork) { 
						for(int j=0; j<m; j++)
							work[j] = work[j] + alloc[i][j];
						
						finish[i] = true;
						safeSeq[count++] = i;
						pFound = true;
						break;
					}
				}
			}
			//IF no such process found 
			if(!pFound)
				break;	
		}
		
		//CHECK if finish[i]==true for all i<n
		boolean finishAllTrue = true;
		for(int i=0; i<n; i++){
			if(finish[i] == false){
				finishAllTrue = false;
				break;
			}
		}
		
		if(finishAllTrue) {
			System.out.println("The system is in safe state.");
			System.out.println("Safe sequence: ");
			for(int i=0; i<n; i++) {
				System.out.print("P"+safeSeq[i]);
				if(i != n-1)
					System.out.print(" -> ");
			}
			System.out.println("");	
			return true;
		}
		else {
			System.out.println("The system is NOT in safe state.");
			return false;
		}		
	}
	
	//Request Allocation Algorithm
	void resourceRequest(Scanner sc) {
		System.out.println("Using the Resource Request Allocation Algorithm...");
		
		System.out.print("Enter processes number: ");
		int pi = sc.nextInt();
		
		int request[] = new int[m];
		boolean resUnavail = false;
		
		System.out.print("Enter request elements: ");
		for(int i=0; i<m; i++) {
			request[i] = sc.nextInt();
			
			//CHECK if process pi has exceeded its maximum claim
			if(!(request[i] <= need[pi][i]))
				resUnavail = true;
			//CHECK if resources are available
			if(!(request[i] <= avail[i]))
				resUnavail = true;
		}
		
		if(resUnavail) {
			System.out.println("Request by Process cannot be granted! Resources not available.");
			return;
		}
		
		Bankers tmp = new Bankers(n, m);
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				//copy
				tmp.avail[j] = avail[j];
				tmp.alloc[i][j] = alloc[i][j];
				tmp.need[i][j] = need[i][j];
				tmp.res[j] = res[j];
				tmp.max[i][j] = max[i][j];
				
				//update tmp
				tmp.avail[j] = avail[j] - request[j];
				tmp.alloc[pi][j] = alloc[pi][j] + request[j];
				tmp.need[pi][j] = need[pi][j] - request[j];
			}
		}
		System.out.println("Assuming request is granted, the system state: ");
		tmp.displayDetails();
		
		if(tmp.safeState()) {
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					//update original
					avail[j] = tmp.avail[j];
					alloc[pi][j] = tmp.alloc[pi][j];
					need[pi][j] = tmp.need[pi][j];
				}
			}
			System.out.println("Request has been granted. New state of the system: ");
			this.displayDetails();
		}
		else
			System.out.println("Request cannot be granted.");
	}
}
