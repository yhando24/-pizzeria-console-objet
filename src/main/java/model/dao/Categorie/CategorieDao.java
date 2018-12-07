package model.dao.Categorie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exception.NotExistingPizzaException;
import exception.UpdatePizzaException;
import model.bean.Categorie;
import model.bean.Pizza;
import utils.Connect;

public class CategorieDao implements ICategorieDao {

	public ArrayList<Categorie> findAllCategories() {
		
		
		Connection  co = null;
		Statement statement = null;
		ResultSet result = null;
		
		
		ArrayList <Categorie> categories = new ArrayList<Categorie>();
		try {
			co = Connect.getConnection();
			statement = co.createStatement();
			String query = "Select * from Categorie";
			
			
			result = statement.executeQuery(query);
			
			while(result.next()) {
				Categorie categorie = new Categorie();
				categorie.setId(result.getInt("categorie.ID"));
				categorie.setNom(result.getString("NOM"));
			
				
				categories.add(categorie);
				
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
		return categories;
		
	}
	
	public void updateCategorie(int id, String nomCategorie)  {
		
		
		Connection  co = null;
		PreparedStatement statement = null;
		int result;
		try {
			co = Connect.getConnection();
	
			String query = "UPDATE categorie SET NOM = ? WHERE pizza.id ="+id ;
			
			statement = co.prepareStatement(query);
			
			statement.setString(1, nomCategorie);

			
			result = statement.executeUpdate();
			
			System.out.println("result = " + result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
	
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
		
	

	public Categorie findCategorieById(int id) throws NotExistingPizzaException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void listCategorie(ArrayList <Categorie> categories) {
		for (Categorie categorie : categories) {
			System.out.println(categorie);
		}
	}




}
