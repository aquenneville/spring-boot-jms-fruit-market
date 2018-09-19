package github.aq.market;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.BeforeClass;
import org.junit.Test;

import github.aq.market.common.Account;
import github.aq.market.common.Order;
import github.aq.market.common.OrderType;
import github.aq.market.common.Transaction;
import github.aq.market.common.fruit.FruitName;

public class MarketOrderMatcherTest {
	
	static ExecutorService executor;
	static Queue<Order> buyQueue;
	static Queue<Order> sellQueue;
	List<Transaction> transactions = new ArrayList<>();
	
	public class OrderMatchTask implements Runnable {
		
		Transaction transaction; 
		
		public OrderMatchTask() {
		}
		
		@Override
		public void run() {
			MarketOrderMatcher matcher = new MarketOrderMatcher();
			Order buy = buyQueue.peek();
			Order sell = sellQueue.peek();
			if (buy != null || sell != null) {
				transaction = matcher.updateBalances(buyQueue, sellQueue, buy, sell);
				transactions.add(transaction);
			}
		}
		
		public Transaction getTransaction() {
			return transaction;
		}
	}
	
	@BeforeClass
	public static void runOnce() {
		executor = Executors.newFixedThreadPool(1);
		buyQueue = new PriorityQueue<Order>();
		sellQueue = new PriorityQueue<Order>();
	}
	
	@Test
	public void testTransactionCreation() throws InterruptedException, ExecutionException {
		Account buyer = new Account();
		Account seller = new Account();
		BigDecimal price = new BigDecimal(0.25);
		Order sell = new Order(buyer, "1", FruitName.APPLE.name(), price, 1, OrderType.SELL);
		Order sell2 = new Order(buyer, "2", FruitName.APPLE.name(), price, 1, OrderType.SELL);
		Order sell3 = new Order(buyer, "3", FruitName.APPLE.name(), price, 1, OrderType.SELL);
		Order buy = new Order(seller, "1", FruitName.APPLE.name(), price, 5, OrderType.BUY);
		buyQueue.add(buy);
		sellQueue.add(sell);
		sellQueue.add(sell2);
		sellQueue.add(sell3);
		while(buyQueue.size() > 0 && sellQueue.size() > 0) {
			System.out.println(buyQueue.size());
			OrderMatchTask omt = new OrderMatchTask();
			CompletableFuture cf = new CompletableFuture();
			CompletableFuture<Void> future = cf.runAsync(omt);
			if (future != null) {
				future.get();
			}

			if (omt.getTransaction() != null) {	
				System.out.println(omt.getTransaction());
			} else {
				System.out.println("transaction could not be created");
			}
			System.out.println("Done");
			
		}
		assertTrue(transactions.size() == 3);
	}
	
	//@Test
	public void testMatchQuantityEqual() {
		Queue<Order> buyQueue = new PriorityQueue<Order>();
		Queue<Order> sellQueue = new PriorityQueue<Order>();
		MarketOrderMatcher matcher = new MarketOrderMatcher();
		Account buyer = new Account();
		Account seller = new Account();
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
		Account buyer = new Account();
		Account seller = new Account();
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
		Account buyer = new Account();
		Account seller = new Account();
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
