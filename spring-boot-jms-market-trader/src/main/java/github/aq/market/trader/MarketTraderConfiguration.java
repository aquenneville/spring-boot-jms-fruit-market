package github.aq.market.trader;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import github.aq.market.common.Account;
import github.aq.market.common.Order;
import github.aq.market.common.OrderType;
import github.aq.market.common.fruit.FruitOrderGenerator;
@Component
public class MarketTraderConfiguration {

	@Bean
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	public TraderAccounts getTraderAccounts() {
		TraderAccounts accounts = new TraderAccounts();
		FruitOrderGenerator gen = new FruitOrderGenerator();
		List<Order> randomBuyOrders = gen.generateFruitOrders(OrderType.BUY);
		
		List<Order> distinctAccountIds = randomBuyOrders.stream().filter(StreamUtil.distinctByKey(o -> o.getAccount().getAccountId()))
				.collect(Collectors.toList());
		
		for (Order order: distinctAccountIds) {
			List<Order> buyOrdersByAccount =  randomBuyOrders.stream().filter(o -> o.getAccount().getAccountId().equals(order.getAccount().getAccountId())).collect(Collectors.toList());
			Account account = new Account();
			account.setAccountId(order.getAccount().getAccountId());
			account.setOrders(buyOrdersByAccount);
			accounts.addAccount(account);
		}
		
		List<Order> randomSellOrders = gen.generateFruitOrders(OrderType.SELL);
		distinctAccountIds = randomSellOrders.stream().distinct()
				.collect(Collectors.toList());
		for (Order order: distinctAccountIds) {
			List<Order> sellOrdersByAccount = randomSellOrders.stream().filter(o -> o.getAccount().getAccountId().equals(order.getAccount().getAccountId())).collect(Collectors.toList());
			Account account = new Account();
			account.setAccountId(order.getAccount().getAccountId());
			account.setOrders(sellOrdersByAccount);
			accounts.addAccount(account);
		}
		//System.out.println(accounts.getAccounts().size());
		return accounts;
	}
	
	
}
