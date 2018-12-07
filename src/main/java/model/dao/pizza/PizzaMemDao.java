package model.dao.pizza;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import exception.DeletePizzaException;
import exception.NotExistingPizzaException;
import exception.SavePizzaException;
import exception.StockageException;
import exception.UpdatePizzaException;
import model.bean.Categorie;
import model.bean.Pizza;
import utils.Connect;

public class  PizzaMemDao implements IPizzaDao{
	


	
	
	
	
	public  ArrayList<Pizza> findAllPizzas() {
		
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
				pizza.setCategoriePizza(new Categorie(result.getInt("categorie.id"),result.getString("NOM")));
				
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
	
	
	
	public Pizza findPizzaById(int id) throws NotExistingPizzaException {
		
		Connection  co = null;
		Statement statement = null;
		ResultSet result = null;
		
		
		 Pizza pizza = new Pizza();
		try {
			co = Connect.getConnection();
			statement = co.createStatement();
			String query = "Select * from Pizza JOIN categorie ON pizza.id_categorie = categorie.id where pizza.id ="+id ;
			
			
			result = statement.executeQuery(query);
			
			while(result.next()) {
			
				pizza.setId(result.getInt(1));
				pizza.setCode(result.getString("CODE"));
				pizza.setDésignation(result.getString("designation"));
				pizza.setPrix(
					result.getFloat("PRIX"));
				pizza.setCategoriePizza(new Categorie(result.getInt("ID_CATEGORIE"),result.getString("NOM")));
				
			
				
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new NotExistingPizzaException("Cette pizza n'existe pas");
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
	
		return pizza;
}
	
	
	
	public  ArrayList<Pizza> findPizzasByCategorieName(String name) {
		
		
		Connection  co = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		
		
		ArrayList <Pizza> pizzas = new ArrayList<Pizza>();
		try {
			co = Connect.getConnection();
	
			String query = "Select * from Pizza JOIN categorie ON pizza.ID_CATEGORIE = categorie.id where categorie.NOM =?";
			preparedStatement = co.prepareStatement(query);
			preparedStatement.setString(1, name);
			result = preparedStatement.executeQuery();
			
			while(result.next()) {
				Pizza pizza = new Pizza();
				pizza.setId(result.getInt(1));
				pizza.setCode(result.getString("CODE"));
				pizza.setDésignation(result.getString("designation"));
				pizza.setPrix(
					result.getFloat("PRIX"));
				pizza.setCategoriePizza(new Categorie(result.getInt("categorie.id"),result.getString("NOM")));
				
				pizzas.add(pizza);
			
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				result.close();
				preparedStatement.close();
				co.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.err.println("Les pizza de la categorie "+ name + " : ");
		return pizzas;
	}
	
	
	
	public boolean isPizzaExists(int id) {
	
		boolean exist = true;
		Connection  co = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		
		
		 Pizza pizza = new Pizza();
		try {
			co =Connect.getConnection();

			String query = "Select * from Pizza JOIN categorie ON pizza.id_categorie = categorie.id where pizza.id =?" ;
			
		
			statement = co.prepareStatement(query);
			
			statement.setInt(1, id );
			
			result = statement.executeQuery();
			
			if (!result.isBeforeFirst() ) {    
			   exist = false;
			} 	
		
			while(result.next()) {
			
				pizza.setId(result.getInt(1));
				pizza.setCode(result.getString("CODE"));
				pizza.setDésignation(result.getString("DESIGNATION"));
				pizza.setPrix(result.getFloat("PRIX"));
				pizza.setCategoriePizza(new Categorie(result.getInt("categorie.id"),result.getString("NOM")));
				
				}
			
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
		
				
					statement.close();
					co.close();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("SA MARCHE PAS");
				}
	}

		return exist;
			
}
	
	
	
	
	
	

	public void updatePizza(int id, Pizza pizzaToModif) throws UpdatePizzaException {	
	Pizza pizza;

	Boolean exist = isPizzaExists(id);
	Boolean sucess = true;
	
	if(exist) {

		
			try {
				pizzaToModif.dataControl();
			} catch (StockageException e1) {
				System.err.println("pizza non conforme : " +e1.getMessage());
				System.err.println("Impossible de mettre a jour la pizza");
				sucess = false;
				
			}
			
			if(sucess) {
				
		
				Connection  co = null;
				PreparedStatement statement = null;
				int result;
				try {
					co = Connect.getConnection();
			
					String query = "UPDATE pizza SET CODE = ?, DESIGNATION = ?, PRIX = ?, ID_CATEGORIE = ? WHERE pizza.id ="+id ;
					
					statement = co.prepareStatement(query);
					
					statement.setString(1, pizzaToModif.getCode());
					statement.setString(2, pizzaToModif.getDésignation());
					statement.setFloat(3, pizzaToModif.getPrix());
					statement.setInt(4, pizzaToModif.getCategoriePizza().getId());
					
					result = statement.executeUpdate();
					
					System.out.println("result = " + result);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					sucess = false;
			
				}finally {
					try {
						statement.close();
						co.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
		
			}
		
	}	else {
		throw new UpdatePizzaException("La pizza n'existe pas");
	}
}





	public void addPizza(Pizza pizza) throws SavePizzaException {
		
		
		boolean success = true;
		
		
		try {
			pizza.dataControl();
		} catch (StockageException e) {
			success = false;
			System.out.println("OHP HOP HOP");
		}
		
		
		
		if(success) {
	
			Connection co = null;
			PreparedStatement prepareStatement = null;
			
			try {
				co = Connect.getConnection();
				String query = "INSERT INTO pizza(CODE,DESIGNATION, PRIX, ID_CATEGORIE)VALUES(?,?,?,?)";
				prepareStatement = co.prepareStatement(query);
				prepareStatement.setString(1, pizza.getCode());
				prepareStatement.setString(2, pizza.getDésignation());
				prepareStatement.setFloat(3, pizza.getPrix());
				prepareStatement.setInt(4, pizza.getCategoriePizza().getId());
				
				prepareStatement.executeUpdate();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					prepareStatement.close();
					co.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			
		}

		
	}

	
	
	public void deletePizza(int id) throws DeletePizzaException{
		
		Boolean exist = isPizzaExists(id);
		System.out.println(exist +" laa");
		if(exist) {
			
			Connection co = null;
			Statement statement = null;
			int result;
			
			try {
				co = Connect.getConnection();
				statement = co.createStatement();
				String query = "DELETE FROM pizza WHERE id="+id;
				result = statement.executeUpdate(query);
				System.err.println("Pizza supprimé");
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
				
					statement.close();
					co.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}
			
		


		} else {
			throw new DeletePizzaException("L'id de la pizza entrer n'existe pas");
			
		}
		
	}


//
	public void listPizza(ArrayList <Pizza> pizzas) {
		for (Pizza pizza : pizzas) {
			System.out.println(pizza);
		}
		
	}







}


