package Entities;

import java.util.ArrayList;
import java.util.List;

import Entities.enums.AccountStatus;

public class Account {

	private Integer accountNumber;
	private Client client;
	private BankAgency bankAgency;
	private double balance;
	private double withdrawalLimit;
	private List<Transaction> transactions = new ArrayList<>();
	private AccountStatus status;
	
	public Account() {
	}
	
	public Account(Integer accountNumber, Client client, BankAgency bankAgency,
			double withdrawalLimit, AccountStatus status) {
		super();
		this.accountNumber = accountNumber;
		this.client = client;
		this.bankAgency = bankAgency;
		this.balance = 0;
		this.withdrawalLimit = withdrawalLimit;
		this.status = status;
	}

	public Integer getAccountNumber() {
		return accountNumber;
	}

	public Client getClient() {
		return client;
	}

	public BankAgency getBankAgency() {
		return bankAgency;
	}

	public double getBalance() {
		return balance;
	}

	public double getWithdrawalLimit() {
		return withdrawalLimit;
	}

	public void setWithdrawalLimit(double withdrawalLimit) {
		this.withdrawalLimit = withdrawalLimit;
	}
	
	public List<Transaction> getTransactions() {
		return transactions;
	}
	public void addTransaction(Transaction tr) {
		List<Transaction> trList = getTransactions();
		trList.add(tr);
	}

	public void deposit(double value){
		this.balance = getBalance() + value;
	}
	
	public void withdrawal(double value) {
		this.balance = getBalance() - value;
	}
}
