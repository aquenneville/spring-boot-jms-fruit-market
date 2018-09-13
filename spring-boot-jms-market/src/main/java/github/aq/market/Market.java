package github.aq.market;

import java.util.Queue;

import org.springframework.stereotype.Component;

import github.aq.market.common.Order;
@Component
public class Market {

	Queue<Order> buyOrder;
	Queue<Order> sellOrder;
	
	public Queue<Order> getBuyOrder() {
		return buyOrder;
	}
	public Queue<Order> getSellOrder() {
		return sellOrder;
	}
	
	
}
