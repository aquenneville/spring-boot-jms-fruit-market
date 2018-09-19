package github.aq.market.common;

import java.util.ArrayList;
import java.util.List;

public class TransactionHistory {
	
	List<Transaction> transactions = new ArrayList<>();
	
	public void addTransaction(Transaction t) {
		transactions.add(t);
	}
}
