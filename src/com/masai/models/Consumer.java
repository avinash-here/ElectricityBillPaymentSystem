package com.masai.models;

public class Consumer {
	private int ConsumerId;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String status;
	
	public Consumer() {}

	public Consumer(int consumerId,String firstName,String lastName,String username,String password,String status) {
		super();
		ConsumerId = consumerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.status = status;
	}	

	public int getConsumerId() {
		return ConsumerId;
	}	

	public void setConsumerId(int consumerId) {
		ConsumerId = consumerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ConsumerId=" + ConsumerId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", username=" + username + ", password=" + password + ", status=" + status;
	}
	
}
