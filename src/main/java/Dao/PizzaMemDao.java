package Dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Bean.Pizza;
import exception.DeletePizzaException;
import exception.SavePizzaException;
import exception.StockageException;
import exception.UpdatePizzaException;
import model.PizzaEnum;

public class  PizzaMemDao implements IPizzaDao{
	
	 static ArrayList<Pizza> pizzas = new ArrayList<Pizza>();
	static {
	
	 
	pizzas.add(	new Pizza("PEP","Péperoni",12.50, PizzaEnum.FROMAGE));
	pizzas.add(new Pizza("MAR","Margherita",14.00, PizzaEnum.FROMAGE));
	pizzas.add(	new Pizza("REIN","La Reine",11.50, PizzaEnum.FROMAGE));
	pizzas.add(	new Pizza ("FRO","La 4 fromages",12.00, PizzaEnum.FROMAGE));
	pizzas.add(	new Pizza("CAN","La cannibale",12.50, PizzaEnum.VIANDE));
	pizzas.add(	new Pizza("SAV","La savoyarde",13.00, PizzaEnum.POISSON));
	pizzas.add(	new Pizza("ORI","L'orientale",13.50, PizzaEnum.POISSON));
	pizzas.add(	new Pizza("IND","L'indienne",14.00, PizzaEnum.FROMAGE));
	
	
	}

	
	
	
	
	public  List<Pizza> findAllPizzas() {
		return pizzas;
	}
	

	
	public void updatePizza(String codePizza, Pizza pizzaToModif) throws UpdatePizzaException {	
		Pizza pizza = findPizzaByCode(codePizza);
		Boolean exist = isPizzaExists(codePizza);
		Boolean sucess = true;
		if(exist) {
			try {
				pizzaToModif.dataControl();
			} catch (StockageException e) {
				sucess = false;
			}
			if(sucess) {
				pizza = pizzaToModif;
				}
		}
		else {
			throw new UpdatePizzaException("La pizza n'existe pas");
		}
		
	}

	

	public Pizza findPizzaByCode(String codePizza) {
		for (Pizza pizza : pizzas) {
			if(pizza.getCode().equals(codePizza)) {
				return pizza;
			}
		}
			return null;
	}

	
	public boolean isPizzaExists(String codePizza) {
		
		for (Pizza pizza : pizzas) {
			if(pizza.getCode().equals(codePizza)) {
				return true;
			}
		} return false;
	}

	
	
	public void addPizza(Pizza pizza) throws SavePizzaException {
		
		
		boolean success = true;
		
		
		try {
			pizza.dataControl();
		} catch (StockageException e) {
			success = false;
		}
		
		
		
		if(success) {
			pizzas.add(pizza);
		}

		
	}

	public void deletePizza(String codePizza) throws DeletePizzaException{
		
		Boolean exist = isPizzaExists(codePizza);
		
		if(exist) {
			
		for(int i = 0; i<pizzas.size(); i++) {
		
			if(pizzas.get(i).getCode().equals(codePizza)) {
			 pizzas.remove(pizzas.get(i));

				System.out.println("Pizza supprimé");
			}
		}
		


		} else {
			throw new DeletePizzaException("Le code de la pizza entrer n'existe pas");
			
		}
		
	}



	public void listPizza() {
		for (Pizza pizza : pizzas) {
			System.out.println(pizza);
		}
		
	}



}


