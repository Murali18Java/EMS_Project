package com.cognixia.jump;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
		EmsMenu menu = new EmsMenu();
		
		String con = "Y";
		Scanner sc = new Scanner(System.in);
		
		do {
		
		menu.mainMenu();
		
		System.out.println("Do you want to exit ?(Y/N) :");
		//sc.nextLine();
		con = sc.nextLine();
		
		}while(!con.matches("y") && !con.matches("Y"));
		
		sc.close();
	}
	
}
