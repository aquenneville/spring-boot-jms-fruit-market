package github.aq.market;

import java.util.PriorityQueue;
import java.util.Queue;

import org.springframework.stereotype.Component;

import github.aq.market.common.Order;

public class Market {

	Queue<Order> buyOrder;
	Queue<Order> sellOrder;
	
	public Market() {
		buyOrder = new PriorityQueue<>();
		sellOrder = new PriorityQueue<>();
	}
	
	public Queue<Order> getBuyOrder() {
		return buyOrder;
	}
	public Queue<Order> getSellOrder() {
		return sellOrder;
	}
	
	
}
