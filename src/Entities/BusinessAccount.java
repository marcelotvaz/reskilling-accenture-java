package Entities;

import Entities.enums.AccountStatus;

public class BusinessAccount extends Account {
	
	private Integer score;

	public BusinessAccount(Integer score) {
		super();
		this.score = score;
	}

	public BusinessAccount(Integer accountNumber, Client client, BankAgency bankAgency, double withdrawalLimit,
			AccountStatus status, Integer score) {
		super(accountNumber, client, bankAgency, withdrawalLimit, status);
		this.score = score;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

}
