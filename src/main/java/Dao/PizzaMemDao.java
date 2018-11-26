package Dao;

import classe.Pizza;
import exception.DeletePizzaException;
import exception.SavePizzaException;
import exception.StockageException;
import exception.UpdatePizzaException;
import model.PizzaEnum;

public class  PizzaMemDao implements IPizzaDao{
	
	
	static Pizza [] pizzas = {
			new Pizza("PEP","Péperoni",12.50, PizzaEnum.FROMAGE),
			new Pizza("MAR","Margherita",14.00, PizzaEnum.FROMAGE),
			new Pizza("REIN","La Reine",11.50, PizzaEnum.FROMAGE),
			new Pizza("FRO","La 4 fromages",12.00, PizzaEnum.FROMAGE),
			new Pizza("CAN","La cannibale",12.50, PizzaEnum.VIANDE),
			new Pizza("SAV","La savoyarde",13.00, PizzaEnum.POISSON),
			new Pizza("ORI","L'orientale",13.50, PizzaEnum.POISSON),
			new Pizza("IND","L'indienne",14.00, PizzaEnum.FROMAGE),
};
	
	
	
	
	public void listPizza() {
		
		System.out.println(" Liste des pizzas");
		for (Pizza pizza : pizzas) {
			System.out.println(pizza.toString());			
		}	
	}
	
	
	
	
	public  Pizza[] findAllPizzas() {
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
				pizzas[pizza.getId()] = pizzaToModif;
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
		
		// creation du tableau temporaire de pizza et attribution des valeurs de l'ancien tableau
		Pizza [] pizzasTemp = new Pizza[pizzas.length+1];			
		System.out.println(pizzasTemp.length);
		for(int i = 0; i<pizzas.length; i++) {
			pizzasTemp[i] = pizzas[i];
			
		}
		
		// changment de la taille de l'ancien tableau et recuperation des anciennes valeurs
		pizzas = pizzasTemp;
		
		
		// rajout a la fin du tableau de la nouvelle pizza

		
		
		if(success) {
			pizzas[pizzas.length-1] = pizza;
		}

		
	}

	public void deletePizza(String codePizza) throws DeletePizzaException{
		
		Boolean exist = isPizzaExists(codePizza);
		
		if(exist) {
			
		
		// creation d'un tableau temporaire plus petit que l'ancien
		Pizza [] pizzasTemp2 = new Pizza[pizzas.length-1];
		
		
	// utilisation d'une variable i pour lajout des pizza a garder dans le tableau
		int i = 0;
		
		for (Pizza pizzaDelete : pizzas) {
			// si le code de la pizza n'est pas celui a supprimer on le rajoute au tableau
			if(!pizzaDelete.getCode().equals(codePizza)) {
			pizzasTemp2[i] = pizzaDelete;
			i++;
			}
		}
		
		// modification de la taille de l'ancien tableau et copie des données
		pizzas = new Pizza[pizzasTemp2.length];
		pizzas = pizzasTemp2;
		
		
		System.out.println("Pizza supprimé");
		} else {
			throw new DeletePizzaException("Le code de la pizza entrer n'existe pas");
			
		}
		
	}



}
