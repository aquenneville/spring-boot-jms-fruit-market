package github.aq.market.common;

import java.io.Serializable;
import java.math.BigDecimal;

public class Order implements Serializable {

	private Account account;
	private String orderId;
	private String parentOrderId;
	private String commodityName;
	private BigDecimal price;
	private int quantity;
	private OrderType orderType;
	
	public Order(Account account, String orderId, String commodityName, BigDecimal price, int quantity, OrderType orderType) {
		this.account = account;
		this.orderId = orderId;
		this.commodityName = commodityName;
		this.price = price;
		this.quantity = quantity;
		this.orderType = orderType;
	}
	
	public String toString() {
		return "Account Id: " + account.getAccountId() + " Order Id: " + orderId 
				+ " Commodity name: " + commodityName + " cost: " + price + " quantity: " + quantity + " orderType: " + orderType; 
	}

	public Account getAccount() {
		return account;
	}

	public String getCommodityName() {
		return commodityName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

	public OrderType getOrderType() {
		return orderType;
	}

	public String getOrderId() {
		return orderId;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getParentOrderId() {
		return parentOrderId;
	}

	public void setParentOrderId(String parentOrderId) {
		this.parentOrderId = parentOrderId;
	}

}
