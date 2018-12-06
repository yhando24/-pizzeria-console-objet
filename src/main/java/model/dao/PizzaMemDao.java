package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import exception.DeletePizzaException;
import exception.NotExistingPizzaException;
import exception.SavePizzaException;
import exception.StockageException;
import exception.UpdatePizzaException;
import model.bean.Categorie;
import model.bean.Pizza;

public class  PizzaMemDao implements IPizzaDao{
	


	
	
	
	
	public  List<Pizza> findAllPizzas() {
		
		Connection  co = null;
		Statement statement = null;
		ResultSet result = null;
		
		
		ArrayList <Pizza> pizzas = new ArrayList<Pizza>();
		try {
			co = Connect.getConnection();
			statement = co.createStatement();
			String query = "Select * from Pizza JOIN categorie ON pizza.ID_CATEGORIE = categorie.id";
			
			
			result = statement.executeQuery(query);
			
			while(result.next()) {
				Pizza pizza = new Pizza();
				pizza.setId(result.getInt(1));
				pizza.setCode(result.getString("CODE"));
				pizza.setDésignation(result.getString("designation"));
				pizza.setPrix(
					result.getFloat("PRIX"));
				pizza.setCategoriePizza(new Categorie(result.getInt(6),result.getString("NOM")));
				
				pizzas.add(pizza);
				
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				result.close();
				statement.close();
				co.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return pizzas;
		
		
	}

	public void updatePizza(String codePizza, Pizza pizza) throws UpdatePizzaException {
		// TODO Auto-generated method stub
		
	}

	public Pizza findPizzaByCode(String codePizza) throws NotExistingPizzaException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isPizzaExists(String codePizza) {
		// TODO Auto-generated method stub
		return false;
	}

	public void addPizza(Pizza pizza) throws SavePizzaException {
		// TODO Auto-generated method stub
		
	}

	public void deletePizza(String codePizza) throws DeletePizzaException {
		// TODO Auto-generated method stub
		
	}


	

	
//	public void updatePizza(String codePizza, Pizza pizzaToModif) throws UpdatePizzaException {	
//		Pizza pizza;
//		try {
//			pizza = findPizzaByCode(codePizza);
//		} catch (NotExistingPizzaException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		Boolean exist = isPizzaExists(codePizza);
//		Boolean sucess = true;
//		if(exist) {
//			try {
//				pizzaToModif.dataControl();
//			} catch (StockageException e) {
//				sucess = false;
//			}
//			if(sucess) {
//				pizza = pizzaToModif;
//				}
//		}
//		else {
//			throw new UpdatePizzaException("La pizza n'existe pas");
//		}
//		
//	}
//
//	
//
//	public Pizza findPizzaByCode(String codePizza) throws NotExistingPizzaException {
//		for (Pizza pizza : pizzas) {
//			if(pizza.getCode().equals(codePizza)) {
//				return pizza;
//			}
//		}
//			throw new NotExistingPizzaException("Cette pizza n'existe pas");
//	}
//
//	
//	public boolean isPizzaExists(String codePizza) {
//		
//		for (Pizza pizza : pizzas) {
//			if(pizza.getCode().equals(codePizza)) {
//				return true;
//			}
//		} return false;
//	}
//
//	
//	
//	public void addPizza(Pizza pizza) throws SavePizzaException {
//		
//		
//		boolean success = true;
//		
//		
//		try {
//			pizza.dataControl();
//		} catch (StockageException e) {
//			success = false;
//			System.out.println("OHP HOP HOP");
//		}
//		
//		
//		
//		if(success) {
//			pizzas.add(pizza);
//		}
//
//		
//	}
//
//	public void deletePizza(String codePizza) throws DeletePizzaException{
//		
//		Boolean exist = isPizzaExists(codePizza);
//		
//		if(exist) {
//			
//		for(int i = 0; i<pizzas.size(); i++) {
//		
//			if(pizzas.get(i).getCode().equals(codePizza)) {
//			 pizzas.remove(pizzas.get(i));
//
//				System.out.println("Pizza supprimé");
//			}
//		}
//		
//
//
//		} else {
//			throw new DeletePizzaException("Le code de la pizza entrer n'existe pas");
//			
//		}
//		
//	}
//
//
//
	public void listPizza() {
		for (Pizza pizza : findAllPizzas()) {
			System.out.println(pizza);
		}
		
	}



}


