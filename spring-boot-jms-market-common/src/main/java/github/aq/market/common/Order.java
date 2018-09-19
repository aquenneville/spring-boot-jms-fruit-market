<<<<<<< HEAD
package github.aq.market.common;

import java.io.Serializable;
import java.math.BigDecimal;

public class Order implements Serializable, Comparable<Order> {

	private Account account;
	private String orderId;
	private String parentOrderId;
	private String commodityName;
	private BigDecimal price;
	private int quantity;
	private OrderType orderType;
	
	public Order() {};
	
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
				+ " Parent order id: " + parentOrderId 
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

	@Override
	public int compareTo(Order o) {
		return this.getPrice().compareTo(o.getPrice());
	}



}
=======
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
>>>>>>> 8c09f5362fec9c4677e317a88320840f48d842b1
