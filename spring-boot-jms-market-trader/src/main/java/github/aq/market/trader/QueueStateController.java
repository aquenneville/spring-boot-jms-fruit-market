<<<<<<< HEAD
package github.aq.market.trader;

import java.io.Serializable;
import java.util.Enumeration;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.QueueBrowser;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jms.core.BrowserCallback;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import github.aq.market.common.jms.ActiveMQConfig;
import github.aq.market.common.jms.OrderMessageRequest;

@RestController
@RequestMapping("/trader-api/v1/queue")
public class QueueStateController {

	@Autowired
	JmsTemplate jmsTemplate;
	
	@RequestMapping(path = "/count", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody QueueCount count() throws JMSException {
		QueueCount qc = new QueueCount();
		qc.buyOrders = browse(ActiveMQConfig.BUY_ORDER_QUEUE);
		qc.sellOrders = browse(ActiveMQConfig.SELL_ORDER_QUEUE);
		qc.bananaBuyOrders = browse("BANANA-BUY-ORDER");
		qc.bananaSellOrders = browse("BANANA-SELL-ORDER");
		qc.orangeBuyOrders = browse("ORANGE-BUY-ORDER");
		qc.orangeSellOrders = browse("ORANGE-SELL-ORDER");
		return qc;
	}
	
	private int browse(String destinationName) {
		int count = this.jmsTemplate.browse(destinationName, new BrowserCallback<Integer>() {
			public Integer doInJms(final Session session, final QueueBrowser browser) throws JMSException {
				@SuppressWarnings("unchecked")
				Enumeration<Message> enumeration = browser.getEnumeration();
				int counter = 0;
				while (enumeration.hasMoreElements()) {
					Message msg = (Message) enumeration.nextElement();
					//OrderMessageRequest omr = (OrderMessageRequest) msg.getObjectProperty("_type");
					System.out.println(msg);
					counter += 1;
				}
				return counter;
			}
		});
		return count;
	}

	class QueueCount implements Serializable {
		public int buyOrders;
		public int sellOrders;
		public int bananaBuyOrders;
		public int bananaSellOrders;
		public int orangeBuyOrders;
		public int orangeSellOrders;
	}
}
=======
package github.aq.market.trader;

import java.io.Serializable;
import java.util.Enumeration;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.QueueBrowser;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jms.core.BrowserCallback;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/queue")
public class QueueStateController {

	@Autowired
	JmsTemplate jmsTemplate;
	
	@RequestMapping(path = "/count", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody QueueCount count() throws JMSException {
		QueueCount qc = new QueueCount();
		qc.appleBuyOrders = browse("APPLE-BUY-ORDER");
		qc.appleSellOrders = browse("APPLE-SELL-ORDER");
		qc.bananaBuyOrders = browse("BANANA-BUY-ORDER");
		qc.bananaSellOrders = browse("BANANA-SELL-ORDER");
		qc.orangeBuyOrders = browse("ORANGE-BUY-ORDER");
		qc.orangeSellOrders = browse("ORANGE-SELL-ORDER");
		return qc;
	}
	
	private int browse(String destinationName) {
		int count = this.jmsTemplate.browse(destinationName, new BrowserCallback<Integer>() {
			public Integer doInJms(final Session session, final QueueBrowser browser) throws JMSException {
				Enumeration<Message> enumeration = browser.getEnumeration();
				int counter = 0;
				while (enumeration.hasMoreElements()) {
					Message msg = (Message) enumeration.nextElement();
					counter += 1;
				}
				return counter;
			}
		});
		return count;
	}

	class QueueCount implements Serializable {
		public int appleBuyOrders;
		public int appleSellOrders;
		public int bananaBuyOrders;
		public int bananaSellOrders;
		public int orangeBuyOrders;
		public int orangeSellOrders;
	}
}
>>>>>>> 8c09f5362fec9c4677e317a88320840f48d842b1
