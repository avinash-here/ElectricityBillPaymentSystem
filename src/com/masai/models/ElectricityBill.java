package com.masai.models;

import java.time.LocalDate;

public class ElectricityBill {
	private long bill_no;
	private int consumerId;
	private LocalDate billDate;
	private double unitsConsumed;
	private String status;
	
	public ElectricityBill() {}

	public ElectricityBill(long bill_no, int consumerId, LocalDate billDate, double unitsConsumed, String status) {
		super();
		this.bill_no = bill_no;
		this.consumerId = consumerId;
		this.billDate = billDate;
		this.unitsConsumed = unitsConsumed;
		this.status = status;
	}

	public long getBill_no() {
		return bill_no;
	}

	public void setBill_no(long bill_no) {
		this.bill_no = bill_no;
	}

	public int getConsumerId() {
		return consumerId;
	}

	public void setConsumerId(int consumerId) {
		this.consumerId = consumerId;
	}

	public LocalDate getBillDate() {
		return billDate;
	}

	public void setBillDate(LocalDate billDate) {
		this.billDate = billDate;
	}

	public double getUnitsConsumed() {
		return unitsConsumed;
	}

	public void setUnitsConsumed(double unitsConsumed) {
		this.unitsConsumed = unitsConsumed;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ElectricityBill [bill_no=" + bill_no + ", consumerId=" + consumerId + ", billDate=" + billDate
				+ ", unitsConsumed=" + unitsConsumed + ", status=" + status + "]";
	}	
	
}
