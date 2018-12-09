package model.dao.pizza;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import exception.DeletePizzaException;
import exception.NotExistingPizzaException;
import exception.SavePizzaException;
import exception.UpdatePizzaException;
import model.bean.Pizza;

public interface IPizzaDao {
	

	List <Pizza> findAllPizzas();
	void updatePizza(int id, Pizza pizza) throws UpdatePizzaException;
	Pizza findPizzaById(int id) throws NotExistingPizzaException;
	boolean isPizzaExists(int id);
	void addPizza(Pizza pizza) throws SavePizzaException;
	void deletePizza(int id) throws DeletePizzaException;
	void listPizza(ArrayList <Pizza> pizzas);
	
}
