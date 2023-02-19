package com.masai.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.masai.exceptions.ConsumerException;
import com.masai.models.Consumer;
import com.masai.utility.DBUtil;

public class AdminDaoImpl implements AdminDao{

	@Override
	public boolean adminLogin(Scanner sc) {

		System.out.print("Please enter your username  ");
		String user = sc.next();
		System.out.print("Pleas enter your password   ");
		String pass = sc.next();		
		
		try (Connection conn = DBUtil.getAConnection()) {
			PreparedStatement ps = conn.prepareStatement("SELECT * from admin where username=? AND password=?");
			ps.setString(1, user);
			ps.setString(2, pass);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				String name = rs.getString("name");
				System.out.println("\nWelcome, " + name);
				return true;
			}
			else {
				System.out.println("Invalid Credentials!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		return false;
	}

	@Override
	public boolean registerANewCustomer(Scanner sc) {
		
		System.out.print("Please enter Consumer's First Name: ");
		String first = sc.next();
		System.out.print("Pleas enter Consumer's Last Name:  ");
		String last = sc.next();		
		System.out.print("Please enter a Username ");
		String user = sc.next();
		System.out.print("Please enter a Password ");
		String pass = sc.next();
			
		try (Connection conn = DBUtil.getAConnection()) {
			boolean find = true;
			
			while(find) {
				PreparedStatement ps = conn.prepareStatement("SELECT * from consumers where username=?");
				ps.setString(1, user);
				
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
					System.out.println("Username already exists!");
					System.out.print("Please enter a different username ");
					user = sc.next();
				}else {
					find = false;
				}
			}	
			String insertConsumer = "INSERT into CONSUMERS (firstName, lastName, username, password) values(?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(insertConsumer);
			ps.setString(1, first);
			ps.setString(2, last);
			ps.setString(3, user);
			ps.setString(4, pass);			
			
			int res = ps.executeUpdate();
			if(res > 0) {
				System.out.println("Consumer Registered Successfully.");
				return true;
			}
			else {
				System.out.println("Something went wrong!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		return false;
	}

	
	@Override
	public List<Consumer> viewAllConsumers() throws ConsumerException{
		List<Consumer> list = new ArrayList<>(); 
		try (Connection conn = DBUtil.getAConnection()) {
			PreparedStatement ps = conn.prepareStatement("SELECT * from Consumers");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				list.add(new Consumer(rs.getInt(1), rs.getString(2), rs.getString(3), 
						              rs.getString(4), rs.getString(5), rs.getString(6)));
			}
			
			if(list.size() == 0) throw new ConsumerException("No Consumers Found!");			
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	
	@Override
	public void deleteConsumer(String username) throws ConsumerException {
				
		try (Connection con = DBUtil.getAConnection()) {
			PreparedStatement ps = con.prepareStatement("select * from consumers where username = ?");
			ps.setString(1, username);
			
			ResultSet rs = ps.executeQuery();
			if(!rs.next()) {
				throw new ConsumerException("No Consumer with the given username Found!");
			}
			else {
				if(rs.getString(6).equals("inactive")) {
					System.out.println(rs.getString(2) +" "+ rs.getString(3) + " with username '" + rs.getString(4) + 
					           "' is already inactive.");
					return;
				}
				ps = con.prepareStatement("UPDATE consumers SET status = 'inactive' where username = ?");
				ps.setString(1, username);
				int x = ps.executeUpdate();
				if(x > 0)
				System.out.println(rs.getString(2) +" "+ rs.getString(3) + " with username '" + rs.getString(4) + 
						           "' is inactive now.");
				else 
					System.out.println("Something went wrong, Consumer is not deleted.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
