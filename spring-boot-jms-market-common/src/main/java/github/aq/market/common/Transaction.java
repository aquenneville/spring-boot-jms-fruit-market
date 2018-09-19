package github.aq.market.common;

import java.io.Serializable;
import java.time.LocalDate;

public class Transaction implements Serializable {

	private String transactionId;
	private Order buyOrder;
	private Order sellOrder;
	private LocalDate transactionTime;
	
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public Order getBuyOrder() {
		return buyOrder;
	}
	public void setBuyOrder(Order buyOrder) {
		this.buyOrder = buyOrder;
	}
	public Order getSellOrder() {
		return sellOrder;
	}
	public void setSellOrder(Order sellOrder) {
		this.sellOrder = sellOrder;
	}

	public void setTransactionTime(LocalDate transactionTime) {
		this.transactionTime = transactionTime;
	}
	
	public LocalDate getTransactionTime() {
		return transactionTime;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Transaction id: ").append(transactionId).append(" {");
		sb.append(buyOrder.toString());
 		sb.append(" ");
		sb.append(sellOrder.toString());
		sb.append("}");
		return sb.toString();
	}
}
