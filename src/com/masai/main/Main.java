package com.masai.main;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

import com.masai.dao.AdminDao;
import com.masai.dao.AdminDaoImpl;
import com.masai.dao.UserDao;
import com.masai.dao.UserDaoImpl;
import com.masai.exceptions.ConsumerException;
import com.masai.models.Consumer;
import com.masai.utility.DBUtil;

public class Main {
	static UserDao userdao = new UserDaoImpl();
	static AdminDao admindao = new AdminDaoImpl();
	static String option = "";
	
	static void optionsForAll(Scanner sc) {
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
	
	static String optionsForAdmin(Scanner sc) {
		System.out.println("Please choose an option: \n1. Register a new Consumer \n2. View all Consumers \n"+
		                   "3. View bill of a Consumer \n4. View all the bills \n"+
						   "5. View all Paid and Pending bills \n6. Delete a Consumer \n7. Logout");
		String opt = sc.next();
		
		while(!opt.equals("1") && !opt.equals("2") && !opt.equals("3") && !opt.equals("4") &&
			  !opt.equals("5") && !opt.equals("6") && !opt.equals("7") ) {
			System.out.println("\nPlease choose a valid option");	
			System.out.println("Please choose an option: \n1. Register a new Consumer \n2. View all Consumers \n"+
	                   "3. View bill of a Consumer \n4. View all the bills \n"+
					   "5. View all Paid and Pending bills \n6. Delete a Consumer \n7. Logout");
			opt = sc.next();
		}
		return opt;
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
		
		optionsForAll(sc);	
		
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
						optionsForAll(sc);
					}
					else res = userdao.userLogin(sc);
				}				
				break;
				
				
			case "2" : 
				boolean res2 = admindao.adminLogin(sc);
				while(!res2) {
					String opt = tryAgain(sc);
					if(opt.equals("3")) {
						execution = false;
						res2 = true;
						exitMethod();						
					}
					else if(opt.equals("2")) {
						res2 = true;
						optionsForAll(sc);
					}
					else res2 = admindao.adminLogin(sc);
				}
				String opt = optionsForAdmin(sc);
				if(opt.equals("1")) {
					admindao.registerANewCustomer(sc);
				}
				else if(opt.equals("2")) {
					try {
						List<Consumer> list = admindao.viewAllConsumers();
						System.out.println(" -> All Consumer Details: ");
						for(Consumer c : list) System.out.println(c);
						
					} catch (ConsumerException e) {
						System.out.println(e.getMessage());
					}
				}
				else if(opt.equals("6")) {
					
					System.out.println("Enter username of the consumer you want to delete");
					String user = sc.next();
					try {
						admindao.deleteConsumer(user);
					} catch (ConsumerException e) {
						System.out.println(e.getMessage());
					}
					
				}
				else if(opt.equals("7")) {
					execution = false;			
					exitMethod();						
				}
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
