package Entities;

public class Transaction {
	private String operation;
	private String date;
	private double value;
	
	public Transaction(String operation, String date, double value) {
		this.operation = operation;
		this.date = date;
		this.value = value;
	}

	public String getOperation() {
		return operation;
	}

	public String getDate() {
		return date;
	}

	public double getValue() {
		return value;
	}
	
}
