package github.aq.market;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import github.aq.market.common.Order;
import github.aq.market.common.fruit.FruitName;
import github.aq.market.common.jms.ActiveMQConfig;
import github.aq.market.common.jms.OrderMessageRequest;
@Component
public class OrderListener {
    
    @Autowired(required=true)
    @Qualifier("MarketController")
	private MarketController marketController;
	
	@JmsListener(destination = ActiveMQConfig.BUY_ORDER_QUEUE, containerFactory = "queueListenerFactory") 
	public void receiveBuyOrder(OrderMessageRequest orderRequest) {
		Order buy = orderRequest.getOrder();
		System.out.println(orderRequest.getData());
		if (FruitName.APPLE.name().equals(buy.getCommodityName())) {
			marketController.getMarkets().get(FruitName.APPLE.name()).getBuyOrder().add(buy);
		    //marketController.getBuyOrder().add(buy);
			System.out.println("received apple buy order");
			// send order on the queue
			// create queue
		}
	}
	
	@JmsListener(destination = ActiveMQConfig.SELL_ORDER_QUEUE, containerFactory = "queueListenerFactory") 
	public void receiveSellOrder(OrderMessageRequest orderRequest) {
		Order sell = orderRequest.getOrder();
		System.out.println(orderRequest.getData());
		if (FruitName.APPLE.name().equals(sell.getCommodityName())) {
			marketController.getMarkets().get(FruitName.APPLE.name()).getSellOrder().add(sell);
		    //marketController.getSellOrder().add(sell);
			System.out.println("received apple sell order");
			// send order on the queue
			// create queue
		}
	}
	
	
}
