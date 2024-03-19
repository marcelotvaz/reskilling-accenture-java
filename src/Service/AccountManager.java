package Service;
import java.util.HashMap;
import java.util.Map;

import Entities.Account;

public class AccountManager {


	private Map<String, Account> accounts;
	
	public AccountManager() {
        accounts = new HashMap<>();
    }

    public void addAccount(Account account) {
        accounts.put(account.getAccountNumber().toString(), account);
    }

    public Account getAccount(String id) {
        return accounts.get(id);
    }

}
