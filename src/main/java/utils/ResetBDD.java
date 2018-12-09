package utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.Categorie;
import model.bean.Pizza;

public class ResetBDD {
	
	public static void delete(Connection co) {
		
		
		
	
		Statement statement = null;
		int result=0;
		

		try {
			
			co.setAutoCommit(false);
			statement = co.createStatement();
			
			
			String query = "TRUNCATE pizza";
			result += statement.executeUpdate(query);
			
			query = "SET FOREIGN_KEY_CHECKS = 0";
			result += statement.executeUpdate(query);
			
			query = "TRUNCATE categorie";
			result += statement.executeUpdate(query);
			
			query = "SET FOREIGN_KEY_CHECKS = 1"; 
			result += statement.executeUpdate(query);
			
			
			System.err.println("Donnée effacée");
			co.commit();
		
		} catch (Exception e) {
			try {
				co.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally {
			try {
				co.setAutoCommit(true);
				statement.close();
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		
	}

	
	public static void add(Connection co) {
		
		
		

		Statement statement = null;
		int result=0;
		

		try {
			
			co.setAutoCommit(false);
			statement = co.createStatement();
			
			
			String query = "INSERT INTO `CATEGORIE` (`ID`, `NOM`) VALUES\r\n" + 
					"(4, 'AUTRE'),\r\n" + 
					"(1, 'FROMAGE'),\r\n" + 
					"(3, 'POISSON'),\r\n" + 
					"(2, 'VIANDE')";
			result += statement.executeUpdate(query);
			
			query = "INSERT INTO `PIZZA` (`ID`, `CODE`, `DESIGNATION`, `PRIX`, `ID_CATEGORIE`) VALUES\r\n" + 
					"(1, 'PEP', 'PEPERONI', 7.2, 4),\r\n" + 
					"(2, 'MAR', 'MARGHERITA', 8.2, 1),\r\n" + 
					"(3, 'REI', 'LA REINE', 6.8, 1),\r\n" + 
					"(4, 'FRO', '4 FROMAGES', 8.1, 1),\r\n" + 
					"(5, 'CAN', 'CANADIENNE', 9.3, 2),\r\n" + 
					"(6, 'ORI', 'ORIENTALE', 10.4, 2),\r\n" + 
					"(7, 'IND', 'INDIENNE', 10.2, 4)";
			result += statement.executeUpdate(query);
			

	
			
			
			System.err.println("Donnée remise ");
			co.commit();
		
		} catch (Exception e) {
			try {
				co.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally {
			try {
				co.setAutoCommit(true);
				statement.close();
				co.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
