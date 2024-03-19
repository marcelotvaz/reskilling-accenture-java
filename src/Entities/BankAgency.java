package Entities;

public class BankAgency {
	private Integer agencyNumber;
	private String state;
	private String city;
	
	public BankAgency(Integer agencyNumber, String state, String city) {
		super();
		this.agencyNumber = agencyNumber;
		this.state = state;
		this.city = city;
	}

	public Integer getAgencyNumber() {
		return agencyNumber;
	}

	public String getState() {
		return state;
	}

	public String getCity() {
		return city;
	}
	
}
