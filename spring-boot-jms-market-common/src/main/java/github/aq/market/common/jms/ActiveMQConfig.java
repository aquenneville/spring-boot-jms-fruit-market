package github.aq.market.common.jms;

import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;

public class ActiveMQConfig {

    public static final String BUY_ORDER_QUEUE = "buy-order-queue";
	public static final String SELL_ORDER_QUEUE = "sell-order-queue";
    
    @Bean
    public Queue appleBuyOrderQueue() {
        return new ActiveMQQueue(BUY_ORDER_QUEUE);
    }
}
