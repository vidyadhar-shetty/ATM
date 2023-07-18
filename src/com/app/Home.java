package com.app;

import java.util.Scanner;

public class Home {
	public static void Home() {
		Scanner ip=new Scanner(System.in);
		System.out.println("----------ALPHA BOOK----------");
		System.out.println("1. Login \n2. Signup \n3. EXIT");
		System.out.println("ENTER YOUR CHOICE :");

		int choice=ip.nextInt();
		System.out.println("your choice is : "+choice);
		switch (choice) {
		case 1: {
			login_n.login();
			break;
		}
		case 2:{
			Sign_up_n.Signup();
			break;
		}case 3:{
			System.exit(0);
		}
		default:
			System.out.println("Please enter valid input");
		}
	}

	public static void main(String[] args) {
		Home();

	}
}

