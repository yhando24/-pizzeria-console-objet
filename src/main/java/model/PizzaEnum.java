package model;

public enum PizzaEnum {
	
	
	FROMAGE ("Fromage"),
	VIANDE ("Viande"),
	POISSON("Poisson"),
	AUTRE("Autre");

	
	
	private String CategoriePizza;

	
	
	private PizzaEnum(String categoriePizza) {
		CategoriePizza = categoriePizza;
	}

	
	
	
	public String getCategoriePizza() {
		return CategoriePizza;
	}

	public void setCategoriePizza(String categoriePizza) {
		CategoriePizza = categoriePizza;
	}
	
	
	
}
