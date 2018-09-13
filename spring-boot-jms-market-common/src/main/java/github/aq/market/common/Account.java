package github.aq.market.common;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Account implements Serializable {
	private Long accountId;
	private BigDecimal balance;
	private int quantity;
	private List<Order> orders;
	private List<Transaction> history;
	
	public Account(long accountId) {
		this.accountId = accountId;
		this.orders = new ArrayList<>();
		this.history = new ArrayList<>();
		balance = new BigDecimal(0);
		quantity = 0;
	}
	
	public void addOrder(Order order) {
		orders.add(order);
		updateBalance();
	}
	
	public void updateBalance() {
		balance = new BigDecimal(0);
		quantity = 0;
		for (Order order: orders) {
			if (order.getOrderType() == OrderType.BUY) {
				BigDecimal commodityTotalCost = order.getPrice().multiply(new BigDecimal(order.getQuantity()));
				balance = balance.add(commodityTotalCost);
			}
			if (order.getOrderType() == OrderType.SELL) {
				quantity += order.getQuantity();
			}
		}
	}
	
	public void setOrders(List<Order> orders) {
		this.orders = orders;
		updateBalance();
	}

	public Long getAccountId() {
		return accountId;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public List<Transaction> getHistory() {
		return history;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public void setHistory(List<Transaction> history) {
		this.history = history;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	
}
