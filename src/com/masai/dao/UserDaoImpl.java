package com.masai.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.masai.utility.DBUtil;

public class UserDaoImpl implements UserDao{

	@Override
	public boolean userLogin(Scanner sc) {		 
		
		System.out.print("Please enter your username  ");
		String user = sc.next();
		System.out.print("Pleas enter your password   ");
		String pass = sc.next();		
		
		try (Connection conn = DBUtil.getAConnection()) {
			PreparedStatement ps = conn.prepareStatement("SELECT * from consumers where username=? AND password=?");
			ps.setString(1, user);
			ps.setString(2, pass);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				String name = rs.getString("name");
				System.out.println("Welcome, " + name);
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

}
