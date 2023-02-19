package com.masai.main;

import java.sql.Connection;
import com.masai.utility.DBUtil;

public class Main {
	
	public static void main(String[] args) {
		
		Connection con = DBUtil.getAConnection();
		System.out.println("Connection Done");
				
	}

}
