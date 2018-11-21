package Dao;

import classe.Pizza;

public interface IPizzaDao {
	

	Pizza[] findAllPizzas();
	void updatePizza(String codePizza, Pizza pizza);
	Pizza findPizzaByCode(String codePizza);
	boolean isPizzaExists(String codePizza);
	void addPizza(Pizza pizza);
	void deletePizza(String codePizza);
	void listPizza();
	
}
