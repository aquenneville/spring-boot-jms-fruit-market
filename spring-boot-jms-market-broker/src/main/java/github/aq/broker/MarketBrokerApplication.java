package github.aq.broker;

import java.net.URI;

import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.broker.TransportConnector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
<<<<<<< HEAD
=======
import org.springframework.context.ConfigurableApplicationContext;
>>>>>>> 8c09f5362fec9c4677e317a88320840f48d842b1
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class MarketBrokerApplication {

	@Bean
	public BrokerService createBrokerService() throws Exception {
		BrokerService broker = new BrokerService();
		broker.setPersistent(true);	
<<<<<<< HEAD
		//broker.setUseJmx(false);
=======
		broker.setUseJmx(false);
>>>>>>> 8c09f5362fec9c4677e317a88320840f48d842b1
		TransportConnector connector = new TransportConnector();
		connector.setUri(new URI("tcp://localhost:61616"));		 
		broker.addConnector(connector);

		return broker;
	}
	
	public static void main(String[] args) {
<<<<<<< HEAD
		SpringApplication.run(MarketBrokerApplication.class, args);
=======
		ConfigurableApplicationContext context = SpringApplication.run(MarketBrokerApplication.class, args);
>>>>>>> 8c09f5362fec9c4677e317a88320840f48d842b1
	}
}
