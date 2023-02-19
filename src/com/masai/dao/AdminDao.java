package com.masai.dao;

import java.util.List;
import java.util.Scanner;

import com.masai.exceptions.ConsumerException;
import com.masai.models.Consumer;

public interface AdminDao {
	
	boolean adminLogin(Scanner sc);
	
	boolean registerANewCustomer(Scanner sc);
	
	List<Consumer> viewAllConsumers() throws ConsumerException;
	
	void deleteConsumer(String username) throws ConsumerException;

}
