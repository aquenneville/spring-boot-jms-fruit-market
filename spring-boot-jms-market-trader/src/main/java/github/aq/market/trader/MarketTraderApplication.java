package github.aq.market.trader;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.stereotype.Component;


//import github.aq.market.common.Account;
//import github.aq.market.common.Order;
//import github.aq.market.common.OrderType;
//import github.aq.market.common.fruit.FruitOrderGenerator;
//import github.aq.market.common.jms.ActiveMQConfig;
//import github.aq.market.common.jms.OrderMessageRequest;
//import github.aq.market.common.jms.Task1MessageRequest;

@EnableJms
@SpringBootApplication
@ComponentScan(basePackages = {"github.aq.market"})
public class MarketTraderApplication {
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
//    @Bean // Serialize message content to json using TextMessage
//    public MessageConverter jacksonJmsMessageConverter() {
//        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
//        converter.setTargetType(MessageType.TEXT);
//        converter.setTypeIdPropertyName("_type");
//        return converter;
//    }
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(MarketTraderApplication.class, args);
		//JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
		
        // Send a message with a POJO - the template reuse the message converter
        //System.out.println("Sending an email message.");
        //jmsTemplate.convertAndSend("mailbox.queue", new Task1MessageRequest("info@example.com", "Hello"));
	}
	//https://memorynotfound.com/spring-boot-passing-command-line-arguments-example
	
//	@Component
//	public class LoadDataOnStartup implements CommandLineRunner {
//		public void run(String... args) {
//			FruitOrderGenerator gen = new FruitOrderGenerator();
//			List<Order> randomBuyOrders = gen.generateFruitOrders(OrderType.BUY);
//			ApplicationContext context = new AnnotationConfigApplicationContext(MarketTraderApplication.class);
//			TraderAccounts accounts = context.getBean(TraderAccounts.class);
//			
//			List<Order> distinctAccountIds = randomBuyOrders.stream().filter(StreamUtil.distinctByKey(o -> o.getAccountId()))
//					.collect(Collectors.toList());
//			
//			for (Order order: distinctAccountIds) {
//				List<Order> buyOrdersByAccount =  randomBuyOrders.stream().filter(o -> o.getAccountId().equals(order.getAccountId())).collect(Collectors.toList());
//				Account account = new Account();
//				account.setAccountId(order.getAccountId());
//				account.setOrders(buyOrdersByAccount);
//				accounts.addAccount(account);
//			}
//			
//			List<Order> randomSellOrders = gen.generateFruitOrders(OrderType.SELL);
//			distinctAccountIds = randomSellOrders.stream().distinct()
//					.collect(Collectors.toList());
//			for (Order order: distinctAccountIds) {
//				List<Order> sellOrdersByAccount = randomSellOrders.stream().filter(o -> o.getAccountId().equals(order.getAccountId())).collect(Collectors.toList());
//				Account account = new Account();
//				account.setAccountId(order.getAccountId());
//				account.setOrders(sellOrdersByAccount);
//				accounts.addAccount(account);
//			}
//			System.out.println(accounts.getAccounts().size());
//		}
//	}
}
