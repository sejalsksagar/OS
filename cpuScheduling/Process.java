package cpuScheduling;

import java.util.Scanner;

class TmpProcess{
	Process p;
	int tmpAT;
	int tmpBT;
}

public class Process {
	static int count = 0;
	int pId; 
	int AT; //Arrival Time
	int BT; //Burst Time
	int FT; //Finish Time/ Completion Time 
	int TT; //Turnaround Time
	int WT; //Waiting Time
	
	Process(){
		pId = count++;
		AT = 0;
		BT = 0;
		FT = 0;
		TT = 0;
		WT = 0;
	}
	
	void accept_AT_BT(Scanner sc) {
		System.out.print("Enter Arrival Time: ");
		AT = sc.nextInt();
		System.out.print("Enter Burst Time: ");
		BT = sc.nextInt();
	}
	
	void calculate_TT_WT() {
		TT = FT - AT;
		WT = TT - BT;
	}
	
	void display() {
		System.out.println();
		System.out.format("%15s %15d %15d %15d %15d %15d", "P"+pId, AT, BT, FT, TT, WT);
	}
}
