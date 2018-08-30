package github.aq.fruitmarket.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class FruitGenerator {
	
	BigDecimal[] costs = {new BigDecimal(0.25), new BigDecimal(0.5), new BigDecimal(0.75)};
	int[] quantities = {10, 20, 30};
			
	public int randomSelector(int maxlimit) {
		return ThreadLocalRandom.current().nextInt(maxlimit);
	}
	
	public List<Fruit> generateFruit() {
		List<Fruit> list = new ArrayList<Fruit>();
		
		while(list.size() < 100) {
			int randNameIndex = randomSelector(FruitName.values().length);
			int randCostIndex = randomSelector(costs.length);
			int randQuantityIndex = randomSelector(quantities.length);
			
			Fruit fruit = new Fruit(FruitName.values()[randNameIndex], costs[randCostIndex], quantities[randQuantityIndex]);
			System.out.println(fruit.toString());
			list.add(fruit);
		}
		return list;
	}
	
	public static void main(String[] args) {
		new FruitGenerator().generateFruit();
	}
}
