<<<<<<< HEAD
package github.aq.market;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class MarketConfiguration {

	@Bean("MarketController")
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	public MarketController getMarketController() {
		// return Queue
		return new MarketController();
	}
	
	
}
=======
package github.aq.market;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class MarketConfiguration {

	@Bean
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	public Object getMarketController() {
		// return Queue
		return new Market();
	}
	
	
}
>>>>>>> 8c09f5362fec9c4677e317a88320840f48d842b1
