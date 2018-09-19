package github.aq.market;

import java.math.BigDecimal;
import java.util.List;
import java.util.Queue;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Async;

import github.aq.market.common.Order;
import github.aq.market.common.Transaction;
import github.aq.market.common.fruit.FruitName;
import github.aq.market.common.jms.TransactionMessage;

public class MarketOrderMatcher {

	@Autowired
	MarketController marketController;
	
	@Autowired
	JmsTemplate jmsTemplate;
	
	public Transaction updateBalances(Queue<Order> buyQueue, Queue<Order> sellQueue, Order buy, Order sell) {
		Transaction transaction = new Transaction(); 
		transaction.setBuyOrder(buy);
		transaction.setSellOrder(sell);
		BigDecimal totalCost = sell.getPrice().multiply(new BigDecimal(sell.getQuantity()));
		BigDecimal newBuyBalance = buy.getAccount().getBalance().subtract(totalCost);
		BigDecimal newSellBalance = sell.getAccount().getBalance().add(totalCost);
		buy.getAccount().setBalance(newBuyBalance);
		sell.getAccount().setBalance(newSellBalance);
		
		buy.getAccount().getOrders().add(buy);
		sell.getAccount().getOrders().add(sell);
		buy.getAccount().getHistory().add(transaction);
		sell.getAccount().getHistory().add(transaction);
		// new balance
		System.out.println(sell.getQuantity() + " " +buy.getQuantity());
		if (sell.getQuantity() < buy.getQuantity()) {
			buy.getAccount().setQuantity(buy.getQuantity() - sell.getQuantity());
			sell.getAccount().setQuantity(0);
			String buyUUID = UUID.randomUUID().toString();
			Order newBuyOrder = new Order(buy.getAccount(), buyUUID, buy.getCommodityName(), buy.getPrice(), (buy.getQuantity() - sell.getQuantity()), buy.getOrderType());
			newBuyOrder.setParentOrderId(buy.getOrderId());
			buy.getAccount().getOrders().add(newBuyOrder);
			buyQueue.add(newBuyOrder);
			System.out.println("new buy: " + newBuyOrder.getOrderId() + " added");
			
		} else if (sell.getQuantity() > buy.getQuantity()) {
			buy.getAccount().setQuantity(0);
			sell.getAccount().setQuantity(sell.getQuantity() - buy.getQuantity());
			String sellUUID = UUID.randomUUID().toString();
			Order newSellOrder = new Order(sell.getAccount(), sellUUID, sell.getCommodityName(), sell.getPrice(), (sell.getQuantity() - buy.getQuantity()), sell.getOrderType());
			newSellOrder.setParentOrderId(sell.getOrderId());
			sell.getAccount().getOrders().add(newSellOrder);
			sellQueue.add(newSellOrder);
			System.out.println("new sell: " + newSellOrder.getOrderId() + " added");
				
		} else if (sell.getQuantity() == buy.getQuantity()) {
			buy.getAccount().setQuantity(0);
			sell.getAccount().setQuantity(0);
		}
		System.out.println("sell: " + sell.getOrderId() + " removed");
		System.out.println("buy: " + buy.getOrderId() + " removed");
		sellQueue.remove(sell);
		buyQueue.remove(buy);
		
		transaction.setTransactionId(UUID.randomUUID().toString());
		return transaction;
	}
	
	@Async
	public void matchAppleOrder() {
		Market appleMarket = marketController.getMarkets().get(FruitName.APPLE.name());
		Order buy = appleMarket.getBuyOrder().peek();
		
		List<Order> sellOrders = appleMarket.getSellOrder()
				.stream().filter(o -> o.getPrice() == buy.getPrice()).collect(Collectors.toList());
		
		if (sellOrders != null && sellOrders.size() > 0) {
			Order sell = sellOrders.get(0);
			Transaction transaction = updateBalances(appleMarket.buyOrder, appleMarket.sellOrder, sell, buy);
			
			TransactionMessage transactionMessage = new TransactionMessage();
			transactionMessage.setTransaction(transaction);
			jmsTemplate.convertAndSend("TRANSACTIONS", transactionMessage);
		}

		// loop on the apple buy order: get the first
		// loop on the apple sell order until == price or end of queue
		// create transaction 
		//   update order buy 
		//     substract balance
		//   update order sell
		//     increase balance
		//     substract quantity
		//     increase receivedQuantity
		// if order buy balance = 0 then remove from list
		// if order sell quantity = 0 then remove from list
		// send transaction to buyer account 
		// send transaction to seller account
	}
}
