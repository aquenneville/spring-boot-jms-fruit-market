package github.aq.market;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import github.aq.market.common.Order;
import github.aq.market.common.jms.OrderMessageRequest;
@Component
public class AppleOrderListener {

	@Autowired
	Market appleMarket;
	
	@JmsListener(destination = "APPLE-BUY-ORDER", containerFactory = "myFactory") 
	public void receiveBuyOrder(OrderMessageRequest orderRequest) {
		Order buy = orderRequest.getOrder();
		appleMarket.getBuyOrder().add(buy);
		System.out.println("received apple buy order");
		// send order on the queue
		// create queue
	}
	
	@JmsListener(destination = "APPLE-SELL-ORDER", containerFactory = "myFactory") 
	public void receiveSellOrder(OrderMessageRequest orderRequest) {
		Order sell = orderRequest.getOrder();
		appleMarket.getSellOrder().add(sell);
		System.out.println("received apple sell order");
		// send order on the queue
		// create queue
	}
	
	
}
