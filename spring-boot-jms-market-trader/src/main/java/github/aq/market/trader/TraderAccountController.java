package github.aq.market.trader;

import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.MediaType;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import github.aq.market.common.Account;
import github.aq.market.common.Order;
import github.aq.market.common.jms.OrderMessageRequest;

@RestController
@RequestMapping("/api/v1/accounts")
public class TraderAccountController {//implements ApplicationContextAware {

	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	JmsTemplate jmsTemplate;
	
	@RequestMapping(path = "account/{accountId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Account listAccounts(@PathVariable Long accountId) {
		TraderAccounts accounts = applicationContext.getBean(TraderAccounts.class);
//		String[] allBeanNames = applicationContext.getBeanDefinitionNames();
//		
//		for (String name: allBeanNames) {
//			System.out.println(name);
//		}
		Account a = accounts.getAccount(accountId);
		//accounts.getAccounts().remove(accountId);
		return a;
	}

//	@Override
//	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
//		this.applicationContext = arg0;
//	}
	
	@RequestMapping(path = "send-orders", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String sendOrders() {
		TraderAccounts accounts = applicationContext.getBean(TraderAccounts.class);
		for (Long accountId: accounts.getAccounts().keySet()) {
			List<Order> orders = accounts.getAccount(accountId).getOrders();
			for (Order order: orders) {
				OrderMessageRequest orderReq = new OrderMessageRequest();
				orderReq.setOrder(order);
				System.out.println("sending message to queue: " + order.getCommodityName()+"-" +order.getOrderType() +"-ORDER");
				//jmsTemplate.convertAndSend(order.getCommodityName()+"-" +order.getOrderType() +"-ORDER", orderReq);
				//jmsTemplate.convertAndSend("APPLE-"+orderReq+"-ORDER", orderReq);
				jmsTemplate.convertAndSend("APPLE-BUY-ORDER", orderReq);
			}
		}
		return "";
	}
}
