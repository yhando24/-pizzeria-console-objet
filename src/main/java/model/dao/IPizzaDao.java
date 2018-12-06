package model.dao;

import java.util.InputMismatchException;
import java.util.List;

import exception.DeletePizzaException;
import exception.NotExistingPizzaException;
import exception.SavePizzaException;
import exception.UpdatePizzaException;
import model.bean.Pizza;

public interface IPizzaDao {
	

	List <Pizza> findAllPizzas();
	void updatePizza(String codePizza, Pizza pizza) throws UpdatePizzaException;
	Pizza findPizzaByCode(String codePizza) throws NotExistingPizzaException;
	boolean isPizzaExists(String codePizza);
	void addPizza(Pizza pizza) throws SavePizzaException;
	void deletePizza(String codePizza) throws DeletePizzaException;
	void listPizza();
	
}
