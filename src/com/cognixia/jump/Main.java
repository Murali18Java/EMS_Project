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
		
		con = sc.nextLine();
		
		}while(!con.matches("y") && !con.matches("Y"));
		
		System.out.println("Thanks! For using this product.");
		System.out.println("(c)Copyright all rights reserved.");
		
		sc.close();
	}
	
}
