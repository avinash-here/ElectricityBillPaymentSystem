package com.masai.main;

import java.sql.Connection;
import java.util.Scanner;

import com.masai.dao.AdminDao;
import com.masai.dao.AdminDaoImpl;
import com.masai.dao.UserDao;
import com.masai.dao.UserDaoImpl;
import com.masai.utility.DBUtil;

public class Main {
	static UserDao userdao = new UserDaoImpl();
	static AdminDao admindao = new AdminDaoImpl();
	static String option = "";
	
	static void optionsForUser(Scanner sc) {
		System.out.println("Please choose an option: \n1. LogIn as Consumer \n2. LogIn as Admin \n"+
		                   "3. Want a new Connnection \n4. Exit");
		option = sc.next();
		
		while(!option.equals("1") && !option.equals("2") && !option.equals("3") && !option.equals("4")) {
			System.out.println("\nPlease choose a valid option");	
			System.out.println("Please choose an option: \n1. LogIn as Consumer \n2. LogIn as Admin \n"+
		                       "3. Want a new Connnection \n4. Exit");
			option = sc.next();
		}
	}
	
	static String tryAgain(Scanner sc) {
		System.out.println("\n1. Try Again? \n2. Last Menu \n3. Exit");
		String opt = sc.next();
		
		while(!opt.equals("1") && !opt.equals("2") && !opt.equals("3")) {
			System.out.println("\nPlease choose a valid option");	
			System.out.println("1. Try Again? \n2. Last Menu \n3. Exit");
			opt = sc.next();
		}
		return opt;
	}
	
	static void exitMethod() {
		System.out.println("Thank you for using our services.");
		System.out.println("Visit Again!");
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("     WEL-COME TO RAJASTHAN\nELECTRICITY-BILL-PAYMENT SYSTEM");
		System.out.println();
		
		optionsForUser(sc);	
		
		boolean execution = true;
		
		while(execution) {
			switch(option) {
			case "1" :
				boolean res = userdao.userLogin(sc);
				while(!res) {
					String opt = tryAgain(sc);
					if(opt.equals("3")) {
						execution = false;
						res = true;
						exitMethod();						
					}
					else if(opt.equals("2")) {
						res = true;
						optionsForUser(sc);
					}
					else res = userdao.userLogin(sc);
				}				
				break;
				
				
			case "2" : 
				boolean res2 = admindao.adminLogin(sc);
				
				execution = false;
				
				break;
				
				
			case "3" :
				execution = false;
				System.out.println("Please contact to the admin nearest to you.\nThank you.");
				break;
				
				
			case "4" : 
				execution = false;
				exitMethod();						
			}
		}
		
		
		
		sc.close();		
	}

}
