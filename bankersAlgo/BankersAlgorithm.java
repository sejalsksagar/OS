/**
 *  OS Lab #2 21/05/21
 *  2372 SEJAL KSHIRSAGAR
 *  Title: Write a Java program to implement Bankers Algorithm for deadlock handling.
 */
package bankersAlgo;

import java.util.Scanner;

public class BankersAlgorithm {

	public static void main(String[] args) {
		
		byte ch;
		Bankers B = null;
		Scanner sc = new Scanner(System.in);
		
		do {
			System.out.println("_________________________________________");
			System.out.println("********** BANKERS ALGORITHM ***********");
			System.out.println("0. Exit");
			System.out.println("1. Accept details");
			System.out.println("2. Display details");
			System.out.println("3. Display safe sequence");
			System.out.println("4. Request resources for a process");
			System.out.print("Enter your choice: ");
			ch = sc.nextByte();
			System.out.println("_________________________________________");
			
			switch(ch) {
			case 0: System.out.println("*********** PROGRAM ENDED ************");
					break;
					
			case 1:	B = Bankers.acceptDetails(sc);
					break;
					
			case 2: if(B == null)
						System.out.println("Please enter details first.");
					else
						B.displayDetails();
					break;
					
			case 3: if(B == null)
						System.out.println("Please enter details first.");
					else
						B.safeState();
					break;
					
			case 4: if(B == null)
						System.out.println("Please enter details first.");
					else
						B.resourceRequest(sc);
					break;
					
			default: System.out.println("Invalid choice.");
			}
		}while(ch!=0);

		sc.close();
	}

}
