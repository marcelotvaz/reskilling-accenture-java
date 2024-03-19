package Entities;

import java.util.Date;

public class Client {
	
	private Integer Id;
	private String name;
	private String cpfcnpj;
	private Date birthDate;
	
	public Client(String name, String cpfcnpj, Date birthDate) {
		super();
		this.name = name;
		this.cpfcnpj = cpfcnpj;
		this.birthDate = birthDate;
	}

	public Integer getId() {
		return Id;
	}

	public String getName() {
		return name;
	}

	public String getCpfcnpj() {
		return cpfcnpj;
	}

	public Date getBirthDate() {
		return birthDate;
	}
}
