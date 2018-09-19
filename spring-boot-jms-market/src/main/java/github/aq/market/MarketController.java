package github.aq.market;

import java.util.HashMap;
import java.util.Map;

import github.aq.market.common.fruit.FruitName;

public class MarketController {

	private Map<String, Market> markets;
	
	public MarketController() {
		markets = new HashMap<>();
		markets.put(FruitName.APPLE.name(), new Market());
		markets.put(FruitName.BANANA.name(), new Market());
		markets.put(FruitName.ORANGE.name(), new Market());
	}

	public Map<String, Market> getMarkets() {
		return markets;
	}
	
}
