package github.aq.fruitmarket.common;

import java.math.BigDecimal;

public class Fruit {

	FruitName name;
	BigDecimal cost;
	int quantity;
	
	public Fruit(FruitName name, BigDecimal cost, int quantity) {
		this.name = name;
		this.cost = cost;
		this.quantity = quantity;
	}
	
	public String toString() {
		return "Fruit name: " + name + " cost: " + cost + " quantity: " + quantity;
	}
}
