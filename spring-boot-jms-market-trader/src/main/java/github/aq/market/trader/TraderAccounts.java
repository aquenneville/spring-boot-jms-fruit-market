<<<<<<< HEAD
package github.aq.market.trader;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import github.aq.market.common.Account;

//@Service
public class TraderAccounts {
	
	private Map<Long, Account> accounts;
	
	public TraderAccounts() {
		accounts = new HashMap<>();
	}
	
	public void addAccount(Account account) {
		if (accounts == null) {
			accounts = new HashMap<>();
		}
		accounts.put(account.getAccountId(), account);
	}
	
	public Map<Long, Account> getAccounts() {
		return accounts;
	}

	public Account getAccount(Long accountId) {
		if (accounts.containsKey(accountId)) {
			return accounts.get(accountId);
		} else {
			throw new IllegalStateException("Error: Account id " + accountId + " does not exists!");
		}
	}
}
=======
package github.aq.market.trader;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import github.aq.market.common.Account;

//@Service
public class TraderAccounts {
	
	private Map<Long, Account> accounts;
	
	public TraderAccounts() {
		accounts = new HashMap<>();
	}
	
	public void addAccount(Account account) {
		if (accounts == null) {
			accounts = new HashMap<>();
		}
		accounts.put(account.getAccountId(), account);
	}
	
	public Map<Long, Account> getAccounts() {
		return accounts;
	}

	public Account getAccount(Long accountId) {
		if (accounts.containsKey(accountId)) {
			return accounts.get(accountId);
		} else {
			throw new IllegalStateException("Error: Account id " + accountId + " does not exists!");
		}
	}
}
>>>>>>> 8c09f5362fec9c4677e317a88320840f48d842b1
