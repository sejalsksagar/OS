package cpuScheduling;

import java.util.ArrayList;
import java.util.Scanner;


public class SchedulingAlgo {
	int n; //number of processes
	ArrayList<Process> P; //Processes
	ArrayList<String> GC; //Gantt Chart
	double avgWT;
	double avgTT;
	
	SchedulingAlgo(){
		n = 0;
		P = new ArrayList<Process>();
		GC = new ArrayList<String>();
		avgWT = 0;
		avgTT = 0;
	}
	
	void accept(Scanner sc) {
		System.out.print("\nEnter the number of processes to be performed: ");
		n = sc.nextInt();
		
		for(int i=0; i<n; i++) {
			//add new process to the ArrayList
			Process tmp = new Process();
			P.add(tmp);
			
			//accept AT & BT
			P.get(i).accept_AT_BT(sc);
		}
	}
	
	void FCFS() {
		System.out.println("First Come, First Serve (FCFS) :");
		
		for(int i=0; i<n; i++) {
			//calculate FT
			if(i == 0)
				P.get(i).FT = P.get(i).BT;
			else
				P.get(i).FT = P.get(i-1).FT + P.get(i).BT;
			
			//calculate TT & WT
			P.get(i).calculate_TT_WT();
			
			//calculate total TT & total WT
			avgWT += P.get(i).WT;
			avgTT += P.get(i).TT;
			
			GC.add("P"+i);
		}
		//calculate avgTT & avgWT
		avgWT /= n;
		avgTT /= n;
	}
	
	int findMin(ArrayList<TmpProcess> RQ) {
		TmpProcess min = null;
		int m = Integer.MAX_VALUE;
		
		for(TmpProcess tp: RQ) {
			if(tp.tmpBT < m) {
				m = tp.tmpBT;
				min = tp;
			}
		}
		if(m==RQ.get(0).tmpBT)
			return 0;
		else
			return RQ.indexOf(min);
	}
	
	//preemptive SJF
	void SRTF() {
		ArrayList<TmpProcess> RQ = new ArrayList<TmpProcess>();
		TmpProcess TP[] = new TmpProcess[n];
		
		for(int i=0; i<n; i++) {
			TmpProcess tmp = new TmpProcess();
			tmp.p = P.get(i);
			tmp.tmpAT = P.get(i).AT;
			tmp.tmpBT = P.get(i).BT;
			TP[i] = tmp;
		}
		
		int i = 0;
		int cur_time = TP[0].p.AT;
		RQ.add(TP[i++]);
		TmpProcess cur = RQ.remove(0);
		boolean allArrived = false;
		
		while(!allArrived) {
			
			if(TP[i].p.AT == cur_time) 
				RQ.add(TP[i++]);

			if(!RQ.isEmpty()) {
				int j = findMin(RQ);
				if(RQ.get(j).tmpBT<cur.tmpBT) {
					RQ.add(cur);
					cur.p.FT = cur_time;
					cur = RQ.remove(j);
				}
			}
			GC.add("P"+cur.p.pId);
			cur_time++;
			cur.tmpBT--;
			
			if(cur.tmpBT==0) 
				cur.p.FT = cur_time;
			if(i == n)
				allArrived = true;
		}
		
		while(!RQ.isEmpty()) {
			cur = RQ.remove(findMin(RQ));
			while(cur.tmpBT!=0) {
				GC.add("P"+cur.p.pId);
				cur_time++;
				cur.tmpBT--;
			}
			cur.p.FT = cur_time;
		}
		
		//calculate TT & WT
		for(int j=0; j<n; j++) {
			P.get(j).calculate_TT_WT();
			//calculate total TT & total WT
			avgWT += P.get(j).WT;
			avgTT += P.get(j).TT;
		}
		
		//calculate avgTT & avgWT
		avgWT /= n;
		avgTT /= n;	
	}
	
	void display() {
		System.out.println("-------------------------------------------------------------------------------------------------------------");
		System.out.format("%15s %15s %15s %15s %15s %15s", "Process", "AT", "BT", "FT", "TT", "WT");
		for(int i=0; i<n; i++)
			P.get(i).display();
		System.out.println("\n-------------------------------------------------------------------------------------------------------------");
		System.out.println("The average turnaround time is: "+avgTT);
		System.out.println("The average waiting time is: "+avgWT);
		
		System.out.print("GANTT CHART : ");
		for(String P : GC)
			System.out.print(P+" | ");
		System.out.println("\n-------------------------------------------------------------------------------------------------------------");
		avgWT = 0;
		avgTT = 0;
	}
	
}
