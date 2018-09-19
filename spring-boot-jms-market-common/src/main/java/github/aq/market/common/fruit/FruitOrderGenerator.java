<<<<<<< HEAD
package github.aq.market.common.fruit;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import github.aq.market.common.Account;
import github.aq.market.common.Order;
import github.aq.market.common.OrderType;

public class FruitOrderGenerator {
	
	final int MAX_ITEMS = 7;//100
	
	BigDecimal[] costs = {new BigDecimal(0.25), new BigDecimal(0.5)};//, new BigDecimal(0.75)};
	int[] quantities = {10, 20};//, 30};
	long accountIndex = 0;
			
	public int randomSelector(int maxlimit) {
		return ThreadLocalRandom.current().nextInt(maxlimit);
	}
	
	public List<Order> generateFruitOrders(OrderType orderType) {
		List<Order> list = new ArrayList<Order>();
		long accountId = accountIndex;
		Account account = new Account();
		account.setAccountId(accountId);
		long orderId = 0;
		while(list.size() < MAX_ITEMS) {
			int randNameIndex = 0;//randomSelector(FruitName.values().length);
			int randCostIndex = randomSelector(costs.length);
			int randQuantityIndex = randomSelector(quantities.length);
			if (list.size() % 10 == 0) {
				accountId ++;
			}
			Order fruit = new Order(account, new String(orderId+""), FruitName.values()[randNameIndex].toString(), costs[randCostIndex], quantities[randQuantityIndex], orderType);
			System.out.println(fruit.toString());
			list.add(fruit);
			orderId ++;
		}
		accountIndex = accountId;
		return list;
	}
	
	public Order generateFruitOrder(Account account, String orderId, OrderType orderType) {
		
		int randNameIndex = randomSelector(FruitName.values().length);
		int randCostIndex = randomSelector(costs.length);
		int randQuantityIndex = randomSelector(quantities.length);
		
		Order fruitOrder = new Order(account, orderId, FruitName.values()[randNameIndex].toString(), costs[randCostIndex], quantities[randQuantityIndex], orderType);
		System.out.println(fruitOrder.toString());
		return fruitOrder;
	}
	
	public static void main(String[] args) {
		new FruitOrderGenerator().generateFruitOrders(OrderType.BUY);
	}
}
=======
package github.aq.market.common.fruit;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import github.aq.market.common.Account;
import github.aq.market.common.Order;
import github.aq.market.common.OrderType;

public class FruitOrderGenerator {
	
	final int MAX_ITEMS = 10;//100
	
	BigDecimal[] costs = {new BigDecimal(0.25), new BigDecimal(0.5)};//, new BigDecimal(0.75)};
	int[] quantities = {10, 20};//, 30};
	long accountIndex = 0;
			
	public int randomSelector(int maxlimit) {
		return ThreadLocalRandom.current().nextInt(maxlimit);
	}
	
	public List<Order> generateFruitOrders(OrderType orderType) {
		List<Order> list = new ArrayList<Order>();
		long accountId = accountIndex;
		Account account = new Account(accountId);
		//account.setAccountId(accountId);
		long orderId = 0;
		while(list.size() < MAX_ITEMS) {
			int randNameIndex = randomSelector(FruitName.values().length);
			int randCostIndex = randomSelector(costs.length);
			int randQuantityIndex = randomSelector(quantities.length);
			if (list.size() % 10 == 0) {
				accountId ++;
			}
			Order fruit = new Order(account, new String(orderId+""), FruitName.values()[randNameIndex].toString(), costs[randCostIndex], quantities[randQuantityIndex], orderType);
			System.out.println(fruit.toString());
			list.add(fruit);
			orderId ++;
		}
		accountIndex = accountId;
		return list;
	}
	
	public Order generateFruitOrder(Account account, String orderId, OrderType orderType) {
		
		int randNameIndex = randomSelector(FruitName.values().length);
		int randCostIndex = randomSelector(costs.length);
		int randQuantityIndex = randomSelector(quantities.length);
		
		Order fruitOrder = new Order(account, orderId, FruitName.values()[randNameIndex].toString(), costs[randCostIndex], quantities[randQuantityIndex], orderType);
		System.out.println(fruitOrder.toString());
		return fruitOrder;
	}
	
	public static void main(String[] args) {
		new FruitOrderGenerator().generateFruitOrders(OrderType.BUY);
	}
}
>>>>>>> 8c09f5362fec9c4677e317a88320840f48d842b1
