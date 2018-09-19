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
