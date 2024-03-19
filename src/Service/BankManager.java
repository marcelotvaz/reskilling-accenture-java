package Service;

import java.util.HashMap;
import java.util.Map;

import Entities.BankAgency;

public class BankManager {
	
private Map<Integer, BankAgency> banks;
	public BankManager() {
        banks = new HashMap<>();
    }

    public void addBank(BankAgency bank) {
    	banks.put(bank.getAgencyNumber(), bank);
    }

    public BankAgency getBankNumber(String id) {
        return banks.get(id);
    }
}
