package github.aq.market.common.jms;

import java.io.Serializable;
import java.time.LocalDate;

import javax.print.attribute.standard.DateTimeAtCompleted;

import github.aq.market.common.Order;

public class OrderMessageRequest implements Serializable {

	//private LocalDate orderRequestTime;
	private Order order;
	
	private String data;
	
	public OrderMessageRequest() {}
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	};

	
	
//	public LocalDate getOrderRequestTime() {
//		return orderRequestTime;
//	}
//	public void setOrderRequestTime(LocalDate orderRequestTime) {
//		this.orderRequestTime = orderRequestTime;
//	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	
	
}
