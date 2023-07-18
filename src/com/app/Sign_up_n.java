package com.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;



public class Sign_up_n  {

	public static void Signup() 
	{
		Scanner ip = new Scanner(System.in);
		System.out.println("Enter you first name");
		String first_name = ip.next();
		System.out.println("Enter your last name");
		String last_name= ip.next();
		System.out.println("Gender ");
		String gender= ip.next();
		System.out.println("Enter you Contact Number");
		long contact_number= ip.nextLong();
		System.out.println("Enter DOB");
		String date_of_birth= ip.next();
		System.out.println("Email_id");
		String email_id= ip.next();
		System.out.println("Password");
		String u_password= ip.next();

		String url="jdbc:mysql://localhost:3306/social_media";
		String username="root";
		String password="root";
		
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection con= DriverManager.getConnection(url, username, password);
			String query="select email_id from users";
			PreparedStatement ps= con.prepareStatement(query);

			ResultSet rs =ps.executeQuery();
			boolean user = false;

			while(rs.next()) {

				if(email_id.equals(rs.getString(1))) {
					System.out.println("User already exists");
					user=false;
					break;
				}else {
					user=true;
				}
			}
			if(user == true) {
				String query1= "Insert into users values(?,?,?,?,?,?,?)"; 
				ps=con.prepareStatement(query1);
				ps.setString(1,first_name);
				ps.setString(2,last_name);
				ps.setString(3,gender);
				ps.setLong(4, contact_number);
				ps.setString(5, date_of_birth);
				ps.setString(6,email_id);
				ps.setString(7, u_password);

				System.out.println("1. SUBMIT \n2. CANCEL");
				System.out.println("ENTER YOUR CHOICE :");
				int choice=ip.nextInt();
				switch (choice) {
				case 1: 
					ps.execute();
					System.out.println("Signed  In");
					break;

				case 2: System.out.println("Thank you");
				System.exit(0);

				default:System.out.println("Invalid choice");
				}

				con.close();
			}
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}


	}

}