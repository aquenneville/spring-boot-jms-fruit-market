package github.aq.market;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.PriorityQueue;
import java.util.Queue;

import org.junit.Test;

import github.aq.market.common.Account;
import github.aq.market.common.Order;
import github.aq.market.common.OrderType;
import github.aq.market.common.Transaction;
import github.aq.market.common.fruit.FruitName;

public class MarketOrderMatcherTest {

	
	@Test
	public void testMatchQuantityEqual() {
		Queue<Order> buyQueue = new PriorityQueue<Order>();
		Queue<Order> sellQueue = new PriorityQueue<Order>();
		MarketOrderMatcher matcher = new MarketOrderMatcher();
		Account buyer = new Account(1L);
		Account seller = new Account(1L);
		BigDecimal price = new BigDecimal(0.25);
		
		String orderId = "1";
		Order sell = new Order(buyer, orderId, FruitName.APPLE.name(), price, 1, OrderType.SELL);
		Order buy = new Order(seller, orderId, FruitName.APPLE.name(), price, 1, OrderType.BUY);
		
		Transaction t = matcher.updateBalances(buyQueue, sellQueue, buy, sell);
		System.out.println(t);
		
		assertTrue(sellQueue.size() == 0);
		assertTrue(buyQueue.size() == 0);
	}

	@Test
	public void testMatchBuyGreaterQuantity() {
		Queue<Order> buyQueue = new PriorityQueue<Order>();
		Queue<Order> sellQueue = new PriorityQueue<Order>();
		MarketOrderMatcher matcher = new MarketOrderMatcher();
		Account buyer = new Account(1L);
		Account seller = new Account(1L);
		BigDecimal price = new BigDecimal(0.25);
		
		String orderId = "1";
		Order sell = new Order(seller, orderId, FruitName.APPLE.name(), price, 1, OrderType.SELL);
		Order buy = new Order(buyer, orderId, FruitName.APPLE.name(), price, 2, OrderType.BUY);
		buyer.addOrder(buy);
		seller.addOrder(sell);
		System.out.println(buyer.getQuantity());
		assertTrue(buy.getAccount().getQuantity() == 0);
		Transaction t = matcher.updateBalances(buyQueue, sellQueue, buy, sell);
		System.out.println(t);
		
		assertTrue(sellQueue.size() == 0);
		assertTrue(buyQueue.size() == 1);
		Order newBuyOrder = buyQueue.peek();
		System.out.println("parent order id: " + newBuyOrder.getParentOrderId());
		System.out.println("order id: " + newBuyOrder.getOrderId());
		System.out.println("quantity: " + newBuyOrder.getQuantity());
		assertTrue(newBuyOrder.getAccount().getQuantity() == 1);
		assertTrue(sell.getAccount().getQuantity() == 0);
	}
	
	@Test
	public void testMatchSellGreaterQuantity() {
		Queue<Order> buyQueue = new PriorityQueue<Order>();
		Queue<Order> sellQueue = new PriorityQueue<Order>();
		MarketOrderMatcher matcher = new MarketOrderMatcher();
		Account buyer = new Account(1L);
		Account seller = new Account(1L);
		BigDecimal price = new BigDecimal(0.25);
		
		String orderId = "1";
		Order sell = new Order(seller, orderId, FruitName.APPLE.name(), price, 2, OrderType.SELL);
		Order buy = new Order(buyer, orderId, FruitName.APPLE.name(), price, 1, OrderType.BUY);
		buyer.addOrder(buy);
		seller.addOrder(sell);
		System.out.println(buyer.getQuantity());
		assertTrue(buy.getAccount().getQuantity() == 0);
		Transaction t = matcher.updateBalances(buyQueue, sellQueue, buy, sell);
		System.out.println(t);
		
		assertTrue(sellQueue.size() == 1);
		assertTrue(buyQueue.size() == 0);
		Order newSellOrder = sellQueue.peek();
		System.out.println("parent order id: " + newSellOrder.getParentOrderId());
		System.out.println("order id: " + newSellOrder.getOrderId());
		System.out.println("quantity: " + newSellOrder.getQuantity());
		assertTrue(newSellOrder.getAccount().getQuantity() == 1);
		assertTrue(sell.getAccount().getQuantity() == 1);
	}
	
}
