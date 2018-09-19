package github.aq.broker;

import java.net.URI;

import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.broker.TransportConnector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class MarketBrokerApplication {

	@Bean
	public BrokerService createBrokerService() throws Exception {
		BrokerService broker = new BrokerService();
		broker.setPersistent(true);	
		broker.setUseJmx(false);
		TransportConnector connector = new TransportConnector();
		connector.setUri(new URI("tcp://localhost:61616"));		 
		broker.addConnector(connector);

		return broker;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(MarketBrokerApplication.class, args);
	}
}
