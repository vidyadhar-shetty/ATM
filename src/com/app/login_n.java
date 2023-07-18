package com.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class login_n {
	static Boolean email=false;;
	static Boolean pass=false;


	public static void login() {
		Scanner ip=new Scanner(System.in);
		System.out.println("-------Login----------");
		System.out.println("----------ALPHA BOOK----------");
		System.out.println("User-name(Email_id) :");
		String email_id = ip.nextLine();
		System.out.println("Password :");
		String u_password=ip.nextLine();


		String url = "jdbc:mysql://localhost:3306/social_media";
		String username = "root";
		String password = "root";
		String query = "select * from users";


		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, username, password);
			Statement st = con.createStatement();
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = st.executeQuery(query);

			while(rs.next()) {

				if(email_id.equals(rs.getString(6))){
					email=true;	
				}
				if(u_password.equals(rs.getString(7))) {
					pass=true;
				}

				if(email==true && pass==true) {
					System.out.println("Login Sucessfull");
					System.out.println("First name " + rs.getString(1)+"   ");
					System.out.println("Last name " + rs.getString(2)+"   ");

					System.out.println("\nFor logout press 1");
					int Choice=ip.nextInt();
					switch (Choice) {
					case 1: {
						System.out.println("Tavu Tolagabahudu");
						System.exit(0);
						break;
					}
					}
				}
			}

			if(email==false) {
				System.err.println("User not found");
				Home.Home();
			}
			if(email==true && pass==false) {
				int count=0;
				for(int i=0;i<3;i++) {
					System.out.println("Wrong password");
					System.out.print("Please re-enter the password");
					password=ip.next();
					System.out.println();
					rs =ps.executeQuery();
					while(rs.next()) {

						if(email_id.equals(rs.getString(6))) {

							if(password.equals(rs.getString(7))) {
								count++;
								System.out.println("First name " + rs.getString(1)+"   ");
								System.out.println("Last name " + rs.getString(2)+"   ");
								break;
							}
						}
					}
				}
				if(count==0) {
					System.out.println("You have exceded the limit");
				}

			} 	

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
