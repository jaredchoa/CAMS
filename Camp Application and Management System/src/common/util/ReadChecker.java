package common.util;

import java.util.Scanner;


public class ReadChecker {
	
	public static int checkInt() {		
			Scanner sc = new Scanner(System.in);
			try {
				return sc.nextInt();
				
			}catch( Exception e) {
				System.out.println("Invalid response. Please re-enter an integer");
			    System.out.print("Please choose an option: ");
				return checkInt();
			}
	}
	
	public static int verifyInt(int min, int max) {
		int input=-1;
		while (true) {
			input = checkInt();
			if (input<min || input > max) {
				System.out.println("Response is out of range.");
				continue;
			}
			else
				return input;
		}
	}
	
//	public static String checkStr() {
//		Scanner sc = new Scanner(System.in);
//		try {
//			return sc.nextLine();
//		}catch (Exception e) {
//			System.out.println("Invalid response. Please re-enter your input.");
//			return checkStr();
//		}
//		
//	}
	
}
